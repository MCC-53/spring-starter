var department = {};
var deptId;


$(document).ready(function () {
    var table= $('#tableDepartment').DataTable({
        ajax : {
            url : 'http://localhost:8081/department',
            dataSrc : ''
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "Department Name",
                "data": "name"
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <button type="button" class="btn btn-success"
                         data-bs-toggle="modal"
                         data-bs-target="#departmentModal"
                         onclick="detail(${data})">
                     <i class="fa fa-eye" aria-hidden="true"></i>
                 </button>
                 <button type="button" class="btn btn-warning"
                         data-bs-toggle="modal"
                         data-bs-target="#departmentModal"
                         onclick="edit(${data})">
                     <i class="fa fa-edit" aria-hidden="true"></i>
                 </button>
                 <button type="button" class="btn btn-danger"
                        data-bs-toggle="modal"
                        data-bs-target="#departmentModal"
                        onclick="deleteById(${data})">
                     <i class="fa fa-trash" aria-hidden="true"></i>
                 </button>
              </div>
            `;
                }
            }
        ]
    });

    submit();
});

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(deptId){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/department/${deptId}` ,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        console.log(data);
                        success('department update');
                        $('#tableDepartment').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
                var _this = this;
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/department/`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        success('department created');
                        $('#tableDepartment').DataTable().ajax.reload(null, false);
                    }
                });
            }
            $('.modal').modal('hide');
            // setInterval('refreshPage()', 1000);
        }else{
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    })
}


function getById(id) {
    $.ajax({
        type: "GET",
        url: `/department/${id}`,
        dataType: 'json',
        success: (data) => {
            deptId = id;
            department.name = data.name;
            setForm();
        }
    });
}

function detail(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(true);
}

function edit(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(false);
}

function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/department/${id}`,
            dataType: 'json',
            success: (data) => {
                success('department deleted');
                $('#tableDepartment').DataTable().ajax.reload(null, false);
            },
            error: (data) => {
                error("department failed to delete");
            }
        });
    });
}
function create() {
    department={};
    deptId=null;
    setForm({});
    disabledForm(false);
}

function setForm() {
    $('#departmentName').val(department.name);
}

function setValue() {
    department.name = $('#departmentName').val();
}

function disabledForm(isDisable) {
    $('#departmentName').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}

// without cors
// $(document).ready( function () {
//     $('#tableDepartment').DataTable();
//     $.ajax({
//         url: '/department/get-all',
//         type: 'GET',
//         dataType: 'json',
//         success: (res) => {
//             let row = null;
//             res.forEach((data) => {
//                 row += `<tr>
//                             <td>${data.id}</td>
//                             <td>${data.name}</td>
//                               <td>
//                                 <div>
//                                     <button type="button" class="btn btn-success"
//                                             data-bs-toggle="modal"
//                                             data-bs-target="#departmentModal"
//                                             onclick="detail(${data.id})">
//                                         <i class="fa fa-eye" aria-hidden="true"></i>
//                                     </button>
//                                     <button type="button" class="btn btn-warning"
//                                             data-bs-toggle="modal"
//                                             data-bs-target="#departmentModal">
//                                         <i class="fa fa-edit" aria-hidden="true"></i>
//                                     </button>
//                                     <button type="button" class="btn btn-danger"
//                                             data-bs-toggle="modal"
//                                             data-bs-target="#departmentModal">
//                                         <i class="fa fa-trash" aria-hidden="true"></i>
//                                     </button>
//                                 </div>
//                             </td>
//                         </tr>`
//             });
//             $('#tBodyDepartment').html(row);
//             dataTable();
//         }
//     });
// });

