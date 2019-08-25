package com.ViasApp.main
import com.ViasApp.movimiento._
import scala.collection.mutable.Queue
import com.ViasApp.inmovil._
import com.ViasApp.movil._
import com.ViasApp.JsonClasses._
import scala.io.StdIn.readLine

object Main {
  
  //ruta donde se CARGARAN los parametros de la simulacion (DEBE EXISTIR "parametros.json" PARA EJECUTAR CORRECTAMENTE)
  val rutaParametros = "C:\\Users\\....\\Desktop\\ViasApp-master\\resources\\parametros.json"
  //ruta donde se GUARDARAN los parametros de la simulacion
  val rutaResultados = "C:\\Users\\....\\Desktop\\ViasApp-master\\resources\\resultados.json"
  
  def main(args: Array[String]): Unit = {
    Simulacion.run()
  }
}
