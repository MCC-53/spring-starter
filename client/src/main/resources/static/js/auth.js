function addRequestHeader() {
    $(document).ajaxSend((evt, req) => {
        req.setRequestHeader('Authorization', 'Basic ' + getLocalStorage());
    });
}

/* => Set local storage berdasarkan username dan password yang di Post, lalu kita ubah ke hash 64 dengan boa  */
function setLocalStorage(username,password){
    localStorage.setItem('lstorage', btoa(`${username}:${password}`));
}

/* => Local Storage sudah tersedia, kita tinggal get*/
function getLocalStorage(){
    return localStorage.getItem('lstorage');
}

temp = {};
function setValue(){
        temp.firstName = $('#firstName').val();
        temp.lastName = $('#lastName').val();
        temp.address = $('#address').val();
        temp.email = $('#email').val();
        temp.username = $('#username').val();
        temp.password = $('#password').val();
}

