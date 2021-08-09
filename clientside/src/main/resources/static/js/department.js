var department = {};
var dataId;

$(document).ready(function () {
    getAll();
    submit();
})

function getAll() {
    $.ajax({
        url: '/department',
        type: 'GET',
        dataType: 'json',
        success: response => {
            let row = null;
            response.forEach(data => {
                row += `<tr>
                            <td> ${data.id}</td>
                            <td> ${data.name}</td>
                            <td>
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
                            </td>
                        </tr>`;
            });
            $('tbody').html(row);
            dataTable();
        }
    })
}

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
        if (department) {
            $.ajax({
                type: "POST",
                url: '/department/form',
                contentType: 'application/json',
                data: department,
                dataType: 'json',
                success: () => {
                    success("department created");
                }
            });
        } else {
            $.ajax({
                type: "PUT",
                url: '/department/reform',
                contentType: 'application/json',
                data: department,
                success: () => {
                    success("department updated");
                }
            })
        }
    })
}

function setValue() {
    department.name = $('#name').val();
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
            window.location.href = "/";
        })
}

function getById(id) {
    $.ajax({
        url: `department/${id}`,
        dataType: 'json',
        success: data => {
            dataId = id;
            department.name = data.name;
            setForm();
        }
    });
}

function setForm() {
    $('#departmentName').val(department.name);
}

function disabledForm(isDisable) {
    $('#departmentName').val('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable)
}