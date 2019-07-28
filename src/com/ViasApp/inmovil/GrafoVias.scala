package inmovil
import com.ViasApp.movimiento._
import scalax.collection.mutable.Graph
import scalax.collection.edge.WLDiEdge

object GrafoVias {
  var _grafo = Graph[Interseccion, WLDiEdge]()
  def construir(vias: Array[Via]){
    vias.foreach(via_actual => {
      
      grafo.add(WLDiEdge(via_actual.origen, via_actual.fin)(via_actual.longitud, via_actual))
      if (via_actual.sentido.nombre == "dobleVia"){
        grafo.add(WLDiEdge(via_actual.fin, via_actual.origen)(via_actual.longitud, via_actual))
      }
    }
  )
  }
  
  def grafo = _grafo //getter

}
