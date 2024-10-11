package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.flexao

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format

class FlexaoViewModel : ViewModel() {

    companion object {
        const val ZERO = 0.toDouble()
        const val EMPTY_STRING = ""
    }

    //Variáveis do dimensionamento a flexão
    private val fyk = MutableLiveData(ZERO)
    val fykString = fyk.map {
        when (fyk.value) {
            ZERO -> ""
            else -> fyk.value?.format(2)
        }
    }

    private val fyd = MutableLiveData(ZERO)
    val fydString = fyd.map {
        when (fyd.value) {
            ZERO -> ""
            else -> fyd.value?.format(2)
        }
    }

    private val fck = MutableLiveData(ZERO)
    val fckString = fck.map {
        when (fck.value) {
            ZERO -> ""
            else -> fck.value?.format(2)
        }
    }

    private val fcd = MutableLiveData(ZERO)
    val fcdString = fcd.map {
        when (fcd.value) {
            ZERO -> ""
            else -> fcd.value?.format(2)
        }
    }

    private val momentoCaracteristico = MutableLiveData(ZERO)
    val momentoCaracteristicoString = momentoCaracteristico.map {
        when (momentoCaracteristico.value) {
            ZERO -> ""
            else -> momentoCaracteristico.value?.format(2)
        }
    }

    private val momentoDeCalculo = MutableLiveData(ZERO)
    val momentoDeCalculoString = momentoDeCalculo.map {
        when (momentoDeCalculo.value) {
            ZERO -> ""
            else -> momentoDeCalculo.value?.format(2)
        }
    }

    private val alturaUtil = MutableLiveData(ZERO)
    val alturaUtilString = alturaUtil.map {
        when (alturaUtil.value) {
            ZERO -> ""
            else -> alturaUtil.value?.format(2)
        }
    }
    private val alturaLinhaNeutra = MutableLiveData(ZERO)
    val alturaLinhaNeutraString = alturaLinhaNeutra.map {
        when (alturaLinhaNeutra.value) {
            ZERO -> ""
            else -> alturaLinhaNeutra.value?.format(2)
        }
    }
    private val kx = MutableLiveData(ZERO)
    val kxString = kx.map {
        when (kx.value) {
            ZERO -> ""
            else -> kx.value?.format(2)
        }
    }
    private val dominio = MutableLiveData(EMPTY_STRING)
    val dominioString: LiveData<String>
        get() = dominio

    private val areaAcoPositivoPrincipal = MutableLiveData(ZERO)
    val areaAcoPositivoPrincipalString = areaAcoPositivoPrincipal.map {
        when (areaAcoPositivoPrincipal.value) {
            ZERO -> ""
            else -> areaAcoPositivoPrincipal.value?.format(2)
        }
    }

    private val areaAcoPositivoSecundario = MutableLiveData(ZERO)
    val areaAcoPositivoSecundarioString = areaAcoPositivoSecundario.map {
        when (areaAcoPositivoSecundario.value) {
            ZERO -> ""
            else -> areaAcoPositivoSecundario.value?.format(2)
        }
    }
    private val areaAcoNegativo = MutableLiveData(ZERO)
    val areaAcoNegativoString = areaAcoNegativo.map {
        when (areaAcoNegativo.value) {
            ZERO -> ""
            else -> areaAcoNegativo.value?.format(2)
        }
    }

    val areaAcoEfetivoPorMetroPositivoPrincipal = MutableLiveData(ZERO)

    val areaAcoEfetivoPorMetroPositivoSecundario = MutableLiveData(ZERO)

    val areaAcoEfetivoPorMetroNegativo = MutableLiveData(ZERO)


    //EVENTS
    val eventAtualizarEspacamentosAcoPositivoPrincipal = ViewModelEvent()
    val eventAtualizarEspacamentosAcoPositivoSecundario = ViewModelEvent()
    val eventAtualizarEspacamentosAcoNegativo = ViewModelEvent()


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
                Log.i("Teste", "Biotla Diferente!")
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

