package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.sobrecarga

import Constantes.CARGA_DISTRIBUIDA_MAXIMA
import android.app.Application
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format

class SobrecargaViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val DESMARCADO = false
        const val MARCADO = true
        const val EMPTY_STRING = ""
        const val ZERO_STRING ="0"
        const val ZERO = 0.toDouble()
        const val INDEFINIDO = "Indef."
    }


    val spinnerClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            cargaNormativa.value = parent?.getItemAtPosition(position).toString()
            textoCargaNormativa.value =  application.resources.getStringArray(R.array.sobrecargas_nbr_6120_modulos_kgf_m2)[position]
            atualizarCargaNormativa()
            salvarSelecao()
        }
    }

    val cargaNormativa = MutableLiveData(INDEFINIDO)
    private val valorCargaNormativa: String
        get() = cargaNormativa.value ?: ""

    private val textoCargaNormativa = MutableLiveData(INDEFINIDO)
    val valorTextoCargaNoramtiva: LiveData<String>
        get() = textoCargaNormativa

    val sobrecargaNormativaCheckBox = MutableLiveData(MARCADO)
    private val sobrecargaNormativaCheckBoxMarcado: Boolean
        get() = sobrecargaNormativaCheckBox.value ?: MARCADO

    val sobrecargaManualCheckBox = MutableLiveData(DESMARCADO)
    private val sobrecargaManualCheckBoxMarcado: Boolean
        get() = sobrecargaManualCheckBox.value ?: DESMARCADO

    val sobrecargaPatamares = MutableLiveData(EMPTY_STRING)
    private val valorSobrecargaPatamares: Double
        get() = sobrecargaPatamares.value?.toDoubleOrNull() ?: ZERO

    val sobrecargaLances = MutableLiveData(EMPTY_STRING)
    private val valorSobrecargaLances: Double
        get() = sobrecargaLances.value?.toDoubleOrNull() ?: ZERO

    //Transformations
    val sobrecargaPatamresTransformation = sobrecargaPatamares.map {
        if (checarSobrecargaPatamares()) {
            atualizarSobrecargaPatamares()
        }
    }
    val sobrecargaLancesTransformation = sobrecargaLances.map {
        if (checarSobrecargaLances()) {
            atualizarSobrecargaLances()
        }
    }

    val sobrecargaManualCheckBoxTransformation = sobrecargaManualCheckBox.map{
        if(!it){
            sobrecargaLances.value = EMPTY_STRING
            ESCADA.sobrecargaManualLance = ZERO

            sobrecargaPatamares.value = EMPTY_STRING
            ESCADA.sobrecargaManualPatamares = ZERO
        }
    }

    //Events
    val eventSobrecargaPatamaresForaDeIntervalo = ViewModelEvent()
    val eventSobrecargaPatamaresOk = ViewModelEvent()
    val eventSobrecargaLancesForaDeIntervalo = ViewModelEvent()
    val eventSobrecargaLancesOk = ViewModelEvent()

    init{
        atualizarUI()
    }

    private fun checarSobrecargaPatamares(): Boolean {
        if(valorSobrecargaPatamares in 0.0 .. CARGA_DISTRIBUIDA_MAXIMA){
            eventSobrecargaPatamaresOk.trigger()
            return true
        }
        if(valorSobrecargaPatamares != ZERO){
            eventSobrecargaPatamaresForaDeIntervalo.trigger()
            return false
        }
        eventSobrecargaPatamaresOk.trigger()
        return false
    }

    private fun checarSobrecargaLances(): Boolean {
        if(valorSobrecargaLances in 0.0 .. CARGA_DISTRIBUIDA_MAXIMA){
            eventSobrecargaLancesOk.trigger()
            return true
        }
        if(valorSobrecargaLances != ZERO){
            eventSobrecargaLancesForaDeIntervalo.trigger()
            return false
        }
        eventSobrecargaLancesOk.trigger()
        return false
    }

    private fun atualizarCargaNormativa(){
        if(sobrecargaNormativaCheckBoxMarcado){
            ESCADA.sobrecargaNormativa = textoCargaNormativa.value?.toDoubleOrNull() ?: ZERO
        }
    }

    private fun salvarSelecao(){
        if(sobrecargaNormativaCheckBoxMarcado){
            //TODO
        }
    }

    private fun atualizarSobrecargaLances(){
        if(sobrecargaManualCheckBoxMarcado){
            ESCADA.sobrecargaManualLance = valorSobrecargaLances
        }
    }

    private fun atualizarSobrecargaPatamares(){

        if(sobrecargaManualCheckBoxMarcado){
            ESCADA.sobrecargaManualPatamares = valorSobrecargaPatamares
        }
    }

    fun atualizarUI(){
        //definirEntradasSobreCargaNormativa()
        definriEntradasSobrecargaManual()
    }

    private fun definriEntradasSobrecargaManual(){
        if(ESCADA.sobrecargaManualPatamares != ZERO || ESCADA.sobrecargaManualLance != ZERO){

            Log.i("Teste", "Atualizando Valores de Sobrecarga")
            sobrecargaManualCheckBox.value = MARCADO
            sobrecargaLances.value = if(ESCADA.sobrecargaManualLance != ZERO) ESCADA.sobrecargaManualLance.format(2) else EMPTY_STRING
            sobrecargaPatamares.value = if(ESCADA.sobrecargaManualPatamares != ZERO) ESCADA.sobrecargaManualPatamares.format(2) else EMPTY_STRING
            return
        }
        sobrecargaLances.value = EMPTY_STRING
        sobrecargaPatamares.value = EMPTY_STRING
    }
}