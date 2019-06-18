var servletName = "HackGameWeb";
var interval1;
var line1 = null;
var line2 = null;
var player1name;
var player2name;

window.onload = function() {
    document.getElementById("player1line").style.visibility = "hidden";
    document.getElementById("player2line").style.visibility = "hidden";
    clearGameState();
};
function initGame() {
	var xhttp;
 	xhttp=new XMLHttpRequest();
 	xhttp.onreadystatechange = function() {
 	    if (xhttp.readyState == 4 && xhttp.status == 200) {
 	    	var obj = JSON.parse(xhttp.responseText);
 	    	var player1x = obj.player1x;
 	    	var player1y = obj.player1y;
 	    	var player2x = obj.player2x;
 	    	var player2y = obj.player2y;
			var trapList = obj.trapList;
 	    	player1name = obj.player1name;
 	    	player2name = obj.player2name;
 	    	document.getElementById("player1name").innerHTML = player1name;
 	    	document.getElementById("player2name").innerHTML = player2name;
 	    	console.log("INIT GAME COORD "+player1x+" "+player1y+" , "+player2x+" "+player2y);
			initRender(player1x, player1y, player2x, player2y, trapList);
 	    }
 	}
 	//xhttp.open("POST", "/"+servletName+"/game/getInitGameState", true);
 	 	xhttp.open("POST", "/"+"game/getInitGameState", true);

 	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 	xhttp.send();
}

function initRender(player1x, player1y, player2x, player2y, trapList) {
    var player1LineElem = document.getElementById("player1line");
    var player2LineElem = document.getElementById("player2line");
    var player1xcoord = 80 + (60 * player1y);
    var player1ycoord = 80 + (60 * player1x);
    player1LineElem.setAttribute("x1", player1xcoord);
    player1LineElem.setAttribute("y1", player1ycoord);
    player1LineElem.setAttribute("x2", player1xcoord + 3);
    player1LineElem.setAttribute("y2", player1ycoord);
    
    var player2xcoord = 80 + (60 * player2y);
    var player2ycoord = 80 + (60 * player2x);
    player2LineElem.setAttribute("x1", player2xcoord);
    player2LineElem.setAttribute("y1", player2ycoord);
    player2LineElem.setAttribute("x2", player2xcoord + 3);
    player2LineElem.setAttribute("y2", player2ycoord);
	for (var i=0; i<trapList.length; i++)
	{
		var trapIterElement = "rect";
		for (var j=0; j<trapList[i].length; j++)
		{
			trapIterElement += trapList[i][j];
		}
		var renderedTrap = document.getElementById(trapIterElement);
		renderedTrap.style.fill = "black";
	}


	document.getElementById("player1line").style.visibility = "visible";
    document.getElementById("player2line").style.visibility = "visible";
}

function startGame() {
	var xhttp;
 	xhttp=new XMLHttpRequest();
 	xhttp.onreadystatechange = function() {
 	    if (xhttp.readyState == 4 && xhttp.status == 200) {
 	    	
 	    }
 	}
 	//xhttp.open("POST", "/"+servletName+"/game/startGame", true);
 	 	xhttp.open("POST", "/"+"game/startGame", true);

 	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 	xhttp.send();
 	interval1 = window.setInterval(function() {
 		getCurrentState();
 		
 	}, 500);
}

function getCurrentState() {
	var xhttp;
 	xhttp=new XMLHttpRequest();
 	xhttp.onreadystatechange = function() {
 	    if (xhttp.readyState == 4 && xhttp.status == 200) {
 	    	console.log("Get State " + xhttp.responseText);
 	    	var obj = JSON.parse(xhttp.responseText);
 	    	var isMoveOver = obj.isMoveOver;
 	    	if (isMoveOver) {
 	    		render(obj.player1currentmove, obj.player2currentmove);
                document.getElementById("roundnumber").innerHTML = obj.roundNumber;
 	    		var isGameOver = obj.isGameOver;
 	    		if (isGameOver) {
 	    			clearInterval(interval1);
 	    			var winner = obj.winner;
 	    			window.setTimeout(function() {
 	    			    declareWinner(winner);
 	    			}, 2000);
 	    		}
 	    	}
 	    }
 	}
 	//xhttp.open("POST", "/"+servletName+"/game/getCurrentGameState", true);
 	 	xhttp.open("POST", "/"+"game/getCurrentGameState", true);

 	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 	xhttp.send();
}

