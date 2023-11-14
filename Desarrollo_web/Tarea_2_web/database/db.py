import pymysql

DB_NAME = "tarea2"
DB_USERNAME = "cc5002"
DB_PASSWORD = "programacionweb"
DB_HOST = "localhost"
DB_PORT = 3306
DB_CHARSET = "utf8"

def getConnection():
	conn = pymysql.connect(
		db=DB_NAME,
		user=DB_USERNAME,
		passwd=DB_PASSWORD,
		host=DB_HOST,
		port=DB_PORT,
		charset=DB_CHARSET
	)
	return conn

def get_regiones():
    conn = getConnection()
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM region")
    regiones = cursor.fetchall()
    conn.close()
    return regiones  # Esta línea también está correctamente alineada

def get_comunas(region_id):
    conn = getConnection()
    cursor = conn.cursor()
    query = "SELECT * FROM comuna JOIN region ON comuna.region_id = region.id WHERE region.id = %s"
    cursor.execute(query, (region_id,))
    comunas = cursor.fetchall()
    conn.close()
    return comunas

def get_tipo_artesania():
    conn = getConnection()
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM tipo_artesania")
    tipos_artesania = cursor.fetchall()
    conn.close()
    return tipos_artesania

def insert_database(comuna, descripcion, nombre, email, celular, tipo_artesania, img_filenames,uploads):
    conn = getConnection()
    cursor = conn.cursor()
    query = "INSERT INTO artesano (comuna_id, descripcion_artesania, nombre, email, celular) VALUES (%s, %s, %s, %s, %s)"
    cursor.execute(query, (comuna, descripcion, nombre, email, celular))
    conn.commit()

    artesano_id = cursor.lastrowid  # Obtener el lastrowid después de la inserción en 'artesano'

    for img_filename in img_filenames:
        query_fotos = "INSERT INTO foto (artesano_id, nombre_archivo, ruta_archivo) VALUES (%s, %s, %s)"
        cursor.execute(query_fotos, (artesano_id, img_filename, uploads))
        conn.commit()

    for tipo_id in tipo_artesania:
        query2 = "INSERT INTO artesano_tipo (artesano_id, tipo_artesania_id) VALUES (%s,%s)"
        cursor.execute(query2, (artesano_id, tipo_id))
        conn.commit()


def get_tipos_artesania_por_artesano(artesano_id):
    conn = getConnection()
    cursor = conn.cursor()
    query = "SELECT tipo_artesania.nombre FROM tipo_artesania JOIN artesano_tipo ON tipo_artesania.id = artesano_tipo.tipo_artesania_id WHERE artesano_tipo.artesano_id = %s"
    cursor.execute(query, (artesano_id,))
    tipos_artesania = cursor.fetchall()
    conn.close()
    return [tipo[0] for tipo in tipos_artesania]


def get_foto_info_por_artesano(artesano_id):
    conn = getConnection()
    cursor = conn.cursor()
    query = "SELECT foto.nombre_archivo FROM foto WHERE artesano_id = %s"
    cursor.execute(query, (artesano_id,))
    foto_info = cursor.fetchall()
    conn.close()
    return [foto[0] for foto in foto_info]


def get_artesanos_paginados(offset, per_page, total_artesanos, current_page):
    # Calcular el número total de páginas
    total_pages = total_artesanos // per_page + (total_artesanos % per_page != 0)

    # Validar la página actual
    if current_page < 1:
        current_page = 1
    elif current_page > total_pages:
        current_page = total_pages

    # Calcular el OFFSET para la consulta SQL
    offset = (current_page - 1) * per_page

    # Obtener los artesanos para la página actual
    conn = getConnection()
    cursor = conn.cursor()
    query = "SELECT artesano.id, comuna.nombre AS comuna_nombre, artesano.nombre, artesano.celular FROM artesano JOIN comuna ON artesano.comuna_id = comuna.id LIMIT %s OFFSET %s"
    cursor.execute(query, (per_page, offset))
    artesanos = cursor.fetchall()
    conn.close()

    return artesanos, total_pages, current_page


def get_numero_total_artesanos():
    conn = getConnection()
    cursor = conn.cursor()
    cursor.execute("SELECT COUNT(*) FROM artesano")
    total_artesanos = cursor.fetchone()[0]
    conn.close()
    return total_artesanos

def obtener_region_por_comuna(artesano_id):
    conn = getConnection()
    cursor = conn.cursor()

    cursor.execute("SELECT comuna_id FROM artesano WHERE id = %s", (artesano_id,))
    comuna_id = cursor.fetchone()[0]
   
    query = "SELECT region.nombre FROM region JOIN comuna ON comuna.region_id = region.id WHERE comuna.id = %s"
    cursor.execute(query, (comuna_id,))
    region_nombre = cursor.fetchone()

    conn.close()
    return region_nombre[0]

def obtener_artesano(artesano_id):
    conn = getConnection()
    cursor = conn.cursor()
    query = "SELECT artesano.id, comuna.nombre AS comuna_nombre, artesano.descripcion_artesania, artesano.nombre, artesano.email, artesano.celular FROM artesano JOIN comuna ON artesano.comuna_id = comuna.id WHERE artesano.id = %s"
    cursor.execute(query, (artesano_id,))
    artesano = cursor.fetchone()  
    conn.close()
    return artesano
