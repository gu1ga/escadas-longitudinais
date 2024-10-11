package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.planta

import Constantes.COMPRIMENTO_MAXIMO_LANCE
import Constantes.COMPRIMENTO_MAXIMO_LARGURA_POR_LANCE
import Constantes.COMPRIMENTO_MAXIMO_LARGURA_TOTAL
import Constantes.COMPRIMENTO_MAXIMO_PATAMAR
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_LANCE
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_POR_LANCE
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_TOTAL
import Constantes.COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR
import Constantes.LARGURA_MAXIMA_APOIO
import Constantes.LARGURA_MINIMA_ABSOLUTA_APOIO
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutAviso
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutErro
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentPlantaBinding




class PlantaFragment : Fragment() {

    lateinit var binding: FragmentPlantaBinding
    lateinit var viewModel: PlantaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planta, container, false)
        viewModel = ViewModelProvider(this).get(PlantaViewModel::class.java)
        binding.plantaViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyTransformations()
        observarImagem()
        observarEventos()
        definirVisibilidadeLayout(true)
    }

    override fun onPause() {
        super.onPause()
        definirVisibilidadeLayout(false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarUI()
        definirVisibilidadeLayout(true)
    }

    private fun observarEventos() {
        observarAvisos()
        observarErros()
        observarCamposOk()
        observarVisibilidadeInputLayouts()
    }

    private fun observarAvisos() {
        viewModel.apply{
            eventPatamarInicialForaDeNorma.observe(viewLifecycleOwner, {
                if (it) {
                    binding.layoutLpini.apply{
                        configurarLayoutAviso(this)
                        error = "Aviso: Acessibilidade!\nNBR 9050  Mínimo de ${Constantes.COMPRIMENTO_MINIMO_NORMATIVO_PATAMAR.toInt()} cm"
                    }
                }
            })


            eventPatamarIntermediarioForaDeNorma.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpint.apply{
                        configurarLayoutAviso(this)
                        error = "Aviso: Acessibilidade!\nNBR 9050  Mínimo de ${Constantes.COMPRIMENTO_MINIMO_NORMATIVO_PATAMAR.toInt()} cm"

                    }
                }
            })
        }
    }

    private fun observarErros(){
        viewModel.apply{
            eventPatamarInicialForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpini.apply {
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR.toInt()} e ${COMPRIMENTO_MAXIMO_PATAMAR.toInt()} cm"
                    }
                }
            })
            eventLanceForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLance.apply{
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${COMPRIMENTO_MINIMO_ABSOLUTO_LANCE.toInt()} e ${COMPRIMENTO_MAXIMO_LANCE.toInt()} cm"
                    }
                }
            })

            eventPatamarIntermediarioForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpint.apply{
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR.toInt()} e ${COMPRIMENTO_MAXIMO_PATAMAR.toInt()} cm"
                    }
                }
            })

            eventLarguraTotalForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLargura.apply{
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_TOTAL.toInt()} e ${COMPRIMENTO_MAXIMO_LARGURA_TOTAL.toInt()} cm"
                    }
                }
            })

            eventLarguraPorLanceForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLargura.apply{
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_POR_LANCE.toInt()} e ${COMPRIMENTO_MAXIMO_LARGURA_POR_LANCE.toInt()} cm"
                    }
                }
            })

            eventBaseApoioEsquerdoForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutApoioEsquerdo.apply{
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${LARGURA_MINIMA_ABSOLUTA_APOIO} e ${LARGURA_MAXIMA_APOIO} cm"
                    }
                }
            })
            eventBaseApoioDireitoForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutApoioDireito.apply{
                        configurarLayoutErro(this)
                        error = "Erro!\nApenas valores entre ${LARGURA_MINIMA_ABSOLUTA_APOIO} e ${LARGURA_MAXIMA_APOIO} cm"
                    }
                }
            })
        }
    }

    private fun observarCamposOk(){
        viewModel.apply {
            eventPatamarInicialOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpini.error = null
                }
            })
            eventLanceOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLance.error = null
                }
            })
            eventPatamarIntermediarioOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpint.error = null
                }
            })
            eventLarguraOk.observe(viewLifecycleOwner, {
                if (it){
                    binding.layoutLargura.error = null
                }
            })
            eventBaseApoioEsquerdoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutApoioEsquerdo.error = null
                }
            })
            eventBaseApoioDireitoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutApoioDireito.error = null
                }
            })
        }
    }

    private fun observarVisibilidadeInputLayouts(){
        viewModel.apply{
            eventEsconderEditTextPatamarInicial.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpini.visibility = View.GONE
                    eventEsconderEditTextPatamarInicial.stop()
                }
            })
            eventMostrarEditTextPatamarInicial.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpini.visibility = View.VISIBLE
                    eventMostrarEditTextPatamarInicial.stop()
                }
            })
            eventEsconderEditTextPamatarIntermediario.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpint.visibility = View.GONE
                    eventEsconderEditTextPamatarIntermediario.stop()
                }
            })
            eventMostrarEditTextPatamarIntermediario.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutLpint.visibility = View.VISIBLE
                    eventMostrarEditTextPatamarIntermediario.stop()
                }
            })
        }
    }

    private fun emptyTransformations() {
        viewModel.patamarInicialTransformation.observe(viewLifecycleOwner, {})
        viewModel.lanceTransformation.observe(viewLifecycleOwner, {})
        viewModel.patamarIntermediarioTransformation.observe(viewLifecycleOwner, {})
        viewModel.larguraTotalTransformation.observe(viewLifecycleOwner, {})
        viewModel.apoioEsquerdoTransformation.observe(viewLifecycleOwner, {})
        viewModel.apoioDireitoTransformation.observe(viewLifecycleOwner, {})
    }

    private fun definirVisibilidadeLayout(visibilidade: Boolean){

        when(visibilidade){
            true ->{
                binding.layoutPlanta.visibility = View.VISIBLE
                binding.imageProgressBar.visibility = View.INVISIBLE
            }
            false -> {
                binding.layoutPlanta.visibility = View.INVISIBLE
                binding.imageProgressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun observarImagem(){
        viewModel.imagemId.observe(viewLifecycleOwner, {
            mudarImagem(it)
        })
    }

    private fun mudarImagem(id: Int){
        binding.imageDimensoesPlanta.setImageResource(id)
    }
}