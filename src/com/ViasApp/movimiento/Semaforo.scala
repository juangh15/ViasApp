package com.ViasApp.movimiento

import com.ViasApp.inmovil.Velocidad


//viaAsociada no sè si guardarla acà
class Semaforo(val id:Long,var estado:Int, val tv:Double) {
  
 // var nodo : Nodo
  
  //Estado 0:Verde
  //Estado 1:Amarillo
  //estado 2:Rojo
 
  //tv si es variable

  //verdadero solo mientras creo esa vaina
  def sePuedePasar(v:Double, a:Double, d:Double = 0.0): Boolean = {
    var t:Int=0
    if(a==0){
      t=(d/v).toInt
    }else{
      t=((-v+Math.sqrt(v*v+2*a*d))/a).toInt
    }
   // (nodo.tiempoSemaforo(id)>= t)
    true
  }
}
