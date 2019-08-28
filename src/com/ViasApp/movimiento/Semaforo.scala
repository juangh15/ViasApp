package com.ViasApp.movimiento

import com.ViasApp.inmovil.Velocidad


//viaAsociada no sè si guardarla acà
class Semaforo(val id:Long,var estado:Int, val tv:Double) {
  //var nodo : Nodo
  //Estado 0:Verde
  //Estado 1:Amarillo
  //estado 2:Rojo
  

  
  //tv si es variable

  
  
  //verdadero solo mientras creo esa vaina
  def sePuedePasar(v:Double):Boolean=true
}
