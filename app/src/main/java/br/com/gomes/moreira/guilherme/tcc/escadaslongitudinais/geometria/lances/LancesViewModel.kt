package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.lances

import Constantes.DOIS_LANCES
import Constantes.UM_LANCE
import android.util.Log
import androidx.lifecycle.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.adapter.GeometriaFragmentStateAdapter
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.patamares.PatamaresViewModel
import kotlinx.coroutines.*

class LancesViewModel : ViewModel() {

    val umLanceChecked = MutableLiveData(true)
    val doisLancesChecked = MutableLiveData(false)

    val umLanceTransformation = umLanceChecked.map {
        if (it) {
            definirNumeroLances(UM_LANCE)
        }
    }
    val doisLancesTransformation = doisLancesChecked.map {
        if (it) {
            definirNumeroLances(DOIS_LANCES)
        }
    }

    //EVENTS
    val eventNotificarMudancaNosLances = ViewModelEvent()

    //Init
    init {
        atualizarValores()
    }

    fun atualizarValores(){
        atualizarSelecao()
    }

    private fun atualizarSelecao(){
        when(ESCADA.numeroLances){
            UM_LANCE -> umLanceChecked.value = true
            DOIS_LANCES -> doisLancesChecked.value = true
        }
    }

    private fun definirNumeroLances(numeroLances: Int){
        when(numeroLances){
            UM_LANCE -> ESCADA.numeroLances = numeroLances
            DOIS_LANCES -> ESCADA.numeroLances = numeroLances
            else -> {}
        }
    }
}