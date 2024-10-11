 package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais

import EscadaLongitudinal
import android.util.Log

 fun testeEscadaTipologiaA(escadaLongitudinal: EscadaLongitudinal){

     Log.i("Teste", "Testando Escada Tipologia A")

    escadaLongitudinal.numeroLances = Constantes.DOIS_LANCES
    escadaLongitudinal.existePatamarInicial = true
    escadaLongitudinal.existePatamarIntermediario = true
    escadaLongitudinal.cobrimentoEfetivo = 2.5
    escadaLongitudinal.piso = 28.0
    escadaLongitudinal.espelho = 17.0
    escadaLongitudinal.larguraTotal = 240.0
    escadaLongitudinal.espessura = 14.0
    escadaLongitudinal.baseApoioEsquerdo = 20.0
    escadaLongitudinal.alturaApoioEsquerdo = 60.0
    escadaLongitudinal.baseApoioDireito = 14.0
    escadaLongitudinal.alturaApoioDireito = 40.0
    escadaLongitudinal.comprimentoPatamarInicial = 100.0
    escadaLongitudinal.comprimentoLance = 224.0
    escadaLongitudinal.comprimentoPatamarIntermediario = 120.0
    escadaLongitudinal.peDireitoTotal = 306.0
    escadaLongitudinal.sobrecargaNormativa = 300.0
    escadaLongitudinal.cargaPermanenteManualPatamares = 100.0
    escadaLongitudinal.cargaPermanenteManualLance = 100.0
    escadaLongitudinal.calcularEsforcosCaracteristicos()
    escadaLongitudinal.calcularAreasDeAco()
    escadaLongitudinal.detalharEsacada()
    escadaLongitudinal.verificarFlecha()

}

fun testeEscadaTipologiaB(escadaLongitudinal: EscadaLongitudinal){

    Log.i("Teste", "Testando Escada Tipologia B")

    escadaLongitudinal.numeroLances = Constantes.DOIS_LANCES
    escadaLongitudinal.existePatamarInicial = true
    escadaLongitudinal.existePatamarIntermediario = false
    escadaLongitudinal.cobrimentoEfetivo = 2.5
    escadaLongitudinal.piso = 28.0
    escadaLongitudinal.espelho = 17.0
    escadaLongitudinal.larguraTotal = 240.0
    escadaLongitudinal.espessura = 14.0
    escadaLongitudinal.baseApoioEsquerdo = 20.0
    escadaLongitudinal.alturaApoioEsquerdo = 60.0
    escadaLongitudinal.baseApoioDireito = 14.0
    escadaLongitudinal.alturaApoioDireito = 40.0
    escadaLongitudinal.comprimentoPatamarInicial = 120.0
    escadaLongitudinal.comprimentoLance = 224.0
    //escadaLongitudinal.comprimentoPatamarIntermediario = 120.0
    escadaLongitudinal.peDireitoTotal = 306.0
    escadaLongitudinal.sobrecargaNormativa = 300.0
    escadaLongitudinal.cargaPermanenteManualPatamares = 100.0
    escadaLongitudinal.cargaPermanenteManualLance = 100.0
    escadaLongitudinal.calcularEsforcosCaracteristicos()
    escadaLongitudinal.calcularAreasDeAco()
    escadaLongitudinal.detalharEsacada()
    escadaLongitudinal.verificarFlecha()

}

fun testeEscadaTipologiaC(escadaLongitudinal: EscadaLongitudinal){

    Log.i("Teste", "Testando Escada Tipologia C")

    escadaLongitudinal.numeroLances = Constantes.DOIS_LANCES
    escadaLongitudinal.existePatamarInicial = false
    escadaLongitudinal.existePatamarIntermediario = true
    escadaLongitudinal.cobrimentoEfetivo = 2.5
    escadaLongitudinal.piso = 28.0
    escadaLongitudinal.espelho = 17.0
    escadaLongitudinal.larguraTotal = 240.0
    escadaLongitudinal.espessura = 14.0
    escadaLongitudinal.baseApoioEsquerdo = 20.0
    escadaLongitudinal.alturaApoioEsquerdo = 60.0
    escadaLongitudinal.baseApoioDireito = 14.0
    escadaLongitudinal.alturaApoioDireito = 40.0
    //escadaLongitudinal.comprimentoPatamarInicial = 120.0
    escadaLongitudinal.comprimentoLance = 224.0
    escadaLongitudinal.comprimentoPatamarIntermediario = 120.0
    escadaLongitudinal.peDireitoTotal = 306.0
    escadaLongitudinal.sobrecargaNormativa = 300.0
    escadaLongitudinal.cargaPermanenteManualPatamares = 100.0
    escadaLongitudinal.cargaPermanenteManualLance = 100.0
    escadaLongitudinal.calcularEsforcosCaracteristicos()
    escadaLongitudinal.calcularAreasDeAco()
    escadaLongitudinal.detalharEsacada()
    escadaLongitudinal.verificarFlecha()

}

 fun testeEscadaTipologiaD(escadaLongitudinal: EscadaLongitudinal){

     Log.i("Teste", "Testando Escada Tipologia D")

     escadaLongitudinal.numeroLances = Constantes.DOIS_LANCES
     escadaLongitudinal.existePatamarInicial = false
     escadaLongitudinal.existePatamarIntermediario = false
     escadaLongitudinal.cobrimentoEfetivo = 2.5
     escadaLongitudinal.piso = 28.0
     escadaLongitudinal.espelho = 17.0
     escadaLongitudinal.larguraTotal = 240.0
     escadaLongitudinal.espessura = 14.0
     escadaLongitudinal.baseApoioEsquerdo = 20.0
     escadaLongitudinal.alturaApoioEsquerdo = 60.0
     escadaLongitudinal.baseApoioDireito = 14.0
     escadaLongitudinal.alturaApoioDireito = 40.0
     //escadaLongitudinal.comprimentoPatamarInicial = 120.0
     escadaLongitudinal.comprimentoLance = 224.0
     //escadaLongitudinal.comprimentoPatamarIntermediario = 120.0
     escadaLongitudinal.peDireitoTotal = 306.0
     escadaLongitudinal.sobrecargaNormativa = 300.0
     escadaLongitudinal.cargaPermanenteManualPatamares = 100.0
     escadaLongitudinal.cargaPermanenteManualLance = 100.0
     escadaLongitudinal.calcularEsforcosCaracteristicos()
     escadaLongitudinal.calcularAreasDeAco()
     escadaLongitudinal.detalharEsacada()
     escadaLongitudinal.verificarFlecha()

 }

object Escada {
    val ESCADA = EscadaLongitudinal()
    //val teste = testeEscadaTipologiaC(ESCADA)
    var geometriaDefinida: Boolean = false
    var dimensionamentoRealizado: Boolean = false
}

