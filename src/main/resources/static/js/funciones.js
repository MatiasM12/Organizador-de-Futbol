//Noficicaciones ------------------------------------------------------------
const notificationCounter = document.querySelector('.notification-counter');

export function updateNotificationCounter(token,userId) {
    fetch(`http://localhost:8080/notifications/user/${userId}`, {
            method: 'GET',
           headers: {
               'Authorization': 'Bearer '+token
           }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            const notifications = data.length || 0;
            notificationCounter.textContent = notifications;
            notificationCounter.style.display = notifications > 0 ? 'inline-block' : 'none';
        })
        .catch(error => {
            console.error('Error al obtener notificaciones:', error);
        });
}

export function verNotificaciones(token,userId){
    if (notificationCounter.textContent != '0') {
        fetch(`http://localhost:8080/notifications/markAllRead/${userId}`, {
            method: 'PUT', 
            headers: {
                'Authorization': 'Bearer '+token
            }
        })
        .then(response => response)
        .then(data => {
            console.log('Notificaciones marcadas como leídas:', data);
            updateNotificationCounter(token,userId); 
            window.location.href = 'partidos.html';
        })
        .catch(error => {
            console.error('Error al marcar notificaciones como leídas:', error);
        });
    } else {
        alert("No hay notificaciones pendientes");
    }
};

export function obtenerNotificacionesDesdeServidor(token,idUsuario) {
    const notificacionesList = document.getElementById('notificacionesList');

    fetch(`http://localhost:8080/notifications/user/${idUsuario}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        }
    })
    .then(response => response.json())
    .then(notificaciones => {
        if (notificaciones.length > 0) {
            notificacionesList.innerHTML = notificaciones.map(notif => `<li>${notif.message}, fecha: ${notif.date}</li>`).join('');
        } else {
            notificacionesList.innerHTML = '<h4>No hay notificaciones</h4>';
        }
    })
    .catch(error => console.error('Error al obtener notificaciones:', error));
}

//Comentarios ---------------------------------------------------------------
export function obtenerComentarios(token,idUser){
    const comentariosList = document.getElementById('comentariosList');
        comentariosList.innerHTML = `
            <!-- La lista de comentarios se actualizará aquí -->
        `;

    fetch(`http://localhost:8080/comments/user/${idUser}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer '+token
        }
    })
    .then(response => response.json())
    .then(comments => {
        // Actualizar dinámicamente la lista de comentarios
        comentariosList.innerHTML = comments.map(comment => `
        <li>
            <p>Comentario: ${comment.text}</p>
            <p>Valoración:
                <span class="star-rating">
                    <span class="star ${comment.stars >= 1 ? 'filled' : ''}"></span>
                    <span class="star ${comment.stars >= 2 ? 'filled' : ''}"></span>
                    <span class="star ${comment.stars >= 3 ? 'filled' : ''}"></span>
                    <span class="star ${comment.stars >= 4 ? 'filled' : ''}"></span>
                    <span class="star ${comment.stars === 5 ? 'filled' : ''}"></span>
                </span>
            </p>
        </li>
        `).join('');
    })
    .catch(error => console.error('Error al obtener los comentarios:', error));
}

function obtenerCantidadEstrellas() {
    const stars = document.querySelectorAll('.rating span.selected');
    return stars.length;
}

export function enviarComentario(token,jugadorId) {
    const text = document.getElementById('comment').value; 
    const stars = obtenerCantidadEstrellas();

    const comentario = {
        text: text,
        stars: stars,
        user: {
            idUser:jugadorId
        }
    };

    if(text!= ''){
        fetch('http://localhost:8080/comments', {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer '+token,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(comentario),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Respuesta del servidor:', data);
            
            })
            .catch(error => {
                console.error('Error al enviar el comentario:', error);
            });
        } 
}

//Partido --------------------------------------------------------------------

export function obtenerPartido(token,fieldId,hourMatch){
 fetch('http://localhost:8080/fields/' + fieldId, {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('imagenCancha').src = data.photo;
        document.getElementById('nombreCancha').innerText = data.name;
        document.getElementById('ubicacionCancha').innerText += ` ${data.location}`;
        document.getElementById('horarioCancha').innerText += ` ${hourMatch}`;
        document.getElementById('precioCancha').innerText += ` $${data.price}`;
    })
    .catch(error => console.error('Error al obtener datos:', error));   
}

export function pintarJugadoresDelPartido(restoredSet){
    const listaJugadoresTeam1 = document.getElementById('listaJugadoresTeam1');
    const listaJugadoresTeam2 = document.getElementById('listaJugadoresTeam2');
    restoredSet.forEach(jugador => {
        const listItem = document.createElement('li');
        listItem.classList.add('jugador-seleccionado');
    
        const jugadorImg = document.createElement('img');
        jugadorImg.src = jugador.foto;
        jugadorImg.alt = `Foto del ${jugador.nombre}`;
        jugadorImg.classList.add('jugador-seleccionado-img');
    
        const jugadorDetails = document.createElement('div');
        jugadorDetails.classList.add('jugador-seleccionado-details');
    
        const jugadorName = document.createElement('h3');
        jugadorName.innerText = jugador.nombre;
    
        const confirmacionStatus = 'Invitado';
        const confirmacionP = document.createElement('p');
        confirmacionP.innerText = confirmacionStatus;
    
    
        // Agregar los elementos al listItem
        jugadorDetails.appendChild(jugadorName);
        jugadorDetails.appendChild(confirmacionP);
    
        listItem.appendChild(jugadorImg);
        listItem.appendChild(jugadorDetails);
    
        // Determinar la sección correcta según el equipo
        if (jugador.equipo === 'equipo1') {
            listaJugadoresTeam1.appendChild(listItem);
        } else if (jugador.equipo === 'equipo2') {
            listaJugadoresTeam2.appendChild(listItem);
        } else {
            console.error('Equipo no reconocido:', jugador.equipo);
            return;
        }
    });
}

