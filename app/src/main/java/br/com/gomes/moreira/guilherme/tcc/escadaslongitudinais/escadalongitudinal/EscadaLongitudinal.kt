
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.*
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraNegativa
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraPositivaPrincipal
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.Armaduras.ArmaduraPositivaSecundaria
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.escadalongitudinal.Detalhamento.ResumoDeAco.ResumoDeAco
import java.util.*
import kotlin.math.*


class EscadaLongitudinal : Cisalhamento, Flecha, Flexao {


    companion object{
        const val ZERO = 0.toDouble()
        const val TIPOLOGIA_A = 7L
        const val TIPOLOGIA_B = 77L
        const val TIPOLOGIA_C = 777L
        const val TIPOLOGIA_D = 7777L
    }

    //Variáveis relacionadas ao concreto - todas as unidades em função de kgf e cm
    var fck: Double = Constantes.C25
    val fcd: Double get() = fck/1.4
    val Eci: Double get() = 1*5600* sqrt(fck*0.1)*10
    val Ecs: Double get() = min(1.0, 0.8 + 0.2*(fck*0.1/80))*Eci
    val fctkInf: Double get() = (0.7*0.3*((fck*0.1).pow(2.0/3)))*10
    val fctd: Double get() = fctkInf/1.4
    val fct: Double get() = (0.3*Math.pow((fck*0.1), 2.0/3))*10
    val trd: Double get() = 0.25*fctd
    var Es: Double = 2100000.0
    var cobrimento: Double = 2.0
    var cobrimentoEfetivo: Double = ZERO

    var fyk: Double = Constantes.CA50
    val fyd: Double get() = fyk/1.15

    //Variáveis relacionadas a geometria da escada - em cm
    var existePatamarInicial: Boolean = false
    var existePatamarIntermediario: Boolean = false
    var numeroLances: Int = Constantes.UM_LANCE
    var espessura: Double = ZERO
    var larguraTotal: Double = ZERO
    val larguraPorLance: Double get() = when(numeroLances){1 -> larguraTotal; 2 -> larguraTotal*0.5; else -> ZERO}
    var peDireitoTotal: Double = ZERO
    val peDireitoLance: Double get() = when(numeroLances){1 -> peDireitoTotal; 2 -> peDireitoTotal*0.5; else -> ZERO}
    var comprimentoPatamarInicial: Double = ZERO
    var comprimentoLance: Double = ZERO
    var comprimentoPatamarIntermediario: Double = ZERO
    var baseApoioEsquerdo: Double = ZERO
    var alturaApoioEsquerdo: Double = ZERO
    var baseApoioDireito: Double = ZERO
    var alturaApoioDireito: Double = ZERO
    val comprimentoTotal: Double get() = baseApoioEsquerdo + comprimentoPatamarInicial + comprimentoLance + comprimentoPatamarIntermediario + baseApoioDireito
    var piso: Double = ZERO
    var espelho: Double = ZERO
    val numeroDegraus: Int get() = if(espelho != 0.toDouble()) (peDireitoLance/espelho).toInt() else 0
    val anguloLance: Double get() = if(piso != 0.toDouble()) atan(espelho / piso) else ZERO


    //Tipologia
    val tipologia: Long get() = definirTipologia()


    //Variáveis relacionadas a propriedades geométricas pertinentes para cálculos e verificações
    val vao: Double get() = comprimentoPatamarInicial + comprimentoLance + comprimentoPatamarIntermediario
    val momentoDeInerciaTotal: Double get() = (larguraPorLance*espessura*espessura*espessura)/12.0
    val areaSecaoTransversal: Double get() = (larguraPorLance*espessura)
    val rigidezFlexaoTotal: Double get() = Ecs*momentoDeInerciaTotal
    val rigidezNormal: Double get() = Ecs*areaSecaoTransversal

    //Variáveis relacionadas as cargas
    //Sobrecarga segundo a NBR6120:2019
    var sobrecargaNormativa: Double = ZERO

    //Cargas nos patamares
    val pesoProprioPatamares: Double get() = 25.0*espessura
    var cargaPermanenteManualPatamares: Double = ZERO
    var sobrecargaManualPatamares: Double = ZERO

