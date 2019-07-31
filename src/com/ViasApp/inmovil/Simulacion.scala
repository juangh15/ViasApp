package com.ViasApp.main
import scala.collection.mutable.ArrayBuffer
import com.ViasApp.movimiento._
import com.ViasApp.inmovil._
import com.ViasApp.movil._

object Simulacion  extends Runnable {
  
  var t : Int = 0
  
  

  
  
  
  
  
  
  // lectura variables del json
  val json = new Json
  val dt = json.dt
  val tRefresh = json.tRefresh
  val minVehiculos = json.minVehiculos
  val maxVehiculos = json.maxVehiculos
  val minVelocidad = json.minVelocidad
  val maxVelocidad = json.maxVelocidad
  
  val totalVehiculos = (math.random()*(maxVehiculos-minVehiculos))+minVehiculos
  val carros = math.random()*totalVehiculos
  val motos = math.random()*(totalVehiculos-carros)+carros
  //Así hasta motoTaxis
  val r.scala.util.Random
  var inicio = new Punto()
  var final = new Punto()
  for(i <- carros){
    //inicio=//Aqui tiene que darle la interseccion de origen
    //final= //Aqui le tiene que asignar la final
    
    //Tiene que generar la lista que llegue más rapido al final de cada carro y no se si guardarlo en una matriz
    
    vehiculos += new Carro(s"${r.nextPrintableChar}${r.nextPrintableChar}${r.nextPrintableChar}${r.nextInt}${r.nextInt}${r.nextInt}",(math.random()*(maxVelocidad-minVelocidad)+minVelocidad),inicio)
  }
  //Así hasta moto taxi

    
    var g = GrafoVias.construir(vias)//Devuelve graph del mapa
    val grafico = new Grafico()//Tal vez este mal escrito
    val ventana = grafico.graficarVias(vias) //Devuelve un Jframe con el dibujo del mapa(JFrame es una ventana que contiene dibujo titulo y otras cosas)

    //val chart = ventana.(fnqueextraeelfreechart)

    val plot: XYPlot = ventana.getXYPlot()//Si da error hay que buscar una funcion que extraiga el Jfreechart
  
  
 def run() {
   while (true) {
     vehiculos.foreach(_.mover(dt))
     t += dt
     val imagen = grafico.graficarVehiculos(vehiculos)
     Thread.sleep(tRefresh)
 }
}
}
