/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let dataLogin = {};

function loginProcess() {

    dataLogin.username = $('#username').val();
    dataLogin.password = $('#password').val();

    console.log(dataLogin);

    $.ajax({
        url: `/auth/login`,
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(dataLogin),
        contentType: 'application/json',
        success: (data) => {
            setToken(dataLogin.username, dataLogin.password);
            location.href = `/`;
        }
    });
}

function addAuthRequestHeader() {
    $(document).ajaxSend((evt, req) => {
        req.setRequestHeader('Authorization', 'Basic ' + getToken());
    });
}

function setToken(username, password) {
    localStorage.setItem('tokenKuYangPalingRahasia', btoa(`${username}:${password}`));
}

function getToken() {
    return localStorage.getItem('tokenKuYangPalingRahasia');
}
