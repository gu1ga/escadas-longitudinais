package GeometriaAnalitica

import android.graphics.PointF
import java.lang.Math.*
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.pow

class Segmento(xInicial: Float, yInicial: Float, xFinal: Float, yFinal: Float) {

    companion object{
        fun MutableList<Segmento>.addSegmentoRelativo(dx: Float, dy: Float){
            this.add(Segmento(last().xFinal, last().yFinal, last().xFinal + dx, last().yFinal + dy))
        }
    }

    var xInicial: Float = 0f
    var yInicial: Float = 0f
    var xFinal: Float = 0f
    var yFinal: Float = 0f
    val xMedio = (xInicial + xFinal)*0.5f
    val yMedio = (yInicial + yFinal)*0.5f
    var deltaX: Float = 0f
    var deltaY: Float = 0f


    init {
        this.xInicial = xInicial
        this.yInicial = yInicial
        this.xFinal = xFinal
        this.yFinal = yFinal
        this.deltaX = xFinal - xInicial
        this.deltaY = yFinal - yInicial
    }

    val comprimento: Double get() = comprimento()
    val angulo: Float get() = angulo()
    val pontoInicial: PointF get() = PointF(xInicial, yInicial)
    val pontoFinal: PointF get() = PointF(xFinal, yFinal)
    val pontoMedio: PointF get() = PointF(xMedio, yMedio)

    private fun comprimento(): Double{
        return sqrt((xFinal - xInicial).pow(2) + (yFinal - yInicial).pow(2).toDouble())
    }

    private fun angulo(): Float{

        return if(deltaX == 0f) 0f else toDegrees(atan(deltaY/deltaX).toDouble()).toFloat()
    }

    override fun toString(): String {

        val string = "Segmento:" +
                "\nPonto Inicial: $pontoInicial" +
                "\nPonto Final: $pontoFinal" +
                "\nPonto Médio: $pontoMedio" +
                "\nComprimento: $comprimento"

        return string
    }
}