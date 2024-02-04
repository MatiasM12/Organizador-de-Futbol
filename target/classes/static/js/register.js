document.addEventListener('DOMContentLoaded', function () {
    function handleRoleChange() {
        var selectedRole = document.getElementById("role").value;

        var positionField = document.getElementById("position");
        var positionLabel = document.getElementById("label-position");
        var teamField = document.getElementById("team");
        var teamLabel = document.getElementById("label-team");

        // Verificar el valor seleccionado en el campo de rol y ocultar/mostrar los campos correspondientes
        if (selectedRole === "dueno_cancha") {
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

    // Agregar el evento change al campo de rol
    document.getElementById("role").addEventListener("change", handleRoleChange);

});


function validateRegistration() {
    var name = document.getElementById('name').value;
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var email = document.getElementById('mail').value;
    var photoInput = document.getElementById('photo');
    var role = document.getElementById('role').value;
    var age = document.getElementById('age').value;
    var phone = document.getElementById('phone').value;
    var position;
    var team ;
    if(role == 'jugador'){
        position = document.getElementById('position').value;
        team = document.getElementById('team').value;
    }

    // Asignar un valor numérico según el rol seleccionado
    var roleId = (role === 'jugador') ? 1 : 2;

    // Verificar si se seleccionó un archivo y guarda el nombre de la imagen
    if (photoInput.files.length > 0) {
        var photoFile = photoInput.files[0];
        var photoFileName = photoFile.name;
    }else{
        photoFileName = 'perfil2.jpg'
    }

    var jugador = {
        name: name,
        username: username,
        password: password,
        mail: email,
        photo: photoFileName,
        roleId:{
            idRole:roleId
        },
        age: age,
        phone: phone,
        position: position,
        team: team
    };

    // Valido que los campos esten todos completos para poder registrarse
    if (validarDatosJugador(jugador)) {

    fetch('http://localhost:8080/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jugador)
    })
    .then(response => response.json())
    .then(data => {
        if (data.message != null) {
            alert("El usuario ya existe, por favor ingrese otro nombre");
        }else{
            localStorage.setItem('token', data.token);
            localStorage.setItem('idUser', data.userId);

            mostrarMensajeExito("Se registro correctamente",roleId)           
        }

    })
    .catch(error => {
        // Manejar errores aquí
        console.error('Error:', error);
    });
    } 
    else{
        mostrarMensajeError("Debe completar todos los campos para podes registrarse")
    }  
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

function mostrarMensajeExito(mensaje,roleId) {
    const successMessage = document.createElement('div');
    successMessage.id = 'success-message';
    successMessage.innerHTML = `
                <p>${mensaje}</p>
            `;

    document.body.appendChild(successMessage);
    document.getElementById('success-message').style.display = 'block';

    setTimeout(function () {
        document.body.removeChild(successMessage);
        if (roleId == 1) window.location.href = `inicio-jugador.html`;
        else window.location.href = `inicio-dueño.html`; 
    }, 2000); 
}

function validarDatosJugador(jugador) {
    const propiedadesObligatorias = ['name', 'username', 'password', 'mail', 'photo', 'roleId', 'age', 'phone'];

    // Verificar que las propiedades obligatorias tengan contenido
    for (const prop of propiedadesObligatorias) {
        if (!jugador.hasOwnProperty(prop) || jugador[prop] === null || jugador[prop] === undefined || jugador[prop] === '') {
            console.log(`El campo ${prop} es obligatorio`);
            return false;
        }
    }
    return true;
}