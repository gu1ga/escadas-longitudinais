package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities

class InputTestStatus(initialStatus: Boolean = OK) {

    var status: Boolean

    companion object{
        const val OK = true
        const val NOT_OK = false
    }

    init{
        this.status = initialStatus
    }

    fun notOK(){
        this.status = false
    }

    fun ok(){
        this.status = true
    }

}