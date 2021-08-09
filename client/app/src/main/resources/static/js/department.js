var department = {};
$(document).ready(function () {
    getAllDepartment();
    submitCreateDepartment();
}); function getAllDepartment() {
    $.ajax({
        url: '/departments/get-all',
        type: 'GET',
        dataType: 'json',
        success: (res) => {
            let row = null;
            res.forEach((data) => {
                row += `<tr>
                            <td>${data.id}</td>
                            <td>${data.name}</td>
                            <td>
                                <div class="action-button">
                                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#departmentModal" onclick="detail(${data.id})">
                                        <i class="fa fa-sm fa-eye"></i>
                                    </button>
                                    <button class="btn btn-sm btn-warning text-white" data-bs-toggle="modal" data-bs-target="#departmentModal" onclick="edit(${data.id})">
                                        <i class="fa fa-sm fa-edit"></i>
                                    </button>
                                    <button class="btn btn-sm btn-danger" onclick="deleteById(${data.id})">
                                        <i class="fa fa-sm fa-trash"></i>
                                    </button>
                                </div>
                            </td>
                            </td>
                        </tr>`;
            }); $('tbody').html(row);
            dataTable();
        }
    });
} function setValue() {
    department.name = $('#name').val();
} function submitCreateDepartment() {
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        $.ajax({
            type: "POST",
            url: `/departments/save`,
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: (data) => {
                success('Department created');
            }
        });
    });
} function detail(id) {
    console.log(id);
    getById(id);
    setForm(department);
    disabledForm(true);
} function create() {
    todo = null;
    setForm({});
    disabledForm(false);
} function edit(id) {
    getById(id);
    setForm(department);
    disabledForm(false);
    $('form').submit((e) => {
        e.preventDefault();
        setValue();
        $.ajax({
            type: "PUT",
            url: `/departments/update`,
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: (data) => {
                success('Department updated!');
            }
        });
    });
} function deleteById(id) {
    question("Do you want to delete this department?", "Department deleted!", "Delete", () => {
        $.ajax({
            url: `/departments/delete/${id}`,
            type: 'DELETE',
            dataType: 'json',
            success: (data) => {
                success('Department deleted!');
            }
        });
    });
} function getById(id) {
    $.ajax({
        url: `/departments/get-by-id/${id}`,
        type: 'GET',
        dataType: 'json',
        success: (data) => {
            department.id = data.id;
            department.name = data.name;
            setForm(department);
        }
    });
} function setForm(department) {
    $('#name').val(department.name);
} function disabledForm(isDisable) {
    $('#name').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}