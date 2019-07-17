package com.ViasApp.inmovil

class Punto(private var _x1: Int,private var _y1: Int) {
  def x = _x1
  def y = _y1
  def x_=(x_nuevo:Int): Unit = _x1 = x_nuevo
  def y_=(y_nuevo:Int): Unit = _y1 = y_nuevo
}