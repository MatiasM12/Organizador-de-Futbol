import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('token');
    var idUser= localStorage.getItem('idUser');

    // Si no hay token, redirigir a la página de inicio de sesión
    if (!token) {
        window.location.href = 'login.html';
    }

    const closeButton = document.querySelector('.close-button');

    closeButton.addEventListener('click', function () {
        localStorage.removeItem('token');
        localStorage.removeItem('idUser');
        window.location.href = 'login.html';
    });

    //Notificaciones
    var userId = localStorage.getItem('idUser');
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
