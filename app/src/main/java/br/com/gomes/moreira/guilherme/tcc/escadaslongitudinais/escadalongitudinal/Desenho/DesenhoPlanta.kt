package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho

import EscadaLongitudinal
import android.graphics.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraNegativa
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraPositivaPrincipal
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraPositivaSecundaria
import kotlin.math.tan

open class DesenhoPlanta(escada: EscadaLongitudinal) {

    val escada: EscadaLongitudinal
    val armaduraPositivaPrincipal: ArmaduraPositivaPrincipal get()  = escada.armaduraPositivaPrincipal
    val armaduraPositivaSecundaria: ArmaduraPositivaSecundaria get() = escada.armaduraPositivaSecundaria
    val armaduraNegativa: ArmaduraNegativa get() = escada.armaduraNegativa

    //Variaveis Globais
    val raio: Float get() = 1f
    val deltaBarra: Float get() = ((escada.espessura - escada.cobrimento - raio) * tan(escada.anguloLance * 0.5)).toFloat()
    val cobrimento: Float get() = escada.cobrimento.toFloat()

    val baseApoioEsquerdo: Float get() = escada.baseApoioEsquerdo.toFloat()
    val alturaApoioEsquerdo: Float get() = escada.alturaApoioDireito.toFloat()
    val baseApoioDireito: Float get() = escada.baseApoioDireito.toFloat()
    val alturaApoioDireito: Float get() = escada.alturaApoioDireito.toFloat()

    val espessura: Float get() = escada.espessura.toFloat()
    val comprimentoPatamarInicial: Float get() = escada.comprimentoPatamarInicial.toFloat()
    val comprimentoLance: Float get() = escada.comprimentoLance.toFloat()
    val comprimentoPatamarIntermediario: Float get() = escada.comprimentoPatamarIntermediario.toFloat()
    val comprimentoTotal: Float get() = escada.comprimentoTotal.toFloat()
    val peDireitoLance: Float get() = escada.peDireitoLance.toFloat()
    val angulo: Float get() = -escada.anguloLance.toFloat()
    val larguraTotal: Float get() = escada.larguraTotal.toFloat()
    val larguraPorLance: Float get() = escada.larguraPorLance.toFloat()

    val piso: Float get() = escada.piso.toFloat()
    val espelho: Float get() = escada.espelho.toFloat()

    val baseTriangulo = 20f
    val alturaTriangulo = 20f

    init {
        this.escada = escada
    }

    //Funções de Desenho
    //Desenho Geral
    open fun desenharPlanta(xBase: Float,
                        yBase: Float,
                        canvas: Canvas,
                        contornoPaint: Paint,
                        armaduraPositivaPrincipalPaint: Paint,
                        armaduraPositivaSecundariaPaint: Paint,
                        armaduraNegativaCortePaint: Paint,
                        textoPaint: Paint,
                        textoTitulosPaint: Paint,
                        linhaCortePaint: Paint,
                        hachuraPaint: Paint ){}

