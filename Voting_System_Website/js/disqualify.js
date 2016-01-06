$(document).ready(function() 
{
    $( "#accordion" ).accordion();
	$( ".button").button();
});

function CheckAll(chk)
{
	for (i = 0; i < chk.length; i++)
		chk[i].checked = true ;
}

function UnCheckAll(chk)
{
	for (i = 0; i < chk.length; i++)
		chk[i].checked = false ;
}

function disqaulify()
{
	var answer = confirm("Are you sure you want to disqualify these votes? (This may result in a change in the results of the election)");
		if(answer){
			alert("votes have been disqualified!")
			window.location.href = "electionsAdmin.html"
		}
}