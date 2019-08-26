package com.ViasApp.inmovil
import org.neo4j.driver.v1._
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.movil._
import scalax.collection.mutable.ArraySet
import scala.collection.mutable.Queue

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

  def insertarViajes(viajes:ArrayBuffer[Viaje]){
    val (driver, session) = getSession()
    var script:String = "CREATE "
    var cont = 0
    viajes.foreach(x =>{
      var cuentaVias:Int = 1
      
      //obtiene el nombre largo de clase que usa JAVA
      var classe = x.v.getClass.toString()
      
      //recorta solo el nombre utilizado en scala (Carro, Moto, etc)
      var clase = classe.substring(classe.lastIndexOf("com.ViasApp")+18, classe.size)
      
      script+=s"(vehiculo$cont:${clase}{pla:'${x.v.placa}',x:${x.v.posicion.x},y:${x.v.posicion.y},mag:${x.v.velocidad.magnitud},dir:${x.v.velocidad.direccion.valor}}),\n"
      script+=s"(:Ruta{size:${x.ruta.size},"
      x.ruta.foreach(y => {
        //Clave: origenX-origenY-finX-finY
        script+=s"v$cuentaVias:'${y.or.x.toString()}-${y.or.y.toString()}-${y.fn.x.toString()}-${y.fn.y.toString()}',"
        
        cuentaVias+=1
      })
      //quita la ultima coma dentro de ruta
        script = script.substring(0,script.size-1)
        script+=s"})<-[:ENRUTADO]-(vehiculo$cont),\n"
      cont+=1
    })
    //quita el "\n" y la ultima coma del script
    script = script.substring(0,script.size-2)
    //println(script)
    val result = session.run(script)
    session.close()
    driver.close()
  }

  //obtiene array de viajes Viaje(vehiculo, ruta)
  def getViajes(vias: Array[Via]): ArrayBuffer[Viaje] = {
    var viajes = ArrayBuffer.empty[Viaje]
    val (driver, session) = getSession()
    val script = s"MATCH (N)-[:ENRUTADO]->(Ruta1)\nWHERE (N:Carro) or (N:Bus) or (N:Camion) or (N:Moto) or (N:MotoTaxi)\nRETURN DISTINCT N, labels(N) as clase, Ruta1"
    val result = session.run(script)
    while (result.hasNext()) {
      val valores = result.next().values()
      
      //Vnode = nodo del vehiculo
      val Vnode = valores.get(0)
      //clase = Clase del vehiculo
      val clase = valores.get(1).get(0).asString()
      
      val velocidad = new Velocidad(Vnode.get("mag").asDouble(), new Angulo(Vnode.get("dir").asDouble()))
      val posicion = new Punto(Vnode.get("x").asDouble(), Vnode.get("y").asDouble())
      val placa = Vnode.get("pla").asString()

      //vehiculo generico para luego hacer el matching
      var vehiculo: Vehiculo = new Vehiculo("k", new Punto(1, 1), new Velocidad(1, new Angulo(0))) {}
      clase match {
        case "Bus"      => vehiculo = new Bus(placa, posicion, velocidad)
        case "Carro"    => vehiculo = new Carro(placa, posicion, velocidad)
        case "Moto"     => vehiculo = new Moto(placa, posicion, velocidad)
        case "Camion"   => vehiculo = new Camion(placa, posicion, velocidad)
        case "MotoTaxi" => vehiculo = new MotoTaxi(placa, posicion, velocidad)
      }
      //Rnode = nodo de la ruta
      val Rnode = valores.get(2)

      //PARA VER LAS LLAVES DE CADA NODO-----------------

      //      println("llaves de nodo")
      //      println(Vnode.keys())
      //      println(clase)
      //      println(Rnode.keys())

      //----------------------------------------

      var ruta = Queue.empty[Via]
      //obtiene la cantidad de vias 
      val tamaño = Rnode.get("size").asInt
      //recorre los strings con coordenadas, de cada via
      for (i <- 1 to tamaño) {
        val f = Rnode.get(s"v$i").asString().split("-")
        val inicioX = f(0).toDouble
        val inicioY = f(1).toDouble
        val finX = f(2).toDouble
        val finY = f(3).toDouble
        var ind: Int = 0
        var condicion = true
        while (condicion) {
          var via1 = vias(ind)
          if ((via1.origen.x == inicioX) & (via1.origen.y == inicioY) & (via1.fin.x == finX) & (via1.fin.y == finY)) {
            ruta.enqueue(via1)
            condicion = false
          }
          ind += 1
        }

      }
      viajes += new Viaje(vehiculo, ruta)
    }
    session.close()
    driver.close()
    viajes
  }

}
