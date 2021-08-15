var employee = {};
var empId;
var department = {};

$(document).ready(function () {
    $('#table_employee').DataTable({
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
                "name": "Username",
                "data" : null,
                render : function(data, type, row) {
                    return row.user ? row.user.username : null;
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
                "name": "Action",
                "data": "id",
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <a class = "btn btn-info btn-sm" type="button" href="#" onclick="detail(${data})"><i class="fas fa-eye"></i></a> | 
                  <a class = "btn btn-warning btn-sm" type="button" href="#" onclick="edit(${data})"><i class="fas fa-edit"></i></a> | 
                  <a class = "btn btn-danger btn-sm" type="button" href="#" onclick="deleteById(${data})"><i class="fas fa-trash-alt"></i></a> 
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
        beforeSend : addRequestHeader(),
        success: (data) => {
            console.log(data);
            empId = id;
            employee = data;
            setForm();
        }
    });
}

function deleteById(id) {
    question("Do you want to delete this employee?", "employee deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/employee/${id}`,
            contentType: 'application/json',
            data: employee,
            beforeSend: addRequestHeader(),
            success: (data) => {
                success('employee deleted');
                $('#table_employee').DataTable().ajax.reload(null, false);
            },
            error: function (request, error) {
                console.log(arguments);
                alert(" Can't do because: " + error);
            }
        });
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
                    url: `http://localhost:8081/employee/${empId}`,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    beforeSend : addRequestHeader(),
                    success: (data) => {
                        console.log(data);
                        success('employee update');
                        $('#table_employee').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/employee`,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    beforeSend : addRequestHeader(),
                    dataType: 'json',
                    success: (data) => {
                        success('employee created');
                        $('#table_employee').DataTable().ajax.reload(null, false);
                    }
                });
            }
            $('.modal').modal('hide');
        }else{
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    })
}

function setValue() {
    employee.department.id = $('select[name="department"] option').filter(':selected').val();
    employee.firstName = $('#first_name').val();
    employee.lastName = $('#last_name').val();
    employee.email = $('#email').val();
    employee.address = $('#address').val();
    console.log(employee)
}

function setForm() {
    $('#first_name').val(employee.firstName);
    $('#last_name').val(employee.lastName);
    $('#email').val(employee.email);
    $('#address').val(employee.address);
    $('#departmentSelect').val(employee.department.id);
}

function disabledForm(isDisable) {
    $('#first_name').prop('disabled', isDisable);
    $('#last_name').prop('disabled', isDisable);
    $('#email').prop('disabled', isDisable);
    $('#address').prop('disabled', isDisable);
    $('#departmentSelect').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}