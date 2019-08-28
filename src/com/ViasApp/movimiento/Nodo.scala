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
    var sumatory:Int=semaforos(0).tv
    var condicion =(tiempo<=sumatory)
    var cont=0
    var cosa=false
    while(!condicion){
      
      if(cosa){//si el semaforo en la posicion cont va para amarillo
        sumatory+=ta//aqui le suma el tiempo amarillo
        cosa=false//aqui quiere decir que va para verde el semaforo en la posicion cont+1
        cont+=1//semaforo que sigue
      }else{//si los semaforos están en verde
        sumatory+=semaforos(cont).tv
        cosa=true//esto quiere decir que sigue para amarillo
      }
      condicion=(tiempo<=sumatory)
    }
    
    if(cosa){//si está en amarillo
      semaforos(cont).estado=1
    }else{//si está en verde
      semaforos(cont).estado=0//cambia el estado del semaforo en la posicion cont a 0
      //aqui cambia el semaforo anterior
      if(!(cont-1<0)){//esto verifica el cambio del anterior
        semaforos(cont-1).estado=2
      }else {
        semaforos(semaforos.length-1).estado=2
      }
      
    }
  
}
