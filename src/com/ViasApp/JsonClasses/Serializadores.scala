package com.ViasApp.JsonClasses
import net.liftweb.json._
import java.io._
import scala.io.Source

//////////////////////////////////////Inicio de resultadosSimulacion//////////////////////////////////////////////

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
class VehiculosSerializer extends Serializer[Vehiculos]{
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
class MallaVialSerializer extends Serializer[MallaVial]{
  private val clase = classOf[MallaVial]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), MallaVial] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(JField("vias", JInt(vias)) ::
  JField("intersecciones", JInt(intersecciones)) ::
  JField("viasUnSentido", JInt(viasUnSentido)) ::
  JField("viasDobleSentido", JInt(viasDobleSentido)) ::
  JField("velocidadMinima", JInt(velocidadMinima)) ::
  JField("velocidadMaxima", JInt(velocidadMaxima)) ::
  JField("longitudPromedio", JInt(longitudPromedio)) ::
  JField("vehiculosEnInterseccion", vehiculosEnInterseccion) :: Nil) =>
      new MallaVial(vias.intValue,intersecciones.intValue,
          viasUnSentido.intValue,viasDobleSentido.intValue,
          velocidadMinima.intValue,velocidadMaxima.intValue,
          longitudPromedio.intValue,vehiculosEnInterseccion.extract[VehiculosEnInterseccion]) 
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
class VehiculosEnInterseccionSerializer extends Serializer[VehiculosEnInterseccion]{
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
class TiemposSerializer extends Serializer[Tiempos]{
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
class VelocidadesSerializer extends Serializer[Velocidades]{
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
class DistanciasSerializer extends Serializer[Distancias]{
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
/////////////////////Añadido para la tercera entrega////////////////////////
case class Comparendos(cantidad:Int,promedioPorcentajeExceso:Int)extends SerializableJson{
  def getAtributosJson = JField("cantidad", JInt(cantidad)) ::
  JField("promedioPorcentajeExceso", JInt(promedioPorcentajeExceso)) :: Nil
}
class ComparendosSerializer extends Serializer[Comparendos]{
  private val clase = classOf[Comparendos]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Comparendos] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(JField("cantidad", JInt(cantidad)) ::
  JField("promedioPorcentajeExceso", JInt(promedioPorcentajeExceso)) :: Nil) =>
      new Comparendos(cantidad.intValue,promedioPorcentajeExceso.intValue)
    case x => throw new MappingException("No se puede convertir " + x + " a Comparendos")
    }
  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Comparendos => JObject(x.getAtributosJson)
  }
}
/////////////////////////////////////////////
case class ResultadosSimulacion(vehiculos:Vehiculos,
                                mallaVial:MallaVial,
                                tiempos:Tiempos,
                                velocidades:Velocidades,
                                distancias:Distancias,
                                comparendos:Comparendos)extends SerializableJson{
  def getAtributosJson = JField("vehiculos", JObject(vehiculos.getAtributosJson)) ::
  JField("mallaVial", JObject(mallaVial.getAtributosJson)) ::
  JField("tiempos", JObject(tiempos.getAtributosJson)) ::
  JField("velocidades", JObject(velocidades.getAtributosJson)) ::
  JField("distancias", JObject(distancias.getAtributosJson)) ::
  JField("comparendos", JObject(comparendos.getAtributosJson)):: Nil
}

class ResultadosSimulacionSerializer extends Serializer[ResultadosSimulacion]{
  private val clase = classOf[ResultadosSimulacion]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), ResultadosSimulacion] = {
    case (TypeInfo(clase, _), json) => json match {
    case JObject(
  JField("vehiculos", vehiculos) ::
  JField("mallaVial", mallaVial) ::
  JField("tiempos", tiempos) ::
  JField("velocidades", velocidades) ::
  JField("distancias", distancias) ::
  JField("comparendos", comparendos):: Nil) =>
      new ResultadosSimulacion(
          vehiculos.extract[Vehiculos],
          mallaVial.extract[MallaVial],
          tiempos.extract[Tiempos],
          velocidades.extract[Velocidades],
          distancias.extract[Distancias],
          comparendos.extract[Comparendos])
    case x => throw new MappingException("No se puede convertir " + x + " a ResultadosSimulacionSerializer")
    }
  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: ResultadosSimulacion => JObject(x.getAtributosJson)
  }
}
case class InputResultadosSimulacion(resultadosSimulacion:ResultadosSimulacion) extends SerializableJson{
  def getAtributosJson = JField("resultadosSimulacion",JObject(resultadosSimulacion.getAtributosJson)) :: Nil
}
class InputResultadosSimulacionSerializer extends Serializer[InputResultadosSimulacion]{
  private val claseV = classOf[InputResultadosSimulacion]
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), InputResultadosSimulacion] = {
    case (TypeInfo(claseV, _), json) => json match {
    case JObject(JField("resultadosSimulacion",resultadosSimulacion) :: Nil) =>
      new InputResultadosSimulacion(resultadosSimulacion.extract[ResultadosSimulacion])
    case x => throw new MappingException("No se puede convertir " + x + " a InputResultadosSimulacion")
    }
  }
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: InputResultadosSimulacion => JObject(x.getAtributosJson)
  }
}


////////////////////////////////////////Fin de resultadosSimulacion///////////////////////////////////////////


object Json{
  implicit val formats = DefaultFormats
  Serialization.formats(NoTypeHints) +
  new VelocidadesSerializer + 
  new VehiculosEnInterseccionSerializer +
  new VehiculosSerializer +
  new MallaVialSerializer +
  new TiemposSerializer +
  new VelocidadesSerializer +
  new DistanciasSerializer +
  new ResultadosSimulacionSerializer +
  new InputResultadosSimulacionSerializer +
  new ComparendosSerializer
   

}