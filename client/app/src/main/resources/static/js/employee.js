var employee = {
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    departments: {
        id: 0
    }, projects: [
        {
            id: 0
        }
    ], users: {
        username: "",
        password: "",
        roles: [
            {
                id: 0
            }
        ]
    }
}; var employeeData = {
    departmentId: 0,
    email: "",
    firstName: "",
    lastName: "",
    password: "",
    phoneNumber: "",
    username: "",
    projects: [
        {
            id: 0
        }
    ], roles: [
        {
            id: 0
        }
    ]
}; var id = 0;
$(document).ready(function () {
    $('form').submit((e) => {
        e.preventDefault();
        id ? edit(id) : submitCreateEmployee();
    });
}); function create() {
    employee = {
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        departments: {
            id: 0
        }, projects: [
            {
                id: 0
            }
        ], users: {
            username: "",
            password: "",
            roles: [
                {
                    id: 0
                }
            ]
        }
    }; this.id = 0;
    setForm(employee);
    disabledForm(false);
} function setForm(employee) {
    $('#firstName').val(employee.firstName);
    $('#lastName').val(employee.lastName);
    $('#email').val(employee.email);
    $('#phoneNumber').val(employee.phoneNumber);
    $('#username').val(employee.users.username);
    $('#password').val(employee.users.password);
} function disabledForm(isDisable) {
    $('#firstName').prop('disabled', isDisable);
    $('#lastName').prop('disabled', isDisable);
    $('#email').prop('disabled', isDisable);
    $('#phoneNumber').prop('disabled', isDisable);
    $('#username').prop('disabled', isDisable);
    $('#password').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
} function submitCreateEmployee() {
    this.id = 0;
    setValue();
    $.ajax({
        type: "POST",
        url: `http://localhost:8081/users/create-register`,
        contentType: 'application/json',
        data: JSON.stringify(employeeData),
        dataType: 'json',
        beforeSend: addRequestHeader(),
        success: (data) => {
            success('Employee created');
        }
    });
} function edit(id) {
    this.id = id;
    getById(id);
    disabledForm(false);
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        $.ajax({
            type: "PUT",
            url: `http://localhost:8081/users/update-register/${id}`,
            contentType: 'application/json',
            data: JSON.stringify(employeeData),
            dataType: 'json',
            beforeSend: addRequestHeader(),
            success: (data) => {
                success('Employee updated!');
            }
        });
    });
} function deleteById(id) {
    question("Do you want to delete this department?", "Department deleted!", "Delete", () => {
        $.ajax({
            url: `http://localhost:8081/users/delete-register/${id}`,
            type: 'DELETE',
            dataType: 'json',
            beforeSend: addRequestHeader(),
            success: (data) => {
                success('Employee deleted!');
            }
        });
    });
} function setValue() {
    employeeData.departmentId = $('#departmentId').val();
    employeeData.email = $('#email').val();
    employeeData.firstName = $('#firstName').val();
    employeeData.lastName = $('#lastName').val();
    employeeData.password =  $('#password').val();
    employeeData.phoneNumber = $('#phoneNumber').val();
    employeeData.username =  $('#username').val();
    employeeData.projects[0].id = $('#projectId').val();
    employeeData.roles[0].id = $('#roleId').val();
} function getById(id) {
    $.ajax({
        url: `http://localhost:8081/employees/${id}`,
        type: 'GET',
        dataType: 'json',
        beforeSend: addRequestHeader(),
        success: (data) => {
            employee.firstName = data.firstName;
            employee.lastName = data.lastName;
            employee.email = data.email;
            employee.phoneNumber = data.phoneNumber;
            employee.users.username = data.users.username;
            employee.users.password = data.users.password;
            setForm(employee);
        }
    });
} function detail(id) {
    getById(id);
    setForm(employee);
    disabledForm(true);
}