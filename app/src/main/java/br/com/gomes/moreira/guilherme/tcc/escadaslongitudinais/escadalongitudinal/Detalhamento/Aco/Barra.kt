package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco

import Constantes.BITOLA_63
import GeometriaAnalitica.Segmento
import android.graphics.Path
import android.graphics.PointF
import java.lang.Math.toRadians
import kotlin.math.*


class Barra(bitola: Bitola = BITOLA_63, espacamento: Int) {

    var posicao: Int = -1
    var quantidade: Int = 1
    var repeticao: Int = 1
    var bitola: Bitola = BITOLA_63
    var espacamento: Int = 1
    val comprimento: Int get() = comprimento()
    val comprimentoMetros: Double get() = comprimento*0.01
    val texto: String get() =  texto()
    val texto2Linhas: String get() = texto2Linhas()
    val textoReduzido: String get() = textoReduzido()
    private var geometria: List<Segmento> = listOf()


    init{
        this.bitola = bitola
        this.espacamento = espacamento
    }

    private fun comprimento(): Int {
        var comprimento = 0
        for (segmento in geometria) {
            comprimento += segmento.comprimento.toInt()
        }
        return comprimento
    }

    private fun texto(): String{
        val _espacamento = if (espacamento != 0) " C/${espacamento}"  else ""
        return "$quantidade N${posicao} Φ${bitola.diametroMilimetros} $_espacamento C = $comprimento"
    }

    private fun  texto2Linhas(): String{
        val _espacamento = if (espacamento != 0) " C/${espacamento}"  else ""
        return "$quantidade N${posicao} Φ${bitola.diametroMilimetros}\n$_espacamento C = $comprimento"
    }

    private fun textoReduzido(): String{
        return "$quantidade N${posicao} Φ${bitola.diametroMilimetros}"
    }

    fun determinarQuantidades(comprimentoDistribuicao: Double, arredondarParaCima: Boolean = true){
        when(arredondarParaCima){
            true -> quantidade = ceil(comprimentoDistribuicao/espacamento).toInt()
            false -> quantidade = floor(comprimentoDistribuicao/espacamento).toInt()
        }
    }

    fun textoSegmentos(): List<String> {

        val textoSegmentos = mutableListOf<String>()

        for (segmento in geometria){
            textoSegmentos.add(segmento.comprimento.roundToInt().toString())
        }

        textoSegmentos.add(texto)

        return textoSegmentos
    }

    fun posicaoTextosSegmentos(xBase: Float, yBase: Float, indiceSegmentoGeral: Int = 0): List<PointF>{
        val posicaoTextoSegmentos = mutableListOf<PointF>()

        for (segmento in geometria){

            val x = xBase + segmento.pontoMedio.x
            val y = yBase + segmento.pontoMedio.y
            val pontoMedio = PointF(x,y)

            posicaoTextoSegmentos.add(pontoMedio)
        }

        val xGeral = xBase + geometria[indiceSegmentoGeral].pontoMedio.x
        val yGeral = yBase + geometria[indiceSegmentoGeral].pontoMedio.y
        val segmentoGeral = PointF(xGeral, yGeral)

        posicaoTextoSegmentos.add(segmentoGeral)

        return posicaoTextoSegmentos
    }

    fun angulosTextosSegmentos(indiceSegmentoGeral: Int = 0): List<Float>{
        val angulosTextosSegmentos = mutableListOf<Float>()

        for (segmento in geometria){
            angulosTextosSegmentos.add(segmento.angulo)
        }

        angulosTextosSegmentos.add(geometria[indiceSegmentoGeral].angulo)

        return angulosTextosSegmentos
    }

    fun posicaoTextoPlanta(xBase: Float = 0f, yBase: Float = 0f, vertical: Boolean = false): PointF{
        var comprimentoTotal = 0f

        for(segmento in geometria){
            if(segmento.deltaX != 0f){
                comprimentoTotal += segmento.deltaX
            }
        }

        val posicaoTexto = comprimentoTotal*0.5f
        var x = 0f
        var y = 0f

        if(vertical){
            x = xBase - 5f
            y = yBase + posicaoTexto
            return PointF(x, y)
        }

        x = xBase + posicaoTexto
        y = yBase - 5f

        return PointF(x, y)
    }

    fun posicaoTextoCorteEsquerdaParaDireita(xBase: Float = 0f, yBase: Float = 0f, cobrimento: Float, angulo: Float = 0f, raio: Float = 1f): PointF{

        val xInicial = xBase + raio + cobrimento + 5
        val yInicial = yBase - raio + cobrimento + 5

        val dx = if(angulo != toRadians(90.0).toFloat()) quantidade*espacamento*cos(angulo) else 0f
        val dy = if(angulo != toRadians(0.0).toFloat()) quantidade*espacamento*sin(angulo) else 0f

        val xFinal = xBase + raio + dx
        val yFinal = yBase - raio + dy

        val xTexto: Float = (xInicial + xFinal)*0.5f
        val yTexto: Float = (yInicial + yFinal)*0.5f

        return PointF(xTexto, yTexto)

    }

    fun posicaoTextoCorteDireitaParaEsquerda(xBase: Float = 0f, yBase: Float = 0f, cobrimento: Float, angulo: Float = 0f, raio: Float = 1f): PointF{

        val xInicial = xBase + raio + cobrimento + 5
        val yInicial = yBase - raio + cobrimento + 5

        val dx = if(angulo != toRadians(90.0).toFloat()) quantidade*espacamento*cos(angulo) else 0f
        val dy = if(angulo != toRadians(0.0).toFloat()) quantidade*espacamento*sin(angulo) else 0f

        val xFinal = xBase + raio - dx
        val yFinal = yBase - raio + dy

        val xTexto: Float = (xInicial + xFinal)*0.5f
        val yTexto: Float = (yInicial + yFinal)*0.5f

        return PointF(xTexto, yTexto)

    }

