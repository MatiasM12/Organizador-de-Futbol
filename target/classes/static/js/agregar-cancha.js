import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, crearCancha } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    const agregarCanchaForm = document.getElementById('agregarCanchaForm');
    var token = localStorage.getItem('token');
    var userId = localStorage.getItem('idUser');

    agregarCanchaForm.addEventListener('submit', function (event) {
        event.preventDefault();
        
        const canchaData = {};
        canchaData.name = agregarCanchaForm.querySelector('#name').value;
        let photoInput = document.querySelector('#photo');
    
        // Verificar si se seleccionó un archivo y guarda el nombre de la imagen
        if (photoInput.files.length > 0) {
            var photoFile = photoInput.files[0];
            var photoFileName = '/img/'+ photoFile.name;
        } else {
            alert('No se seleccionó ninguna imagen.');
        }
        
        canchaData.photo = photoFileName;
        canchaData.mail = agregarCanchaForm.querySelector('#mail').value;
        canchaData.size = parseInt(agregarCanchaForm.querySelector('#size').value);
        canchaData.phone = agregarCanchaForm.querySelector('#phone').value;
        canchaData.location = agregarCanchaForm.querySelector('#location').value;
        canchaData.price = parseInt(agregarCanchaForm.querySelector('#price').value);
        canchaData.userId = {
            idUser: userId
        }
    
        const requestBody = JSON.stringify(canchaData);
        crearCancha(token,requestBody)
    });

    //Notificaciones
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

