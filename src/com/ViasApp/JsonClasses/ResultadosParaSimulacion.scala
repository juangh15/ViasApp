package com.ViasApp.JsonClasses

class ResultadosParaSimulacion(
    total:Int,carros:Int,motos:Int,buses:Int,camiones:Int,motoTaxis:Int,
    vias:Int,intersecciones:Int,viasUnSentido:Int,viasDobleSentido:Int,velocidadMinima:Int,velocidadMaxima:Int,longitudPromedio:Int,
    promedioOrigen:Int,promedioDestino:Int,sinOrigen:Int,sinDestino:Int,
    simulacion:Int,realidad:Int,
    minima:Int,maxima:Int,promedio:Int,
    minima2:Int,maxima2:Int,promedio2:Int,
    ) {

  Json.escribirArchivo("resultados.json",
    new InputResultadosSimulacion(new ResultadosSimulacion(
    new Vehiculos(total,carros,motos,buses,camiones,motoTaxis),
    new MallaVial(vias,intersecciones,viasUnSentido,viasDobleSentido,velocidadMinima,velocidadMaxima,longitudPromedio,
        new VehiculosEnInterseccion(promedioOrigen,promedioDestino,sinOrigen,sinDestino)),
    new Tiempos(simulacion,realidad),
    new Velocidades(minima,maxima,promedio),
    new Distancias(minima2,maxima2,promedio2))))
}