package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.flecha

import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Bitola
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentFlechaBinding

class FlechaFragment : Fragment() {

    lateinit var binding: FragmentFlechaBinding
    lateinit var viewModel: FlechaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, avedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flecha, container,false)
        viewModel = ViewModelProvider(this).get(FlechaViewModel::class.java)
        binding.flechaViewModel = viewModel
        binding.lifecycleOwner = this
        configurarOnClicks()
        observarEventos()
        configurarAdaptersSpinnersPositivoPrincipal()
        return binding.root

    }

    override fun onResume() {
        viewModel.atualizarValores()
        esconderFlecha()
        configurarAdaptersSpinnersPositivoPrincipal()
        definirVisibilidadeLayout(true)
        super.onResume()
    }

    override fun onPause() {
        definirVisibilidadeLayout(false)
        super.onPause()
    }

    private fun configurarOnClicks(){
        binding.apply{
            botaoFlecha.setOnClickListener {
                onClickFlecha()
            }
        }
    }

    //TABELA
    private fun onClickFlecha(){
        when(binding.momentoAtuante.visibility){
            View.VISIBLE -> {
                configurarBotaoFlecha(View.VISIBLE)
                esconderFlecha()
            }
            View.GONE -> {
                configurarBotaoFlecha(View.GONE)
                mostrarFlecha()
            }
            View.INVISIBLE -> return
        }
    }

    private fun configurarBotaoFlecha(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoFlecha.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoFlecha.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun esconderFlecha(){
        binding.apply{
            momentoAtuante.visibility = View.GONE
            momentoFissuracao.visibility = View.GONE
            pecaFissurada.visibility = View.GONE
            divisorRigidez.visibility = View.GONE
            rigidezTotal.visibility = View.GONE
            alturaLinhaNeutraEstadioII.visibility = View.GONE
            momentoInerciaEstadioII.visibility = View.GONE
            rigidezEquivalente.visibility = View.GONE
            rigidezFinal.visibility = View.GONE
        }
    }

    private fun mostrarFlecha(){
        binding.apply {
            momentoAtuante.visibility = View.VISIBLE
            momentoFissuracao.visibility = View.VISIBLE
            pecaFissurada.visibility = View.VISIBLE
            divisorRigidez.visibility = View.VISIBLE
            rigidezTotal.visibility = View.VISIBLE
            alturaLinhaNeutraEstadioII.visibility = View.VISIBLE
            momentoInerciaEstadioII.visibility = View.VISIBLE
            rigidezEquivalente.visibility = View.VISIBLE
            rigidezFinal.visibility = View.VISIBLE
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

    private fun selecionarBitolasAtuais(){
        selecionarBitolaPositivoPrincipalAtual()
    }

    private fun selecionarEspacamentosAtuais(){
        selecionarEspacamentoPositivoPrincipalAtual()
    }

    //ACO POSITIVO PRINCIPAL
    private fun configurarAdaptersSpinnersPositivoPrincipal(){
        configurarAdapterSpinnerBitolaPositivoPrincipal()
        configurarAdapterSpinnerEspacamentoPositivoPrincipal()
        selecionarBitolaPositivoPrincipalAtual()
        selecionarEspacamentoPositivoPrincipalAtual()
    }

    private fun configurarAdapterSpinnerBitolaPositivoPrincipal() {
        val bitolas = Escada.ESCADA.armaduraPositivaPrincipal.bitolasSelecionaveis
        val spinner = binding.spinnerBarraPositivaPrincipal
        configurarSpinnerBitola(spinner, bitolas)
    }

    private fun configurarAdapterSpinnerEspacamentoPositivoPrincipal(){
        val espacamentos = Escada.ESCADA.armaduraPositivaPrincipal.espacamentosPossiveisPorBitola()
        val spinner = binding.spinnerEspacamentoPositivoPrincipal
        configurarSpinnerEspacamentos(spinner, espacamentos)
    }

    private fun selecionarBitolaPositivoPrincipalAtual(){
        val bitola = Escada.ESCADA.armaduraPositivaPrincipal.bitolaAtual
        val indice = Escada.ESCADA.armaduraPositivaPrincipal.bitolasSelecionaveis.indexOf(bitola)
        binding.spinnerBarraPositivaPrincipal.setSelection(indice)
    }

    private fun selecionarEspacamentoPositivoPrincipalAtual(){
        val espacamento = Escada.ESCADA.armaduraPositivaPrincipal.espacamentoAtual
        val indice = Escada.ESCADA.armaduraPositivaPrincipal.espacamentosPossiveisPorBitola().indexOf(espacamento)
        binding.spinnerEspacamentoPositivoPrincipal.setSelection(indice)
    }


    //EVENTOS
    private fun observarEventos(){
        viewModel.apply {
            eventAtualizarEspacamentosAcoPositivoPrincipal.observe(viewLifecycleOwner, {
                if (it) {
                    configurarAdapterSpinnerEspacamentoPositivoPrincipal()
                }
            })
        }
    }


    //METODOS DE LAYOUT
    private fun definirVisibilidadeLayout(visibilidade: Boolean){
        when(visibilidade){
            true ->{
                binding.layoutFlecha.visibility = View.VISIBLE
                binding.layoutProgressBar.visibility = View.INVISIBLE
            }
            false -> {
                binding.layoutFlecha.visibility = View.INVISIBLE
                binding.layoutProgressBar.visibility = View.VISIBLE
            }
        }
    }

}