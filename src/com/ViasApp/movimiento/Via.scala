package com.ViasApp.movimiento
import com.ViasApp.inmovil.Recta

class Via(var or: Interseccion, var fn: Interseccion, val velocidad: Int, val tipovia: TipoVia, val sentido: Sentido, val numero: String, val nombre: String) extends Recta {
  type Origen = Interseccion
  type Fin = Interseccion
  var _origen: Origen = or
  var _fin: Fin = fn
  val longitud = math.sqrt(math.pow(origen.x - fin.x, 2) + math.pow(origen.y - fin.y, 2)).toInt
  
  def origen = _origen  //getters
  def fin = _fin
  
  override def toString = s"Via: ${numero} con ${nombre} "
}
