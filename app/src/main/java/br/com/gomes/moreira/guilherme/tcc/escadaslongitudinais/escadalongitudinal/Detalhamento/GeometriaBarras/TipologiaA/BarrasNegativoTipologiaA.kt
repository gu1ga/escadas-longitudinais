package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaA

import EscadaLongitudinal
import GeometriaAnalitica.Segmento
import GeometriaAnalitica.Segmento.Companion.addSegmentoRelativo
import kotlin.math.max
import kotlin.math.min


//Arrmadura Negativa

fun definirBarrasArmdauraNegativaTipologiaA(escada: EscadaLongitudinal) {
    definirBarraNegativaInicialInferior(escada)
    definirBarraNegativaFinalInferior(escada)
    definirBarraNegativaInicialSuperior(escada)
    definirBarraNegativaFinalSuperior(escada)
}

private fun definirBarraNegativaInicialInferior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()
    val comprimentoNegativo = min(escada.comprimentoTotal*0.25, escada.comprimentoPatamarInicial + escada.baseApoioEsquerdo - escada.cobrimento*2).toFloat()
    val dyApoio = ((escada.espessura - escada.cobrimento * 2)).toFloat()


    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -dyApoio))
        addSegmentoRelativo(comprimentoNegativo, 0f)
        addSegmentoRelativo(0f, dyApoio)
    }

    escada.armaduraNegativa.barraInicialInferior.definirGeometria(listaSegmentos)
}

private fun definirBarraNegativaFinalInferior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val comprimentoNegativo = min(escada.comprimentoTotal*0.25, escada.comprimentoPatamarIntermediario + escada.baseApoioDireito - escada.cobrimento*2).toFloat()
    val dyApoio = ((escada.espessura - escada.cobrimento * 2)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -dyApoio))
        addSegmentoRelativo(-comprimentoNegativo, 0f)
        addSegmentoRelativo(0f, dyApoio)
    }

    escada.armaduraNegativa.barraFinalInferior.definirGeometria(listaSegmentos)
}

private fun definirBarraNegativaInicialSuperior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val comprimentoNegativo = min(escada.comprimentoTotal*0.25, escada.comprimentoPatamarInicial + escada.baseApoioEsquerdo - escada.cobrimento*2).toFloat()
    val dyApoio = ((escada.espessura - escada.cobrimento * 2)).toFloat()


    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -dyApoio))
        addSegmentoRelativo(comprimentoNegativo, 0f)
        addSegmentoRelativo(0f, dyApoio)
    }

    escada.armaduraNegativa.barraInicialSuperior.definirGeometria(listaSegmentos)
}

private fun definirBarraNegativaFinalSuperior(escada: EscadaLongitudinal) {
    val listaSegmentos = mutableListOf<Segmento>()

    val comprimentoNegativo = min(escada.comprimentoTotal*0.25, escada.comprimentoPatamarIntermediario + escada.baseApoioDireito - escada.cobrimento*2).toFloat()
    val dyApoio = ((escada.espessura - escada.cobrimento * 2)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, -dyApoio))
        addSegmentoRelativo(-comprimentoNegativo, 0f)
        addSegmentoRelativo(0f, dyApoio)
    }

    escada.armaduraNegativa.barraFinalSuperior.definirGeometria(listaSegmentos)
}