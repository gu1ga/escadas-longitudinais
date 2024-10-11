package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.materiais

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.MainActivity
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.sobrecarga.SobrecargaViewModel
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentMateriaisBinding


class MateriaisFragment : Fragment() {

    lateinit var binding: FragmentMateriaisBinding
    lateinit var viewModel: MateriaisViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_materiais, container, false)
        viewModel = ViewModelProvider(this).get(MateriaisViewModel::class.java)
        binding.materiaisViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}