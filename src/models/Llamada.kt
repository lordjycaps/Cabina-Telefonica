package models

data class Call(
    //en este apartado con data class podremos utilizar la infromacion de mejor manera, como podemos ver traemos la clase CallType la cual esta
    //dentro de un enum class y seleccionar el tipo de llamada
    val type: CallType,
    val durationInMinutes: Int
) {
    val cost: Double
        get() = type.ratePerMinute * durationInMinutes
}
