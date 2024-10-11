package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras


import Constantes.ESPACAMENTO_MAXIMO_PRINCIPAL_NORMATIVO
import Constantes.ESPACAMENTO_MAXIMO_SECUNDARIO_NORMATIVO
import Constantes.LISTA_BITOLAS
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Barra
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Bitola
import kotlin.math.floor


open class Armadura(tipo: Long = POSITIVA_PRINCIPAL) {

    companion object{
        const val POSITIVA_PRINCIPAL = 1L
        const val POSTIIVA_SECUNDARIA = 2L
        const val NEGATIVA = 3L
    }

    var areaAcoNecessario: Double = 0.1
    val bitolas = LISTA_BITOLAS
    var tipo: Long = 0L
    val bitolasSelecionaveis: List<Bitola> get() = definirListaBitolasSelecionaveis()
    var espacamentoMinimo = 5
    var espacamentoMaximoNormativo = 20
    val espacamentosMaximosEfetivos = MutableList(bitolas.size) {espacamentoMinimo}
    var bitolaAtual = bitolas.last()
    val bitolaOtima: Bitola get() = bitolaOtima()
    var espacamentoAtual = espacamentoMinimo
    val areaAcoEfetiva: Double get() = espacamentoAtual*bitolaAtual.areaCentimetros
    val areaAcoEfetivaPorMetro: Double get() = (bitolaAtual.areaCentimetros/espacamentoAtual)*100
    val desperdicio = MutableList(bitolas.size) {0.0}
    private val _barras: List<Barra>
    val barras: List<Barra> get() = barrasAtualizadas()

    init{
        this.tipo = tipo
        espacamentoMaximoNormativo = when(tipo){
            POSITIVA_PRINCIPAL -> ESPACAMENTO_MAXIMO_PRINCIPAL_NORMATIVO
            POSTIIVA_SECUNDARIA -> ESPACAMENTO_MAXIMO_SECUNDARIO_NORMATIVO
            NEGATIVA -> ESPACAMENTO_MAXIMO_SECUNDARIO_NORMATIVO
            else -> ESPACAMENTO_MAXIMO_PRINCIPAL_NORMATIVO
        }
        _barras = when(tipo){
            POSITIVA_PRINCIPAL -> List(4) { Barra(bitolaAtual, espacamentoAtual) }
            POSTIIVA_SECUNDARIA -> List(4) { Barra(bitolaAtual, espacamentoAtual) }
            NEGATIVA -> List(4) { Barra(bitolaAtual, espacamentoAtual) }
            else -> List(4) { Barra(bitolaAtual, espacamentoAtual) }
        }
    }

    fun definirEspacamentosMaximosEfetivos(areaAco: Double = this.areaAcoNecessario){

        bitolas.forEachIndexed { i, bitola ->
            val espacamento = floor((100*bitola.areaCentimetros)/areaAco).toInt()
            espacamentosMaximosEfetivos[i] = if (espacamento <= espacamentoMaximoNormativo) espacamento else espacamentoMaximoNormativo
        }

        if(this.areaAcoNecessario != areaAco){
            this.areaAcoNecessario = areaAco
        }
        calcularDesperdicios()
    }

    fun definirListaBitolasSelecionaveis(): List<Bitola>{

        val listaBitolasSelecionaveis = mutableListOf<Bitola>()

        espacamentosMaximosEfetivos.forEachIndexed { i, espacamento ->
            if(espacamento >= espacamentoMinimo){
                listaBitolasSelecionaveis.add(bitolas[i])
            }
        }

        return listaBitolasSelecionaveis
    }

    fun calcularDesperdicios(){
        desperdicio.forEachIndexed { i, desperdicio ->
            this.desperdicio[i] = (bitolas[i].areaCentimetros/espacamentosMaximosEfetivos[i])
        }
    }

    fun bitolaOtima() : Bitola {

        var desperdicioTemporario = desperdicio.last()
        var bitolaOtima = bitolasSelecionaveis.first()


        bitolas.forEachIndexed { i, bitola ->

            if(desperdicio[i] < desperdicioTemporario && bitola in bitolasSelecionaveis){
                desperdicioTemporario = desperdicio[i]
                bitolaOtima = bitola
            }
        }

        return bitolaOtima
    }

    fun escolherBarraOtima(){
        this.bitolaAtual = bitolaOtima
        this.espacamentoAtual = espacamentosPossiveisPorBitola().first()
    }

    fun espacamentosPossiveisPorBitola(bitola: Bitola = bitolaAtual): List<Int>{

        val indice = bitolas.indexOf(bitola)
        val espacamentoMaximo = espacamentosMaximosEfetivos[indice]
        val listaEsapacamentos = mutableListOf<Int>()

        for (i in espacamentoMinimo .. espacamentoMaximo){
            val passo = i - espacamentoMinimo
            listaEsapacamentos.add(espacamentoMinimo + passo)
        }

        return listaEsapacamentos.reversed()
    }

    fun selecionarBitolaAtual(bitolaString: String){
        for (bitola in bitolasSelecionaveis){
            if(bitola.toString().equals(bitolaString)){
                bitolaAtual = bitola
                espacamentoAtual = espacamentosPossiveisPorBitola().first()
                return
            }
        }
    }

    private fun barrasAtualizadas(): List<Barra>{
        definirPropriedadesAtuais()
        return _barras
    }

    private fun definirPropriedadesAtuais(){
        for (barra in _barras){
            barra.apply {
                bitola = bitolaAtual
                espacamento = espacamentoAtual
            }
        }
    }
}
