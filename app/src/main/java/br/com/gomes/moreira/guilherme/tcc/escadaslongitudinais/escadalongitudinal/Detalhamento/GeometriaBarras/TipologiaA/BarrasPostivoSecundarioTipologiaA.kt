package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaA

import EscadaLongitudinal
import GeometriaAnalitica.Segmento

//Armadura Positiva Secundaria
fun definirBarrasArmaduraPositivaSecundariaTipologiaA(escada: EscadaLongitudinal) {
    definirBarrasArmaduraPositivaSecundariaPatamarInicial(escada)
    definirBarrasArmaduraPositivaSecundariaLanceSuperior(escada)
    definirBarrasArmaduraPositivaSecundariaLanceInferior(escada)
    definirBarrasArmaduraPositivaSecundariaPatamarIntermediario(escada)
}

private fun definirBarrasArmaduraPositivaSecundariaPatamarInicial(escada: EscadaLongitudinal){

    val comprimento = (escada.larguraTotal - escada.cobrimento*2).toFloat()
    val segmentos =  listOf(Segmento(0f, 0f, comprimento,0f))
    escada.armaduraPositivaSecundaria.barraPatamarInicial.definirGeometria(segmentos)
}

private fun definirBarrasArmaduraPositivaSecundariaLanceSuperior(escada: EscadaLongitudinal){

    val comprimento = (escada.larguraPorLance - escada.cobrimento*2).toFloat()
    val segmentos =  listOf(Segmento(0f, 0f, comprimento,0f))
    escada.armaduraPositivaSecundaria.barraLanceSuperior.definirGeometria(segmentos)
}

private fun definirBarrasArmaduraPositivaSecundariaLanceInferior(escada: EscadaLongitudinal){

    val comprimento = (escada.larguraPorLance - escada.cobrimento*2).toFloat()
    val segmentos =  listOf(Segmento(0f, 0f, comprimento,0f))
    escada.armaduraPositivaSecundaria.barraLanceInferior.definirGeometria(segmentos)
}


private fun definirBarrasArmaduraPositivaSecundariaPatamarIntermediario(escada: EscadaLongitudinal){

    val comprimento = (escada.larguraTotal - escada.cobrimento*2).toFloat()
    val segmentos =  listOf(Segmento(0f, 0f, comprimento,0f))
    escada.armaduraPositivaSecundaria.barraPatamarIntermediario.definirGeometria(segmentos)
}