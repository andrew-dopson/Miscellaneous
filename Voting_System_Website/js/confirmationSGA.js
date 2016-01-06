$(document).ready(function() 
{
    $( "#accordion" ).accordion();
	$( ".button").button();
	$("#datepicker").datepicker();
	$(".radio").button();
});

function back(){
	var answer = confirm("Are you sure of your vote. All votes are final!")
		if(answer){
			alert("Vote Confirmed")
			window.location.href = "student.html"
		}
	}
	
function party1(thing){
	 $("#radio1").trigger("click");
	 $("#radio3").trigger("click");
	 $("#radio5").trigger("click");
	 $("#radio7").trigger("click");
	 $("#radio9").trigger("click");
	}
	
function party2(thing){
	 $("#radio2").trigger("click");
	 $("#radio4").trigger("click");
	 $("#radio6").trigger("click");
	 $("#radio8").trigger("click");
	 $("#radio10").trigger("click");
	}

