/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function logout() {
    $.ajax({
        type: "POST",
        url: 'logout',
        success: (data) => {
            window.location.href = 'http://localhost:8082/logout';
        }
    })
}