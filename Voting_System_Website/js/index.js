$(document).ready(function() {
    $( "#accordion" ).accordion();
	$( "#button" ).button();
});

function buttonClick(){
	var active = $("#accordion h3").index($("#accordion h3.ui-state-active")); 
	localStorage.setItem("user",active);
	if(active == 2){
		window.location.href = "electionsAdmin.html";
	}else{
		window.location.href = "student.html";
	}
}