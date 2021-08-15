/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var employee = {};
var empId;

$(document).ready(function () {
    $('#table_id').DataTable({
        ajax : {
            url : 'http://localhost:8081/employee',
            dataSrc : '',
            beforeSend : addRequestHeader()
        },
        "columns": [
            {
                "name": "Id",
                "data": "id"
            },
            {
                "name": "Employee Name",
                "data": "firstName"
            },
            {
                "name": "Email",
                "data": "email"
            },
            {
                "name": "Address",
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
                "name": "User",
                "data": null,
                render : function(data, type, row) {
                    return row.user ? row.user.username : null;
                }
            },
            {
                "name": "Projects",
                "data" : null,
                render : function(data, type, row) {
                    var result = "";
                    row.projects.forEach((data) => {
                        result += ' | '+data.name;
                    });
                    return result;
                }
            },
            {
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <a href="#" type="button" class = "btn btn-info btn-sm" onclick="detail(${data})"><i class="fas fa-eye"></i></a> | 
                  <a href="#" type="button" class = "btn btn-warning btn-sm" onclick="edit(${data})"><i class="fas fa-edit"></i></a> | 
                  <a href="#" type="button" class = "btn btn-danger btn-sm" onclick="deleteById(${data})"><i class="fas fa-trash-alt"></i></a> 
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
        type: "GET",
        url: `http://localhost:8081/employee/${id}`,
        dataType: 'json',
        beforeSend : addRequestHeader(),
        success: (data) => {
            empId = id;
            employee.name = data.name;
            setForm();
        }
    });
}

function create() {
    employee={};
    empId=null;
    setForm({});
    disabledForm(false);
}

function edit(id) {
    getById(id);
    $('#employeeModal').modal('show');
    disabledForm(false);
}

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(empId){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/employee/${empId}` ,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    beforeSend : addRequestHeader(),
                    success: (data) => {
                        console.log(data);
                        success('employee updated');
                        $('#table_id').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
//                var _this = this;
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/employee/`,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    beforeSend : addRequestHeader(),
                    success: (data) => {
                        success('employee created');
                        $('#table_id').DataTable().ajax.reload(null, false);
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

function setValue() {
    employee.name = $('#name_emp').val();
}

function deleteById(id) {
    question("Do you want to delete this employee?", "employee deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/employee/${id}`,
            contentType: 'application/json',
            data: employee,
            beforeSend : addRequestHeader(),
            success: (data) => {
                $('.modal').modal('hide');
                success('employee deleted');
                $('#table_id').DataTable().ajax.reload(null, false);
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
    });
}

function setForm() {
    $('#name_emp').val(employee.name);
}

function disabledForm(isDisable) {
    $('#name_emp').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}