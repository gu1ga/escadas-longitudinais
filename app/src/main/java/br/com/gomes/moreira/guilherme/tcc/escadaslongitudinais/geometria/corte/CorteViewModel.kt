package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.corte

import Constantes.ALTURA_APOIO_MAXIMA
import Constantes.DOIS_LANCES
import Constantes.ESPESSURA_MAXIMA
import Constantes.ESPESSURA_MINIMA_NORMATIVA
import Constantes.PE_DIREITO_MAXIMO_DOIS_LANCES
import Constantes.PE_DIREITO_MAXIMO_UM_LANCE
import Constantes.PE_DIREITO_MINIMO_DOIS_LANCES
import Constantes.PE_DIREITO_MINIMO_UM_LANCE
import Constantes.UM_LANCE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format


class CorteViewModel : ViewModel() {

    companion object{
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
    }

    val imagemId: MutableLiveData<Int> = MutableLiveData()

    val peDireito = MutableLiveData(EMPTY_STRING)
    private val valorPeDireito: Double
        get() = peDireito.value?.toDoubleOrNull() ?: ZERO

    val espessura = MutableLiveData(EMPTY_STRING)
    private val valorEspessura: Double
        get() = espessura.value?.toDoubleOrNull() ?: ZERO

    val alturaApoioEsquerdo = MutableLiveData(EMPTY_STRING)
    private val valorAlturaApoioEsquerdo: Double
        get() = alturaApoioEsquerdo.value?.toDoubleOrNull() ?: ZERO

    val alturaApoioDireito = MutableLiveData(EMPTY_STRING)
    private val valorAlturaApoioDireito: Double
        get() = alturaApoioDireito.value?.toDoubleOrNull() ?: ZERO

    val peDireitoTransformation = peDireito.map{
        if(checarPeDireitoUmLance()){
            ESCADA.peDireitoTotal = valorPeDireito
        }
        else{
            ESCADA.peDireitoTotal = ZERO
        }
    }
    val espessuraTransformation = espessura.map{
        if(checarEspessura()){
            ESCADA.espessura = valorEspessura
        }
        else{
            ESCADA.espessura = ZERO
        }
    }
    val apoioEsquerdoTransformation = alturaApoioEsquerdo.map{
        if(checarApoioEsquerdo()){
            ESCADA.alturaApoioEsquerdo = valorAlturaApoioEsquerdo
        }
        else{
            ESCADA.alturaApoioEsquerdo = ZERO
        }
    }
    val apoioDireitoTransformation = alturaApoioDireito.map{
        if(checarApoioDireito()){
            ESCADA.alturaApoioDireito = valorAlturaApoioDireito
        }
        else{
            ESCADA.alturaApoioDireito = ZERO
        }
    }

    //Eventos
    val eventPeDireitoUmLanceForaDeIntervalo = ViewModelEvent()
    val eventPeDireitoDoisLancesForaDeIntervalo = ViewModelEvent()
    val eventPeDireitoOk = ViewModelEvent()

    val eventEspessuraForaDeNorma = ViewModelEvent()
    val eventEspessuraForaDeIntervalo = ViewModelEvent()
    val eventEspessuraOk = ViewModelEvent()

    val eventApoioEsquerdoForaDeIntervalo = ViewModelEvent()
    val eventApoioEsquerdoOk = ViewModelEvent()

    val eventApoioDireitoForaDeIntervalo = ViewModelEvent()
    val eventApoioDireitoOk = ViewModelEvent()

    private fun checarPeDireitoUmLance() : Boolean{
        if(valorPeDireito in PE_DIREITO_MINIMO_UM_LANCE..PE_DIREITO_MAXIMO_UM_LANCE){
            eventPeDireitoOk.trigger()
            return true
        }
        if(valorPeDireito != ZERO){
            eventPeDireitoUmLanceForaDeIntervalo.trigger()
            return false
        }
        eventPeDireitoOk.trigger()
        return false
    }