    //Contorno
    open fun desenharContorno(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Apoios
    open fun desenharApoioEsquerdo(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = apoioEsquerdoPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    open fun desenharApoioDireito(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = apoioDireitoPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    //Indicacoes Corte
    open fun desenharLinhaCorte(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = linhaCortePath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    open fun desenharTrianguloCorte(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){
        val path = trianguloCortePath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    //Armadura Positiva Principal Superior
    //Inicial
    open fun desenharArmaduraPositivaPrincipalInicialSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraPositivaPrincipalInicialSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Final
    open fun desenharArmaduraPositivaPrincipalFinalSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraPositivaPrincipalFinalSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Armdaura Positiva Principal Inferior
    //Inicial
    open fun desenharArmaduraPositivaPrincipalInicialInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraPositivaPrincipalInicialInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Final
    open fun desenharArmaduraPositivaPrincipalFinalInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraPositivaPrincipalFinalInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}


    //Armadura Positiva Secundaria Patamar Inicial
    open fun desenharArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    //Armadura Positiva Secundaria Patamar Intermediario
    open fun desenharArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    //Armadura Positiva Secundaria Lance Superior
    open fun desenharArmaduraPositivaSecundariaLanceSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaLanceSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    //Armadura Positiva Secundaria Lance Inferior
    open fun desenharArmaduraPositivaSecundariaLanceInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaLanceInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    //Armadura Negativa Inicial
    open fun desenharArmaduraNegativaInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraNegativaInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Armadura Negtaiva Final
    open fun desenharArmaduraNegativaFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraNegativaFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}


    //AUXILIARES
    //Contorno
    open fun contornoPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    //Apoios
    //Apoio
    open fun apoioEsquerdoPath(xBase: Float, yBase: Float): Path {
        val path = Path()

        val alturaSobra = 50f
        val dyApoio = 2f*alturaSobra + larguraTotal

        val xInicial = xBase
        val yInicial = yBase - alturaSobra

        val dxExternoSimbolo = 15f
        val dxInternoSimbolo = baseApoioEsquerdo*0.2f
        val dxSimbolo = dxExternoSimbolo + dxInternoSimbolo
        val dySimboloTotal = 15f
        val dySimboloParcial = 0.5f*dySimboloTotal

        path.apply {
            //Apoio
            moveTo(xInicial, yInicial)
            rLineTo(0f, dyApoio)
            rMoveTo(baseApoioEsquerdo, 0f)
            rLineTo(0f, -dyApoio)

            //Simbolo Superior
            rMoveTo(dxExternoSimbolo, 0f)
            rLineTo(-dxSimbolo, 0f)
            rLineTo(-dxInternoSimbolo, -dySimboloParcial)
            rLineTo(-dxInternoSimbolo, dySimboloTotal)
            rLineTo(-dxInternoSimbolo, -dySimboloParcial)
            rLineTo(-dxSimbolo, 0f)

            //Simbolo Inferior
            rMoveTo(0f, dyApoio)
            rLineTo(dxSimbolo, 0f)
            rLineTo(dxInternoSimbolo, dySimboloParcial)
            rLineTo(dxInternoSimbolo, -dySimboloTotal)
            rLineTo(dxInternoSimbolo, dySimboloParcial)
            rLineTo(dxSimbolo, 0f)
        }

        return path
    }

    open fun apoioDireitoPath(xBase: Float, yBase: Float): Path {
        val path = Path()

        val alturaSobra = 50f
        val dyApoio = 2f*alturaSobra + larguraTotal

        val xInicial = xBase
        val yInicial = yBase - alturaSobra

        val dxExternoSimbolo = 15f
        val dxInternoSimbolo = baseApoioDireito*0.2f
        val dxSimbolo = dxExternoSimbolo + dxInternoSimbolo
        val dySimboloTotal = 15f
        val dySimboloParcial = 0.5f*dySimboloTotal

        path.apply {
            //Apoio
            moveTo(xInicial, yInicial)
            rLineTo(0f, dyApoio)
            rMoveTo(-baseApoioDireito, 0f)
            rLineTo(0f, -dyApoio)

            //Simbolo Superior
            rMoveTo(-dxExternoSimbolo, 0f)
            rLineTo(dxSimbolo, 0f)
            rLineTo(dxInternoSimbolo, dySimboloParcial)
            rLineTo(dxInternoSimbolo, -dySimboloTotal)
            rLineTo(dxInternoSimbolo, dySimboloParcial)
            rLineTo(dxSimbolo, 0f)

            //Simbolo Inferior
            rMoveTo(0f, dyApoio)
            rLineTo(-dxSimbolo, 0f)
            rLineTo(-dxInternoSimbolo, -dySimboloParcial)
            rLineTo(-dxInternoSimbolo, dySimboloTotal)
            rLineTo(-dxInternoSimbolo, -dySimboloParcial)
            rLineTo(-dxSimbolo, 0f)
        }

        return path
    }

    //Armadura Positiva Principal  Superior
    //Inicial
    open fun armaduraPositivaPrincipalInicialSuperiorPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaPrincipalInicialSuperior(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaPrincipalInicialSuperior(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Final
    open fun armaduraPositivaPrincipalFinalSuperiorPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaPrincipalFinalSuperior(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaPrincipalFinalSuperior(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Armadura Positiva Principal Inferior
    //Inicial
    open fun armaduraPositivaPrincipalInicialInferiorPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaPrincipalInicialInferior(): String {
        return  ""
    }

    open fun posicaoTextoArmaduraPositivaPrincipalInicialInferior(xBase: Float, yBase: Float): PointF {
        return PointF()
    }

    //Final
    open fun armaduraPositivaPrincipalFinalInferiorPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaPrincipalFinalInferior(): String {
        return  ""
    }

    open fun posicaoTextoArmaduraPositivaPrincipalFinalInferior(xBase: Float, yBase: Float): PointF {
        return PointF()
    }


    //Armadura Positiva Secundaria Patamar Inicial
    open fun armaduraPositivaSecundariaPatamarInicialPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaPatamarInicial(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float): PointF{
        return PointF()
    }


    //ArmaduraPositiva Secundaria Patmar Intermediario
    open fun armaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaPatamarIntermediario(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Armadura Positiva Secundaria Lance Superior
    open fun armaduraPositivaSecundariaLanceSuperiorPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaLanceSuperior(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaLanceSuperior(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Armadura Positiva Secundaria Lance Inferior
    open fun armaduraPositivaSecundariaLanceInferiorPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaLanceInferior(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaLanceInferior(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Armadura Negativa Inicial
    open fun armaduraNegativaInicialPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraNegativaInicial(): String{
        return ""
    }

    open fun posicaoTextoArmaduraInicial(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Armadura Negativa Final
    open fun armaduraNegativaFinalPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraNegativaFinal(): String{
        return ""
    }

    open fun posicaoTextoArmaduraNegativaFinal(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    //Metodos genericos
    //Linhas de Corte
    open fun linhaCortePath(xBase: Float, yBase: Float) : Path{
        val linhaCortePath = Path()

        val comprimentoTotal = comprimentoTotal

        val xInicialPrimeiraLinha = xBase - baseApoioEsquerdo - 3f*baseTriangulo
        val yInicialPrimeiraLinha = yBase

        val dxLinha = 2.5f*baseTriangulo

        val xInicialSegundaLinha = xBase + comprimentoTotal + 3f*baseTriangulo
        val yInicialSegundaLinha = yBase

        linhaCortePath.apply {
            moveTo(xInicialPrimeiraLinha, yInicialPrimeiraLinha)
            rLineTo(dxLinha, 0f)
            moveTo(xInicialSegundaLinha, yInicialSegundaLinha)
            rLineTo(-dxLinha, 0f)
        }

        return linhaCortePath
    }

    //Triangulo Corte
    open fun trianguloCortePath(xBase: Float, yBase: Float): Path{
        val trianguloPath = Path()

        val xInicialPrimeiraLinha = xBase - baseApoioEsquerdo - 3f*baseTriangulo
        val yInicialPrimeiraLinha = yBase

        val xInicialSegundaLinha = xBase + comprimentoTotal + 3f*baseTriangulo
        val yInicialSegundaLinha = yBase

        trianguloPath.apply {
            moveTo(xInicialPrimeiraLinha, yInicialPrimeiraLinha)
            rLineTo(baseTriangulo*0.5f, -alturaTriangulo)
            rLineTo(baseTriangulo*0.5f, alturaTriangulo)
            moveTo(xInicialSegundaLinha, yInicialSegundaLinha)
            rLineTo(-baseTriangulo*0.5f, -alturaTriangulo)
            rLineTo(-baseTriangulo*0.5f, alturaTriangulo)
        }

        return trianguloPath
    }

    //Texto Corte
    open fun desenharTextoCorte(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint, texto: String){
        val caixaTexto = Rect()
        paint.getTextBounds(texto,0, texto.length, caixaTexto)
        val xTextoEsquerda = xBase - baseApoioEsquerdo - 1.5f*baseTriangulo + caixaTexto.width()*0.5f
        val xTextoDireita = xBase + comprimentoTotal + 1.5f*baseTriangulo - caixaTexto.width()*0.5f
        val y = yBase - 4f

        canvas.drawText(texto, xTextoEsquerda, y, paint)
        canvas.drawText(texto, xTextoDireita, y , paint)
    }

    //Texto barras
    open fun desenharTextoBarra(texto: String, posicao: PointF, canvas: Canvas, paint: Paint, angulo: Float = 0f){
        val caixaTexto = Rect()
        paint.getTextBounds(texto,0, texto.length, caixaTexto)
        val xTexto = -0.5f*caixaTexto.width()
        val yTexto = 0f

        canvas.save()
        canvas.translate(posicao.x, posicao.y)
        canvas.rotate(angulo)
        canvas.drawText(texto, xTexto, yTexto, paint)
        canvas.restore()
    }
}
