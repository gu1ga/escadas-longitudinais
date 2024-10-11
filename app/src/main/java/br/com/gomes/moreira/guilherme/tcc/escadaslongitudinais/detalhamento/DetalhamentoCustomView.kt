package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.detalhamento

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.ScaleGestureDetector
import android.view.View
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.DesenhosEscada

class DetalhamentoCustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        const val INVALLID_POINTER_ID = -1
        const val MAX_DURATION = 150

        const val zoomMinimo = 0.5f
        const val zoomMaximo = 20.0f
    }

    var canvasGeral: Canvas = Canvas()

    var scaleGestureDetector: ScaleGestureDetector? = null

    val background: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = context.resources.getColor(R.color.drawing_background)
    }

    var clickCount = 0
    var startTime: Long = 0
    var currentMoveX: Float = 0f
    var currentMoveY: Float = 0f
    var lastTouchPositionX: Float = 0f
    var lastTouchPositionY: Float = 0f
    var dx: Float = 0f
    var dy: Float = 0f
    var fatorEscalaAtual = 1.0f

    val scaleDetector: ScaleGestureDetector
    var scaling: Boolean  = false

    //COORDENADAS
    val xBaseCorteInferior: Float get() = measuredWidth*0.15f
    val yBaseCorteInferior: Float get() = measuredHeight*0.5f

    val xBaseCorteSuperior: Float get() = measuredWidth*0.15f
    val yBaseCorteSuperior: Float get() = -measuredHeight*0.5f

    val xBasePlanta: Float get() = measuredWidth*0.15f
    val yBasePlanta: Float get() = 0f

    //DESENHO DETALHAMENTO
    val desenhoDetalhamento = DesenhosEscada(ESCADA, context)

    init{
        scaleDetector = ScaleGestureDetector(context, ScaleListener())
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvasGeral = canvas
        canvasGeral.save()
        canvasGeral.translate(currentMoveX, currentMoveY)
        canvasGeral.scale(fatorEscalaAtual, fatorEscalaAtual)
        canvasGeral.drawPaint(background)

        if(Escada.geometriaDefinida && Escada.dimensionamentoRealizado){
            desenhoDetalhamento.desenharDetalhamentoCompleto(
                xBaseCorteSuperior,
                yBaseCorteSuperior,
                xBaseCorteInferior,
                yBaseCorteInferior,
                xBasePlanta,
                yBasePlanta,
                canvasGeral
            )
        }
}

    override fun onTouchEvent(event: MotionEvent): Boolean {

        scaleDetector.onTouchEvent(event)
        if(scaleDetector.isInProgress){
            scaling = true
        }

        val x: Float = event.x
        val y: Float = event.y

        when (event.action) {

            ACTION_DOWN -> {
                startTime = System.currentTimeMillis()
                clickCount++

                lastTouchPositionX = event.x
                lastTouchPositionY = event.y
            }

            ACTION_UP -> {
                val time = System.currentTimeMillis() - startTime
                if (clickCount == 2) {
                    if (time <= MAX_DURATION) {
                        currentMoveX = 0f
                        currentMoveY = 0f
                        fatorEscalaAtual = 1f
                        invalidate()
                    }
                    clickCount = 0
                }

                lastTouchPositionX = 0f
                lastTouchPositionY = 0f

                scaling = false
            }

            ACTION_MOVE -> {
                if(!scaling){
                    dx = x - lastTouchPositionX
                    dy = y - lastTouchPositionY

                    currentMoveX += dx
                    currentMoveY += dy
                    invalidate()

                    lastTouchPositionX = x
                    lastTouchPositionY = y
                }
            }
        }

        return true
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            fatorEscalaAtual *= scaleGestureDetector.scaleFactor
            val scale = scaleGestureDetector.scaleFactor

            //NÃO PERMITIR A IMAGEM FICAR MUITO GRANDE OU MUITO PEQUENA
            fatorEscalaAtual = Math.max(zoomMinimo, Math.min(fatorEscalaAtual, zoomMaximo))

            //RECALCULAR A ORIGEM PARA DAR ZOOM NO CENTRO DO PINCH
            if (fatorEscalaAtual < zoomMaximo) {
                val centerX = scaleGestureDetector.focusX
                val centerY = scaleGestureDetector.focusY
                var diffX: Float = centerX - currentMoveX
                var diffY: Float = centerY - currentMoveY
                diffX = diffX * scale - diffX
                diffY = diffY * scale - diffY
                currentMoveX -= diffX
                currentMoveY -= diffY
            }

            invalidate()
            return true
        }
    }
}