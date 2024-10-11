package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.geometria.patamares

import Constantes.DOIS_LANCES
import Constantes.UM_LANCE
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.databinding.ObservableInt
import androidx.lifecycle.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.ViewModelEvent
import kotlinx.coroutines.*

class PatamaresViewModel : ViewModel() {

    val existePatamarInicial = MutableLiveData(false)
    val existePatamarIntermediario = MutableLiveData(false)
    val imagemId: MutableLiveData<Int> = MutableLiveData(R.drawable.geometria_planta_escada_1_lance_2_patamares)

    init{
        atualizarUI()
    }


    val existePatamarInicialTransformation = existePatamarInicial.map {
        existePatamarInicial(it)
        definirImagem()
    }

    val existePatamarIntermediarioTransformation = existePatamarIntermediario.map {
        existePatamarIntermediario(it)
        definirImagem()
    }


    fun atualizarUI(){
            definirImagem()
            atualizarValores()
    }

    private fun existePatamarInicial(existePatamar: Boolean){
        ESCADA.existePatamarInicial = existePatamar
        if(!existePatamar){
            ESCADA.comprimentoPatamarInicial = 0.toDouble()
        }
    }

    private fun existePatamarIntermediario(existePatmar: Boolean){
        ESCADA.existePatamarIntermediario = existePatmar
        if(!existePatmar){
            ESCADA.comprimentoPatamarIntermediario = 0.toDouble()
        }
    }

    private  fun  definirImagem(){

        if(ESCADA.numeroLances == UM_LANCE){
            imagemId.value = when(ESCADA.existePatamarInicial && ESCADA.existePatamarIntermediario){
                true  -> R.drawable.geometria_planta_escada_1_lance_2_patamares
                false -> when(!ESCADA.existePatamarInicial && !ESCADA.existePatamarIntermediario){
                    true  -> R.drawable.geometria_planta_escada_1_lance_sem_patamar
                    false -> when(ESCADA.existePatamarInicial){
                        true  -> R.drawable.geometria_planta_escada_1_lance_patamar_ini
                        false -> R.drawable.geometria_planta_escada_1_lance_patamar_int
                    }
                }
            }
        }
        else if(ESCADA.numeroLances == DOIS_LANCES){
            imagemId.value = when(ESCADA.existePatamarInicial && ESCADA.existePatamarIntermediario){
                true  -> R.drawable.geometria_planta_escada_2_lances_2_patamares
                false -> when(!ESCADA.existePatamarInicial && !ESCADA.existePatamarIntermediario){
                    true  -> R.drawable.geometria_planta_escada_2_lances_sem_patamar
                    false -> when(ESCADA.existePatamarInicial){
                        true  -> R.drawable.geometria_planta_escada_2_lances_1_patamar_ini
                        false -> R.drawable.geometria_planta_escada_2_lances_1_patamar_int
                    }
                }
            }
        }
    }

    private fun atualizarValores() {
        existePatamarInicial.value = ESCADA.existePatamarInicial
        existePatamarIntermediario.value = ESCADA.existePatamarIntermediario
    }
    }