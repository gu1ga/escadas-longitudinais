package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras

import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Barra

class ArmaduraPositivaSecundaria: Armadura(POSTIIVA_SECUNDARIA) {
    val barraPatamarInicial: Barra get() = barras[0]
    val barraLanceSuperior: Barra get() = barras[1]
    val barraLanceInferior: Barra get() = barras[2]
    val barraPatamarIntermediario: Barra get() = barras[3]
}