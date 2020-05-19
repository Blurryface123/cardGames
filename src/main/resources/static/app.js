var stompClient = null;
var players = null;
var userID = Math.floor(Math.random() * 10);
var listUserId = [];

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/card-games'); //AQUI SE CONECTA CON EL BACK
    stompClient = Stomp.over(socket);

    //MIENTRAS ESTEMOS CONECTADOS
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        setCookie(userID);
        listUserId.push(userID);
        //console.log(listUserId);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function setCookie(cvalue,cplayerType) {
    var d = new Date();
    d.setTime(d.getTime() + (30 * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = "user" + "=" + cvalue + ";"
                    + "playerType = "+ cplayerType + ";" 
                    + expires + ";"+ "SameSite=Strict;"; //samesite para evitar cross site attacks
  }

function getCookie(name) {
    //var name = "user =";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
         
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }

  function getZar(){
    var listUserId = getCookie()
  }


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendIds(){
    
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

//ensures that your function is called once all the DOM elements of the page are ready to be used.
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});