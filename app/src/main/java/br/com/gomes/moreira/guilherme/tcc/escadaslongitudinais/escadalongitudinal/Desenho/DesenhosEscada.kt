package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho

import Constantes.DOIS_LANCES
import EscadaLongitudinal
import EscadaLongitudinal.Companion.TIPOLOGIA_A
import EscadaLongitudinal.Companion.TIPOLOGIA_B
import EscadaLongitudinal.Companion.TIPOLOGIA_C
import EscadaLongitudinal.Companion.TIPOLOGIA_D
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.Log
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaA.CorteInferiorTipologiaA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaA.CorteSuperiorTipologiaA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaA.PlantaTipologiaA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaB.CorteInferiorTipologiaB
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaB.CorteSuperiorTipologiaB
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaB.PlantaTipologiaB
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaC.CorteInferiorTipologiaC
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaC.CorteSuperiorTipologiaC
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaC.PlantaTipologiaC
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaD.CorteInferiorTipologiaD
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaD.CorteSuperiorTipologiaD
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaD.PlantaTipologiaD

class DesenhosEscada(escadaLongitudinal: EscadaLongitudinal, context: Context) {

    val escadaLongitudinal: EscadaLongitudinal

    val linhaCorte = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(8f, 8f, 8f, 8f), 0f)
    }
    val hachura = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.LTGRAY
        style = Paint.Style.FILL
    }
    val contornoPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.nord_3)
        style = Paint.Style.STROKE
    }
    val armaduraPositivaPrincipalCortePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.CYAN
        style = Paint.Style.STROKE
    }
    val armaduraPositivaSecundariaPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.MAGENTA
        style = Paint.Style.STROKE
    }
    val linhaChamadaPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.WHITE
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(2f, 2f, 2f, 2f), 0f)
    }
    val armaduraNegativaCortePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = Color.GREEN
        style = Paint.Style.STROKE
    }
    val textoPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.nord_13)
        style = Paint.Style.FILL
        textSize = 10f
    }
    val textoTitulosPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.resources.getColor(R.color.nord_14)
        style = Paint.Style.FILL
        textSize = 20f
    }

    init {
        this.escadaLongitudinal = escadaLongitudinal
    }


    fun desenharDetalhamentoCompleto(xBaseCorteSuperior: Float,
                                     yBaseCorteSuperior: Float,
                                     xBaseCorteInferior: Float,
                                     yBaseCorteInferior: Float,
                                     xBasePlanta: Float,
                                     yBasePlanta: Float,
                                     canvas: Canvas){

        when(escadaLongitudinal.tipologia){
            TIPOLOGIA_A -> desenharDetalhamento(
                xBaseCorteSuperior,
                yBaseCorteSuperior,
                xBaseCorteInferior,
                yBaseCorteInferior,
                xBasePlanta,
                yBasePlanta,
                canvas,
                TIPOLOGIA_A)

            TIPOLOGIA_B -> desenharDetalhamento(
                xBaseCorteSuperior,
                yBaseCorteSuperior,
                xBaseCorteInferior,
                yBaseCorteInferior,
                xBasePlanta,
                yBasePlanta,
                canvas,
                TIPOLOGIA_B)

            TIPOLOGIA_C -> desenharDetalhamento(
                xBaseCorteSuperior,
                yBaseCorteSuperior,
                xBaseCorteInferior,
                yBaseCorteInferior,
                xBasePlanta,
                yBasePlanta,
                canvas,
                TIPOLOGIA_C)
            TIPOLOGIA_D -> desenharDetalhamento(
                xBaseCorteSuperior,
                yBaseCorteSuperior,
                xBaseCorteInferior,
                yBaseCorteInferior,
                xBasePlanta,
                yBasePlanta,
                canvas,
                TIPOLOGIA_D)
        }

    }

    private fun desenharDetalhamento(xBaseCorteSuperior: Float,
                                     yBaseCorteSuperior: Float,
                                     xBaseCorteInferior: Float,
                                     yBaseCorteInferior: Float,
                                     xBasePlanta: Float,
                                     yBasePlanta: Float,
                                     canvas: Canvas,
                                     tipologia: Long){

        val corteInferior = when(tipologia){
            TIPOLOGIA_A -> CorteInferiorTipologiaA()
            TIPOLOGIA_B -> CorteInferiorTipologiaB()
            TIPOLOGIA_C -> CorteInferiorTipologiaC()
            else -> CorteInferiorTipologiaD()
        }
        val corteSuperior = when(tipologia){
            TIPOLOGIA_A -> CorteSuperiorTipologiaA()
            TIPOLOGIA_B -> CorteSuperiorTipologiaB()
            TIPOLOGIA_C -> CorteSuperiorTipologiaC()
            else -> CorteSuperiorTipologiaD()
        }
        val planta = when(tipologia){
            TIPOLOGIA_A -> PlantaTipologiaA()
            TIPOLOGIA_B -> PlantaTipologiaB()
            TIPOLOGIA_C -> PlantaTipologiaC()
            else -> PlantaTipologiaD()
        }

        corteInferior.desenharCorte(
            xBaseCorteInferior,
            yBaseCorteInferior,
            canvas,
            contornoPaint,
            armaduraPositivaPrincipalCortePaint,
            armaduraPositivaSecundariaPaint,
            armaduraNegativaCortePaint,
            textoPaint,
            textoTitulosPaint,
            linhaChamadaPaint)

        if(escadaLongitudinal.numeroLances == DOIS_LANCES){
            corteSuperior.desenharCorte(
                xBaseCorteSuperior,
                yBaseCorteSuperior,
                canvas,
                contornoPaint,
                armaduraPositivaPrincipalCortePaint,
                armaduraPositivaSecundariaPaint,
                armaduraNegativaCortePaint,
                textoPaint,
                textoTitulosPaint,
                linhaChamadaPaint)

        }

        planta.desenharPlanta(
            xBasePlanta,
            yBasePlanta,
            canvas,
            contornoPaint,
            armaduraPositivaPrincipalCortePaint,
            armaduraPositivaSecundariaPaint,
            armaduraNegativaCortePaint,
            textoPaint,
            textoTitulosPaint,
            linhaCorte,
            hachura)
    }
}