package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.corte

import Constantes.ALTURA_APOIO_MAXIMA
import Constantes.ALTURA_APOIO_MINIMA
import Constantes.ESPESSURA_MAXIMA
import Constantes.ESPESSURA_MINIMA_NORMATIVA
import Constantes.PE_DIREITO_MAXIMO_DOIS_LANCES
import Constantes.PE_DIREITO_MAXIMO_UM_LANCE
import Constantes.PE_DIREITO_MINIMO_DOIS_LANCES
import Constantes.PE_DIREITO_MINIMO_UM_LANCE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutErro
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentCorteBinding

class CorteFragment : Fragment() {

    lateinit var binding: FragmentCorteBinding
    lateinit var viewModel: CorteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_corte, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CorteViewModel::class.java)
        binding.corteViewModel = viewModel
        binding.lifecycleOwner = this

        observarImagem()
        observarEventos()
        emptyTransformations()
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarUI()
        definirVisibilidadeLayout(true)
    }

    override fun onPause() {
        super.onPause()
        definirVisibilidadeLayout(false)
    }

    private fun emptyTransformations() {
        viewModel.peDireitoTransformation.observe(viewLifecycleOwner, {})
        viewModel.espessuraTransformation.observe(viewLifecycleOwner, {})
        viewModel.apoioEsquerdoTransformation.observe(viewLifecycleOwner, {})
        viewModel.apoioDireitoTransformation.observe(viewLifecycleOwner, {})
    }

    private fun observarEventos(){
        observarErros()
        observarCamposOk()
    }

    private fun observarImagem(){
        viewModel.imagemId.observe(viewLifecycleOwner, {
            mudarImagem(it)
        })
    }

    private fun mudarImagem(id: Int){
        binding.imageDimensoesVerticais.setImageResource(id)
    }

    private fun definirVisibilidadeLayout(visibilidade: Boolean){

        when(visibilidade){
            true ->{
                binding.layoutCorte.visibility = View.VISIBLE
                binding.imageProgressBar.visibility = View.INVISIBLE
            }
            false -> {
                binding.layoutCorte.visibility = View.INVISIBLE
                binding.imageProgressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun observarErros(){
        viewModel.apply{
            eventPeDireitoUmLanceForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPeDireito.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre $PE_DIREITO_MINIMO_UM_LANCE e $PE_DIREITO_MAXIMO_UM_LANCE cm"
                    }
                }
            })

            eventPeDireitoDoisLancesForaDeIntervalo    .observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPeDireito.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre $PE_DIREITO_MINIMO_DOIS_LANCES e $PE_DIREITO_MAXIMO_DOIS_LANCES cm"
                    }
                }
            })

            eventEspessuraForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspessura.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor não deve ser maior do que $ESPESSURA_MAXIMA cm"
                    }
                }
            })
            eventEspessuraForaDeNorma.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspessura.apply {
                        configurarLayoutErro(this)
                        error = "Erro:\nNBR 6118 - Espessura mínima de $ESPESSURA_MINIMA_NORMATIVA cm"
                    }
                }
            })
            eventApoioEsquerdoForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutAlturaApoioEsquerdo.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre max(Espessura, $ALTURA_APOIO_MINIMA) e $ALTURA_APOIO_MAXIMA cm"
                    }
                }
            })
            eventApoioDireitoForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutAlturaApoioDireito.apply{
                        configurarLayoutErro(this)
                        error = "Erro:\nValor deve estar entre max(Espessura, 1$ALTURA_APOIO_MINIMA) e $ALTURA_APOIO_MAXIMA cm"
                    }
                }
            })
        }
    }

    private fun observarCamposOk(){
        viewModel.apply{
            eventPeDireitoOk.observe(viewLifecycleOwner,{
                if(it){
                    binding.layoutPeDireito.error = null
                }
            })

            eventEspessuraOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspessura.error = null
                }
            })

            eventApoioEsquerdoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutAlturaApoioEsquerdo.error = null
                }
            })

            eventApoioDireitoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutAlturaApoioDireito.error = null
                }
            })
        }
    }
}
