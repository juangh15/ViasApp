package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Movil(var posicion: Punto, var velocidad: Velocidad, var aceleracion: Double) {
  
  def mover(dt: Double)
}
