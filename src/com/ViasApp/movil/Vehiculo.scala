package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Vehiculo(val placa: String,var pos: Punto,var vel: Velocidad) extends Movil(pos, vel) with MovimientoUniforme {
  override def toString = s"${this.getClass}(placa=${placa}, posicion=($posicion))"
}
