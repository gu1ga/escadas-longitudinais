package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.adapter.CargasFragmentStateAdapter
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentCargasHostBinding
import com.google.android.material.tabs.TabLayoutMediator


class CargasFragment : Fragment() {

    lateinit var binding: FragmentCargasHostBinding
    lateinit var cargasPageAdapter: CargasFragmentStateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cargas_host, container, false)
        cargasPageAdapter = CargasFragmentStateAdapter(this)
        activity?.title = "Cargas"
        binding.viewPager2Cargas.adapter = cargasPageAdapter
        binding.viewPager2Cargas.offscreenPageLimit = 3
        TabLayoutMediator(binding.tabLayoutCargas, binding.viewPager2Cargas) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "SOBRECARGAS"
                    tab.setIcon(R.drawable.icon_sobrecargas)
                }
                1 -> {
                    tab.text = "PERMANENTES"
                    tab.setIcon(R.drawable.icon_cargas_permanentes)
                }
                2 -> {
                    tab.text = "RESUMO"
                    tab.setIcon(R.drawable.ic_outline_check_circle_outline_24)
                }
            }

        }.attach()
        binding.tabLayoutCargas.setTabTextColors(resources.getColor(R.color.da_wae_lighter), resources.getColor(R.color.white))

        return binding.root
    }
}