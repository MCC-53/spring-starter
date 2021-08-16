/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var dataLogin = {};

function loginProcess() {

    dataLogin.username = $('input[name=username]').val();
    dataLogin.password = $('input[name=password]').val();

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

        }, 
        error: function (xhr, err, e) {
            alert("Username or Password Invalid");
        }

    })
}

