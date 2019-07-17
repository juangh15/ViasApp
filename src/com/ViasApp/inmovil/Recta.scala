package com.ViasApp.inmovil

trait Recta {
  type Origen <: Punto
  type Fin <: Punto
  var origen: Origen
  var fin : Fin
}