package com.ViasApp.inmovil
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._
import com.ViasApp.JsonClasses.ResultadosParaSimulacion
import scala.util.Random
import scalax.collection.mutable.Graph
import scalax.collection.edge.WLDiEdge
import scala.collection.mutable.{ Queue, Map }
import com.ViasApp.JsonClasses.ManejoJson
import com.ViasApp.main.Grafico
import scalax.collection.mutable.ArraySet

object Simulacion extends Runnable {

  //estado 0 = apagada / en espera de presionar una tecla
  //estado 1 = en movimiento
  //estado 2 = finalizada, regresara a 0 en cuanto tome los resultados
  //estado 3 = guarda datos en neo y cierra
  //estado 4 = busca en neo los datos anteriores y si los hay, "reinicia" colocando esos datos
  var estadoSimulacion: Int = 0
  var t: Double = 0.1

  //variable random para la creacion de vehiculos y otros
  var random = scala.util.Random

  // lectura variables del json ------------------------------------------
  val dt = ManejoJson.parametros.dt
  val tRefresh = ManejoJson.parametros.tRefresh
  val minVehiculos = ManejoJson.parametros.vehiculos.minimo
  val maxVehiculos = ManejoJson.parametros.vehiculos.maximo
  val minVelocidad = ManejoJson.parametros.velocidad.minimo
  val maxVelocidad = ManejoJson.parametros.velocidad.maximo
  val proporcionCarros = ManejoJson.parametros.proporciones.carros
  val proporcionMotos = ManejoJson.parametros.proporciones.motos
  val proporcionBuses = ManejoJson.parametros.proporciones.buses
  val proporcionCamiones = ManejoJson.parametros.proporciones.camiones
  val proporcionMototaxis = ManejoJson.parametros.proporciones.motoTaxis
  val minTiempoVerde = ManejoJson.parametros.semaforos.minTiempoVerde
  val maxTiempoVerde = ManejoJson.parametros.semaforos.maxTiempoVerde
  val tiempoAmarillo = ManejoJson.parametros.semaforos.tiempoAmarillo
  val XSemaforoFrenar = ManejoJson.parametros.distanciasFrenadoVehiculos.XSemaforoFrenar
  val XSemaforoAmarilloContinuar = ManejoJson.parametros.distanciasFrenadoVehiculos.XSemaforoAmarilloContinuar

  //cantidad de vehiculos aleatoria entre limites y cantidad por tipo de vehiculo
  val cantidadVehiculos = minVehiculos + random.nextInt((maxVehiculos - minVehiculos) + 1)
  val cantidadCarros = (proporcionCarros * cantidadVehiculos).toInt
  val cantidadMotos = (proporcionMotos * cantidadVehiculos).toInt
  val cantidadBuses = (proporcionBuses * cantidadVehiculos).toInt
  val cantidadCamiones = (proporcionCamiones * cantidadVehiculos).toInt
  val cantidadMototaxis = cantidadVehiculos - (cantidadCarros + cantidadMotos + cantidadBuses + cantidadCamiones)
//fin de lectura variables del JSON-------------------------------------------------------
  
  
   //NEO4J --------------------------

     val (vias1, intersecciones) = Conexion.getVias
  
     val vias = viasDobles(vias1)

  //----------------------------------
     
     //-------------------------CAMARAS--------------
     vias.foreach(x=>{
       if(x.camara.isDefined){
         println("VIA CON CAMARA A :" + x.camara.get.distancia)
         println(x)
       }
     })
     val comparendos = ArraySet[Comparendo]()
     //-------------------------------

  // PARA BORRAR CUANDO SE OBTENGAN VIAS E INTERSECCIONES DEL NEO4J-----------------------------------

