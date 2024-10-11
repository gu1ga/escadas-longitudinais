import GeometriaAnalitica.Quadratica
import kotlin.math.pow

interface Flecha {

    /**Retorna o Momento de Fissuração*/
    fun calcularMr(Fct: Double, Ic: Double, yt: Double, alpha: Double = 1.5) = (alpha*Fct*Ic/yt)

    /**Retorna o Fctm*/
    fun calcularFct(Fck: Double) = (0.3*Math.pow(Fck, 2.0/3))*10 //kgf/cm²

    /**Retorna a Aultura da Linha Neutra Para o Estádio de Flexão II*/
    fun calcularAlturaLinhaNeutraEstadioII(base: Double,
                                                   areaAcoPositivoPrincipal: Double,
                                                   alturaUtil: Double,
                                                   Es: Double,
                                                   Ecs: Double) : Double{

        val alpha: Double = Es/Ecs
        val a: Double = 1.toDouble()
        val b: Double = (2*areaAcoPositivoPrincipal*alpha)/base
        val c: Double = (-1)*((2*areaAcoPositivoPrincipal*alturaUtil*alpha)/base)

        return Quadratica.calcularRaizes(a, b, c)[0]

    }

    /**Retorna o Momento de Inércia Fissurado Para o Estádio de Flexão II*/
    fun calcularMomentoInerciaEstadioII(alturaLinhaNeutraEstadioII: Double,
                                        base: Double,
                                        areaAcoPositivoPrincipal: Double,
                                        alturaUtil: Double,
                                        Es: Double,
                                        Ecs: Double) : Double{

        val alpha: Double = Es/Ecs
        val A: Double = (base*alturaLinhaNeutraEstadioII.pow(3))/12.0
        val B: Double = base*alturaLinhaNeutraEstadioII*(alturaLinhaNeutraEstadioII/2.0).pow(2)
        val C: Double = alpha*areaAcoPositivoPrincipal*((alturaUtil - alturaLinhaNeutraEstadioII).pow(2))

        return A + B + C

    }

    /**Retonra a Rigidez à Flexão Equivalente*/
    fun calcularRigidezEquivalente(Ecs: Double,
                                   Ic: Double,
                                   Mr: Double,
                                   Ma: Double,
                                   momentoInerciaEstadioII: Double) : Double{

        return Ecs*( Ic*(Mr/Ma).pow(3) + momentoInerciaEstadioII*(1.0 - ((Mr/Ma).pow(3))))



    }

    /**Retorna a Flecha Total*/
    fun calcularFlechaDiferida(flechaImediata: Double, tempo: Double = 71.0, taxaArmaduraCompressao: Double = 0.0) : Double{

        return when(tempo <= 70.0){

            true -> ((0.68*(0.996.pow(tempo))*tempo.pow(0.32))/(1+taxaArmaduraCompressao))*flechaImediata
            false -> 2*flechaImediata
            else -> 2*flechaImediata

        }

    }
}