//Partidos -------------------------------------------------------------------

export function agregarPartidos(partidos) {
    const partidosContainer = document.querySelector('.partidos-container');
    partidos.forEach(partido => {
        const partidoCard = document.createElement('div');
        partidoCard.classList.add('partido-card');

        const titulo = document.createElement('h2');
        titulo.textContent = `Partido: ${partido.match.title}`;
        partidoCard.appendChild(titulo);

        const fecha = document.createElement('p');
        fecha.textContent = `Hora: ${partido.match.hour}`;
        partidoCard.appendChild(fecha);

        console.log(partido.match);

        partidoCard.addEventListener('click', function() {
            localStorage.setItem('fieldId', partido.match.fieldId.idField);
            localStorage.setItem('hourMatch', partido.match.hour);
            localStorage.setItem('idMatch', partido.match.idMatch);
            window.location.href = `partido.html`;
        });

        partidosContainer.appendChild(partidoCard);
    });
}

export function obtenerPartidos(token,idUser){
    fetch('http://localhost:8080/matchparticipants/user/'+idUser, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json())
    .then(data => {
        agregarPartidos(data);
        console.log(data);
    })
    .catch(error => {
        console.error('Error al obtener los datos de la API:', error);
    });
}

//Perfil ----------------------------------------------------------------------

export function obtenerUsuario(token,idUser){
    fetch('http://localhost:8080/users/' + idUser, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            actualizarDatosJugador(data,token,idUser);
        })
        .catch(error => console.error('Error al obtener los datos del perfil:', error));
}

function actualizarDatosJugador(data,token,idUser) {
    const jugadorInfo = document.getElementById('datos-jugador');
    if (data.roleId.idRole == 1) {
        jugadorInfo.innerHTML = `
            <img src="${data.photo}" alt="Foto del Jugador" class="jugador-img">
            <div class="jugador-details">
                <h2>${data.name}</h2>
                <p>Nombre de Usuario: ${data.username}</p>
                <p>Correo Electrónico: ${data.mail}</p>
                <p>Edad: ${data.age}</p>
                <p>Teléfono: ${data.phone}</p>
                <p>Posición: ${data.position}</p>
                <p>Equipo: ${data.team}</p>
            </div>
       `;
    } else {
        jugadorInfo.innerHTML = `
        <img src="${data.photo}" alt="Foto del Jugador" class="jugador-img">
        <div class="jugador-details">
            <h2>${data.name}</h2>
            <p>Nombre de Usuario: ${data.username}</p>
            <p>Correo Electrónico: ${data.mail}</p>
            <p>Edad: ${data.age}</p>
            <p>Teléfono: ${data.phone}</p>
        </div>
   `;
    }

    const comentariosList = document.getElementById('comentariosList');

    // Agregar la sección de comentarios
    comentariosList.innerHTML = `
           <!-- La lista de comentarios se actualizará aquí -->
       `;

    fetch(`http://localhost:8080/comments/user/${idUser}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(comments => {
            comentariosList.innerHTML = comments.map(comment => `
           <li>
                <span class="eliminar-comentario" data-comment-id="${comment.idComment}"></span>
                <p>Comentario: ${comment.text}</p>
                <p>Valoración:
                    <span class="star-rating">
                        <span class="star ${comment.stars >= 1 ? 'filled' : ''}"></span>
                        <span class="star ${comment.stars >= 2 ? 'filled' : ''}"></span>
                        <span class="star ${comment.stars >= 3 ? 'filled' : ''}"></span>
                        <span class="star ${comment.stars >= 4 ? 'filled' : ''}"></span>
                        <span class="star ${comment.stars === 5 ? 'filled' : ''}"></span>
                    </span>
                </p>
            </li>
           `).join('');
        })
        .catch(error => console.error('Error al obtener los comentarios:', error));

    document.getElementById('comentariosList').addEventListener('click', function (event) {
        if (event.target.classList.contains('eliminar-comentario')) {
            const comentarioId = event.target.getAttribute('data-comment-id');

            fetch(`http://localhost:8080/comments/${comentarioId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            })
                .then(response => {
                    if (response.ok) {
                        event.target.closest('li').remove();
                    } else {
                        console.error('Error al eliminar el comentario:', response.statusText);
                    }
                })
                .catch(error => console.error('Error al eliminar el comentario:', error));
        }
    });

}