    //Cargas permanentes e sobrecargas totais nos patamares
    val cargaPermanenteTotalPatamares: Double get() = pesoProprioPatamares + cargaPermanenteManualPatamares
    val sobrecargaTotalPatamares: Double get() = sobrecargaNormativa + sobrecargaManualPatamares

    //Combinação de carga para o ELU e ELS nos patamres
    val cargaTotalPatamaresELU: Double get() = cargaPermanenteTotalPatamares + sobrecargaTotalPatamares
    val cargaTotalPatamaresELS: Double get() = cargaPermanenteTotalPatamares + sobrecargaTotalPatamares*0.3

    //Cargas no lance
    val pesoProprioLance: Double get() = (espessura/cos(anguloLance) + espelho*0.5)*25
    var cargaPermanenteManualLance: Double = ZERO
    var sobrecargaManualLance: Double = ZERO

    //Cargas permanentes e sobrecargas totais no lance
    val cargaPermanenteTotalLance: Double get() = pesoProprioLance + cargaPermanenteManualLance
    val sobrecargaTotalLance: Double get() = sobrecargaNormativa + sobrecargaManualLance

    //Combinação de carga para o ELU e ELS no lance
    val cargaTotalLanceELU: Double get() = cargaPermanenteTotalLance + sobrecargaTotalLance
    val cargaTotalLanceELS: Double get() = cargaPermanenteTotalLance + sobrecargaTotalLance*0.3

    //Variáveis realcionadas à análise estrutural
    var mk: Double = ZERO
    val md: Double get() = mk*1.4
    var vk: Double = ZERO
    val vd: Double get() = vk*1.4

    //Variáveis relacionadas ao dimensionamento à flexão
    val alturaUtil: Double get() = espessura - cobrimentoEfetivo
    val alturaUtilMinima: Double get() = definirAlturaUtilMinima(md, fyd, 100.0, fcd)
    var alturaLinhaNeutra: Double = ZERO
    var kx: Double = ZERO
    var dominio: String = "Indefinido"
    var alturaUtilMinimaOk: Boolean = false
    var dominioOk: Boolean = false
    var flexaoOk: Boolean = false

    //Variáveis relacionadas à verificação de flecha
    var momentoELS: Double = ZERO
    val momentoFissuracao: Double get() = calcularMr(fct, momentoDeInerciaTotal, espessura*0.5)*0.01
    var alturaLinhaNeutraEstadioII: Double = ZERO
    var momentoInerciaEstadioII: Double = ZERO
    var rigidezEquivalente: Double = ZERO
    val trechoFissurado: Boolean get() = momentoELS >= momentoFissuracao
    var rigidezFinal: Double = ZERO
    var flechaImediata: Double = ZERO
    val flechaDiferida: Double get() = calcularFlechaDiferida(flechaImediata)
    val flechaTotal: Double get() = flechaImediata + flechaDiferida
    val flechaLimite: Double get() = vao/250.0
    val flechaOk: Boolean get() = flechaTotal <= flechaLimite

    //Variáveis relacionadas à verificação do cisalhamento
    val k: Double get() = calcularK(alturaUtil)
    val vrd1: Double get() = calcularVrd1(trd, 100.0, alturaUtil, k)
    val cisalhamentoOK: Boolean get() = vrd1 > vd

    //Objecto de Modelo Estrutural Para Cálculo dos Momentos para o ELU (Equações, momento máximo, cortante máxima)
    val modeloEstruturalELU = ModeloEstrutural()

    //Objecto de Modelo Estrutural Para Cálculo dos Momentos para o ELU (Equações, momento máximo, deslocamentos)
    val modeloEstruturalELS = ModeloEstrutural()

    //Variáveis relacionadas ao detalhamento
    val areaAcoMinima: Double get() = 100*espessura*definirTaxaMinima(fck)
    var areaAcoPositivaPrincipalNecessaria: Double = ZERO
    var areaAcoPoisitivaSecundariaNecessaria: Double = ZERO
    var areaAcoNegativaNecessaria: Double = ZERO


