/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const baseUrl = `http://localhost:8080/department`;

$(document).ready(function () {
    showTable();
    eventSubmit();
});

function showTable() {
    var table = $('#departmentTable').DataTable({
        ajax: {
            url: `${baseUrl}/show`,
            dataSrc: ''
        },
        columns: [
            {
                name: "Id",
                data: "id"
            },
            {
                name: "Name",
                data: "name"
            },
            {
                name: "Action",
                data: "id",
                render: function (data, type, row, meta) {
                    return `<div class="action-button">
                                <button 
                                    class="btn btn-sm btn-primary" 
                                    data-bs-toggle="modal" 
                                    data-bs-target="#departmentModal"
                                    onclick="detail(${data})">
                                        <i class="fa fa-sm fa-eye"></i>
                                </button>
                                <button class="btn btn-sm btn-warning text-white" 
                                        data-bs-toggle="modal" 
                                        data-bs-target="#departmentModal"
                                        onclick="preparingUpdate(${data})">
                                    <i class="fa fa-sm fa-edit"></i>
                                </button>
                                <button class="btn btn-sm btn-danger"
                                        onclick="deleteById(${data})">
                                    <i class="fa fa-sm fa-trash"></i>
                                </button>
                            </div>`;
                }
            }
        ]
    });
}

function reloadTable() {
    $('#departmentTable').DataTable().ajax.reload(null, false);
}

function getById(id) {
    let department;
    $.ajax({
        url: `${baseUrl}/showbyid/${id}`,
        dataType: 'json',
        succes: (data) => {
            console.log(data);
            department=data;
        }
    });
    return department;
}

function detail(id){
    disabledForm(true);
    setForm(getById(id));
}

function deleteById(id) {
    question("Hapus department?", "Delete", () => {
        $.ajax({
            type: "DELETE",
            url: `${baseUrl}/delete/${id}`,
            success: (data) => {
                success(`department ${data.name} berhasil dihapus`);
                reloadTable();
            }
        });
    });
}

function preparingUpdate(id){
    disabledForm(false);
    setForm(getById(id));
}

function update(department) {
    $.ajax({
        type: "PUT",
        url: `${baseUrl}/update/${department.id}`,
        contentType: 'application/json',
        data: JSON.stringify(department),
        dataType: 'json',
        success: (data) => {
            success(`department ${data.name} berhasil diubah`);
            reloadTable();
        }
    });
}

function preparingCreate(){
    disabledForm(false);
    setForm({});
}

function create(department) {
    $.ajax({
        type: "POST",
        url: `${baseUrl}/create`,
        contentType: 'application/json',
        data: JSON.stringify(department),
        dataType: 'json',
        success: () => {
            success(`Berhasil menambah department`);
            reloadTable();
        }
    });
}

function setForm(department){
    $('#id').val(department.id);
    $('#name').val(department.name);
}

function setDepartment() {
    let department;
    department.id = $('#id').val();
    department.name = $('#name').val();
    return department;
}

function eventSubmit() {
    $('form').submit((e) => {
        e.preventDefault();
        let department = setDepartment();
        if (!department.id) {
            create(department);
        } else {
            update(department);
        }
    });
}

function disabledForm(isDisable){
    $('#name').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}