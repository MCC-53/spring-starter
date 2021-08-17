/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var employee = {};
var IdEmp;

$(document).ready(function () {
    var table= $('#table_emp').DataTable({
        ajax : {
            url : 'http://localhost:8081/employee',
            dataSrc : ''
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "First Name",
                "data": "firstName"
            },
            {
                "name": "Last Name",
                "data": "lastName"
            },
            {
                "name": "Email",
                "data": "email"
            },
            {
                "name": "Addres",
                "data": "address"
            },
            {
                "name": "Department",
                "data" : null,
                render : function(data, type, row) {
                    return row.department ? row.department.name : null;
                }
            },
            {
                "name": "Project",
                "data" : null,
                render : function(data, type, row) {
                    var result = "";
                    row.projects.forEach((data, index) => {
                        result += index==1 ?  data.name : ' | '+data.name+' | ';
                    });
                    return result;
                }
            },
            {
                "name": "Username",
                "data" : null,
                render : function(data, type, row) {
                    return row.user ? row.user.username : null;
                }
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
               <div class="d-flex justify-content-center">
                  <a class = "btn btn-info" type="button" href="#" onclick=""><i class="fas fa-eye"></i></a> | 
                  <a class = "btn btn-warning" type="button" href="#" onclick=""><i class="fas fa-edit"></i></a> | 
                  <a class = "btn btn-danger" type="button" href="#" onclick=""><i class="fas fa-trash-alt"></i></a> 
              </div> 
            `;
                }
            }
        ]
    });
    submit();
});

function detail(id) {
    getById(id);
    $('#employeeModal').modal('show');
    disabledForm(true);
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8081/employee/${id}`,
        dataType: 'json',
        success: (data) => {
            IdEmp = id;
            employee.name = data.name;
            setForm();
        }
    });
}

function create() {
    employee={};
    depEmp=null;
    setForm({});
    disabledForm(false);
}

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(IdEmp){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/employee/${IdEmp}` ,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    success: (data) => {
                        console.log(data);
                        success('employee update');
                        $('#table_emp').DataTable().ajax.reload(null, false);
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
                    url: `http://localhost:8081/register`,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    success: (data) => {
                        success('employee created');
                        $('#table_emp').DataTable().ajax.reload(null, false);
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


function edit(id) {
    getById(id);
    $('#employeeModal').modal('show');
    disabledForm(false);
}

function deleteById(id) {
    question("Do you want to delete this employee?", "employee deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/employee/${id}`,
            contentType: 'application/json',
            data: employee,
            success: (data) => {
                $('.modal').modal('hide');
                success('employee deleted');
                $('#table_emp').DataTable().ajax.reload(null, false);
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    });
}
function setValue() {
    employee.firstName=$('#firstname').val();
    employee.lastName=$('#lastname').val();
    employee.email=$('#email').val();
    employee.address=$('#address').val();
    employee.department.name=$('#name_dept').val();
    employee.project.name=$('#name_project').val();
    
}

function setForm() {
    $('#firstname').val(employee.name);
    $('#lastname').val(employee.name);
    $('#email').val(employee.name);
    $('#addres').val(employee.name);
    $('#name_dept').val(employee.name);
    $('#name_project').val(employee.name);
}

function disabledForm(isDisable) {
    $('#firstname').prop('disabled',isDisable);
    $('#lastname').prop('disabled', isDisable);
    $('#email').prop('disabled', isDisable);
    $('#addres').prop('disabled', isDisable);
    $('#name_dept').prop('disabled', isDisable);
    $('#name_project').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}

