$(document).ready(function() {
	$( ".button" ).button();	
});

function submit(thing){
	var text = document.getElementsByName('electionName')[0].value
	var text2 = document.getElementsByName('electionCommissioner')[0].value
	if(text2=="Dakota Kirby"){
		var ans = confirm("Do you wish to make " + text + " a new election and "+text2+" the election commisoner?")
		if(ans){
			alert("Created!")
			window.location.href = "electionsAdmin.html"
		}
	}else{
		alert("Invalid Election Commisioner! " + text + " is not in system!")
	}
}
