import kotlin.math.pow
import kotlin.math.sqrt


interface Flexao {



    /**Define a altura útil de uma seção retangular**/
    fun definirAlturaUtil(alturaTotal: Double, dLinha: Double) = alturaTotal - dLinha

    /**Define a armadura mínima de flexão*/
    fun definirTaxaMinima(classeResistencia: Double) : Double{

        return when(classeResistencia){
            Constantes.C20 -> 0.00150
            Constantes.C25 -> 0.00150
            Constantes.C30 -> 0.00150
            Constantes.C35 -> 0.00164
            Constantes.C40 -> 0.00179
            Constantes.C45 -> 0.00194
            Constantes.C50 -> 0.00208
            else -> 0.00208
        }
    }

    /**Calcula a posicao "x" da linha neutra*/
    //Md em Kgf*m | b em cm | Fcd em Kgf/cm² | d em cm
    fun calcularX(Md: Double, Fyd: Double, b: Double, Fcd: Double, d: Double) = (d/0.8)*(1 - sqrt(1 - (200*Md)/(0.85*Fcd*b*d.pow(2) ) ) )

    /**Calcula a razão x/d*/
    fun calcularKx(x: Double, d: Double) = x/d

    /**Calcula o As*/
    //Md em Kgf*m | Fyd em Kgf/cm² | d em cm | x em cm.
    fun calcularAs(Md: Double, Fyd: Double, d: Double, x: Double) = (100*Md) / (Fyd * (d - 0.4 * x)) //cm²

    /**Retorna o Dominio de deformação*/
    fun dominio(kx: Double) : String{

        return when {
            kx == 0.0 -> Constantes.DOMINIO_1
            kx > 0 && kx <= 0.2592592593 -> Constantes.DOMINIO_2
            kx > 0.2592592593 && kx <= Constantes.KX_LIMITE -> Constantes.DOMINIO_3_A
            kx > Constantes.KX_LIMITE && kx <= 0.6283662478 -> Constantes.DOMINIO_3_B
            kx > 0.6283662478 -> Constantes.DOMINIO_4
            else -> "Erro"
        }
    }

    /**Retorna a Altura Útil Mínima para Flexão */
    //Altura para evitar Raiz(Negativo)
    fun definirAlturaUtilMinima(Md: Double, Fyd: Double, b: Double, Fcd: Double) = sqrt(200*Md/(0.50184*Fcd*b))

}