    private fun checarPeDireitoDoisLances() : Boolean{
        if(valorPeDireito in PE_DIREITO_MINIMO_DOIS_LANCES..PE_DIREITO_MAXIMO_DOIS_LANCES){
            eventPeDireitoOk.trigger()
            return true
        }
        if(valorPeDireito != ZERO){
            eventPeDireitoDoisLancesForaDeIntervalo.trigger()
            return false
        }
        eventPeDireitoOk.trigger()
        return false
    }

    private fun checarEspessura() : Boolean{
        if(valorEspessura >= ESPESSURA_MINIMA_NORMATIVA){
            if(valorEspessura <= ESPESSURA_MAXIMA){
                eventEspessuraOk.trigger()
                return true
            }
            eventEspessuraForaDeIntervalo.trigger()
            return false
        }
        if(valorEspessura != ZERO){
            eventEspessuraForaDeNorma.trigger()
            return false
        }
        eventEspessuraOk.trigger()
        return false
    }
    private fun checarApoioEsquerdo() : Boolean{
        if(valorAlturaApoioEsquerdo in maxOf(valorEspessura, ESPESSURA_MINIMA_NORMATIVA) .. ALTURA_APOIO_MAXIMA){
            eventApoioEsquerdoOk.trigger()
            return true
        }
        if(valorAlturaApoioEsquerdo != ZERO){
            eventApoioEsquerdoForaDeIntervalo.trigger()
            return false
        }
        eventApoioEsquerdoOk.trigger()
        return false
    }
    private fun checarApoioDireito() : Boolean{
        if(valorAlturaApoioDireito in maxOf(valorEspessura, ESPESSURA_MINIMA_NORMATIVA) .. ALTURA_APOIO_MAXIMA){
            eventApoioDireitoOk.trigger()
            return true
        }
        if(valorAlturaApoioDireito != ZERO){
            eventApoioDireitoForaDeIntervalo.trigger()
            return false
        }
        eventApoioDireitoOk.trigger()
        return false
    }

    private fun definirImagem(){

        if(ESCADA.numeroLances == UM_LANCE){
            imagemId.value = when(ESCADA.existePatamarInicial && ESCADA.existePatamarIntermediario){
                true  -> R.drawable.geometria_corte_escada_1_lances_2_patamares_cotas
                false -> when(!ESCADA.existePatamarInicial && !ESCADA.existePatamarIntermediario){
                    true  -> R.drawable.geometria_corte_escada_1_lances_sem_patamar_cotas
                    false -> when(ESCADA.existePatamarInicial){
                        true  -> R.drawable.geometria_corte_escada_1_lances_1_patamar_ini_cotas
                        false -> R.drawable.geometria_corte_escada_1_lances_1_patamar_int_cotas
                    }
                }
            }
        }
        else if(ESCADA.numeroLances == DOIS_LANCES){
            imagemId.value = when(ESCADA.existePatamarInicial && ESCADA.existePatamarIntermediario){
                true  -> R.drawable.geometria_corte_escada_2_lances_2_patamares_cotas
                false -> when(!ESCADA.existePatamarInicial && !ESCADA.existePatamarIntermediario){
                    true  -> R.drawable.geometria_corte_escada_2_lances_sem_patamar_cotas
                    false -> when(ESCADA.existePatamarInicial){
                        true  -> R.drawable.geometria_corte_escada_2_lances_1_patamar_ini_cotas
                        false -> R.drawable.geometria_corte_escada_2_lances_1_patamar_int_cotas
                    }
                }
            }
        }
    }

    private fun inicializarValores() {
        peDireito.value = if (ESCADA.peDireitoTotal != ZERO) ESCADA.peDireitoTotal.format(2) else EMPTY_STRING
        espessura.value = if (ESCADA.espessura != ZERO) ESCADA.espessura.format(2) else EMPTY_STRING
        alturaApoioEsquerdo.value = if (ESCADA.alturaApoioEsquerdo != ZERO) ESCADA.alturaApoioEsquerdo.format(2) else EMPTY_STRING
        alturaApoioDireito.value = if (ESCADA.alturaApoioDireito != ZERO) ESCADA.alturaApoioDireito.format(2) else EMPTY_STRING
    }

    fun atualizarUI(){
        definirImagem()
        inicializarValores()
    }

    init{
        atualizarUI()
    }
}