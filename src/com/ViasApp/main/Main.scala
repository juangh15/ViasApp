package com.ViasApp.main

import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._


object Main {
  def main(args: Array[String]): Unit = {
    var inter1 = new Interseccion(1,2)
    var punto1 = new Punto(1,2)
    punto1.x = 155
    println(punto1.x)
    println(inter1.nombre)
    
    val bus1 = new Bus()
    println(bus1)
    
    
  }
      
}
