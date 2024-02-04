import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerCanchasDueñoEditar } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    var idField;
    const token = localStorage.getItem('token');
    const idUser = localStorage.getItem('idUser');

    obtenerCanchasDueñoEditar(token,idUser,idField)

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

