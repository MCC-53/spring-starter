$(document).ready( function () {
    $('#tableEmployee').DataTable();
    $.ajax({
        url: '/employee/get-all',
        type: 'GET',
        dataType: 'json',
        success: (res) => {
            let row = null;
            res.forEach((data) => {
                row += `<tr>
                            <td>${data.id}</td>
                            <td>${data.firstName}</td>
                            <td>${data.lastName}</td>
                            <td>${data.email}
                            <td>${data.address}</td>
                              <td>
                                <div>
                                    <button type="button" class="btn btn-success"
                                            data-bs-toggle="modal"
                                            data-bs-target="#add-employee">
                                        <i class="fa fa-eye" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-warning"
                                            data-bs-toggle="modal"
                                            data-bs-target="#add-employee">
                                        <i class="fa fa-edit" aria-hidden="true"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger"
                                            data-bs-toggle="modal"
                                            data-bs-target="#add-employee">
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>`
            });
            $('#tBodyEmployee').html(row);
            dataTable();
        }
    });
} );



function getAll() {

}

// function submit() {
//     $('form').su
// }

//function set form
// function setForm(){
//     $('#id').val(emp.id);
//     $('#firstName').val(emp.firstName);
//     $('#lastName').val(emp.lastName);
//     $('#email').val(emp.email);
//     $('#address').val(emp.address);
//     $('#department').val(emp.department);
// }

