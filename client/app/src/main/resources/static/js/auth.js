function setToken(username, password) {
    localStorage.setItem('token', btoa(`${username}:${password}`));
} function getToken() {
    return localStorage.getItem('token');
}