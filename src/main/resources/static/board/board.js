var userID = Math.floor(Math.random() * 10);
function connect() {
  var socket = new SockJS('/card-games'); //AQUI SE CONECTA CON EL BACK
  stompClient = Stomp.over(socket);


  //MIENTRAS ESTEMOS CONECTADOS
  stompClient.connect({}, function (frame) {
    var newItem = `<div class="nueva-carta own-hand"><img class="card-img" src="./img/carta-virus.jpg" alt="Card image">
    <div class="card-image-overlay"></div></div>`;
    setCookie(userID);
    stompClient.subscribe('/topic/newCard', function (card) {
      var cardString = JSON.parse(card.body).cardValue;
      var cardId = JSON.parse(card.body).id;

      $(cardString).attr("id", cardId).appendTo("#deck");
    });
    stompClient.subscribe('/topic/conexion', function (positionsValues) {
      var id = JSON.parse(positionsValues.body).id;
      var y = JSON.parse(positionsValues.body).y;
      var x = JSON.parse(positionsValues.body).x;
      console.log("TOP: " + y);
      console.log("LEFT: " + x);
      $("#"+id).css({
        top: y + "px",
        left: x + "px"
      });
    });
  })
}

//".draggable div"
function setCookie(cvalue) {
  var d = new Date();
  d.setTime(d.getTime() + (30 * 24 * 60 * 60 * 1000));
  var expires = "expires=" + d.toUTCString();
  document.cookie = "user" + "=" + cvalue + ";"
    + expires + ";" + "SameSite=Strict;"; //samesite para evitar cross site attacks
}

function getCookie(name) {
  //var name = "user =";
  var ca = document.cookie.split(';');
  for (var i = 0; i < ca.length; i++) {
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

$(function () {
  connect();
  var n = $("#player-self").children().length; //TIENE QUE ESTAR DENTRO DE UNA FUNCION
  helperTop = null;
  helperLeft = null;
  var style = "style='top: " + helperTop + "px; left: " + helperLeft + "px; height:130px;width: 100px;'";

  var button = $("#button");

  button.on("click", function (e) {
    var $this = $(this);
    var $cartaDiv = $('<div>', {
      class: "nueva-carta card own-hand",
      id: ($(".nueva-carta").length + 1) //aqui el lenght es 0 porque no hay nada creado
    }).css({
      top: "15px",
      left: "15px",
      height: "130px",
      width: "100px",
      position: "absolute"
    });
    //$($cartaDiv).appendTo($this.parent());
    var id = ($(".nueva-carta").length +1);

    console.log($cartaDiv[0].outerHTML);
    console.log("id: " + id);
    console.log("string div: " + $("#carta-" + ($(".nueva-carta").length + 1)).outerHTML)
    stompClient.send("/app/createCard", {}, JSON.stringify(
      {
        'id': ($(".nueva-carta").length+1),
        'cardValue': $cartaDiv[0].outerHTML
      }
    ));
  });

  //ver https://api.jquery.com/on/ y even delegation luego para evitar tener que hacer dos clicks para mover carta
  $("#deck").on("mousedown",".nueva-carta", function (e) {
    $(".nueva-carta").draggable({
      grid: [20, 20],
      drag: function (e, ui) {
        console.log("CARTA NUEVA"+($(".nueva-carta").length));
        stompClient.send("/app/prueba", {}, JSON.stringify(
          {
            'id': ($(".nueva-carta").length),
            'y': ui.position.top,
            'x': ui.position.left
          }
        ));
      }
    });
  })

});
