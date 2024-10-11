package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento

import EscadaLongitudinal
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaB.definirBarrasArmaduraPositivaPrincipalTipologiaB
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaB.definirBarrasArmaduraPositivaSecundariaTipologiaB
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaB.definirBarrasArmdauraNegativaTipologiaB

fun definirBarrasTipologiaB(escada: EscadaLongitudinal) {
    definirBarrasArmaduraPositivaPrincipalTipologiaB(escada)
    definirBarrasArmaduraPositivaSecundariaTipologiaB(escada)
    definirBarrasArmdauraNegativaTipologiaB(escada)
}