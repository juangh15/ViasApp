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
    def angulo = new Angulo ({
    val m=(fin.y - origen.y)/(fin.x-origen.x)
    var res : Double = 0
    if(m<0){
      if(fin.y>origen.y){
        if(fin.x<origen.x) {
          res=Math.tan(m)+Math.toRadians(90)
        } else {
          res=Math.tan(m)-Math.toRadians(90)
        }
      }
      
    }else if(m>0){
      if(fin.y<origen.y){
        if(fin.x<origen.x) {res=Math.tan(m)+Math.toRadians(180)} else {res=Math.tan(m)}
      }
    }
    
    if(fin.y==origen.y){
      if(fin.x>origen.x) {res=Math.toRadians(0)} else {res=Math.toRadians(180)}
    }
    if(fin.x==origen.x){
      if(fin.y>origen.y) {res=Math.toRadians(90)} else {res=Math.toRadians(270)}
    }
    res
    })
  override def toString = s"Via: ${numero} con ${nombre} "
}
