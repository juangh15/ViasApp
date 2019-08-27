package com.ViasApp.movimiento



class Nodo(val id: Int,val semaforos: Array[Option[Semaforo]], var funcionar:Boolean=true ) {
    //Tiempo en amarillo es estandar para todos
  val ta:Double=2//nùmero estandar
  val semaforosExistentes=semaforos.flatten
  
  //hay secuencia.length() -1 semaforos
  
  
  def procesoDeCambio(x:Array[Semaforo], i:Int){
    x(i-1).estado=1
    
    //luego 
    x(i-1).estado=2
    x(i).estado=1
  }
  
  
}