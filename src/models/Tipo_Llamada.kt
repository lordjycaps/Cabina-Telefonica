package models

//Con enum class podremos definir un numero finito de valores en este caso la definicion de los tipos de llamada que son tres, esto nos facilita poder
//tomar la informacion necesaria, en este caso el tipo de llamada y su respectivo valor.
enum class CallType(val ratePerMinute: Double) {

    LOCAL(50.0),
    LONG_DISTANCE(350.0),
    CELLULAR(150.0)
}
