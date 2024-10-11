package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentDimensionamentoHostBinding
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.adapter.DimensionamentoFragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DimensionamentoFragment : Fragment() {

    lateinit var binding: FragmentDimensionamentoHostBinding
    lateinit var dimensionamentoFragmentStateAdapter: DimensionamentoFragmentStateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, avedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dimensionamento_host, container,false)
        dimensionamentoFragmentStateAdapter = DimensionamentoFragmentStateAdapter(this)
        activity?.title = "Dimensionamento"
        binding.viewPager2Dimensionamento.adapter = dimensionamentoFragmentStateAdapter
        binding.viewPager2Dimensionamento.offscreenPageLimit = 4

        TabLayoutMediator(binding.tabLayoutDimensionamento, binding.viewPager2Dimensionamento) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "PROCESSAMENTO"
                    tab.setIcon(R.drawable.icon_processamento)
                }
                1 -> {
                    tab.text = "FLEXÃO"
                    tab.setIcon(R.drawable.icon_flexao)
                }
                2 -> {
                    tab.text = "FLECHA"
                    tab.setIcon(R.drawable.icon_flecha)
                }
                3 -> {
                    tab.text = "RELATÓRIO"
                    tab.setIcon(R.drawable.icon_relatorio)
                }
            }
        }.attach()

        binding.tabLayoutDimensionamento.setTabTextColors(resources.getColor(R.color.da_wae_lighter), resources.getColor(R.color.white))

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        childFragmentManager.fragments.forEach {
            it.onResume()
        }
    }
}