package com.ViasApp.movimiento
import com.ViasApp.inmovil.Punto

case class Interseccion(private var _x: Int, private var _y: Int, val nombre: String = "SinNombre") extends Punto(_x,_y){
  
  override def toString = s"Inters: ${nombre}"
}
