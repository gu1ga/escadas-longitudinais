package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.flecha

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.BooleanToYesOrNo.Companion.simOuNao
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format


class FlechaViewModel : ViewModel() {


    companion object{
        const val ZERO = 0.toDouble()
    }

    private val momentoAtuante = MutableLiveData(ZERO)
    val momentoAtuanteString = momentoAtuante.map{
        when(momentoAtuante.value){
            ZERO -> ""
            else -> momentoAtuante.value?.format(2)
        }
    }
    private val momentoFissuracao = MutableLiveData(ZERO)
    val momentoFissuracaoString = momentoFissuracao.map{
        when(momentoFissuracao.value){
            ZERO -> ""
            else -> momentoFissuracao.value?.format(2)
        }
    }
    private val pecaFissurada = MutableLiveData(false)
    val pecaFissuradaString = pecaFissurada.map{
           pecaFissurada.value?.simOuNao()
    }
    private val rigidezTotal = MutableLiveData(ZERO)
    val rigidezTotalString = rigidezTotal.map{
        when(rigidezTotal.value){
            ZERO -> ""
            else -> rigidezTotal.value?.format(2)
        }
    }
    private val alturaLinhaNeutraEstadioII = MutableLiveData(ZERO)
    val alturaLinhaNeutraEstadioIIString = alturaLinhaNeutraEstadioII.map{
        when(alturaLinhaNeutraEstadioII.value){
            ZERO -> ""
            else -> alturaLinhaNeutraEstadioII.value?.format(2)
        }
    }
    private val momentoInerciaEstadioII = MutableLiveData(ZERO)
    val momentoInerciaEstadioIIString = momentoInerciaEstadioII.map{
        when(momentoInerciaEstadioII.value){
            ZERO -> ""
            else -> momentoInerciaEstadioII.value?.format(2)
        }
    }
    private val rigidezEquivalente = MutableLiveData(ZERO)
    val rigidezEquivalenteString = rigidezEquivalente.map{
        when(rigidezEquivalente.value){
            ZERO -> ""
            else -> rigidezEquivalente.value?.format(2)
        }
    }
    private val rigidezFinal = MutableLiveData(ZERO)
    val rigidezFinalString = rigidezFinal.map{
        when(rigidezFinal.value){
            ZERO -> ""
            else -> rigidezFinal.value?.format(2)
        }
    }
    private val flechaImediata = MutableLiveData(ZERO)
    val flechaImediataString = flechaImediata.map{
        when(flechaImediata.value){
            ZERO -> ""
            else -> flechaImediata.value?.format(2)
        }
    }
    private val flechaDiferida = MutableLiveData(ZERO)
    val flechaDiferidaString = flechaDiferida.map{
        when(flechaDiferida.value){
            ZERO -> ""
            else -> flechaDiferida.value?.format(2)
        }
    }
    private val flechaTotal = MutableLiveData(ZERO)
    val flechaTotalString = flechaTotal.map{
        when(flechaTotal.value){
            ZERO -> ""
            else -> flechaTotal.value?.format(2)
        }
    }
    private val flechaLimite = MutableLiveData(ZERO)
    val flechaLimiteString = flechaLimite.map{
        when(flechaLimite.value){
            ZERO -> ""
            else -> flechaLimite.value?.format(2)
        }
    }

    val areaAcoEfetivoPorMetroPositivoPrincipal = MutableLiveData(ZERO)


    //EVENTOS
    val eventAtualizarEspacamentosAcoPositivoPrincipal = ViewModelEvent()

    //SPINERS LISTENERS
    //ARMADURA POSITIVA PRINCIPAL
    val bitolaArmaduraPositivaPrincipalOnClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val bitolaPrevia = ESCADA.armaduraPositivaPrincipal.bitolaAtual.toString()
            val bitolaSelecionada = parent.getItemAtPosition(position).toString()
            ESCADA.armaduraPositivaPrincipal.selecionarBitolaAtual(bitolaSelecionada)
            if(bitolaSelecionada != bitolaPrevia){
                eventAtualizarEspacamentosAcoPositivoPrincipal.trigger()
            }
            atualizarAreaAcoEfetivoPorMetroPositivoPrincipal()
        }
    }

    val espacamentoArmaduraPositivaPrincipalOnClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val espacamentoSelecionado = parent.getItemAtPosition(position).toString().toInt()
            ESCADA.apply{
                armaduraPositivaPrincipal.espacamentoAtual = espacamentoSelecionado
                calcularQuantidades()
            }
            atualizarAreaAcoEfetivoPorMetroPositivoPrincipal()
        }
    }


    init{
        atualizarValores()

    }

    fun verificarFlecha(){
        ESCADA.verificarFlecha()
        atualizarTabela()
    }

    fun atualizarValores() {
        atualizarTabela()
        atualizarAreaAcoEfetivoPorMetroPositivoPrincipal()
    }

    private fun atualizarTabela(){
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

    private fun atualizarAreaAcoEfetivoPorMetroPositivoPrincipal() {
        areaAcoEfetivoPorMetroPositivoPrincipal.value = ESCADA.armaduraPositivaPrincipal.areaAcoEfetivaPorMetro
    }
}