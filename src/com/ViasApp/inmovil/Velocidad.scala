package com.ViasApp.inmovil

class Velocidad(private var _magnitud: Double, private var _direccion: Angulo) {
  
  def magnitud = _magnitud
  def direccion = _direccion
  
  def magnitud_=(mag: Double): Unit = _magnitud = mag
  def direccion_=(dir: Angulo): Unit = _direccion = dir
}
