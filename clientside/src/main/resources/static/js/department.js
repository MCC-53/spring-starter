var department = {};
var dataId = '';

$(document).ready(function () {
    var table = $('#table').DataTable({
        ajax: {
            url: 'http://localhost:8087/api/department',
            dataSrc: 'content'
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
                "render": function (data, type, row, meta) {
                    return `
                       <div class="action-button">
                                    <button 
                                        class="btn btn-sm btn-primary"
                                        data-bs-toggle="modal"
                                        data-bs-target="#departmentModal"
                                        onclick="detail(${data.id})">
                                        <i class="fa fa-sm fa-eye"></i>
                                    </button>
                                    <button
                                        class="btn btn-sm btn-warning text-white"
                                        data-bs-toggle="modal"
                                        data-bs-target="#departmentModal"
                                        onclick="edit(${data.id})">
                                        <i class="fa fa-sm fa-edit"></i>
                                    </button>
                                    <button 
                                        class="btn btn-sm btn-danger"
                                        onclick="deleteById(${data.id})">
                                        <i class="fa fa-sm fa-trash"></i>
                                    </button>
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
    disabledForm(true);
}

function create() {
    department = {};
    setForm({});
    disabledForm(false);
}

function submit() {
    $('form').submit(e => {
        e.preventDefault();
        setValue();
        if ($('.input-data').val()) {
            if (dataId) {
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8087/api/department/${dataId}`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: data => {
                        $(".modal").modal('hide');
                        success('Department successfully updated');
                        data.ajax.reload(null, false);
                    },
                    error: data => {
                        error("Department cant be update")
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: 'http://localhost:8087/api/department',
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: data => {
                        $(".modal").modal('hide');
                        success('Department successfully created');
                        data.ajax.reload(null, false);
                    },
                    error: data => {
                        error("Department cant be create")
                    }
                });
            }
        }
        if (department) {

        } else {

        }
    })
}

function setValue() {
    department.name = $('#departmentName').val();
}

function edit(id) {
    getById(id);
    disabledForm(false);
}

function deleteById(id) {
    question("Do you wanna delete this department for sure?",
        "department successfully deleted",
    "Delete",
        () => {
            $.ajax({
                type: "DELETE",
                url: `http://localhost:8087/api/department/${id}`,
                dataType: 'json',
                success: data => {
                    success('department deleted');
                    data.ajax.reload(null, false)
                },
                error: data => {
                    error("Department cant be delete");
                }
            })
        })
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8087/api/department/${id}`,
        dataType: 'json',
        success: data => {
            dataId = id;
            department.name = data.content.name;
            setForm();
        }
    });
}

function setForm() {
    $('#departmentName').val(department.name);
}

function disabledForm(isDisable) {
    $('#departmentName').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable)
}