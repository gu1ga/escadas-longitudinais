package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.planta

import Constantes.COMPRIMENTO_MAXIMO_LANCE
import Constantes.COMPRIMENTO_MAXIMO_LARGURA_POR_LANCE
import Constantes.COMPRIMENTO_MAXIMO_LARGURA_TOTAL
import Constantes.COMPRIMENTO_MAXIMO_PATAMAR
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_LANCE
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_POR_LANCE
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_TOTAL
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR
import Constantes.COMPRIMENTO_MINIMO_NORMATIVO_PATAMAR
import Constantes.DOIS_LANCES
import Constantes.LARGURA_MAXIMA_APOIO
import Constantes.LARGURA_MINIMA_ABSOLUTA_APOIO
import Constantes.UM_LANCE
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format
class PlantaViewModel : ViewModel() {

    companion object {
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
    }

    val imagemId: MutableLiveData<Int> = MutableLiveData()

    val comprimentoPatamarInicial = MutableLiveData(EMPTY_STRING)
    private val valorComprimentoPatamarInicial: Double
        get() = comprimentoPatamarInicial.value?.toDoubleOrNull() ?: ZERO

    val comprimentoLance = MutableLiveData(EMPTY_STRING)
    private val valorComprimentoLance: Double
        get() = comprimentoLance.value?.toDoubleOrNull() ?: ZERO

    val comprimentoPatamarIntermediario = MutableLiveData(EMPTY_STRING)
    private val valorComprimentoPatamarIntermediario: Double
        get() = comprimentoPatamarIntermediario.value?.toDoubleOrNull() ?: ZERO

    val larguraTotal = MutableLiveData(EMPTY_STRING)
    private val valorLarguraTotal: Double
        get() = larguraTotal.value?.toDoubleOrNull() ?: ZERO

    val baseApoioEsquerdo = MutableLiveData(EMPTY_STRING)
    private val valorBaseApoioEsquerdo: Double
        get() = baseApoioEsquerdo.value?.toDoubleOrNull() ?: ZERO

    val baseApoioDireito = MutableLiveData(EMPTY_STRING)
    private val valorBaseApoioDireito: Double
        get() = baseApoioDireito.value?.toDoubleOrNull() ?: ZERO

    val patamarInicialTransformation = comprimentoPatamarInicial.map {
        if (checarComprimentoPatamarInicial() && ESCADA.existePatamarInicial) {
            ESCADA.comprimentoPatamarInicial = valorComprimentoPatamarInicial
        }
        else{
            ESCADA.comprimentoPatamarInicial = ZERO
        }
    }
    val lanceTransformation = comprimentoLance.map {
        if (checarComprimentoLance()) {
            ESCADA.comprimentoLance = valorComprimentoLance
        }
        else{
            ESCADA.comprimentoLance = ZERO
        }
    }
    val patamarIntermediarioTransformation = comprimentoPatamarIntermediario.map {
        if (checarComprimentoPatamarIntermediario() && ESCADA.existePatamarIntermediario) {
            ESCADA.comprimentoPatamarIntermediario = valorComprimentoPatamarIntermediario
        }
        else{
            ESCADA.comprimentoPatamarIntermediario = ZERO
        }
    }
    val larguraTotalTransformation = larguraTotal.map {
        if (checarLargura()) {
            ESCADA.larguraTotal = valorLarguraTotal
        }
        else{
            ESCADA.larguraTotal = ZERO
        }
    }
    val apoioEsquerdoTransformation = baseApoioEsquerdo.map {
        if (checarBaseApoioEsquerdo()) {
            ESCADA.baseApoioEsquerdo = valorBaseApoioEsquerdo
        }
        else{
            ESCADA.baseApoioEsquerdo = ZERO
        }
    }
    val apoioDireitoTransformation = baseApoioDireito.map {
        if (checarBaseApoioDireito()) {
            ESCADA.baseApoioDireito = valorBaseApoioDireito
        }
        else{
            ESCADA.baseApoioDireito = ZERO
        }
    }

    //EVENTOS PARA CHECAR VALROES

    //PATAMAR INICIAL
    val eventPatamarInicialForaDeNorma = ViewModelEvent()
    val eventPatamarInicialForaDeIntervalo = ViewModelEvent()
    val eventPatamarInicialOk = ViewModelEvent()
    val eventEsconderEditTextPatamarInicial = ViewModelEvent()
    val eventMostrarEditTextPatamarInicial = ViewModelEvent()

