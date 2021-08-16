var logindata = {
    username: "",
    password: ""
}; $(document).ready(function () {
    login();
}); function login() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        $.ajax({
            type: "POST",
            url: `/login`,
            contentType: 'application/json',
            data: JSON.stringify(logindata),
            dataType: 'JSON',
            success: (data) => {
                console.log(data);
                if (data === null) {
                    error('Login failed!');
                    //location.href="/login";
                } else {
                    setToken(logindata.username, logindata.password);
                    console.log(getToken());
                    success('Login success!');
                    location.href="/departments";
                }
            }
        });
    });
} function setValue() {
    logindata.username = $('#userName').val();
    logindata.password = $('#passWord').val();
}