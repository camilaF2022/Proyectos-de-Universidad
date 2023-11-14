// Obtén el índice de la URL
const urlParams = new URLSearchParams(window.location.search);
const index = parseInt(urlParams.get("index"));

const listaHinchas = JSON.parse(localStorage.getItem("listaHinchas")) || [];
const hinchaSeleccionado = listaHinchas[index];

const detallesContainer = document.getElementById("detalles-container");

// Ejemplo de cómo mostrar los detalles en una lista
const listaDetalles = document.createElement("ul");
for (const key in hinchaSeleccionado) {
    const listItem = document.createElement("li");
    listItem.textContent = `${key}: ${hinchaSeleccionado[key]}`;
    listaDetalles.appendChild(listItem);
}
detallesContainer.appendChild(listaDetalles);