    //LANCE
    val eventLanceForaDeIntervalo = ViewModelEvent()
    val eventLanceOk = ViewModelEvent()

    //LARGURA
    val eventLarguraTotalForaDeIntervalo = ViewModelEvent()
    val eventLarguraPorLanceForaDeIntervalo = ViewModelEvent()
    val eventLarguraOk = ViewModelEvent()

    //PATAMAR INTERMEDIARIO
    val eventPatamarIntermediarioForaDeNorma = ViewModelEvent()
    val eventPatamarIntermediarioForaDeIntervalo = ViewModelEvent()
    val eventPatamarIntermediarioOk = ViewModelEvent()
    val eventEsconderEditTextPamatarIntermediario = ViewModelEvent()
    val eventMostrarEditTextPatamarIntermediario = ViewModelEvent()

    //APOIO DIREITO
    val eventBaseApoioEsquerdoForaDeIntervalo = ViewModelEvent()
    val eventBaseApoioEsquerdoOk = ViewModelEvent()

    //APOIO ESQUERDO
    val eventBaseApoioDireitoForaDeIntervalo = ViewModelEvent()
    val eventBaseApoioDireitoOk = ViewModelEvent()

    //INIT
    init {
        atualizarUI()
    }

    //METODOS
    private fun checarComprimentoPatamarInicial(): Boolean {
        if (valorComprimentoPatamarInicial in COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR..COMPRIMENTO_MAXIMO_PATAMAR) {
            if (valorComprimentoPatamarInicial < COMPRIMENTO_MINIMO_NORMATIVO_PATAMAR) {
                eventPatamarInicialForaDeNorma.trigger()
                return true
            }
            eventPatamarInicialOk.trigger()
            return true
        }
        if (valorComprimentoPatamarInicial != ZERO) {
            eventPatamarInicialForaDeIntervalo.trigger()
            return false
        }
        eventPatamarInicialOk.trigger()
        return false
    }

    private fun checarComprimentoLance(): Boolean {
        if (valorComprimentoLance in COMPRIMENTO_MINIMO_ABSOLUTO_LANCE..COMPRIMENTO_MAXIMO_LANCE) {
            eventLanceOk.trigger()
            return true
        }
        if (valorComprimentoLance != ZERO) {
            eventLanceForaDeIntervalo.trigger()
            return false
        }
        eventLanceOk.trigger()
        return false
    }

    private fun checarLargura(): Boolean {
        return when(ESCADA.numeroLances){
            1 -> checarLarugaTotalUmLance()
            2 -> checarLarguraTotralDoisLances()
            else -> false
        }
    }

    private fun checarLarugaTotalUmLance(): Boolean {
        if (valorLarguraTotal in COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_POR_LANCE..COMPRIMENTO_MAXIMO_LARGURA_POR_LANCE) {
            eventLarguraOk.trigger()
            return true
        }
        if (valorLarguraTotal != ZERO) {
            eventLarguraPorLanceForaDeIntervalo.trigger()
            return false
        }
        eventLarguraOk.trigger()
        return false
    }

    private fun checarLarguraTotralDoisLances(): Boolean {
        if (valorLarguraTotal in COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_TOTAL..COMPRIMENTO_MAXIMO_LARGURA_TOTAL) {
            eventLarguraOk.trigger()
            return true
        }
        if (valorLarguraTotal != ZERO) {
            eventLarguraTotalForaDeIntervalo.trigger()
            return false
        }
        eventLarguraOk.trigger()
        return false
    }

    private fun checarComprimentoPatamarIntermediario(): Boolean {
        if (valorComprimentoPatamarIntermediario in COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR..COMPRIMENTO_MAXIMO_PATAMAR) {
            if (valorComprimentoPatamarIntermediario < COMPRIMENTO_MINIMO_NORMATIVO_PATAMAR) {
                eventPatamarIntermediarioForaDeNorma.trigger()
                return true
            }
            eventPatamarIntermediarioOk.trigger()
            return true
        }
        if (valorComprimentoPatamarIntermediario != ZERO) {
            eventPatamarIntermediarioForaDeIntervalo.trigger()
            return false
        }
        eventPatamarIntermediarioOk.trigger()
        return false
    }

