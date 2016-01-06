$(document).ready(function() {
	$( ".button" ).button();
});

function save(){
	var answer = confirm("Are you sure you want change your settings?")
		if(answer){
			alert("Seetings Changed")
			window.location.href = "student.html"
		}
}
