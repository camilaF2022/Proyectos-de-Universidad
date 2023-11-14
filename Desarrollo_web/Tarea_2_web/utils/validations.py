import re
import filetype

def validar_region(region):
    if region == "":
        return False
    return True

def validar_comuna(comuna):
    if comuna == "":
        return False
    return True

def validar_artesania(artesanias):
    if 1 <= len(artesanias) <= 3:
        return True
    return False

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
    if 3 <= len(nombre) <= 80:
        return True
    return False

def validar_email(email):
    exp_reg = r'^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$'
    return re.match(exp_reg, email) is not None

def validar_numero_celular(numero):
    exp_reg2 = r'^(\d{9,15})?$'
    return re.match(exp_reg2, numero) is not None

def validar_artesano(region, comuna, artesanias, descripcion, file, nombre, email, numero):
    return (
        validar_region(region) and
        validar_comuna(comuna) and
        validar_artesania(artesanias) and
        validar_descripcion(descripcion) and
        validate_files(file) and
        validar_nombre(nombre) and
        validar_email(email) and
        validar_numero_celular(numero)
    )
