package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.corte.CorteFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.degraus.DegrausFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.lances.LancesFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.patamares.PatamaresFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.planta.PlantaFragment

class GeometriaFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> LancesFragment()
            1 -> PatamaresFragment()
            2 -> PlantaFragment()
            3 -> CorteFragment()
            4 -> DegrausFragment()
            else -> Fragment()
        }
    }
}