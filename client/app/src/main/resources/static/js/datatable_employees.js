var data = $('#table').DataTable({
    ajax : {
        url: '/employees/get-all',
        dataSrc: ''
    },
    "columns": [
        {
            "name": "Employee ID",
            "data": "id",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data + '</div></div>';
            }
        },
        {
            "name": "Employee First Name",
            "data": "firstName",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data + '</div></div>';
            }
        },
        {
            "name": "Employee Last Name",
            "data": "lastName",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data + '</div></div>';
            }
        },
        {
            "name": "Employee Email",
            "data": "email",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data + '</div></div>';
            }
        },
        {
            "name": "Employee Phone Number",
            "data": "phoneNumber",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data + '</div></div>';
            }
        },
        {
            "name": "Department ID",
            "data": "departments",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data.id + '</div></div>';
            }
        },
        {
            "name": "Department Name",
            "data": "departments",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data.name + '</div></div>';
            }
        },
        {
            "name": "Project ID",
            "data": "projects",
            "render": function (data, type, row, meta) {
                let projectId = "";
                $.each(data, function(index){
                    projectId += data[index].id + "<br><br>";
                }); return '<div class="row center"><div class="col">' + projectId + '</div></div>';
            }
        },
        {
            "name": "Project Name",
            "data": "projects",
            "render": function (data, type, row, meta) {
                let projectName = "";
                $.each(data, function(index){
                    projectName += data[index].name + "<br><br>";
                }); return '<div class="row center"><div class="col">' + projectName + '</div></div>';
            }
        },
        {
            "name": "Project Description",
            "data": "projects",
            "render": function (data, type, row, meta) {
                let projectDescription = "";
                $.each(data, function(index){
                    projectDescription += data[index].description + "<br><br>";
                }); return '<div class="row center"><div class="col">' + projectDescription + '</div></div>';
            }
        },
        {
            "name": "Username",
            "data": "users",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data.username + '</div></div>';
            }
        },
        {
            "name": "Password",
            "data": "users",
            "render": function (data, type, row, meta) {
                return '<div class="row center"><div class="col">' + data.password + '</div></div>';
            }
        },
        {
            "name": "Role ID",
            "data": "users",
            "render": function (data, type, row, meta) {
                let roleId = "";
                $.each(data.roles, function(index){
                    roleId += data.roles[index].id + "<br><br>";
                }); return '<div class="row center"><div class="col">' + roleId + '</div></div>';
            }
        },
        {
            "name": "Employee First Name",
            "data": "users",
            "render": function (data, type, row, meta) {
                let roleName = "";
                $.each(data.roles, function(index){
                    roleName += data.roles[index].name + "<br><br>";
                });return '<div class="row center"><div class="col">' + roleName + '</div></div>';
            }
        },
        {
            "name": "Privilege ID",
            "data": "users",
            "render": function (data, type, row, meta) {
                let privilegeId = "";
                $.each(data.roles, function(index){
                    $.each(data.roles[index].privileges, function(index1){
                        privilegeId += data.roles[index].privileges[index1].id + "<br><br>";
                    });
                }); return '<div class="row center"><div class="col">' + privilegeId + '</div></div>';
            }
        },
        {
            "name": "Privilege Name",
            "data": "users",
            "render": function (data, type, row, meta) {
                let privilegeName = "";
                $.each(data.roles, function(index){
                    $.each(data.roles[index].privileges, function(index1){
                        privilegeName += data.roles[index].privileges[index1].name + "<br><br>";
                    });
                }); return '<div class="row center"><div class="col">' + privilegeName + '</div></div>';
            }
        },
        {
            "name": "Action",
            "data": "id",
            "render": function (data, type, row, meta) {
                return '<div class="action-button">\n\
                            <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#employeeModal" onclick="detail(' + data + ')">\n\
                                <i class="fa fa-sm fa-eye"></i>\n\
                            </button>\n\
                            <button class="btn btn-sm btn-warning text-white" data-bs-toggle="modal" data-bs-target="#employeeModal" onclick="edit(' + data + ')">\n\
                                <i class="fa fa-sm fa-edit"></i>\n\
                            </button>\n\
                            <button class="btn btn-sm btn-danger" onclick="deleteById(' + data + ')">\n\
                                <i class="fa fa-sm fa-trash"></i>\n\
                            </button>\n\
                        </div>';
            }
        }
    ]
}); (function () {
    'use strict'
    var forms = document.querySelectorAll('.needs-validation');
    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            } form.classList.add('was-validated')
        }, false)
    })
})(); setInterval(function() {
    data.ajax.reload(null, false);
}, 1000);