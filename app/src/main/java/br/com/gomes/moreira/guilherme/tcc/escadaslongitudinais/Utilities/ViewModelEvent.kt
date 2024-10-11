package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities

import androidx.lifecycle.MutableLiveData

class ViewModelEvent(inputTestStatus: InputTestStatus = InputTestStatus(), offValue: Boolean = false) : MutableLiveData<Boolean>() {

    val offValue: Boolean
    val inputTestStatus: InputTestStatus

    init{
        this.offValue = offValue
        this.value = offValue
        this.inputTestStatus = inputTestStatus
    }

    fun start(value: Boolean = offValue.not()){
        this.value = value
    }

    fun stop(inputTestStatus: Boolean = false){
        this.value = offValue
        this.inputTestStatus.status = inputTestStatus
    }

    fun trigger(){
        start()
        stop()
    }
}