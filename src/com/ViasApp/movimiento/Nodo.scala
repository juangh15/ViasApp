package com.ViasApp.movimiento

import scala.collection.mutable.ArrayBuffer



class Nodo(val posicion: Interseccion,val semaforos: ArrayBuffer[Semaforo], val tiempoAmarillo: Int = 0 , var funcionar:Boolean=true) {
    //Tiempo en amarillo es estandar para todos
  var tiempo:Int=0
  val ta:Int=tiempoAmarillo
  
  val tiempoTotal = {var ñ=0;for(i <- semaforos){ñ=ñ+i.tv.toInt};ñ}+ta*(semaforos.length)
  
  
  //estados iniciales
  for(i <- semaforos){
    i.estado=2
  }
  semaforos(0).estado=0
  
  
  
  def procesoDeCambioSemaforos(dt:Int){
    tiempo+=dt
    if(tiempo>=tiempoTotal){
      tiempo=tiempo-tiempoTotal
    }
    
    var sumatory:Int=semaforos(0).tv.toInt
    var condicion =(tiempo<=sumatory)
    var cont=0
    var cosa=false
    while(!condicion){
      
      if(cosa){
        sumatory+=ta
        cosa=false
        cont+=1
      }else{
        sumatory+=semaforos(cont).tv.toInt
        cosa=true
      }
      condicion=(tiempo<=sumatory)      
    }
    
    if(cosa){
      semaforos(cont).estado=1
    }else{
      semaforos(cont).estado=0
      if(!(cont-1<0)){
        semaforos(cont-1).estado=2
      }else {
        semaforos(semaforos.length-1).estado=2
      }
      
    }
    
      
  }
  
}
