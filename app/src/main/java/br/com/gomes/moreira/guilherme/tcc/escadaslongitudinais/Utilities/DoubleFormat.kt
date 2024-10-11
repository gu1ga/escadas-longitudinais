package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities
import java.lang.Math.floor
import java.lang.Math.round
import java.util.*

fun Double.format(digits: Int) = "%.${digits}f".format(Locale.ROOT,this)

fun String.equalsOr(vararg values: String) : Boolean {

    val check =  BooleanArray(values.size)
    var result: Boolean = false

    values.forEachIndexed { index, value ->
        check[index] = this == value
    }

    check.forEach {
        result = it.or(result)
    }

    return  result

}

fun Double.roundToMultipleOf(divisor: Double) = if(this - floor(this)*divisor in 0.25..0.75) floor(this/divisor)*divisor else round(this/divisor).toInt()*divisor


