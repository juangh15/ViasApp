package com.ViasApp.inmovil
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._

class ResultadosSimulacion(totalVehiculos : Int, totalCarros : Int, totalMotos : Int, totalBuses : Int,totalCamiones : Int, totalMototaxis : Int,
    vias : Array[Via], intersecciones : Array[Interseccion], vehiculos: Array[Vehiculo], tiempo: Double){
  
  val tipoVias = vias.map(_.tipovia)
  var conteo1 = 0
  val unaVia = {
    for(x <- vias){
      if(x.nombre == "unaVia"){
        conteo1 = conteo1 + 1
      }
    }
  }
  var conteo2 = 0
  val dobleVia = {
    for(x <- vias){
      if(x.nombre == "dobleVia"){
        conteo2 = conteo2 + 1
      }
    }
  }
  
}
