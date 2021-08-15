/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var dataLogin = {};

function loginNow() {

    dataLogin.username = $('#username').val();
    dataLogin.password = $('#password').val();


    $.ajax({
        url: 'login',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(dataLogin),
        contentType: 'application/json',
        success: function (e) {
            setToken(dataLogin.username, dataLogin.password);
            location.href = "/";
        },
        error: function (data) {
            errorToast("Invalid username or password!");
        }


    });
}