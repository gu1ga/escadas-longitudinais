package Algebra

import kotlin.math.*

class Quartica(A: Double, B: Double, C: Double, D: Double, E: Double, dominio: Array<Double> = arrayOf(Double.MIN_VALUE, Double.MAX_VALUE)) {

    var A: Double
    var B: Double
    var C: Double
    var D: Double
    var E: Double
    var dominio: Array<Double>
    var xMax: Double = 0.0
    var yMax: Double = 0.0
    val derivada: Cubica

    init{

        this.A = A
        this.B = B
        this.C = C
        this.D = D
        this.E = E
        this.dominio = dominio
        this.derivada = Cubica(4.0*A, 3.0*B, 2.0*C, D, this.dominio)

        this.definirXmax()
        this.definirYmax()

    }

    fun Y(x: Double) : Double{

        return this.A*x.pow(4) + this.B*x.pow(3) + this.C*x.pow(2) + this.D*x + this.E

    }

    fun Ycos(x: Double, angulo: Double) : Double {

        return this.Y(x)*cos(angulo)

    }

    fun Ysen(x: Double, angulo: Double) : Double{

        return this.Y(x)*sin(angulo)

    }

    fun definirXmax(){

        val pontoMedio = 0.5*(this.dominio[0] + this.dominio[1])
        val xMaxNewtonInicial = newtonRaphson(dominio[0])
        val xMaxNewtonMedio   = newtonRaphson(pontoMedio)
        val xMaxNewtonFinal   = newtonRaphson(dominio[1])

        val xPossiveis = arrayOf(dominio[0], dominio[1], xMaxNewtonInicial, xMaxNewtonMedio, xMaxNewtonFinal)
        var xMax = xPossiveis[0]

        for (x in xPossiveis){

            if(x >= this.dominio[0] && x <= this.dominio[1]){

                if(this.Y(x) < this.Y(xMax)){

                    xMax = x

                }

            }

        }

        this.xMax = xMax

        /*println(this)
        println("Ponto Médio: $pontoMedio")
        println("Y'($pontoMedio) = ${this.derivada.Y(pontoMedio)}")
        println("x Inicial Newton Rhapson: $xMaxNewtonInicial")
        println("x Médio Newton Rhapson: $xMaxNewtonMedio")
        println("x Máximo Newton Rhapson: ${xMaxNewtonFinal}")
        println("x Máximo Escolhido: $xMax")*/
    }

    fun definirYmax(){

        this.yMax = Y(xMax)

    }

    fun yMaxCos(angulo: Double) : Double{

        return this.yMax*cos(angulo)

    }

    private fun newtonRaphson(xInicial: Double = 1.0, erro: Double =0.00000001) : Double {

        var primeiraEstimativa = xInicial

        if (this.derivada.derivada.Y(xInicial) == 0.toDouble()) {

            primeiraEstimativa = xInicial + erro

        }

        var h: Double = this.derivada.Y(primeiraEstimativa) / this.derivada.derivada.Y(primeiraEstimativa)

        var x: Double = primeiraEstimativa

        while (abs(h) > erro) {

            h = this.derivada.Y(x) / this.derivada.derivada.Y(x)

            x = x - h


        }

        return x


    }

    fun matrizCoord(eixo: String, basePointX: Double, basePointY: Double, passo: Double, angulo: Double = 0.0, xInicial: Double = dominio[0], xFinal: Double = dominio[1], xScale: Double = 1.0, yScale: Double = 1.0, rotatePointX: Double = basePointX, rotatePointY: Double = basePointY) : DoubleArray{

        val cosseno = cos(angulo)
        val seno = sin(angulo)
        val tamanhoIntervalo = xFinal - xInicial
        val trechos: Int = (tamanhoIntervalo/passo).toInt()
        val matrizX = DoubleArray(trechos)
        val matrizY = DoubleArray(trechos)

        for(indice in 0..(trechos - 1)){

            val xAtual = basePointX + (passo*indice)*xScale
            val yAtual = basePointY + (Y(xInicial + passo*indice))*yScale

            matrizX[indice] = if(angulo != 0.0) {rotatePointX + (xAtual - rotatePointX)*cosseno - (yAtual - rotatePointY)*seno} else {xAtual}
            matrizY[indice] = if(angulo != 0.0) {rotatePointY + (xAtual - rotatePointX)*seno + (yAtual - rotatePointY)*cosseno} else {yAtual}

        }

        return when(eixo){

            Quadratica.x -> matrizX
            Quadratica.y -> matrizY

            else -> DoubleArray(0)

        }

    }

    fun matrizCoordYcos(eixo: String, basePointX: Double, basePointY: Double, passo: Double, angulo: Double = 0.0, xInicial: Double = dominio[0], xFinal: Double = dominio[1], xScale: Double = 1.0, yScale: Double = 1.0, rotatePointX: Double = basePointX, rotatePointY: Double = basePointY, anguloCalc: Double = angulo) : DoubleArray{

        val cosseno = cos(angulo)
        val seno = sin(angulo)
        val tamanhoIntervalo = xFinal - xInicial
        val trechos: Int = (tamanhoIntervalo/passo).toInt()
        val matrizX = DoubleArray(trechos)
        val matrizY = DoubleArray(trechos)

        for(indice in 0..(trechos - 1)){

            val xAtual = basePointX + (passo*indice)*xScale
            val yAtual = basePointY + (Ycos(xInicial + passo*indice, anguloCalc))*yScale

            matrizX[indice] = if(angulo != 0.0) {rotatePointX + (xAtual - rotatePointX)*cosseno - (yAtual - rotatePointY)*seno} else {xAtual}
            matrizY[indice] = if(angulo != 0.0) {rotatePointY + (xAtual - rotatePointX)*seno + (yAtual - rotatePointY)*cosseno} else {yAtual}

        }

        return when(eixo){

            Quadratica.x -> matrizX
            Quadratica.y -> matrizY

            else -> DoubleArray(0)

        }

    }

    override fun toString(): String {

        return "Quártica: $A x^4 + $B x^3 + $C x^2 + $D x + $E"

    }
}