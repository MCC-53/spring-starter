
let employee = {};
let idEmployee = 0;

var data = $('#employee_table').DataTable({
    ajax: {
        url: 'http://localhost:8081/employee',
        dataSrc: 'payLoad'
    },
    "columns":[
        {"data": "id"},
        {"data": "firstName"},
        {"data": "lastName"},
        {"data": "email"},
        {"data": "address"},
        {"data": "department.name"},
        {
            "data": "id",
            "render": function (data)  {
                return `<div class="action-button">
                        <button 
                            class="btn btn-sm btn-primary" 
                            data-bs-toggle="modal" 
                            data-bs-target="#employeeModal"
                            onclick="detail(${data})"
                        >
                            <i class="fa fa-sm fa-eye"></i>
                        </button>
                        <button class="btn btn-sm btn-warning text-white" 
                            data-bs-toggle="modal" 
                            data-bs-target="#employeeModal"
                            onclick="edit(${data})"                                                    
                        >
                            <i class="fa fa-sm fa-edit"></i>
                        </button>
                        <button class="btn btn-sm btn-danger"
                            onclick="deleteById(${data})"  
                        >
                            <i class="fa fa-sm fa-trash"></i>
                        </button>
                    </div>`;
            }
        }
        
    ]
});
//setInterval(function () {
//    data.ajax.reload(null, false);
//}, 2000);

function getById(id) {
    $.ajax({
        url: `http://localhost:8081/employee/${id}`,
        dataType: 'json',
        success: (data) => {
            idEmployee = id;
            employee.firstName = data.payLoad.firstName;
            employee.lastName = data.payLoad.lastName;
            employee.email = data.payLoad.email;
            employee.address = data.payLoad.address;
            employee.department = data.payLoad.department.id;
            setForm();
        }
    });
}

function detail(id) {
    getById(id);

    disabledForm(true);
}

function deleteById(id) {
    question("Do you want to delete this employee?", "employee deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/employee/${id}`,
            dataType: 'json',
            success: (data) => {
                success('employee deleted');
                ajax.reload(null, false);
            },
            error: (data) => {
                error("employee failed delete");
            }
        });
    });
}

function edit(id) {
    getById(id);
    disabledForm(false);
    submit();
}

function create() {
    employee = {};
    setForm({});
    disabledForm(false);
    submit();
}

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if ($('.input-data').val()) {
            if (idEmployee) {
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/employee/${idEmployee}`,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    success: (data) => {
                        $(".modal").modal('hide');
                        success('employee updated');
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        error("employee failed to update");
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/employee`,
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    success: (data) => {
                        $(".modal").modal('hide');
                        success('employee created');
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        error("employee failed to create");
                    }
                });

            }
        } else {
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    });
}

function setValue() {
    console.log(employee);
    employee.firstName = $('#firstname').val();
    employee.lastName = $('#lastname').val();
    employee.email = $('#email').val();
    employee.address = $('#address').val();
    employee.department = $('#department_select').find(":selected").val();
}

function setForm() {
    $('#firstname').val(employee.firstName);
    $('#lastname').val(employee.lastName);
    $('#email').val(employee.email);
    $('#address').val(employee.address);
    $('#department_select').val(employee.department).change();
}

function disabledForm(isDisable) {
    $('#firstname').prop('disabled', isDisable);
    $('#lastname').prop('disabled', isDisable);
    $('#email').prop('disabled', isDisable);
    $('#address').prop('disabled', isDisable);
    $('#department_select').prop('disabled', isDisable);
}

//function getAll() {
//    $.ajax({
//        url: 'http://localhost:8081/department',
//        type: 'GET',
//        dataType: 'json',
//        success: (res) => {
//            let optionSelect = null;
//            res.forEach((data) => {
//                optionSelect += `
//                    <option>${data.id}</option>`;
//            });
//            $('select').html(row);
//        }
//    });
//}