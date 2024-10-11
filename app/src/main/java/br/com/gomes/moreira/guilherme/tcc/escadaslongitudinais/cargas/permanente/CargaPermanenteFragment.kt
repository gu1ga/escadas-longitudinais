package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.permanente

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutErro
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.CargasFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentCargaPermanenteBinding


class CargaPermanenteFragment : Fragment() {

    lateinit var binding: FragmentCargaPermanenteBinding
    lateinit var viewModel: CargaPermanenteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_carga_permanente, container, false)
        viewModel = ViewModelProvider(this).get(CargaPermanenteViewModel::class.java)
        binding.cargaPermanenteViewModel = viewModel
        binding.lifecycleOwner = this

        observarTransformations()
        observarCheckBoxes()
        observarEventos()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarUI()
    }

    private fun observarTransformations() {
        viewModel.apply {
            cargaPermanentePatmaresTransformation.observe(viewLifecycleOwner, {})
            cargaPermanenteLanceTransformation.observe(viewLifecycleOwner, {})
            cargaPermanenteCheckboxTransformation.observe(viewLifecycleOwner, {})
        }
    }

    private fun observarCheckBoxes() {
        viewModel.cargaPermanenteManualCheckBox.observe(viewLifecycleOwner, {
            when (it) {
                true -> binding.layoutCargaPormanenteManual.visibility = View.VISIBLE
                false -> binding.layoutCargaPormanenteManual.visibility = View.GONE
            }
        })
    }

    private fun observarEventos() {
        observarErros()
        observarCamposOk()

    }

    private fun observarErros(){
        viewModel.apply {
            eventCargaPatamaresForaDeIntervalo.observe(viewLifecycleOwner, {
                if (it) {
                    binding.layoutCargaPermanentePatamares.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre 0 e 100000 kgf/m²"
                    }
                }
            })
            eventCargaLanceForaDeIntervalo.observe(viewLifecycleOwner, {
                if (it) {
                    binding.layoutCargaPermanenteLances.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\\nValor deve estar entre 0 e 100000 kgf/m²"
                    }
                }
            })
        }
    }

    private fun observarCamposOk(){
        viewModel.apply {
            eventCargaPatamaresOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutCargaPermanentePatamares.error = null
                }
            })

            eventCargaLnceOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutCargaPermanenteLances.error = null
                }
            })
        }
    }
}