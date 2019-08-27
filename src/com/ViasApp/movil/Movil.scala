package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Movil(var posicion: Punto, var velocidad: Velocidad, val velocidadAuto:Double, var aceleracion: Int) {
  
  def mover(dt: Double, acelera: Int)
  def distanciaFrenado:Double
}
