// Carga las filas desde el LocalStorage
const cargarFilas = () => {
    const listaHinchas = JSON.parse(localStorage.getItem("listaHinchas")) || [];
    
    // Obtener el elemento tbody donde deseas agregar las filas
    const tbody = document.getElementById("datos-hinchas");

    // Limpiar el contenido existente del tbody (para evitar duplicados)
    tbody.innerHTML = "";

    // Iterar a través de la lista de artesanos y agregar una fila por cada uno
    listaHinchas.forEach((hincha,index) => {
        const fila = document.createElement("tr");

        // Crear celdas para cada dato y asignar los valores
        const nombreCelda = document.createElement("td");
        nombreCelda.textContent = hincha.Nombre;
        fila.appendChild(nombreCelda);

        const comunaCelda = document.createElement("td");
        comunaCelda.textContent = hincha.Comuna;
        fila.appendChild(comunaCelda);

        const deporteCelda = document.createElement("td");
        deporteCelda.textContent = hincha.Deportes;
        fila.appendChild(deporteCelda);

        const transporteCelda = document.createElement("td");
        transporteCelda.textContent = hincha.Transporte;
        fila.appendChild(transporteCelda);

        const telefonoCelda = document.createElement("td");
        telefonoCelda.textContent = hincha.Telefono;
        fila.appendChild(telefonoCelda);

        // Agregar la fila al tbody
        tbody.appendChild(fila);
        fila.addEventListener("click", () => {
            window.location.href = `informacion-hincha.html?index=${index}`;
        });
    });
};

// Llama a la función para cargar las filas al cargar la página
cargarFilas();
