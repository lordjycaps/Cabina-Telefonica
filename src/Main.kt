// Main.kt
package main

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

fun getConsolidatedReport(cabins: Map<Int, Cabin>): String {

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

    println("""
       
     Ingrese el número de cabinas telefonicas: " 
        
    """.trimIndent())
    val numberOfCabins = scanner.nextInt()

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
            |5. Salir
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
                    println("Cabina no encontrada, recuerda que son de 1 a ${numberOfCabins}")
                    continue
                }

                println("Ingrese el tipo de llamada ")
                println("(1. local")
                println("(2. larga distancia")
                println("(3. celular")
                val callTypeInput = scanner.next().uppercase()
                val callType = try {
                    CallType.valueOf(callTypeInput)
                } catch (e: IllegalArgumentException) {
                    println("Tipo de llamada inválido.")
                    continue
                }

                println("Ingrese la duración de la llamada en minutos:")
                val duration = scanner.nextInt()
                if (duration <= 0) {
                    println("Duración inválida.")
                    continue
                }

                cabin.registerCall(Call(callType, duration))
                println("Llamada registrada exitosamente.")
            }

            2 -> {
                println("Ingrese el numero de la cabina:")
                val cabinId = scanner.nextInt()
                val cabin = cabins[cabinId]

                if (cabin == null) {
                    println("Cabina no encontrada")
                } else {
                    println(cabin.getDetailedReport())
                }
            }

            3 -> {
                println(getConsolidatedReport(cabins))
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

            5 -> {
                println("Saliendo de la aplicación.")
                break
            }

            else -> println("Opción inválida.")
        }
    }
}
