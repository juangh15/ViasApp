package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Movil(var posicion: Punto, var velocidad: Velocidad, var aceleracion: Double, val va:Double) {
  
  def mover(dt: Double, acelera:Boolean)
  def distanciaFrenado
}
