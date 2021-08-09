/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var department = {};
var deptId;

$(document).ready(function () {
    var table= $('#table').DataTable({
        ajax : {
            url : 'http://localhost:8085/department',
            dataSrc : ''
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
                "render": function ( data, type, row, meta ) {
                    return `
              <div class="d-flex justify-content-center">
                  <a class="btn btn-info" href="#" onclick="detail(${data})"><i class="fa fa-eye" aria-hidden="true"></i></a>
                  <a class="btn btn-info" href="#" onclick="edit(${data})"><i class="far fa-edit"></i></a>
                  <a class="btn btn-info" href="#" onclick="deleteById(${data})"><i class="fa fa-trash" aria-hidden="true"></i></a>
              </div>
            `;
                }
            }
        ]
    });
    submit();
});


function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if($('.input-data').val()){
            if(deptId){
                $.ajax({
                    type: "PUT",
                    url: `http://localhost:8085/department/${deptId}` ,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        console.log(data);
                        success('department update');
                        $('#table').DataTable().ajax.reload(null, false);
                    },
                    error: function (request, error) {
                        console.log(arguments);
                        alert(" Can't do because: " + error);
                    }
                });
            }else{
                var _this = this;
                $.ajax({
                    type: "POST",
                    url: `http://localhost:8085/department/`,
                    contentType: 'application/json',
                    data: JSON.stringify(department),
                    dataType: 'json',
                    success: (data) => {
                        success('department created');
                        $('#table').DataTable().ajax.reload(null, false);
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

function detail(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(true);
}

function setValue() {
    department.name = $('#deptname').val();
}

function edit(id) {
    getById(id);
    $('#departmentModal').modal('show');
    disabledForm(false);
}

function create() {
    department={};
    deptId=null;
    setForm({});
    disabledForm(false);
}

function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `http://localhost:8085/department/${id}`,
            dataType: 'json',
            success: (data) => {
                success('department deleted');
                $('#table').DataTable().ajax.reload(null, false);
            }
        });
    });
}

function getById(id) {
    $.ajax({
        type: "GET",
        url: `/department/${id}`,
        dataType: 'json',
        success: (data) => {
            deptId = id;
            department.name = data.name;
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
