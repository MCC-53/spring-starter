var department = {};
var dept_id = null;

$(document).ready(function () {
//    getAll();
    submit();
});

//function getAll() {
//    $.ajax({
//        url: '/department/get-all',
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
//                                 >
//                                        <i class="fa fa-sm fa-eye"></i>
//                                    </button>
//                                    <button class="btn btn-sm btn-warning text-white"
//                                            data-bs-toggle="modal"
//                                            data-bs-target="#departmentModal"
//                                            onclick="edit(${data.id})"
//                                            >
//                                        <i class="fa fa-sm fa-edit"></i>
//                                    </button>
//                                    <button class="btn btn-sm btn-danger"
//                                            onclick="deleteById(${data.id})"
//                                            >
//                                        <i class="fa fa-sm fa-trash"></i>
//                                    </button>
//                                </div>
//                            </td>
//                        </tr>`;
//            });
//
//            $('tbody').html(row);
//            dataTable();
//        }
//    });
//}


function create() {
    department={};
    dept_id=null;
    setForm({});
    disabledForm(false);
}

function submit() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        if (dept_id) {
            $.ajax({
                type: "PUT",
                url: `http://localhost:8085/department/${dept_id}`,
                contentType: 'application/json',
                data: JSON.stringify(department),
                dataType:'json',
                success: (data) => {
                    success('department updated');
                    $('.modal').modal('hide');
                    dataTableDepartment.ajax.reload(null, false);
                },
                error: (data) => {
                    error('coba cek network');
                }
            })
        }else {
            $.ajax({
                type: "POST",
                url: 'http://localhost:8085/department',
                contentType: 'application/json',
                data: JSON.stringify(department),
                dataType: 'json',
                success: (data) => {
                    success('department created');
                    $('.modal').modal('hide');
                    dataTableDepartment.ajax.reload(null, false);
                }
            });            
        }
    });
}

function setValue() {
    department.name = $('#name_dept').val();
}

function edit(id) {
    getById(id);
    setForm(department);
    disabledForm(false);
}

function deleteById(id) {
    question("Do you want to delete this department?", "department deleted", "Delete", () => {
        $.ajax({
            type: "DELETE",
                url: `http://localhost:8085/department/${id}`,
                dataType:'json',
                success: (data) => {
                    success('department deleted');
                    $('.modal').modal('hide');
                    dataTableDepartment.ajax.reload(null, false);
                },
                error: (data) => {
                    error('coba cek network');
                }
        })
    });
}


function detail(id) {
    getById(id);
    disabledForm(true);
}

function getById(id) {
    $.ajax({
        url: `http://localhost:8085/department/${id}`,
        dataType: 'json',
        type: "GET",
        success: (data) => {
            department.name = data.name;
            dept_id = data.id;
            setForm();
        }
    });
}

function setForm() {
    $('#name_dept').val(department.name);
}
function disabledForm(isDisable) {
    $('#name_dept').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);

}
