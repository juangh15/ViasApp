package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Vehiculo(val placa: String,var pos: Punto,var vel: Velocidad,val velocidadAuto:Double, val ac: Int) extends Movil(pos, vel,ac,velocidadAuto) with MovimientoUniformementeAcelerado {
  override def toString = s"${this.getClass}(placa=${placa}, posicion=($posicion))"
}
