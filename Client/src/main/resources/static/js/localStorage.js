/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addRequestHeader() {
    $(document).ajaxSend((evt, req) => {
        req.setRequestHeader('Authorization', 'Basic ' + getToken());
    });
}

function setToken(username, password) {
    localStorage.setItem('token', btoa(`${username}:${password}`));
}

function getToken() {
    return localStorage.getItem('token');
}