  //intersecciones, arreglo intersecciones y arreglo de vias

//  val niquia = new Interseccion(300, 12000, Some("Niquia")); val lauraAuto = new Interseccion(2400, 11400, Some("M. Laura Auto")); val lauraReg = new Interseccion(2400, 12600, Some("M. Laura Reg")); val ptoCero = new Interseccion(5400, 12000, Some("Pto 0")); val mino = new Interseccion(6300, 15000, Some("Minorista")); val villa = new Interseccion(6300, 19500, Some("Villanueva")); val ig65 = new Interseccion(5400, 10500, Some("65 Igu")); val robledo = new Interseccion(5400, 1500, Some("Exito Rob")); val colReg = new Interseccion(8250, 12000, Some("Col Reg")); val colFerr = new Interseccion(8250, 15000, Some("Col Ferr")); val col65 = new Interseccion(8250, 10500, Some("Col 65")); val col80 = new Interseccion(8250, 1500, Some("Col 80")); val juanOr = new Interseccion(10500, 19500, Some("Sn Juan Ori")); val maca = new Interseccion(10500, 12000, Some("Macarena")); val expo = new Interseccion(12000, 13500, Some("Exposiciones")); val reg30 = new Interseccion(13500, 15000, Some("Reg 30")); val monte = new Interseccion(16500, 15000, Some("Monterrey")); val agua = new Interseccion(19500, 15000, Some("Aguacatala")); val viva = new Interseccion(21000, 15000, Some("Viva Env")); val mayor = new Interseccion(23400, 15000, Some("Mayorca")); val ferrCol = new Interseccion(8250, 15000, Some("Ferr Col")); val ferrJuan = new Interseccion(10500, 15000, Some("Alpujarra")); val sanDiego = new Interseccion(12000, 19500, Some("San Diego")); val premium = new Interseccion(13500, 19500, Some("Premium")); val pp = new Interseccion(16500, 19500, Some("Parque Pob")); val santafe = new Interseccion(19500, 18750, Some("Santa Fe")); val pqEnv = new Interseccion(21000, 18000, Some("Envigado")); val juan65 = new Interseccion(10500, 10500, Some("Juan 65")); val juan80 = new Interseccion(10500, 1500, Some("Juan 80")); val _33_65 = new Interseccion(12000, 10500, Some("33 con 65")); val bule = new Interseccion(12000, 7500, Some("Bulerias")); val gema = new Interseccion(12000, 1500, Some("St Gema")); val _30_65 = new Interseccion(13500, 10500, Some("30 con 65")); val _30_70 = new Interseccion(13500, 4500, Some("30 con 70")); val _30_80 = new Interseccion(13500, 1500, Some("30 con 80")); val bol65 = new Interseccion(11100, 10500, Some("Boliv con 65")); val gu10 = new Interseccion(16500, 12000, Some("Guay con 10")); val terminal = new Interseccion(16500, 10500, Some("Term Sur")); val gu30 = new Interseccion(13500, 12000, Some("Guay 30")); val gu80 = new Interseccion(19500, 12000, Some("Guay 80")); val _65_80 = new Interseccion(19500, 10500, Some("65 con 30")); val gu_37S = new Interseccion(21000, 12000, Some("Guay con 37S"));
//
//  val intersecciones = Array(niquia, lauraAuto, lauraReg, ptoCero, mino, villa, ig65, robledo, colReg, col65, col80, juanOr, maca, expo, reg30, monte, agua, viva, mayor, ferrCol, ferrJuan, sanDiego, premium, pp, santafe, pqEnv, juan65, juan80, _33_65, bule, gema, _30_65, _30_70, _30_80, bol65, gu10, terminal, gu30, gu80, _65_80, gu_37S)
//
//  val vias1 = Array(
//    new Via(niquia, lauraAuto, 80, TipoVia("Carrera"), Sentido.dobleVia, "64C", Some("Auto Norte")), new Via(niquia, lauraReg, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(lauraAuto, lauraReg, 60, TipoVia("Calle"), Sentido.dobleVia, "94", Some("Pte Madre Laura")), new Via(lauraAuto, ptoCero, 80, TipoVia("Carrera"), Sentido.dobleVia, "64C", Some("Auto Norte")), new Via(lauraReg, ptoCero, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(ptoCero, mino, 60, TipoVia("Calle"), Sentido.dobleVia, "58", Some("Oriental")), new Via(mino, villa, 60, TipoVia("Calle"), Sentido.dobleVia, "58", Some("Oriental")), new Via(ptoCero, ig65, 60, TipoVia("Calle"), Sentido.dobleVia, "55", Some("Iguaná")), new Via(ig65, robledo, 60, TipoVia("Calle"), Sentido.dobleVia, "55", Some("Iguaná")), new Via(ptoCero, colReg, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(colReg, maca, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(maca, expo, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(expo, reg30, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(reg30, monte, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(monte, agua, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(agua, viva, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")),
//    new Via(viva, mayor, 80, TipoVia("Carrera"), Sentido.dobleVia, "62", Some("Regional")), new Via(mino, ferrCol, 60, TipoVia("Carrera"), Sentido.dobleVia, "55", Some("Ferrocarril")), new Via(ferrCol, ferrJuan, 60, TipoVia("Carrera"), Sentido.dobleVia, "55", Some("Ferrocarril")), new Via(ferrJuan, expo, 60, TipoVia("Carrera"), Sentido.dobleVia, "55", Some("Ferrocarril")), new Via(villa, juanOr, 60, TipoVia("Carrera"), Sentido.dobleVia, "46", Some("Oriental")), new Via(juanOr, sanDiego, 60, TipoVia("Carrera"), Sentido.dobleVia, "46", Some("Oriental")), new Via(sanDiego, premium, 60, TipoVia("Carrera"), Sentido.dobleVia, "43A", Some("Av Pob")), new Via(premium, pp, 60, TipoVia("Carrera"), Sentido.dobleVia, "43A", Some("Av Pob")), new Via(pp, santafe, 60, TipoVia("Carrera"), Sentido.dobleVia, "43A", Some("Av Pob")), new Via(santafe, pqEnv, 60, TipoVia("Carrera"), Sentido.dobleVia, "43A", Some("Av Pob")), new Via(pqEnv, mayor, 60, TipoVia("Carrera"), Sentido.dobleVia, "43A", Some("Av Pob")), new Via(ferrCol, colReg, 60, TipoVia("Calle"), Sentido.dobleVia, "450", Some("Colombia")), new Via(colReg, col65, 60, TipoVia("Calle"), Sentido.dobleVia, "450", Some("Colombia")), new Via(col65, col80, 60, TipoVia("Calle"), Sentido.dobleVia, "450", Some("Colombia")), new Via(juanOr, ferrJuan, 60, TipoVia("Calle"), Sentido.dobleVia, "44", Some("Sn Juan")), new Via(ferrJuan, maca, 60, TipoVia("Calle"), Sentido.dobleVia, "44", Some("Sn Juan")), new Via(maca, juan65, 60, TipoVia("Calle"), Sentido.dobleVia, "44", Some("Sn Juan")), new Via(juan65, juan80, 60, TipoVia("Calle"), Sentido.dobleVia, "44", Some("Sn Juan")), new Via(sanDiego, expo, 60, TipoVia("Calle"), Sentido.dobleVia, "33", Some("33")), new Via(expo, _33_65, 60, TipoVia("Calle"), Sentido.dobleVia, "33", Some("33")), new Via(_33_65, bule, 60, TipoVia("Calle"), Sentido.dobleVia, "33", Some("33")), new Via(bule, gema, 60, TipoVia("Calle"), Sentido.dobleVia, "33", Some("33")), new Via(premium, reg30, 60, TipoVia("Calle"), Sentido.dobleVia, "30", Some("30")), new Via(reg30, _30_65, 60, TipoVia("Calle"), Sentido.dobleVia, "30", Some("30")), new Via(_30_65, _30_70, 60, TipoVia("Calle"), Sentido.dobleVia, "30", Some("30")), new Via(_30_70, _30_80, 60, TipoVia("Calle"), Sentido.dobleVia, "30", Some("30")), new Via(maca, bol65, 60, TipoVia("Diagonal"), Sentido.dobleVia, "74B", Some("Boliv")), new Via(bol65, bule, 60, TipoVia("Diagonal"), Sentido.dobleVia, "74B", Some("Boliv")), new Via(bule, _30_70, 60, TipoVia("Diagonal"), Sentido.dobleVia, "74B", Some("Boliv")), new Via(juan80, bule, 60, TipoVia("Transversal"), Sentido.dobleVia, "39B", Some("Nutibara")), new Via(pp, monte, 60, TipoVia("Calle"), Sentido.dobleVia, "10", Some("10")), new Via(monte, gu10, 60, TipoVia("Calle"), Sentido.dobleVia, "10", Some("10")), new Via(gu10, terminal, 60, TipoVia("Calle"), Sentido.dobleVia, "10", Some("10")), new Via(expo, gu30, 60, TipoVia("Carrera"), Sentido.dobleVia, "52", Some("Av Guay")), new Via(gu30, gu10, 60, TipoVia("Carrera"), Sentido.dobleVia, "52", Some("Av Guay")), new Via(gu10, gu80, 60, TipoVia("Carrera"), Sentido.dobleVia, "52", Some("Av Guay")), new Via(gu80, gu_37S, 60, TipoVia("Carrera"), Sentido.dobleVia, "52", Some("Av Guay")), new Via(lauraAuto, ig65, 60, TipoVia("Carrera"), Sentido.dobleVia, "65", Some("65")), new Via(ig65, col65, 60, TipoVia("Carrera"), Sentido.dobleVia, "65", Some("65")), new Via(juan65, col65, 60, TipoVia("Carrera"), Sentido.unaVia, "65", Some("65")), new Via(bol65, juan65, 60, TipoVia("Carrera"), Sentido.unaVia, "65", Some("65")), new Via(_33_65, bol65, 60, TipoVia("Carrera"), Sentido.unaVia, "65", Some("65")), new Via(_30_65, _33_65, 60, TipoVia("Carrera"), Sentido.unaVia, "65", Some("65")), new Via(_30_65, terminal, 60, TipoVia("Carrera"), Sentido.dobleVia, "65", Some("65")), new Via(terminal, _65_80, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("65")), new Via(robledo, col80, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(col80, juan80, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(juan80, gema, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(gema, _30_80, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(_30_80, _65_80, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(_65_80, gu80, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(gu80, agua, 60, TipoVia("Carrera"), Sentido.dobleVia, "80", Some("80")), new Via(agua, santafe, 60, TipoVia("Calle"), Sentido.dobleVia, "12S", Some("80")), new Via(viva, pqEnv, 60, TipoVia("Calle"), Sentido.dobleVia, "37S", Some("37S")),
//    new Via(viva, gu_37S, 60, TipoVia("Calle"), Sentido.dobleVia, "63", Some("37S")));
//
//  val vias = viasDobles(vias1)

