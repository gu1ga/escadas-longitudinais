package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Desenho

import EscadaLongitudinal
import android.graphics.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraNegativa
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraPositivaPrincipal
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraPositivaSecundaria
import kotlin.math.cos
import kotlin.math.tan

open class DesenhoCorte(escada: EscadaLongitudinal) {

    val escada: EscadaLongitudinal
    val armaduraPositivaPrincipal: ArmaduraPositivaPrincipal get() = escada.armaduraPositivaPrincipal
    val armaduraPositivaSecundaria: ArmaduraPositivaSecundaria get() = escada.armaduraPositivaSecundaria
    val armaduraNegativa: ArmaduraNegativa get() = escada.armaduraNegativa

    //Variaveis Globais
    val raio: Float get() = 1f
    val deltaBarra: Float get() = ((escada.espessura - escada.cobrimento - raio) * tan(escada.anguloLance * 0.5)).toFloat()
    val cobrimento: Float get() = escada.cobrimento.toFloat()
    val cobrimentoInclinado: Float get() = cobrimento/cos(anguloLance)
    val baseApoioEsquerdo: Float get() = escada.baseApoioEsquerdo.toFloat()
    val alturaApoioEsquerdo: Float get() = escada.alturaApoioEsquerdo.toFloat()
    val baseApoioDireito: Float get() = escada.baseApoioDireito.toFloat()
    val alturaApoioDireito: Float get() = escada.alturaApoioDireito.toFloat()

    val espessura: Float get() = escada.espessura.toFloat()
    val espessuraInclinada: Float get() = espessura/cos(anguloLance)
    val comprimentoPatamarInicial: Float get() = escada.comprimentoPatamarInicial.toFloat()
    val comprimentoLance: Float get() = escada.comprimentoLance.toFloat()
    val comprimentoPatamarIntermediario: Float get() = escada.comprimentoPatamarIntermediario.toFloat()
    val comprimentoTotal: Float get() = escada.comprimentoTotal.toFloat()
    val peDireitoLance: Float get() = escada.peDireitoLance.toFloat()
    val anguloLance: Float get() = escada.anguloLance.toFloat()
    val anguloLanceNegativo: Float get() = -anguloLance


    val piso: Float get() = escada.piso.toFloat()
    val espelho: Float get() = escada.espelho.toFloat()

    init {
        this.escada = escada
    }

    //Funções de Desenho
    //Desenho Geral
    open fun desenharCorte(xBase: Float,
                           yBase: Float,
                           canvas: Canvas,
                           contornoPaint: Paint,
                           armaduraPositivaPrincipalCortePaint: Paint,
                           armaduraPositivaSecundariaPaint: Paint,
                           armaduraNegativaCortePaint: Paint,
                           textoPaint: Paint,
                           tituloPaint: Paint,
                           linhaChamadaPaint: Paint){}



    //Título
    open fun desenharTitulo(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint, titulo: String){
        canvas.drawText(titulo, xBase, yBase, paint)
    }

    //Contorno
    open fun desenharContorno(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Armadura Positiva Principal Inicial
    open fun desenharArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Armadura Positiva Principal Final
    open fun desenharArmaduraPositivaPrincipalFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    open fun desenharTextoArmaduraPositivaPrincipalFinal(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint){}

    //Armadura Positiva Secundaria Patamar Inicial
    open fun desenharArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharLinhaChamadaArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    //Armadura Positiva Secundaria Lance
    open fun desenharArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharLinhaChamadaArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}


    //Armadura Positiva Secundaria Patamar Intermediario
    open fun desenharArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharLinhaChamadaArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}

    open fun desenharTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float, canvas: Canvas, paint: Paint) {}


    //Armadura Negativa  Inicial
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

    //Armadura Positiva Principal Inicial
    open fun armaduraPositivaPrincipalInicialPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textosArmaduraPositivaPrincipalInicial(): List<String>{
        return listOf()
    }

    open fun posicaoTextosArmaduraPositivaPrincipalInicial(xBase: Float, yBase: Float): List<PointF>{
        return listOf()
    }

    open fun angulosTextosArmaduraPositivaPrincipalInicial(): List<Float>{
        return listOf()
    }

    //Armadura Positiva Principal Final
    open fun armaduraPositivaPrincipalFinalPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textosArmaduraPositivaPrincipalFinal(): List<String>{
        return listOf()
    }

    open fun posicaoTextosArmaduraPositivaPrincipalFinal(xBase: Float, yBase: Float): List<PointF>{
        return listOf()
    }

    open fun angulosTextosArmaduraPositivaPrincipalFinal(): List<Float>{
        return listOf()
    }

    //Armadura Positiva Secundaria Patamar Inicial
    open fun armaduraPositivaSecundariaPatamarInicialPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun linhaDeChamadaArmaduraPositivaSecundariaPatamarInicialPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaPatamarInicial(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaPatamarInicial(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    open fun anguloTextoArmaduraPositivaSecundariaPatamarInicial(): Float{
        return 0f
    }

    //Armadura Positiva Secundaria Lance
    open fun armaduraPositivaSecundariaLancePath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun linhaDeChamadaArmaduraPositivaSecundariaLancePath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaLance(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaLance(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    open fun anguloTextoArmaduraPositivaSecundariaLance(): Float{
        return 0f
    }

    //ArmaduraPositiva Secundaria Patmar
    open fun armaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun linhaDeChamadaArmaduraPositivaSecundariaPatamarIntermediarioPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textoArmaduraPositivaSecundariaPatamarIntermediario(): String{
        return ""
    }

    open fun posicaoTextoArmaduraPositivaSecundariaPatamarIntermediario(xBase: Float, yBase: Float): PointF{
        return PointF()
    }

    open fun anguloTextoArmaduraPositivaSecundariaPatamarIntermediario(): Float{
        return 0f
    }


    //Armadura Negativa Inicial
    open fun armaduraNegativaInicialPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textosArmaduraNegativaInicial(): List<String>{
        return listOf()
    }

    open fun posicaoTextosArmaduraNegativaInicial(xBase: Float, yBase: Float): List<PointF>{
        return listOf()
    }

    open fun angulosTextosArmaduraNegativaInicial(): List<Float>{
        return listOf()
    }

    //Armadura Negativa Final
    open fun armaduraNegativaFinalPath(xBase: Float, yBase: Float): Path{
        return Path()
    }

    open fun textosArmaduraNegativaFinal(): List<String>{
        return listOf()
    }

    open fun posicaoTextosArmaduraNegativaFinal(xBase: Float, yBase: Float): List<PointF>{
        return listOf()
    }

    open fun angulosTextosArmaduraNegativaFinal(): List<Float>{
        return listOf()
    }
}