package com.ViasApp.inmovil
import com.ViasApp.movimiento._
import scalax.collection.mutable.Graph
import scalax.collection.edge.WLDiEdge
import scala.collection.mutable.Queue

object GrafoVias {
  
  var _grafo = Graph[Interseccion, WLDiEdge]()
  def grafo = _grafo //getter


  def construir(vias: Array[Via]) {
    vias.foreach(via_actual => {

      grafo.add(WLDiEdge(via_actual.origen, via_actual.fin)(via_actual.longitud, via_actual))

    })
  }

  def trazarRuta(inicio: Interseccion, fin: Interseccion, k: Graph[Interseccion, WLDiEdge]=GrafoVias.grafo): k.Path = {
    def nodo(outer: Interseccion): k.NodeT = k get outer
    println("intenta obtener del grafo: "+inicio)
    val n1: k.NodeT = nodo(inicio)
    println("intenta obtener del grafo: "+fin)
    val n2: k.NodeT = nodo(fin)
    
    val z = (n1) shortestPathTo (n2)
    
    println("generando la ruta en grafovias;")
    z.getOrElse((n1 shortestPathTo n1).get)


  }
}
