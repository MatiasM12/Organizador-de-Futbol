import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor } from './funciones.js';

document.addEventListener('DOMContentLoaded', () => {
    var token = localStorage.getItem('token');

    fetch('http://localhost:8080/users/players', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(data => {
            const jugadoresContainer = document.getElementById('jugadoresContainer');

            data.forEach(jugador => {
                const jugadorCard = document.createElement('div');
                jugadorCard.classList.add('jugador-card');

                const img = document.createElement('img');
                img.src = jugador.photo;
                img.alt = jugador.username;
                jugadorCard.appendChild(img);

                const jugadorInfo = document.createElement('div');
                jugadorInfo.classList.add('jugador-info');

                const h2 = document.createElement('h2');
                h2.textContent = jugador.username;
                jugadorInfo.appendChild(h2);

                const nombre = document.createElement('p');
                nombre.textContent = `Nombre: ${jugador.name}`;
                jugadorInfo.appendChild(nombre);

                const edad = document.createElement('p');
                edad.textContent = `Edad: ${jugador.age}`;
                jugadorInfo.appendChild(edad);

                const correo = document.createElement('p');
                correo.textContent = `Correo: ${jugador.mail}`;
                jugadorInfo.appendChild(correo);

                jugadorCard.appendChild(jugadorInfo);

                jugadorCard.addEventListener('click', function () {
                    localStorage.setItem('jugadorId', jugador.idUser);
                    window.location.href = 'jugador.html';
                });

                jugadoresContainer.appendChild(jugadorCard);
            });
        })
        .catch(error => console.error('Error al obtener jugadores:', error));

    //Notificaciones
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

});


