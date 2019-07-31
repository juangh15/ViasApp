package com.ViasApp.main
import com.ViasApp.inmovil._
import com.ViasApp.movil._
import org.jfree.data.xy._
import org.jfree.chart.JFreeChart
import org.jfree.chart.ChartFactory
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import java.awt.Color
import java.awt.BasicStroke
import org.jfree.chart.ChartPanel
import javax.swing.JFrame
import com.ViasApp.movimiento._
import org.jfree.chart.ChartFrame
import java.awt.Label
import org.jfree.chart.annotations.XYTextAnnotation
import java.awt.Paint
import org.jfree.chart.renderer.xy.XYItemRenderer
import org.jfree.chart.renderer.xy.XYDotRenderer
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Grafico {

  def graficarVias(vias: Array[Via]) {
    //Guillermo's code

    //Dataset
    var dataset: XYSeriesCollection = new XYSeriesCollection()
    //Render para personalizar
    var renderer: XYLineAndShapeRenderer = new XYLineAndShapeRenderer()
    //Contador para darle un id y personalizar cada via
    var cont = 0
    //Ancho de las vias, unica instancia y es el mism para todas
    val stroke = new BasicStroke(4f)

    var conjunto_intersecciones = scalax.collection.mutable.ArraySet[Interseccion]()

    //Recorre las vias para graficarlas
    vias.foreach(via_actual => {

      var viaserie = new XYSeries(cont)

      viaserie.add(via_actual.origen.x, via_actual.origen.y)
      viaserie.add(via_actual.fin.x, via_actual.fin.y)

      conjunto_intersecciones.add(via_actual.origen)
      conjunto_intersecciones.add(via_actual.fin)

      dataset.addSeries(viaserie)
      renderer.setSeriesPaint(cont, Color.gray)
      renderer.setSeriesStroke(cont, stroke)

      cont += 1

    })

    conjunto_intersecciones.foreach(intersecccion_actual => {

      //Añade textos en las posiciones iniciales
      var nuevo_texto = new XYTextAnnotation(intersecccion_actual.nombre, intersecccion_actual.x, intersecccion_actual.y)
      if (intersecccion_actual.x % 9 == 0) {
        nuevo_texto.setPaint(Color.YELLOW)
      } else if (intersecccion_actual.x % 7 == 0) {
        nuevo_texto.setPaint(Color.BLUE)
      } else if (intersecccion_actual.x % 8 == 0) {
        nuevo_texto.setPaint(Color.cyan)
      } else if (intersecccion_actual.x % 4 == 0) {
        nuevo_texto.setPaint(Color.GREEN)
      } else {
        nuevo_texto.setPaint(Color.RED)
      }
      renderer.addAnnotation(nuevo_texto)
    })

    //crea el grafico con el dataset enviado

    var xylinechart: JFreeChart = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset,
      PlotOrientation.VERTICAL, false, false, false)

    var plot: XYPlot = xylinechart.getXYPlot() //obtiene el grafico solo
    //personaliza el grafico anterior
    plot.setBackgroundPaint(Color.WHITE) //color de fondo
    plot.getDomainAxis.setTickLabelsVisible(false) //desaparecen los numeros y rectas
    plot.getRangeAxis.setTickLabelsVisible(false)
    plot.getDomainAxis.setTickMarksVisible(false) //desaparecen las marcas pequeñas
    plot.getRangeAxis.setTickMarksVisible(false)

    //esto le manda el renderizado a la grafica
    plot.setRenderer(renderer);

    //Se crea la ventana
    var ventana = new ChartFrame("ViasApp", xylinechart);

    //esto es para que el grafico reconozca las teclas presionadas
    val teclas = new KeyListener() {
      def keyPressed(e: KeyEvent) {
        if (e.getKeyCode == KeyEvent.VK_F5)
          println("f5")
        //aqui creo que se llama al Simulacion.run y ya eso se queda en ciclo
      }

      def keyReleased(e: KeyEvent) {
      }

      def keyTyped(e: KeyEvent) {
      }
    }
    ventana.addKeyListener(teclas)
    ventana.setVisible(true);
    ventana.setSize(800, 600);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  def graficarVehiculos(vehiculos: Array[Vehiculo]) {
    //Darwin´s code
    
    //Colecciones de "Arrays" para las series de puntos
    var dataset: XYSeriesCollection = new XYSeriesCollection()

    //Series de los puntos de cada tipo de vehiculo
    var carros = new XYSeries(true)
    var buses = new XYSeries(true)
    var motos = new XYSeries(true)
    var camiones = new XYSeries(true)
    var motoTaxis = new XYSeries(true)
    
    
    //filtro
    for (i <- vehiculos) {
      if (i.isInstanceOf[Carro]) {
        carros.add(i.pos.x, i.pos.y)
      } else if (i.isInstanceOf[Moto]) {
        motos.add(i.pos.x, i.pos.y)
      } else if (i.isInstanceOf[Bus]) {
        buses.add(i.pos.x, i.pos.y)
      } else if (i.isInstanceOf[Camion]) {
        camiones.add(i.pos.x, i.pos.y)
      } else if (i.isInstanceOf[MotoTaxi]) {
        motoTaxis.add(i.pos.x, i.pos.y)
      }
    }

    //agregando las series en cada coleccion
    dataset.addSeries(carros)
    dataset.addSeries(buses)
    dataset.addSeries(motos)
    dataset.addSeries(camiones)
    dataset.addSeries(motoTaxis)
    
    //creando cada Jfreechart que contiene un plot y unas letricas
    val chart = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset,
      org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false)
    //cada grafico de cada jfreechart
    val plot: XYPlot = chart.getXYPlot
    
    val renderer:XYItemRenderer = plot.getRenderer
    renderer.setBaseSeriesVisible(true)
    
    
    //estilos del carro
    renderer.setSeriesShape(0, cuerpocarro)
    renderer.setSeriesPaint(0, Color.getHSBColor(243 , 85,84))
    //estilo del bus
    renderer.setSeriesShape(1, cuerpobus)
    renderer.setSeriesPaint(0, Color.getHSBColor(166,92,87))
    //estilo moto
    renderer.setSeriesShape(2, cuerpomoto)
    renderer.setSeriesPaint(0, Color.getHSBColor(359,100,100))
    //estilo camion
    renderer.setSeriesShape(3, cuerpocamion)
    renderer.setSeriesPaint(0, Color.getHSBColor(117,100,64))
    //estilo mototaxi
    renderer.setSeriesShape(4, cuerpomototax)
    renderer.setSeriesPaint(0, Color.getHSBColor(59,83,100))
    

    renderer.setBasePaint(Color.red)

    
    
    //Alerta!
    //creacion de ventana solo para ejemplos pero en el proyecto final se borra
    var ventana = new ChartFrame("ViasApp", chart)
    ventana.setVisible(true);
    ventana.setSize(800, 600);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    //cuerpos marikas no son necesarios para el codigo
    val cuerpocarro = ShapeUtilities.createRegularCross(2, 3)
    val cuerpomoto = ShapeUtilities.createDiamond(3)
    val cuerpobus = ShapeUtilities.createRegularCross(3,2)
    val cuerpocamion = ShapeUtilities.rotateShape(ShapeUtilities.createRegularCross(2, 3), 180, 0, 0)
    val cuerpomototax = ShapeUtilities.createDownTriangle(4)
  }

}
