package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities

import androidx.core.content.ContextCompat
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import com.google.android.material.textfield.TextInputLayout

fun configurarLayoutAviso(layout: TextInputLayout){
    layout.apply{
        setErrorTextColor(ContextCompat.getColorStateList(context, R.color.aviso_text_input_layout))
        boxStrokeErrorColor = ContextCompat.getColorStateList(context, R.color.aviso_text_input_layout)
        errorIconDrawable = ContextCompat.getDrawable(context, R.drawable.icon_aviso)
        setErrorIconTintList(ContextCompat.getColorStateList(context, R.color.aviso_text_input_layout))
        error = null
    }
}

fun configurarLayoutErro(layout: TextInputLayout){
    layout.apply{
        setErrorTextColor(ContextCompat.getColorStateList(context, R.color.erro_text_input_layout))
        boxStrokeErrorColor = ContextCompat.getColorStateList(context, R.color.erro_text_input_layout)
        errorIconDrawable = ContextCompat.getDrawable(context, R.drawable.icon_erro)
        setErrorIconTintList(ContextCompat.getColorStateList(context, R.color.erro_text_input_layout))
        error = null
    }
}