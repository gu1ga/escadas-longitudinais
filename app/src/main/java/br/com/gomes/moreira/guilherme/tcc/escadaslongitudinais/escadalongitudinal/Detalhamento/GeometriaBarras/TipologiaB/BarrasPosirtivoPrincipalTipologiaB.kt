package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaB

import EscadaLongitudinal
import GeometriaAnalitica.Segmento
import GeometriaAnalitica.Segmento.Companion.addSegmentoRelativo
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

//Armadura Positiva Principal
fun definirBarrasArmaduraPositivaPrincipalTipologiaB(escada: EscadaLongitudinal) {
    definirBarraArmaduraPositivaPrincipalInicialInferior(escada)
    definirBarraArmaduraPositivaPrincipalInicialSuperior(escada)
    definirBarraArmaduraPositivaPrincipalFinalSuperior(escada)
}

private fun definirBarraArmaduraPositivaPrincipalInicialInferior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()
    val espessuraInclinada = (escada.espessura/cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/cos(escada.anguloLance)).toFloat()
    val espessura = escada.espessura.toFloat()
    val peDireitoLance = escada.peDireitoLance.toFloat()
    val piso = escada.piso.toFloat()
    val espelho = escada.espelho.toFloat()

    val delta1 = ((escada.espessura - cobrimento) * tan(escada.anguloLance * 0.5)).toFloat()

    val dyApoioEsquerdo = (escada.espessura - 2 * cobrimento).toFloat()
    val dxPatamarInicial = (escada.comprimentoPatamarInicial + escada.baseApoioEsquerdo - cobrimento + delta1).toFloat()

    val dxApoioDireito = (escada.baseApoioDireito - cobrimento).toFloat()
    val dyApoioDireito = (dxApoioDireito*tan(escada.anguloLance)).toFloat()
    val dyApoioDireito2 = espelho + espessuraInclinada - cobrimentoInclinado - dyApoioDireito - cobrimento

    val dxLance = (escada.comprimentoLance - delta1).toFloat() + dxApoioDireito
    val dyLance = peDireitoLance + espessura - cobrimento - espelho - espessuraInclinada + cobrimentoInclinado + dyApoioDireito

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioEsquerdo))
        addSegmentoRelativo(dxPatamarInicial, 0f)
        addSegmentoRelativo(dxLance, -dyLance)
        addSegmentoRelativo(0f, -dyApoioDireito2)
    }

    escada.armaduraPositivaPrincipal.barraInicialInferior.definirGeometria(listaSegmentos)
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
    val espessuraInclinada = (escada.espessura/cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/cos(escada.anguloLance)).toFloat()
    val delta2 = ((escada.espelho - cobrimento + ((escada.espessura - cobrimento) / cos(escada.anguloLance))) / tan(escada.anguloLance)).toFloat()
    val dyApoioDireito = espessuraInclinada - cobrimento - cobrimentoInclinado + ((escada.baseApoioDireito - cobrimento)*tan(escada.anguloLance)).toFloat()
    val dxLance = (escada.comprimentoLance + delta2 + escada.baseApoioDireito - cobrimento).toFloat()
    val dyLance = (escada.peDireitoLance + dyApoioDireito).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioDireito))
        addSegmentoRelativo(-dxLance, -dyLance)
        addSegmentoRelativo(-traspasse, 0f)
    }

    escada.armaduraPositivaPrincipal.barraFinalSuperior.definirGeometria(listaSegmentos)
}