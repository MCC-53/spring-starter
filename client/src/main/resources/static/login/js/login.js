var data = {};

function LoginProcess() {
    data.username = $('input[name=username]').val();
    data.password = $('input[name=password]').val();

    $.ajax({
        url: 'login',
        data: JSON.stringify(data),
        contentType: "application/json",
        type: 'post',
        dataType: 'text',
        success: function (e) {
            if (e == 'Success') {
                console.log(e);
                setToken(data.username, data.password);
                location.href='/emp'
            }else{
                location.href='department'
            }
        }, error: (e) => {
//            console.log(e)
        }
    })
}
