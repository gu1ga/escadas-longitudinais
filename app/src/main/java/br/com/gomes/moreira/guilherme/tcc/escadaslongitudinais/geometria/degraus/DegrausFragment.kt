package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.degraus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutAviso
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.configurarLayoutErro
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentDegrausBinding
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.ESPELHO_MAXIMO
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.ESPELHO_MINIMO
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.PISO_MAXIMO
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.PISO_MINIMO

class DegrausFragment : Fragment() {

    lateinit var viewModel: DegrausViewModel
    lateinit var binding: FragmentDegrausBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, avedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_degraus, container, false)
        viewModel = ViewModelProviders.of(this).get(DegrausViewModel::class.java)
        binding.degrausViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observarEventos()
        emptyTransformations()
    }

    override fun onResume() {
        super.onResume()
        viewModel.atualizarValores()
        definirVisibilidadeLayout(true)
    }

    override fun onPause() {
        super.onPause()
        definirVisibilidadeLayout(false)
    }

    private fun observarEventos(){
        observarErros()
        observarCamposOk()
    }

    private fun observarErros(){
        viewModel.apply {
            eventPisoInvalido.observe(viewLifecycleOwner, {
                if (it) {
                    binding.layoutPiso.apply {
                        configurarLayoutErro(this)
                        error = "Piso inválido!"
                    }
                }
            })

            eventPisoForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPiso.apply{
                        configurarLayoutErro(this)
                        error = "Piso deve estar entre ${PISO_MINIMO.toInt()} e ${PISO_MAXIMO.toInt()} cm"
                    }
                }
            })

            eventImpossivelSugerirPiso.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPiso.apply{
                        configurarLayoutErro(this)
                        error = "Impossível sugerir piso!"
                    }
                }
            })

            eventComprimentoLanceIndefinido.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPiso.apply{
                        configurarLayoutErro(this)
                        error = "Comprimento do lance indefinido!"
                    }
                }
            })

            eventEspelhoInvalido.observe(viewLifecycleOwner, {
                if (it) {
                    binding.layoutEspelho.apply {
                        configurarLayoutErro(this)
                        error = "Espelho inválido!"
                    }
                }
            })

            eventEspelhoForaDeIntervalo.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspelho.apply{
                        configurarLayoutErro(this)
                        error = "Espelho deve estar entre ${ESPELHO_MINIMO.toInt()} e ${ESPELHO_MAXIMO.toInt()}"
                    }
                }
            })

            eventImpossivelSugerirEspelho.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspelho.apply{
                        configurarLayoutErro(this)
                        error = "Impossível sugerir espelho!"
                    }
                }
            })

            eventPeDireitoLanceIndefinido.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspelho.apply {
                        configurarLayoutErro(this)
                        error = "Pé direito indefinido!"
                    }
                }
            })

            eventGeometriaInvalida.observe(viewLifecycleOwner, {
                if(it){
                    binding.apply {
                        layoutEspelho.apply{
                            configurarLayoutErro(this)
                            error = "Geometria inválida!"
                        }
                        layoutPiso.apply {
                            configurarLayoutErro(this)
                            error = "Geometria inválida!"
                        }
                    }
                }
            })

            eventBlondeMaior.observe(viewLifecycleOwner, {
                if(it){
                    binding.apply{
                        layoutEspelho.apply {
                            configurarLayoutAviso(this)
                            error = "Aviso: Geometria Fora de norma:\n" +
                                    "2 x ${ESCADA.espelho} + ${ESCADA.piso} = ${2*ESCADA.espelho + ESCADA.piso} > 64 "
                        }
                        layoutPiso.apply{
                            configurarLayoutAviso(this)
                            error = "Aviso: Geometria Fora de norma:\n" +
                                    "2 x ${ESCADA.espelho} + ${ESCADA.piso} = ${2*ESCADA.espelho + ESCADA.piso} > 64 "
                        }
                    }
                }
            })

            eventBlondelMenor.observe(viewLifecycleOwner, {
                if(it){
                    binding.apply{
                        layoutEspelho.apply {
                            configurarLayoutAviso(this)
                            error = "Aviso: Geometria Fora de norma:\n" +
                                    "2 x ${ESCADA.espelho} + ${ESCADA.piso} = ${2*ESCADA.espelho + ESCADA.piso} < 62 "
                        }
                        layoutPiso.apply{
                            configurarLayoutAviso(this)
                            error = "Aviso: Geometria Fora de norma:\n" +
                                    "2 x ${ESCADA.espelho} + ${ESCADA.piso} = ${2*ESCADA.espelho + ESCADA.piso} < 62 "
                        }
                    }
                }
            })

        }
    }

    private fun observarCamposOk(){
        viewModel.apply {
            eventPisoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPiso.error = null
                }
            })

            eventImpossivelSugerirPisoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutPiso.error = null
                }
            })

            eventEspelhoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspelho.error = null
                }
            })

            eventImpossivelSugerirEspelhoOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.layoutEspelho.error = null
                }
            })

            eventGeometriaInvalidaOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.apply {
                        layoutPiso.error = null
                        layoutEspelho.error = null
                    }
                }
            })

            eventBlondelOk.observe(viewLifecycleOwner, {
                if(it){
                    binding.apply {
                        layoutPiso.error = null
                        layoutEspelho.error = null
                    }
                }
            })
        }
    }

    private fun emptyTransformations(){
        viewModel.apply {
            pisoTransformation.observe(viewLifecycleOwner,{})
            espelhoTransformation.observe(viewLifecycleOwner, {})
        }
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
}