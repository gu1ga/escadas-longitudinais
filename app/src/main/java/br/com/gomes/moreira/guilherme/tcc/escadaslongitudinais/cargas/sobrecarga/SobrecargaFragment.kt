package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.sobrecarga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutErro
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentSobrecargaBinding


class SobrecargaFragment : Fragment() {

    lateinit var binding: FragmentSobrecargaBinding
    lateinit var viewModel: SobrecargaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sobrecarga, container, false)
        viewModel = ViewModelProvider(this).get(SobrecargaViewModel::class.java)
        binding.sobrecargaViewModel = viewModel
        binding.lifecycleOwner = this


        configurarSpinner()
        observarTransformations()
        observarCheckBoxes()
        observarEventos()

        return binding.root
    }

    private fun observarTransformations() {
        viewModel.sobrecargaPatamresTransformation.observe(viewLifecycleOwner ,{})
        viewModel.sobrecargaLancesTransformation.observe(viewLifecycleOwner, {})
        viewModel.sobrecargaManualCheckBoxTransformation.observe(viewLifecycleOwner, {})
    }

    private fun observarEventos() {
        observarErros()
        observarCamposOk()
    }

    private fun observarCheckBoxes(){
        viewModel.sobrecargaNormativaCheckBox.observe(viewLifecycleOwner, {
            when(it){
                true -> binding.layoutSobrecargaNbr6120.visibility = View.VISIBLE
                false -> binding.layoutSobrecargaNbr6120.visibility = View.GONE
            }
        })
        viewModel.sobrecargaManualCheckBox.observe(viewLifecycleOwner, {
            when(it){
                true -> binding.apply {
                    imageCargasSobrecargas.visibility = View.VISIBLE
                    scrollViewSobrecarga.visibility = View.VISIBLE
                }
                    false -> binding.apply{
                        imageCargasSobrecargas.visibility = View.GONE
                        scrollViewSobrecarga.visibility = View.GONE
                    }
            }
        })
    }

    private fun configurarSpinner(){
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(this.requireContext(), R.array.sobrecargas_nbr_6120_descricoes, R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_item)
            // Apply the adapter to the spinner
            binding.spinnerCargasNbr6120.adapter = adapter
        }
    }

    private fun observarErros(){
        viewModel.apply{
            eventSobrecargaPatamaresForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutSobrecargaPatamares.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre 0 e 100000 kgf/m²"
                    }
                }
            })
            eventSobrecargaLancesForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutSobrecargaLances.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre 0 e 100000 kgf/m²"
                    }
                }
            })
        }
    }

    private fun observarCamposOk(){
        viewModel.apply{
            eventSobrecargaPatamaresOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutSobrecargaPatamares.error = null
                }
            })

            eventSobrecargaLancesOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutSobrecargaLances.error = null
                }
            })
        }
    }
}