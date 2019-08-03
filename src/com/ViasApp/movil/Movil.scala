package com.ViasApp.movil
import com.ViasApp.inmovil._

abstract class Movil(var _posicion: Punto, var _velocidad: Velocidad) {
  
  
  def posicion = _posicion
  def velocidad = _velocidad
  def posicion_=(posicion_nuevo:Punto): Unit = _posicion = posicion_nuevo
  def velocidad_=(velocidad_nuevo:Velocidad): Unit = _velocidad = velocidad_nuevo
  def mover(dt: Double)
}
