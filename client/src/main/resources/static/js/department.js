/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const baseUrl = `http://localhost:8080/department`;
let department={};

$(document).ready(function () {
    showTable();
    eventSubmit();
});

function showTable() {
    var table = $('#departmentTable').DataTable({
        ajax: {
            url: `department/api`,
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
    $.ajax({
        url: `department/api/${id}`,
        dataType: 'json',
        success: (data) => {
            department.id = data.id;
            department.name = data.name;
            setForm();
        }
    });
}

function detail(id){
    getById(id);
    disabledForm(true);
}

function deleteById(id) {
    question("Hapus department?", "Delete", () => {
        $.ajax({
            type: "POST",
            url: `department/api/delete/${id}`,
            success: (data) => {
                success(`department ${data.name} berhasil dihapus`);
                reloadTable();
            }
        });
    });
}

function preparingUpdate(id){
    getById(id);
    disabledForm(false);
}

function update(department) {
    $.ajax({
        type: "POST",
        url: `department/api/update`,
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
    department = {};
    setForm();
    disabledForm(false);
}

function create(department) {
    $.ajax({
        type: "POST",
        url: `department/api/create`,
        contentType: 'application/json',
        data: JSON.stringify(department),
        dataType: 'json',
        success: () => {
            success(`Berhasil menambah department`);
            reloadTable();
        }
    });
}

function setForm(){
    $('#id').val(department.id);
    $('#name').val(department.name);
}

function setDepartment() {
    department.id = $('#id').val();
    department.name = $('#name').val();
}

function eventSubmit() {
    $('form').submit((e) => {
        e.preventDefault();
        setDepartment();
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