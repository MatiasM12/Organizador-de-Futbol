document.addEventListener('DOMContentLoaded', () => {
    const cancha = document.getElementById('cancha');
    const canchaRect = cancha.getBoundingClientRect();

    function iniciarArrastre(evento) {
        evento.preventDefault(); // Evitar el desplazamiento predeterminado en dispositivos táctiles

        const jugador = evento.target;

        function moverJugador(evento) {
            const x = (evento.clientX || evento.touches[0].clientX) - cancha.offsetLeft - jugador.clientWidth / 2;
            const y = (evento.clientY || evento.touches[0].clientY) - cancha.offsetTop - jugador.clientHeight / 2;

            // Restringir las coordenadas dentro de los límites de la cancha
            const maxX = canchaRect.width - jugador.clientWidth;
            const maxY = canchaRect.height - jugador.clientHeight;

            jugador.style.left = `${Math.min(maxX, Math.max(0, x))}px`;
            jugador.style.top = `${Math.min(maxY, Math.max(0, y))}px`;
        }

        function detenerArrastre() {
            document.removeEventListener('mousemove', moverJugador);
            document.removeEventListener('touchmove', moverJugador);
            document.removeEventListener('mouseup', detenerArrastre);
            document.removeEventListener('touchend', detenerArrastre);

            // Guardar la posición en localStorage
            localStorage.setItem(jugador.id, JSON.stringify({
                left: jugador.style.left,
                top: jugador.style.top
            }));
        }

        document.addEventListener('mousemove', moverJugador);
        document.addEventListener('touchmove', moverJugador);
        document.addEventListener('mouseup', detenerArrastre);
        document.addEventListener('touchend', detenerArrastre);
    }

    const idMatch = localStorage.getItem('idMatch');
    const storedData = localStorage.getItem(`jugadoresSeleccionados_${idMatch}`);
    const restoredSet = new Set(JSON.parse(storedData));

    var i = 0;
    const team = localStorage.getItem('team');

    // Crear jugadores y hacerlos arrastrables
    restoredSet.forEach((jugadorData) => {
        // Verificar si el jugador pertenece al equipo actual
        if (jugadorData.equipo === team) {
            const jugador = document.createElement('div');
            jugador.className = 'jugador';
            jugador.id = `jugador${jugadorData.id+idMatch}`;

            // Restaurar la posición desde las posiciones almacenadas
            const storedPosition = localStorage.getItem(jugador.id);
            if (storedPosition) {
                const { left, top } = JSON.parse(storedPosition);
                jugador.style.left = left;
                jugador.style.top = top;
            } else {
                // Posición predeterminada si no hay información almacenada
                jugador.style.left = `${i * 50}px`;
                jugador.style.top = '200px';
            }

            // Agregar imagen del jugador
            jugador.style.backgroundImage = `url('${jugadorData.foto}')`;
            jugador.style.backgroundSize = 'cover';

            jugador.addEventListener('mousedown', iniciarArrastre);
            jugador.addEventListener('touchstart', iniciarArrastre);
            cancha.appendChild(jugador);

            i++;
        }
    });
});
