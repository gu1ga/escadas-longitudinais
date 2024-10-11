package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.processamento

import Constantes.ESPESSURA_MAXIMA
import Constantes.ESPESSURA_MINIMA_NORMATIVA
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.corte.CorteViewModel

class ProcessamentoViewModel : ViewModel() {

    companion object{
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
    }

    val espessura = MutableLiveData(EMPTY_STRING)
    private val valorEspessura: Double
        get() = espessura.value?.toDoubleOrNull() ?: ZERO

    val cobrimentoEfetivo = MutableLiveData(EMPTY_STRING)
    private val valorCobrimentoEfetivo: Double
        get() = cobrimentoEfetivo.value?.toDoubleOrNull() ?: ZERO

    //TRANSFORMATIONS
    val espessuraTransformation = espessura.map{
        if(checarEspessura()){
            ESCADA.espessura = valorEspessura
        }
    }

    val cobrimentoEfetivoTransformation = cobrimentoEfetivo.map{
        if(checarCobrimentoEfetivo()){
            ESCADA.cobrimentoEfetivo = valorCobrimentoEfetivo
        }
    }

    //EVENTS
    //TEXTOS
    val eventFlexaoOk = ViewModelEvent()
    val eventFlexaoNaoOk = ViewModelEvent()
    val eventFlechaOk = ViewModelEvent()
    val eventFlechaNaoOk = ViewModelEvent()
    val eventCisalhamentoOk = ViewModelEvent()
    val eventCisalhamentoNaoOk = ViewModelEvent()

    //CANPOS
    val eventEspessuraForaDeNorma = ViewModelEvent()
    val eventEspessuraForaDeIntervalo = ViewModelEvent()
    val eventEspessuraOk = ViewModelEvent()



    fun dimensionar(){
        ESCADA.calcularEsforcosCaracteristicos()
        ESCADA.calcularAreasDeAco()
        ESCADA.detalharEsacada()
        ESCADA.verificarFlecha()
        checarDimensionamento()
        Escada.apply{
            geometriaDefinida = true
            dimensionamentoRealizado = true
        }
    }

    init{
        atualizarValores()
    }

    private fun checarEspessura() : Boolean{
        if(valorEspessura > ESPESSURA_MINIMA_NORMATIVA){
            if(valorEspessura <= ESPESSURA_MAXIMA){
                eventEspessuraOk.trigger()
                return true
            }
            eventEspessuraForaDeIntervalo.trigger()
            return false
        }
        if(valorEspessura != CorteViewModel.ZERO){
            eventEspessuraForaDeNorma.trigger()
            return false
        }
        eventEspessuraOk.trigger()
        return false
    }

    private fun checarCobrimentoEfetivo() : Boolean{
        //TODO
        return true
    }

    private fun checarDimensionamento() {
        when(ESCADA.flexaoOk){
            true -> eventFlexaoOk.trigger()
            false -> eventFlexaoNaoOk.trigger()
        }
        when(ESCADA.flechaOk){
            true -> eventFlechaOk.trigger()
            false -> eventFlechaNaoOk.trigger()
        }
        when(ESCADA.cisalhamentoOK){
            true -> eventCisalhamentoOk.trigger()
            false -> eventCisalhamentoNaoOk.trigger()
        }
    }

    fun atualizarValores() {
        espessura.value = if(ESCADA.espessura != ZERO) ESCADA.espessura.format(2) else EMPTY_STRING
        cobrimentoEfetivo.value = if(ESCADA.cobrimentoEfetivo != ZERO) ESCADA.cobrimentoEfetivo.format(2) else EMPTY_STRING
    }
}