# ViasApp

### Integrantes del equipo:

#### Alejandro Mejía Osorio
#### Andres Felipe Gaviria Lora
#### Darwin Stiven Herrera Cartagena
#### Juan Guillermo Sarrias Escudero

_________________________________________________________________
### Para Ejecutar: 
#### - Si se desea, cambiar en la carpeta Resources los parámetros de la simulación (JSON).
#### - Tomar todos los comandos de CREAR_Vias_Neo4J.txt en la carpeta Resources y crear la base de datos en Neo4J agregandolos.
#### - Entrar a com.ViasApp.main.Main.scala para cambiar la ruta correspondiente a la carpeta resources según el ordenador actual, para los parametros y  para los resultados. 
#### Cambiar también en el Main la url, usuario y contraseña de la base de datos.
#### - Ejecutar el proyecto en com.ViasApp.main.Main.scala para llamar el run de simulación, asegurandose de tener un grafo activado en Neo4J.
#### - Al finalizar una Simulación, se guardarán los resultados (JSON) en la carpeta resources.

_______________________________________________________________

### Instrucciones:
#### Luego de correr el programa, la simulacion genera vehiculos aleatorios, al presionar:
#### - F5 -> inicia y mantiene el flujo de vehiculos hasta finalizar un recorrido completo de cada uno.
#### - F6 -> reinicia la simulacion con nuevos vehiculos aleatorios.
#### - F2 -> detiene y guarda la simulacion ACTUAL en Neo4J borrando los parametros guardados con anterioridad. Luego cierra el programa.
#### - F1 -> verifica si hay datos guardados en Neo4j y si los hay, detiene la simulacion actual y reemplaza los valores traídos de Neo.
#### - Cuando todos los vehiculos terminan su recorrido, se guardan los resultados.json en resources y se queda a la espera de una nueva pulsación de F6 - F5.
