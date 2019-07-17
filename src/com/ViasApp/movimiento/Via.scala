package com.ViasApp.movimiento
import com.ViasApp.inmovil.Recta

class Via(var or: Interseccion, var fn: Interseccion) extends Recta {
  type Origen = Interseccion
  type Fin = Interseccion
  var origen: Origen = or
  var fin: Fin = fn
}