from flask import Flask, render_template, request, redirect, url_for ,  jsonify, session
from database import db
from werkzeug.utils import secure_filename
import filetype
import hashlib
import os
from utils.validations import validar_artesano, validar_artesania,validar_comuna,validar_descripcion,validar_email,validar_nombre,validar_numero_celular,validar_region,validate_files
UPLOAD_FOLDER = 'static/uploads'

app = Flask(__name__)
app.secret_key = 's3cr3t_k3y'
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

@app.route("/")
def index():
    return render_template("index.html")
   
@app.route("/registrar-artesano")
def registrar_artesano():
    regiones = db.get_regiones()
    tipo_artesania = db.get_tipo_artesania()
    return render_template("artesanos/registrar-artesano.html", regiones=regiones,tipo_artesania=tipo_artesania)

@app.route("/registrar-hincha")
def registrar_hincha():
    return render_template("hinchas/registrar-hincha.html")

@app.route("/mostrar-hincha")
def mostrar_hincha():
    return render_template("hinchas/ver-hincha.html")

@app.route("/obtener-comunas")
def obtener_comunas():
    region_id = request.args.get("region_id")
    comunas = db.get_comunas(region_id)  
    return jsonify(comunas)


@app.route("/procesar-formulario", methods=["POST"])
def procesar_formulario():
    region = request.form.get("region")
    comuna = request.form.get("comuna")
    tipo_artesanias = request.form.getlist("artesania[]")
    descripcion = request.form.get("descripcion")
    nombre = request.form.get("nombre")
    email = request.form.get("email")
    numero = request.form.get("numero")
    files = request.files.getlist("files")

    if validar_artesano(region, comuna, tipo_artesanias, descripcion, files, nombre, email, numero):
        img_filenames = []
        for file in files:
            _filename = hashlib.sha256(secure_filename(file.filename).encode("utf-8")).hexdigest()
            _extension = filetype.guess(file).extension
            img_filename = f"{_filename}.{_extension}"
            img_path = os.path.join(app.config["UPLOAD_FOLDER"], img_filename)
            file.save(img_path)
            img_filenames.append(img_filename)
        db.insert_database(comuna, descripcion, nombre, email, numero, tipo_artesanias, img_filenames, UPLOAD_FOLDER)

    return redirect(url_for('mostrar_datos'))



@app.route("/ver-artesanos")
def mostrar_datos():
    page = request.args.get('page', 1, type=int)  
    per_page = 5 

    total_artesanos = db.get_numero_total_artesanos()

    artesanos, total_pages, current_page = db.get_artesanos_paginados(per_page=per_page, offset=0, total_artesanos=total_artesanos, current_page=page)

    datos_formulario = []

    for artesano in artesanos:
        artesano_id = artesano[0]
        comuna_nombre = artesano[1]
        nombre = artesano[2]
        numero = artesano[3]
        tipos_artesania = db.get_tipos_artesania_por_artesano(artesano[0])
        foto_info = db.get_foto_info_por_artesano(artesano[0])

        img_paths = [url_for('static', filename=f"uploads/{img}") for img in foto_info]
        datos_formulario.append({
            "id": artesano_id,
            "nombre": nombre,
            "numero": numero,
            "comuna": comuna_nombre,
            "tipo_artesanias": tipos_artesania,
            "img_path": img_paths
        })

    return render_template("artesanos/ver-artesanos.html", datos_formulario=datos_formulario, total_pages=total_pages, current_page=page)

@app.route("/ver-detalles-artesano/<int:artesano_id>", methods=["GET"])
def ver_detalles_artesano(artesano_id):
 
    detalles_artesano = db.obtener_artesano(artesano_id) 
    region = db.obtener_region_por_comuna(artesano_id)
    tipos_artesania = db.get_tipos_artesania_por_artesano(artesano_id)
    foto = db.get_foto_info_por_artesano(artesano_id)
    img_paths = [url_for('static', filename=f"uploads/{img}") for img in foto]
    descripcion = detalles_artesano[2]
    nombre = detalles_artesano[3]
    email = detalles_artesano[4]
    numero = detalles_artesano[5]
    comuna = detalles_artesano[1]
    
    datos_info ={
            "nombre": nombre,
            "numero": numero,
            "comuna": comuna,
            "email": email,
            "region": region,
            "descripcion": descripcion,
            "tipo_artesanias": tipos_artesania,
            "img_path": img_paths
        }
    
    return render_template("artesanos/informacion-artesano.html", datos_info=datos_info)

if __name__ == '__main__':
    app.run(debug=True)
  
