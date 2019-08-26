package com.ViasApp.inmovil
import org.neo4j.driver.v1._
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import scalax.collection.mutable.ArraySet

object Conexion {

  val url = com.ViasApp.main.Main.url
  val user = com.ViasApp.main.Main.user //Usuario por defecto
  val pass = com.ViasApp.main.Main.pass //Pass de la bd activa

  def getSession(): (Driver, Session) = {
    val driver = GraphDatabase.driver(url, AuthTokens.basic(user, pass))
    val session = driver.session
    (driver, session)
  }

  //OBTIENE SOLO INTERSECCIONES
  def getIntersecciones(): Array[Interseccion] = {
    val (driver, session) = getSession()
    val script = s"MATCH (i:Interseccion) RETURN DISTINCT i"
    val result = session.run(script)
    val intersecciones = ArraySet.empty[Interseccion]

    while (result.hasNext()) {
      val valores = result.next().values()
      val node = valores.get(0)
      val nombre = node.get("nombre").asString()
      if (nombre.equalsIgnoreCase("None")) {
        val i1 = new Interseccion(node.get("x").asString().toFloat.toInt, node.get("y").asString().toFloat.toInt, None)
        intersecciones += i1
      } else {
        val i1 = new Interseccion(node.get("x").asString().toFloat.toInt, node.get("y").asString().toFloat.toInt, Some(node.get("nombre").asString()))
        intersecciones += i1
      }

      session.close()
      driver.close()
    }
    intersecciones.toArray[Interseccion]
  }

  //OBTIENE TUPLA DE ARRAYS (VIAS, INTERSECCIONES)
  def getVias(): (Array[Via], Array[Interseccion]) = {
    val (driver, session) = getSession()
    val script = s"MATCH(i1:Interseccion)-[:ORIGEN]->(v1:Via)-[:FIN]->(i2:Interseccion) RETURN DISTINCT i1,v1,i2"
    val result = session.run(script)

    val vias = ArrayBuffer.empty[Via]
    val intersecciones = ArraySet.empty[Interseccion]

    while (result.hasNext()) {
      val valores = result.next().values()
      val i1 = valores.get(0)
      val v1 = valores.get(1)
      val i2 = valores.get(2)

      var nombre_i1: Option[String] = None
      var string_obtenido = i1.get("nombre").asString()

      if (string_obtenido.equalsIgnoreCase("None")) {
        nombre_i1 = None
      } else {
        nombre_i1 = Some(string_obtenido)
      }

      val In1 = new Interseccion(i1.get("x").asString().toFloat.toInt, i1.get("y").asString().toFloat.toInt, nombre_i1)
      intersecciones += In1

      string_obtenido = i2.get("nombre").asString()
      if (string_obtenido.equalsIgnoreCase("None")) {
        nombre_i1 = None
      } else {
        nombre_i1 = Some(string_obtenido)
      }

      val In2 = new Interseccion(i2.get("x").asString().toFloat.toInt, i2.get("y").asString().toFloat.toInt, nombre_i1)
      intersecciones += In2

      string_obtenido = v1.get("nombre").asString()

      if (string_obtenido.equalsIgnoreCase("None")) {
        nombre_i1 = None
      } else {
        nombre_i1 = Some(string_obtenido)
      }

      var camara: Option[CamaraFotoDeteccion] = None
      string_obtenido = v1.get("camara_distancia").asString()

      if (string_obtenido.equalsIgnoreCase("None")) {
        camara = None
      } else {
        camara = Some(new CamaraFotoDeteccion(string_obtenido.toDouble))
      }

      string_obtenido = v1.get("sentido").asString()

      if (string_obtenido.equalsIgnoreCase("dobleVia")) {
        val V1 = new Via(In1, In2, v1.get("velocidad").asInt(), TipoVia(v1.get("tipo").asString()), Sentido.dobleVia, v1.get("numero").asString(), nombre_i1, camara)
        vias += V1
      } else {
        val V1 = new Via(In1, In2, v1.get("velocidad").asInt(), TipoVia(v1.get("tipo").asString()), Sentido.unaVia, v1.get("numero").asString(), nombre_i1, camara)
        vias += V1
      }

      session.close()
      driver.close()
    }

    (vias.toArray[Via], intersecciones.toArray[Interseccion])
  }

}
