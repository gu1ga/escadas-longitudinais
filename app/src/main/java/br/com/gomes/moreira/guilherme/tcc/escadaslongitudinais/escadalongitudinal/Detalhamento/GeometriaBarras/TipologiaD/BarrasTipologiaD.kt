package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento

import EscadaLongitudinal
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaD.definirBarrasArmaduraPositivaPrincipalTipologiaD
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaD.definirBarrasArmaduraPositivaSecundariaTipologiaD
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaD.definirBarrasArmdauraNegativaTipologiaD

fun definirBarrasTipologiaD(escada: EscadaLongitudinal) {
    definirBarrasArmaduraPositivaPrincipalTipologiaD(escada)
    definirBarrasArmaduraPositivaSecundariaTipologiaD(escada)
    definirBarrasArmdauraNegativaTipologiaD(escada)
}
