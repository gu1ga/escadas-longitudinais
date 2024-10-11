import kotlin.math.pow
import kotlin.math.abs

interface Cisalhamento {

    /**Calcula o Fctk,inf*/
    fun calcularFctkinf(Fck: Double) : Double = (0.7*0.3*(Fck.pow(2.0/3)))*10

    /**Calcula Fctd*/
    fun calcularFctd(Fctkinf: Double, fatorGamac : Double = 1.4) : Double = Fctkinf/fatorGamac

    /**Calcula Trd*/
    fun calcularTrd(fctd: Double) : Double = 0.25*fctd

    /**Calcula o fator k */
    fun calcularK(alturaUtil: Double) : Double = abs(160 - alturaUtil)/100

    /**Calula Vrd1*/
    fun calcularVrd1(trd : Double,
                     base: Double,
                     alturaUtil: Double,
                     k : Double,
                     p1 : Double = 0.0,
                     tensaoCP : Double = 0.0) : Double = (trd*k*(1.2 + 40*p1) + 0.15*tensaoCP)*base*alturaUtil

}