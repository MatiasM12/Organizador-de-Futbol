import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerCanchas } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    const token = localStorage.getItem('token');

    obtenerCanchas(token)

    //Notificaciones
    var userId = localStorage.getItem('idUser');
    updateNotificationCounter(token, userId);

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