function declareWinner(winner) {
    if (winner == 1) {
	  alert(player1name + " wins the match");
	} else if (winner == 2) {
	  alert(player2name + " wins the match");
	} else {
	  alert("This match is a draw");
	}
}

function render(player1move, player2move) {
	if (line1 == null) {
	    line1 = document.getElementById('player1line');
	}
	if (line2 == null) {
	    line2 = document.getElementById('player2line');
	}
	
	var svg = document.getElementsByTagName('svg')[0];
	var newLine1 = document.createElementNS("http://www.w3.org/2000/svg", 'line');
	newLine1.setAttribute("x1", line1.getAttribute('x2'));
	newLine1.setAttribute("y1", line1.getAttribute('y2'));
	newLine1.setAttribute("x2", line1.getAttribute('x2'));
	newLine1.setAttribute("y2", line1.getAttribute('y2'));
	newLine1.setAttribute("stroke-width", "5");
	newLine1.setAttribute("stroke", "red");
	svg.appendChild(newLine1);
	
	var newLine2 = document.createElementNS("http://www.w3.org/2000/svg", 'line');
	newLine2.setAttribute("x1", line2.getAttribute('x2'));
	newLine2.setAttribute("y1", line2.getAttribute('y2'));
	newLine2.setAttribute("x2", line2.getAttribute('x2'));
	newLine2.setAttribute("y2", line2.getAttribute('y2'));
	newLine2.setAttribute("stroke-width", "5");
	newLine2.setAttribute("stroke", "blue");
	svg.appendChild(newLine2);
	
	var count = 0;
	var interval = window.setInterval(function() {
		if (player1move.indexOf("UP") != -1) {
			newLine1.setAttribute('y2', newLine1.getAttribute('y2') - 1);
		} else if (player1move.indexOf("DOWN") != -1) {
			newLine1.setAttribute('y2', 1 + +newLine1.getAttribute('y2'));
		} else if (player1move.indexOf("LEFT") != -1) {
			newLine1.setAttribute('x2', newLine1.getAttribute('x2') - 1);
		} else if (player1move.indexOf("RIGHT") != -1) {
			newLine1.setAttribute('x2', 1 + +newLine1.getAttribute('x2'));
		}
		
		if (player2move.indexOf("UP") != -1) {
			newLine2.setAttribute('y2', newLine2.getAttribute('y2') - 1);
		} else if (player2move.indexOf("DOWN") != -1) {
			newLine2.setAttribute('y2', 1 + +newLine2.getAttribute('y2'));
		} else if (player2move.indexOf("LEFT") != -1) {
			newLine2.setAttribute('x2', newLine2.getAttribute('x2') - 1);
		} else if (player2move.indexOf("RIGHT") != -1) {
			newLine2.setAttribute('x2', 1 + +newLine2.getAttribute('x2'));
		}
	    if (count++ > 59)
	        window.clearInterval(interval);
	}, 20);
	line1 = newLine1;
	line2 = newLine2;
}

function clearGameState() {
	var xhttp;
 	xhttp=new XMLHttpRequest();
 	xhttp.onreadystatechange = function() {
 	    if (xhttp.readyState == 4 && xhttp.status == 200) {
 	    }
 	}
 //	xhttp.open("POST", "/"+servletName+"/game/clearGameState", true);
  	xhttp.open("POST", "/"+"game/clearGameState", true);
 	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
 	xhttp.send();
}

function resetGame() {
	window.location.reload();
}
