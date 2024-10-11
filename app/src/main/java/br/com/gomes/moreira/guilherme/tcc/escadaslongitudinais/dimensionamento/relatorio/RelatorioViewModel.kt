package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.relatorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format


class RelatorioViewModel : ViewModel() {

    companion object{
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
        const val INT_ZERO = 0
    }

    //GEOMETRIA
    private val numeroLances = MutableLiveData(INT_ZERO)
    val numeroLancesString = numeroLances.map {
        when(it){
            1 -> "Um Lance"
            2 -> "Dois Lances"
            else -> "Indefinido"
        }
    }

    private val patamares = MutableLiveData(EMPTY_STRING)
    val patamaresString: LiveData<String>
        get() = patamares

    private val comprimentoPatamarInicial = MutableLiveData(ZERO)
    val comprimentoPatamarInicialString = comprimentoPatamarInicial.map {
        it.format(2)
    }

    private val larguraPatamarInicial = MutableLiveData(ZERO)
    val larguraPatamarInicialString = larguraPatamarInicial.map {
        it.format(2)
    }

    private val comprimentoPatamarIntermediario = MutableLiveData(ZERO)
    val comprimentoPatamarIntermediarioString = comprimentoPatamarIntermediario.map {
        it.format(2)
    }

    private val larguraPatamarIntermediario = MutableLiveData(ZERO)
    val larguraPatamarIntermediarioString = larguraPatamarIntermediario.map {
        it.format(2)
    }

    private val comprimentoLance = MutableLiveData(ZERO)
    val comprimentoLanceString = comprimentoLance.map {
        it.format(2)
    }

    private val larguraLance = MutableLiveData(ZERO)
    val larguraLanceString = larguraLance.map {
        it.format(2)
    }

    private val espessura = MutableLiveData(ZERO)
    val espessuraString = espessura.map {
        it.format(2)
    }

    private val peDireito = MutableLiveData(ZERO)
    val peDireitoString = peDireito.map {
        it.format(2)
    }

    private val peDireitoLance = MutableLiveData(ZERO)
    val peDireitoLanceString = peDireitoLance.map {
        it.format(2)
    }

    private val numeroDegraus = MutableLiveData(INT_ZERO)
    val numeroDegrausString = numeroDegraus.map {
        "$it Degraus"
    }

    private val piso = MutableLiveData(ZERO)
    val pisoString = piso.map {
        it.format(2)
    }

    private val espelho = MutableLiveData(ZERO)
    val espelhoString = espelho.map {
        it.format(2)
    }

    private val baseApoioEsquerdo = MutableLiveData(ZERO)
    val baseApoioEsquerdoString = baseApoioEsquerdo.map {
        it.format(2)
    }

    private val alturaApoioEsquerdo = MutableLiveData(ZERO)
    val alturaApoioEsquerdoString = alturaApoioEsquerdo.map {
        it.format(2)
    }

    private val baseApoioDireito = MutableLiveData(ZERO)
    val baseApoioDireitoString = baseApoioDireito.map {
        it.format(2)
    }

    private val alturaApoioDireito = MutableLiveData(ZERO)
    val alturaApoioDireitoString = alturaApoioDireito.map {
        it.format(2)
    }

    //CARGAS
    private val pesoProprioPatamares = MutableLiveData(ZERO)
    val pesoProprioPatamaresString = pesoProprioPatamares.map {
        it.format(2)
    }

    private val pesoProprioLances = MutableLiveData(ZERO)
    val pesoProprioLancesString = pesoProprioLances.map {
        it.format(2)
    }

    private val cargaPermanenteManualPatamares = MutableLiveData(ZERO)
    val cargaPermanenteManualPatamaresString = cargaPermanenteManualPatamares.map {
        it.format(2)
    }

    private val cargaPermanenteManualLances = MutableLiveData(ZERO)
    val cargaPermanenteManualLancesString = cargaPermanenteManualLances.map {
        it.format(2)
    }

    private val cargaPermanenteTotalPatamares = MutableLiveData(ZERO)
    val cargaPermanenteTotalPatamaresString = cargaPermanenteTotalPatamares.map {
        it.format(2)
    }

    private val cargaPermanenteTotalLances = MutableLiveData(ZERO)
    val cargaPermanenteTotalLancesString = cargaPermanenteTotalLances.map {
        it.format(2)
    }

    private val sobrecargaNormativaPatamares = MutableLiveData(ZERO)
    val sobrecargaNormativaPatamaresString = sobrecargaNormativaPatamares.map {
        it.format(2)
    }

    private val sobrecargaNormativaLances = MutableLiveData(ZERO)
    val sobrecargaNormativaLancesString = sobrecargaNormativaLances.map {
        it.format(2)
    }

    private val sobrecargaManualPatamares = MutableLiveData(ZERO)
    val sobrecargaManualPatamaresString = sobrecargaManualPatamares.map {
        it.format(2)
    }

    private val sobrecargaManualLances = MutableLiveData(ZERO)
    val sobrecargaManualLancesString = sobrecargaManualLances.map {
        it.format(2)
    }

