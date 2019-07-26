package com.ViasApp.movimiento

class Sentido private(val nombre: String) {
  
}

object Sentido{
  def dobleVia(): Sentido ={
    new Sentido("dobleVia")
  }
  def unaVia(): Sentido ={
    new Sentido("unaVia")
  }
}
