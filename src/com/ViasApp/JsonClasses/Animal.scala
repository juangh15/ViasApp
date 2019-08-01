package com.ViasApp.JsonClasses
import net.liftweb.json._
import net.liftweb.json.JsonAST.JValue
import scala.math.BigInt.int2bigInt
//Creo una clase simple, con dos atributos: nombre, edad
class Animal(val nombre:String,val edad:Int) 

//Creo una clase que usare para serializar y desserializar las instancias de Animal
//desserializar: Es convertir un elemento de Json a una instancia de Animal
//serializar: Es convertir una instancia de Animal a un elemnto de Json
class AnimalSerializer extends Serializer[Animal] {
  private val AnimalClass = classOf[Animal]
  //Este metodo Desserializa
  def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), Animal] = {
    case (TypeInfo(AnimalClass, _), json) => json match {
      //Especifico los atributos de la clase y sus respectivos tipos de dato:
      //nombre debe ser JString, edad debe ser JInt
      case JObject(JField("nombre", JString(s)) :: JField("edad", JInt(e)) :: Nil) =>
              //Creo una nueva instancia de Animal con los datos conseguidos
               new Animal(s.toString, e.toInt)
      case x => throw new MappingException("Can't convert " + x + " to Prueba")
    }
  }
  //Este metodo serializa
  def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
    case x: Animal => JObject(JField("nombre", JString(x.nombre)) :: 
                     JField("edad",   JInt(x.edad)) :: Nil)
  }
}