    //Variáveis relacionadas ao aço positivo principal
    val armaduraPositivaPrincipal = ArmaduraPositivaPrincipal()

    //Variáveis relacionadas ao aço positivo secundário
    val armaduraPositivaSecundaria = ArmaduraPositivaSecundaria()

    //Variáveis relacionadas ao aço negativo
    val armaduraNegativa = ArmaduraNegativa()

    //ResumoAco
    var resumoDeAco: ResumoDeAco = ResumoDeAco(armaduraPositivaPrincipal, armaduraPositivaSecundaria, armaduraNegativa)

    //Métodos

    //Método para definir tipologia
    /**Define a tipologia da escada baseado nos patamares e lances*/
    private fun definirTipologia(): Long{
        if(existePatamarInicial){
            if(existePatamarIntermediario){
                return TIPOLOGIA_A
            }
            return TIPOLOGIA_B
        }

        if(existePatamarIntermediario){
            return TIPOLOGIA_C
        }

        return TIPOLOGIA_D
    }

    //Métodos relacionados ao cálculo dos esforços para dimensionamento e verificações no ELU
    /**Calcula os esforços característicos - Mk, Vk*/
    fun calcularEsforcosCaracteristicos(){
        this.definirModeloEstruturalELU()
        this.calcularMomentoMaximo()
        this.calcularCortanteMaxima()
    }

    /**Define e geometria e cargas do objeto de modelo estrutural do ELU*/
    private fun definirModeloEstruturalELU() {
        modeloEstruturalELU.apply {
            a = comprimentoPatamarInicial*Constantes.CM_PARA_M
            b = comprimentoLance*Constantes.CM_PARA_M
            c = comprimentoPatamarIntermediario*Constantes.CM_PARA_M
            d = peDireitoLance*Constantes.CM_PARA_M
            q1 = cargaTotalPatamaresELU
            q2 = cargaTotalLanceELU
            q3 = cargaTotalPatamaresELU
            redefinir()
        }
    }

    /**Calcula o momento característico máximo com base no modelo estrutural do ELU*/
    private fun calcularMomentoMaximo(){
        mk = this.modeloEstruturalELU.calcularMomentoMax()
    }

    //Métodos relacionados à determinação das áreas de aço necessárias
    /**Calcula o As minima, As principal, As secuncaria e As negativa*/
    fun calcularAreasDeAco(){
        calcularAcoPositivoPrincipal()
        calcularAcoPositivoSecundario()
        calcularAcoNegativo()
    }


    /**Calcula a area de aço positiva principal)*/
    private fun calcularAcoPositivoPrincipal(){
        checarAlturaUtilMinima()
        determinarLinhaNeutra()
        checarDominio()
        checarFlexao()
        determinarAreasDeAco()
    }

    /**Checa se a altura útil atual é maior ou igual que a altura útil mínima*/
    private fun checarAlturaUtilMinima(){
        alturaUtilMinimaOk = when(alturaUtil >= alturaUtilMinima){
            true -> true
            false -> false
        }
    }

    /**Determina a altura da linha neutra, kx, e dominio*/
    private fun determinarLinhaNeutra(){
        if(alturaUtilMinimaOk){
            alturaLinhaNeutra = calcularX(md, fyd, 100.0, fcd, alturaUtil)
            kx = calcularKx(alturaLinhaNeutra, alturaUtil)
            dominio = dominio(kx)
        }
    }

    /**Checa o domínimo de deformação*/
    private fun checarDominio(){
        when(dominio){
            Constantes.DOMINIO_1 -> {dominioOk = true}
            Constantes.DOMINIO_2 -> {dominioOk = true}
            Constantes.DOMINIO_3_A -> {dominioOk = true}
            else -> {dominioOk = false}
        }
    }

    /**Checa se a peça foi pode ser dimensionada e está no domínio 2 ou 3a*/
    private fun checarFlexao(){
        when(alturaUtilMinimaOk && dominioOk){
            true ->  {flexaoOk = true}
            false -> {flexaoOk = false}
        }
    }