export function actualizarDatosDelUsuario(token,idUsuario){
    var nombre = document.getElementById('nombre').value;
    var mail = document.getElementById('mail').value;
    var age = document.getElementById('age').value;
    var position = document.getElementById('position').value;
    var phone = document.getElementById('phone').value;
    var team = document.getElementById('team').value;
    var imagenInput = document.getElementById('imagen');
    var imagen = (imagenInput.files && imagenInput.files[0]) ? imagenInput.files[0] : "";

    const jugador = {
        name: nombre,
        mail: mail,
        age: age,
        position: position,
        phone: phone,
        team: team,
        photo: imagen.name
    };

    console.log(jugador);

    var idUsuario = localStorage.getItem('idUser');
    var token = localStorage.getItem('token');

    fetch('http://localhost:8080/users/' + idUsuario, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jugador)
    })
        .then(response => {
            if (response.ok) {
                document.getElementById('cerrarModal').click();
                location.reload();
            } else {
                console.error('Error al actualizar datos:', response.statusText);
            }
        })
        .then(data => {
            console.log('Datos actualizados con éxito:', data);
        })
        .catch(error => {
            console.error('Error al actualizar datos:', error);
        });
}

//Historial -------------------------------------------------------------------
export function obtenerHistorial(token,idUser){
 fetch('http://localhost:8080/matchparticipants/historial/'+idUser, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json())
    .then(data => {
        agregarPartidosHistorial(data);
    })
    .catch(error => {
        console.error('Error al obtener los datos de la API:', error);
    });   
}

function agregarPartidosHistorial(partidos) {
    const partidosContainer = document.querySelector('.partidos-container');
    partidos.forEach(partido => {
        const partidoCard = document.createElement('div');
        partidoCard.classList.add('partido-card');

        const titulo = document.createElement('h2');
        titulo.textContent = `Partido: ${partido.match.title}`;
        partidoCard.appendChild(titulo);

        const fecha = document.createElement('p');
        fecha.textContent = `Fecha: ${partido.match.date}`;
        partidoCard.appendChild(fecha);

        // partidoCard.addEventListener('click', function() {
        //     localStorage.setItem('fieldId', partido.match.fieldId);
        //     localStorage.setItem('hourMatch', partido.match.hour);
        //     localStorage.setItem('idMatch', partido.match.idMatch);
        //     window.location.href = `partido.html`;
        // });

        partidosContainer.appendChild(partidoCard);
    });
}

//Cancha ----------------------------------------------------------------------
export function obtenerCancha(token, canchaId) {
    fetch('http://localhost:8080/fields/' + canchaId, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('La solicitud para obtener datos de la cancha no fue exitosa');
        }
        return response.json();
    })
    .then(datosCancha => {
        mostrarDatosCancha(datosCancha);
    })
    .catch(error => {
        console.error('Error al obtener datos de la cancha:', error);
    });
}

function mostrarDatosCancha(datosCancha) {
    const canchaInfoContainer = document.getElementById('canchaInfoContainer');
    canchaInfoContainer.innerHTML = `
        <img src="${datosCancha.photo}" alt="${datosCancha.name}" class="cancha-img">
        <div class="cancha-details">
            <h2>${datosCancha.name}</h2>
            <p>Correo: ${datosCancha.mail}</p>
            <p>Teléfono: ${datosCancha.phone}</p>
            <p>Tamaño: ${datosCancha.size}</p>
            <p>Ubicación: ${datosCancha.location}</p>
            <p>Precio: ${datosCancha.price}</p>
        </div>
    `;
}

//Canchas ----------------------------------------------------------------------
export function obtenerCanchas(token){
    fetch('http://localhost:8080/fields', {
          method: 'GET',
          headers: {
              'Authorization': 'Bearer '+token
          }
          })
        .then(response => {
            const newToken = response.headers.get('Authorization');
            if (newToken) {
                localStorage.setItem('token', newToken);
            }
            return response.json();
        })
        .then(canchas => {
            const canchasContainer = document.getElementById('canchasContainer');
            canchas.forEach(cancha => {
                const canchaCard = document.createElement('div');
                canchaCard.classList.add('cancha-card');
            
                const img = document.createElement('img');
                img.src = cancha.photo; 
                img.alt = cancha.name;
                canchaCard.appendChild(img);
            
                const h2 = document.createElement('h2');
                h2.textContent = cancha.name;
                canchaCard.appendChild(h2);
            
                const capacidad = document.createElement('p');
                capacidad.textContent = `Capacidad: ${cancha.size} personas`; 
                canchaCard.appendChild(capacidad);
            
                const precio = document.createElement('p');
                precio.textContent = `Precio: $${cancha.price}/hora`;
                canchaCard.appendChild(precio);
            
                const horarios = document.createElement('p');
                horarios.textContent = `Horarios: ${cancha.schedule}`;
                canchaCard.appendChild(horarios);
            
                canchasContainer.appendChild(canchaCard);

                canchaCard.addEventListener('click', function () {
                    localStorage.setItem('canchaId', cancha.idField);
                    window.location.href = 'cancha.html';
                });
            });
            
        })
        .catch(error => console.error('Error al obtener datos de canchas:', error));
}

