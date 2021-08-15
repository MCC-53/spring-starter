function logout(){
    $.ajax({
        type: "POST",
        url: 'logout',
        success: (data) => {
            window.location.href = 'http://localhost:8082/logout';
        }
    })
}