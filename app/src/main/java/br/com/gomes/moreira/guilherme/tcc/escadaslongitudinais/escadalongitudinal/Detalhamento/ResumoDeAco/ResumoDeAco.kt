package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.ResumoDeAco

import Constantes.BITOLA_25
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.Armadura
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Barra
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Bitola

class ResumoDeAco(vararg armaduras: Armadura) {

    private val armaduras: Array<out Armadura> = armaduras
    private val barras: List<Barra> get() = barras()

    //Resumo
    //Tabela de Posicoes
    val posicoes: List<Int> get() = posicoes()
    val quantidadesPorPosicao: List<Int> get() = quantidadesPorPosicao()
    val bitolasPorPosicao: List<Bitola> get() = bitolasPorPosicao()
    val comprimentosUnitariosPorPosicao: List<Int> get() = comprimentosUnitariosPorPosicao()
    val comprimentosUnitariosPorPosicaoMetros: List<Double> get() = comprimentosUnitariosPorPosicaoMetros()
    val comprimentosTotaisPorPosicao: List<Int> get() = comprimentosTotaisPorPosicao()
    val comprimentosTotaisPorPosicaoMetros: List<Double> get() = comprimentosTotaisPorPosicaoMetros()


    //Tabela De Bitolas
    val bitolas: List<Bitola> get() = bitolas()
    val comprimentosTotaisPorBitola: List<Int> get() = comprimentosTotaisPorBitola()
    val comprimentosTotaisPorBitolaMetros: List<Double> get() = comprimentosTotaisPorBitolaMetros()
    val pesoTotalPorBitola: List<Int> get() = pesoTotalPorBitola()
    val pesoTotal: Int get() = pesoTotal()



    fun definirPosicoes() {
        enumerarBarras()
    }

    private fun enumerarBarras(){
        var posicaoAtual: Int = 1
        val barrasJaNumeradas: MutableList<Barra> = mutableListOf()

        for(i in barras.indices){
            if(barras[i].comprimento > 0 && barras[i] !in barrasJaNumeradas){
                barras[i].posicao = posicaoAtual
                for(j in barras.indices){
                    if(barras[j].bitola == barras[i].bitola && barras[j].comprimento == barras[i].comprimento){
                        barras[j].posicao = posicaoAtual
                        barrasJaNumeradas.add(barras[j])
                    }
                }
                posicaoAtual++
            }
        }
    }

    private fun posicoes(): List<Int>{
        val posicoes: MutableList<Int> = mutableListOf()
        for(barra in barras){
            if(barra.posicao !in posicoes && barra.posicao > -1){
                posicoes.add(barra.posicao)
            }
        }
        return posicoes.toList()
    }

    private fun quantidadesPorPosicao(): List<Int>{
        val quantidades = MutableList<Int>(posicoes.size) {0}
        for(posicao in posicoes.indices){
            for(barra in barras){
                if(barra.posicao == posicoes[posicao]){
                    quantidades[posicao] += barra.quantidade
                }
            }
        }
        return quantidades
    }

    private fun bitolasPorPosicao(): List<Bitola>{
        val bitolas = MutableList<Bitola>(posicoes.size) {BITOLA_25}
                for(posicao in posicoes.indices){
            for(barra in barras){
                if(barra.posicao == posicoes[posicao]){
                    bitolas[posicao] = barra.bitola
                }
            }
        }
        return bitolas
    }

    private fun comprimentosUnitariosPorPosicao(): List<Int>{
        val comprimentoUnitario = MutableList(posicoes.size) {0}
        for(posicao in posicoes.indices){
            for(barra in barras){
                if(barra.posicao == posicoes[posicao]){
                    comprimentoUnitario[posicao] += barra.comprimento
                }
            }
        }
        return comprimentoUnitario
    }

    private fun comprimentosUnitariosPorPosicaoMetros(): List<Double>{
        val comprimentoUnitario = MutableList(posicoes.size) {0.0}
        for(posicao in posicoes.indices){
            for(barra in barras){
                if(barra.posicao == posicoes[posicao]){
                    comprimentoUnitario[posicao] += barra.comprimentoMetros
                }
            }
        }
        return comprimentoUnitario
    }

    private fun comprimentosTotaisPorPosicao(): List<Int>{
        return quantidadesPorPosicao.zip(comprimentosUnitariosPorPosicao) { quantidade, comprimento -> quantidade*comprimento}
    }

    private fun comprimentosTotaisPorPosicaoMetros(): List<Double>{
        return quantidadesPorPosicao.zip(comprimentosUnitariosPorPosicaoMetros) { quantidade, comprimento -> quantidade*comprimento}
    }

    private fun pesosPorPosicao(): List<Int>{
        return comprimentosTotaisPorPosicao.zip(bitolasPorPosicao) { comprimento, bitola ->  (comprimento*bitola.pesoLinear).toInt()}
    }

    private fun barras(): List<Barra>{
        val barras: MutableList<Barra>  = mutableListOf()
        for(armadura in armaduras){
            for(barra in armadura.barras){
                barras.add(barra)
            }
        }
        return barras.toList()
    }

    private fun bitolas(): List<Bitola> {
        val bitolas = mutableListOf<Bitola>()

        for(bitola in bitolasPorPosicao){
            if(!bitolas.contains(bitola)){
                bitolas.add(bitola)
            }
        }
        return bitolas
    }

    private fun comprimentosTotaisPorBitola(): List<Int>{
        val comprimentosPorBitola = MutableList<Int>(bitolas.size) {0}

        for(i in comprimentosPorBitola.indices){
            for(barra in barras){
                if(barra.bitola.diametroMilimetros == bitolas[i].diametroMilimetros){
                    comprimentosPorBitola[i] += barra.comprimento
                }
            }
        }
        return comprimentosPorBitola
    }

    private fun comprimentosTotaisPorBitolaMetros(): List<Double>{
        val comprimentosPorBitola = MutableList<Double>(bitolas.size) {0.0}

        for(i in comprimentosPorBitola.indices){
            for(barra in barras){
                if(barra.bitola.diametroMilimetros == bitolas[i].diametroMilimetros){
                    comprimentosPorBitola[i] += barra.comprimentoMetros
                }
            }
        }
        return comprimentosPorBitola
    }

    private fun pesoTotalPorBitola(): List<Int>{
        return bitolas.zip(comprimentosTotaisPorBitola) { bitola, comprimento ->  (bitola.pesoLinear*comprimento).toInt()}
    }

    private fun pesoTotal(): Int{
        var peso: Int = 0
        for(bitolaPorBitola in pesoTotalPorBitola){
            peso += bitolaPorBitola
        }
        return peso
    }
}