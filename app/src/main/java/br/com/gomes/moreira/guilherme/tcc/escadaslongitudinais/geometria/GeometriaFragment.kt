package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.asynclayoutinflater.view.AsyncLayoutInflater.OnInflateFinishedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.MainActivity
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentGeometriaHostBinding
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.adapter.GeometriaFragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator


class GeometriaFragment : Fragment() {

    lateinit var binding: FragmentGeometriaHostBinding
    lateinit var geometriaFragmentStateAdapter: GeometriaFragmentStateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_geometria_host, container,false)
        binding.viewPager2Geometria.offscreenPageLimit = 5
        activity?.title = "Geometria"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        geometriaFragmentStateAdapter = GeometriaFragmentStateAdapter(this)
        binding.viewPager2Geometria.adapter = geometriaFragmentStateAdapter
        //binding.viewPager2Geometria.offscreenPageLimit = 5

        TabLayoutMediator(binding.tabLayoutGeometria, binding.viewPager2Geometria) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "LANCES"
                    tab.setIcon(R.drawable.icon_lances)
                }
                1 -> {
                    tab.text = "PATAMARES"
                    tab.setIcon(R.drawable.icon_patamares)
                }
                2 -> {
                    tab.text = "PLANTA"
                    tab.setIcon(R.drawable.icon_planta)
                }
                3 -> {
                    tab.text = "CORTE"
                    tab.setIcon(R.drawable.icon_corte)
                }
                4 -> {
                    tab.text = "DEGRAUS"
                    tab.setIcon(R.drawable.icon_degraus)
                }
            }

        }.attach()

        binding.tabLayoutGeometria.setTabTextColors(resources.getColor(R.color.da_wae_lighter), resources.getColor(R.color.white))
    }
}

