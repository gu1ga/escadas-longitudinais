package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.lances

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentLancesBinding
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.GeometriaFragment


class LancesFragment : Fragment() {

    lateinit var binding: FragmentLancesBinding
    lateinit var viewModel: LancesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lances, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(LancesViewModel::class.java)
        binding.lancesViewModel = viewModel
        binding.lifecycleOwner = this

        observarChips()
        emptyTransformations()
        obsrevarEventos()
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarValores()
    }

    private fun emptyTransformations() {
        viewModel.umLanceTransformation.observe(viewLifecycleOwner, {})
        viewModel.doisLancesTransformation.observe(viewLifecycleOwner, {})
    }

    private fun observarChips(){
        viewModel.umLanceChecked.observe(viewLifecycleOwner, {
            if(it){
                binding.imageLances.setImageResource(R.drawable.geometria_planta_escada_1_lance_2_patamares_geom)
            }
        })
        viewModel.doisLancesChecked.observe(viewLifecycleOwner, {
            if(it){
                binding.imageLances.setImageResource(R.drawable.geometria_planta_escada_2_lances_2_patamares_geom)
            }
        })
    }

    private fun obsrevarEventos() {}
}