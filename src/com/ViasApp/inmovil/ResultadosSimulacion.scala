package com.ViasApp.inmovil
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._

class ResultadosSimulacion(val totalVehiculos : Int, val totalCarros : Int, val totalMotos : Int, val totalBuses : Int, val totalCamiones : Int, val totalMototaxis : Int,
  val  arrayVias : Array[Via], val arrayIntersecciones : Array[Interseccion], val arrayVehiculos: Array[Vehiculo],val  tiempo: Double, val interOrigen: ArrayBuffer[Interseccion], val interDestino: ArrayBuffer[Interseccion]){
  
  var aux1 = 0
  var aux2 = 0
  var aux3 = 0
  var aux4 = 0
  var aux5 = 0
  var aux6 = 0
  var intersecciones = arrayIntersecciones.size
  var viasUnSentido = {
    for( x <- arrayVias ){
      if(x.sentido.nombre == "unaVia"){
        aux1+=1
      }
    }
    aux1
  }
  var viasDobleSentido = {
    for( x <- arrayVias ){
      if(x.sentido.nombre == "dobleVia"){
        aux2+=1
      }
    }
    aux2/2
  }
  var vias = viasUnSentido + viasDobleSentido
  
  var longitudpromedio = (arrayVias.map(_.longitud).sum)/arrayVias.size

  var velmaxvias = arrayVias.map(_.velocidad).max
  
  var velminvias = arrayVias.map(_.velocidad).min
  
  var promedioOrigen = interOrigen.size/interOrigen.distinct.size
  
  var promedioDestino = interDestino.size/interDestino.distinct.size
  
  var sinOrigen = arrayIntersecciones.size - interOrigen.distinct.size
  
  var sinDestino = arrayIntersecciones.size - interDestino.distinct.size
  
  var velmaxveh = arrayVehiculos.map(_.velocidad.magnitud).max //Velocidad máxima de los vehiculos
  
  var velminveh = arrayVehiculos.map(_.velocidad.magnitud).min //Velocidad minima de los vehiculos
  
  var velpromveh = arrayVehiculos.map(_.velocidad.magnitud).sum/arrayVehiculos.size  //Velocidad promedio de los vehiculos
  
  
}
