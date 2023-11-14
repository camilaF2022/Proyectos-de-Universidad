import regionesYComunas from '../regionesYComunas.json' assert { type: 'json'};
import deportes from '../deportes.json' assert { type: 'json' };


// Relleno con los datos de la lista de deportes.
let deporteSelect = document.getElementById("deporte");

deportes.Deportes.forEach(function(deporte){
    console.log("Creando deporte:", deporte);
    let li = document.createElement("li");
    let input = document.createElement("input");
    input.type = "checkbox";
    input.name = "deporte[]";
    input.value = deporte;
    li.appendChild(input);
    li.appendChild(document.createTextNode(deporte));
    deporteSelect.appendChild(li);
});

let regionSelect = document.getElementById("region");
let comunaSelect = document.getElementById("comuna");

regionesYComunas.regiones.forEach(function(region) {  // itero por las regiones
    let option = document.createElement("option");  // creo las opciones de regiones
    option.value = region.region;  //creo el value
    option.textContent = region.region;  //creo el contenido 
    regionSelect.appendChild(option);  // la opcion creada se agrega al select
});

// Se ve que pasa cuando se cambia la opcion de region
regionSelect.addEventListener("change", function() {
    comunaSelect.innerHTML = ""; // Borra las opciones actuales del select de comunas

    let selectedRegion = regionSelect.value; // Obtengo la nueva region 

    // Busco la region en el json 
    let regionData = regionesYComunas.regiones.find(function(region) {
        return region.region === selectedRegion;  // Busco la region que sea igual a la seleccionada 
    });
    // Agrego la opcion de defaul
    let defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.textContent = "Seleccione una comuna";
    comunaSelect.appendChild(defaultOption);

    //Relleno el select con las comunas.
    regionData.comunas.forEach(function(comuna) {
        let option = document.createElement("option");
        option.value = comuna;
        option.textContent = comuna;
        comunaSelect.appendChild(option);
    });

    //Se habilita la opcion que permite seleccionar una comuna 
    comunaSelect.disabled = false;
});

    

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
    
    if (mensajeError === "") {
        const modal = document.getElementById("modal");
        modal.style.display = "block";

        // Agrega un evento al botón "Si" dentro de la ventana modal
        const siBtn = document.getElementById("si-btn");
        siBtn.addEventListener("click", () => {
            alert("Hemos recibido el registro de Hincha. Muchas gracias.");
            const datosHinchas = {
                Deportes:  Array.from(DeportesSeleccionados).map(input => input.value).join(", "),
                Region: regionSeleccionada,
                Comuna: comunaSeleccionada,
                Transporte : transporteSeleccionado,
                Nombre: nombreSeleccionado,
                Email: emailSeleccionado,
                Telefono: numeroSeleccionado,
                Comentario: comentarioSeleccionado,
            };

            let listaHinchas = JSON.parse(localStorage.getItem("listaHinchas")) || [];
            listaHinchas.push(datosHinchas);
            localStorage.setItem("listaHinchas", JSON.stringify(listaHinchas));

            window.location.href = "../index.html"; // Cambia la URL según tu estructura de archivos
        });

        const noBtn = document.getElementById("no-btn");
        noBtn.addEventListener("click", () => {
            modal.style.display = "none"; // Ocultar la ventana modal
        });
        
   } else {
       alert("Error:\n\n" + mensajeError);
   }
};

let submitBtn = document.getElementById("submit-btn");
submitBtn.addEventListener("click", validarFormulario);