    /**Calcula as áreas de aço e checa se é maior que a mínima*/
    private fun determinarAreasDeAco(){
        if(flexaoOk){
            areaAcoPositivaPrincipalNecessaria = calcularAs(md, fyd, alturaUtil, alturaLinhaNeutra)

            if(this.areaAcoPositivaPrincipalNecessaria < areaAcoMinima){

                this.areaAcoPositivaPrincipalNecessaria = this.areaAcoMinima

            }
        }
    }

    /**Calcula a area de aço poisitiva secundária se a peça foi dimensionada*/
    private fun calcularAcoPositivoSecundario(){
        if(flexaoOk) {
            areaAcoPoisitivaSecundariaNecessaria = Collections.max(listOf(areaAcoPositivaPrincipalNecessaria * 0.2, 0.9, 0.5 * areaAcoMinima))
        }
    }

    /**Calcula a area de aço negativa secundária se a peça foi dimensionada*/
    private fun calcularAcoNegativo(){
        if(flexaoOk) {
            areaAcoNegativaNecessaria = areaAcoMinima
        }
    }

    //Métodos relacionados ao detalhamento
    /**Detalha a escada*/
    fun detalharEsacada(){
        calcularEspacamentos()
        selecionarBarrasOtimas()
        calcularQuantidades()
        definirGeometriaBarras(this)
        definirPosicoes()
    }

    /**Alimenta as armaduras com a área de aço necessaária*/
    fun calcularEspacamentos(){
        determinarEspacamentosPositivoPrincipal()
        determinarEspacamentosPositivoSecundario()
        determinarEspacamentosNegativo()
    }

    /**Calcula as quantidades por bitola e espaçamento atual para as armaduras*/
    fun calcularQuantidades(){
        determinarQuantidadesPositivoPrincipal()
        determinarQuantidadesPoistivoSecundario()
        determinarQuantidadesNegtavio()
    }

    /**Seleciona as barras ótimas para cada armdaura*/
    fun selecionarBarrasOtimas(){
        selecionarBarrasOtimasPositivoPrincipal()
        selecionarBarrasOtimasPositivoSecundario()
        selecionarBarrasOtimasNegativo()
    }

    /**Define as posices das barras com base na bitola e comprimento*/
    fun definirPosicoes(){
        resumoDeAco.definirPosicoes()
    }

    /**Determinar os espacamentos por bitola para a armadura positiva principal*/
    private fun determinarEspacamentosPositivoPrincipal(){
        armaduraPositivaPrincipal.definirEspacamentosMaximosEfetivos(areaAcoPositivaPrincipalNecessaria)
    }

    /**Determinar os espacamentos por bitola para a armadura positiva secundária*/
    private fun determinarEspacamentosPositivoSecundario(){
        armaduraPositivaSecundaria.definirEspacamentosMaximosEfetivos(areaAcoPoisitivaSecundariaNecessaria)
    }

    /**Determinar os espacamentos por bitola para a armadura negativa*/
    private fun determinarEspacamentosNegativo(){
        armaduraNegativa.definirEspacamentosMaximosEfetivos(areaAcoNegativaNecessaria)
    }

    /**Determinar as quantiades por bitola para a armadura positiva principal*/
    private fun determinarQuantidadesPositivoPrincipal(){
        armaduraPositivaPrincipal.apply {
          for (barra in barras){
              barra.determinarQuantidades(larguraPorLance)
          }
        }
    }

    /**Determinar as quantiades por bitola para a armadura positiva secundaria*/
    private fun determinarQuantidadesPoistivoSecundario(){

        val comprimentoDistribuicaoPatamarInicial = if(existePatamarInicial) baseApoioEsquerdo + comprimentoPatamarInicial else ZERO
        val comprimentoDistribuicaoLances = when(tipologia){
            TIPOLOGIA_A -> if(cos(anguloLance) != ZERO) (comprimentoLance + piso)/cos(anguloLance) else ZERO
            else -> if(cos(anguloLance) != ZERO) (comprimentoLance)/cos(anguloLance) else ZERO
        }
        val comprimentoDistribuicaoPatamarIntermediario = if(existePatamarIntermediario) comprimentoPatamarIntermediario + baseApoioDireito else ZERO


        armaduraPositivaSecundaria.apply {
            barraPatamarInicial.determinarQuantidades(comprimentoDistribuicaoPatamarInicial)
            barraLanceSuperior.determinarQuantidades(comprimentoDistribuicaoLances)
            barraLanceInferior.determinarQuantidades(comprimentoDistribuicaoLances)
            barraPatamarIntermediario.determinarQuantidades(comprimentoDistribuicaoPatamarIntermediario)
        }
    }

