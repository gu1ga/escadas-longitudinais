package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.degraus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.*


class DegrausViewModel : ViewModel() {

    companion object {
        const val EMPTY_STRING = ""
        const val ZERO = 0.0
    }

    val piso = MutableLiveData(EMPTY_STRING)
    private val valorPiso: Double
        get() = piso.value?.toDoubleOrNull() ?: ZERO

    val espelho = MutableLiveData(EMPTY_STRING)
    private val valorEspelho: Double
        get() = espelho.value?.toDoubleOrNull() ?: ZERO

    //Transformations
    val pisoTransformation = piso.map{
        if(checarPisoValido() && checarConsistenciaGeral()){
            ESCADA.piso = valorPiso
            checarBlondelOk()
        }
        else{
            ESCADA.piso = ZERO
        }
    }

    val espelhoTransformation = espelho.map{
        if(checarEspelhoValido() && checarConsistenciaGeral()){
            ESCADA.espelho = valorEspelho
            checarBlondelOk()
        }
        else{
            ESCADA.espelho = ZERO
        }
    }

    //Events
    val eventPisoInvalido = ViewModelEvent()
    val eventPisoForaDeIntervalo = ViewModelEvent()
    val eventPisoOk = ViewModelEvent()
    val eventImpossivelSugerirPiso = ViewModelEvent()
    val eventImpossivelSugerirPisoOk = ViewModelEvent()

    val eventEspelhoInvalido = ViewModelEvent()
    val eventEspelhoForaDeIntervalo = ViewModelEvent()
    val eventEspelhoOk = ViewModelEvent()
    val eventImpossivelSugerirEspelho = ViewModelEvent()
    val eventImpossivelSugerirEspelhoOk = ViewModelEvent()

    val eventGeometriaInvalida = ViewModelEvent()
    val eventGeometriaInvalidaOk= ViewModelEvent()

    val eventBlondelMenor = ViewModelEvent()
    val eventBlondeMaior = ViewModelEvent()
    val eventBlondelOk = ViewModelEvent()

    val eventComprimentoLanceIndefinido = ViewModelEvent()
    val eventComprimentoLanceIndefinidoOk = ViewModelEvent()

    val eventPeDireitoLanceIndefinido = ViewModelEvent()
    val eventPeDireitoLanceIndefinidoOk = ViewModelEvent()

    //Métodos públicos
    fun sugerirGeometria(){

        if(ESCADA.comprimentoLance == ZERO){
            eventComprimentoLanceIndefinido.trigger()
            if(ESCADA.peDireitoLance == ZERO){
                eventPeDireitoLanceIndefinido.trigger()
            }
            return
        }

        eventComprimentoLanceIndefinidoOk.trigger()
        eventPeDireitoLanceIndefinidoOk.trigger()

        if(valorPiso == ZERO && valorEspelho == ZERO){
            sugerirGeometriaTotal()
            return
        }

        if(valorPiso != ZERO){
            sugerirGeometriaComPisoFixo(valorPiso)
            return
        }

        if(valorEspelho!=ZERO){
            sugerirGeometriaComEspelhoFixo(valorEspelho)
            return
        }
    }

    fun atualizarValores(){
        espelho.value = if(ESCADA.espelho != ZERO) ESCADA.espelho.format(2) else EMPTY_STRING
        piso.value = if(ESCADA.piso != ZERO) ESCADA.piso.format(2) else EMPTY_STRING
    }

    //Métodos priados
    private fun checarPisoValido() : Boolean {

        if(ESCADA.comprimentoLance == ZERO){

            if(ESCADA.piso == ZERO){
                eventPisoOk.trigger()
                return false
            }
            eventComprimentoLanceIndefinido.trigger()
            return false
        }

        eventComprimentoLanceIndefinidoOk.trigger()

        if(valorPiso in PISO_MINIMO..PISO_MAXIMO){
            if(pisoValido(valorPiso, ESCADA.comprimentoLance)){
                eventPisoOk.trigger()
                return true
            }
            eventPisoInvalido.trigger()
            return false
        }
        if(valorPiso != ZERO){
            eventPisoForaDeIntervalo.trigger()
            return false
        }

        eventPisoOk.trigger()
        return false
    }

