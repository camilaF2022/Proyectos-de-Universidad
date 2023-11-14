document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("region").onchange = function() {
        const selectedRegionId = this.value;
        const comunasDropdown = document.getElementById("comuna");

        const xhr = new XMLHttpRequest();
        xhr.open("GET", "/obtener-comunas?region_id=" + selectedRegionId, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                comunasDropdown.innerHTML = '<option value="" selected>Seleccione una comuna</option>';
                const comunas = JSON.parse(xhr.responseText);
                comunas.forEach(function(comuna) {
                    const option = document.createElement("option");
                    option.value = comuna[0];
                    option.innerText = comuna[1];
                    comunasDropdown.appendChild(option);
                });
            }
        };
        xhr.send();
    };

    const validarFormulario = () => {
        // Funciones auxiliares 
        const validarRegion = (region) => {
            if (region === "") {
            return false;
            }
            return true;
        };

        const validarComuna = (comuna) => {
            if (comuna === "" ) {
            return false;
            }
            return true;
        };
        
        const validarArtesania = (artesania) => {
            if (artesania.length < 1 || artesania.length > 3) {
            return false;
            }
            return true;
        };

        const validarDescripcion = (descripcion) => {
            const lineas = descripcion.split('\n');
            if (lineas.length <= 4) {
                for (const linea of lineas) {
                if (linea.length > 50) {
                    return false;
                }
                }
                return true;
            }
            return false;
        };
        const validarFiles = (files) => {
            if (!files) return false;
        
            // number of files validation
            let lengthValid = 1 <= files.length && files.length <= 3;
        
            // file type validation
            let typeValid = true;
        
            for (const file of files) {
            // file.type should be "image/<foo>" or "application/pdf"
            let fileFamily = file.type.split("/")[0];
            typeValid &&= fileFamily == "image";
            }
        
            // return logic AND of validations.
            return lengthValid && typeValid;
        };
        
        const validarNombre = (nombre) => {
            if (nombre.length < 3 || nombre.length > 80) {
            return false;
            }
            return true;
        };
        
        const validarEmail = (email) => {
            const expReg =  /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/ ;
            return expReg.test(email);
        };

        const validarNumeroCelular = (numero) => {
            const expReg2 = /^(\d{9,15})?$/; 
            return expReg2.test(numero);
        };

        
        let regionSeleccionada = document.getElementById("region").value;
        let comunaSeleccionada = document.getElementById("comuna").value;
        let artesaniaSeleccionada = document.querySelectorAll('input[name="artesania[]"]:checked');
        let descripcionSeleccionada = document.getElementById("descripcion").value;
        let filesSeleccionados = document.getElementById("files").files;
        let nombreSeleccionado = document.getElementById("nombre").value;
        let emailSeleccionado = document.getElementById("email").value;
        let numeroSeleccionado = document.getElementById("numero").value;
        let mensajeError = "";

        
        if (!validarRegion(regionSeleccionada)) {
            mensajeError += "Debe seleccionar una región válida.\n";
        }
        if (!validarComuna(comunaSeleccionada)) {
            mensajeError += "Debe seleccionar una comuna válida.\n";
        }
        if (!validarArtesania(artesaniaSeleccionada)) {
            mensajeError += "Debe seleccionar entre 1 y 3 artesanias.\n";
        }
        
        if (!validarDescripcion(descripcionSeleccionada)) {
            mensajeError += "No pueden ser mas de 4 filas y 50 columnas.\n";
        }
        if (!validarFiles(filesSeleccionados)) {
            mensajeError += "Archvios no validos.\n";
        }
        
        if (!validarNombre(nombreSeleccionado)) {
            mensajeError += "El nombre debe tener entre 3 y 80 caracteres.\n";
        }
        
        if (!validarEmail(emailSeleccionado)) {
            mensajeError += "Ingrese una dirección de correo electrónico válida.\n";
        }
        if (!validarNumeroCelular(numeroSeleccionado)) {
            mensajeError += "Ingrese un numero valido.\n";
        }
                
        return mensajeError
    }
    document.getElementById("formulario").addEventListener("submit", function(event) {
        event.preventDefault();

        const mensajeError = validarFormulario(); // Llama a la función de validación

        if (mensajeError === "") {
            // Si no hay errores, muestra el modal de confirmación
            const modal = document.getElementById("modal");
            modal.style.display = "block";

            const siBtn = document.getElementById("si-btn");
            const noBtn = document.getElementById("no-btn");

            siBtn.addEventListener("click", function() {
                document.getElementById("formulario").submit();
                modal.style.display = "none";
            });

            noBtn.addEventListener("click", function() {
                modal.style.display = "none";
            });
        } else {
            // Si hay errores, muestra un mensaje de error al usuario
            alert("Error:\n\n" + mensajeError);
        }
    });
});