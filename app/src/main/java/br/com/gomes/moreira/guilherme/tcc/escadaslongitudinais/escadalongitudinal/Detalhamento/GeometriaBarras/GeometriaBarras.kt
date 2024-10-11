package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento

import EscadaLongitudinal
import EscadaLongitudinal.Companion.TIPOLOGIA_A
import EscadaLongitudinal.Companion.TIPOLOGIA_B
import EscadaLongitudinal.Companion.TIPOLOGIA_C
import EscadaLongitudinal.Companion.TIPOLOGIA_D

fun definirGeometriaBarras(escadaLongitudinal: EscadaLongitudinal){
    when(escadaLongitudinal.tipologia){
        TIPOLOGIA_A -> definirBarrasTipologiaA(escadaLongitudinal)
        TIPOLOGIA_B -> definirBarrasTipologiaB(escadaLongitudinal)
        TIPOLOGIA_C -> definirBarrasTipologiaC(escadaLongitudinal)
        TIPOLOGIA_D -> definirBarrasTipologiaD(escadaLongitudinal)
    }
}






