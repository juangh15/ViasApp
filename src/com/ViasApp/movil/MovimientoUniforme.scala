package com.ViasApp.movil

trait MovimientoUniforme extends Movil {
  def mover(dt: Double){
   val dx = velocidad.magnitud * math.cos(velocidad.direccion.valor) * dt
   val dy = velocidad.magnitud * math.sin(velocidad.direccion.valor) * dt
   val mueveX = posicion.x + dx
   val mueveY = posicion.y + dy
   posicion.x_=(mueveX)
   posicion.y_=(mueveY)
  } 
}
