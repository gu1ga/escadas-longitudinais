package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaD

import EscadaLongitudinal
import GeometriaAnalitica.Segmento

//Armadura Positiva Secundaria
fun definirBarrasArmaduraPositivaSecundariaTipologiaD(escada: EscadaLongitudinal) {
    definirBarrasArmaduraPositivaSecundariaLanceSuperior(escada)
    definirBarrasArmaduraPositivaSecundariaLanceInferior(escada)
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

