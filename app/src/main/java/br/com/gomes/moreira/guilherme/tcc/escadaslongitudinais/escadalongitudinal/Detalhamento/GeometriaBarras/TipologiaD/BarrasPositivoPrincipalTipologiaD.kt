package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaD

import EscadaLongitudinal
import GeometriaAnalitica.Segmento
import GeometriaAnalitica.Segmento.Companion.addSegmentoRelativo
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

//Armadura Positiva Principal
fun definirBarrasArmaduraPositivaPrincipalTipologiaD(escada: EscadaLongitudinal) {
    definirBarraArmaduraPositivaPrincipalInicialInferior(escada)
    definirBarraArmaduraPositivaPrincipalInicialSuperior(escada)
}

private fun definirBarraArmaduraPositivaPrincipalInicialInferior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()
    val espessuraInclinada = (escada.espessura/ cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/ cos(escada.anguloLance)).toFloat()
    val dyApoioEsquerdo = espessuraInclinada - cobrimento - cobrimentoInclinado + ((escada.baseApoioEsquerdo - cobrimento)* tan(escada.anguloLance)).toFloat()
    val dyApoioDireito = escada.espelho.toFloat() + espessuraInclinada - cobrimento - cobrimentoInclinado - ((escada.baseApoioDireito - cobrimento)* tan(escada.anguloLance)).toFloat()
    val dxLance = (escada.comprimentoTotal - 2*escada.cobrimento).toFloat()
    val dyLance = (escada.peDireitoLance + dyApoioEsquerdo - dyApoioDireito).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioEsquerdo))
        addSegmentoRelativo(dxLance, -dyLance)
        addSegmentoRelativo(0f, -dyApoioDireito)
    }

    escada.armaduraPositivaPrincipal.barraInicialInferior.definirGeometria(listaSegmentos)
}

private fun definirBarraArmaduraPositivaPrincipalInicialSuperior(escada: EscadaLongitudinal){
    val listaSegmentos = mutableListOf<Segmento>()

    val traspasse = 50f
    val cobrimento = (escada.cobrimento).toFloat()
    val espessuraInclinada = (escada.espessura/ cos(escada.anguloLance)).toFloat()
    val cobrimentoInclinado = (escada.cobrimento/ cos(escada.anguloLance)).toFloat()
    val dyApoioEsquerdo = escada.espelho.toFloat() + espessuraInclinada - cobrimento - cobrimentoInclinado - ((escada.baseApoioEsquerdo - cobrimento)* tan(escada.anguloLance)).toFloat()
    val dyApoioDireito =  espessuraInclinada - cobrimento - cobrimentoInclinado + ((escada.baseApoioDireito - cobrimento)* tan(escada.anguloLance)).toFloat()
    val dxLance = (escada.comprimentoTotal - 2*escada.cobrimento).toFloat()
    val dyLance = (escada.peDireitoLance + dyApoioDireito - dyApoioEsquerdo).toFloat()

    listaSegmentos.apply {
        add(Segmento(0f, 0f, 0f, dyApoioDireito))
        addSegmentoRelativo(-dxLance, -dyLance)
        addSegmentoRelativo(0f, -dyApoioEsquerdo)
    }

    escada.armaduraPositivaPrincipal.barraInicialSuperior.definirGeometria(listaSegmentos)
}