    private fun checarBaseApoioEsquerdo(): Boolean {
        if (valorBaseApoioEsquerdo in LARGURA_MINIMA_ABSOLUTA_APOIO..LARGURA_MAXIMA_APOIO) {
            eventBaseApoioEsquerdoOk.trigger()
            return true
        }
        if (valorBaseApoioEsquerdo != ZERO) {
            eventBaseApoioEsquerdoForaDeIntervalo.trigger()
            return false
        }
        eventBaseApoioEsquerdoOk.trigger()
        return false
    }

    private fun checarBaseApoioDireito(): Boolean {
        if (valorBaseApoioDireito in LARGURA_MINIMA_ABSOLUTA_APOIO..LARGURA_MAXIMA_APOIO) {
            eventBaseApoioDireitoOk.trigger()
            return true
        }
        if (valorBaseApoioDireito != ZERO) {
            eventBaseApoioDireitoForaDeIntervalo.trigger()
            return false
        }
        eventBaseApoioDireitoOk.trigger()
        return false
    }

    private fun definirImagem() {

        if (ESCADA.numeroLances == UM_LANCE) {
            imagemId.value = when (ESCADA.existePatamarInicial && ESCADA.existePatamarIntermediario) {
                true -> R.drawable.geometria_planta_escada_1_lance_2_patamares_cotas
                false -> when (!ESCADA.existePatamarInicial && !ESCADA.existePatamarIntermediario) {
                    true -> R.drawable.geometria_planta_escada_1_lances_sem_patamar_cotas
                    false -> when (ESCADA.existePatamarInicial) {
                        true -> R.drawable.geometria_planta_escada_1_lance_patamar_ini_cotas
                        false -> R.drawable.geometria_planta_escada_1_lance_patamar_int_cotas
                    }
                }
            }
        } else if (ESCADA.numeroLances == DOIS_LANCES) {
            imagemId.value = when (ESCADA.existePatamarInicial && ESCADA.existePatamarIntermediario) {
                true -> R.drawable.geometria_planta_escada_2_lances_2_patamares_cotas
                false -> when (!ESCADA.existePatamarInicial && !ESCADA.existePatamarIntermediario) {
                    true -> R.drawable.geometria_planta_escada_2_lances_sem_patamar_cotas
                    false -> when (ESCADA.existePatamarInicial) {
                        true -> R.drawable.geometria_planta_escada_2_lances_1_patamar_ini_cotas
                        false -> R.drawable.geometria_planta_escada_2_lances_1_patamar_int_cotas
                    }
                }
            }
        }
    }

    private fun definirEntradas() {
        when (ESCADA.existePatamarInicial) {
            false -> {
                eventEsconderEditTextPatamarInicial.start()
                comprimentoPatamarInicial.value = EMPTY_STRING
            }
            true -> eventMostrarEditTextPatamarInicial.start()
        }
        when (ESCADA.existePatamarIntermediario) {
            false -> {
                eventEsconderEditTextPamatarIntermediario.start()
                comprimentoPatamarIntermediario.value = EMPTY_STRING
            }
            true -> eventMostrarEditTextPatamarIntermediario.start()
        }
    }

    private fun inicializarValores() {
        comprimentoPatamarInicial.value = if (ESCADA.comprimentoPatamarInicial != ZERO) ESCADA.comprimentoPatamarInicial.format(2) else EMPTY_STRING
        comprimentoLance.value = if (ESCADA.comprimentoLance != ZERO) ESCADA.comprimentoLance.format(2) else EMPTY_STRING
        comprimentoPatamarIntermediario.value = if (ESCADA.comprimentoPatamarIntermediario != ZERO) ESCADA.comprimentoPatamarIntermediario.format(2) else EMPTY_STRING
        larguraTotal.value = if (ESCADA.larguraTotal != ZERO) ESCADA.larguraTotal.format(2) else EMPTY_STRING
        baseApoioEsquerdo.value = if (ESCADA.baseApoioEsquerdo != ZERO) ESCADA.baseApoioEsquerdo.format(2) else EMPTY_STRING
        baseApoioDireito.value = if (ESCADA.baseApoioDireito != ZERO) ESCADA.baseApoioDireito.format(2) else EMPTY_STRING
    }

    fun atualizarUI() {
        definirImagem()
        definirEntradas()
        inicializarValores()
    }
}