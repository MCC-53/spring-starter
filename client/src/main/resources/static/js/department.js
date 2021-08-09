
var department = {};
var idData = 0;

var data = $('#department_table').DataTable({
    ajax: {
        url: 'http://localhost:8081/department',
        dataSrc: 'payLoad'
    },
    "columns": [
        {"data": "id"},
        {"data": "name"},
        {
            "data": "id",
            "render": function (data, type, row, meta)  {
                return `<div class="action-button">
                        <button 
                            class="btn btn-sm btn-primary" 
                            data-bs-toggle="modal" 
                            data-bs-target="#departmentModal"
                            onclick="detail(${data})"
                        >
                            <i class="fa fa-sm fa-eye"></i>
                        </button>
                        <button class="btn btn-sm btn-warning text-white" 
                            data-bs-toggle="modal" 
                            data-bs-target="#departmentModal"
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
setInterval(function () {
    data.ajax.reload(null, false);
}, 2000);

$(document).ready(function () {
    submit();
});

//function getAll() {
//    $.ajax({
//        url: 'http://localhost:8081/department',
//        type: 'GET',
//        dataType: 'json',
//        success: (res) => {
//            let row = null;
//            res.forEach((data) => {
//                row += `<tr>
//                            <td>${data.id}</td>
//                            <td>${data.name}</td>
//                            <td>
//                            <div class="action-button">
//                                <button 
//                                    class="btn btn-sm btn-primary" 
//                                    data-bs-toggle="modal" 
//                                    data-bs-target="#departmentModal"
//                                    onclick="detail(${data.id})"
//                                    >
//                                    <i class="fa fa-sm fa-eye"></i>
//                                </button>
//                                <button class="btn btn-sm btn-warning text-white" 
//                                    data-bs-toggle="modal" 
//                                    data-bs-target="#departmentModal"
//                                    onclick="edit(${data.id})"                                                    
//                                    >
//                                    <i class="fa fa-sm fa-edit"></i>
//                                </button>
//                                <button class="btn btn-sm btn-danger"
//                                    onclick="deleteById(${data.id})"  
//                                    >
//                                    <i class="fa fa-sm fa-trash"></i>
//                                </button>
//                            </div>
//                            </td>
//                        </tr>`;
//            });
//
//            $('tbody').html(row);
//            dataTable();
//        }
//    });
//}

function detail(id) {
    getById(id);

    disabledForm(true);
}

function setValue() {
    department.name = $('#deptname').val();
}

function create() {
    department = {};
    setForm({});
    disabledForm(false);
}

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if ($('.input-data').val()) {
            if (idData) {
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/department/${idData}`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        $(".modal").modal('hide');
                        success('department updated');
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        error("department failed to update");
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/department`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        $(".modal").modal('hide');
                        success('department created');
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        error("department failed to create");
                    }
                });

            }
        } else {
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    });
}

function edit(id) {
    getById(id);
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
                data.ajax.reload(null, false);
            },
            error: (data) => {
                error("department failed delete");
            }
        });
    });
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8081/department/${id}`,
        dataType: 'json',
        success: (data) => {
            idData = id;
            department.name = data.payLoad.name;
            setForm();
        }
    });
}

function setForm() {
    $('#deptname').val(department.name);
}

function disabledForm(isDisable) {
    $('#deptname').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}

