package com.ViasApp.inmovil

class Punto(private var _x1: Double,private var _y1: Double) {
  def x = _x1
  def y = _y1
  def x_=(x_nuevo:Double): Unit = _x1 = x_nuevo
  def y_=(y_nuevo:Double): Unit = _y1 = y_nuevo
  override def toString = s"(${x}, ${y})"
}
