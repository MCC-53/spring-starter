
let employee = {};
let idEmployee = 0;

var data = $('#employee_table').DataTable({
    ajax: {
        url: 'http://localhost:8081/employee',
        dataSrc: 'payLoad',
        beforeSend: addRequestHeader()
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
                            data-toggle="modal" 
                            data-target="#employeeModal"
                            onclick="detail(${data})"
                        >
                            <i class="fa fa-sm fa-eye"></i>
                        </button>
                        <button class="btn btn-sm btn-warning text-white" 
                            data-toggle="modal" 
                            data-target="#employeeModal"
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
        beforeSend: addRequestHeader(),
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
    confirmToast("DELETE", "Once deleted, you will not be able to recover this imaginary file!", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/employee/${id}`,
            dataType: 'json',
            beforeSend: addRequestHeader(),
            success: () => {
                successToast("Employee has been deleted!");
                data.ajax.reload(null, false);
            },
            error: (data) => {
                errorToast("Delete employee failed!");
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
                    beforeSend: addRequestHeader(),
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    success: () => {
                        $(".modal").modal('hide');
                        successToast("Employee has been updated!");
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        errorToast("Update employee failed!");
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/employee`,
                    beforeSend: addRequestHeader(),
                    contentType: 'application/json',
                    data: JSON.stringify(employee),
                    dataType: 'json',
                    success: () => {
                        $(".modal").modal('hide');
                        successToast("Employee has been created!");
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        errorToast("Create employee failed!");
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
    $('#submitButton').prop('disabled', isDisable);
}
