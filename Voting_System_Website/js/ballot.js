$(document).ready(function() 
{
    $( "#accordion" ).accordion();
	$( ".button").button();
	$("#datepicker").datepicker();
	$(".radio").button();
});

function back(){
	window.location.href = "ballotManager.html"
	}
	
function party1(thing){
	 $("#radio1").trigger("click");
	 $("#radio5").trigger("click");
	}
	
function party2(thing){
	 $("#radio2").trigger("click");
	 $("#radio6").trigger("click");
	}

