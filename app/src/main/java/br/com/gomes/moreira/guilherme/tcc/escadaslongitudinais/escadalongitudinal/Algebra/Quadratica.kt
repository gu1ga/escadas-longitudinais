package Algebra

import kotlin.math.*

class Quadratica(A: Double, B: Double, C: Double, dominio: Array<Double> = arrayOf(Double.MIN_VALUE, Double.MAX_VALUE)) {

    var A: Double
    var B: Double
    var C: Double
    var dominio: Array<Double>
    var Xmax: Double = 0.0
    var Ymax: Double = 0.0
    var delta: Double = 0.0
    var raiz1: Double = 0.0
    var raiz2: Double = 0.0

    init{

        this.A = A
        this.B = B
        this.C = C
        this.dominio = dominio
        this.definirDelta()
        this.definirXmax()
        this.definirYmax()

    }

    fun Y(x: Double) : Double{

        return this.A*x.pow(2) + this.B*x + this.C

    }

    private fun definirXmax() {

        this.Xmax = -B/(2.0*A)

    }

    private fun definirYmax() {

        val testeDominio: Boolean = (this.Xmax >= this.dominio[0]) && this.Xmax <= this.dominio[1]

        when(testeDominio){

            true -> this.Ymax = this.Y(this.Xmax)
            false -> this.Ymax = max(this.Y(this.dominio[0]), this.Y(this.dominio[1]))

        }


    }

    private fun definirDelta(){

        this.delta = this.B.pow(2) - 4.0*this.A*this.C

    }

    private fun definirRaizes(){

        if(this.delta >= 0.0){


                this.raiz1 = (-this.B - this.delta.pow(0.5))/(2*this.A)
                this.raiz1 = (-this.B + this.delta.pow(0.5))/(2*this.A)


        }

    }

    fun definirA(A: Double ){

        this.A = A

    }

    fun definirB(B: Double){

        this.B = B

    }

    fun definirC(C: Double){

        this.C = C

    }

    fun matrizCoord(eixo: String, basePointX: Double, basePointY: Double, passo: Double, angulo: Double = 0.0, xInicial: Double = dominio[0], xFinal: Double = dominio[1], xScale: Double = 1.0, yScale: Double = 1.0) : DoubleArray{

        val cosseno = cos(angulo)
        val seno = sin(angulo)
        val tamanhoIntervalo = xFinal - xInicial
        val trechos: Int = (tamanhoIntervalo/passo).toInt()
        val matrizX = DoubleArray(trechos)
        val matrizY = DoubleArray(trechos)

        for(indice in 0..(trechos - 1)){

            val xAtual = basePointX + (passo*indice)*xScale
            val yAtual = basePointY + (Y(xInicial + passo*indice))*yScale

            matrizX[indice] = if(angulo != 0.0) {basePointX + (xAtual - basePointX)*cosseno - (yAtual - basePointY)*seno} else {xAtual}
            matrizY[indice] = if(angulo != 0.0) {basePointY + (xAtual - basePointX)*seno + (yAtual - basePointY)*cosseno} else {yAtual}

        }

        return when(eixo){

            x -> matrizX
            y -> matrizY

            else -> DoubleArray(0)

        }

    }

    override fun toString() : String{

        return "Equação Quadrática: ${this.A}x² + ${this.B}x + ${this.C}"

    }

    companion object {

        const val x: String = "x"

        const val y: String = "y"

        fun calcularRaizes(a: Double, b: Double, c: Double): List<Double> {

            val delta = b.pow(2) - 4 * a * c

            return when {
                delta > 0 -> listOf((-b + sqrt(delta)) / (2 * a), (-b - sqrt(delta)) / (2 * a))
                delta == 0.toDouble() -> listOf(-b / (2 * a))
                delta < 0 -> listOf()
                else -> listOf(0.0, 0.0, 0.0)

            }

        }

    }

}



