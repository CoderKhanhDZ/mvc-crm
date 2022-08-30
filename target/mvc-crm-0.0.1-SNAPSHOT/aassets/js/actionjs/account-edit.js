/**
 * account edit
 */
  $(function(){
	$('.btn-account-edit').click(function(){
		
		var sid =$(this).data("id");
		var link = 'http://localhost:8080/mvc-crm/account/edit';
		$.ajax({
			url: link,
			type: 'GET',
			dataType: 'APPLICATION/JSON',
			data: {
				id : sid,
				action: 'edit'
			},
			success: function(resp){
				$("#id").val(resp.id);
				$("#email ").val(resp.email);
				$('.control-password').hide();
				$("#fullname").val(resp.fullname);
				$("#phone ").val(resp.phone);
				$("#address").val(resp.address);
				$("#role_id").val(resp.role_id);
				console.log(resp);
			},
			error: function(error){
				showToastr('error',error)
			}
		})
	})
});