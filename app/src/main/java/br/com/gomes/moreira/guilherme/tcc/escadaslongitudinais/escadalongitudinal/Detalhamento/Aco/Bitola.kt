package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco

data class Bitola(val diametroMilimetros: Double, val areaCentimetros: Double, val pesoLinear: Double) {
    val diametroCentimetros: Double get() = diametroMilimetros*0.1

    override fun toString(): String {
        return diametroMilimetros.toString()
    }
}