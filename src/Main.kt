// Main.kt
package main

import kotlin.random.Random
import models.Cabin
import models.Call
import models.CallType
import java.util.Scanner

//fun main() {
//    val cabins = mutableMapOf<Int, Cabin>()
//    val cabinCount = 5 // Número de cabinas

    // Inicializar cabinas
//    for (i in 1..cabinCount) {
//        cabins[i] = Cabin(i)
//    }

    // Registrar una llamada en la cabina 1
//    val call = Call(CallType.LOCAL, 10) // Llamada local de 10 minutos
//    cabins[1]?.registerCall(call)

//    println(cabins[1]?.getDetailedReport())
//}

fun ObtenerInfoCabinas(cabins: Map<Int, Cabin>): String {

    val totalCalls = cabins.values.sumOf { it.getTotalCalls() }
    val totalDuration = cabins.values.sumOf { it.getTotalDuration() }
    val totalCost = cabins.values.sumOf { it.getTotalCost() }
    val averageCostPerMinute = if (totalDuration > 0) totalCost / totalDuration else 0.0

    return """
        |--- Reporte Consolidado ---
        |Número total de llamadas realizadas: $totalCalls
        |Duración total de llamadas: $totalDuration minutos
        |Costo total de las llamadas: $${"%.2f".format(totalCost)} pesos
        |Costo promedio por minuto: $${"%.2f".format(averageCostPerMinute)} pesos/minuto
    """.trimMargin()
}

fun main() {
    val scanner = Scanner(System.`in`)
    val cabins = mutableMapOf<Int, Cabin>()
    var numberOfCabins = 1
    val duration = Random.nextInt(1, 101)  // Genera un número aleatorio entre 1 y 100
    for (i in 1..numberOfCabins) {
        cabins[i] = Cabin(i)
    }
    while (true) {
        println(
            """
            |-------------------------------
            |------------- Menú ------------
            |-------------------------------
            |Numero de cabinas disponibles: ${numberOfCabins}
            |1. Registrar una llamada
            |2. Mostrar información de una cabina
            |3. Mostrar consolidado total
            |4. Reiniciar una cabina
            |5. Crear Cabina
            |6. Salir
            |Seleccione una opción:
            |
            |-------------------------------
            """.trimMargin()
        )

        when (scanner.nextInt()) {
            1 -> {
                println("Ingrese el numero de la cabina:")
                val cabinId = scanner.nextInt()
                val cabin = cabins[cabinId]

                if (cabin == null) {
                    println("Cabina no encontrada.")
                    continue
                }

                println("Ingrese el tipo de llamada (LOCAL, LONG_DISTANCE, CELLULAR):")
                val callTypeInput = scanner.next().uppercase();

                val callType = try {
                    println(callTypeInput);
                    CallType.valueOf(callTypeInput)
                } catch (e: IllegalArgumentException) {
                    println(callTypeInput);
                    println("Tipo de llamada inválido.")
                    continue
                }
//cambiar este apartado y que el numero registrado sea por numero aleatorio 1-100--------------------------
                println("Duración de la llamada en minutos: $duration")
                if (duration <= 0) {
                    println("Duración inválida.")
                    continue
                }

                cabin.registerCall(Call(callType, duration))
                println("\n")
                println("Llamada registrada exitosamente.")
                println("\n")
            }

            2 -> {
                println("Ingrese el numero de la cabina:")
                val cabinId = scanner.nextInt()
                val cabin = cabins[cabinId]

                if (cabin == null) {
                    println("Cabina no encontrada.")
                } else {
                    println(cabin.getDetailedReport())
                }
            }

            3 -> {
                println(ObtenerInfoCabinas(cabins))
            }

            4 -> {
                println("Ingrese el numero de la cabina:")
                val cabinId = scanner.nextInt()
                val cabin = cabins[cabinId]

                if (cabin == null) {
                    println("Cabina no encontrada.")
                } else {
                    cabin.reset()
                    println("Cabina reiniciada exitosamente.")
                }
            }

            5->{
                println("¿Cuántas cabinas quiere crear?")
                val addCabin = scanner.nextInt()
                numberOfCabins += addCabin
                for (i in 1..numberOfCabins) {
                    cabins[i] = Cabin(i)
                }
            }
            6 -> {
                println("Saliendo de la aplicación.")
                break
            }

            else -> println("Opción inválida.")
        }
    }
}
