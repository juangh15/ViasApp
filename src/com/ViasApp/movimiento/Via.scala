package com.ViasApp.movimiento
import com.ViasApp.inmovil.Recta
import com.ViasApp.inmovil.Angulo

class Via(var or: Interseccion, var fn: Interseccion, val velocidad: Int, val tipovia: TipoVia, val sentido: Sentido, val numero: String, val nombre: Option[String] = None, val camara: Option[CamaraFotoDeteccion] = None) extends Recta {
  type Origen = Interseccion
  type Fin = Interseccion
  var _origen: Origen = or
  var _fin: Fin = fn
  val longitud = math.sqrt(math.pow(origen.x - fin.x, 2) + math.pow(origen.y - fin.y, 2)).toInt
  val angulo = {
    var angulo = Math.atan2(fin.y - origen.y,fin.x-origen.x)*180/Math.PI
    if(angulo<0){
      angulo += 360
    }
     new Angulo(angulo)
  }
  def origen = _origen  //getters
  def fin = _fin
  

    
  override def toString = s"Via: ${numero} con ${nombre} "
}
