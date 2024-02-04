import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerPartido, pintarJugadoresDelPartido } from './funciones.js';

const token = localStorage.getItem('token');
const fieldId = localStorage.getItem('fieldId');
const hourMatch = localStorage.getItem('hourMatch');

obtenerPartido(token, fieldId, hourMatch)

const idMatch = localStorage.getItem('idMatch');
const storedData = localStorage.getItem(`jugadoresSeleccionados_${idMatch}`);
const restoredSet = new Set(JSON.parse(storedData));

pintarJugadoresDelPartido(restoredSet)

const botonAlineacion1 = document.getElementById('alineacion-team1');
botonAlineacion1.addEventListener('click', function() {
    localStorage.setItem('team',"equipo1");
    window.location.href = `alineacion.html`;
});

const botonAlineacion2 = document.getElementById('alineacion-team2');
botonAlineacion2.addEventListener('click', function() {
    localStorage.setItem('team',"equipo2");
    window.location.href = `alineacion.html`;
});

const botonCacncelarPartido = document.getElementById('cancelar-partido');
botonCacncelarPartido.addEventListener('click', function() {
    alert("Esta funcion aun no esta implementada")
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

