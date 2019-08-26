package com.ViasApp.movimiento
import com.ViasApp.movil.Vehiculo

class CamaraFotoDeteccion(val distancia: Double) {
  def crearComparendo(vehiculo: Vehiculo, via: Via): Comparendo = {
    val comparendo = new Comparendo(vehiculo, vehiculo.velocidad.magnitud, via.velocidad)
    return comparendo
  }
}