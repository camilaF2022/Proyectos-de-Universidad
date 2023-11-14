import regionesYComunas from '../regionesYComunas.json' assert { type: 'json'};

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

    comunaSelect.disabled = false;
});


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
    
    if (mensajeError === "") {
        const modal = document.getElementById("modal");
         modal.style.display = "block";

        const siBtn = document.getElementById("si-btn");

        siBtn.addEventListener("click", () => {
            alert("Hemos recibido el registro de Artesano. Muchas gracias.");
            const datosArtesanos = {
                Region: regionSeleccionada,
                Comuna: comunaSeleccionada,
                Artesanias: Array.from(artesaniaSeleccionada).map(input => input.value).join(", "),
                Descripcion: descripcionSeleccionada,
                Fotos: Array.from(filesSeleccionados).map(input => input.value).join(", "),
                Nombre: nombreSeleccionado,
                Email: emailSeleccionado,
                Teléfono: numeroSeleccionado,
            };
     
            const listaArtesanos = JSON.parse(localStorage.getItem("listaArtesanos")) || [];
            listaArtesanos.push(datosArtesanos);
            localStorage.setItem("listaArtesanos", JSON.stringify(listaArtesanos));
    
            window.location.href = "../index.html";
         });       
        const noBtn = document.getElementById("no-btn");
        noBtn.addEventListener("click", () => {
            modal.style.display = "none"; 
        });
    
    } else {
        alert("Error:\n\n" + mensajeError);
    }
        

        

       

       
        
   
    
};

const fileInput = document.getElementById("files");
const imagePreview = document.getElementById("image-preview");

fileInput.addEventListener("change", function () {
    if (fileInput.files && fileInput.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imagePreview.src = e.target.result;
            imagePreview.style.display = "block";
        };
        reader.readAsDataURL(fileInput.files[0]);
    } else {
        imagePreview.style.display = "none";
    }
});

let submitBtn = document.getElementById("submit-btn");
submitBtn.addEventListener("click", validarFormulario);


