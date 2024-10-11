package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras

import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Barra

class ArmaduraNegativa: Armadura(NEGATIVA) {
    val barraInicialInferior: Barra get() = barras[0]
    val barraFinalInferior: Barra get() = barras[1]
    val barraInicialSuperior: Barra get() = barras[2]
    val barraFinalSuperior: Barra get() = barras[3]

}