    private fun checarConsistenciaGeral(): Boolean {

        if(valorPiso == ZERO || valorEspelho == ZERO){
            eventGeometriaInvalidaOk.trigger()
            return true
        }

        val consistencia =  checarConsistenciaPisoEspelho(valorPiso, valorEspelho, ESCADA.comprimentoLance, ESCADA.peDireitoLance)

        if(consistencia){
            eventGeometriaInvalidaOk.trigger()
            return true
        }

        eventGeometriaInvalida.trigger()
        return false
    }

    private fun checarBlondelOk(){

        if(valorPiso == ZERO || valorEspelho == ZERO){
            eventBlondelOk.trigger()
            return
        }

        if(checarBlondel(valorPiso, valorEspelho)){
            eventBlondelOk.trigger()
            return
        }

        if(checarBlondelMaior(valorPiso, valorEspelho)){
            eventBlondeMaior.trigger()
            return
        }

        if(checarBlondelMenor(valorPiso, valorEspelho)){
            eventBlondeMaior.trigger()
            return
        }
    }

    private fun checarEspelhoValido() : Boolean{

        if(ESCADA.peDireitoLance == ZERO){

            if(ESCADA.espelho == ZERO){
                eventEspelhoOk.trigger()
                return false
            }

            eventPeDireitoLanceIndefinido.trigger()
            return false
        }

        eventPeDireitoLanceIndefinidoOk.trigger()

        if(valorEspelho in ESPELHO_MINIMO..ESPELHO_MAXIMO){
            if(espelhoValido(valorEspelho, ESCADA.peDireitoLance)){
                eventEspelhoOk.trigger()
                return true
            }
            eventEspelhoInvalido.trigger()
            return false
        }
        if(valorEspelho != ZERO){
            eventEspelhoForaDeIntervalo.trigger()
            return false
        }
        eventEspelhoOk.trigger()
        return false
    }

    private fun sugerirGeometriaComPisoFixo(piso: Double){

        if(!checarPisoValido()){
            return
        }

        val numeroDegraus = (ESCADA.comprimentoLance/piso).toInt()
        val espelhoCalculado = ESCADA.peDireitoLance/(numeroDegraus + 1)

        if(espelhoCalculado in ESPELHO_MINIMO..ESPELHO_MAXIMO){
            espelho.value = espelhoCalculado.format(2)
            eventImpossivelSugerirEspelhoOk.trigger()
            return
        }

        espelho.value = EMPTY_STRING
        eventImpossivelSugerirEspelho.trigger()
        return
    }

    private fun sugerirGeometriaComEspelhoFixo(espelho: Double){

        if(!checarEspelhoValido()){
            return
        }

        val numeroDegraus = (ESCADA.peDireitoLance/espelho).toInt() - 1
        val pisoCalculado = ESCADA.comprimentoLance/numeroDegraus

        if(pisoCalculado in PISO_MINIMO..PISO_MAXIMO){
            piso.value = pisoCalculado.format(2)
            eventImpossivelSugerirPisoOk.trigger()
            return
        }

        piso.value = EMPTY_STRING
        eventImpossivelSugerirPiso.trigger()
        return
    }

    private fun sugerirGeometriaTotal(){
        var espelhoCalculado = sugerirEspelho(ESCADA.peDireitoLance)
        var pisoCalculado = sugerirPisoPeloEspelho(ESCADA.comprimentoLance, ESCADA.peDireitoLance, espelhoCalculado)


        if(espelhoCalculado in ESPELHO_MINIMO..ESPELHO_MAXIMO && pisoCalculado in PISO_MINIMO..PISO_MAXIMO){
            if(espelhoValido(espelhoCalculado, ESCADA.peDireitoLance) && pisoValido(pisoCalculado, ESCADA.comprimentoLance)){
                espelho.value = espelhoCalculado.format(2)
                piso.value = pisoCalculado.format(2)

                return
            }
        }

        eventImpossivelSugerirEspelho.trigger()
        eventImpossivelSugerirPiso.trigger()
        return
    }

    //Init
    init {
        atualizarValores()
    }
}

