package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.patamares

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentPatamaresBinding
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.GeometriaFragment


class PatamaresFragment : Fragment() {

    lateinit var binding: FragmentPatamaresBinding

    lateinit var viewModel: PatamaresViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_patamares, container, false)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(PatamaresViewModel::class.java)
        binding.patamaresViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyTransformations()
        observarImagem()
        observarChips()
        observarEventos()
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarUI()
        definirVisibilidadeImagem(true)
    }

    override fun onPause() {
        super.onPause()
        definirVisibilidadeImagem(false)
    }

    private fun observarEventos() {}

    private fun emptyTransformations(){
        viewModel.existePatamarInicialTransformation.observe(viewLifecycleOwner, {})
        viewModel.existePatamarIntermediarioTransformation.observe(viewLifecycleOwner, {})
    }

    private fun observarChips() {

        viewModel.existePatamarInicial.observe(viewLifecycleOwner, {
            if(it){

            }
        })
    }

    private fun observarImagem(){
        viewModel.imagemId.observe(viewLifecycleOwner, {
            binding.imagePatamares.setImageResource(it)
        })
    }

    private fun definirVisibilidadeImagem(visibilidade: Boolean){

        when(visibilidade){
            true ->{
                binding.imagePatamares.visibility = View.VISIBLE
                binding.imageProgressBar.visibility = View.INVISIBLE
            }
            false -> {
                binding.imagePatamares.visibility = View.INVISIBLE
                binding.imageProgressBar.visibility = View.VISIBLE
            }
        }
    }
}