    fun definirGeometria(listaSegmentos: List<Segmento>) {
        geometria = listaSegmentos
    }

    fun geometriaPerfilPath(xBase: Float = 0f, yBase: Float = 0f): Path {

        val geometriaPath = Path()

        geometriaPath.apply {
            moveTo(xBase, yBase)
            for(segmento in geometria){
                rLineTo(segmento.deltaX, segmento.deltaY)
            }
        }

        return geometriaPath
    }

    fun geometriaCorteEsquerdaParaDireitaPath(xBase: Float, yBase: Float,angulo: Float = 0f, raio: Float = 1f): Path{
        val cortePath = Path()

        val xInicial = xBase + raio
        val yInicial = yBase - raio

        var xAtual = xInicial
        var yAtual = yInicial

        val dx = if(angulo != toRadians(90.0).toFloat()) espacamento*cos(angulo) else 0f
        val dy = if(angulo != toRadians(0.0).toFloat()) espacamento*sin(angulo) else 0f

        for(i in 1..quantidade){
            cortePath.addCircle(xAtual, yAtual, raio, Path.Direction.CCW)
            xAtual += dx
            yAtual += dy
        }

        return cortePath
    }

    fun linhaChamadaCorteEsquerdaParaDireitaPath(xBase: Float, yBase: Float, cobrimento: Float, angulo: Float = 0f, raio: Float = 1f): Path{
        val linhaChamadaPath = Path()

        var xAtual = xBase + raio
        var yAtual = yBase - raio

        val dxBarras = if(angulo != toRadians(90.0).toFloat()) espacamento*cos(angulo) else 0f
        val dyBarras = if(angulo != toRadians(0.0).toFloat()) espacamento*sin(angulo) else 0f

        val distanciaLinha = cobrimento + 5f
        val dxLinhas = if(angulo != toRadians(0.0).toFloat())  distanciaLinha*sin(angulo) else 0f
        val dyLinhas = if(angulo != toRadians(90.0).toFloat()) abs(distanciaLinha*cos(angulo)) else 0f

        linhaChamadaPath.moveTo(xAtual, yAtual)

        for(i in 1..quantidade){
            linhaChamadaPath.apply{
                moveTo(xAtual, yAtual)
                rLineTo(-dxLinhas, dyLinhas)
            }
            xAtual += dxBarras
            yAtual += dyBarras
        }

        linhaChamadaPath.lineTo(xBase + raio - dxLinhas, yBase - raio + dyLinhas)

        return linhaChamadaPath
    }

    fun geometriaCorteDireitaParaEsquerdaPath(xBase: Float, yBase: Float, angulo: Float = 0f, raio: Float = 1f): Path{
        val cortePath = Path()

        var xAtual = xBase - raio
        var yAtual = yBase - raio

        val dx = if(angulo != toRadians(90.0).toFloat()) espacamento*cos(angulo) else 0f
        val dy = if(angulo != toRadians(0.0).toFloat()) espacamento*sin(angulo) else 0f

        for(i in 1..quantidade){
            cortePath.addCircle(xAtual, yAtual, raio, Path.Direction.CCW)
            xAtual += -dx
            yAtual += dy
        }

        return cortePath
    }

    fun linhaChamadaCorteDireitaParaEsquerdaPath(xBase: Float = 0f, yBase: Float = 0f, cobrimento: Float, angulo: Float = 0f, raio: Float = 1f): Path{
        val linhaChamadaPath = Path()

        var xAtual = xBase - raio
        var yAtual = yBase - raio

        val dxBarras = if(angulo != toRadians(90.0).toFloat()) espacamento*cos(angulo) else 0f
        val dyBarras = if(angulo != toRadians(0.0).toFloat()) espacamento*sin(angulo) else 0f

        val distanciaLinha = cobrimento + 5f
        val dxLinhas = if(angulo != toRadians(0.0).toFloat())  distanciaLinha*sin(angulo) else 0f
        val dyLinhas = if(angulo != toRadians(90.0).toFloat()) abs(distanciaLinha*cos(angulo)) else 0f

        linhaChamadaPath.moveTo(xAtual, yAtual)

        for(i in 1..quantidade){
            linhaChamadaPath.apply{
                moveTo(xAtual, yAtual)
                rLineTo(dxLinhas, dyLinhas)
            }
            xAtual += -dxBarras
            yAtual += dyBarras
        }

        linhaChamadaPath.lineTo(xBase - raio + dxLinhas, yBase - raio + dyLinhas)

        return linhaChamadaPath
    }

    fun geometriaProjecaoHorizontalPath(xBase:Float = 0f, yBase: Float = 0f, vertical: Boolean = false): Path{

        val projecaoHorizontalPath = Path()

        projecaoHorizontalPath.moveTo(xBase, yBase)

        when(vertical){
            true -> {
                for(segmento in geometria){
                if(segmento.deltaX != 0f){
                    projecaoHorizontalPath.rLineTo(0f, segmento.deltaX)
                }
            }
            }
            false -> {
                for(segmento in geometria){
                if(segmento.deltaX != 0f){
                    projecaoHorizontalPath.rLineTo(segmento.deltaX, 0f)
                }
            }
            }
        }

        return projecaoHorizontalPath
    }
}
