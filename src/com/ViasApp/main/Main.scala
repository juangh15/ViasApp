package com.ViasApp.main
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._

object Main {

  //ruta donde se CARGARAN los parametros de la simulacion (DEBE EXISTIR "parametros.json" PARA EJECUTAR CORRECTAMENTE)
  val rutaParametros = "C:\\Users\\...\\Desktop\\ViasApp-master\\resources\\parametros.json"
  
  //ruta donde se GUARDARAN los parametros de la simulacion
  val rutaResultados = "C:\\Users\\...\\Desktop\\ViasApp-master\\resources\\resultados.json"

  //URL, NOMBRE DE USUARIO Y CONTRASEÑA DE LA BASE DE DATOS EN NEO4J (DEBE ESTAR ACTIVA):
  val url = "bolt://localhost/7687"
  val user = "neo4j" //Usuario por defecto
  val pass = "1234" //Pass de la bd activa

  def main(args: Array[String]): Unit = {
    Simulacion.run()
  }
}
