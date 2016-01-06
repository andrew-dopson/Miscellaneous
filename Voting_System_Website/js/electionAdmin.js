$(document).ready(function() 
{
    $( "#accordion" ).accordion();
	$( ".button").button();
});

function buttonClick(theLink){	
	if(theLink.id == "1")
	{
		window.location.href = "disqualify1.html";
	}
	if(theLink.id == "2")
	{
		window.location.href = "disqualify2.html";
	}
	if(theLink.id == "3")
	{
		window.location.href = "disqualify3.html";
	}	
}

function buttonClick2(theLink){	
	if(theLink.id == "1")
	{
		window.location.href = "verify1.html";
	}
	if(theLink.id == "2")
	{
		window.location.href = "verify2.html";
	}
	if(theLink.id == "3")
	{
		window.location.href = "verify3.html";
	}	
}

function buttonClick3(theLink){	
	if(theLink.id == "1")
	{
		window.location.href = "auditballot.html";
	}
	if(theLink.id == "2")
	{
		window.location.href = "auditballot2.html";
	}
	if(theLink.id == "3")
	{
		window.location.href = "auditballot3.html";
	}	
}

function buttonClick4(theLink){	
	if(theLink.id == "1")
	{
		window.location.href = "appoint1.html";
	}
	if(theLink.id == "2")
	{
		window.location.href = "appoint2.html";
	}
	if(theLink.id == "3")
	{
		window.location.href = "appoint3.html";
	}	
}

function buttonClick5(theLink){	
	if(theLink.id == "1")
	{
		window.location.href = "statSumSga.html";
	}
	if(theLink.id == "2")
	{
		window.location.href = "stats.html";
	}
	if(theLink.id == "3")
	{
		window.location.href = "statSum.html";
	}	
}
function buttonClick6(theLink){	
	window.location.href = "createElection.html";
}