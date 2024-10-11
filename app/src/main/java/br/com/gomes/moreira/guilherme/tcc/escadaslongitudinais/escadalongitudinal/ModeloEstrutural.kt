
import GeometriaAnalitica.Quadratica
import GeometriaAnalitica.Quartica
import GeometriaAnalitica.Reta
import kotlin.math.*

class ModeloEstrutural(a: Double = 0.toDouble(),
                       b: Double = 0.toDouble(),
                       c: Double = 0.toDouble(),
                       d: Double = 0.toDouble(),
                       q1: Double = 0.toDouble(),
                       q2: Double = 0.toDouble(),
                       q3: Double = 0.toDouble()) {

    var r1: Double
    var r2: Double
    var a: Double
    var b: Double
    var c: Double
    var d: Double
    val alpha get() =  atan(d / b)

    var q1: Double
    var q2: Double
    var q3: Double

    var momentoMaximo: Double = 0.0

    lateinit var momentoFletor1: Quadratica
    lateinit var momentoFletor2: Quadratica
    lateinit var momentoFletor3: Quadratica

    lateinit var deslocamentos1: Quartica
    lateinit var deslocamentos2: Quartica
    lateinit var deslocamentos3: Quartica

    init {

        this.a = a
        this.b = b
        this.c = c
        this.d = d

        this.q1 = q1
        this.q2 = q2
        this.q3 = q3

        this.r1 = calcularRv1(this.a, this.b, this.c, this.d, this.q1, this.q2, this.q3)
        this.r2 = calcularRv2(this.r1, this.a, this.b, this.c, this.d, this.q1, this.q2, this.q3)

        this.definirMomentos()
    }

    fun redefinir(){
        this.r1 = calcularRv1(this.a, this.b, this.c, this.d, this.q1, this.q2, this.q3)
        this.r2 = calcularRv2(this.r1, this.a, this.b, this.c, this.d, this.q1, this.q2, this.q3)
        this.definirMomentos()
    }

    fun definirMomentos() {

        this.momentoFletor1 = definirM1Local(this)
        this.momentoFletor2 = definirM2Local(this, this.momentoFletor1.Y(this.a))
        this.momentoFletor3 = definirM3Local(this)

    }

    fun definirDeslocamentos(rigidezFlexao: Double, rigidezAxial: Double) {

        this.deslocamentos1 = definirY1(this, this.momentoFletor1, rigidezFlexao, rigidezAxial)
        this.deslocamentos2 = definirY2(this, this.momentoFletor2, rigidezFlexao, rigidezAxial)
        this.deslocamentos3 = definirY3(this, this.momentoFletor3, rigidezFlexao, rigidezAxial)

    }

    fun redefinirCargas(q1: Double = this.q1, q2: Double = this.q2, q3: Double = this.q3) {

        this.q1 = q1
        this.q2 = q2
        this.q3 = q3

    }

    fun calcularMomentoMax(): Double {

        return max(this.momentoFletor1.Ymax, max(this.momentoFletor2.Ymax, this.momentoFletor3.Ymax))

    }

    fun calcularDeslocamentoMax(): Double {

        return min(this.deslocamentos1.yMax, min(this.deslocamentos2.yMaxCos(alpha), this.deslocamentos3.yMax))

    }

    fun calcularVmax(): Double {

        return max(this.r1, this.r2)

    }

    fun calcularRv1(a: Double, b: Double, c: Double, d: Double,q1: Double, q2: Double, q3: Double): Double {

        val cosa: Double = cos(atan(d / b))

        return (q1 * a * (0.5 * a + b + c) + q2 * (b / cosa) * (0.5 * b + c) + 0.5 * q3 * c * c) / (a + b + c)

    }

    fun calcularRv2(rv1: Double, a: Double, b: Double, c: Double, d: Double, q1: Double, q2: Double, q3: Double): Double {

        val cosa: Double = cos(atan(d / b))

        return q1 * a + q2 * b / cosa + q3 * c - rv1

    }

    fun calcularRvh(rv1: Double, a: Double, b: Double, c: Double, d: Double, q1: Double, q2: Double, q3: Double): Double {

        val cosa: Double = cos(atan(d / b))

        return (rv1 * (a + b + c) - q1 * a * (0.5 * a + b + c) - q2 * (b / cosa) * (0.5 * b + c) - 0.5 * q3 * c * c) / d

    }

    fun calcularM1(x: Double, rv1: Double, q1: Double): Double = rv1 * x - 0.5 * q1 * x.pow(2)

    fun calcularM2(x: Double, rv1: Double, q1: Double, q2: Double, a: Double) =
        rv1 * x - q1 * a * (x - 0.5 * a) - 0.5 * q2 * ((x - a).pow(2))

    fun calcularM3(x: Double, rv1: Double, q1: Double, q2: Double, q3: Double, a: Double, b: Double): Double =
        rv1 * x - q1 * a * (x - 0.5 * a) - q2 * b * (x - a - 0.5 * b) - 0.5 * q3 * ((x - a - b).pow(2))

    fun definirM1(rv1: Double, q1: Double, a: Double): Quadratica {

        return Quadratica(-0.5 * q1, rv1, 0.0, dominio = arrayOf(0.0, a))

    }

    fun definirM2(rv1: Double, q1: Double, q2: Double, a: Double, b: Double): Quadratica {

        return Quadratica(
            -0.5 * q2,
            rv1 - q1 * a + q2 * a,
            0.5 * (q1 * a * a - q2 * a * a),
            dominio = arrayOf(a, a + b)
        )

    }

    fun definirM3(rv1: Double, a: Double, b: Double, c: Double, q1: Double, q2: Double, q3: Double): Quadratica {

        return Quadratica(
            -0.5 * q3,
            rv1 - q1 * a - q2 * b + q3 * (a + b),
            0.5 * (q1 * a.pow(2) + q2 * b.pow(2) - q3 * (a + b).pow(2)),
            dominio = arrayOf(b, a + b + c)
        )

    }

    fun definirM1Local(modeloEstrutural: ModeloEstrutural): Quadratica {

        return Quadratica(
            -0.5 * modeloEstrutural.q1,
            modeloEstrutural.r1,
            0.0,
            dominio = arrayOf(0.0, modeloEstrutural.a)
        )

    }

    fun definirM2Local(modeloEstrutural: ModeloEstrutural, m1a: Double): Quadratica {

        return Quadratica(
            -0.5 * modeloEstrutural.q2 * cos(modeloEstrutural.alpha),
            (modeloEstrutural.r1 - modeloEstrutural.q1 * modeloEstrutural.a) * (cos(modeloEstrutural.alpha)),
            m1a,
            dominio = arrayOf(0.0, modeloEstrutural.b / (cos(modeloEstrutural.alpha)))
        )

    }

    fun definirM3Local(modeloEstrutural: ModeloEstrutural): Quadratica {

        return Quadratica(
            -0.5 * modeloEstrutural.q3,
            modeloEstrutural.r2,
            0.0,
            dominio = arrayOf(0.0, modeloEstrutural.c)
        )

    }

    fun deslocamentoA(modeloEstrutural: ModeloEstrutural, rigidezFlexao: Double, rigidezAxial: Double): Double {

        //Geometria
        val a = modeloEstrutural.a
        val b = modeloEstrutural.b
        val c = modeloEstrutural.c
        val d = modeloEstrutural.d
        val alpha = atan(d / b)

        //Modelo Estrutural Unitario
        val r1Unitario = (b + c) / (a + b + c)
        val r2Unitario = 1 - r1Unitario


        val modeloEstruturalTotal = modeloEstrutural


        //Funções de Momentos P/ Carga unitária aplicada em a
        val momentoUnit1 = Reta(r1Unitario, 0.0)
        val momentoUnit2 = Reta((r1Unitario - 1) * cos(alpha), momentoUnit1.Y(a))
        val momentoUnit3 = Reta(r2Unitario, 0.0)

        //Esforço normal no Lance P/ Carga unitária em a
        val normalUni2: Double = -(r1Unitario - 1) * sin(alpha)

        //Funcões de Momento P/ Carregamento Efetivo
        val momento1 = definirM1Local(modeloEstruturalTotal)
        val momento2 = definirM2Local(modeloEstruturalTotal, momento1.Y(modeloEstruturalTotal.a))
        val momento3 = definirM3Local(modeloEstruturalTotal)

        //Esforço normal no Lance P/ Carregamento Efetivo
        val normal2 = Reta(this.q2 * sin(alpha), -(this.r1 - this.q1*this.a) * sin(alpha))

        val M1 = momentoUnit1.M
        val L1 = momentoUnit1.L

        val M2 = momentoUnit2.M
        val L2 = momentoUnit2.L

        val M3 = momentoUnit3.M
        val L3 = momentoUnit3.L

        val A1 = momento1.A
        val B1 = momento1.B
        val C1 = momento1.C

        val A2 = momento2.A
        val B2 = momento2.B
        val C2 = momento2.C

        val A3 = momento3.A
        val B3 = momento3.B
        val C3 = momento3.C

        //Parcelas da Integral de Deslocamentos (P.T.V)
        val bcos: Double = b / cos(alpha)

        val primeiraParcela = ((A1 * M1) / 4.0) * (a.pow(4)) + ((B1 * M1 + A1 * L1) / 3.0) * (a.pow(3)) + ((C1 * M1 + B1 * L1) / 2.0) * (a.pow(2)) + C1 * L1 * a

        val segundaParcela =  ((A2 * M2) / 4.0) * (bcos.pow(4)) + ((B2 * M2 + A2 * L2) / 3.0) * (bcos.pow(3)) + ((C2 * M2 + B2 * L2) / 2.0) * (bcos.pow(2)) + C2 * L2 * bcos

        val terceiraParcela = ((A3 * M3) / 4.0) * (c.pow(4)) + ((B3 * M3 + A3 * L3) / 3.0) * (c.pow(3)) + ((C3 * M3 + B3 * L3) / 2.0) * (c.pow(2)) + C3 * L3 * c

        val quartaParcela = (normalUni2 / rigidezAxial) * (0.5 * normal2.M * (bcos.pow(2)) + normal2.L * bcos)

        val deslocamento = (primeiraParcela + segundaParcela + terceiraParcela) / (rigidezFlexao) + quartaParcela

        return deslocamento
    }

    fun deslocamentoC(modeloEstrutural: ModeloEstrutural, rigidezFlexao: Double, rigidezAxial: Double): Double {

        //Geometria
        val a = modeloEstrutural.a
        val b = modeloEstrutural.b
        val c = modeloEstrutural.c
        val d = modeloEstrutural.d
        val alpha = atan(d / b)

        //Modelo Estrutural Unitario
        val r1Unitario = (c) / (a + b + c)
        val r2Unitario = 1 - r1Unitario

        val modeloEstruturalTotal = modeloEstrutural


        //Funções de Momentos P/ Carga unitária aplicada em b
        val momentoUnit1 = Reta(r1Unitario, 0.0)
        val momentoUnit2 = Reta((r1Unitario) * cos(alpha), momentoUnit1.Y(a))
        val momentoUnit3 = Reta(r2Unitario, 0.0)

        //Esforço normal no Lance P/ Carga unitária em b
        val normalUni2: Double = -r1Unitario * sin(alpha)

        //Funcões de Momento P/ Carregamento Efetivo
        val momento1 = definirM1Local(modeloEstruturalTotal)
        val momento2 = definirM2Local(modeloEstruturalTotal, momento1.Y(modeloEstruturalTotal.a))
        val momento3 = definirM3Local(modeloEstruturalTotal)

        //Esforço normal no Lance P/ Carregamento Efetivo
        val normal2 = Reta(this.q2 * sin(alpha), -(this.r1 - this.q1*this.a) * sin(alpha))

        val M1 = momentoUnit1.M
        val L1 = momentoUnit1.L

        val M2 = momentoUnit2.M
        val L2 = momentoUnit2.L

        val M3 = momentoUnit3.M
        val L3 = momentoUnit3.L

        val A1 = momento1.A
        val B1 = momento1.B
        val C1 = momento1.C

        val A2 = momento2.A
        val B2 = momento2.B
        val C2 = momento2.C

        val A3 = momento3.A
        val B3 = momento3.B
        val C3 = momento3.C

        //Parcelas da Integral de Deslocamentos (P.T.V)
        val bcos: Double = b / cos(modeloEstrutural.alpha)

        val primeiraParcela = ((A1 * M1) / 4) * (a.pow(4)) + ((B1 * M1 + A1 * L1) / 3) * (a.pow(3)) + ((C1 * M1 + B1 * L1) / 2) * (a.pow(2)) + C1 * L1 * a

        val segundaParcela =  ((A2 * M2) / 4) * (bcos.pow(4)) + ((B2 * M2 + A2 * L2) / 3) * (bcos.pow(3)) + ((C2 * M2 + B2 * L2) / 2) * (bcos.pow(2)) + C2 * L2 * bcos

        val terceiraParcela = ((A3 * M3) / 4) * (c.pow(4)) + ((B3 * M3 + A3 * L3) / 3) * (c.pow(3)) + ((C3 * M3 + B3 * L3) / 2) * (c.pow(2)) + C3 * L3 * c

        val quartaParcela = (normalUni2 / rigidezAxial) * (0.5 * normal2.M * (bcos.pow(2)) + normal2.L * bcos)

        val deslocamento = (primeiraParcela + segundaParcela + terceiraParcela) / (rigidezFlexao) + quartaParcela

        //println("Deslocamento em C: $deslocamento")
        return deslocamento
    }

    fun definirY1(modeloEstrutural: ModeloEstrutural, m1: Quadratica, rigidezFlexao: Double, rigidezAxial: Double): Quartica {

        val Aquadrica = m1.A / (12 * rigidezFlexao)
        val Bquadrica = m1.B / (6 * rigidezFlexao)
        val Cquadrica = m1.C / (2 * rigidezFlexao)

        val y0: Double = 0.0
        val ya: Double = -deslocamentoA(modeloEstrutural, rigidezFlexao, rigidezAxial)

        val a = modeloEstrutural.a
        val C2 = y0*rigidezFlexao
        val Equadrica = C2 / (rigidezFlexao)
        val C1 = when(a > 0){

                true  -> (ya/a - Aquadrica*(a.pow(3)) - Bquadrica*(a.pow(2)) - Cquadrica*a - Equadrica/a)*rigidezFlexao
                false -> 0.0

        }
        val Dquadrica = C1 / (rigidezFlexao)


        return Quartica(Aquadrica, Bquadrica, Cquadrica, Dquadrica, Equadrica, m1.dominio)

    }

    fun definirY2(modeloEstrutural: ModeloEstrutural, m2: Quadratica, rigidezFlexao: Double, rigidezAxial: Double): Quartica {

        val senAlpha = sin(modeloEstrutural.alpha)
        val cosAlpha = cos(modeloEstrutural.alpha)

        val Aquadrica = m2.A / (12 * rigidezFlexao)
        val Bquadrica = m2.B / (6 * rigidezFlexao)
        val Cquadrica = m2.C / (2 * rigidezFlexao)

        val y0local: Double = -(deslocamentoA(modeloEstrutural, rigidezFlexao, rigidezAxial)) / cosAlpha
        val ybcoslocal: Double = -(deslocamentoC(modeloEstrutural, rigidezFlexao, rigidezAxial)) / cosAlpha
        val bcos = modeloEstrutural.b / cosAlpha

        val C2 = (rigidezFlexao * y0local)
        val C1 = (rigidezFlexao * ybcoslocal) / (bcos) - (1 / 12.0) * (m2.A * (bcos.pow(3))) - (1 / 6.0) * (m2.B * (bcos.pow(2))) - 0.5 * (m2.C * bcos) - C2 / bcos
        val Dquadrica = C1 / (rigidezFlexao)
        val Equadrica = C2 / (rigidezFlexao)


        return Quartica(Aquadrica, Bquadrica, Cquadrica, Dquadrica, Equadrica, m2.dominio)

    }

    fun definirY3(modeloEstrutural: ModeloEstrutural, m3: Quadratica, rigidezFlexao: Double, rigidezAxial: Double): Quartica {

        val Aquadrica = m3.A / (12 * rigidezFlexao)
        val Bquadrica = m3.B / (6 * rigidezFlexao)
        val Cquadrica = m3.C / (2 * rigidezFlexao)
        val Equadrica = 0.0

        val y0: Double = 0.0
        val ya: Double = -deslocamentoC(modeloEstrutural, rigidezFlexao, rigidezAxial)

        val c = modeloEstrutural.c
        val C2 = y0
        val C1 = when(c>0){

                true -> (rigidezFlexao * ya) / c - (1 / 12.0) * (m3.A * (c.pow(3))) - (1 / 6.0) * (m3.B * (c.pow(2))) - 0.5 * (m3.C * c) - C2 / c
                false -> 0.0

        }
        val Dquadrica = C1 / (rigidezFlexao)


        return Quartica(Aquadrica, Bquadrica, Cquadrica, Dquadrica, Equadrica, m3.dominio)
    }

    override fun toString(): String {

        val dadosGerais = ("\n>>MODELO ESTRUTURAL<<"+
                "\n>> GEOMETRIA <<\n"+
                "\na: ${this.a}"+
                "\nb: ${this.b}"+
                "\nc: ${this.c}"+
                "\nd: ${this.d}"+
                "\n>> CARGAS <<\n"+
                "\nq1: ${this.q1}"+
                "\nq2: ${this.q2}"+
                "\nq3: ${this.q3}"+
                "\n>> RREAÇÕES DE APOIO <<\n"+
                "\nR1: ${this.r1}"+
                "\nR2: ${this.r2}")

        val momentos = if(this::momentoFletor1.isInitialized && this::momentoFletor2.isInitialized && this::momentoFletor3.isInitialized){
            "\n>> MOMENTOS <<\n"+
                    "\nM1(x): ${this.momentoFletor1}"+
                    "\nM1(0): ${this.momentoFletor1.Y(0.0)}"+
                    "\nM1(${0.25*a}): ${this.momentoFletor1.Y(0.25*a)}"+
                    "\nM1(0.50a): ${this.momentoFletor1.Y(0.5*a)}"+
                    "\nM1(0.75a): ${this.momentoFletor1.Y(0.75*a)}"+
                    "\nM1(a): ${this.momentoFletor1.Y(a)}"+
                    "\nM1max: ${this.momentoFletor1.Ymax}"+
                    "\nM2(x): ${this.momentoFletor2}"+
                    "\nM2(0): ${this.momentoFletor2.Y(0.0)}"+
                    "\nM2(0.25b): ${this.momentoFletor2.Y(0.25*b / cos(alpha))}"+
                    "\nM2(0.50b): ${this.momentoFletor2.Y(0.5*b / cos(alpha))}"+
                    "\nM2(0.75b): ${this.momentoFletor2.Y(0.75*b / cos(alpha))}"+
                    "\nM2(bocs): ${this.momentoFletor2.Y(b / cos(alpha))}"+
                    "\nM2max: ${this.momentoFletor2.Ymax}"+
                    "\nM3(x): ${this.momentoFletor3}"+
                    "\nM3(0): ${this.momentoFletor3.Y(0.0)}"+
                    "\nM1(0.25c): ${this.momentoFletor3.Y(0.25*c)}"+
                    "\nM1(0.50c): ${this.momentoFletor3.Y(0.5*c)}"+
                    "\nM1(0.75c): ${this.momentoFletor3.Y(0.75*c)}"+
                    "\nM3(c): ${this.momentoFletor3.Y(c)}"+
                    "\nM3max: ${this.momentoFletor3.Ymax}"} else ""

        val deslocamentos = if (this::deslocamentos1.isInitialized && this::deslocamentos3.isInitialized && this::deslocamentos3.isInitialized){
            "\n>> DESLOCAMENTOS <<\n"+
                    "\nY1(x): ${this.deslocamentos1}"+
                    "\nY1(0): ${this.deslocamentos1.Y(0.0)}"+
                    "\nY1(0.25a):${this.deslocamentos1.Y(0.25 * a)}"+
                    "\nY1(0.50a):${this.deslocamentos1.Y(0.5 * a)}"+
                    "\nY1(0.75a):${this.deslocamentos1.Y(0.75 * a)}"+
                    "\nY1(a): ${this.deslocamentos1.Y(a)}"+
                    "\nY1max: ${this.deslocamentos1.yMax}"+
                    "\nY2(x): ${this.deslocamentos2}"+
                    "\nY2(0): ${this.deslocamentos2.Ycos(0.0, alpha)}"+
                    "\nY2(0.25bcos):${this.deslocamentos2.Ycos(0.25 * b / cos(alpha), alpha)}"+
                    "\nY2(0.50bcos):${this.deslocamentos2.Ycos(0.5 * b / cos(alpha), alpha)}"+
                    "\nY2(0.75bcos):${this.deslocamentos2.Ycos(0.75 * b / cos(alpha), alpha)}"+
                    "\nY2(bcos): ${this.deslocamentos2.Ycos(b / cos(alpha), alpha)}"+
                    "\nY2max: ${this.deslocamentos2.yMaxCos(alpha)}"+
                    "\nY3(x): ${this.deslocamentos3}"+
                    "\nY3(0): ${this.deslocamentos3.Y(0.0)}"+
                    "\nY3(0.25c):${this.deslocamentos3.Y(0.25*c)}"+
                    "\nY3(0.50c):${this.deslocamentos3.Y(0.5*c)}"+
                    "\nY3(0.75c):${this.deslocamentos3.Y(0.75*c)}"+
                    "\nY3(c): ${this.deslocamentos3.Y(c)}"+
                    "\nY3max: ${this.deslocamentos3.yMax}" } else ""

        val returnString: String = dadosGerais + momentos + deslocamentos

        return returnString
    }
}



