package com.ViasApp.JsonClasses
import net.liftweb.json._
import java.io._
import com.ViasApp.Criaturas.Animal



case class Vehiculos(total:Int,carros:Int,
                     motos:Int,buses:Int,
                     camiones:Int,motoTaxis:Int)
                     extends SerializableJson{
  def getAtributosJson = JField("total", JInt(total))::
    JField("carros", JInt(carros)) ::
    JField("motos", JInt(motos)) ::
    JField("buses", JInt(buses)) ::
    JField("camiones", JInt(camiones)) ::
    JField("motoTaxis", JInt(motoTaxis)) :: Nil
}

class VehiculosSerializer {
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Vehiculos => JObject(x.getAtributosJson)
  }
}
case class MallaVial(vias:Int,intersecciones:Int,
                     viasUnSentido:Int,viasDobleSentido:Int,
                     velocidadMinima:Int,velocidadMaxima:Int,
                     longitudPromedio:Int,
                     vehiculosEnInterseccion:VehiculosEnInterseccion) extends SerializableJson{
  def getAtributosJson = JField("vias", JInt(vias)) ::
  JField("intersecciones", JInt(intersecciones)) ::
  JField("viasUnSentido", JInt(viasUnSentido)) ::
  JField("viasDobleSentido", JInt(viasDobleSentido)) ::
  JField("velocidadMinima", JInt(velocidadMinima)) ::
  JField("velocidadMaxima", JInt(velocidadMaxima)) ::
  JField("longitudPromedio", JInt(longitudPromedio)) ::
  JField("vehiculosEnInterseccion", JObject(vehiculosEnInterseccion.getAtributosJson)) :: Nil
}
class MallaVialSerializer {
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: MallaVial => JObject(x.getAtributosJson)
  }
}
case class VehiculosEnInterseccion(promedioOrigen:Int,
                                   promedioDestino:Int,
                                   sinOrigen:Int,
                                   sinDestino:Int) extends SerializableJson{
  def getAtributosJson = JField("promedioOrigen", JInt(promedioOrigen)) ::
  JField("promedioDestino", JInt(promedioDestino)) ::
  JField("sinOrigen", JInt(sinOrigen)) ::
  JField("sinDestino", JInt(sinDestino)) :: Nil
  
}
class VehiculosEnInterseccionSerializer {
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: VehiculosEnInterseccion => JObject(x.getAtributosJson)
  }
}
case class Tiempos(simulacion:Int,realidad:Int) extends SerializableJson{
  def getAtributosJson = JField("simulacion", JInt(simulacion)) ::
  JField("realidad", JInt(realidad)) :: Nil
}
class TiemposSerializer{
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Tiempos => JObject(x.getAtributosJson)
  }
}
case class Velocidades(minima:Int,maxima:Int,promedio:Int) extends SerializableJson{
  def getAtributosJson = JField("minima", JInt(minima)) ::
  JField("maxima", JInt(maxima)) ::
  JField("promedio", JInt(promedio)) :: Nil
}
class VelocidadesSerializer {
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Velocidades => JObject(x.getAtributosJson)
  }
}
case class Distancias(minima:Int,maxima:Int,promedio:Int)extends SerializableJson{
  def getAtributosJson = JField("minima", JInt(minima)) ::
  JField("maxima", JInt(maxima)) ::
  JField("promedio", JInt(promedio)) :: Nil
}
class Serializer {
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Distancias => JObject(x.getAtributosJson)
  }
}
case class resultadosSimulacion(vehiculos:Vehiculos,
                                mallaVial:MallaVial,
                                tiempos:Tiempos,
                                velocidades:Velocidades,
                                distancias:Distancias)extends SerializableJson{
  def getAtributosJson = JField("vehiculos", JObject(vehiculos.getAtributosJson)) ::
  JField("mallaVial", JObject(mallaVial.getAtributosJson)) ::
  JField("tiempos", JObject(tiempos.getAtributosJson)) ::
  JField("velocidades", JObject(velocidades.getAtributosJson)) ::
  JField("distancias", JObject(distancias.getAtributosJson)) :: Nil
}
class resultadosSimulacionSerializer {
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: resultadosSimulacion => JObject(x.getAtributosJson)
  }
}
object Json{
  implicit val formats = Serialization.formats(NoTypeHints)
  def escribirArchivo(ruta: String, resultados:resultadosSimulacion) {
    import net.liftweb.json.Serialization.write
    val pw = new PrintWriter(new File(ruta))
    pw.write(write(resultados))
    pw.close
  }
}

