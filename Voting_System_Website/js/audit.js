// JavaScript Document
$(document).ready(function() {
	$("#button").button();
	
});


function buttonClick(){
	var acc = confirm("Are you sure you want to reccount the votes for this election?");
	if(acc){
		alert("election will be reccounted")
		window.location.href = "electionsAdmin.html";
	}
	
}