// Carga las filas desde el LocalStorage
const cargarFilas = () => {
    const listaArtesanos = JSON.parse(localStorage.getItem("listaArtesanos")) || [];
    
    // Obtener el elemento tbody donde deseas agregar las filas
    const tbody = document.getElementById("datos-artesano");

    // Limpiar el contenido existente del tbody (para evitar duplicados)
    tbody.innerHTML = "";

    // Iterar a través de la lista de artesanos y agregar una fila por cada uno
    listaArtesanos.forEach((artesano, index) => {
        const fila = document.createElement("tr");

        // Crear celdas para cada dato y asignar los valores
        const nombreCelda = document.createElement("td");
        nombreCelda.textContent = artesano.Nombre;
        fila.appendChild(nombreCelda);

        const telefonoCelda = document.createElement("td");
        telefonoCelda.textContent = artesano.Teléfono;
        fila.appendChild(telefonoCelda);

        const comunaCelda = document.createElement("td");
        comunaCelda.textContent = artesano.Comuna;
        fila.appendChild(comunaCelda);

        const artesaniasCelda = document.createElement("td");
        artesaniasCelda.textContent = artesano.Artesanias;
        fila.appendChild(artesaniasCelda);

        tbody.appendChild(fila);
        
        fila.addEventListener("click", () => {
            window.location.href = `informacion-artesano.html?index=${index}`;
        });
    });
};

cargarFilas();