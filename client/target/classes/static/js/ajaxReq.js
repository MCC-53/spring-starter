var temp = {}; //penampung
 $(document).ready(
    function() {
      // SUBMIT FORM
      $("#postForm").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();//Tidak langsung reload walaupun kosong
        ajaxPost();
      });

      function ajaxPost() {

        // PREPARE FORM DATA
        var formData = {
          name : $("#validationCustomUsername").val()
        }
        // DO POST
        $.ajax({
          type : "POST",
          contentType : "application/json",
          url : "department/save",
          data : JSON.stringify(formData),
          dataType : 'json',
          success: function(hasil){
                $(".modal").modal('hide');
                call_toast('Post Success');
                location.reload();
          }
//          success : function(result) {
//            if (result.status == "success") {
//              $("#postResultDiv").html(
//                  "" + result.data.bookName
//                      + "Post Successfully! <br>"
//                      + "---> Congrats !!" + "</p>");
//            } else {
//              $("#postResultDiv").html("<strong>Error</strong>");
//            }
//            console.log(result);
//          },
//          error : function(e) {
//            alert("Error!")
//            console.log("ERROR: ", e);
//          }
        });

      }

      function ajaxUpdate(id) {

              // PREPARE FORM DATA
              var formData = {
                name : $("#validationCustomUsername").val(),
                id : $("#id_dept").val()
              }
              // DO UPDAATE
              $.ajax({
                type : "PUT",
                contentType : "application/json",
                url : "department/" + id,
                data : JSON.stringify(formData),
                dataType : 'json',
                success: function(hasil){
                      $(".modal").modal('hide');
                      call_toast('Update Success');
//                      location.reload();
                }
      //          success : function(result) {
      //            if (result.status == "success") {
      //              $("#postResultDiv").html(
      //                  "" + result.data.bookName
      //                      + "Post Successfully! <br>"
      //                      + "---> Congrats !!" + "</p>");
      //            } else {
      //              $("#postResultDiv").html("<strong>Error</strong>");
      //            }
      //            console.log(result);
      //          },
      //          error : function(e) {
      //            alert("Error!")
      //            console.log("ERROR: ", e);
      //          }
              });

            }


    });




$(document).ready(function () {
    getAll();
});

       function getAll() {
           $.ajax({
               url: '/department/get-all',
               type: 'GET',
               dataType: 'json',
               success: (res) => {
                   let row = null;//Inisialisasi sebagai penampung
                   res.forEach((data) => {
                       row += `<tr>
                                   <td>${data.id}</td>
                                   <td>${data.name}</td>
                                   <td>
                                       <button
                                           class="btn btn-sm btn-primary p-2 mr-2"
                                           data-bs-toggle="modal"
                                           data-bs-target="#exampleModal"
                                           onclick="details(${data.id})"
                                        >
                                               <i class="fa fa-sm fa-eye"></i>
                                           </button>
                                           <button class="btn btn-sm btn-warning text-white p-2 mr-2"
                                                   data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal2"
                                                   onclick="updates_ajax(${data.id})"
                                                   >
                                               <i class="fa fa-sm fa-edit"></i>
                                           </button>
                                           <button class="btn btn-sm btn-danger p-2 mr-2"
                                                   onclick="ajaxDel(${data.id})"
                                                   >
                                               <i class="fa fa-sm fa-trash"></i>
                                           </button>
                                   </td>
                               </tr>`;
                   });

                   $('tbody').html(row);
                   dataTable();
               }
           });
       }



       function ajaxDel(id){
                     alert_sweet();
                     $.ajax({
                         type : "GET",
                         url : "department/delete/" + id,
                         contentType: "application/json",
                         dataType : 'json',
                         success: function (result) {
                                console.log(result);
                                location.reload();
                         },

                         error: function (e) {
                            location.reload();
                             console.log(e);
                             console.log("ada yang error");
                         }
                     })
//                     location.reload();
                     };

       function getId_ajax(id)
       {
            $.ajax({
                           url: `/department/${id}`,
                           dataType: 'json',
                           success: (data) => {
                               $('#validationCustomUsername').val(data.name);
                               $('#id_dept').val(data.id);
                           }
                       });
       }

       function getId_ajax2(id)
              {
                   $.ajax({
                                  url: `/department/${id}`,
                                  dataType: 'json',
                                  success: (data) => {
                                      $('#validationCustomUsername2').val(data.name);
                                      $('#id_depts').val(data.id);
                                  }
                              });
              }

       function details(id) //Get By Id
       {
           getId_ajax(id);
           disable_ajax(true);
       }

       function updates_ajax(id) //Get By Id
              {
              getId_ajax2(id);
              disable_ajax(false);
              $('#postForm2').submit((e) => {
              e.preventDefault();
              // PREPARE FORM DATA
              var formData = {
                name : $("#validationCustomUsername2").val(),
                id : $("#id_depts").val()
              }
              // DO UPDATE
              $.ajax({
                type : "PUT",
                contentType : "application/json",
                url : "department/" + id,
                data : JSON.stringify(formData),
                dataType : 'json',
                success: function(hasil){
                      $(".modal").modal('hide');
                      call_toast('Update Success');
//                      location.reload();
                }
      //          success : function(result) {
      //            if (result.status == "success") {
      //              $("#postResultDiv").html(
      //                  "" + result.data.bookName
      //                      + "Post Successfully! <br>"
      //                      + "---> Congrats !!" + "</p>");
      //            } else {
      //              $("#postResultDiv").html("<strong>Error</strong>");
      //            }
      //            console.log(result);
      //          },
      //          error : function(e) {
      //            alert("Error!")
      //            console.log("ERROR: ", e);
      //          }
              });
              });
              }
       function disable_ajax(_condition){
            $('#validationCustomUsername').prop('disabled', _condition);
            $('#submitForm').prop('disabled', _condition);
       };

       function alert_sweet()
       {
            Swal.fire({
              title: 'Are you sure?',
              text: "You won't be able to revert this!",
              icon: 'warning',
              showCancelButton: true,
              confirmButtonColor: '#3085d6',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
              if (result.isConfirmed) {
                    call_toast('Deleted Success');
              }
            })
       }

       function call_toast(_word){
            const Toast = Swal.mixin({
                              toast: true,
                              position: 'top-end',
                              showConfirmButton: false,
                              timer: 3000,
                              timerProgressBar: true,
                              didOpen: (toast) => {
                                toast.addEventListener('mouseenter', Swal.stopTimer)
                                toast.addEventListener('mouseleave', Swal.resumeTimer)
                              }
                            })
                            Toast.fire({
                              icon: 'success',
                              title: _word
                            })
       };

       function reset(){
                   $('#validationCustomUsername').val('');
               }


