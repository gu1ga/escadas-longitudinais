package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaA

import EscadaLongitudinal
import GeometriaAnalitica.Segmento
import GeometriaAnalitica.Segmento.Companion.addSegmentoRelativo
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

//Armadura Positiva Principal
fun definirBarrasArmaduraPositivaPrincipalTipologiaA(escada: EscadaLongitudinal) {
    definirBarraArmaduraPositivaPrincipalInicialInferior(escada)
    definirBarraArmaduraPositivaPrincipalFinalInferior(escada)
    definirBarraArmaduraPositivaPrincipalInicialSuperior(escada)
    definirBarraArmaduraPositivaPrincipalFinalSuperior(escada)
}

private fun definirBarraArmaduraPositivaPrincipalInicialInferior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()

    val delta1 = ((escada.espessura - cobrimento) * tan(escada.anguloLance * 0.5)).toFloat()
    val delta2 = ((escada.espelho - cobrimento + ((escada.espessura - cobrimento) / cos(escada.anguloLance))) / tan(escada.anguloLance)).toFloat()

    val dyApoioEsquerdo = (escada.espessura - 2 * cobrimento).toFloat()
    val dxPatamarInicial = (escada.comprimentoPatamarInicial + escada.baseApoioEsquerdo - cobrimento + delta1).toFloat()
    val dxLance = (escada.comprimentoLance - delta1 + delta2).toFloat()
    val dyLance = (escada.peDireitoLance + escada.espessura - 2 * cobrimento).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioEsquerdo))
        addSegmentoRelativo(dxPatamarInicial, 0f)
        addSegmentoRelativo(dxLance, -dyLance)
        addSegmentoRelativo(traspasse, 0f)
    }

    escada.armaduraPositivaPrincipal.barraInicialInferior.definirGeometria(listaSegmentos)
}

private fun definirBarraArmaduraPositivaPrincipalFinalInferior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()

    val delta1 = ((escada.espessura - escada.cobrimento) * tan(escada.anguloLance * 0.5)).toFloat()
    val delta2 = (escada.piso.toFloat() + delta1)
    val delta3 = (((escada.espessura - 2 * escada.cobrimento) / tan(escada.anguloLance)).toFloat())

    val dyApoioDireito = (escada.espessura - 2 * escada.cobrimento).toFloat()
    val dxPatamarIntermediario = (escada.comprimentoPatamarIntermediario - cobrimento + escada.baseApoioDireito - delta2 + delta3).toFloat()
    val dxLance = (traspasse * cos(escada.anguloLance)).toFloat()
    val dyLance = (traspasse * sin(escada.anguloLance)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioDireito))
        addSegmentoRelativo(-dxPatamarIntermediario, 0f)
        addSegmentoRelativo(-dxLance, dyLance)
    }

    escada.armaduraPositivaPrincipal.barraFinalInferior.definirGeometria(listaSegmentos)
}

private fun definirBarraArmaduraPositivaPrincipalInicialSuperior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()

    val delta1 = ((escada.espessura - escada.cobrimento) * tan(escada.anguloLance * 0.5)).toFloat()
    val delta2 = (escada.piso.toFloat() + delta1)
    val delta3 = (((escada.espessura - 2 * escada.cobrimento) / tan(escada.anguloLance)).toFloat())

    val dyApoioEsquerdo = (escada.espessura - 2 * escada.cobrimento).toFloat()
    val dxPatamarInicial = (escada.comprimentoPatamarInicial - cobrimento + escada.baseApoioEsquerdo - delta2 + delta3).toFloat()
    val dxLance = (traspasse * cos(escada.anguloLance)).toFloat()
    val dyLance = (traspasse * sin(escada.anguloLance)).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioEsquerdo))
        addSegmentoRelativo(dxPatamarInicial, 0f)
        addSegmentoRelativo(dxLance, dyLance)
    }

    escada.armaduraPositivaPrincipal.barraInicialSuperior.definirGeometria(listaSegmentos)
}

private fun definirBarraArmaduraPositivaPrincipalFinalSuperior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()

    val delta1 = ((escada.espessura - cobrimento) * tan(escada.anguloLance * 0.5)).toFloat()
    val delta2 = ((escada.espelho - cobrimento + ((escada.espessura - cobrimento) / cos(escada.anguloLance))) / tan(escada.anguloLance)).toFloat()

    val dyApoioDireito = (escada.espessura - 2 * cobrimento).toFloat()
    val dxPatamarIntermediario = (escada.comprimentoPatamarIntermediario + escada.baseApoioDireito - cobrimento + delta1).toFloat()
    val dxLance = (escada.comprimentoLance - delta1 + delta2).toFloat()
    val dyLance = (escada.peDireitoLance + escada.espessura - 2 * cobrimento).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioDireito))
        addSegmentoRelativo(-dxPatamarIntermediario, 0f)
        addSegmentoRelativo(-dxLance, -dyLance)
        addSegmentoRelativo(-traspasse, 0f)
    }

    escada.armaduraPositivaPrincipal.barraFinalSuperior.definirGeometria(listaSegmentos)
}