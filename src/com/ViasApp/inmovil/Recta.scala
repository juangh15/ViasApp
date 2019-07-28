package com.ViasApp.inmovil

trait Recta {
  type Origen <: Punto
  type Fin <: Punto
  var _origen: Origen
  var _fin : Fin
}
