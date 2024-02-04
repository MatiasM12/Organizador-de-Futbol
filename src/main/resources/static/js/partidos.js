import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerPartidos } from './funciones.js';

var idUser= localStorage.getItem('idUser');
var token = localStorage.getItem('token'); 

document.addEventListener('DOMContentLoaded', function () {
    obtenerPartidos(token,idUser)
});

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


