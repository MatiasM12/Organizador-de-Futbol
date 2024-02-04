function validateLogin() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var data = {
        username: username,
        password: password
    };

    fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        localStorage.setItem('token', data.token);
        localStorage.setItem('idUser',data.userId);
        localStorage.setItem('idRole',data.idRole);

        if (data.idRole == 1) window.location.href = `inicio-jugador.html`;
        else window.location.href = `inicio-dueño.html`;
    })
    .catch(error => {
        mostrarMensajeError("Usuario incorrecto")
        console.error('Error:', error);
    });
}

function registrarse() {
    window.location.href = `register.html`;
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
