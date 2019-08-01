package com.ViasApp.JsonClasses
import net.liftweb.json._
import java.io._
import com.ViasApp.Criaturas.Animal


case class Vehiculos(total:Int,carros:Int,
                     motos:Int,buses:Int,
                     camiones:Int,motoTaxis:Int)
case class MallaVial(vias:Int,intersecciones:Int,
                     viasUnSentido:Int,viasDobleSentido:Int,
                     velocidadMinima:Int,velocidadMaxima:Int,
                     longitudPromedio:Int,
                     vehiculosEnInterseccion:VehiculosEnInterseccion)
case class VehiculosEnInterseccion(promedioOrigen:Int,
                                   promedioDestino:Int,
                                   sinOrigen:Int,
                                   sinDestino:Int)
case class Tiempos(simulacion:Int,realidad:Int)
case class Velocidades(minima:Int,maxima:Int,promedio:Int)
case class Distancias(minima:Int,maxima:Int,promedio:Int)

case class resultadosSimulacion(vehiculos:Vehiculos,
                                mallaVial:MallaVial,
                                tiempos:Tiempos,
                                velocidades:Velocidades,
                                distancias:Distancias)
object Json{
  //Es necesario crear los serializadores
  implicit val formats = Serialization.formats(NoTypeHints)
  def escribirArchivo(ruta: String, resultados:resultadosSimulacion) {
    import net.liftweb.json.Serialization.write
    val pw = new PrintWriter(new File(ruta))
    pw.write(write(resultados))
    pw.close
  }
}

