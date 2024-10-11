package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.detalhamento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentDetalhamentoBinding

class DetalhamentoFragment : Fragment() {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentDetalhamentoBinding>(inflater, R.layout.fragment_detalhamento, container, false)
        activity?.title = "Detalhamento"
        return binding?.root
    }
}