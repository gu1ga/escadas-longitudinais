package GeometriaAnalitica

import kotlin.math.abs
import kotlin.math.pow

class Cubica(A: Double, B: Double, C: Double, D: Double, dominio: Array<Double> = arrayOf(Double.MIN_VALUE, Double.MAX_VALUE)){

    var A: Double
    var B: Double
    var C: Double
    var D: Double
    val derivada: Quadratica = Quadratica(0.0, 0.0, 0.0, dominio)
    var dominio: Array<Double>
    val raizes: Array<Double>

    init{

        this.A = A
        this.B = B
        this.C = C
        this.D = D
        this.dominio = dominio
        this.derivada.definirA(3.0*A)
        this.derivada.definirB(2.0*B)
        this.derivada.definirC(C)
        this.raizes =  arrayOf((dominio[0] - 100.0)*2, (dominio[0] - 100.0)*2, (dominio[0] - 100.0)*2)
        this.definirRaizes()

    }

    fun Y(x: Double) : Double{

        return A*x.pow(3) + B*x.pow(2) + C*x + D

    }


    private fun newtonRaphson(xInicial: Double = 1.0, erro: Double =0.00001) : Double {

        var h: Double = this.Y(xInicial)/this.derivada.Y(xInicial)

        var x: Double = xInicial

        while(abs(h) > erro){

            h = this.Y(x)/this.derivada.Y(x)

            x = x - h


        }

        println(this)
        println(x)

        return x

    }

    private fun definirRaizes(){

        when(derivada.delta <=0){

            true  -> definirRaizesOP1()
            false -> definirRaizesOP2()

        }

    }

    private fun definirRaizesOP1(){

        val primeiroPico = this.derivada.Y(this.derivada.raiz1)
        val segundoPico  = this.derivada.Y(this.derivada.raiz2)

        if(primeiroPico*segundoPico < 0){



        }

    }

    private fun definirRaizesOP2(){

        TODO("A implementar")

    }

    override fun toString(): String {
        return "Cúbica: ${this.A}x³ + ${this.B}x² + ${this.C}x + ${this.D}"
    }


}