    /**Determinar as quantiades por bitola para a armadura negativa*/
    private fun determinarQuantidadesNegtavio(){

        val comprimentoDistribuicaoInicialSuperior = when(tipologia){
            TIPOLOGIA_A -> larguraTotal
            TIPOLOGIA_B -> larguraTotal
            TIPOLOGIA_C -> larguraPorLance
            TIPOLOGIA_D -> larguraPorLance
            else -> ZERO
        }
        val comprimentoDistribuicaoFinalSuperior = when(tipologia){
            TIPOLOGIA_A -> larguraTotal
            TIPOLOGIA_B -> larguraPorLance
            TIPOLOGIA_C -> larguraTotal
            TIPOLOGIA_D -> larguraPorLance
            else -> ZERO
        }
        val comprimentoDistribuicaoInicialInferior = when(tipologia){
            TIPOLOGIA_A -> larguraTotal
            TIPOLOGIA_B -> larguraTotal
            TIPOLOGIA_C -> larguraPorLance
            TIPOLOGIA_D -> larguraPorLance
            else -> ZERO
        }
        val comprimentoDistribuicaoFinalInferior = when(tipologia){
            TIPOLOGIA_A -> larguraTotal
            TIPOLOGIA_B -> larguraPorLance
            TIPOLOGIA_C -> larguraTotal
            TIPOLOGIA_D -> larguraPorLance
            else -> ZERO
        }

        armaduraNegativa.apply{
            barraInicialSuperior.determinarQuantidades(comprimentoDistribuicaoInicialSuperior)
            barraFinalSuperior.determinarQuantidades(comprimentoDistribuicaoFinalSuperior)
            barraInicialInferior.determinarQuantidades(comprimentoDistribuicaoInicialInferior)
            barraFinalInferior.determinarQuantidades(comprimentoDistribuicaoFinalInferior)
        }
    }

    private fun selecionarBarrasOtimasPositivoPrincipal(){
        armaduraPositivaPrincipal.escolherBarraOtima()
    }

    private fun selecionarBarrasOtimasPositivoSecundario(){
        armaduraPositivaSecundaria.escolherBarraOtima()
    }

    private fun selecionarBarrasOtimasNegativo(){
        armaduraNegativa.escolherBarraOtima()
    }

    //Metodos relacionados ao cálculo e verificação de flechas
    /**Calcula e verifica o deslocamento máximo com base no modelo estrutural ELU*/
    fun verificarFlecha(){
        definirModeloEstruturalELS()
        definirMomentoELS()
        definirRigidezEquivalente()
        calcularFlechaImediata()
    }

    /**Define o objeto de modelo estrutural para cálculo do deslocamento*/
    private fun definirModeloEstruturalELS() {
        this.modeloEstruturalELS.apply{
            a = comprimentoPatamarInicial
            b = comprimentoLance
            c = comprimentoPatamarIntermediario
            d = peDireitoLance
            q1 = cargaTotalPatamaresELS*larguraPorLance*0.0001
            q2 = cargaTotalLanceELS*larguraPorLance*0.0001
            q3 = cargaTotalPatamaresELS*larguraPorLance*0.0001
            redefinir()
        }
    }

    /**Define o momento para a combinação quase permanente*/
    private fun definirMomentoELS(){
        momentoELS = modeloEstruturalELS.calcularMomentoMax()*0.01 //kgf*cm
    }

