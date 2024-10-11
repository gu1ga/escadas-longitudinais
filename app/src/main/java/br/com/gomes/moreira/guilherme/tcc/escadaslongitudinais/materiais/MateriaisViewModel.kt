package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.materiais

import android.app.Application
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.processamento.ProcessamentoViewModel


class MateriaisViewModel(application: Application) : AndroidViewModel(application) {

    companion object{
        const val INDEFINIDO = "Indef."
        const val EMPTY_STRING = ""
        const val ZERO = 0.toDouble()
    }


    private val classeResistencia = MutableLiveData(INDEFINIDO)

    private val moduloClasseResistencia = MutableLiveData(ZERO)
    val moduloClasseResistenciaString: LiveData<String>
        get() = moduloClasseResistencia.map {
            moduloClasseResistencia.value.toString()
        }

    val cobrimento = MutableLiveData(EMPTY_STRING)
    private val valorCobrimento: Double
        get() = cobrimento.value?.toDoubleOrNull() ?: ZERO


    val spinnerClickListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            classeResistencia.value = parent?.getItemAtPosition(position).toString()
            moduloClasseResistencia.value =  application.resources.getStringArray(R.array.classes_resistencia_modulos_kgf_cm2)[position].toDoubleOrNull() ?: ZERO
        }
    }

    fun salvar(){
        ESCADA.cobrimento = valorCobrimento
        ESCADA.fck = moduloClasseResistencia.value ?: ZERO
    }
}