//Disponibilidad ---------------------------------------------------------------
export function obtenerCanchasDueño(token,idUser){
    fetch(`http://localhost:8080/fields/user/${idUser}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(canchas => {
            canchas.forEach(cancha => {
                const li = document.createElement('li');
                li.textContent = cancha.name;
                li.setAttribute('data-id', cancha.idField);
                canchasList.appendChild(li);
            });
        })
        .catch(error => console.error('Error al obtener las canchas:', error));
}

export function cargarDisponibilidadDesdeAPI(canchaId, token) {
    const disponibilidadSection = document.getElementById('disponibilidadSection');
    const horariosList = document.getElementById('horariosList');

    fetch(`http://localhost:8080/availabilities/fieldAll/${canchaId}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json())
    .then(disponibilidad => {
        // Agrupar la disponibilidad por día de la semana
        const disponibilidadPorDia = disponibilidad.reduce((acc, horario) => {
            acc[horario.day] = acc[horario.day] || [];
            acc[horario.day].push(horario);
            return acc;
        }, {});

        // Filtrar y eliminar horarios duplicados
        for (const dia in disponibilidadPorDia) {
            if (disponibilidadPorDia.hasOwnProperty(dia)) {
                disponibilidadPorDia[dia] = eliminarHorariosDuplicados(disponibilidadPorDia[dia]);
            }
        }

        // Mostrar la disponibilidad por día en la lista
        horariosList.innerHTML = `
            <h3>Lunes</h3>
            <ul>${crearLista(disponibilidadPorDia['Lunes'])}</ul>

            <h3>Martes</h3>
            <ul>${crearLista(disponibilidadPorDia['Martes'])}</ul>

            <h3>Miércoles</h3>
            <ul>${crearLista(disponibilidadPorDia['Miércoles'])}</ul>

            <h3>Jueves</h3>
            <ul>${crearLista(disponibilidadPorDia['Jueves'])}</ul>

            <h3>Viernes</h3>
            <ul>${crearLista(disponibilidadPorDia['Viernes'])}</ul>

            <h3>Sábado</h3>
            <ul>${crearLista(disponibilidadPorDia['Sábado'])}</ul>

            <h3>Domingo</h3>
            <ul>${crearLista(disponibilidadPorDia['Domingo'])}</ul>
        `;

        disponibilidadSection.style.display = 'block';
    })
    .catch(error => console.error('Error al obtener la disponibilidad:', error));
}

function crearLista(horarios) {
    return horarios && horarios.length > 0
        ? horarios.map(horario => `<li>Hora: ${horario.hour} | ${horario.isRented ? 'Ocupado' : 'Disponible'}</li>`).join('')
        : '<li>No hay disponibilidad para este día</li>';
}

function eliminarHorariosDuplicados(horarios) {
    const horariosUnicos = [];
    const horariosExistentes = new Set();

    for (const horario of horarios) {
        const claveHorario = `${horario.hour}-${horario.isRented}`;
        
        if (!horariosExistentes.has(claveHorario)) {
            horariosUnicos.push(horario);
            horariosExistentes.add(claveHorario);
        }
    }

    return horariosUnicos;
}


//Editar Cancha ----------------------------------------------------------------
export function obtenerCanchasDueñoEditar(token,idUser,idField){
    fetch(`http://localhost:8080/fields/user/${idUser}`, {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json())
    .then(canchas => {
        mostrarCanchasEnTarjetas(canchas,token,idUser,idField);
    })
    .catch(error => console.error('Error al obtener las canchas:', error));
}

function mostrarCanchasEnTarjetas(canchas,token,idUser,idField) {    
    const tarjetasCanchas = document.getElementById('canchasContainer');
    const editarCanchaForm = document.getElementById('formularioEditar'); 

    tarjetasCanchas.innerHTML = '';

    canchas.forEach(cancha => {
        const canchaCard = document.createElement('div');
        canchaCard.classList.add('cancha-card');
    
        const img = document.createElement('img');
        img.src = cancha.photo;
        img.alt = cancha.name;
        canchaCard.appendChild(img);
    
        const h2 = document.createElement('h2');
        h2.textContent = cancha.name;
        canchaCard.appendChild(h2);
    
        const capacidad = document.createElement('p');
        capacidad.textContent = `Capacidad: ${cancha.size} personas`;
        canchaCard.appendChild(capacidad);
    
        const precio = document.createElement('p');
        precio.textContent = `Precio: $${cancha.price}/hora`;
        canchaCard.appendChild(precio);
    
        const ubicacion = document.createElement('p');
        ubicacion.textContent = `Ubicacion: ${cancha.location}`;
        canchaCard.appendChild(ubicacion);
    
        // Agregar el botón de editar aquí
        const editarBtn = document.createElement('button');
        editarBtn.classList.add('editar-cancha-btn');
        editarBtn.setAttribute('data-id', cancha.idField);
        editarBtn.textContent = 'Editar';
        
        editarBtn.addEventListener('click', function () {
            const canchaId = this.dataset.id;
            idField = canchaId;

            console.log('Editar cancha con ID:', canchaId);
            abrirModal();

            const editarBtnModal = document.querySelector('.guardarCambiosBtn');
            editarBtnModal.addEventListener('click', function () {
                const canchaData = {
                    name: editarCanchaForm.querySelector('#name').value,
                    mail: editarCanchaForm.querySelector('#mail').value,
                    size: parseInt(editarCanchaForm.querySelector('#size').value),
                    phone: editarCanchaForm.querySelector('#phone').value,
                    location: editarCanchaForm.querySelector('#location').value,
                    price: parseInt(editarCanchaForm.querySelector('#price').value),
                    userId: {
                        idUser: idUser
                    }
                };
                
                const photoInput = editarCanchaForm.querySelector('#photo');
                if (photoInput.files.length > 0) {
                    const photoFile = photoInput.files[0];
                    const photoFileName = photoFile.name;
                    canchaData.photo = photoFileName;
                }

                const requestBody = JSON.stringify(canchaData);

                fetch(`http://localhost:8080/fields/${idField}`, {
                    method: 'PUT',
                    headers: {
                        'Authorization': 'Bearer ' + token,
                        'Content-Type': 'application/json'
                    },
                    body: requestBody
                })
                .then(response => response.json())
                .then(data => {
                    console.log('Cancha editada correctamente:', data);
                    cerrarModal();
                    location.reload();
                })
                .catch(error => {
                    console.error('Error al editar la cancha:', error);
                });
            });
        });
        
        canchaCard.appendChild(editarBtn);
        tarjetasCanchas.appendChild(canchaCard);
    });
}

//Agregar Cancha ----------------------------------------------------------------
export function crearCancha(token,requestBody){
    fetch('http://localhost:8080/fields', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: requestBody
    })
        .then(response => response.json())
        .then(data => {
                var horaInicio = agregarCanchaForm.querySelector('#horaInicio').value;
                var horaFin = agregarCanchaForm.querySelector('#horaFin').value;
                var horasIntermedias = generarHorasIntermedias(horaInicio, horaFin);
                const fechaActual = new Date();
                const month = fechaActual.getMonth() + 1; // Los meses son indexados desde 0

                const diaInicioSeleccionado = document.getElementById('diaInicio').value;
                const diaFinSeleccionado = document.getElementById('diaFin').value;
                const diasSeleccionados = obtenerDiasEntre(diaInicioSeleccionado, diaFinSeleccionado);
                console.log('Días Seleccionados:', diasSeleccionados);
                
                diasSeleccionados.forEach(dia =>{
                    horasIntermedias.forEach(hora => {
                        const canchaData = {
                            month:month,
                            day:dia,
                            hour:hora,
                            isRented:false,
                            field:{
                                idField:data.idField
                            }
                        };
            
                    const requestBody = JSON.stringify(canchaData);
                    agragarDisponibilidad(requestBody,token)

                    });
                })
                
            console.log('Cancha agregada exitosamente:', data);
            mostrarMensajeExito('Cancha agregada exitosamente', 'Volver al inicio');
        })
        .catch(error => {
            console.error('Error al agregar la cancha:', error);
            mostrarMensajeError('Error al agregar la cancha');
        });
}
    
function generarHorasIntermedias(horaInicio, horaFin) {
        const horasIntermedias = [];
        var horaActual = horaInicio;

        while (horaActual <= horaFin) {
            horasIntermedias.push(horaActual);
            const [hora, minutos] = horaActual.split(':');
            const nuevaHora = (parseInt(hora) + 1).toString().padStart(2, '0');
            horaActual = `${nuevaHora}:${minutos}`;
        }

        return horasIntermedias;
}  

function obtenerDiasEntre(diaInicio, diaFin) {
    const dias = ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"];

    const indiceInicio = dias.indexOf(diaInicio);
    const indiceFin = dias.indexOf(diaFin);

    if (indiceInicio === -1 || indiceFin === -1 || indiceInicio >= indiceFin) {
        console.error('Seleccion de días inválida.');
        return [];
    }

    const diasSeleccionados = dias.slice(indiceInicio, indiceFin + 1);
    return diasSeleccionados;
}

function agragarDisponibilidad(requestBody,token){
    fetch('http://localhost:8080/availabilities', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: requestBody
    })
    .then(response => response.json())
    .then(data => {
        console.log('Disponibilidad agregada exitosamente:', data);
     })
    .catch(error => {
        console.error('Error al agregar la disponibilidad:', error);
    });
}

