package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities

class BooleanToYesOrNo {
    companion object{
        fun Boolean.simOuNao() : String {
            return when(this){
                true -> "Sim"
                false -> "Não"
            }
        }
    }
}