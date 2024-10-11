package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.cargas.resumo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentResumoCargasBinding


class ResumoCargasFragment : Fragment() {

    lateinit var binding: FragmentResumoCargasBinding
    lateinit var viewModel: ResumoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_resumo_cargas, container, false)
        viewModel = ViewModelProvider(this).get(ResumoViewModel::class.java)
        binding.resumoViewModel = viewModel
        binding.lifecycleOwner = this

        definirVisibilidadeTabelas()
        definirOnClicks()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarCargas()
        definirVisibilidadeLayout(true)
    }

    override fun onPause() {
        super.onPause()
        viewModel.atualizarCargas()
        definirVisibilidadeLayout(false)
    }

    private fun definirVisibilidadeTabelas(){
        binding.apply {
            //Cargas Permanentes
            pesoProprio.visibility = View.GONE
            cargaPermanenteManual.visibility = View.GONE

            //Sobrecargas
            sobrecargaNormativa.visibility = View.GONE
            sobrecargaManaual.visibility = View.GONE
        }
    }

    private fun onClickPermanente(){
        when(binding.pesoProprio.visibility){
            View.VISIBLE -> {
                configurarTituloBotaoPermanentes(View.VISIBLE)
                esconderCargasPermanentes()
            }
            View.GONE -> {
                configurarTituloBotaoPermanentes(View.GONE)
                mostrarCargasPermanentes()
            }
            View.INVISIBLE -> return
        }
    }

    private fun esconderCargasPermanentes(){
        binding.apply{
            pesoProprio.visibility = View.GONE
            cargaPermanenteManual.visibility = View.GONE
        }
    }

    private fun mostrarCargasPermanentes(){
        binding.apply{
            pesoProprio.visibility = View.VISIBLE
            cargaPermanenteManual.visibility = View.VISIBLE
        }
    }

    private fun configurarTituloBotaoPermanentes(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoPermanentes.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoPermanentes.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun onClickSobrecargas(){
        when(binding.sobrecargaNormativa.visibility){
            View.VISIBLE -> {
                configurarTituloBotaoSobrecargas(View.VISIBLE)
                esconderSobrecargas()
            }
            View.GONE -> {
                configurarTituloBotaoSobrecargas(View.GONE)
                mostrarSobrecargas()
            }
            View.INVISIBLE -> return
        }
    }

    private fun esconderSobrecargas(){
        binding.apply{
            sobrecargaNormativa.visibility = View.GONE
            sobrecargaManaual.visibility = View.GONE
        }
    }

    private fun mostrarSobrecargas(){
        binding.apply{
            sobrecargaNormativa.visibility = View.VISIBLE
            sobrecargaManaual.visibility = View.VISIBLE
        }
    }

    private fun configurarTituloBotaoSobrecargas(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoSobrecargas.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoSobrecargas.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun definirOnClicks(){
        binding.apply {
            botaoPermanentes.setOnClickListener {
                onClickPermanente()
            }

            botaoSobrecargas.setOnClickListener {
                onClickSobrecargas()
            }
        }
    }

    private fun definirVisibilidadeLayout(visibilidade: Boolean){

        when(visibilidade){
            true ->{
                binding.layoutResumoCargas.visibility = View.VISIBLE
                binding.imageProgressBar.visibility = View.INVISIBLE
            }
            false -> {
                binding.layoutResumoCargas.visibility = View.INVISIBLE
                binding.imageProgressBar.visibility = View.VISIBLE
            }
        }
    }
}