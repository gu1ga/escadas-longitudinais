package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.flexao

import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Bitola
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentFlexaoBinding

class FlexaoFragment : Fragment() {

    lateinit var binding: FragmentFlexaoBinding
    lateinit var viewModel: FlexaoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flexao, container,false)
        viewModel = ViewModelProvider(this).get(FlexaoViewModel::class.java)
        binding.flexaoViewModel = viewModel
        binding.lifecycleOwner = this
        esconderFlexao()
        configurarOnClicks()
        observarEventos()
        return binding.root
    }


    override fun onResume() {
        viewModel.atualizarValores()
        configurarAdaptersSpinners()
        definirVisibilidadeLayout(true)
        super.onResume()
    }

    override fun onPause() {
        definirVisibilidadeLayout(false)
        super.onPause()
    }

    private fun configurarOnClicks(){
        binding.apply{
            botaoFlexao.setOnClickListener {
                onClickFlexao()
            }
        }
    }

    //TABELA
    private fun onClickFlexao(){
        when(binding.fyk.visibility){
            View.VISIBLE -> {
                configurarTituloBotaoFlexao(View.VISIBLE)
                esconderFlexao()
            }
            View.GONE -> {
                configurarTituloBotaoFlexao(View.GONE)
                mostrarFlexao()
            }
            View.INVISIBLE -> return
        }
    }

    private fun configurarTituloBotaoFlexao(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoFlexao.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoFlexao.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun esconderFlexao(){
        binding.apply{
            fyk.visibility = View.GONE
            fyd.visibility = View.GONE
            divisorClasseResistencia.visibility = View.GONE
            fck.visibility = View.GONE
            fcd.visibility = View.GONE
            divisorMomentos.visibility = View.GONE
            momentoCaracteristico.visibility = View.GONE
            momentoDeCalculo.visibility = View.GONE
            divisorLinhaNeutra.visibility = View.GONE
            alturaUtil.visibility = View.GONE
            alturaLinhaNeutra.visibility = View.GONE
            kx.visibility = View.GONE
            dominio.visibility = View.GONE

        }
    }

    private fun mostrarFlexao(){
        binding.apply {
            fyk.visibility = View.VISIBLE
            fyd.visibility = View.VISIBLE
            divisorClasseResistencia.visibility = View.VISIBLE
            fck.visibility = View.VISIBLE
            fcd.visibility = View.VISIBLE
            divisorMomentos.visibility = View.VISIBLE
            momentoCaracteristico.visibility = View.VISIBLE
            momentoDeCalculo.visibility = View.VISIBLE
            divisorLinhaNeutra.visibility = View.VISIBLE
            alturaUtil.visibility = View.VISIBLE
            alturaLinhaNeutra.visibility = View.VISIBLE
            kx.visibility = View.VISIBLE
            dominio.visibility = View.VISIBLE
        }
    }

    //METODOS GENERICOS
    private fun configurarSpinnerBitola(spinner: Spinner, bitolas: List<Bitola>){
        val arrayAdapter = ArrayAdapter<Bitola>(requireContext(), R.layout.spinner_item)
        arrayAdapter.apply {
            clear()
            addAll(bitolas)
        }
        spinner.adapter = arrayAdapter
    }

    private fun configurarSpinnerEspacamentos(spinner: Spinner, espacamentos: List<Int>){
        val arrayAdapter = ArrayAdapter<Int>(requireContext(), R.layout.spinner_item)
        arrayAdapter.apply{
            clear()
            addAll(espacamentos)
        }
        spinner.adapter = arrayAdapter
    }

    //CONFIGURACAO INICIAL DOS ADAPTERS DOS SPINNERS
    private fun configurarAdaptersSpinners(){
        configurarAdaptersSpinnersPositivoPrincipal()
        configurarAdaptersSpinnersPositivoSecundario()
        configurarAdaptersSpinnersNegativo()
        selecionarBitolasAtuais()
        selecionarEspacamentosAtuais()
    }

    private fun selecionarBitolasAtuais(){
        selecionarBitolaPositivoPrincipalAtual()
        selecionarBiotaPositivoSecundarioAtual()
        selecionarBitolaNegativoAtual()
    }

    private fun selecionarEspacamentosAtuais(){
        selecionarEspacamentoPositivoPrincipalAtual()
        selecionarEspacamentoPositivoSecundarioAtual()
        selecionarEspacamentoNegativoAtual()
    }

    //ACO POSITIVO PRINCIPAL
    private fun configurarAdaptersSpinnersPositivoPrincipal(){
        configurarAdapterSpinnerBitolaPositivoPrincipal()
        configurarAdapterSpinnerEspacamentoPositivoPrincipal()
    }

    private fun configurarAdapterSpinnerBitolaPositivoPrincipal() {
        val bitolas = ESCADA.armaduraPositivaPrincipal.bitolasSelecionaveis
        val spinner = binding.spinnerBarraPositivaPrincipal
        configurarSpinnerBitola(spinner, bitolas)
    }

    private fun configurarAdapterSpinnerEspacamentoPositivoPrincipal(){
        val espacamentos = ESCADA.armaduraPositivaPrincipal.espacamentosPossiveisPorBitola()
        val spinner = binding.spinnerEspacamentoPositivoPrincipal
        configurarSpinnerEspacamentos(spinner, espacamentos)
    }

    private fun selecionarBitolaPositivoPrincipalAtual(){
        val bitola = ESCADA.armaduraPositivaPrincipal.bitolaAtual
        val indice = ESCADA.armaduraPositivaPrincipal.bitolasSelecionaveis.indexOf(bitola)
        binding.spinnerBarraPositivaPrincipal.setSelection(indice)
    }

    private fun selecionarEspacamentoPositivoPrincipalAtual(){
        val espacamento = ESCADA.armaduraPositivaPrincipal.espacamentoAtual
        val indice = ESCADA.armaduraPositivaPrincipal.espacamentosPossiveisPorBitola().indexOf(espacamento)
        binding.spinnerEspacamentoPositivoPrincipal.setSelection(indice)
    }

    //ACO POSITIVO SECUNDARIO
    private fun configurarAdaptersSpinnersPositivoSecundario(){
        configurarAdapterSpinnerBitolaPositivoSecundario()
        configurarAdapterSpinnerEspacamentoPositivoSecundario()
    }

    private fun configurarAdapterSpinnerBitolaPositivoSecundario() {
        val bitolas = ESCADA.armaduraPositivaSecundaria.bitolasSelecionaveis
        val spinner = binding.spinnerBarraPositivaSecundRia
        configurarSpinnerBitola(spinner, bitolas)
    }

    private fun configurarAdapterSpinnerEspacamentoPositivoSecundario(){
        val espacamentos = ESCADA.armaduraPositivaSecundaria.espacamentosPossiveisPorBitola()
        val spinner = binding.spinnerEspacamentoPositivoSecundario
        configurarSpinnerEspacamentos(spinner, espacamentos)
    }

    private fun selecionarBiotaPositivoSecundarioAtual(){
        val bitola = ESCADA.armaduraPositivaSecundaria.bitolaAtual
        val indice = ESCADA.armaduraPositivaSecundaria.bitolasSelecionaveis.indexOf(bitola)
        binding.spinnerBarraPositivaSecundRia.setSelection(indice)
    }

    private fun selecionarEspacamentoPositivoSecundarioAtual(){
        val espacamento = ESCADA.armaduraPositivaSecundaria.espacamentoAtual
        val indice = ESCADA.armaduraPositivaSecundaria.espacamentosPossiveisPorBitola().indexOf(espacamento)
        binding.spinnerEspacamentoPositivoSecundario.setSelection(indice)
    }

    //ACO NEGATIVO
    private fun configurarAdaptersSpinnersNegativo(){
        configurarAdapterSpinnerBitolaNegativo()
        configurarAdapterSpinnerEspacamentoNegativo()
    }

    private fun configurarAdapterSpinnerBitolaNegativo() {
        val bitolas = ESCADA.armaduraNegativa.bitolasSelecionaveis
        val spinner = binding.spinnerBarraNegativa
        configurarSpinnerBitola(spinner, bitolas)
    }

    private fun configurarAdapterSpinnerEspacamentoNegativo(){
        val espacamentos = ESCADA.armaduraNegativa.espacamentosPossiveisPorBitola()
        val spinner = binding.spinnerEspacamentoNegativo
        configurarSpinnerEspacamentos(spinner, espacamentos)
    }

    private fun selecionarBitolaNegativoAtual(){
        val bitola = ESCADA.armaduraNegativa.bitolaAtual
        val indice = ESCADA.armaduraNegativa.bitolasSelecionaveis.indexOf(bitola)
        binding.spinnerBarraNegativa.setSelection(indice)
    }

    private fun selecionarEspacamentoNegativoAtual(){
        val espacamento = ESCADA.armaduraNegativa.espacamentoAtual
        val indice = ESCADA.armaduraNegativa.espacamentosPossiveisPorBitola().indexOf(espacamento)
        binding.spinnerEspacamentoNegativo.setSelection(indice)
    }

    //EVENTOS
    private fun observarEventos(){
        viewModel.apply{
            eventAtualizarEspacamentosAcoPositivoPrincipal.observe(viewLifecycleOwner) {
                if (it) {
                    configurarAdapterSpinnerEspacamentoPositivoPrincipal()
                }
            }

            eventAtualizarEspacamentosAcoPositivoSecundario.observe(viewLifecycleOwner) {
                if (it) {
                    configurarAdapterSpinnerEspacamentoPositivoSecundario()
                }
            }

            eventAtualizarEspacamentosAcoNegativo.observe(viewLifecycleOwner) {
                if (it) {
                    configurarAdapterSpinnerEspacamentoNegativo()
                }
            }
        }
    }

    //METODOS DE LAYOUT
    private fun definirVisibilidadeLayout(visibilidade: Boolean){
        when(visibilidade){
            true ->{
                binding.layoutFlexao.visibility = View.VISIBLE
                binding.layoutProgressBar.visibility = View.INVISIBLE
            }
            false -> {
                binding.layoutFlexao.visibility = View.INVISIBLE
                binding.layoutProgressBar.visibility = View.VISIBLE
            }
        }
    }
}