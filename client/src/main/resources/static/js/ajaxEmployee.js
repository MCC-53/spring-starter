$('#myTable').DataTable( {
    ajax: {
        url: 'http://localhost:8081/employee',
        dataSrc: 'data'
    },
    columns: [
                { "data": "firstName"},
                { "data": "lastName" },
                { "data": "email" },
                { "data": "address" },
                { "data": "department.name" },
                { "data": "projects[].name" },
                { "data": "id",
                  render : function ( data, type, row, meta ) {
                    return ` <button
                                class="btn btn-sm btn-primary p-2 mr-2"
                                data-bs-toggle="modal" data-bs-target="#exampleModal"
                                onclick="details(${data})"
                             >
                                    <i class="fa fa-sm fa-eye"></i>
                                </button>
                                <button class="btn btn-sm btn-warning text-white p-2 mr-2"
                                        data-bs-toggle="modal"
                                        data-bs-target="#exampleModal"
                                        onclick="updates(${data})"
                                        >
                                    <i class="fa fa-sm fa-edit"></i>
                                </button>
                                <button class="btn btn-sm btn-danger p-2 mr-2"
                                        onclick="ajaxDel(${data})"
                                        >
                                    <i class="fa fa-sm fa-trash"></i>
                                </button> `

                    }
                }

    ]
} );

$(document).ready(function () {
    getAllDepartment();
    getAllProject();
    submit();
});

function getAllDepartment() {
    $.ajax({
        url: 'http://localhost:8081/department',
        type: 'GET',
        dataType: 'json',
        success: (res) => {
            let row = null;
            let data = res.data;
            data.forEach((data) => {
                row += `<option value="${data.id}">${data.name}</option>`;
            });

            $('#validationCustom04').html(row);
        }
    });
}

function getAllProject() {
    $.ajax({
        url: 'http://localhost:8081/project',
        type: 'GET',
        dataType: 'json',
        success: (res) => {
            let row = null;
            let data = res.data;
            data.forEach((data) => {
                    row += `<div class="form-check mb-4">
                                <input class="form-check-input" name="group1" type="checkbox" value="${data.id}">
                                <label class="form-check-label">${data.name}</label>
                            </div>`;
            });
            row = row.slice(4);
            $('#projectChoose').html(row);
        }
    });
}

temp = {};
id_temp = null;
function disable_ajax(_condition){
            $('#validationCustom01').prop('disabled', _condition);
            $('#validationCustom02').prop('disabled', _condition);
            $('#validationEmail').prop('disabled', _condition);
            $('#validationCustom03').prop('disabled', _condition);
            $('#validationCustom04').prop('disabled', _condition);
            $('#validationCustom05').prop('disabled', _condition);
            $('#chooseProj').prop('disabled', _condition);
            $('#submitForm').prop('disabled', _condition);
};



function create(){
    setForm({});
    disable_ajax(false);
}

function updates(id){
    getId_ajax(id);
    disable_ajax(false);
}



function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if(id_temp){
            $.ajax({
                        type: "PUT",
                        url: 'http://localhost:8081/employee/'+ id_temp,
                        contentType: 'application/json',
                        data: JSON.stringify(temp),
                        dataType: 'json',
                        success: (data) => {
                             $(".modal").modal('hide');
                             call_toast("success","Update Success");
                             $('#myTable').DataTable().ajax.reload(null, false);
                        },
                        error: (data) =>{
                             $(".modal").modal('hide');
                             call_toast("error","Update Error");
                        }
                    });
        } else{
        $.ajax({
            type: "POST",
            url: 'http://localhost:8081/employee',
            contentType: 'application/json',
            data: JSON.stringify(temp),
            dataType: 'json',
            success: (data) => {
                 $(".modal").modal('hide');
                 call_toast("success","Pos Success");
                 $('#myTable').DataTable().ajax.reload(null, false);
            },
            error: (data) =>{
                 $(".modal").modal('hide');
                 call_toast("error","Post Error");
            }
        });
        }
    });
}

function setValue() {
    temp.firstName = $('#validationCustom01').val();
    temp.lastName = $('#validationCustom02').val();
    temp.address = $('#validationCustom03').val();
    temp.email = $('#validationEmail').val();
    temp.department = $('#validationCustom04').val();
}

function details(id)
{
   getId_ajax(id);
   disable_ajax(true);
}


function getId_ajax(id)
{
    $.ajax({
       url: "http://localhost:8081/employee/"+ id,
       dataType: 'json',
       success: (hasil) => {
           id_temp = id;
           console.log(id_temp);
           temp.firstName = hasil.data.firstName;
           temp.lastName = hasil.data.lastName;
           temp.email = hasil.data.email;
           temp.address = hasil.data.address;
           temp.department = hasil.data.department.id;
           setForm();
       }
   });
}

function setForm() {
    $('#validationCustom01').val(temp.firstName);
    $('#validationCustom02').val(temp.lastName);
    $('#validationEmail').val(temp.email);
    $('#validationCustom03').val(temp.address);
    $('#validationCustom04').val(temp.department);
}

function ajaxDel(id){
     myFunction(()=>{
           $.ajax({
                    type : "DELETE",
                    url : "http://localhost:8081/employee/" + id,
                    contentType: "application/json",
                    dataType : 'json',
                    success: function (result) {
                           console.log(result);
                           call_toast("success","Success Delete")
                           $('#myTable').DataTable().ajax.reload(null, false);
                    },

                    error: function (e) {
                       call_toast("error","Errors")
                    }
                })

     });

 };