    //ARMADURA POSITIVA SECUNDARIA
    val bitolaArmaduraPositivaSecundarialOnClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val bitolaPrevia = ESCADA.armaduraPositivaSecundaria.bitolaAtual.toString()
            val bitolaSelecionada = parent.getItemAtPosition(position).toString()
            ESCADA.armaduraPositivaSecundaria.selecionarBitolaAtual(bitolaSelecionada)
            if(bitolaSelecionada != bitolaPrevia){
                eventAtualizarEspacamentosAcoPositivoSecundario.trigger()
            }
            atualizarAreaAcoEfetivoPorMetroPositivoSecundario()
        }
    }

    val espacamentoArmaduraPositivaSecundariaOnClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val espacamentoSelecionado = parent.getItemAtPosition(position).toString().toInt()
            ESCADA.apply{
                armaduraPositivaSecundaria.espacamentoAtual = espacamentoSelecionado
                calcularQuantidades()
            }
            atualizarAreaAcoEfetivoPorMetroPositivoSecundario()
        }
    }

    //ARMDARUA NEGATIVA
    val bitolaArmaduraNegativalOnClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val bitolaPrevia = ESCADA.armaduraNegativa.bitolaAtual.toString()
            val bitolaSelecionada = parent.getItemAtPosition(position).toString()
            ESCADA.armaduraNegativa.selecionarBitolaAtual(bitolaSelecionada)
            if(bitolaSelecionada != bitolaPrevia){
                eventAtualizarEspacamentosAcoNegativo.trigger()
            }
            atualizarAreaAcoEfetivoPorMetroNegativo()
        }
    }

    val espacamentoArmaduraNegativaOnClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>) {
        }

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val espacamentoSelecionado = parent.getItemAtPosition(position).toString().toInt()
            ESCADA.apply {
                armaduraNegativa.espacamentoAtual = espacamentoSelecionado
                calcularQuantidades()
            }
                atualizarAreaAcoEfetivoPorMetroNegativo()
        }
    }

    init {
        atualizarValores()
    }

    private fun atualizarTabela() {
        fck.value = ESCADA.fck
        fcd.value = ESCADA.fcd
        fyk.value = ESCADA.fyk
        fyd.value = ESCADA.fyd
        momentoCaracteristico.value = ESCADA.mk
        momentoDeCalculo.value = ESCADA.md
        alturaUtil.value = ESCADA.alturaUtil
        alturaLinhaNeutra.value = ESCADA.alturaLinhaNeutra
        kx.value = ESCADA.kx
        dominio.value = ESCADA.dominio
        areaAcoPositivoPrincipal.value = ESCADA.areaAcoPositivaPrincipalNecessaria
        areaAcoPositivoSecundario.value = ESCADA.areaAcoPoisitivaSecundariaNecessaria
        areaAcoNegativo.value = ESCADA.areaAcoNegativaNecessaria
    }

    private fun atualizarAreasAcoEfetivo() {
        atualizarAreaAcoEfetivoPorMetroPositivoPrincipal()
        atualizarAreaAcoEfetivoPorMetroPositivoSecundario()
        atualizarAreaAcoEfetivoPorMetroNegativo()
    }

    private fun atualizarAreaAcoEfetivoPorMetroPositivoPrincipal() {
        areaAcoEfetivoPorMetroPositivoPrincipal.value = ESCADA.armaduraPositivaPrincipal.areaAcoEfetivaPorMetro
    }

    private fun atualizarAreaAcoEfetivoPorMetroPositivoSecundario() {
        areaAcoEfetivoPorMetroPositivoSecundario.value = ESCADA.armaduraPositivaSecundaria.areaAcoEfetivaPorMetro
    }

    private fun atualizarAreaAcoEfetivoPorMetroNegativo() {
        areaAcoEfetivoPorMetroNegativo.value = ESCADA.armaduraNegativa.areaAcoEfetivaPorMetro
    }


    fun atualizarValores() {
        atualizarTabela()
        atualizarAreasAcoEfetivo()
    }
}
