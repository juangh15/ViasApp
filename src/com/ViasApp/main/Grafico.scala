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
import org.jfree.util.ShapeUtilities
import java.awt.geom.Rectangle2D
import java.awt.geom.Ellipse2D

class Grafico {

  def graficarVias(vias: Array[Via])= {
    //Guillermo's code
    
    //Dataset
    val dataset: XYSeriesCollection = new XYSeriesCollection()
    //Render para personalizar
    val renderer: XYLineAndShapeRenderer = new XYLineAndShapeRenderer()
    //Ancho de las vias, unica instancia y es el mism para todas
    val stroke = new BasicStroke(4f)

    val conjunto_intersecciones = scalax.collection.mutable.ArraySet[Interseccion]()
    var cont:Int=0
    //Recorre las vias para graficarlas
    
    for(i <- vias){
      var viaserie = new XYSeries(cont)

      viaserie.add(i.origen.x, i.origen.y)
      viaserie.add(i.fin.x, i.fin.y)

      conjunto_intersecciones.add(i.origen)
      conjunto_intersecciones.add(i.fin)
      renderer.setSeriesPaint(cont, Color.gray)
      renderer.setSeriesStroke(cont, stroke)
      dataset.addSeries(viaserie)
      cont+=1
    }
    
    
    
    
    
    conjunto_intersecciones.foreach(intersecccion_actual => {

      //Añade textos en las posiciones iniciales
      val nuevo_texto = new XYTextAnnotation(intersecccion_actual.nombre, intersecccion_actual.x, intersecccion_actual.y)
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


    //Se crea la ventana
//    var ventana = new ChartFrame("ViasApp", xylinechart);

    //esto es para que el grafico reconozca las teclas presionadas
//    val teclas = new KeyListener() {
//      def keyPressed(e: KeyEvent) {
//        if (e.getKeyCode == KeyEvent.VK_F5)
//          println("f5")
//        //aqui creo que se llama al Simulacion.run y ya eso se queda en ciclo
//      }
//
//      def keyReleased(e: KeyEvent) {
//      }
//
//      def keyTyped(e: KeyEvent) {
//      }
//    }
//    ventana.addKeyListener(teclas)
    (dataset,renderer,vias.size-1)
  }

  def graficarVehiculos(vehiculos: Array[Vehiculo],vias:Array[Via]):Unit= {
    //Darwin´s code
    
    //Colecciones de "Arrays" para las series de puntos
    //val dataset: XYSeriesCollection = new XYSeriesCollection()

    
    
    
    val g=new Grafico
    val (dataset,renderer,ultimo) =g.graficarVias(vias)
    
    
    //Series de los puntos de cada tipo de vehiculo
    val carros = new XYSeries("carros")
    val buses = new XYSeries("buses")
    val motos = new XYSeries("motos")
    val camiones = new XYSeries("camiones")
    val motoTaxis = new XYSeries("mototax")
    
    
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
    
    //creando cada Jfreechart que contiene un plot y unas letricas(que borramos)
    val chart = ChartFactory.createScatterPlot(
      "",
      "",
      "",
      dataset,
      org.jfree.chart.plot.PlotOrientation.VERTICAL, false, false, false)
    //grafico 
      
    val plot: XYPlot = chart.getXYPlot
    
    //Estilos para todos que luego se especifican para los carritos
    renderer.setBaseLinesVisible(true)
    renderer.setBaseShapesVisible(false)
    for(r <- 1 to 5) {
      renderer.setSeriesShapesVisible(ultimo+r,true) 
      renderer.setSeriesLinesVisible(ultimo+r,false)
    }

    
    
    
    //estilos del carro
    renderer.setSeriesShape(ultimo+1, ShapeUtilities.createRegularCross(2, 6))
    renderer.setSeriesPaint(ultimo+1, Color.getHSBColor(243 , 85,84))
    //estilo del bus
    renderer.setSeriesShape(ultimo+2, ShapeUtilities.createDiamond(5))
    renderer.setSeriesPaint(ultimo+2, Color.BLACK)
    //estilo moto
    renderer.setSeriesShape(ultimo+3, ShapeUtilities.createRegularCross(5,2))
    renderer.setSeriesPaint(ultimo+3, Color.getHSBColor(359,100,100))
    //estilo camion
    renderer.setSeriesShape(ultimo+4, ShapeUtilities.rotateShape(ShapeUtilities.createRegularCross(2, 3), 180, 0, 0))
    renderer.setSeriesPaint(ultimo+4, Color.getHSBColor(117,100,64))
    //estilo mototaxi
    renderer.setSeriesShape(ultimo+5, ShapeUtilities.createDownTriangle(4))
    renderer.setSeriesPaint(ultimo+5, Color.getHSBColor(59,83,100))
    
    
    plot.setBackgroundPaint(Color.WHITE) //color de fondo
    plot.getDomainAxis.setTickLabelsVisible(false) //desaparecen los numeros y rectas
    plot.getRangeAxis.setTickLabelsVisible(false)
    plot.getDomainAxis.setTickMarksVisible(false) //desaparecen las marcas pequeñas
    plot.getRangeAxis.setTickMarksVisible(false)
    
    
    
    //Esto manda los estilos
    plot.setRenderer(renderer)
    
    
    val frame = new ChartFrame("ViasApp",chart)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame.pack()
    frame.setVisible(true)
  }
}
