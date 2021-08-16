$('#myTable').DataTable( {
    ajax: {
        url: 'http://localhost:8081/department',
        dataSrc: 'data',
    },
    columns: [
                { "data": "name"},
                { "data": "id",
                  render : function ( data, type, row, meta ) {
                    return ` <button
                                class="btn btn-sm btn-primary p-2 mr-2"
                                data-bs-toggle="modal"
                                data-bs-target="#exampleModal"
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
    submit();
});

temp = {};
id_temp = null;
function disable_ajax(_condition){
            $('#validationCustomUsername').prop('disabled', _condition);
            $('#submitForm').prop('disabled', _condition);
};

function create(){
    temp = {};
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
        setValue();s
        if(id_temp){
            console.log("id: ",id_temp);
            $.ajax({
                        type: "PUT",
                        url: 'http://localhost:8081/department/'+ id_temp,
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
            url: 'http://localhost:8081/department',
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

function details(id)
{
   getId_ajax(id);
   disable_ajax(true);
}

function getId_ajax(id)
{
    $.ajax({
       url: "http://localhost:8081/department/"+ id,
       dataType: 'json',
       success: (hasil) => {
           id_temp = id;
           temp.name = hasil.data.name;
           setForm();
       }
   });
}

function setForm() {
    $('#validationCustomUsername').val(temp.name);
}
function setValue() {
    temp.name = $('#validationCustomUsername').val();
}

function ajaxDel(id){
     myFunction(()=>{
           $.ajax({
                    type : "DELETE",
                    url : "http://localhost:8081/department/" + id,
                    contentType: "application/json",
                    dataType : 'json',
                    success: function (result) {
                           console.log(result);
                           call_toast("success","Success Delete")
                           $('#myTable').DataTable().ajax.reload(null, false);
                    },

                    error: function (e) {
                       call_toast("error","Errors")
                       data.ajax.reload( null, false);
                    }
                })

     });

 };