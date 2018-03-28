var servletName = "HackGameWeb";

function initGame() {
	var xhttp;
 	xhttp=new XMLHttpRequest();
 	xhttp.onreadystatechange = function() {
 	    if (xhttp.readyState == 4 && xhttp.status == 200) {
 	       //populateDataFormat(xhttp.responseText);
 	    }
 	}
 	xhttp.open("POST", "/"+servletName+"/getInitGameState", true);
 	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 	xhttp.send();
}