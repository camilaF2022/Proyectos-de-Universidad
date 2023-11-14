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
        // verifico si se seleccionaron entre 1 y 3 deportes, entrega False si no se cumple 
        const validarDeportes = (deporte) => {
            if (deporte.length < 1 || deporte.length > 3) {
            return false;
            }
            return true;
        };

        //verifico si se selecciono una region y comuna 
        const validarRegion = (region) => {
            if (region === "") {
            return false;
            }
            return true;
        };

        //verifico si se selecciono una region y comuna 
        const validarComuna = (comuna) => {
            if (comuna === "" ) {
            return false;
            }
            return true;
        };

        // verifico si se selecciono un transporte 
        const validarTransporte = (transporte) => {
            if (transporte === "") {
            return false;
            }
            return true;
        };
    
        // verifico el largo del nombre 
        const validarNombre = (nombre) => {
            if (nombre.length < 3 || nombre.length > 80) {
            return false;
            }
            return true;
        };
        // verifico si es de tipo mail, si tiene un arroba
        const validarEmail = (email) => {
            const expReg =  /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/ ;
            return expReg.test(email);
        };

        const validarNumeroCelular = (numero) => {
            const expReg2 = /^(\d{9,15})?$/; 
            return expReg2.test(numero);
        };
        
        const validarComentarios = (comentario) => {
            if (comentario.length>80) {
            return false;
            }
            return true;
        };

        let DeportesSeleccionados = document.querySelectorAll('input[name="deporte[]"]:checked');
        let regionSeleccionada = document.getElementById("region").value;
        let comunaSeleccionada = document.getElementById("comuna").value;
        let transporteSeleccionado = document.getElementById("transporte").value;
        let nombreSeleccionado = document.getElementById("nombre").value;
        let emailSeleccionado = document.getElementById("email").value;
        let numeroSeleccionado = document.getElementById("numero").value;
        let comentarioSeleccionado = document.getElementById("comentario").value;
    
        let mensajeError = "";
        
        if (!validarDeportes(DeportesSeleccionados)) {
            mensajeError += "Debe seleccionar entre 1 y 3 deportes.\n";
        }
        
        if (!validarRegion(regionSeleccionada)) {
            mensajeError += "Debe seleccionar una región válida.\n";
        }
        if (!validarComuna(comunaSeleccionada)) {
            mensajeError += "Debe seleccionar una comuna válida.\n";
        }
        
        if (!validarTransporte(transporteSeleccionado)) {
            mensajeError += "Debe seleccionar un modo de transporte.\n";
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

        if (!validarComentarios(comentarioSeleccionado)) {
            mensajeError += "El comentario es demasiado largo.\n";
        }
        
        return mensajeError
    }

    document.getElementById("myform").addEventListener("submit", function(event) {
        event.preventDefault();

        const mensajeError = validarFormulario(); // Llama a la función de validación
        console.log(mensajeError)
        if (mensajeError === "") {
            // Si no hay errores, muestra el modal de confirmación
            const modal = document.getElementById("modal");
            modal.style.display = "block";

            const siBtn = document.getElementById("si-btn");
            const noBtn = document.getElementById("no-btn");

            siBtn.addEventListener("click", function() {
                console.log('hola')
                document.getElementById("myform").submit();
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