    private val sobrecargaTotalPatamares = MutableLiveData(ZERO)
    val sobrecargaTotalPatamaresString = sobrecargaTotalPatamares.map {
        it.format(2)
    }

    private val sobrecargaTotalLances = MutableLiveData(ZERO)
    val sobrecargaTotalLancesString = sobrecargaTotalLances.map {
        it.format(2)
    }

    private val cargaTotalPatamaresELU = MutableLiveData(ZERO)
    val cargaTotalPatamaresELUString = cargaTotalPatamaresELU.map {
        it.format(2)
    }

    private val cargaTotalLancesELU = MutableLiveData(ZERO)
    val cargaTotalLancesELUString = cargaTotalLancesELU.map {
        it.format(2)
    }

    private val cargaTotalPatamaresELS = MutableLiveData(ZERO)
    val cargaTotalPatamaresELSString = cargaTotalPatamaresELS.map {
        it.format(2)
    }

    private val cargaTotalLancesELS = MutableLiveData(ZERO)
    val cargaTotalLancesELSString = cargaTotalLancesELS.map {
        it.format(2)
    }

    //FLEXAO
    private val fyk = MutableLiveData(ZERO)
    val fykString = fyk.map {
        it.format(2)
    }

    private val fyd = MutableLiveData(ZERO)
    val fydString = fyd.map {
        it.format(2)
    }

    private val fck = MutableLiveData(ZERO)
    val fckString = fck.map {
        it.format(2)
    }

    private val fcd = MutableLiveData(ZERO)
    val fcdString = fcd.map {
        it.format(2)
    }

    private val momentoCaracteristico = MutableLiveData(ZERO)
    val momentoCaracteristicoString = momentoCaracteristico.map {
        it.format(2)
    }

    private val momentoDeCalculo = MutableLiveData(ZERO)
    val momentoDeCalculoString = momentoDeCalculo.map {
        it.format(2)
    }

    private val alturaUtil = MutableLiveData(ZERO)
    val alturaUtilString = alturaUtil.map {
        it.format(2)
    }

    private val alturaLinhaNeutra = MutableLiveData(ZERO)
    val alturaLinhaNeutraString = alturaLinhaNeutra.map {
        it.format(2)
    }

    private val kx = MutableLiveData(ZERO)
    val kxString = kx.map {
        it.format(2)
    }

    private val dominio = MutableLiveData(EMPTY_STRING)
    val dominioString: LiveData<String>
        get() = dominio

    private val areaAcoPrincipal = MutableLiveData(ZERO)
    val areaAcoPrincipalString = dominio.map {
        it.format(2)
    }

    private val areaAcoSecundaria = MutableLiveData(ZERO)
    val areaAcoSecundariaString = areaAcoSecundaria.map {
        it.format(2)
    }

    private val areaAcoNegativo = MutableLiveData(ZERO)
    val areaAcoNegativoString = areaAcoNegativo.map {
        it.format(2)
    }

    //FLECHA
    private val momentoAtuante = MutableLiveData(ZERO)
    val momentoAtuanteString = momentoAtuante.map {
        it.format(2)
    }

    private val momentoFissuracao = MutableLiveData(ZERO)
    val momentoFissuracaoString = momentoFissuracao.map {
        it.format(2)
    }

    private val pecaFissurada = MutableLiveData(false)
    val pecaFissuradaString = pecaFissurada.map {
        when (it) {
            true -> "Peça fissurada"
            false -> "Peça não fissurada"
        }
    }

    private val rigidezTotal = MutableLiveData(ZERO)
    val rigidezTotalString = rigidezTotal.map {
        it.format(2)
    }

    private val alturaLinhaNeutraEstadioII = MutableLiveData(ZERO)
    val alturaLinhaNeutraEstadioIIString = alturaLinhaNeutraEstadioII.map {
        it.format(2)
    }

    private val momentoInerciaEstadioII = MutableLiveData(ZERO)
    val momentoInerciaEstadioIIString = momentoInerciaEstadioII.map {
        it.format(2)
    }

    private val rigidezEquivalente = MutableLiveData(ZERO)
    val rigidezEquivalenteString = rigidezEquivalente.map {
        it.format(2)
    }

    private val rigidezFinal = MutableLiveData(ZERO)
    val rigidezFinalString = rigidezFinal.map {
        it.format(2)
    }

    private val flechaImediata = MutableLiveData(ZERO)
    val flechaImediataString = flechaImediata.map {
        it.format(2)
    }

    private val flechaDiferida = MutableLiveData(ZERO)
    val flechaDiferidaString = flechaDiferida.map {
        it.format(2)
    }

    private val flechaTotal = MutableLiveData(ZERO)
    val flechaTotalString = flechaTotal.map {
        it.format(2)
    }

    private val flechaLimite = MutableLiveData(ZERO)
    val flechaLimiteString = flechaLimite.map {
        it.format(2)
    }

    //TABELA DE FERROS
    private val pesoTotal = MutableLiveData(0)
    val pesoTotalString = pesoTotal.map{
        it.toString()
    }

    init {
        inicializarValores()
    }

