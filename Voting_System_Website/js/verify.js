$(document).ready(function() 
{
    $( "#accordion" ).accordion();
	$( ".button").button();
});

function verify()
{
	var answer = confirm("Are you sure you want verify this election? Results will be finalized!")
		if(answer){
			alert("Verified")
			window.location.href = "electionsAdmin.html"
		}
}

function leave()
{
	window.location.href = "electionsAdmin.html"		
}