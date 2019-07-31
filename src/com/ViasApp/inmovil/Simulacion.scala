package com.ViasApp.main
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._

object Simulacion  extends Runnable {
  
  val grafico = new Grafico
  var t : Int = 0
  
  // lectura variables del json
  val json = new Json
  val dt = json.dt
  val tRefresh = json.tRefresh
  val minVehiculos = json.minVehiculos
  val maxVehiculos = json.maxVehiculos
  val minVelocidad = json.minVelocidad
  val maxVelocidad = json.maxVelocidad
  
  
  
 def run() {
   while (true) {
     vehiculos.foreach(_.mover(dt))
     t += dt
     grafico.graficarVehiculos(vehiculos)
     Thread.sleep(tRefresh)
 }
}
}
