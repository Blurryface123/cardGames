var posX = null;
var posY = null

function connect() {
  var socket = new SockJS('/card-games'); //AQUI SE CONECTA CON EL BACK
  stompClient = Stomp.over(socket);

  //MIENTRAS ESTEMOS CONECTADOS
  stompClient.connect({}, function (frame) {
    stompClient.subscribe('/topic/conexion', function (positionsValues) {
      var res = JSON.parse(positionsValues.body).x;
      console.log("SUBSCRIBE: "+ res);
      posY = JSON.parse(positionsValues.body).y;
      posX = JSON.parse(positionsValues.body).x;
    });
  });
}

//".draggable div"

$(function () {

  var socket = new SockJS('/card-games'); //AQUI SE CONECTA CON EL BACK
  stompClient = Stomp.over(socket);

  //connect();
  var n = $(".own-hand").length; //TIENE QUE ESTAR DENTRO DE UNA FUNCION
  /*var $newCard = $( `<div class="card own-hand">
                            <img class="card-img" src="./img/carta-virus.jpg" alt="Card image">
                            <div class="card-image-overlay">
                            </div>
                        </div>` );*/
  $(".draggable").draggable({

    drag: function (e, ui) {
      var y = ui.position.top
      var x = ui.position.left
      stompClient.send("/app/prueba", {}, JSON.stringify(
        {
          'y': y,
          'x': x
        }
      ));
      if (posY != null && posX != null) {
        console.log("NO ESTAMOS EN EL NULL")
        ui.position = {
          top: posY,
          left: posX
        };
      } else {
        console.log("TOP: " + posY);
        console.log("LEFT: " + posX);
        ui.position = {
          top: posY,
          left: posX
        };
      }


    }
  });
  $(".containment-wrapper").droppable({
    tolerance: "pointer",
    drop: function () {
      var x = n - 1;
      console.log("There are " + x + " divs.");
      if (x < 3) {
      }
      /*
        $( "div.inner" ).replaceWith( $( $newCard ) ); //no se puede reemplazar la carta automaticamente, hay que ir creado en baraja cuando se hace drop
  */

    }
  })
});
