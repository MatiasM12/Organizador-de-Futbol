import {
    verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor
    , obtenerComentarios, enviarComentario
} from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    var jugadorId = localStorage.getItem('jugadorId');
    var token = localStorage.getItem('token');

    fetch('http://localhost:8080/users/' + jugadorId, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(data => {
            // Actualizar dinámicamente el contenido con la información del jugador
            const jugadorInfo = document.getElementById('jugadorInfo');
            jugadorInfo.innerHTML = `
                <img src="${data.photo}" alt="Foto del Jugador" class="jugador-img">
                <div class="jugador-details">
                    <h2>${data.name}</h2>
                    <p>Nombre de Usuario: ${data.username}</p>
                    <p>Correo Electrónico: ${data.mail}</p>
                    <p>Edad: ${data.age}</p>
                    <p>Teléfono: ${data.phone}</p>
                    <p>Posición: ${data.position}</p>
                    <p>Equipo: ${data.team}</p>
                </div>
           `;

            //Comentarios
            obtenerComentarios(token, data.idUser)

            document.querySelectorAll('.rating span').forEach((star, index, stars) => {
                star.addEventListener('click', function () {
                    for (let i = 0; i <= index; i++) {
                        stars[i].classList.add('selected');
                    }
                    for (let i = index + 1; i < stars.length; i++) {
                        stars[i].classList.remove('selected');
                    }
                });
            });

            document.querySelector('.guardarCambiosBtn').addEventListener('click', function () {
                enviarComentario(token, jugadorId)
            });
        })
        .catch(error => console.error('Error al obtener la información del jugador:', error));
});

var userId = localStorage.getItem('idUser');
var token = localStorage.getItem('token');

updateNotificationCounter(token, userId);

// Agregar evento de clic al botón de notificaciones
document.getElementById('verPartidosBtn').addEventListener('click', function () {
    verNotificaciones(token, userId)
});

document.getElementById('openModalBtn').addEventListener('click', function () {
    document.getElementById('notificacionesModal').style.display = 'flex';
    obtenerNotificacionesDesdeServidor(token, userId);
});

document.getElementById('closeModalBtn').addEventListener('click', function () {
    document.getElementById('notificacionesModal').style.display = 'none';
});

