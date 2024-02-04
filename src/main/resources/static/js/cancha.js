import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerCancha, cargarDisponibilidadDesdeAPI } from './funciones.js';

var token = localStorage.getItem('token'); 
var canchaId = localStorage.getItem('canchaId'); 

document.addEventListener('DOMContentLoaded', function () {
    obtenerCancha(token,canchaId)
    cargarDisponibilidadDesdeAPI(canchaId, token)
});

//Notificaciones
const userId = localStorage.getItem('idUser'); 

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




