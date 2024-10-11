package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaC

import Constantes.DOIS_LANCES
import android.graphics.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.DesenhoCorte
import kotlin.math.cos
import kotlin.math.tan

class CorteSuperiorTipologiaC: DesenhoCorte(ESCADA) {
    //Desenhos
    //Desenho Geral
    override fun desenharCorte(xBase: Float,
                               yBase: Float,
                               canvas: Canvas,
                               contornoPaint: Paint,
                               armaduraPositivaPrincipalCortePaint: Paint,
                               armaduraPositivaSecundariaPaint: Paint,
                               armaduraNegativaCortePaint: Paint,
                               textoPaint: Paint,
                               tituloPaint: Paint,
                               linhaChamadaPaint: Paint
    ){

        //Coordenadas Contorno
        val xContorno = xBase
        val yContorno = yBase

        //Coordenadas Armadura Positiva Principal Inicial
        val xArmaduraPositivaPrinicpalInicialContorno = xBase + comprimentoTotal - cobrimento
        val yArmaduraPositivaPrincipalInicialContorno = yBase + peDireitoLance + cobrimento
        val xArmaduraPositivaPrincipalInicialIsolada = xArmaduraPositivaPrinicpalInicialContorno
        val yArmaduraPositivaPrincipalInicialIsolada = yArmaduraPositivaPrincipalInicialContorno + alturaApoioEsquerdo + 150f

        //Coordenadas Armadura Positiva Secundaria Patamar Intermediario
        val xArmaduraPositivaSecundariaPatamarIntermediario = xBase + comprimentoTotal - cobrimento
        val yArmaduraPositivaSecundariaPatamarIntermediario = yBase + peDireitoLance + espessura - cobrimento

        //Coordenadas Armadura Positiva Secundaria lance
        val xArmaduraPositivaSecundariaLance = xBase + comprimentoTotal - baseApoioDireito -  comprimentoPatamarIntermediario - deltaBarra + raio
        val yArmaduraPositivaSecundariaLance = yBase + peDireitoLance + espessura - cobrimento

        //Coordenadas Armadura Negativa Inicial
        val xArmaduraNegativaInicialContorno = xBase + cobrimento
        val yArmaduraNegativaInicialContorno = yBase + espelho + espessuraInclinada - cobrimentoInclinado - (baseApoioEsquerdo - cobrimento)*tan(anguloLance)
        val xArmaduraNegativaInicialIsolada = xArmaduraNegativaInicialContorno
        val yArmaduraNegativaInicialIsolada = yArmaduraNegativaInicialContorno - 150f

        //Coordenadas Armadura Negativa Final
        val xArmaduraNegativaFinalContorno = xBase  + comprimentoTotal - cobrimento
        val yArmaduraNegativaFinalContorno = yBase  + peDireitoLance + cobrimento + espessura - 2*cobrimento
        val xArmaduraNegativaFinalIsolada = xArmaduraNegativaFinalContorno
        val yArmaduraNegativaFinalIsolada = yArmaduraNegativaFinalContorno - 150f

        //Coordenadas Titulo
        val xTitulo = xBase - cobrimento
        val yTitulo = yArmaduraNegativaInicialIsolada - 50f
        val textoTitulo = "CORTE A-A"

        //Titulo
        desenharTitulo(xTitulo, yTitulo, canvas, tituloPaint, textoTitulo)

        //Contorno
        desenharContorno(xContorno, yContorno, canvas, contornoPaint)

        //Armadura Positiva Principal Inicial
        desenharArmaduraPositivaPrincipalInicial(xArmaduraPositivaPrinicpalInicialContorno, yArmaduraPositivaPrincipalInicialContorno, canvas, armaduraPositivaPrincipalCortePaint)
        desenharArmaduraPositivaPrincipalInicial(xArmaduraPositivaPrincipalInicialIsolada, yArmaduraPositivaPrincipalInicialIsolada, canvas, armaduraPositivaPrincipalCortePaint)
        desenharTextoArmaduraPositivaPrincipalInicial(xArmaduraPositivaPrincipalInicialIsolada, yArmaduraPositivaPrincipalInicialIsolada, canvas, textoPaint)

        //Armadura Positiva Secundaria Patmar Intermediario
        desenharArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, armaduraPositivaSecundariaPaint)
        desenharLinhaChamadaArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, linhaChamadaPaint)
        desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, textoPaint)

        //Armadura Positiva Secundaria Lance
        desenharArmaduraPositivaSecundariaLance(xArmaduraPositivaSecundariaLance, yArmaduraPositivaSecundariaLance, canvas, armaduraPositivaSecundariaPaint)
        desenharLinhaChamadaArmaduraPositivaSecundariaLance(xArmaduraPositivaSecundariaLance, yArmaduraPositivaSecundariaLance, canvas, linhaChamadaPaint)
        desenharTextoArmaduraPositivaSecundariaLance(xArmaduraPositivaSecundariaLance, yArmaduraPositivaSecundariaLance, canvas, textoPaint)

        //Armadura Negativa Patamar Inicial
        desenharArmaduraNegativaInicial(xArmaduraNegativaInicialContorno, yArmaduraNegativaInicialContorno, canvas, armaduraNegativaCortePaint)
        desenharArmaduraNegativaInicial(xArmaduraNegativaInicialIsolada, yArmaduraNegativaInicialIsolada, canvas, armaduraNegativaCortePaint)
        desenharTextoArmaduraNegativaInicial(xArmaduraNegativaInicialIsolada, yArmaduraNegativaInicialIsolada, canvas, textoPaint)

        //Armadura Negativa Lance Final
        desenharArmaduraNegativaFinal(xArmaduraNegativaFinalContorno, yArmaduraNegativaFinalContorno, canvas, armaduraNegativaCortePaint)
        desenharArmaduraNegativaFinal(xArmaduraNegativaFinalIsolada, yArmaduraNegativaFinalIsolada, canvas, armaduraNegativaCortePaint)
        desenharTextoArmaduraNegativaFinal(xArmaduraNegativaFinalIsolada, yArmaduraNegativaFinalIsolada, canvas, textoPaint)

    }

    //Contorno
    override fun desenharContorno(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = contornoPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    //Armadura Positiva Principal Inicial
    override fun desenharArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaPrincipalInicialPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val textos = textosArmaduraPositivaPrincipalInicial()
        val posicoes = posicaoTextosArmaduraPositivaPrincipalInicial(xBase, yBase)
        val angulos = angulosTextosArmaduraPositivaPrincipalInicial()

        textos.forEachIndexed { index, texto ->
            val posicao = posicoes[index]
            val angulo = angulos[index]
            val caixaTexto = Rect()
            paint.getTextBounds(texto,0, texto.length, caixaTexto)
            val xTexto =  when(index){
                0 -> caixaTexto.width()*0.25f
                3 -> -caixaTexto.width()*1.25f
                else -> -caixaTexto.width()*0.5f
            }
            val yTexto = when(index){
                0 -> caixaTexto.height()*0.5f
                4 -> caixaTexto.height()*1.5f
                else -> -caixaTexto.height()*0.25f
            }

            canvas.save()
            canvas.translate(posicao.x, posicao.y)
            canvas.rotate(angulo)
            canvas.drawText(texto, xTexto, yTexto, paint)
            canvas.restore()
        }
    }

    //Armadura Positiva Secundaria Patamar Intermediario
    override fun desenharArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaSecundariaPatamarIntermediarioPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharLinhaChamadaArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){
        val path = linhaDeChamadaArmaduraPositivaSecundariaPatamarIntermediarioPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaSecundariaPatamarIntermediario()
        val posicao = posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase, yBase)
        val angulo = anguloTextoArmaduraPositivaSecundariaPatamarIntermediario()

        val linhas = texto.split("\n")
        val textoPrimeiraLinha = linhas[0]
        val textoSegundaLinha = linhas[1]

        val caixaTextoPrimeiraLinha = Rect()
        paint.getTextBounds(texto,0, textoPrimeiraLinha.length, caixaTextoPrimeiraLinha)

        val caixaTextoSegundaLinha = Rect()
        paint.getTextBounds(texto,0, textoSegundaLinha.length, caixaTextoSegundaLinha)

        val xTextoPrimeiraLinha =  -caixaTextoPrimeiraLinha.width()*0.5f
        val yTextoPrimeiraLinha =  caixaTextoPrimeiraLinha.height()*1.5f

        val xTextoSegundaLinha = -caixaTextoSegundaLinha.width()*0.5f
        val yTextoSegundaLinha = yTextoPrimeiraLinha + caixaTextoPrimeiraLinha.height().toFloat() + 5f


        canvas.save()
        canvas.translate(posicao.x, posicao.y)
        canvas.rotate(angulo)
        canvas.drawText(textoPrimeiraLinha, xTextoPrimeiraLinha, yTextoPrimeiraLinha, paint)
        canvas.drawText(textoSegundaLinha, xTextoSegundaLinha, yTextoSegundaLinha, paint)
        canvas.restore()
    }

    //Armadura Positiva Secundaria Lance
    override fun desenharArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaSecundariaLancePath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharLinhaChamadaArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = linhaDeChamadaArmaduraPositivaSecundariaLancePath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val texto = textoArmaduraPositivaSecundariaLance()
        val posicao = posicaoTextoArmaduraPositivaSecundariaLance(xBase, yBase)
        val angulo = anguloTextoArmaduraPositivaSecundariaLance()

        val caixaTexto = Rect()
        paint.getTextBounds(texto,0, texto.length, caixaTexto)

        val xTexto =  -caixaTexto.width()*0.5f
        val yTexto =  caixaTexto.height()*1.5f

        canvas.save()
        canvas.translate(posicao.x, posicao.y)
        canvas.rotate(angulo)
        canvas.drawText(texto, xTexto, yTexto, paint)
        canvas.restore()
    }

    //Armadura Negativa  Inicial
    override fun desenharArmaduraNegativaInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraNegativaInicialPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraNegativaInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val textos = textosArmaduraNegativaInicial()
        val posicoes = posicaoTextosArmaduraNegativaInicial(xBase, yBase)
        val angulos = angulosTextosArmaduraNegativaInicial()

        textos.forEachIndexed { index, texto ->
            val posicao = posicoes[index]
            val angulo = angulos[index]
            val caixaTexto = Rect()
            paint.getTextBounds(texto,0, texto.length, caixaTexto)
            val xTexto =  when(index){
                0 -> -caixaTexto.width().toFloat()*1.25f
                2 -> caixaTexto.width().toFloat()*0.25f
                else -> -caixaTexto.width()*0.5f
            }
            val yTexto = when(index){
                1 -> -caixaTexto.height()*0.25f
                3 -> caixaTexto.height()*2.5f
                else -> caixaTexto.height()*0.5f
            }

            canvas.save()
            canvas.translate(posicao.x, posicao.y)
            canvas.rotate(angulo)
            canvas.drawText(texto, xTexto, yTexto, paint)
            canvas.restore()
        }
    }

    //Armadura Negativa  Final
    override fun desenharArmaduraNegativaFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraNegativaFinalPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraNegativaFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val textos = textosArmaduraNegativaFinal()
        val posicoes = posicaoTextosArmaduraNegativaFinal(xBase, yBase)
        val angulos = angulosTextosArmaduraNegativaFinal()

        textos.forEachIndexed { index, texto ->
            val posicao = posicoes[index]
            val angulo = angulos[index]
            val caixaTexto = Rect()
            paint.getTextBounds(texto,0, texto.length, caixaTexto)
            val xTexto =  when(index){
                0 -> caixaTexto.width().toFloat()*0.25f
                2 -> -caixaTexto.width().toFloat()*1.25f
                else -> -caixaTexto.width()*0.5f
            }
            val yTexto = when(index){
                1 -> -caixaTexto.height()*0.25f
                3 -> caixaTexto.height()*2.5f
                else -> caixaTexto.height()*0.5f
            }

            canvas.save()
            canvas.translate(posicao.x, posicao.y)
            canvas.rotate(angulo)
            canvas.drawText(texto, xTexto, yTexto, paint)
            canvas.restore()
        }
    }

    //Auxiliares
    //Contorno
    override fun contornoPath(xBase: Float, yBase: Float): Path {

        val contornoPath = Path()

        val delta = (espessura * tan(anguloLance * 0.5)).toFloat()
        val espessuraInclinada = espessura/ cos(anguloLance)

        val comprimentoSuperiorPatamarIntermediario = comprimentoPatamarIntermediario + baseApoioDireito
        val comprimentoIferiorPatamarIntermediario = comprimentoPatamarIntermediario + delta

        val comprimentoLance = comprimentoLance
        val comprimentoLanceInferior = comprimentoLance - delta

        val desnivelPorLance = peDireitoLance
        val desnivelPorLanceInferior = peDireitoLance - espelho - espessuraInclinada + espessura

        val alturaApoioEsquerdo = alturaApoioEsquerdo
        val alturaInferiorApoioEsquerdo = alturaApoioEsquerdo - espelho - espessuraInclinada
        val baseApoioEsquerdo = baseApoioEsquerdo

        val alturaApoioDireito = alturaApoioDireito
        val alturaInferiorApoioDireito = alturaApoioDireito - espessura
        val baseApoioDireito = baseApoioDireito

        //Contorno
        contornoPath.apply {
            moveTo(xBase, yBase)
            rLineTo(baseApoioEsquerdo, 0f)
            rMoveTo(comprimentoLance, desnivelPorLance)
            rLineTo(comprimentoSuperiorPatamarIntermediario , 0f)
            rLineTo(0f, alturaApoioDireito)
            rLineTo(-baseApoioDireito, 0f)
            rLineTo(0f, -alturaInferiorApoioDireito)
            rLineTo(-comprimentoIferiorPatamarIntermediario, 0f)
            rLineTo(-comprimentoLanceInferior, -desnivelPorLanceInferior)
            rLineTo(0f, alturaInferiorApoioEsquerdo)
            rLineTo(-baseApoioEsquerdo, 0f)
            rLineTo(0f, -alturaApoioEsquerdo)
        }

        //Degraus
        contornoPath.apply {
            moveTo(xBase + baseApoioEsquerdo, yBase)
            rLineTo(0f, escada.espelho.toFloat())
        }

        for (degraus in 0..(escada.numeroDegraus - 2)) {
            contornoPath.apply {
                rLineTo(piso, 0f)
                rLineTo(0f, espelho)
            }
        }

        return contornoPath
    }

    //Armadura Positiva Principal Inicial
    override fun armaduraPositivaPrincipalInicialPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaPrincipal.barraInicialSuperior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraPositivaPrincipalInicial(): List<String> {
        return armaduraPositivaPrincipal.barraInicialSuperior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float): List<PointF> {
        return armaduraPositivaPrincipal.barraInicialSuperior.posicaoTextosSegmentos(xBase, yBase, 2)
    }

    override fun angulosTextosArmaduraPositivaPrincipalInicial(): List<Float> {
        return armaduraPositivaPrincipal.barraInicialSuperior.angulosTextosSegmentos(2)
    }

    //Armadura Positiva Secundaria Lance
    override fun armaduraPositivaSecundariaLancePath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraLanceSuperior.geometriaCorteDireitaParaEsquerdaPath(xBase, yBase, anguloLanceNegativo)
    }

    override fun linhaDeChamadaArmaduraPositivaSecundariaLancePath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraLanceSuperior.linhaChamadaCorteDireitaParaEsquerdaPath(xBase, yBase, cobrimento, anguloLanceNegativo)
    }

    override fun textoArmaduraPositivaSecundariaLance(): String {
        return armaduraPositivaSecundaria.barraLanceSuperior.texto
    }

    override fun posicaoTextoArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaSecundaria.barraLanceSuperior.posicaoTextoCorteDireitaParaEsquerda(xBase, yBase, cobrimento , anguloLanceNegativo)
    }

    override fun anguloTextoArmaduraPositivaSecundariaLance(): Float {
        return (Math.toDegrees(anguloLance.toDouble())).toFloat()
    }

    //Armadura Positiva Secundaria Patamar Intermediario
    override fun armaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraPatamarIntermediario.geometriaCorteDireitaParaEsquerdaPath(xBase, yBase)
    }

    override fun linhaDeChamadaArmaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraPatamarIntermediario.linhaChamadaCorteDireitaParaEsquerdaPath(xBase, yBase, cobrimento)
    }

    override fun textoArmaduraPositivaSecundariaPatamarIntermediario(): String{
        return armaduraPositivaSecundaria.barraPatamarIntermediario.texto2Linhas
    }

    override fun posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaSecundaria.barraPatamarIntermediario.posicaoTextoCorteDireitaParaEsquerda(xBase, yBase, cobrimento)
    }

    //Armadura Negativa Inicial
    override fun armaduraNegativaInicialPath(xBase: Float, yBase: Float): Path {
        return armaduraNegativa.barraInicialSuperior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraNegativaInicial(): List<String> {
        return armaduraNegativa.barraInicialSuperior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraNegativaInicial(xBase: Float, yBase: Float): List<PointF> {
        return armaduraNegativa.barraInicialSuperior.posicaoTextosSegmentos(xBase, yBase , 1)
    }

    override fun angulosTextosArmaduraNegativaInicial(): List<Float> {
        return armaduraNegativa.barraInicialSuperior.angulosTextosSegmentos(1)
    }

    //Armadura Negativa Final
    override fun armaduraNegativaFinalPath(xBase: Float, yBase: Float): Path {
        return armaduraNegativa.barraFinalSuperior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraNegativaFinal(): List<String> {
        return armaduraNegativa.barraFinalSuperior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraNegativaFinal(xBase: Float, yBase: Float): List<PointF> {
        return armaduraNegativa.barraFinalSuperior.posicaoTextosSegmentos(xBase, yBase, 1)
    }

    override fun angulosTextosArmaduraNegativaFinal(): List<Float> {
        return armaduraNegativa.barraFinalSuperior.angulosTextosSegmentos(1)
    }
}