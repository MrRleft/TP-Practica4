# TP-Practica4
Practica4 de TP por Rubén y Elena

Objetivo: Programación Orientada a Objetos, Genéricos y Colecciones.
Introducción
Una simulación permite ejecutar un modelo en un ordenador para poder observar su
comportamiento y aplicar este comportamiento a la vida real. Las prácticas del segundo
cuatrimestre consistirán en construir un simulador de tráfico, que modelará vehículos,
carreteras y cruces.
En esta práctica construiremos el simulador utilizando entrada/salida estándar (ficheros
y/o consola). El simulador contendrá una colección de objetos simulados (vehículos y
carreteras conectadas a través de cruces); otra colección de eventos a ejecutar; y un contador
de tiempo que se incrementará en cada paso de la simulación. Un paso de la simulación
consiste en realizar las siguientes operaciones:
1. Procesar los eventos. En particular estos eventos pueden añadir y/o alterar el estado
de los objetos simulados;
2. Avanzar el estado actual de los objetos simulados atendiendo a su comportamiento.
Por ejemplo, los vehículos avanzarán en su carretera correspondiente mientras dicha
carretera finalice en un cruce con un semáforo en verde. Si el semáforo está en rojo,
ningún vehículo de esa carretera podrá avanzar.
3. Mostrar el estado actual de los objetos simulados.
Los eventos se leen de un fichero de texto antes de que la simulación comience. Una
vez leídos, se inicia la simulación, que se ejecutará un número determinado de pasos y, en
cada paso, se mostrará el estado de la simulación, bien en la consola o en un fichero de
texto
