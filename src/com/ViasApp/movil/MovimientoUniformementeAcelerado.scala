package com.ViasApp.movil

trait MovimientoUniformementeAcelerado extends Movil {
  def mover(dt: Double,acelera:Int){
   //La velocidad y la magnitud deben de estar en las mismas unidades
   //Si aceleracion = 0 MU
   //Si aceleracion < 0 MUA desacelerado
   //Si aceleracion > 0 MUA desacelerado
    
   //Quitar esto cuando se arregle la funcion en simulacion y los cambios de aceleracion
   var a=aceleracion
   if(acelera==(-1)){
   }else if (acelera==0){
     a=0
   }
   val dx = (velocidad.magnitud + a) * math.cos(velocidad.direccion.valor*Math.PI/180) * dt
   val dy = (velocidad.magnitud + a) * math.sin(velocidad.direccion.valor*Math.PI/180) * dt
   val mueveX = posicion.x + dx
   val mueveY = posicion.y + dy
   velocidad.magnitud = velocidad.magnitud + aceleracion 
   posicion.x_=(mueveX)
   posicion.y_=(mueveY)
  } 
  //con aceleracion > 0
  def distanciaFrenado = (velocidad.magnitud * velocidad.magnitud / (2.0 * aceleracion))
  
}