    /**Define a rigidezFinal da peça de acordo com a fissuração*/
    private fun definirRigidezEquivalente(){
        when(trechoFissurado) {

            true -> {
                alturaLinhaNeutraEstadioII = calcularAlturaLinhaNeutraEstadioII(larguraPorLance, armaduraPositivaPrincipal.areaAcoEfetiva, alturaUtil, Es, Ecs)

                momentoInerciaEstadioII = calcularMomentoInerciaEstadioII(alturaLinhaNeutraEstadioII, larguraPorLance, armaduraPositivaPrincipal.areaAcoEfetiva, alturaUtil, Es, Ecs)

                rigidezEquivalente = calcularRigidezEquivalente(Ecs, momentoDeInerciaTotal, momentoFissuracao, momentoELS, momentoInerciaEstadioII)

                when(this.rigidezEquivalente <= this.rigidezFlexaoTotal) {

                    true -> rigidezFinal = rigidezEquivalente
                    false -> rigidezFinal = rigidezFlexaoTotal

                }
            }
            false -> this.rigidezFinal = rigidezFlexaoTotal
        }
    }

    /**Calcula a flehca imediata*/
    private fun calcularFlechaImediata(){
        modeloEstruturalELS.definirDeslocamentos(rigidezFinal, rigidezNormal)
        flechaImediata = abs(modeloEstruturalELS.calcularDeslocamentoMax())
    }

    /**Calcula a corte característica máxima com base no modelo estrutural do ELU*/
    private fun calcularCortanteMaxima(){
        vk = this.modeloEstruturalELU.calcularVmax()
    }

