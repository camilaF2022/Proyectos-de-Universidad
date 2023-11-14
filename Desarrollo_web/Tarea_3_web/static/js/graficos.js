function obtenerDatosDelServidor() {
    console.log("Datos del servidor:");
    $.ajax({
        url:  urlHinchasGrafico,
        type: "GET",
        dataType: "json",
        success: function (datos) {
            var datosFormateados = datos.map(function (dato) {
                return { name: dato.nombre_deporte, y: dato.cantidad_hinchas };
            });
            mostrarGraficoHincha(datosFormateados);
        },
        error: function (error) {
            console.error("Error al obtener datos del servidor:", error);
        }
    });

    $.ajax({
        url:  urlArtesanosGrafico,
        type: "GET",
        dataType: "json",
        success: function (datos) {
            var datosFormateados = datos.map(function (dato) {
                return { name: dato.nombre_artesania, y: dato.cantidad_artesanos };
            });
            mostrarGraficoArtesano(datosFormateados);
        },
        error: function (error) {
            console.error("Error al obtener datos del servidor:", error);
        }
    });
}

function mostrarGraficoHincha(datos) {
    Highcharts.chart('graficoHinchas', {
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Cantidad de Hinchas por Deporte'
        },
        xAxis: {
            categories: datos.map(function (dato) {
                return dato.name;
            })
        },
        yAxis: {
            title: {
                text: 'Cantidad de Hinchas'
            }
        },
        series: [{
            name: 'Hinchas',
            data: datos.map(function (dato) {
                return dato.y;
            })
        }]
    });
}

function mostrarGraficoArtesano(datos) {
    Highcharts.chart('graficoArtesanos', {
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Cantidad de Artesanos por Artesanía'
        },
        xAxis: {
            categories: datos.map(function (dato) {
                return dato.name;
            })
        },
        yAxis: {
            title: {
                text: 'Cantidad de Artesanos'
            }
        },
        series: [{
            name: 'Artesanos',
            data: datos.map(function (dato) {
                return dato.y;
            })
        }]
    });
}

$(document).ready(function () {
    obtenerDatosDelServidor();
});
