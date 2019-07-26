package com.ViasApp.movimiento

class TipoVia (nombre: String) 

object TipoVia{
  def apply(nombre: String): TipoVia ={
    new TipoVia(nombre)
  }
}
