package com.ViasApp.main
import com.ViasApp.movimiento.Interseccion
import com.ViasApp.inmovil.Punto

object Main {
  def main(args: Array[String]): Unit = {
    var inter1 = new Interseccion(1,2)
    var punto1 = new Punto(1,2)
    punto1.x = 155
    println(punto1.x)
    println(inter1.nombre)
  }
  
}