function mostrarMensajeExito(mensaje, buttonText) {
    const successMessage = document.createElement('div');
    successMessage.id = 'success-message';
    successMessage.innerHTML = `
                <p>${mensaje}</p>
                <button id="volver-btn">${buttonText}</button>
            `;

    document.body.appendChild(successMessage);
    document.getElementById('success-message').style.display = 'block';

    const volverBtn = document.getElementById('volver-btn');
    volverBtn.addEventListener('click', function () {
        window.location.href = '/inicio-dueño.html';
    });
}

function mostrarMensajeError(mensaje) {
    const errorMessage = document.createElement('div');
    errorMessage.id = 'error-message';
    errorMessage.innerHTML = `
                <p>${mensaje}</p>
            `;

    document.body.appendChild(errorMessage);
    document.getElementById('error-message').style.display = 'block';

    setTimeout(function () {
        document.body.removeChild(errorMessage);
    }, 2000); 
}

//Armar Partido -------------------------------------------------------------------
export function obtenerCanchasParaSeleccionar(token) {
    return fetch('http://localhost:8080/fields', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json());
}

export function construirCanchaElement(cancha, onSelect) {
    const listItem = document.createElement('div');
    listItem.innerHTML = `
        <div class="cancha-info">
            <img src="${cancha.photo}" alt="${cancha.username}" class="cancha-img">
            <h3>${cancha.name}</h3>
            <p>Cantidad de jugadores: ${cancha.size}</p>
            <p>Telefono: ${cancha.phone}</p>
            <button class="seleccionar-cancha-btn">Seleccionar</button>
        </div>
    `;

    const seleccionarCanchaBtn = listItem.querySelector('.seleccionar-cancha-btn');
    seleccionarCanchaBtn.addEventListener('click', () => onSelect(cancha));

    return listItem;
}

