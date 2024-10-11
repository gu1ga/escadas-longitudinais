package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.permanente.CargaPermanenteFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.resumo.ResumoCargasFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.sobrecarga.SobrecargaFragment

class CargasFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SobrecargaFragment()
            1 -> CargaPermanenteFragment()
            2 -> ResumoCargasFragment()
            else -> Fragment()
        }
    }
}