  //--------------------------------------------------------------------------------

    //DOBLES VIAS----------------
  def viasDobles(vias2: Array[Via]) = {
    val vias3 = ArrayBuffer.empty[Via]
    vias2.foreach(via => {
      vias3 += via
      if (via.sentido.nombre == "dobleVia") {
        var via_nueva = new Via(via.fin, via.origen, via.velocidad, via.tipovia, via.sentido, via.numero, via.nombre)
        vias3 += via_nueva
      }
    })
    vias3.toArray[Via]
  }

  //-----------------
  
  //NODOS
  val nodos: Array[Nodo] = crearSemaforos(intersecciones, vias, minTiempoVerde, maxTiempoVerde, tiempoAmarillo).values.toArray[Nodo]
  println("tamaño semaforos: " + nodos.size)
  //sé que es ineficiente guardar un tiempo dentro de cada clase pero en estos momentos ya ni pienso alv

  def cambioSemaforo(dt: Int) {
    for (i <- nodos) {
      i.procesoDeCambioSemaforos(dt)
    }
  }
  
  //crear semaforos por primera vez------------------------------------
  def crearSemaforos(intersecciones2: Array[Interseccion], vias2: Array[Via], minVerde: Int, maxVerde: Int, tiempoAmarillo: Int): scala.collection.mutable.Map[Interseccion, Nodo] = {

    var nodos_semaforos = scala.collection.mutable.Map[Interseccion, ArrayBuffer[Semaforo]]()
    intersecciones2.foreach(x => { nodos_semaforos += (x -> new ArrayBuffer[Semaforo]()) })

    vias2.foreach(via => {
      val tiempoVerde = minVerde + random.nextInt(maxVerde) + 1
      var semaforo = new Semaforo((s"${((via.origen.x) / 100).toInt}${((via.origen.y) / 100).toInt}${((via.fin.x) / 100).toInt}${((via.fin.y) / 100).toInt}").toLong, 0, tiempoVerde)

      var arraySemaforos = nodos_semaforos.get(via.fin)
      if (arraySemaforos.isDefined) {
        arraySemaforos.get += semaforo
      }
    })

    var nodos_totales = scala.collection.mutable.Map[Interseccion, Nodo]()
    nodos_semaforos.keySet.foreach(x => { nodos_totales += (x -> new Nodo(x, nodos_semaforos(x), tiempoAmarillo)) })
    nodos_totales
  }

