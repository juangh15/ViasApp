package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Movil(var _posicion: Punto, var _velocidad: Velocidad) {
  
  
  def posicion = _posicion
  def velocidad = _velocidad
  
  def mover(dt: Double)
}
