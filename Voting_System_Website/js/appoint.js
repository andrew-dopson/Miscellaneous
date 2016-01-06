$(document).ready(function() 
{
	$( ".button").button();
});

function submit(thing){
	var text = document.getElementsByName('text')[0].value
	if(text == "Dakota Kirby"){
		var ans = confirm("Do you wish to make " + text + " the new election commisioner?")
		if(ans){
			alert("Election Commisoner Added!")
			window.location.href = "electionsAdmin.html"
		}
	}
	else{
		alert("Invalid Election Commisioner! " + text + " is not in system!")
	}
}