/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var department = {};
var depId;

$(document).ready(function () {
    $('#table_id').DataTable({
        ajax: {
            url: 'http://localhost:8081/department',
            dataSrc: '',
            beforeSend: addRequestHeader()
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
    $('#departmentModal').modal('show');
    disabledForm(true);
}

function getById(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8081/department/${id}`,
        dataType: 'json',
        beforeSend: addRequestHeader(),
        success: (data) => {
            depId = id;
            department.name = data.name;
            setForm();
        }
    });
}

function create() {
    department = {};
    depId = null;
    setForm({});
    disabledForm(false);
}


function edit(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(false);
}



function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if ($('.input-data').val()) {
            if (depId) {
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8081/department/${depId}`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    beforeSend: addRequestHeader(),
                    success: (data) => {
                        console.log(data);
                        success('department updated');
                        $('#table_id').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            } else {
//                var _this = this;
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8081/department/`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    beforeSend: addRequestHeader(),
                    success: (data) => {
                        success('department created');
                        $('#table_id').DataTable().ajax.reload(null, false);
                    }
                });
            }
            $('.modal').modal('hide');
            // setInterval('refreshPage()', 1000);
        } else {
            e.preventDefault();
            $('.needs-validation').addClass('was-validated')
        }
    })
}


function setValue() {
    department.name = $('#name_dept').val();
}


function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8081/department/${id}`,
            contentType: 'application/json',
            data: department,
            beforeSend: addRequestHeader(),
            success: (data) => {
                $('.modal').modal('hide');
                success('department deleted');
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
    $('#name_dept').val(department.name);
}

function disabledForm(isDisable) {
    $('#name_dept').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}