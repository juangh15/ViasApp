package com.ViasApp.movimiento

import scala.collection.mutable.ArrayBuffer



class Nodo(val posicion: Interseccion,val semaforos: ArrayBuffer[Semaforo],tiempoAmarillo: Int = 0 , var funcionar:Boolean=true) {
    //Tiempo en amarillo es estandar para todos
  var tiempo:Int=0
  val ta:Int=tiempoAmarillo
  
  val tiempoTotal = {var ñ=0;for(i <- semaforos){ñ=ñ+i.tv.toInt};ñ}+ta*(semaforos.length)
  
  val tiempoSemaforo=(g:Long)=>{//tiempo para que se ponga en verde del semaforo con id g
    var cont:Int=0;
    var h:Int ={for (i <- 0 to semaforos.size-1)
          if (semaforos.apply(i).id==g){cont = i}
          cont
            }
//      while(semaforos(cont).id!=g){
//        ñ=ñ+semaforos(cont).tv.toInt
//        cont+=1
//      }
    val ti = h+ta*(cont)
    var tf:Int=0
    if(tiempo<=ti) tf=ti-tiempo
    else tf=tiempoTotal-tiempo+ti
    tf
  }
  def sePuedePasar(v:Double, a:Double, d:Double = 0.0, id:Long): Boolean = {
    var t:Int=0
    if(a==0){
      t=(d/v).toInt
    }else{
      t=((-v+Math.sqrt(v*v+2*a*d))/a).toInt
    }
   (tiempoSemaforo(id)>= t)
    
  }
    
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
      
      if(cosa){//si el semaforo en la posicion cont va para amarillo
        sumatory+=ta//aqui le suma el tiempo amarillo
        cosa=false//aqui quiere decir que va para verde el semaforo en la posicion cont+1
        cont+=1//semaforo que sigue
      }else{//si los semaforos están en verde
        sumatory+=semaforos(cont).tv.toInt
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
}
