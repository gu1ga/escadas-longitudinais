package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.permanente

import Constantes.CARGA_DISTRIBUIDA_MAXIMA
import android.util.Log
import androidx.lifecycle.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format

class CargaPermanenteViewModel : ViewModel(){

    companion object{
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
        const val MARCADO = true
        const val DESMARCADO = false
    }

    val cargaPermanentePatamares = MutableLiveData(EMPTY_STRING)
    private val valorCargaPermanentePamtamres: Double
        get() = cargaPermanentePatamares.value?.toDoubleOrNull() ?: ZERO

    val cargaPermanenteLance = MutableLiveData(EMPTY_STRING)
    private val valorCargaPermanenteLance: Double
        get() = cargaPermanenteLance.value?.toDoubleOrNull() ?: ZERO

    val cargaPermanenteManualCheckBox = MutableLiveData(MARCADO)
    private val cargaPermanenteManualCheckBoxMarcado: Boolean
        get() = cargaPermanenteManualCheckBox.value ?: DESMARCADO


    //TRANSFORMATIONS
    val cargaPermanentePatmaresTransformation = cargaPermanentePatamares.map {
        if (checarCargaPermanentePatamares()) {
            atualizarCargaPermanentePatamares()
        }
    }
    val cargaPermanenteLanceTransformation = cargaPermanenteLance.map {
        if (checarCargaPermanenteLance()) {
            atualizarCargaPermanenteLance()
        }
    }
    val cargaPermanenteCheckboxTransformation = cargaPermanenteManualCheckBox.map{
        if(!it){
            cargaPermanenteLance.value = EMPTY_STRING
            ESCADA.cargaPermanenteManualLance = ZERO

            cargaPermanentePatamares.value = EMPTY_STRING
            ESCADA.cargaPermanenteManualPatamares = ZERO
        }
    }

    //EVENTS
    val eventCargaPatamaresForaDeIntervalo = ViewModelEvent ()
    val eventCargaPatamaresOk = ViewModelEvent()

    val eventCargaLanceForaDeIntervalo = ViewModelEvent ()
    val eventCargaLnceOk = ViewModelEvent()

    init {
        atualizarUI()
    }

    private fun checarCargaPermanentePatamares() : Boolean{
        if(valorCargaPermanentePamtamres in ZERO .. CARGA_DISTRIBUIDA_MAXIMA){
            eventCargaPatamaresOk.trigger()
            return true
        }

        if(valorCargaPermanentePamtamres > CARGA_DISTRIBUIDA_MAXIMA){
            eventCargaPatamaresForaDeIntervalo.trigger()
            return false
        }
        eventCargaPatamaresOk.trigger()
        return false
    }
    private fun checarCargaPermanenteLance() : Boolean{
        if(valorCargaPermanenteLance in ZERO .. CARGA_DISTRIBUIDA_MAXIMA){
            eventCargaLnceOk.trigger()
            return true
        }

        if(valorCargaPermanenteLance > CARGA_DISTRIBUIDA_MAXIMA){
            eventCargaLanceForaDeIntervalo.trigger()
            return false
        }
        eventCargaLnceOk.trigger()
        return false
    }

    private fun atualizarCargaPermanentePatamares(){
        if(cargaPermanenteManualCheckBoxMarcado){
            ESCADA.cargaPermanenteManualPatamares = valorCargaPermanentePamtamres
        }
    }

    private fun atualizarCargaPermanenteLance(){
        if(cargaPermanenteManualCheckBoxMarcado){
            ESCADA.cargaPermanenteManualLance = valorCargaPermanenteLance
        }
    }

    private fun definirValoresCargaPermanente(){
        if(ESCADA.cargaPermanenteManualPatamares != ZERO || ESCADA.cargaPermanenteManualLance != ZERO){

            Log.i("Teste", "Atualizando Valores de Sobrecarga")
            cargaPermanenteManualCheckBox.value = MARCADO
            cargaPermanenteLance.value = if(ESCADA.cargaPermanenteManualLance != ZERO) ESCADA.cargaPermanenteManualLance.format(2) else EMPTY_STRING
            cargaPermanentePatamares.value = if(ESCADA.cargaPermanenteManualPatamares != ZERO) ESCADA.cargaPermanenteManualPatamares.format(2) else EMPTY_STRING
            return
        }
        cargaPermanenteLance.value = EMPTY_STRING
        cargaPermanentePatamares.value = EMPTY_STRING
    }

    fun atualizarUI(){
        definirValoresCargaPermanente()
    }
}