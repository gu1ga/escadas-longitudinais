import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Aco.Bitola

object Constantes {
    const val UM_LANCE: Int = 1
    const val DOIS_LANCES: Int = 2
    const val FCK_MAX = 50.0

    const val CA50: Double = 5000.0 //kgf/cm²
    const val CA60: Double = 6000.0 //kgf/cm²

    const val C20: Double = 200.0 //kgf/cm²
    const val C25: Double = 250.0 //kgf/cm²
    const val C30: Double = 300.0 //kgf/cm²
    const val C35: Double = 350.0 //kgf/cm²
    const val C40: Double = 400.0 //kgf/cm²
    const val C45: Double = 450.0 //kgf/cm²
    const val C50: Double = 500.0 //kgf/cm²

    const val DOMINIO_1: String = "Domínio 1"
    const val DOMINIO_2: String = "Domínio 2"
    const val DOMINIO_3_A: String = "Domínio 3a"
    const val DOMINIO_3_B: String = "Domínio 3b"
    const val DOMINIO_4: String = "Domínio 4"

    const val KX_LIMITE: Double = 0.45
    const val CM_PARA_M: Double = 0.01

    val BITOLA_5 = Bitola(5.0, 0.2, 0.16)
    val BITOLA_63 = Bitola(6.3, 0.31, 0.22)
    val BITOLA_8 = Bitola(8.0, 0.50, 0.40)
    val BITOLA_10 = Bitola(10.0, 0.80, 0.63)
    val BITOLA_125 = Bitola(12.5, 1.25, 1.00)
    val BITOLA_16 =  Bitola(16.0, 2.0, 1.60)
    val BITOLA_20 =  Bitola(20.0, 3.15, 2.50)
    val BITOLA_25 =  Bitola(25.0, 4.91, 3.85)
    val LISTA_BITOLAS = mutableListOf(BITOLA_5, BITOLA_63, BITOLA_8, BITOLA_10, BITOLA_125, BITOLA_16, BITOLA_20, BITOLA_25)

    const val ESPACAMENTO_MAXIMO_PRINCIPAL_NORMATIVO: Int = 20
    const val ESPACAMENTO_MAXIMO_SECUNDARIO_NORMATIVO: Int = 33

    /**LIMITES GEOMÉTRICOS*/
    const val ESPESSURA_MINIMA_NORMATIVA: Double = 8.0
    const val ESPESSURA_MAXIMA: Double = 30.0

    const val PE_DIREITO_MINIMO_UM_LANCE = 45.0
    const val PE_DIREITO_MAXIMO_UM_LANCE = 320.0

    const val PE_DIREITO_MINIMO_DOIS_LANCES = 90.0
    const val PE_DIREITO_MAXIMO_DOIS_LANCES = 640.0

    const val COMPRIMENTO_MINIMO_ABSOLUTO_PATAMAR: Double = 100.0
    const val COMPRIMENTO_MINIMO_NORMATIVO_PATAMAR: Double = 120.0
    const val COMPRIMENTO_MAXIMO_PATAMAR: Double = 250.0

    const val COMPRIMENTO_MINIMO_ABSOLUTO_LANCE: Double = 75.0
    const val COMPRIMENTO_MAXIMO_LANCE: Double = 670.0

    const val COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_TOTAL: Double = 180.0
    const val COMPRIMENTO_MAXIMO_LARGURA_TOTAL: Double = 600.0

    const val COMPRIMENTO_MINIMO_ABSOLUTO_LARGURA_POR_LANCE: Double = 90.0
    const val COMPRIMENTO_MAXIMO_LARGURA_POR_LANCE: Double = 300.0

    const val LARGURA_MINIMA_ABSOLUTA_POR_LANCE: Double = 80.0
    const val LARGURA_MINIMA_NORMATIVA_POR_LANCE: Double = 120.0
    const val LARGURA_MAXIMA_POR_LANCE: Double = 250.0

    const val LARGURA_MINIMA_ABSOLUTA_APOIO: Double = 12.0
    const val LARGURA_MAXIMA_APOIO: Double = 30.0

    const val PE_DIREITO_MINIMO: Double = 90.0
    const val PE_DIREITO_MAXIMO: Double = 640.0

    const val ALTURA_APOIO_MINIMA: Double = 12.0
    const val ALTURA_APOIO_MAXIMA: Double = 200.0

    /**LIMITES DE CARGAS*/
    const val CARGA_DISTRIBUIDA_MAXIMA: Double = 10000.0
}