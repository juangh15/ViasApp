package com.ViasApp.inmovil
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._

class ResultadosSimulacion( val  arrayVias : Array[Via], val arrayIntersecciones : Array[Interseccion], val arrayVehiculos: ArrayBuffer[Vehiculo],val  tiempo: Double, val interOrigen: ArrayBuffer[Interseccion], val interDestino: ArrayBuffer[Interseccion], val distancias:ArrayBuffer[Double]){
  
  var aux1 = 0
  var aux2 = 0
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
  var vias = viasUnSentido + viasDobleSentido //Cantidad Vias
  
  var longitudpromedio = (arrayVias.map(_.longitud).sum)/arrayVias.size //Longitud promedio de las vias

  var velmaxvias = arrayVias.map(_.velocidad).max //Velocidad Maxima de las vias
  
  var velminvias = arrayVias.map(_.velocidad).min //Velocidad Minima de las vias
  
  var promedioOrigen = interOrigen.size/interOrigen.distinct.size  //Promedio de vehiculos por nodo al inicio
  
  var promedioDestino = interDestino.size/interDestino.distinct.size  //Promedio de vehiculos por nodo al final
  
  var sinOrigen = arrayIntersecciones.size - interOrigen.distinct.size  //Nodos sin vehiculos al iniciar
  
  var sinDestino = arrayIntersecciones.size - interDestino.distinct.size //Nodos sin vehiculos al finalizar
  
  var velmaxveh = arrayVehiculos.map(_.velocidadAuto).max //Velocidad máxima de los vehiculos
  
  var velminveh = arrayVehiculos.map(_.velocidadAuto).min //Velocidad minima de los vehiculos
  
  var velpromveh = arrayVehiculos.map(_.velocidadAuto).sum/arrayVehiculos.size  //Velocidad promedio de los vehiculos
  
  var distanciaProm = distancias.sum/arrayVehiculos.size
  
  var distanciaMax = distancias.max
  
  var distanciaMin = distancias.min
  
