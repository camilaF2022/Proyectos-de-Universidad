import re
import filetype
#transporte, comuna, region
def validar_vacio(variable):
    if variable == "":
        return False
    return True

#deporte y artesania
def validar_deporte_artesania(variable):
    return 1 <= len(variable) <= 3

def validar_descripcion(descripcion):
    lineas = descripcion.split('\n')
    if len(lineas) <= 4:
        for linea in lineas:
            if len(linea) > 50:
                return False
        return True
    return False


def validate_files(files):
    ALLOWED_EXTENSIONS = {"png", "jpg", "jpeg", "gif"}
    ALLOWED_MIMETYPES = {"image/jpeg", "image/png", "image/gif"}
    
    for file in files:
        # check if a file was submitted
        if file is None:
            return False

        # check if the browser submitted an empty file
        if file.filename == "":
            return False
        
        # check file extension and mimetype for each file in the list
        ftype_guess = filetype.guess(file)
        if ftype_guess.extension not in ALLOWED_EXTENSIONS or ftype_guess.mime not in ALLOWED_MIMETYPES:
            return False
    
    # If all files are valid, return True
    print("si")
    return True


def validar_nombre(nombre):
    return 3 <= len(nombre) <= 80

def validar_email(email):
    exp_reg = r'^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$'
    return re.match(exp_reg, email) is not None

def validar_numero_celular(numero):
    exp_reg2 = r'^(\d{9,15})?$'
    return re.match(exp_reg2, numero) is not None

def validar_comentarios(comentario):
    return len(comentario) <= 80


def validar_artesano(region, comuna, artesanias, descripcion, file, nombre, email, numero):
    return (
        validar_vacio(region) and
        validar_vacio(comuna) and
        validar_deporte_artesania(artesanias) and
        validar_descripcion(descripcion) and
        validate_files(file) and
        validar_nombre(nombre) and
        validar_email(email) and
        validar_numero_celular(numero)
    )

def validar_hincha(region, comuna, deporte, comentario, transporte, nombre, email, numero):
    return (
        validar_vacio(region) and
        validar_vacio(comuna) and
        validar_deporte_artesania(deporte) and
        validar_comentarios(comentario) and
        validar_vacio(transporte) and
        validar_nombre(nombre) and
        validar_email(email) and
        validar_numero_celular(numero)
    )
