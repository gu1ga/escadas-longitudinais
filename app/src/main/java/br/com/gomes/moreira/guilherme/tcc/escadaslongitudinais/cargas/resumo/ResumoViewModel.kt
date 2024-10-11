package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.resumo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format


class ResumoViewModel : ViewModel() {

    companion object{
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
    }

    //SOBRECARGAS
    private val sobrecargaNormativa = MutableLiveData(ZERO)
    val sobrecargaNormativaString = sobrecargaNormativa.map({
        it.format(2)
    })

    private val sobrecargaManualPatamares = MutableLiveData(ZERO)
    val sobrecargaManualPatamaresString = sobrecargaManualPatamares.map {
        it.format(2)
    }

    private val sobrecargaManualLance = MutableLiveData(ZERO)
    val sobrecargaManualLanceString = sobrecargaManualLance.map {
        it.format(2)
    }

    private val sobrecargaTotalPatamares = MutableLiveData(ZERO)
    val sobrecargaTotalPatamaresString = sobrecargaTotalPatamares.map {
        it.format(2)
    }

    private val sobrecargaTotalLance = MutableLiveData(ZERO)
    val sobrecargaTotalLanceString = sobrecargaTotalLance.map {
        it.format(2)
    }

    //CARGA PERMANENTES
    private val pesoProprioPatamares = MutableLiveData(ZERO)
    val pesoProprioPatamaresString = pesoProprioPatamares.map {
        it.format(2)
    }

    private val pesoProprioLance = MutableLiveData(ZERO)
    val pesoProprioLanceString = pesoProprioLance.map {
        it.format(2)
    }

    private val cargaPermanenteManuallPatamares = MutableLiveData(ZERO)
    val cargaPermanenteManualPatamaresString = cargaPermanenteManuallPatamares.map {
        it.format(2)
    }

    private val cargaPermanenteManualLance = MutableLiveData(ZERO)
    val cargaPermanenteManualLanceString = cargaPermanenteManualLance.map {
        it.format(2)
    }

    private val cargaPermanenteTotalPatamares = MutableLiveData(ZERO)
    val cargaPermanenteTotalPatamaresString = cargaPermanenteTotalPatamares.map {
        it.format(2)
    }

    private val cargaPermanenteTotalLance = MutableLiveData(ZERO)
    val cargaPermanenteTotalLanceString = cargaPermanenteTotalLance.map {
        it.format(2)
    }

    //CARGAS TOTAIS
    private val cargaTotalPatamaresELU = MutableLiveData(ZERO)
    val cargaTotalPatamaresELUString = cargaTotalPatamaresELU.map {
        it.format(2)
    }

    private val cargaTotalLanceELU = MutableLiveData(ZERO)
    val cargaTotalLanceELUString = cargaTotalLanceELU.map {
        it.format(2)
    }

    private val cargaTotalPatamaresELS = MutableLiveData(ZERO)
    val cargaTotalPatamaresELSString = cargaTotalPatamaresELS.map {
        it.format(2)
    }

    private val cargaTotalLanceELS = MutableLiveData(ZERO)
    val cargaTotalLanceELSString = cargaTotalLanceELS.map {
        it.format(2)
    }

    fun atualizarCargas(){
        atualizarCargasPermanentes()
        atualizarSobrecargas()
        atualizarCargaTotal()
    }

    private fun atualizarSobrecargas(){
        sobrecargaNormativa.value = ESCADA.sobrecargaNormativa
        sobrecargaManualPatamares.value = ESCADA.sobrecargaManualPatamares
        sobrecargaManualLance.value = ESCADA.sobrecargaManualLance
        sobrecargaTotalPatamares.value = ESCADA.sobrecargaTotalPatamares
        sobrecargaTotalLance.value = ESCADA.sobrecargaTotalLance
    }

    private fun atualizarCargasPermanentes(){
        pesoProprioPatamares.value = ESCADA.pesoProprioPatamares
        pesoProprioLance.value = ESCADA.pesoProprioLance
        cargaPermanenteManuallPatamares.value = ESCADA.cargaPermanenteManualPatamares
        cargaPermanenteManualLance.value = ESCADA.cargaPermanenteManualLance
        cargaPermanenteTotalPatamares.value = ESCADA.cargaPermanenteTotalPatamares
        cargaPermanenteTotalLance.value = ESCADA.cargaPermanenteTotalLance
    }

    private fun atualizarCargaTotal() {
        cargaTotalPatamaresELU.value = ESCADA.cargaTotalPatamaresELU
        cargaTotalLanceELU.value = ESCADA.cargaTotalLanceELU

        cargaTotalPatamaresELS.value = ESCADA.cargaTotalPatamaresELS
        cargaTotalLanceELS.value = ESCADA.cargaTotalLanceELS
    }

    init{
        atualizarCargas()
    }

}