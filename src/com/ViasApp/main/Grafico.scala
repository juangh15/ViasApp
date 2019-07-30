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
import java.awt.geom.Ellipse2D
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
    var dataset: XYSeriesCollection = new XYSeriesCollection()
    var dataset1: XYSeriesCollection = new XYSeriesCollection()
    var dataset2: XYSeriesCollection = new XYSeriesCollection()
    var dataset3: XYSeriesCollection = new XYSeriesCollection()
    var dataset4: XYSeriesCollection = new XYSeriesCollection()

    var carros = new XYSeries(true)
    var buses = new XYSeries(true)
    var motos = new XYSeries(true)
    var camiones = new XYSeries(true)
    var motoTaxis = new XYSeries(true)
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

    //Personalizando
    dataset.addSeries(carros)
    dataset1.addSeries(buses)
    dataset2.addSeries(motos)
    dataset3.addSeries(camiones)
    dataset4.addSeries(motoTaxis)
    //var renderer:XYLineAndShapeRenderer = new XYLineAndShapeRenderer()
    val chart = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset,
      org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false)
    val chart1 = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset1,
      org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false)
    val chart2 = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset2,
      org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false)
    val chart3 = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset3,
      org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false)

    val chart4 = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset4,
      org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false)

    val plot: XYPlot = chart.getXYPlot
    val plot1: XYPlot = chart1.getXYPlot
    val plot2: XYPlot = chart2.getXYPlot
    val plot3: XYPlot = chart3.getXYPlot
    val plot4: XYPlot = chart4.getXYPlot

    val renderer = plot.getRenderer
    val renderer1 = plot1.getRenderer
    val renderer2 = plot2.getRenderer
    val renderer3 = plot3.getRenderer
    val renderer4 = plot4.getRenderer

    renderer.setBaseShape(new Ellipse2D.Double(-4.0, -4.0, 12.0, 16.0))
    renderer.setSeriesVisibleInLegend(2, false)

    var ventana = new ChartFrame("ViasApp", chart)
    ventana.setVisible(true);
    ventana.setSize(800, 600);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
