package com.ViasApp.JsonClasses
import net.liftweb.json._
import java.io._
import com.ViasApp.Criaturas.Animal
import scala.io.Source

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
  private val claseV = classOf[Vehiculos]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Vehiculos] = {
    case (TypeInfo(claseV, _), json) => json match {
    case JObject(JField("total", JInt(total))::
    JField("carros", JInt(carros)) ::
    JField("motos", JInt(motos)) ::
    JField("buses", JInt(buses)) ::
    JField("camiones", JInt(camiones)) ::
    JField("motoTaxis", JInt(motoTaxis)) :: Nil) =>
      new Vehiculos(total.intValue,carros.intValue,motos.intValue,buses.intValue,camiones.intValue,motoTaxis.intValue)
    case x => throw new MappingException("No se puede convertir " + x + " a Vehiculos")
    }
  }
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
  private val clase = classOf[MallaVial]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), MallaVial] = {
    case (TypeInfo(clase, _), json) => json match {
//    case JObject(JField("vias", JInt(vias)) ::
//  JField("intersecciones", JInt(intersecciones)) ::
//  JField("viasUnSentido", JInt(viasUnSentido)) ::
//  JField("viasDobleSentido", JInt(viasDobleSentido)) ::
//  JField("velocidadMinima", JInt(velocidadMinima)) ::
//  JField("velocidadMaxima", JInt(velocidadMaxima)) ::
//  JField("longitudPromedio", JInt(longitudPromedio)) ::
//  JField("vehiculosEnInterseccion", JObject(vehiculosEnInterseccion)) :: Nil) =>
//      new MallaVial(vias.intValue,intersecciones.intValue,viasUnSentido.intValue,viasDobleSentido.intValue,velocidadMinima.intValue,velocidadMaxima.intValue,longitudPromedio.intValue,vehiculosEnInterseccion)
    case x => throw new MappingException("No se puede convertir " + x + " a MallaVial")
    }
  }
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
  private val clase = classOf[VehiculosEnInterseccion]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), VehiculosEnInterseccion] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(JField("promedioOrigen", JInt(promedioOrigen)) ::
  JField("promedioDestino", JInt(promedioDestino)) ::
  JField("sinOrigen", JInt(sinOrigen)) ::
  JField("sinDestino", JInt(sinDestino)) :: Nil) =>
      new VehiculosEnInterseccion(promedioOrigen.intValue,promedioDestino.intValue,sinOrigen.intValue,sinDestino.intValue)
    case x => throw new MappingException("No se puede convertir " + x + " a VehiculosEnInterseccion")
    }
  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: VehiculosEnInterseccion => JObject(x.getAtributosJson)
  }
}
case class Tiempos(simulacion:Int,realidad:Int) extends SerializableJson{
  def getAtributosJson = JField("simulacion", JInt(simulacion)) ::
  JField("realidad", JInt(realidad)) :: Nil
}
class TiemposSerializer{
  private val clase = classOf[Tiempos]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Tiempos] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(JField("simulacion", JInt(simulacion)) ::
  JField("realidad", JInt(realidad)) :: Nil) =>
      new Tiempos(simulacion.intValue,realidad.intValue)
    case x => throw new MappingException("No se puede convertir " + x + " a Tiempos")
    }
  }
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
  private val clase = classOf[Velocidades]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Velocidades] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(JField("minima", JInt(minima)) ::
  JField("maxima", JInt(maxima)) ::
  JField("promedio", JInt(promedio)) :: Nil) =>
      new Velocidades(minima.intValue,maxima.intValue,promedio.intValue)
    case x => throw new MappingException("No se puede convertir " + x + " a Velocidades")
    }
  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Velocidades => JObject(x.getAtributosJson)
  }
}

case class Distancias(minima:Int,maxima:Int,promedio:Int)extends SerializableJson{
  def getAtributosJson = JField("minima", JInt(minima)) ::
  JField("maxima", JInt(maxima)) ::
  JField("promedio", JInt(promedio)) :: Nil
}
class DistanciasSerializer {
  private val clase = classOf[Distancias]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Distancias] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(JField("minima", JInt(minima)) ::
  JField("maxima", JInt(maxima)) ::
  JField("promedio", JInt(promedio)) :: Nil) =>
      new Distancias(minima.intValue,maxima.intValue,promedio.intValue)
    case x => throw new MappingException("No se puede convertir " + x + " a Distancias")
    }
  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Distancias => JObject(x.getAtributosJson)
  }
}
case class ResultadosSimulacion(vehiculos:Vehiculos,
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
class ResultadosSimulacionSerializer {
  //  private val clase = classOf[]
//  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), ] = {
//    case (TypeInfo(clase, _), json) => json match {
//    case JObject(JField("", JInt())::Nil) =>
//      new ()
//    case x => throw new MappingException("No se puede convertir " + x + " a ")
//    }
//  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: ResultadosSimulacion => JObject(x.getAtributosJson)
  }
}



object Json{
  implicit val formats = Serialization.formats(NoTypeHints)
  def escribirArchivo(ruta: String, resultados:ResultadosSimulacion) {
    import net.liftweb.json.Serialization.write
    val pw = new PrintWriter(new File(ruta))
    pw.write(write(resultados))
    pw.close
  }
  
  def LeerJson(ruta:String):Unit = {
    val contenido = Source.fromFile(ruta).getLines.mkString
    println(s"contenido: $contenido")
    val parsed = parse(contenido)
    print(parsed)
  }
}

