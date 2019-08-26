package com.ViasApp.movimiento
import com.ViasApp.inmovil.Punto

case class Interseccion(private var _x: Double, private var _y: Double, val  nombre: Option[String]=None) extends Punto(_x,_y){
  
  override def toString = s"Inters: ${nombre}"
}
