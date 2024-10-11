package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.TipologiaC

import Constantes.DOIS_LANCES
import android.graphics.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho.DesenhoCorte
import kotlin.math.cos
import kotlin.math.tan

class CorteInferiorTipologiaC: DesenhoCorte(ESCADA) {
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
                               linhaChamadaPaint: Paint){

        //Coordenadas Contorno
        val xContorno = xBase
        val yContorno = yBase

        //Coordenadas Armadura Positiva Principal Inicial
        val xArmaduraPositivaPrinicpalInicialContorno = xBase + cobrimento
        val yArmaduraPositivaPrincipalInicialContorno = yBase + cobrimento
        val xArmaduraPositivaPrincipalInicialIsolada = xArmaduraPositivaPrinicpalInicialContorno
        val yArmaduraPositivaPrincipalInicialIsolada = yArmaduraPositivaPrincipalInicialContorno + alturaApoioEsquerdo + 150f

        //Coordenadas Armadura Positiva Principal Final
        val xArmaduraPositivaPrincipalFinalContorno = xBase + comprimentoTotal - cobrimento
        val yArmaduraPositivaPrincipalFinalContorno = yBase - peDireitoLance + cobrimento
        val xArmaduraPositivaPrincipalFinalIsolada = xArmaduraPositivaPrincipalFinalContorno
        val yArmaduraPositivaPrincipalFinalIsolada = yArmaduraPositivaPrincipalFinalContorno  + alturaApoioEsquerdo + 250f

        //Coordenadas Armadura Positiva Secundaria lance
        val xArmaduraPositivaSecundariaLance = xBase + baseApoioEsquerdo + comprimentoPatamarInicial + deltaBarra - raio
        val yArmaduraPositivaSecundariaLance = yBase + espessura - cobrimento

        //Coordenadas Armadura Positiva Secundaria Patamar Inicial
        val xArmaduraPositivaSecundariaPatamarIntermediario = xBase + comprimentoTotal- 2f*cobrimento
        val yArmaduraPositivaSecundariaPatamarIntermediario = yBase + -peDireitoLance + espessura - cobrimento

        //Coordenadas Armadura Negativa Inicial
        val xArmaduraNegativaPatamarInicialContorno = xBase + cobrimento
        val yArmaduraNegativaPatamarInicialContorno = yBase + cobrimentoInclinado + (baseApoioEsquerdo - cobrimento)*tan(anguloLance) + espessuraInclinada - 2*cobrimentoInclinado
        val xArmaduraNegativaPatamarInicialIsolada = xArmaduraNegativaPatamarInicialContorno
        val yArmaduraNegativaPatamarInicialIsolada = yArmaduraNegativaPatamarInicialContorno - 150f

        //Coordenadas Armadura Negativa Final
        val xArmaduraNegativaPatamarIntermediarioContorno = xBase  + comprimentoTotal - cobrimento
        val yArmaduraNegativaPatamarIntermediarioContorno = yBase  - peDireitoLance + espessura - cobrimento
        val xArmaduraNegativaPatamarIntermediarioIsolada = xArmaduraNegativaPatamarIntermediarioContorno
        val yArmaduraNegativaPatamarIntermediarioIsolada = yArmaduraNegativaPatamarIntermediarioContorno - 150f

        //Coordenadas Titulo
        val xTitulo = xBase - cobrimento
        val yTitulo = yArmaduraNegativaPatamarIntermediarioIsolada - 50f
        val textoTitulo = if(escada.numeroLances == DOIS_LANCES) "CORTE B-B" else "CORTE A-A"

        //Titulo
        desenharTitulo(xTitulo, yTitulo, canvas, tituloPaint, textoTitulo)

        //Contorno
        desenharContorno(xContorno, yContorno, canvas, contornoPaint)

        //Armadura Positiva Principal Inicial
        desenharArmaduraPositivaPrincipalInicial(xArmaduraPositivaPrinicpalInicialContorno, yArmaduraPositivaPrincipalInicialContorno, canvas, armaduraPositivaPrincipalCortePaint)
        desenharArmaduraPositivaPrincipalInicial(xArmaduraPositivaPrincipalInicialIsolada, yArmaduraPositivaPrincipalInicialIsolada, canvas, armaduraPositivaPrincipalCortePaint)
        desenharTextoArmaduraPositivaPrincipalInicial(xArmaduraPositivaPrincipalInicialIsolada, yArmaduraPositivaPrincipalInicialIsolada, canvas, textoPaint)

        //Armadura Positiva Priniciapl Final
        desenharArmaduraPositivaPrincipalFinal(xArmaduraPositivaPrincipalFinalContorno, yArmaduraPositivaPrincipalFinalContorno, canvas, armaduraPositivaPrincipalCortePaint)
        desenharArmaduraPositivaPrincipalFinal(xArmaduraPositivaPrincipalFinalIsolada, yArmaduraPositivaPrincipalFinalIsolada, canvas, armaduraPositivaPrincipalCortePaint)
        desenharTextoArmaduraPositivaPrincipalFinal(xArmaduraPositivaPrincipalFinalIsolada, yArmaduraPositivaPrincipalFinalIsolada, canvas, textoPaint)

        //Armadura Positiva Secundaria Lance
        desenharArmaduraPositivaSecundariaLance(xArmaduraPositivaSecundariaLance, yArmaduraPositivaSecundariaLance, canvas, armaduraPositivaSecundariaPaint)
        desenharLinhaChamadaArmaduraPositivaSecundariaLance(xArmaduraPositivaSecundariaLance, yArmaduraPositivaSecundariaLance, canvas, linhaChamadaPaint)
        desenharTextoArmaduraPositivaSecundariaLance(xArmaduraPositivaSecundariaLance, yArmaduraPositivaSecundariaLance, canvas, textoPaint)

        //Armadura Positiva Secundaria Patmar Intermediario
        desenharArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, armaduraPositivaSecundariaPaint)
        desenharLinhaChamadaArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, linhaChamadaPaint)
        desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xArmaduraPositivaSecundariaPatamarIntermediario, yArmaduraPositivaSecundariaPatamarIntermediario, canvas, textoPaint)

        //Armadura Negativa Patamar Inicial
        desenharArmaduraNegativaInicial(xArmaduraNegativaPatamarInicialContorno, yArmaduraNegativaPatamarInicialContorno, canvas, armaduraNegativaCortePaint)
        desenharArmaduraNegativaInicial(xArmaduraNegativaPatamarInicialIsolada, yArmaduraNegativaPatamarInicialIsolada, canvas, armaduraNegativaCortePaint)
        desenharTextoArmaduraNegativaInicial(xArmaduraNegativaPatamarInicialIsolada, yArmaduraNegativaPatamarInicialIsolada, canvas, textoPaint)

        //Armadura Negativa Patamar Intermediario
        desenharArmaduraNegativaFinal(xArmaduraNegativaPatamarIntermediarioContorno, yArmaduraNegativaPatamarIntermediarioContorno, canvas, armaduraNegativaCortePaint)
        desenharArmaduraNegativaFinal(xArmaduraNegativaPatamarIntermediarioIsolada, yArmaduraNegativaPatamarIntermediarioIsolada, canvas, armaduraNegativaCortePaint)
        desenharTextoArmaduraNegativaFinal(xArmaduraNegativaPatamarIntermediarioIsolada, yArmaduraNegativaPatamarIntermediarioIsolada, canvas, textoPaint)

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
                0 -> -caixaTexto.width()*1.25f
                else -> -caixaTexto.width()*0.5f
            }
            val yTexto = when(index){
                0 -> caixaTexto.height()*0.5f
                3 -> caixaTexto.height()*1.5f
                else -> -caixaTexto.height()*0.25f
            }

            canvas.save()
            canvas.translate(posicao.x, posicao.y)
            canvas.rotate(angulo)
            canvas.drawText(texto, xTexto, yTexto, paint)
            canvas.restore()
        }
    }

    //Armadura Positiva Principal Final
    override fun desenharArmaduraPositivaPrincipalFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val path = armaduraPositivaPrincipalFinalPath(xBase, yBase)
        canvas.drawPath(path, paint)
    }

    override fun desenharTextoArmaduraPositivaPrincipalFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {
        val textos = textosArmaduraPositivaPrincipalFinal()
        val posicoes = posicaoTextosArmaduraPositivaPrincipalFinal(xBase, yBase)
        val angulos = angulosTextosArmaduraPositivaPrincipalFinal()

        textos.forEachIndexed { index, texto ->
            val posicao = posicoes[index]
            val angulo = angulos[index]
            val caixaTexto = Rect()
            paint.getTextBounds(texto,0, texto.length, caixaTexto)
            val xTexto =  when(index){
                0 -> caixaTexto.width()*0.25f
                else -> - caixaTexto.width()*0.5f
            }
            val yTexto = when(index){
                0 -> caixaTexto.height()*0.5f
                3 -> caixaTexto.height()*1.5f
                else -> -caixaTexto.height()*0.25f
            }
            if(index < textos.size - 1) - caixaTexto.height()*0.25f else caixaTexto.height()*1.25f

            canvas.save()
            canvas.translate(posicao.x, posicao.y)
            canvas.rotate(angulo)
            canvas.drawText(texto, xTexto, yTexto, paint)
            canvas.restore()
        }
    }

    //Armadura Positiva Secundaria Lnace
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

    //Armadura Positiva Secundaria Patamar Intermediario
    override fun desenharArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){
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

    //Armadura Negativa Patamar Inicial
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

    //Armadura Negativa Patamar Intermediario
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
        val delta2 = piso + delta

        val comprimentoLance = comprimentoLance
        val comprimentoLanceInferior = comprimentoLance + delta2

        val comprimentoSuperiorPatamarIntermediario = comprimentoPatamarIntermediario + baseApoioDireito
        val comprimentoInferiorPatamarIntermediario = comprimentoPatamarIntermediario - delta2
        val desnivelPorLance = peDireitoLance
        val desnivelPorLanceInferior = peDireitoLance + (espessuraInclinada - espessura)

        val alturaApoioEsquerdo = alturaApoioEsquerdo
        val alturaInferiorApoioEsquerdo = alturaApoioEsquerdo - espessuraInclinada
        val baseApoioEsquerdo = baseApoioEsquerdo

        val alturaApoioDireito = alturaApoioDireito
        val alturaInferiorApoioDireito = alturaApoioDireito - espessura
        val baseApoioDireito = baseApoioDireito

        //Contorno
        contornoPath.apply {
            moveTo(xBase, yBase)
            rLineTo(baseApoioEsquerdo, 0f)
            rMoveTo(comprimentoLance, -desnivelPorLance)
            rLineTo(comprimentoSuperiorPatamarIntermediario, 0f)
            rLineTo(0f, alturaApoioDireito)
            rLineTo(-baseApoioDireito, 0f)
            rLineTo(0f, -alturaInferiorApoioDireito)
            rLineTo(-comprimentoInferiorPatamarIntermediario, 0f)
            rLineTo(-comprimentoLanceInferior, desnivelPorLanceInferior)
            rLineTo(0f, alturaInferiorApoioEsquerdo)
            rLineTo(-baseApoioEsquerdo, 0f)
            rLineTo(0f, -alturaApoioEsquerdo)
        }

        //Degraus
        contornoPath.apply {
            moveTo(xBase + baseApoioEsquerdo, yBase)
            rLineTo(0f, -escada.espelho.toFloat())
        }

        for (degraus in 0..(escada.numeroDegraus - 2)) {
            contornoPath.apply {
                rLineTo(piso, 0f)
                rLineTo(0f, -espelho)
            }
        }

        return contornoPath
    }

    //Armadura Positiva Principal Inicial
    override fun armaduraPositivaPrincipalInicialPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaPrincipal.barraInicialInferior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraPositivaPrincipalInicial(): List<String> {
        return armaduraPositivaPrincipal.barraInicialInferior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float): List<PointF> {
        return armaduraPositivaPrincipal.barraInicialInferior.posicaoTextosSegmentos(xBase, yBase, 1)
    }

    override fun angulosTextosArmaduraPositivaPrincipalInicial(): List<Float> {
        return armaduraPositivaPrincipal.barraInicialInferior.angulosTextosSegmentos(1)
    }

    //Armadura Positiva Principal Final
    override fun armaduraPositivaPrincipalFinalPath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaPrincipal.barraFinalInferior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraPositivaPrincipalFinal(): List<String> {
        return armaduraPositivaPrincipal.barraFinalInferior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraPositivaPrincipalFinal(xBase: Float, yBase: Float): List<PointF> {
        return armaduraPositivaPrincipal.barraFinalInferior.posicaoTextosSegmentos(xBase, yBase, 1)
    }

    override fun angulosTextosArmaduraPositivaPrincipalFinal(): List<Float> {
        return armaduraPositivaPrincipal.barraFinalInferior.angulosTextosSegmentos(1)
    }

    //Armadura Positiva Secundaria Lance
    override fun armaduraPositivaSecundariaLancePath(xBase: Float, yBase: Float): Path {

        return armaduraPositivaSecundaria.barraLanceInferior.geometriaCorteEsquerdaParaDireitaPath(xBase, yBase, anguloLanceNegativo)
    }

    override fun linhaDeChamadaArmaduraPositivaSecundariaLancePath(xBase: Float, yBase: Float): Path {
        return armaduraPositivaSecundaria.barraLanceInferior.linhaChamadaCorteEsquerdaParaDireitaPath(xBase, yBase, cobrimento, anguloLanceNegativo)
    }

    override fun textoArmaduraPositivaSecundariaLance(): String {
        return armaduraPositivaSecundaria.barraLanceInferior.texto
    }

    override fun posicaoTextoArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float): PointF {
        return armaduraPositivaSecundaria.barraLanceInferior.posicaoTextoCorteEsquerdaParaDireita(xBase, yBase, cobrimento , anguloLanceNegativo)
    }

    override fun anguloTextoArmaduraPositivaSecundariaLance(): Float {
        return (Math.toDegrees(anguloLanceNegativo.toDouble())).toFloat()
    }

    //Armadura Positiva Secundaria Patamar Intermediario
    override fun armaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path{
        return armaduraPositivaSecundaria.barraPatamarIntermediario.geometriaCorteEsquerdaParaDireitaPath(xBase , yBase, Math.toRadians(180.0).toFloat())
    }

    override fun linhaDeChamadaArmaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path{
        return armaduraPositivaSecundaria.barraPatamarIntermediario.linhaChamadaCorteEsquerdaParaDireitaPath(xBase, yBase , cobrimento, Math.toRadians(180.0).toFloat())
    }

    override fun textoArmaduraPositivaSecundariaPatamarIntermediario(): String{
        return armaduraPositivaSecundaria.barraPatamarIntermediario.texto2Linhas
    }

    override fun posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float): PointF{
        return armaduraPositivaSecundaria.barraPatamarIntermediario.posicaoTextoCorteEsquerdaParaDireita(xBase, yBase, cobrimento, Math.toRadians(180.0).toFloat())
    }

    //Armadura Negativa Patamar Inicial
    override fun armaduraNegativaInicialPath(xBase: Float, yBase: Float): Path {
        return armaduraNegativa.barraInicialInferior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraNegativaInicial(): List<String> {
        return armaduraNegativa.barraInicialInferior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraNegativaInicial(xBase: Float, yBase: Float): List<PointF> {
        return armaduraNegativa.barraInicialInferior.posicaoTextosSegmentos(xBase, yBase , 1)
    }

    override fun angulosTextosArmaduraNegativaInicial(): List<Float> {
        return armaduraNegativa.barraInicialInferior.angulosTextosSegmentos(1)
    }

    //Armadura Negativa Patamar Intermediario
    override fun armaduraNegativaFinalPath(xBase: Float, yBase: Float): Path {
        return armaduraNegativa.barraFinalInferior.geometriaPerfilPath(xBase, yBase)
    }

    override fun textosArmaduraNegativaFinal(): List<String> {
        return armaduraNegativa.barraFinalInferior.textoSegmentos()
    }

    override fun posicaoTextosArmaduraNegativaFinal(xBase: Float, yBase: Float): List<PointF> {
        return armaduraNegativa.barraFinalInferior.posicaoTextosSegmentos(xBase, yBase, 1)
    }

    override fun angulosTextosArmaduraNegativaFinal(): List<Float> {
        return armaduraNegativa.barraFinalInferior.angulosTextosSegmentos(1)
    }
}