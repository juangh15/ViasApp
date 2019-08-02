package com.ViasApp.inmovil
import com.ViasApp.movimiento._
import scalax.collection.mutable.Graph
import scalax.collection.edge.WLDiEdge

object GrafoVias {
  
  var _grafo = Graph[Interseccion, WLDiEdge]()
  def grafo = _grafo //getter


  def construir(vias: Array[Via]) {
    vias.foreach(via_actual => {

      grafo.add(WLDiEdge(via_actual.origen, via_actual.fin)(via_actual.longitud, via_actual))
      if (via_actual.sentido.nombre == "dobleVia") {
        grafo.add(WLDiEdge(via_actual.fin, via_actual.origen)(via_actual.longitud, via_actual))
      }
    })
  }

  def trazarRuta(inicio: Interseccion, fin: Interseccion, k: Graph[Interseccion, WLDiEdge]=GrafoVias.grafo): k.Path = {
    def nodo(outer: Interseccion): k.NodeT = k get outer

    val n1: k.NodeT = nodo(inicio)
    val n2: k.NodeT = nodo(fin)
    val z = (n1) shortestPathTo (n2)
    return z.get

  }
}
