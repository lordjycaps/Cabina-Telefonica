package models

class Cabin(val id: Int) {
    //al utilizar las lista mutableList nos permite almacenar valores los cuales nos va a servir para poder sacar reporte de
    //las llamadas
    private val calls = mutableListOf<Call>()

    fun registerCall(call: Call) {
        calls.add(call)
    }
    fun getTotalCalls(): Int = calls.size

    //la funcion sumOf nos permite realizar sumas de un elementos de array, estos deben de ser Int o Double
    fun getTotalDuration(): Int = calls.sumOf { it.durationInMinutes }

    fun getTotalCost(): Double = calls.sumOf { it.cost }

    //la funcion clear(), Elimina todos los elementos de esta colección.
    fun reset() {
        calls.clear()
    }
    //esta funcion nos muestra en terminal los valores que van a estar alamacenados en el mutableListOf
    fun getDetailedReport(): String {
        return """
            |--- Reporte de Cabina $id ---
            |Número de llamadas realizadas: ${getTotalCalls()}
            |Duración total de las llamadas: ${getTotalDuration()} minutos
            |Costo total de las llamadas: $${"%.2f".format(getTotalCost())} pesos
        """.trimMargin()
    }
}
