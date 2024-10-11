package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento

import EscadaLongitudinal
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaC.definirBarrasArmaduraPositivaPrincipalTipologiaC
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaC.definirBarrasArmaduraPositivaSecundariaTipologiaC
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaC.definirBarrasArmdauraNegativaTipologiaC

fun definirBarrasTipologiaC(escada: EscadaLongitudinal) {
    definirBarrasArmaduraPositivaPrincipalTipologiaC(escada)
    definirBarrasArmaduraPositivaSecundariaTipologiaC(escada)
    definirBarrasArmdauraNegativaTipologiaC(escada)
}