// Obtén el índice de la URL
const urlParams = new URLSearchParams(window.location.search);
const index = parseInt(urlParams.get("index"));
console.log(index); 
// Accede a los datos en el localStorage
const listaArtesanos = JSON.parse(localStorage.getItem("listaArtesanos")) || [];
const artesanoSeleccionado = listaArtesanos[index];

const detallesContainer = document.getElementById("detalles-container");

// Ejemplo de cómo mostrar los detalles en una lista
const listaDetalles = document.createElement("ul");
for (const key in artesanoSeleccionado) {
    const listItem = document.createElement("li");
    listItem.textContent = `${key}: ${artesanoSeleccionado[key]}`;
    listaDetalles.appendChild(listItem);
}
detallesContainer.appendChild(listaDetalles);
