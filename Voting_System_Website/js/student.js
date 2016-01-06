$(document).ready(function() {
    $( "#menu" ).menu();
	$( "#settings" ).button();
	$( "#electionManager" ).button();

	var user = localStorage.getItem("user");
	if(user == 0){
		$("#electionManager").hide();
	}
	
});

function settingsClick(){
	window.location.href = "settings.html";
}

function emClick(){
	window.location.href = "electionManager.html";
}