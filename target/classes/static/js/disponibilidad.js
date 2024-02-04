import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerCanchasDueño, cargarDisponibilidadDesdeAPI } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    const canchasList = document.getElementById('canchasList');
    const token = localStorage.getItem('token');
    const idUser = localStorage.getItem('idUser');

    obtenerCanchasDueño(token,idUser)

    canchasList.addEventListener('click', function (event) {
        const selectedCancha = event.target;
        const canchaId = selectedCancha.dataset.id;

        disponibilidadSection.style.display = 'none';

        if (canchaId) {
            cargarDisponibilidadDesdeAPI(canchaId,token);
        }
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

});

