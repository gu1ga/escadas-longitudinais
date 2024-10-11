package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaC

import Constantes.DOIS_LANCES
import Constantes.UM_LANCE
import android.graphics.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.DesenhoPlanta

class PlantaTipologiaC: DesenhoPlanta(ESCADA) {
    override fun desenharPlanta(xBase: Float,
                                yBase: Float,
                                canvas: Canvas,
                                contornoPaint: Paint,
                                armaduraPositivaPrincipalPaint: Paint,
                                armaduraPositivaSecundariaPaint: Paint,
                                armaduraNegativaCortePaint: Paint,
                                textoPaint: Paint,
                                textoTitulosPaint: Paint,
                                linhaCortePaint: Paint,
                                hachuraPaint: Paint
    ) {
        //Coordenadas Contorno
        val xContorno = xBase
        val yContorno = yBase - larguraTotal*0.5f

        //Apoio Esquerdo
        val xApoioEsquerdo = xBase
        val yApoioEsquerdo = yContorno

        //Apoio Direito
        val xApoioDireito = xBase + comprimentoTotal
        val yApoioDireito = yContorno

        //Linha de Corte Inferior
        val xLinhaCorteInferior = xBase
        val yLinhaCorteInferior = yContorno + larguraTotal*0.8f
        val xTextoCorteInferior = xLinhaCorteInferior
        val yTextoCorteInferior = yLinhaCorteInferior
        val tituloCorteInferior = "B"

        //Linha de Corte Superior
        val xLinhaCorteSuperior = xBase
        val yLinhaCorteSuperior = yContorno + larguraTotal*0.2f
        val xTextoCorteSuperior = xLinhaCorteSuperior
        val yTextoCorteSuperior = yLinhaCorteSuperior
        val tituloCorteSuperior = "A"

        //Linha de Corte Central
        val xLinhaCorteCentral = xBase
        val yLinhaCorteCentral = yContorno + larguraTotal*0.5f
        val xTextoCorteCentral = xLinhaCorteCentral
        val yTextoCorteCentral = yLinhaCorteCentral
        val tituloCorteCentral = "A"

        //Armadura Positiva Principal Superior
        //Inicial
        val xArmaduraPositivaPrincipalInicialSuperior = xBase + comprimentoTotal - cobrimento
        val yArmaduraPositivaPrincipalInicialSuperior = yContorno + larguraTotal*0.15f

        //Armadura Positiva Principal Inferior
        //Inicial
        val xArmaduraPositivaPrincipalInicialInferior = xBase + cobrimento
        val yArmaduraPositivaPrincipalInicialInferior = yContorno + larguraTotal - larguraTotal*0.15f

        //Final
        val xArmaduraPositivaPrincipalFinalInferior = xBase + comprimentoTotal - cobrimento
        val yArmaduraPositivaPrincipalFinalInferior = yContorno + larguraTotal - larguraTotal*0.30f


        //Armadura Positiva Secundaria Patamar Intermediario
        val xArmaduraPositivaSecundariaPatamarIntermediario = xBase + comprimentoTotal - baseApoioDireito - comprimentoPatamarIntermediario*0.35f
        val yArmaduraPositivaSecundariaPatamarIntermediario = yContorno + cobrimento

        //Armadura Positiva Secundaria Lance Superior
        val xArmaduraPositivaSecundariaLanceSuperior = xBase + baseApoioEsquerdo + comprimentoPatamarInicial + 1.8f*piso
        val yArmaduraPositivaSecundariaLanceSuperior = yContorno + cobrimento

        //Armadura Positiva Secundaria Lance Inferior
        val xArmaduraPositivaSecundariaLanceInferior = xBase + baseApoioEsquerdo + comprimentoPatamarInicial + comprimentoLance - 1.2f*piso
        val yArmaduraPositivaSecundariaLanceInferior = if(escada.numeroLances == DOIS_LANCES) yContorno + larguraPorLance + cobrimento else yContorno + cobrimento

        //Armadura Negativa Patamar Inicial
        val xArmaduraNegativaPatamarInicial = xBase + cobrimento
        val yArmaduraNegativaPatmarInicial = yContorno + larguraTotal - larguraTotal*0.30f

        //Armdura Negativa Patamar Intermediario
        val xArmaduraNegativaPatamarIntermediario = xBase + comprimentoTotal - cobrimento
        val yArmaduraNegativaPatamarIntermediario = yContorno + larguraTotal*0.30f


        ////////////
        //Contorno//
        ////////////
        desenharContorno(xContorno, yContorno, canvas, contornoPaint)

        //////////
        //Apoios//
        /////////
        desenharApoioEsquerdo(xApoioEsquerdo, yApoioEsquerdo, canvas, contornoPaint)
        desenharApoioDireito(xApoioDireito, yApoioDireito, canvas, contornoPaint)

        /////////////////////
        //Indicacoes corte//
        ////////////////////
        when(escada.numeroLances){
            UM_LANCE ->{
                //LinhaCentral
                desenharLinhaCorte(xLinhaCorteCentral, yLinhaCorteCentral, canvas, linhaCortePaint)
                desenharTrianguloCorte(xLinhaCorteCentral, yLinhaCorteCentral, canvas, hachuraPaint)
                desenharTextoCorte(xLinhaCorteCentral, yLinhaCorteCentral, canvas, textoTitulosPaint, tituloCorteCentral)
            }
            DOIS_LANCES ->{
                //Linha Inferior
                desenharLinhaCorte(xLinhaCorteInferior, yLinhaCorteInferior, canvas, linhaCortePaint)
                desenharTrianguloCorte(xLinhaCorteInferior, yLinhaCorteInferior, canvas, hachuraPaint)
                desenharTextoCorte(xLinhaCorteInferior, yLinhaCorteInferior, canvas, textoTitulosPaint, tituloCorteInferior)

                //Linha Superior
                desenharLinhaCorte(xLinhaCorteSuperior, yLinhaCorteSuperior, canvas, linhaCortePaint)
                desenharTrianguloCorte(xLinhaCorteSuperior, yLinhaCorteSuperior, canvas, hachuraPaint)
                desenharTextoCorte(xTextoCorteSuperior, yTextoCorteSuperior, canvas, textoTitulosPaint, tituloCorteSuperior)
            }
        }

        ///////////////////////////////
        //Armadura Positiva Principal//
        ///////////////////////////////

        /////////////////////////////////////////
        //Armadura Positiva Principal Inferior//
        /////////////////////////////////////////
        //Inicial
        desenharArmaduraPositivaPrincipalInicialInferior(xArmaduraPositivaPrincipalInicialInferior, yArmaduraPositivaPrincipalInicialInferior, canvas, armaduraPositivaPrincipalPaint)
        desenharTextoArmaduraPositivaPrincipalInicialInferior(xArmaduraPositivaPrincipalInicialInferior, yArmaduraPositivaPrincipalInicialInferior, canvas, textoPaint)

        //Final
        desenharArmaduraPositivaPrincipalFinalInferior(xArmaduraPositivaPrincipalFinalInferior, yArmaduraPositivaPrincipalFinalInferior, canvas, armaduraPositivaPrincipalPaint)
        desenharTextoArmaduraPositivaPrincipalFinalInferior(xArmaduraPositivaPrincipalFinalInferior, yArmaduraPositivaPrincipalFinalInferior, canvas, textoPaint)

        if(escada.numeroLances == DOIS_LANCES){
            /////////////////////////////////////////
            //Armadura Positiva Principal Superior//
            /////////////////////////////////////////
            //Inicial
            desenharArmaduraPositivaPrincipalInicialSuperior(xArmaduraPositivaPrincipalInicialSuperior, yArmaduraPositivaPrincipalInicialSuperior, canvas, armaduraPositivaPrincipalPaint)
            desenharTextoArmaduraPositivaPrincipalInicialSuperior(xArmaduraPositivaPrincipalInicialSuperior, yArmaduraPositivaPrincipalInicialSuperior, canvas, textoPaint)
        }


        ////////////////////////////////
        //Armadura Positiva Secundaria//
        ////////////////////////////////

        //////////////////////////////////////////////////////
        //Armadura Positiva Secundaria Patamar Intermediario//
        //////////////////////////////////////////////////////
        desenharArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, armaduraPositivaSecundariaPaint)
        desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, textoPaint)

        ///////////////////////////////////////////////
        //Armadura Positiva Secundaria Lance Inferior//
        ///////////////////////////////////////////////
        desenharArmaduraPositivaSecundariaLanceInferior(xArmaduraPositivaSecundariaLanceInferior, yArmaduraPositivaSecundariaLanceInferior, canvas, armaduraPositivaSecundariaPaint)
        desenharTextoArmaduraPositivaSecundariaLanceInferior(xArmaduraPositivaSecundariaLanceInferior, yArmaduraPositivaSecundariaLanceInferior, canvas, textoPaint)

        if(escada.numeroLances == DOIS_LANCES){
            ////////////////////////////////////////////////
            //Armadura Positiva Secundairia Lance Superior//
            ////////////////////////////////////////////////
            desenharArmaduraPositivaSecundariaLanceSuperior(xArmaduraPositivaSecundariaLanceSuperior, yArmaduraPositivaSecundariaLanceSuperior, canvas, armaduraPositivaSecundariaPaint)
            desenharTextoArmaduraPositivaSecundariaLanceSuperior(xArmaduraPositivaSecundariaLanceSuperior, yArmaduraPositivaSecundariaLanceSuperior, canvas, textoPaint)
        }

        /////////////////////////////////////
        //Armadura Negativa Patamar Inicial//
        /////////////////////////////////////
        desenharArmaduraNegativaInicial(xArmaduraNegativaPatamarInicial, yArmaduraNegativaPatmarInicial, canvas, armaduraNegativaCortePaint)
        desenharTextoArmaduraNegativaInicial(xArmaduraNegativaPatamarInicial, yArmaduraNegativaPatmarInicial, canvas, textoPaint)

        ///////////////////////////////////////////
        //Armadura Negativa Patamar Intermediario//
        ///////////////////////////////////////////
        desenharArmaduraNegativaFinal(xArmaduraNegativaPatamarIntermediario, yArmaduraNegativaPatamarIntermediario, canvas, armaduraNegativaCortePaint)
        desenharTextoArmaduraNegativaFinal(xArmaduraNegativaPatamarIntermediario, yArmaduraNegativaPatamarIntermediario, canvas, textoPaint)
    }

    ////////////
    //Desenhos//
    ////////////

    ////////////
    //Contorno//
    ////////////
    override fun desenharContorno(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = contornoPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    /////////////////////////////////////////
    //Armnadura Positiva Principal Superior//
    /////////////////////////////////////////
    //Inicial
    override fun desenharArmaduraPositivaPrincipalInicialSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaPrincipalInicialSuperiorPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaPrincipalInicialSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaPrincipalInicialSuperior()
        val posicao = posicaoTextoArmaduraPositivaPrincipalInicialSuperior(xBase, yBase)

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

    ////////////////////////////////////////
    //Armadura Positiva Principal Inferior//
    ////////////////////////////////////////
    // Inicial
    override fun desenharArmaduraPositivaPrincipalInicialInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaPrincipalInicialInferiorPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaPrincipalInicialInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaPrincipalInicialInferior()
        val posicao = posicaoTextoArmaduraPositivaPrincipalInicialInferior(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint)
    }

    //Final
    override fun desenharArmaduraPositivaPrincipalFinalInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaPrincipalFinalInferiorPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaPrincipalFinalInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaPrincipalFinalInferior()
        val posicao = posicaoTextoArmaduraPositivaPrincipalFinalInferior(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint)
    }

    ////////////////////////////////////////////////
    //Armadura Positiva Secundaria Patamar Intermediario//
    ////////////////////////////////////////////////
    override fun desenharArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaSecundariaPatamarIntermediarioPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaSecundariaPatamarIntermediario()
        val posicao = posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint, angulo = -90f)
    }

    ///////////////////////////////////////////////
    //Armadura Positiva Secundaria Lance Superior//
    ///////////////////////////////////////////////
    override fun desenharArmaduraPositivaSecundariaLanceSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaSecundariaLanceSuperiorPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaSecundariaLanceSuperior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaSecundariaLanceSuperior()
        val posicao = posicaoTextoArmaduraPositivaSecundariaLanceSuperior(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint, angulo = -90f)
    }

    ///////////////////////////////////////////////
    //Armadura Positiva Secundaria Lance Inferior//
    ///////////////////////////////////////////////
    override fun desenharArmaduraPositivaSecundariaLanceInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaSecundariaLanceInferiorPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaSecundariaLanceInferior(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaSecundariaLanceInferior()
        val posicao = posicaoTextoArmaduraPositivaSecundariaLanceInferior(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint, angulo = -90f)
    }

    /////////////////////////////////////
    //Armadura Negativa Patamar Inicial//
    /////////////////////////////////////
    override fun desenharArmaduraNegativaInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraNegativaInicialPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraNegativaInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraNegativaInicial()
        val posicao = posicaoTextoArmaduraInicial(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint)
    }

    /////////////////////////////////////
    //Armadura Negativa Patamar Inicial//
    /////////////////////////////////////
    override fun desenharArmaduraNegativaFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraNegativaFinalPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraNegativaFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraNegativaFinal()
        val posicao = posicaoTextoArmaduraNegativaFinal(xBase, yBase)
        desenharTextoBarra(texto, posicao, canvas, paint)
    }

    //////////////
    //Auxiliares//
    //////////////

    ////////////
    //Contorno//
    ///////////
    override fun contornoPath(xBase: Float, yBase: Float): Path {
        val contornoPath = Path()

        val largura = larguraTotal
        val comprimentoPatamarInicial = comprimentoPatamarInicial
        val comprimentoLance = comprimentoLance
        val comprimentoTotalInterno = comprimentoLance + comprimentoPatamarIntermediario
        val xInicial = xBase + baseApoioEsquerdo
        val yInicial = yBase
        val piso = piso

        contornoPath.apply{
            moveTo(xInicial, yInicial)
            rLineTo(comprimentoTotalInterno, 0f)
            rLineTo(0f, largura)
            rLineTo(-comprimentoTotalInterno, 0f)
            rLineTo(0f, -largura)
            rMoveTo(comprimentoLance, largura)
            rLineTo(0f, -largura)
        }

        //Degraus
        contornoPath.rMoveTo(-comprimentoLance + piso, largura)

        for (degraus in 0..(escada.numeroDegraus - 3)) {
            contornoPath.apply {
                rLineTo(0f, -largura)
                rMoveTo(piso, largura)
            }
        }


        //Dividir Degraus se 2 lances
        if(escada.numeroLances == 2){
            contornoPath.apply{
                moveTo(xInicial + comprimentoPatamarInicial, yInicial + largura*0.5f)
                rLineTo(comprimentoLance, 0f)
            }

        }

        return contornoPath
    }

    /////////////////////////////////////////
    //Armnadura Positiva Principal Superior//
    /////////////////////////////////////////
    //Inicial
    override fun armaduraPositivaPrincipalInicialSuperiorPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaPrincipal.barraInicialSuperior.geometriaProjecaoHorizontalPath(xBase, yBase)
    }

    override fun textoArmaduraPositivaPrincipalInicialSuperior(): String {
        return  armaduraPositivaPrincipal.barraInicialSuperior.textoReduzido
    }

    override fun posicaoTextoArmaduraPositivaPrincipalInicialSuperior(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaPrincipal.barraInicialSuperior.posicaoTextoPlanta(xBase, yBase)
    }


    ///////////////////////////////////////
    //Armdaura Positiva PrincipalInferior//
    ///////////////////////////////////////
    //Inicial
    override fun armaduraPositivaPrincipalInicialInferiorPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaPrincipal.barraInicialInferior.geometriaProjecaoHorizontalPath(xBase, yBase)
    }

    override fun textoArmaduraPositivaPrincipalInicialInferior(): String {
        return  armaduraPositivaPrincipal.barraInicialInferior.textoReduzido
    }

    override fun posicaoTextoArmaduraPositivaPrincipalInicialInferior(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaPrincipal.barraInicialInferior.posicaoTextoPlanta(xBase, yBase)
    }


    //Final
    override fun armaduraPositivaPrincipalFinalInferiorPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaPrincipal.barraFinalInferior.geometriaProjecaoHorizontalPath(xBase, yBase)
    }

    override fun textoArmaduraPositivaPrincipalFinalInferior(): String {
        return  armaduraPositivaPrincipal.barraFinalInferior.textoReduzido
    }

    override fun posicaoTextoArmaduraPositivaPrincipalFinalInferior(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaPrincipal.barraFinalInferior.posicaoTextoPlanta(xBase, yBase)
    }

    //////////////////////////////////////////////////////
    //Armadura Positiva Secundaria Patamar Intermediario//
    //////////////////////////////////////////////////////
    override fun armaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraPatamarIntermediario.geometriaProjecaoHorizontalPath(xBase, yBase, vertical = true)
    }

    override fun textoArmaduraPositivaSecundariaPatamarIntermediario(): String {
        return armaduraPositivaSecundaria.barraPatamarIntermediario.textoReduzido
    }

    override fun posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaSecundaria.barraPatamarIntermediario.posicaoTextoPlanta(xBase, yBase, vertical = true)
    }


    ///////////////////////////////////////////////
    //Armadura Positiva Secundaria Lance Superior//
    ///////////////////////////////////////////////
    override fun armaduraPositivaSecundariaLanceSuperiorPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraLanceInferior.geometriaProjecaoHorizontalPath(xBase, yBase, vertical = true)
    }

    override fun textoArmaduraPositivaSecundariaLanceSuperior(): String {
        return armaduraPositivaSecundaria.barraLanceInferior.textoReduzido
    }

    override fun posicaoTextoArmaduraPositivaSecundariaLanceSuperior(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaSecundaria.barraLanceInferior.posicaoTextoPlanta(xBase, yBase, vertical = true)
    }

    ///////////////////////////////////////////////
    //Armadura Positiva Secundaria Lance Inferior//
    ///////////////////////////////////////////////
    override fun armaduraPositivaSecundariaLanceInferiorPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraLanceInferior.geometriaProjecaoHorizontalPath(xBase, yBase, vertical = true)
    }

    override fun textoArmaduraPositivaSecundariaLanceInferior(): String {
        return armaduraPositivaSecundaria.barraLanceInferior.textoReduzido
    }

    override fun posicaoTextoArmaduraPositivaSecundariaLanceInferior(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaSecundaria.barraLanceInferior.posicaoTextoPlanta(xBase, yBase, vertical = true)
    }

    //////////////////////////////
    //Armadura Negativa Inicial//
    /////////////////////////////
    override fun armaduraNegativaInicialPath(xBase: Float, yBase: Float): Path {
        return armaduraNegativa.barraInicialInferior.geometriaProjecaoHorizontalPath(xBase, yBase)
    }

    override fun textoArmaduraNegativaInicial(): String {
        return armaduraNegativa.barraInicialInferior.textoReduzido
    }

    override fun posicaoTextoArmaduraInicial(xBase: Float, yBase: Float): PointF {
        return armaduraNegativa.barraInicialInferior.posicaoTextoPlanta(xBase, yBase)
    }

    ////////////////////////////
    //Armadura Negativa Final//
    ///////////////////////////
    override fun armaduraNegativaFinalPath(xBase: Float, yBase: Float): Path {
        return armaduraNegativa.barraFinalInferior.geometriaProjecaoHorizontalPath(xBase, yBase)
    }

    override fun textoArmaduraNegativaFinal(): String {
        return armaduraNegativa.barraFinalInferior.textoReduzido
    }

    override fun posicaoTextoArmaduraNegativaFinal(xBase: Float, yBase: Float): PointF {
        return armaduraNegativa.barraFinalInferior.posicaoTextoPlanta(xBase, yBase)
    }
}