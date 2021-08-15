
var department = {};
var idData = 0;

var data = $('#department_table').DataTable({
    ajax: {
        url: `http://localhost:8081/department`,
        dataSrc: 'payLoad',
        beforeSend: addRequestHeader()
    },
    "columns": [
        {"data": "id"},
        {"data": "name"},
        {
            "data": "id",
            "render": function (data, type, row, meta) {
                return `<div class="action-button">
                        <button 
                            class="btn btn-sm btn-primary" 
                            data-toggle="modal" 
                            data-target="#departmentModal"
                            onclick="detail(${data})"
                        >
                            <i class="fa fa-sm fa-eye"></i>
                        </button>
                        <button class="btn btn-sm btn-warning text-white" 
                            data-toggle="modal" 
                            data-target="#departmentModal"
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

$(document).ready(function () {
    submit();
});

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
                    beforeSend: addRequestHeader(),
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: () => {
                        $(".modal").modal('hide');
                        successToast("Department has been updated!")
                        data.ajax.reload(null, false);
                    },
                    error: () => {
                        errorToast("Update department failed!");
                    }
                });
            } else {
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/department`,
                    contentType: 'application/json',
                    beforeSend: addRequestHeader(),
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: () => {
                        $(".modal").modal('hide');
                        successToast("Department has been created!")
                        data.ajax.reload(null, false);
                    },
                    error: (data) => {
                        errorToast("Create department failed!");
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
    confirmToast("DELETE", "Once deleted, you will not be able to recover this imaginary file!", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/department/${id}`,
            beforeSend: addRequestHeader(),
            dataType: 'json',
            success: () => {
                successToast("Department has been deleted!")
                data.ajax.reload(null, false);
            },
            error: () => {
                errorToast("Delete department failed!");
            }
        });
    });
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8081/department/${id}`,
        beforeSend: addRequestHeader(),
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

