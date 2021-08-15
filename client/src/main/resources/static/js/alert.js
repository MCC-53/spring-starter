var Toast;

// sweet alert pesan sukses
function success(message) {
    toast();

    Toast.fire({
        icon: 'success',
        title: message
    });
}

// sweet alert pesan error
function error(message) {
    toast();
    Toast.fire({
        icon: 'error',
        title: message
    });
}

// sweet alert confirm




function toast() {
    Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    })
}

function errorToast(message) {
    iziToast.error({
        title: 'Error',
        message: message,
        position: 'topRight',
        timeout: 3000
    });
}

function successToast(message) {
    iziToast.success({
        title: 'Success',
        message: message,
        position: 'topRight',
        timeout: 2000
    });
}

function confirmToast(title, message, action) {
    iziToast.question({
        timeout: 10000,
        close: true,
        overlay: true,
        toastOnce: true,
        id: 'question',
        zindex: 999,
        title: title,
        message: message,
        position: 'center',
        transitionIn: 'fadeIn',
        buttons: [
            [`<button><b>OK</b></button>`, function (instance, toast) {
                    instance.hide({transitionOut: 'fadeOut'}, toast, 'delete');
                }],
            ['<button>CANCEL</button>', function (instance, toast) {
                    instance.hide({transitionOut: 'fadeOut'}, toast, 'cancel');
                }]
        ],
        onClosing: function (instance, toast, closedBy) {
            if(closedBy === 'delete'){
              action();
            }
        }
    });
}

function confirmQuestion(confirm, action) {
    swal({
        title: 'Are you sure?',
        text: confirm,
        icon: 'warning',
        buttons: true,
        dangerMode: true
    }).then((result) => {
        if (result) {
            action();
        }
    });
}

function question(question, message, confirmText, action) {
    Swal.fire({
        title: question,
        showCancelButton: true,
        confirmButtonText: `${confirmText}`,
        icon: 'question'
    }).then((result) => {
        if (result.isConfirmed) {
            success(message);
            action();
        }
    });
}