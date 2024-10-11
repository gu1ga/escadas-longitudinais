package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal

import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.roundToMultipleOf
import kotlin.math.ceil
import kotlin.math.floor


const val ESPELHO_MINIMO = 15.0
const val ESPELHO_MAXIMO = 20.0
const val PISO_MINIMO = 25.0
const val PISO_MAXIMO = 32.0

//FUNÇÔES RELACIOANDAS AOS PISOS
fun numeroMinimoPisos(comprimentoLance: Double): Int = ceil(comprimentoLance/PISO_MAXIMO).toInt()

fun numeroMaximoPisos(comprimentoLance: Double): Int = floor(comprimentoLance/PISO_MINIMO).toInt()

fun pisoValido(piso: Double, comprimentoLance: Double) : Boolean = comprimentoLance/piso -  floor((comprimentoLance/piso)) <= 0.01

//FUNÇÔES RELACIOANDAS AOS ESPELHOS
fun espelhoPossiveis(desnivel: Double): List<Double>{

    val espelhosPossiveis = mutableListOf<Double>()

    for (quantidade in numeroMinimoEspelhos(desnivel)..numeroMaximoEspelhos(desnivel)){
        espelhosPossiveis.add(desnivel/quantidade)
    }

    return espelhosPossiveis
}

fun numeroMinimoEspelhos(desnivel: Double): Int = ceil(desnivel/ESPELHO_MAXIMO).toInt()

fun numeroMaximoEspelhos(desnivel: Double): Int = floor(desnivel/ESPELHO_MINIMO).toInt()

fun espelhoValido(espelho: Double, desnivel: Double) : Boolean =  desnivel/espelho - floor(desnivel/espelho) <= 0.01

//SUGERIR GEOMETRIA
fun sugerirEspelho(desnivel: Double): Double{

    val espelhosPossiveis = espelhoPossiveis(desnivel)

    val espelhosElegiveis = mutableListOf<Double>()

    val espelhosPerfeitos = mutableListOf<Double>()

    for (espelho in espelhosPossiveis){
        if(espelho.toBigDecimal().scale() <= 2){
            espelhosElegiveis.add(espelho)
        }
    }

    for (espelho in espelhosElegiveis){
        if(espelho.roundToMultipleOf(0.5) == espelho){
            espelhosPerfeitos.add(espelho)
        }
    }

    if(espelhosPerfeitos.isNotEmpty()){
        return espelhosPerfeitos.first()
    }

    return espelhosPossiveis.first()
}

fun sugerirPisoPeloEspelho(comprimentoLance: Double, desnivel: Double, espelho: Double) : Double{

    val numeroDegraus = (desnivel/espelho).toInt() - 1

    return comprimentoLance/numeroDegraus

}

//FUNÇÕES GERAIS
fun checarBlondel(piso: Double, espelho: Double) : Boolean =  (2*espelho + piso) in 62.0..64.0

fun checarBlondelMaior(piso: Double, espelho: Double) : Boolean =  (2*espelho + piso) > 64.0

fun checarBlondelMenor(piso: Double, espelho: Double) : Boolean =  (2*espelho + piso) < 62.0

fun checarConsistenciaPisoEspelho(piso: Double, espelho: Double, comprimentoLance: Double, desnivel: Double) : Boolean{

    val numeroDegrausEspelho = (desnivel/espelho).toInt() - 1
    val numeroDegrausPiso = (comprimentoLance/piso).toInt()

    return numeroDegrausEspelho == numeroDegrausPiso
}

fun checarGeometriaValida(comprimentoLance: Double, desnivel: Double) : Boolean{
    val espelho = sugerirEspelho(desnivel)
    val piso = sugerirPisoPeloEspelho(comprimentoLance, desnivel, espelho)

    return espelho in ESPELHO_MINIMO..ESPELHO_MAXIMO && piso in PISO_MINIMO..PISO_MAXIMO
}