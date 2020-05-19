var countDrops = 0

$(function () {
  $(".draggable div").draggable({
    containment: ".containment-wrapper"
    , stack: ".draggable div"
  });
});

$(function () {
  var n = $(".own-hand").length; //TIENE QUE ESTAR DENTRO DE UNA FUNCION
  /*var $newCard = $( `<div class="card own-hand">
                            <img class="card-img" src="./img/carta-virus.jpg" alt="Card image">
                            <div class="card-image-overlay">
                            </div>
                        </div>` );*/
  $(".droppable").droppable({
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
