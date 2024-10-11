package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento

import EscadaLongitudinal
import GeometriaAnalitica.Segmento
import GeometriaAnalitica.Segmento.Companion.addSegmentoRelativo
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaA.definirBarrasArmaduraPositivaPrincipalTipologiaA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaA.definirBarrasArmaduraPositivaSecundariaTipologiaA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.GeometriaBarras.TipologiaA.definirBarrasArmdauraNegativaTipologiaA
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

fun definirBarrasTipologiaA(escada: EscadaLongitudinal) {
    definirBarrasArmaduraPositivaPrincipalTipologiaA(escada)
    definirBarrasArmaduraPositivaSecundariaTipologiaA(escada)
    definirBarrasArmdauraNegativaTipologiaA(escada)
}
