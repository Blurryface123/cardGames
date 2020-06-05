var userID = Math.floor(Math.random() * 10);
//window.response = false;
function connect() {
  var socket = new SockJS('/card-games'); //AQUI SE CONECTA CON EL BACK
  stompClient = Stomp.over(socket);


  //MIENTRAS ESTEMOS CONECTADOS
  stompClient.connect({}, function (frame) {
    var cardImg = `<img class="card-img" src="./img/carta-virus.jpg" alt="Card image">
    <div class="card-image-overlay"></div>`;
    setCookie(userID);
    stompClient.subscribe('/topic/newCard', function (card) {
      var cardString = JSON.parse(card.body).cardValue;
      var cardId = JSON.parse(card.body).id;

      $(cardString).attr("id", cardId).html(cardImg).appendTo("#deck");
      

    });

    stompClient.subscribe('/topic/conexion', function (positionsValues) {
      var id = JSON.parse(positionsValues.body).id;
      var y = JSON.parse(positionsValues.body).y;
      var x = JSON.parse(positionsValues.body).x;
      console.log("TOP: " + y);
      console.log("LEFT: " + x);
      console.log("subscribe id: " + id);
      $("#" + id).css({
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

  createCard();
  dragCard();
});

function createCard() {
  var button = $("#button");

  button.on("click", function (e) {
    var $cartaDiv = $('<div>', {
      class: "nueva-carta card own-hand",
      id: "card-" + ($(".nueva-carta").length + 1) //aqui el lenght es 0 porque no hay nada creado
    }).css({
      top: "15px",
      left: "15px",
      height: "130px",
      width: "100px",
      position: "absolute"
    });
    //$($cartaDiv).appendTo($this.parent());
    var id = "card-" + ($(".nueva-carta").length + 1);

    console.log($cartaDiv[0].outerHTML);
    console.log("id: " + id);
    stompClient.send("/app/createCard", {}, JSON.stringify(
      {
        'id': "card-" + ($(".nueva-carta").length + 1),
        'cardValue': $cartaDiv[0].outerHTML
      }
    ));
  });
}

function dragCard() {
  //ver https://api.jquery.com/on/ y even delegation luego para evitar tener que hacer dos clicks para mover carta
  $(document).on("mousedown", ".nueva-carta", function (e) {
    console.log("id PARA VER: " + this.id);
    $("#" + this.id).draggable({
      grid: [20, 20],
      drag: function (e, ui) {
        stompClient.send("/app/prueba", {}, JSON.stringify(
          {
            'id': this.id,
            'y': ui.position.top,
            'x': ui.position.left
          }
        ));
      }
    });
  });
}