    fun inicializarValores(){
        atualizarValoresGeometria()
        atualizarValoresCargas()
        atualizarValoresFlexao()
        atualizarValoresFlecha()
        atualizarValoresTableaDeFerros()
    }

    private fun atualizarValoresGeometria(){
        numeroLances.value = ESCADA.numeroLances
        patamares.value = when(ESCADA.existePatamarInicial){
            true -> when(ESCADA.existePatamarIntermediario){
                true -> "Patamares Inicial e Secundário"
                false -> "Um Patamar Inicial"
            }
            false -> when(ESCADA.existePatamarIntermediario){
                true -> "Um Patamar Intermediário"
                false -> "Sem Patamares"
            }
        }

        comprimentoPatamarInicial.value = ESCADA.comprimentoPatamarInicial
        larguraPatamarInicial.value = ESCADA.larguraTotal

        comprimentoPatamarIntermediario.value = ESCADA.comprimentoPatamarIntermediario
        larguraPatamarIntermediario.value = ESCADA.larguraTotal

        comprimentoLance.value = ESCADA.comprimentoLance
        larguraLance.value = ESCADA.larguraPorLance

        espessura.value = ESCADA.espessura

        peDireito.value = ESCADA.peDireitoTotal
        peDireitoLance.value = ESCADA.peDireitoLance

        numeroDegraus.value = ESCADA.numeroDegraus
        piso.value = ESCADA.piso
        espelho.value = ESCADA.espelho

        baseApoioEsquerdo.value = ESCADA.baseApoioEsquerdo
        alturaApoioEsquerdo.value = ESCADA.alturaApoioEsquerdo

        baseApoioDireito.value = ESCADA.baseApoioDireito
        alturaApoioDireito.value = ESCADA.alturaApoioDireito
    }

    private fun atualizarValoresCargas(){
        sobrecargaNormativaPatamares.value = ESCADA.sobrecargaNormativa
        sobrecargaNormativaLances.value = ESCADA.sobrecargaNormativa
        sobrecargaManualPatamares.value = ESCADA.sobrecargaManualPatamares
        sobrecargaManualLances.value = ESCADA.sobrecargaManualLance
        sobrecargaTotalPatamares.value = ESCADA.sobrecargaTotalPatamares
        sobrecargaTotalLances.value = ESCADA.sobrecargaTotalLance

        pesoProprioPatamares.value = ESCADA.pesoProprioPatamares
        pesoProprioLances.value = ESCADA.pesoProprioLance
        cargaPermanenteManualPatamares.value = ESCADA.cargaPermanenteManualPatamares
        cargaPermanenteManualLances.value = ESCADA.cargaPermanenteManualLance
        cargaPermanenteTotalPatamares.value = ESCADA.cargaPermanenteTotalPatamares
        cargaPermanenteTotalLances.value = ESCADA.cargaPermanenteTotalLance

        cargaTotalPatamaresELU.value = ESCADA.cargaTotalPatamaresELU
        cargaTotalLancesELU.value = ESCADA.cargaTotalLanceELU

        cargaTotalPatamaresELS.value = ESCADA.cargaTotalPatamaresELS
        cargaTotalLancesELS.value = ESCADA.cargaTotalLanceELS
    }

    private fun atualizarValoresFlexao(){

        fyk.value = ESCADA.fyk
        fyd.value = ESCADA.fyd
        fck.value = ESCADA.fck
        fcd.value = ESCADA.fcd

        momentoCaracteristico.value = ESCADA.mk
        momentoDeCalculo.value = ESCADA.md
        alturaUtil.value = ESCADA.alturaUtil
        alturaLinhaNeutra.value = ESCADA.alturaLinhaNeutra
        kx.value = ESCADA.kx
        dominio.value = ESCADA.dominio
        areaAcoPrincipal.value = ESCADA.areaAcoPositivaPrincipalNecessaria
        areaAcoSecundaria.value = ESCADA.areaAcoPoisitivaSecundariaNecessaria
        areaAcoNegativo.value = ESCADA.areaAcoNegativaNecessaria
    }

    private fun atualizarValoresFlecha(){
        momentoAtuante.value = ESCADA.momentoELS
        momentoFissuracao.value = ESCADA.momentoFissuracao
        pecaFissurada.value = ESCADA.trechoFissurado
        rigidezTotal.value = ESCADA.rigidezFlexaoTotal
        alturaLinhaNeutraEstadioII.value = ESCADA.alturaLinhaNeutraEstadioII
        momentoInerciaEstadioII.value = ESCADA.momentoInerciaEstadioII
        rigidezEquivalente.value = ESCADA.rigidezEquivalente
        rigidezFinal.value = ESCADA.rigidezFinal
        flechaImediata.value = ESCADA.flechaImediata
        flechaDiferida.value = ESCADA.flechaDiferida
        flechaTotal.value = ESCADA.flechaTotal
        flechaLimite.value = ESCADA.flechaLimite
    }

    private fun atualizarValoresTableaDeFerros(){
        pesoTotal.value = ESCADA.resumoDeAco.pesoTotal
    }
}