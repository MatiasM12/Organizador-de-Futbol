import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, obtenerUsuario, actualizarDatosDelUsuario } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    const editarBtn = document.getElementById('editarBtn');
    const modalEditar = document.getElementById('modalEditar');
    const cerrarModal = document.getElementById('cerrarModal');

    var token = localStorage.getItem('token');
    var idUser = localStorage.getItem('idUser');

    obtenerUsuario(token,idUser)

    //Abrir modal editar
    editarBtn.addEventListener('click', function () {
        handleRoleChange()
        modalEditar.style.display = 'block';
    });

    //Cerrar modal editar
    cerrarModal.addEventListener('click', function () {
        modalEditar.style.display = 'none';
    });

    //Funcion para esconder campo equipo y posicion en caso de ser dueño
    function handleRoleChange() {
        var selectedRole = localStorage.getItem('idRole');;

        var positionField = document.getElementById("position");
        var positionLabel = document.getElementById("label-position");
        var teamField = document.getElementById("team");
        var teamLabel = document.getElementById("label-team");

        // Verificar el valor seleccionado en el campo de rol y ocultar/mostrar los campos correspondientes
        if (selectedRole == 2) {
            positionLabel.style.display = "none";
            positionField.style.display = "none";
            teamField.style.display = "none";
            teamLabel.style.display = "none";
        } else {
            positionField.style.display = "block";
            positionLabel.style.display = "block";
            teamField.style.display = "block";
            teamLabel.style.display = "block";
        }
    }

    //Funcion para editar los datos del usuario
    document.getElementById('guardarCambiosBtn').addEventListener('click', function () {
        actualizarDatosDelUsuario(token,idUser)
    });

    //Notificaciones
    updateNotificationCounter(token, idUser);
    
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

