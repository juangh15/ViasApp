package com.ViasApp.movil
import com.ViasApp.inmovil._

class Camion(val pla: String="SinPlacas", var po: Punto = new Punto(0,0), var ve: Velocidad = new Velocidad(1, new Angulo(0)),val va:Double , val ac=Double) extends Vehiculo(pla, po, ve,va, ac){
  
}
