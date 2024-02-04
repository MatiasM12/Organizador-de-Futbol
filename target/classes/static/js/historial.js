import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor,obtenerHistorial } from './funciones.js';

var idUser = localStorage.getItem('idUser');
var token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', function () {
    obtenerHistorial(token, idUser)
});

//Notificaciones
const userId = idUser;

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




