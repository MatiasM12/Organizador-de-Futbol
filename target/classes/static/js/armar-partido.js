import { verNotificaciones, updateNotificationCounter, obtenerNotificacionesDesdeServidor, 
    obtenerCanchasParaSeleccionar, obtenerHorariosYActualizarDropdown, construirCanchaElement, pintarJugadores, agregarMiUsuarioASeleccionados, pintarJugadoresEnEquipos, notificarPartido } from './funciones.js';

document.addEventListener('DOMContentLoaded', function () {
    var jugadoresSeleccionadosSet = new Set();

    // Para la seleccionar la cancha
    var canchaId;
    var selectedField;
    document.getElementById('seleccionar-cancha').addEventListener('click', function () {
        document.getElementById('canchas-modal').style.display = 'block';
    
        const token = localStorage.getItem('token');
    
        obtenerCanchasParaSeleccionar(token)
            .then(data => {

                function onSelect(selectedCancha) {
                    selectedField = selectedCancha;
                    canchaId = selectedCancha.idField;
                
                    const canchaSection = document.getElementById('seleccionar-cancha');
                    canchaSection.style.background = `url('${selectedCancha.photo}') center/cover`;
                
                    document.getElementById('canchas-modal').style.display = 'none';
                
                    obtenerHorariosYActualizarDropdown(canchaId, token)
                        .catch(error => {
                            console.error('Error al obtener horarios:', error);
                        });

                }
                
                const canchasListModal = document.getElementById('canchas-list-modal');
                canchasListModal.innerHTML = '';
    
                data.forEach(cancha => {
                    const listItem = construirCanchaElement(cancha, onSelect);
                    canchasListModal.appendChild(listItem);
                });
    
            })
            .catch(error => {
                console.error('Error al obtener canchas:', error);
            });
    });

    // Para los seleccionar los jugadores
    document.getElementById('seleccionar-jugadores').addEventListener('click', function () {
        document.getElementById('jugadores-modal').style.display = 'block';
    
        const token = localStorage.getItem('token');
    
        pintarJugadores(token,jugadoresSeleccionadosSet)
    });
    
    var token = localStorage.getItem('token');
    const idUser= localStorage.getItem('idUser');

    agregarMiUsuarioASeleccionados(token,idUser,jugadoresSeleccionadosSet)
    
    // Funcion para reordenar los equipos de froma random
    document.getElementById('reordenar-teams-btn').addEventListener('click', function () {
        const jugadoresArray = Array.from(jugadoresSeleccionadosSet);

        jugadoresArray.forEach(jugador => {
            // Generar un número aleatorio entre 0 y 1
            const randomValue = Math.random();

            // Decidir aleatoriamente si el jugador cambia de equipo o no
            if (randomValue < 0.5) {
                jugador.equipo = (jugador.equipo === 'equipo1') ? 'equipo2' : 'equipo1';
            }
        });

        pintarJugadoresEnEquipos(jugadoresArray,jugadoresSeleccionadosSet);
    });

    // Boton de notificar partido
    document.getElementById('notificar-partido-btn').addEventListener('click', function () {
        notificarPartido(token,canchaId,jugadoresSeleccionadosSet);
    });

    // Boton de volver al inicio
    document.getElementById('volver-btn').addEventListener('click', function () {
        window.location.href = 'inicio-jugador.html';
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

    document.getElementsByClassName('close')[1].addEventListener('click', function () {
        document.getElementById('jugadores-modal').style.display = 'none';
   });
   document.getElementsByClassName('close')[0].addEventListener('click', function () {
        document.getElementById('canchas-modal').style.display = 'none';
   });
    
});
