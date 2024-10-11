package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.processamento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutErro
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentProcessamentoBinding
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.DimensionamentoFragment

class ProcessamentoFragment : Fragment() {

    lateinit var binding: FragmentProcessamentoBinding
    lateinit var viewModel: ProcessamentoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_processamento, container, false)
        viewModel = ViewModelProvider(this).get(ProcessamentoViewModel::class.java)
        binding.processamentoViewModel = viewModel
        binding.lifecycleOwner = this

        emptyTransformatons()
        observarEventos()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarValores()
    }

    private fun observarEventos(){
        observarErros()
        observarTextos()
        observarCamposOk()
    }

    private fun observarErros(){
        viewModel.apply{
            eventEspessuraForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspessura.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor não deve ser maior do que 50 cm"
                    }
                }
            })
            eventEspessuraForaDeNorma.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspessura.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nNBR 6118 - Espessura mínima de 8 cm"
                    }
                }
            })
        }
    }

    private fun observarTextos(){
        viewModel.apply{
            eventFlexaoOk.observe(viewLifecycleOwner, {
                if (it) {
                    binding.textFlexaoOk.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_outline_done_24, 0, 0, 0)
                }
            })
            eventFlexaoNaoOk.observe(viewLifecycleOwner, {
                if (it) {
                    binding.textFlexaoOk.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_outline_clear_24, 0, 0, 0)
                }
            })
            eventFlechaOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.textFlechaOk.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_outline_done_24, 0 ,0 ,0)
                }
            })
            eventFlechaNaoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.textFlechaOk.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_outline_clear_24, 0 ,0 ,0)
                }
            })
            eventCisalhamentoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.textCisalhamentoOk.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_outline_done_24, 0, 0, 0)
                }
            })
            eventCisalhamentoNaoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.textCisalhamentoOk.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_outline_clear_24, 0, 0, 0)
                }
            })
        }
    }

    private fun observarCamposOk(){
        viewModel.apply{
            eventEspessuraOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspessura.error = null
                }
            })
        }
    }

    private fun emptyTransformatons(){
        viewModel.apply{
            espessuraTransformation.observe(viewLifecycleOwner, {})
            cobrimentoEfetivoTransformation.observe(viewLifecycleOwner, {})
        }
    }
}