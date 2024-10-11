package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaD

import EscadaLongitudinal
import GeometriaAnalitica.Segmento
import GeometriaAnalitica.Segmento.Companion.addSegmentoRelativo
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.tan

//Arrmadura Negativa

fun definirBarrasArmdauraNegativaTipologiaD(escada: EscadaLongitudinal) {
    definirBarraNegativaInicialInferior(escada)
    definirBarraNegativaFinalInferior(escada)
    definirBarraNegativaInicialSuperior(escada)
    definirBarraNegativaFinalSuperior(escada)
}

private fun definirBarraNegativaInicialInferior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val espessuraInclinada = (escada.espessura/ cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/ cos(escada.anguloLance)).toFloat()
    val alturaPerna = espessuraInclinada - 2*cobrimentoInclinado
    val dxComprimentoNegativo = (escada.comprimentoTotal*0.25).toFloat()
    val dyComprimmentoNegativo = (dxComprimentoNegativo* tan(escada.anguloLance)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -alturaPerna))
        addSegmentoRelativo(dxComprimentoNegativo, -dyComprimmentoNegativo)
        addSegmentoRelativo(0f, alturaPerna)
    }

    escada.armaduraNegativa.barraInicialInferior.definirGeometria(listaSegmentos)
}


private fun definirBarraNegativaFinalInferior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val espessuraInclinada = (escada.espessura/cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/cos(escada.anguloLance)).toFloat()

    val alturaPerna = espessuraInclinada - 2*cobrimentoInclinado

    val dxComprimentoNegativo = (escada.comprimentoTotal*0.25).toFloat()
    val dyComprimmentoNegativo = (dxComprimentoNegativo*tan(escada.anguloLance)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -alturaPerna))
        addSegmentoRelativo(-dxComprimentoNegativo, dyComprimmentoNegativo)
        addSegmentoRelativo(0f, alturaPerna)
    }

    escada.armaduraNegativa.barraFinalInferior.definirGeometria(listaSegmentos)
}

private fun definirBarraNegativaInicialSuperior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val espessuraInclinada = (escada.espessura/ cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/ cos(escada.anguloLance)).toFloat()
    val alturaPerna = espessuraInclinada - 2*cobrimentoInclinado
    val dxComprimentoNegativo = (escada.comprimentoTotal*0.25).toFloat()
    val dyComprimmentoNegativo = (dxComprimentoNegativo* tan(escada.anguloLance)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -alturaPerna))
        addSegmentoRelativo(dxComprimentoNegativo, dyComprimmentoNegativo)
        addSegmentoRelativo(0f, alturaPerna)
    }

    escada.armaduraNegativa.barraInicialSuperior.definirGeometria(listaSegmentos)
}

private fun definirBarraNegativaFinalSuperior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val espessuraInclinada = (escada.espessura/cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/cos(escada.anguloLance)).toFloat()

    val alturaPerna = espessuraInclinada - 2*cobrimentoInclinado

    val dxComprimentoNegativo = (escada.comprimentoTotal*0.25).toFloat()
    val dyComprimmentoNegativo = (dxComprimentoNegativo*tan(escada.anguloLance)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -alturaPerna))
        addSegmentoRelativo(-dxComprimentoNegativo, -dyComprimmentoNegativo)
        addSegmentoRelativo(0f, alturaPerna)
    }

    escada.armaduraNegativa.barraFinalSuperior.definirGeometria(listaSegmentos)
}