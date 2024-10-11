package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.relatorio.RelatorioFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.flecha.FlechaFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.flexao.FlexaoFragment
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.processamento.ProcessamentoFragment

class DimensionamentoFragmentStateAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ProcessamentoFragment()
            1 -> FlexaoFragment()
            2 -> FlechaFragment()
            3 -> RelatorioFragment()
            else -> Fragment()
        }
    }
}