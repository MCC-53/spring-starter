var dataTableDepartment = $('#table_id').DataTable( {
    ajax: {
        url: 'http://localhost:8085/department',
        dataSrc: ''
    },
    columns: [ { 'data' : 'id'}, 
               { 'data' : 'name' },
               {'data' : 'id',
               'render' : function(data) {
               return `<div class="action-button">
                               <button
                                    class="btn btn-sm btn-primary"
                                    data-bs-toggle="modal"
                                    data-bs-target="#departmentModal"
                                    onclick="detail('${data}')"
                                 >
                                        <i class="fa fa-sm fa-eye"></i>
                                    </button>
                                    <button class="btn btn-sm btn-warning text-white"
                                            data-bs-toggle="modal"
                                            data-bs-target="#departmentModal"
                                            onclick="edit('${data}')"
                                            >
                                        <i class="fa fa-sm fa-edit"></i>
                                    </button>
                                    <button class="btn btn-sm btn-danger"
                                            onclick="deleteById('${data}')"
                                            >
                                        <i class="fa fa-sm fa-trash"></i>
                                    </button>
                                </div>` }} ]
} );
