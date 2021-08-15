var dataLogin = {};

function loginProcess() {

    dataLogin.username = $('#username').val();
    dataLogin.password = $('#password').val();

    console.log(username, password);

    $.ajax({
        url: 'login',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(dataLogin),
        contentType: 'application/json',
        success: (data) => {
            console.log(data);
            setToken(dataLogin.username, dataLogin.password);
            location.href="/dashboard"
        }

    })
}