package com.ViasApp.inmovil

import com.ViasApp.movil.Vehiculo
import scala.collection.mutable.Queue
import com.ViasApp.movimiento.Via
import scala.collection.mutable.ArrayBuffer

class Viaje(_v:Vehiculo,_ruta:Queue[Via]) {
  var viajes = ArrayBuffer[Viaje]()
  
  def v = _v 
  def ruta = _ruta
}