$( document ).ready(function() {
	
	// SUBMIT FORM
    $("#customerForm").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		ajaxPost();
		$(location).attr('href',"https://www.google.com/?trackid=sp-006&s=23");
	});
    
    
    function ajaxPost(){
    	
    	// PREPARE FORM DATA
    	var formData = {
    		firstname : $("#idQuestion").val(),
    		firstname : $("#question").val(),
    		firstname : $("#idExamen").val(),
    		firstname : $("#choice").val()
    		//firstname : $("#firstname").val(),
    		//lastname :  $("#lastname").val()
    	}
    	
    	// DO POST
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : window.location + "api/customer/saveRespponse2",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				if(result.status == "Done"){
					$("#postResultDiv").html("<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>" + 
												"Post Successfully! <br>"
												);
					
				}else{
					$("#postResultDiv").html("<strong>Error</strong>");
				}
				console.log(result);
				
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    	
    	// Reset FormData after Posting
    	//resetData();
 
    }
    
    function resetData(){
    	//$("#firstname").val("");
    	//$("#lastname").val("");
    }
})