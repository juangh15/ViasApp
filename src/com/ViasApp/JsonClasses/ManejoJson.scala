package com.ViasApp.JsonClasses

import net.liftweb.json._
import java.io._

object ManejoJson {
  implicit val formats = DefaultFormats
  //Es necesario actualizar la siguiente ruta si deseas ejecutar
  var direccion = "C:\\Users\\prueba\\Desktop\\ViasApp-master\\src\\com\\ViasApp\\parametros.json" 
  
  case class Vehiculos(minimo: Int, maximo: Int)
  case class Velocidad(minimo: Int, maximo: Int)
  case class Proporciones(carros: Double,
                          motos: Double,
                          buses: Double,
                          camiones: Double,
                          motoTaxis: Double)
  case class Semaforos(minTiempoVerde:Int, maxTiempoVerde:Int, tiempoAmarillo:Int)
  case class DistanciasFrenadoVehiculos(XSemaforoFrenar:Int,XSemaforoAmarilloContinuar:Int)
  case class ParametrosSimulacion(dt: Int,
                                  tRefresh: Int,
                                  vehiculos: Vehiculos,
                                  velocidad: Velocidad,
                                  proporciones: Proporciones,
                                  semaforos: Semaforos,
                                  distanciasFrenadoVehiculos:DistanciasFrenadoVehiculos)
  //Lectura de los parametros desde el archivo Json
  val contenidoArchivo = scala.io.Source.fromFile(direccion).getLines.mkString
  val contenidopreParsed = net.liftweb.json.parse(contenidoArchivo)
  val contenido = (contenidopreParsed \\ "pametrosSimulacion" ).children
  val parametros = contenido (0).extract[ParametrosSimulacion]
  
  def escribirArchivo(ruta: String, resultados:InputResultadosSimulacion) {
    import net.liftweb.json.Serialization.write
    val pw = new PrintWriter(new File(ruta))
    pw.write(write(resultados))
    pw.close
  }
}