  //------------------------------------------------------------------

  //--------------------------------------------------------------------------------
  GrafoVias.construir(vias) //crea el grafo de las vias

  val grafico = new Grafico()
  
  //el radio permitido para aparecer en la siguiente interseccion
  //depende del dt y de la velocidad
  val radioPermitido = maxVelocidad*dt + 1

  var inicio: Interseccion = _ //cada interseccion inicio instanciada
  var fin: Interseccion = _ //cada interseccion final instanciada
  var vehiculo: Vehiculo = _ //cada vehiculo instanciado
  var vehiculos = new ArrayBuffer[Vehiculo]() //todos los vehiculos de la simulacion
  var interOrigen = new ArrayBuffer[Interseccion]() //todos los vehiculos de la simulacion
  var interDestino = new ArrayBuffer[Interseccion]() //todos los vehiculos de la simulacion

  //VIAJES-----------------------------

  var viajes: ArrayBuffer[Viaje] = new ArrayBuffer[Viaje]()

  //------------------------------------

  // Metodo para crear los vehiculos cada vez que se necesite--------------------------
  def creacionVehiculos = {

    //borra los existentes
    vehiculos.clear()
    viajes.clear()

    //Random para cantidades de cada vehiculo
    val cantidadVehiculos = minVehiculos + random.nextInt((maxVehiculos - minVehiculos) + 1)
    val cantidadCarros = (proporcionCarros * cantidadVehiculos).toInt
    val cantidadMotos = (proporcionMotos * cantidadVehiculos).toInt
    val cantidadBuses = (proporcionBuses * cantidadVehiculos).toInt
    val cantidadCamiones = (proporcionCamiones * cantidadVehiculos).toInt
    val cantidadMototaxis = cantidadVehiculos - (cantidadCarros + cantidadMotos + cantidadBuses + cantidadCamiones)

    val limIndex = intersecciones.size - 1

    //Creación de los vehiculos

    var camino = Queue[Via]() //cada camino por separado en una cola
    var indice = 0
    var control = false //control para no tomar la misma interseccion
    for (j <- List(cantidadCarros, cantidadMotos, cantidadBuses, cantidadCamiones, cantidadMototaxis)) {
      for (i <- 1 to j) {
        println("------------------------------------------------------------")
        //obtiene intersecciones aleatorias para el inicio y el final del camino
        inicio = intersecciones(random.nextInt(limIndex))
        interOrigen += inicio
        while (control == false) {
          fin = intersecciones(random.nextInt(limIndex))
          if (fin != inicio) {
            control = true
            interDestino += fin
          }
        }
        control = false
        println("comienza en: " + inicio)
        println("termina en: " + fin)

        //EDGES se obtiene la mejor ruta
        var edges = GrafoVias.trazarRuta(inicio, fin).edges
        //LAS VIAS SE AGREGAN AL CAMINO
        camino = new Queue[Via]()
        edges.foreach(x => camino.+=(x.toOuter.label.asInstanceOf[Via]))
        println("entro a camino y genero la ruta: ")
        if (camino.isEmpty) {
          println("no ruta generada")
        } else {
          camino.foreach(x => println(x.origen + " > " + x.fin + "\n"))
        }

        var velocidad_primera = cambioVelocidad(camino.front)
        println("se creó la velocidad" + velocidad_primera.magnitud + "con dir: " + velocidad_primera.direccion)
        
        //crea una aceleracion semi aleatoria segun su velocidad inicial /10
        var aceleracion_primera = random.nextInt(((velocidad_primera.magnitud / 10)).toInt)
        if (aceleracion_primera == 0) { aceleracion_primera = 1 }
        
        //se crea la instancia de vehiculo segun el caso
        val letras = Array("A", "B", "C", "D", "E", "F", "G", "Z", "X", "Y", "T")
        indice match {
          case 0 => vehiculo = new Carro(s"${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}-${random.nextInt(9)}${random.nextInt(9)}", new Punto(inicio.x, inicio.y), velocidad_primera, velocidad_primera.magnitud, aceleracion_primera)
          case 1 => vehiculo = new Moto(s"${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}-${random.nextInt(9)}${random.nextInt(9)}", new Punto(inicio.x, inicio.y), velocidad_primera, velocidad_primera.magnitud, aceleracion_primera)
          case 2 => vehiculo = new Bus(s"${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}-${random.nextInt(9)}${random.nextInt(9)}", new Punto(inicio.x, inicio.y), velocidad_primera, velocidad_primera.magnitud, aceleracion_primera)
          case 3 => vehiculo = new Camion(s"${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}-${random.nextInt(9)}${random.nextInt(9)}", new Punto(inicio.x, inicio.y), velocidad_primera, velocidad_primera.magnitud, aceleracion_primera)
          case 4 => vehiculo = new MotoTaxi(s"${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}${letras(random.nextInt(letras.size - 1))}-${random.nextInt(9)}${random.nextInt(9)}", new Punto(inicio.x, inicio.y), velocidad_primera, velocidad_primera.magnitud, aceleracion_primera)
        }

        vehiculos += vehiculo
        println(s"se logro crear el vehiculo: $vehiculo con velocidad ${vehiculo.velocidad}")

        //A LOS CAMINOS SE AGREGA EL VEHICULO CREADO Y SU RUTA

        viajes += new Viaje(vehiculo, camino)

      }
      indice += 1

    }

  }
  // FIN del Metodo para crear los vehiculos -----------------------------------------------
  
  
  //para no salirse del rango de velocidad permitido-------------------------------
  def cambioVelocidad(via: Via): Velocidad = {

    var min = 0
    var max = 0
    if (minVelocidad < via.velocidad) {
      min = minVelocidad
    } else {
      min = via.velocidad.toInt
    }
    if (maxVelocidad > via.velocidad) {
      max = maxVelocidad
    } else {
      max = via.velocidad.toInt
    }
    var velo = min + random.nextInt(max - min + 1)
    return new Velocidad(velo, via.angulo)
  }
  