    override fun toString(): String {

        val outPutString = "GEOMETRIA\n\n" +
                "Número de Lances: ${this.numeroLances}\n" +
                "Espessura: ${this.espessura}\n" +
                "Comprimento Patamar Inicial; ${this.comprimentoPatamarInicial}\n" +
                "Comprimento Patamar Interemdiario; ${this.comprimentoPatamarIntermediario}\n" +
                "Comprimento Lance: ${this.comprimentoLance}\n" +
                "Pé Direito total: ${this.peDireitoTotal}\n" +
                "Pé Direito por lance: ${this.peDireitoLance}\n" +
                "Largura total: ${this.larguraTotal}\n" +
                "Largura por lance: ${this.larguraPorLance}\n" +
                "Piso: ${this.piso}\n"+
                "Espelho: ${this.espelho}\n"+
                "Ângulo lance: ${this.anguloLance}\n" +
                "Cos(angulo): ${cos(this.anguloLance)}\n"+
                "Tipologia: ${this.tipologia}\n"+

                "\n\nCARGAS\n" +
                "Sobrecarga normativa: ${this.sobrecargaNormativa}\n"+
                "Peso próprio patamares: ${this.pesoProprioPatamares}\n" +
                "Carga permanente manual patamares: ${this.cargaPermanenteManualPatamares}\n"+
                "Sobrecarga manual nos patamares: ${this.sobrecargaManualPatamares}\n"+
                "Carga total nos Patamares: ${this.cargaTotalPatamaresELU}\n" +
                "Peso próprio lance: ${this.pesoProprioLance}\n" +
                "Carga permanente manual lance: ${this.cargaPermanenteManualLance}\n"+
                "Sobrecarga manual nos lance: ${this.sobrecargaManualLance}\n"+
                "Carga total no Lance: ${this.cargaTotalLanceELU}\n" +

                "\n\nESFORÇOS\n" +
                "Mk: ${this.mk}\n" +
                "Vk: ${this.vk}\n" +
                "Md: ${this.md}\n" +
                "Vd: ${this.vd}\n" +

                "\n\nFLEXÃO\n" +
                "Altura Minima: ${this.alturaUtilMinimaOk}\n"+
                "Altura LN: ${this.alturaLinhaNeutra}\n" +
                "Kx: ${this.kx}\n" +
                "Dominio: ${this.dominio}\n" +
                "As principal: ${this.areaAcoPositivaPrincipalNecessaria}\n" +
                "As secundário: ${this.areaAcoPoisitivaSecundariaNecessaria}\n" +
                "As negativo: ${this.areaAcoNegativaNecessaria}\n" +

                "\n\nDETALHAMENTO\n" +
                "Armadura Positiva Principal:\n" +
                "Espaçamentos máximos: ${armaduraPositivaPrincipal.espacamentosMaximosEfetivos}\n"+
                "Bitolas selecionáveis: ${armaduraPositivaPrincipal.bitolasSelecionaveis}\n"+
                "Desperdicio: ${armaduraPositivaPrincipal.desperdicio}\n"+
                "Bitola ótima: ${armaduraPositivaPrincipal.bitolaOtima}\n"+
                "Bitola Atual: ${armaduraPositivaPrincipal.bitolaAtual}\n"+
                "Espaçamentos possíveis para a bitola atual: ${armaduraPositivaPrincipal.espacamentosPossiveisPorBitola()}\n"+


                "\nArmadura Positiva Secundária:\n" +
                "Espaçamentos máximos: ${armaduraPositivaSecundaria.espacamentosMaximosEfetivos}\n"+
                "Bitolas selecionáveis: ${armaduraPositivaSecundaria.bitolasSelecionaveis}\n"+
                "Desperdicio: ${armaduraPositivaSecundaria.desperdicio}\n"+
                "Bitola ótima: ${armaduraPositivaSecundaria.bitolaOtima}\n"+
                "Bitola Atual: ${armaduraPositivaSecundaria.bitolaAtual}\n"+
                "Espaçamentos possíveis para a bitola atual: ${armaduraPositivaSecundaria.espacamentosPossiveisPorBitola()}\n"+

                "\nArmadura Negativa:\n" +
                "Espaçamentos máximos: ${armaduraNegativa.espacamentosMaximosEfetivos}\n"+
                "Bitolas selecionáveis: ${armaduraNegativa.bitolasSelecionaveis}\n"+
                "Desperdicio: ${armaduraNegativa.desperdicio}\n"+
                "Bitola ótima: ${armaduraNegativa.bitolaOtima}\n"+
                "Bitola Atual: ${armaduraNegativa.bitolaAtual}\n"+
                "Espaçamentos possíveis para a bitola atual: ${armaduraNegativa.espacamentosPossiveisPorBitola()}\n"+

                "\n\nFLECHA\n" +
                "Cargas permanentes nos patamares: ${this.cargaPermanenteTotalPatamares}\n" +
                "Sobrecarga nos patamares: ${this.sobrecargaTotalPatamares}\n" +
                "Carga total nos Patamares para o ELS: ${this.modeloEstruturalELS.q1*100}\n" +
                "Cargas permanentes no lance: ${this.cargaPermanenteTotalLance}\n" +
                "Sobrecarga  no lance: ${this.sobrecargaTotalLance}\n" +
                "Carga total no Lance para o ELS: ${this.modeloEstruturalELS.q2*100}\n" +
                "Fct: ${this.fct}\n" +
                "Momento ELS: ${this.momentoELS}\n" +
                "Momento Fissuração: ${this.momentoFissuracao}\n" +
                "Momento de Inérica: ${this.momentoDeInerciaTotal}"+
                "Modulo de elasticidade tangente: ${this.Eci}"+
                "Fissurou? ${this.trechoFissurado}\n" +
                "Rigidez total: ${this.rigidezFlexaoTotal}\n" +
                "Altura LN Estadio II: ${this.alturaLinhaNeutraEstadioII}\n" +
                "Momento de Inércia Estádio II: ${this.momentoInerciaEstadioII}\n" +
                "Rigidez Fissurada: ${this.rigidezEquivalente}\n" +
                "Rigidez final: ${this.rigidezFinal}\n" +
                "Flecha imediata: ${this.flechaImediata}\n" +
                "Flecha diferida: ${this.flechaDiferida}\n" +
                "Flecha total: ${this.flechaTotal}\n" +
                "Flecha limite: ${this.flechaLimite}\n" +
                "Flecha passou: ${this.flechaOk}\n" +

                "\n\nCISALHAMENTO\n" +
                "Vrd1: ${this.vrd1}\n"+
                "Vk: ${this.vk}\n"+
                "Vd: ${this.vd}\n"+
                "Cisalhamento ok: ${this.cisalhamentoOK}\n"

        return outPutString
    }
}