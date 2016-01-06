$(document).ready(function() 
{
    $( "#accordion" ).accordion();
	$( ".button").button();
	$("#datepicker").datepicker();
	$(".radio").button();
});

function click1(){
	window.location.href = "ballot1.html"
	}
	
function click2(){
	window.location.href = "ballot2.html"
	}
	
function click3(){
	window.location.href = "ballot3.html"
	}
	
function click4(){
	window.location.href = "ballot4.html"
	}
	
function create(){
	var acc = confirm("Are you sure you want to set this ballot type to this election?");
	if(acc){
		alert("Ballot Added!")
		window.location.href = "electionManager.html"
	}
}

function deleted(){
	var acc = confirm("Are you sure you want to delter this ballot? May cause election to become unavailable");
	if(acc){
		alert("Ballot Removed")
		window.location.href = "electionManager.html"
	}
}