  //FIN del metodo cambio velocidad-----------------------------------

  //CONTROL DE RUTAS CON VIAJES IMPLEMENTADOS ---------------------------------------------

  //variable para indicar que todos los vehiculos estan en su destino:
  var todosllegaron = false

  def controlDeRutas(dt: Double) {
    var total = 0 //conteo para saber cuantos llegaron
    todosllegaron = false //indicador

    //recorre el array de VIAJES para analizar cada vehiculo y su camino
    viajes.foreach(viaje_actual => {
      //vehiculo y caminos en la posicion actual del for
      var vehiculo_actual = viaje_actual.v
      var camino_actual = viaje_actual.ruta
      var posicion_antes = new Punto(vehiculo_actual.posicion.x, vehiculo_actual.posicion.y)
      //mueve el vehiculo

      if (!camino_actual.isEmpty) {
        var via_fin = camino_actual.front.fin //obtiene la primera via y su punto final

        var via_actual = camino_actual.front //obtiene la via actual

        var pasa = via_actual.semaforo.sePuedePasar(vehiculo_actual.velocidad.magnitud) //pregunta si el vehiculo pasa o no con la velocidad que tiene y los tiempos de los nodos de los semaforos
        //es decir si va a estar en rojo cuando el este pasando por ahí con la velocidad que tiene,

        //SI ENTRA AL RADIO DE LA INTERSECCION DE LA VIA:
        if ((math.pow((vehiculo_actual.posicion.x - via_fin.x), 2) + math.pow((vehiculo_actual.posicion.y - via_fin.y), 2)) <= radioPermitido * radioPermitido) {
          vehiculo_actual.posicion.x_=(via_fin.x)
          vehiculo_actual.posicion.y_=(via_fin.y)
          camino_actual.dequeue() //saca la primera via del camino Ya completa.

          //si luego de llegar a la interseccion NO HAY MAS CAMINO:
          if (camino_actual.isEmpty) {
            //no se moverá más
            vehiculo_actual.aceleracion = 0
            vehiculo_actual.velocidad.magnitud_=(0)
            vehiculo_actual.velocidad.direccion_=(new Angulo(0))
          } //si luego de llegar a la interseccion SI HAY MAS CAMINOS:
          else {
            //crea una nueva velocidad para el vehiculo basandose en la nueva via
            var nueva_velocidad = cambioVelocidad(camino_actual.front)
            vehiculo_actual.velocidad_=(nueva_velocidad)
            //vehiculo_actual.mover(dt)
          }
        } //SI NO ENTRA AL RADIO DE LA INTERSECCION: 
        else {
          vehiculo_actual.velocidad.direccion_=(camino_actual.front.angulo)
          
          if(camino_actual.front.camara.isDefined){
            var pos_vehiculo = Math.pow((camino_actual.front.origen.x - vehiculo_actual.posicion.x),2) + Math.pow((camino_actual.front.origen.y - vehiculo_actual.posicion.y),2)
            if((Math.pow(camino_actual.front.camara.get.distancia,2)-pos_vehiculo)<0){
              if(vehiculo_actual.velocidad.magnitud > camino_actual.front.velocidad){
                comparendos += new Comparendo(vehiculo_actual, vehiculo_actual.velocidad.magnitud, camino_actual.front.velocidad)
              }
            }
          }
          //vehiculo_actual.mover(dt)
        }

        //Mover y desicion de si mover pa donde y cuando desacelerar o acelerar

        //rango permitido para que se aproxime a la distancia de frenado(, es decir, margen de error)
        val rangoPermitido = 10

        //esto solo proteje de errores, como tenemos aceleraciones que casi nunca van a encajar con la velocidadAuto o el 0
        if (vehiculo_actual.velocidad.magnitud > vehiculo_actual.velocidadAuto) {
          vehiculo_actual.velocidad.magnitud = vehiculo_actual.velocidadAuto
        } else if (vehiculo_actual.velocidad.magnitud < 0) {
          vehiculo_actual.velocidad.magnitud = 0
        }

        if (pasa) {
          if (vehiculo_actual.velocidad.magnitud == vehiculo_actual.velocidadAuto) {
            vehiculo_actual.mover(dt, 0)
          } else {
            vehiculo_actual.mover(dt, 1)
          }

        } else {
          if ((math.pow((vehiculo_actual.posicion.x - via_fin.x), 2) + math.pow((vehiculo_actual.posicion.y - via_fin.y), 2) <= (vehiculo_actual.distanciaFrenado + rangoPermitido) * (vehiculo_actual.distanciaFrenado + rangoPermitido))) {
            vehiculo_actual.mover(dt, -1)
          } else {
            vehiculo_actual.mover(dt, 0)
          }

        }
      } else {
        vehiculo_actual.velocidad.magnitud_=(0)
        vehiculo_actual.velocidad.direccion_=(new Angulo(0))
        //        println(vehiculo_actual + " llego a su destino")
      }
      if (vehiculo_actual.posicion.x > 23000 || vehiculo_actual.posicion.y > 19900 || vehiculo_actual.posicion.x < 0 || vehiculo_actual.posicion.y < 0) {
        vehiculo_actual.velocidad.magnitud_=(0.0)
      }
      if (posicion_antes.x == vehiculo_actual.posicion.x && posicion_antes.y == vehiculo_actual.posicion.y) {
        total += 1
        if (total == vehiculos.size) todosllegaron = true
        //        println("no todos llñegaron")
      }

    })
  } //FINALIZA EL CONTROL DE RUTAS CON VIAJES IMPLEMENTADOS---------------------------

  
  //METODO RUN ------------------------------------------------------------------
  var condicion = true
  def run() {

    //al inicio de la simulacion solo crea los vehiculos y los envia a Serie por primera vez
    creacionVehiculos
    var serie = grafico.graficarVehiculos(vehiculos.toArray[Vehiculo], vias)

    while (true) {
      
      //estado = 2, simulacion terminada con F6, luego borra, crea y grafica nuevos vehiculos
      if (estadoSimulacion == 2) {
        //1 carros 2 buses 3 motos 4 camiones 5 mototaxis
        serie._1.clear; serie._2.clear; serie._3.clear; serie._4.clear; serie._5.clear
        creacionVehiculos
        vehiculos.foreach(vehiculo1 => {
          if (vehiculo1.isInstanceOf[Carro]) { serie._1.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[Bus]) { serie._2.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[Moto]) { serie._3.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[Camion]) { serie._4.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[MotoTaxi]) { serie._5.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
        })
        //luego cambia el estado a 0 para esperar una nueva pulsación de F5
        estadoSimulacion = 0
      }
      
      //estado = 1, simulacion CORRIENDO ACTUALMENTE, f5 fue presionado 
      if (estadoSimulacion == 1) {
        //1 carros 2 buses 3 motos 4 camiones 5 mototaxis
        serie._1.clear; serie._2.clear; serie._3.clear; serie._4.clear; serie._5.clear
        vehiculos.foreach(vehiculo1 => {
          if (vehiculo1.isInstanceOf[Carro]) { serie._1.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[Bus]) { serie._2.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[Moto]) { serie._3.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[Camion]) { serie._4.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          else if (vehiculo1.isInstanceOf[MotoTaxi]) { serie._5.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
        })
        
        //metodo para mover y controlar la logica de los vehiculos y los semaforos
        controlDeRutas(dt)
        cambioSemaforo(dt)
        t += dt

        //comprueba que TODOS los vehiculos hayan llegado a su destino
        //y guarda en resultados JSON
        if (todosllegaron) {
          println("todos llegaron")
          println("numero de comparendos:" +comparendos.size)
          estadoSimulacion = 0
          //val Resultados = new ResultadosSimulacion(cantidadVehiculos, cantidadCarros, cantidadMotos, cantidadBuses, cantidadCamiones, cantidadMototaxis, vias, intersecciones, vehiculos.toArray, t)
          val guardarResultados = new ResultadosParaSimulacion(
            cantidadVehiculos,
            cantidadCarros,
            cantidadMotos,
            cantidadBuses,
            cantidadCamiones,
            cantidadMototaxis, 50, 15, 10, 40, 60, 80, 422, 50, 46, 5, 3,
            600, 50, 40, 80, 63, 523, 1540, 1250, 1, 2)

        }

        
      }
      // estado 3: F2 FUE PRESIONADO Y GUARDARA EN NEO4J PARA LUEGO CERRAR EL GRAFICO
      if (estadoSimulacion == 3) {
        println("guardando en neo4j")
        //aqui todos los de conexion
        println("borrando anteriores")
        Conexion.borrarViajes()
        println("insertando")
        Conexion.insertarViajes(viajes)
        print("Datos Guardados en Neo4j")
        System.exit(0);  //termina el programa
      }
      
      
      //estado 4: F1 FUE PRESIONADO Y BUSCA EN NEO4J ESTADO DE SIMULACION PARA CARGARLA
      if (estadoSimulacion == 4) {
        println("comprobando que exista simulacion guardada en neo4j")
        //en este momento esta en false porque se  debe cuadrar todo antes de permitirlo
        val datosCarga = Conexion.getViajes(vias)
        if (datosCarga.size>0) {
          println("si hay, cargando")
          vehiculos.clear()
          viajes.clear()
          viajes = datosCarga
          viajes.foreach(x => {
            vehiculos += x.v
          })
          serie._1.clear; serie._2.clear; serie._3.clear; serie._4.clear; serie._5.clear
          vehiculos.foreach(vehiculo1 => {
            if (vehiculo1.isInstanceOf[Carro]) { serie._1.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
            else if (vehiculo1.isInstanceOf[Bus]) { serie._2.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
            else if (vehiculo1.isInstanceOf[Moto]) { serie._3.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
            else if (vehiculo1.isInstanceOf[Camion]) { serie._4.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
            else if (vehiculo1.isInstanceOf[MotoTaxi]) { serie._5.add(vehiculo1.posicion.x, vehiculo1.posicion.y) }
          })
        } else {
          println("no hay")
        }
        estadoSimulacion = 0
      }

      Thread.sleep(tRefresh.toLong)
    }
  }

}
