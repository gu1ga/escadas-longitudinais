package GeometriaAnalitica

class Reta(M: Double, L: Double) {

    var M: Double
    var L: Double

    init{

        this.M = M
        this.L = L

    }

    fun Y(x: Double) : Double = this.M*x + L

}