export function obtenerHorariosYActualizarDropdown(canchaId, token) {
    return fetch(`http://localhost:8080/availabilities/field/${canchaId}`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
    .then(response => response.json())
    .then(availabilities => {
        var disponibilidadMap = new Map();
        availabilities.forEach(item => {
            const { idAvailability, day, hour } = item;

            // Verificar si el día ya existe en el mapa
            if (!disponibilidadMap.has(day)) {
                // Si no existe, crear un nuevo array para ese día
                disponibilidadMap.set(day, [{ idAvailability, hour }]);
            } else {
                // Si ya existe, agregar el objeto al array existente
                disponibilidadMap.get(day).push({ idAvailability, hour });
            }
        });

        repintarDias()
        limpiarHoras()

        const diaSelect = document.getElementById('dia');
        diaSelect.addEventListener('change', function () {
            const diaSeleccionado = this.value;
            const horariosParaDia = disponibilidadMap.get(diaSeleccionado);
            // Llenar el segundo menú desplegable con los horarios obtenidos
            const horaDropdown = document.getElementById('hora');
            horaDropdown.innerHTML = '';

            if(horariosParaDia != undefined){
                    horariosParaDia.forEach(item => {
                    const { idAvailability, hour } = item;
                    const option = document.createElement('option');
                    option.value = idAvailability;
                    option.text = hour;
                    horaDropdown.add(option);
            });
            }else{
                limpiarHoras()
            }


        });


    })
    .catch(error => {
        console.error('Error al obtener horarios:', error);
    });
}

function repintarDias() {
    const diasSelect = document.getElementById('dia');
    diasSelect.innerHTML = '';

    const dias = ["Lunes", "Martes", "Miércoles", "Jueves", "Sábado", "Domingo"];

    const opcionVacia = document.createElement('option');
    opcionVacia.value = 'vacio';
    diasSelect.appendChild(opcionVacia);

    dias.forEach(dia => {
        const opcionDia = document.createElement('option');
        opcionDia.value = dia;
        opcionDia.textContent = dia;
        diasSelect.appendChild(opcionDia);
    });
}

function limpiarHoras() {
    const horasSelect = document.getElementById('hora');
    horasSelect.innerHTML = '';

    const opcionPredeterminada = document.createElement('option');
    opcionPredeterminada.value = 'vacio';
    opcionPredeterminada.textContent = 'No Hay';
    horasSelect.appendChild(opcionPredeterminada);
}

export function pintarJugadores(token,jugadoresSeleccionadosSet){
    fetch('http://localhost:8080/users/players', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
        .then(response => response.json())
        .then(data => {
            // Procesar la respuesta y construir la lista de jugadores
            const jugadoresListModal = document.getElementById('jugadores-list-modal');
            jugadoresListModal.innerHTML = ''; // Limpiar la lista antes de agregar jugadores

            data.forEach(jugador => {
                const listItem = document.createElement('li');
                listItem.innerHTML = `
                    <div class="jugador-info" id="jugador-info">
                        <img src="${jugador.photo}" alt="${jugador.username}" class="jugador-img">
                        <div class="jugador-details">
                            <h3>${jugador.username}</h3>
                            <p>Nombre: ${jugador.name}</p>
                            <p>Correo: ${jugador.mail}</p>
                            <select class="equipo" name="equipo">
                                <option value="equipo1">Team 1</option>
                                <option value="equipo2">Team 2</option>
                            </select>
                            <button class="invitar-btn">Invitar</button>
                        </div>
                    </div>`;
                jugadoresListModal.appendChild(listItem);

                // Agregar evento para invitar al jugador
                const invitarBtn = listItem.querySelector('.invitar-btn');
                invitarBtn.addEventListener('click', function () {
                    const equipoSeleccionado = listItem.querySelector('.equipo').value;
                    const jugadorInvitado = {
                        id: jugador.idUser,
                        username: jugador.username,
                        nombre: jugador.name,
                        correo: jugador.mail,
                        foto: jugador.photo,
                        equipo: equipoSeleccionado,
                        edad: jugador.age,
                        posicion: jugador.position,
                        telefono: jugador.phone
                    };

                    // Agregar el jugador invitado a la lista de jugadores seleccionados
                    agregarJugadorSeleccionado(jugadorInvitado,jugadoresSeleccionadosSet);
                    console.log(jugador);

                    // Cerrar la ventana modal
                    listItem.querySelector('.jugador-info').style.backgroundColor = "#0A2647";
                });
            });
        })
        .catch(error => {
            console.error('Error al obtener jugadores:', error);
        });
}

export function agregarMiUsuarioASeleccionados(token,idUser,jugadoresSeleccionadosSet){
    // Agrega siempre mi usuario a la lista de seleccionados
    fetch('http://localhost:8080/users/'+idUser, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
        .then(response => response.json())
        .then(data => {
                    const miUsuario = {
                        id: data.idUser,
                        username: data.username,
                        nombre: data.name,
                        correo: data.mail,
                        foto: data.photo,
                        equipo: "equipo1",
                        edad: data.age,
                        telefono: data.phone,
                        posicion: data.position
                    };
                    agregarJugadorSeleccionado(miUsuario,jugadoresSeleccionadosSet)
        })
        .catch(error => {
            console.error('Error al obtener jugadores:', error);
    }); 
}

function agregarJugadorSeleccionado(jugador, jugadoresSeleccionadosSet) {
    const jugadorElement = document.createElement('div');
    jugadorElement.classList.add('jugador-seleccionado');
    jugadorElement.innerHTML = `
        <img src="${jugador.foto}" alt="${jugador.username}" class="jugador-seleccionado-img">
        <div class="jugador-seleccionado-details">
            <h3>${jugador.username}</h3>
            <p>Nombre: ${jugador.nombre}</p>
            <button class="desinvitar-btn"><img src="/img/quitar-boton-redondo.png" alt=""></button>
            <button class="mover-team"><img src="/img/mover-flechas.png" alt=""></button>
        </div>
    `;

    // Determinar la sección correcta según el equipo
    let sectionId;
    if (jugador.equipo === 'equipo1') {
        sectionId = 'team1-section';
    } else if (jugador.equipo === 'equipo2') {
        sectionId = 'team2-section';
    } else {
        console.error('Equipo no reconocido:', jugador.equipo);
        return;
    }

    let jugadoresSeleccionadosSection = document.getElementById(sectionId);

    // Verificar si el jugador ya está en el conjunto
    const jugadorExistente = Array.from(jugadoresSeleccionadosSet).some(
        (jug) => jug.nombre === jugador.nombre
    );

    if (!jugadorExistente) {
        jugadoresSeleccionadosSection.appendChild(jugadorElement);
        jugadoresSeleccionadosSection.style.display = 'block';

        jugadoresSeleccionadosSet.add(jugador);

        const desinvitarBtn = jugadorElement.querySelector('.desinvitar-btn');
        desinvitarBtn.addEventListener('click', function () {
            jugadoresSeleccionadosSection.removeChild(jugadorElement);
            jugadoresSeleccionadosSet.delete(jugador);
        });

        const moverTeamBtn = jugadorElement.querySelector('.mover-team');
        moverTeamBtn.addEventListener('click', function () {
            sectionId = (sectionId === 'team1-section') ? 'team2-section' : 'team1-section';
            jugadoresSeleccionadosSection = document.getElementById(sectionId);
            jugadoresSeleccionadosSection.appendChild(jugadorElement);
            jugador.equipo = (sectionId === 'team1-section') ? 'equipo1' : 'equipo2';
        });
    } else {
        console.log('El jugador ya está en la lista.');
    }
}

export function pintarJugadoresEnEquipos(jugadoresArray, jugadoresSeleccionadosSet) {
    // Limpiar las secciones de los equipos antes de pintar
    const team1Section = document.getElementById('team1-section');
    const team2Section = document.getElementById('team2-section');
    team1Section.innerHTML = '';
    team2Section.innerHTML = '';

    // Obtener las secciones de los equipos
    const team1Title = document.createElement('h3');
    team1Title.textContent = 'Team 1';
    team1Section.appendChild(team1Title);

    // Pintar cada jugador en Team 1
    jugadoresArray.forEach(jugador => {
        if (jugador.equipo === 'equipo1') {
            const jugadorElement = crearElementoJugador(jugador);
            team1Section.appendChild(jugadorElement);
            agregarEventosJugador(jugadorElement, jugador, jugadoresSeleccionadosSet);
        }
    });

    // Pintar cada jugador en Team 2
    const team2Title = document.createElement('h3');
    team2Title.textContent = 'Team 2';
    team2Section.appendChild(team2Title);

    jugadoresArray.forEach(jugador => {
        if (jugador.equipo === 'equipo2') {
            const jugadorElement = crearElementoJugador(jugador);
            team2Section.appendChild(jugadorElement);
            agregarEventosJugador(jugadorElement, jugador, jugadoresSeleccionadosSet);
        }
    });
}

function crearElementoJugador(jugador) {
    const jugadorElement = document.createElement('div');
    jugadorElement.classList.add('jugador-seleccionado');
    jugadorElement.draggable = true;
    jugadorElement.innerHTML = `
        <img src="${jugador.foto}" alt="${jugador.username}" class="jugador-seleccionado-img">
        <div class="jugador-seleccionado-details">
            <h3>${jugador.username}</h3>
            <p>Nombre: ${jugador.nombre}</p>
            <button class="desinvitar-btn"><img src="/img/quitar-boton-redondo.png" alt=""></button>
            <button class="mover-team"><img src="/img/mover-flechas.png" alt=""></button>
        </div>
    `;
    return jugadorElement;
}

function agregarEventosJugador(jugadorElement, jugador, jugadoresSeleccionadosSet) {
    const desinvitarBtn = jugadorElement.querySelector('.desinvitar-btn');
    desinvitarBtn.addEventListener('click', function () {
        jugadorElement.parentElement.removeChild(jugadorElement);
        jugadoresSeleccionadosSet.delete(jugador);
    });

    const moverTeamBtn = jugadorElement.querySelector('.mover-team');
    moverTeamBtn.addEventListener('click', function () {
        const sectionId = (jugador.equipo === 'equipo1') ? 'team2-section' : 'team1-section';
        const jugadoresSeleccionadosSection = document.getElementById(sectionId);

        if (jugadoresSeleccionadosSection) {
            jugadoresSeleccionadosSection.appendChild(jugadorElement);
            jugador.equipo = (sectionId === 'team1-section') ? 'equipo1' : 'equipo2';
        } else {
            console.error('Sección no encontrada:', sectionId);
        }
    });
}


export function notificarPartido(token,canchaId,jugadoresSeleccionadosSet){
    const selectedHour = document.getElementById('hora').options[document.getElementById('hora').selectedIndex].text;
    const matchTitle = (document.getElementById('nombrePartido').value === '') ? "Sin titulo" : document.getElementById('nombrePartido').value;
    const fechaActual = new Date();
    const year = fechaActual.getFullYear();
    const month = fechaActual.getMonth() + 1; // Los meses son indexados desde 0
    const day = fechaActual.getDate();
    var actualDate = year+'-'+month+'-'+day;
    var idMatch;
    
    if ((selectedHour != "No Hay") && (selectedHour != null) && (selectedHour != undefined)){

        const matchData = {
            date: actualDate,     
            hour: selectedHour,
            title: matchTitle,
            fieldId: {
                idField: canchaId
            }  
        };
    
        fetch('http://localhost:8080/matches', {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(matchData)
        })
        .then(response => response.json())
        .then(data => {
            jugadoresSeleccionadosSet.forEach(user => {
                const notificationData = {
                    message: 'Nuevo partido',
                    isSeen: false,
                    date: actualDate,
                    user: {
                        idUser: user.id
                    }
                };

                enviarNotificacion(token,notificationData);
                

                // Insert de matchParticipants
                const matchParticipantData = {
                    user: {
                        idUser: user.id,
                        username: user.username,
                        mail: user.correo,
                        name: user.nombre,
                        age: user.edad,
                        position: user.posicion,
                        phone: user.telefono
                    },
                    match: {
                        idMatch: data.idMatch,
                        date: data.date,
                        hour: data.hour,
                        title: data.title,
                        field:{
                            fieldId: canchaId
                        }
                        
                    }
                };

                console.log(matchParticipantData);

                idMatch = data.idMatch;

                insertarParticipantes(token,matchParticipantData)

                    //Marcar la cancha como reservada
                    const requestBody = {
                        isRented: true
                    };

                    actualizarDisponibilidad(token,requestBody)

            });
            
            // Guardo los datos para partidos.html
            localStorage.setItem(`jugadoresSeleccionados_${idMatch}`, JSON.stringify(Array.from(jugadoresSeleccionadosSet)));

            console.log('Partido notificado con éxito:', data);
            document.getElementById('loading-overlay').style.display = 'none';
            document.getElementById('success-message').style.display = 'block';
        })
        .catch(error => {
            console.error('Error al notificar el partido:', error);
            document.getElementById('loading-overlay').style.display = 'none';
        }); 
    }
    else{
        mostrarMensajeError("Debe seleccionar el dia y la hora")
    }
}

function enviarNotificacion(token, notificationData){
    fetch('http://localhost:8080/notifications', {
                        method: 'POST',
                        headers: {
                            'Authorization': 'Bearer ' + token,
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(notificationData),
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Respuesta del servidor:', data);
                    })
                    .catch(error => {
                        console.error('Error al realizar la solicitud:', error);
                });
}

function insertarParticipantes(token,matchParticipantData){
    fetch('http://localhost:8080/matchparticipants', {
                    method: 'POST',
                    headers: {
                        'Authorization': 'Bearer ' + token,
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(matchParticipantData),
                })
                    .then(response => response.json())
                    .then(matchParticipant => {
                        console.log('Match participant created:', matchParticipant);
                    })
                    .catch(error => {
                        console.error('Error creating match participant:', error);
                    });
}

function actualizarDisponibilidad(token,requestBody){
    let availabilityId = document.getElementById('hora').value;
    fetch(`http://localhost:8080/availabilities/${availabilityId}`, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la solicitud: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Solicitud PUT exitosa:', data);
        })
        .catch(error => {
            console.error('Error en la solicitud PUT:', error);
    });
}


