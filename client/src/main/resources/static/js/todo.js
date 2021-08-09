/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var todo = {};

function detail(id) {
    getById();
    
    setForm(todo);
    
    disabledForm(true);
}

function create() {
    setForm({});
    disabledForm(false);
}

function edit(id) {
    getById();
    setForm(todo);
    disabledForm(false);
}

function deleteById(id) {
    question("Do you want to delete this todo?", "todo deleted", "Delete", () => {
        window.location.href = "/";
    });
}

function getById() {
    todo.userId = 1;
    todo.title = "lorem ipsum";
    todo.completed = true;
}

function setForm(todo) {
    $('#userId').val(todo.userId);
    $('#title').val(todo.title);
    $('#completed').prop('checked', todo.completed);
}

function disabledForm(isDisable) {
    $('#userId').prop('disabled', isDisable);
    $('#title').prop('disabled', isDisable);
    $('#completed').prop('disabled', isDisable);
    $('#submitButton').prop('disabled', isDisable);
}
