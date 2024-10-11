package br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.dimensionamento.relatorio

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Escada.ESCADA
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.R
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.Utilities.format
import br.com.gomes.moreira.guilherme.tcc.escadaslongitudinais.databinding.FragmentRelatorioBinding


class RelatorioFragment : Fragment() {


    lateinit var binding: FragmentRelatorioBinding
    lateinit var viewModel: RelatorioViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_relatorio, container,false)
        viewModel = ViewModelProvider(this).get(RelatorioViewModel::class.java)
        binding.relatorioViewModel = viewModel
        binding.lifecycleOwner = this
        atualizarTabelaDeAco()
        definirVisibilidadeInicialTabelas()
        definirOnClicks()
        return binding.root
    }

    override fun onResume() {
        viewModel.inicializarValores()
        atualizarTabelaDeAco()
        definirVisibilidadeInicialTabelas()
        super.onResume()
    }

    private fun definirOnClicks(){
        binding.apply {
            botaoGeometria.setOnClickListener {
                onClickGeometria()
            }
            botaoCargas.setOnClickListener {
                onClickCargas()
            }
            botaoFlexao.setOnClickListener {
                onClickFlexao()
            }
            botaoFlecha.setOnClickListener {
                onClickFlecha()
            }
        }
    }

    private fun definirVisibilidadeInicialTabelas(){
        binding.apply {
            //Geometria
            esconderGeometria()
            //Cargas
            esconderCargas()
            //Flexao
            esconderFlexao()
            //Flecha
            esconderFlecha()
        }
    }

    //Geometria
    private fun onClickGeometria(){
        when(binding.numeroLances.visibility){
            View.VISIBLE -> {
                configurarTituloBotaoGeometria(View.VISIBLE)
                esconderGeometria()
            }
            View.GONE -> {
                configurarTituloBotaoGeometria(View.GONE)
                mostrarGoemetria()
            }
            View.INVISIBLE -> return
        }
    }

    private fun esconderGeometria(){
        binding.apply {
            numeroLances.visibility = View.GONE
            numeroPatamares.visibility = View.GONE

            divisorPatamarInicial.visibility = View.GONE
            comprimentoPatamarInicial.visibility = View.GONE
            larguraPatamarInicial.visibility = View.GONE

            divisorPatamarIntermediario.visibility = View.GONE
            comprimentoPatamarIntermediario.visibility = View.GONE
            larguraPatamarIntermediario.visibility = View.GONE

            divisorLance.visibility = View.GONE
            comprimentoLance.visibility = View.GONE
            larguraLance.visibility = View.GONE

            divisorEspessura.visibility = View.GONE
            espessura.visibility = View.GONE

            divisorPeDireito.visibility = View.GONE
            peDireito.visibility = View.GONE
            peDireitoLance.visibility = View.GONE

            divisorDegraus.visibility = View.GONE
            numeroDegraus.visibility = View.GONE
            piso.visibility = View.GONE
            espelho.visibility = View.GONE

            divisorApoios.visibility = View.GONE
            apoioEsquerdo.visibility = View.GONE
            apoioDireito.visibility = View.GONE
        }
    }

    private fun mostrarGoemetria(){
        binding.apply {
            numeroLances.visibility = View.VISIBLE
            numeroPatamares.visibility = View.VISIBLE

            if(ESCADA.existePatamarInicial){
                divisorPatamarInicial.visibility = View.GONE
                comprimentoPatamarInicial.visibility = View.GONE
                larguraPatamarInicial.visibility = View.GONE
            }

            if(ESCADA.existePatamarIntermediario) {
                divisorPatamarIntermediario.visibility = View.GONE
                comprimentoPatamarIntermediario.visibility = View.GONE
                larguraPatamarIntermediario.visibility = View.GONE
            }

            divisorLance.visibility = View.VISIBLE
            comprimentoLance.visibility = View.VISIBLE
            larguraLance.visibility = View.VISIBLE

            divisorEspessura.visibility = View.VISIBLE
            espessura.visibility = View.VISIBLE

            divisorPeDireito.visibility = View.VISIBLE
            peDireito.visibility = View.VISIBLE
            peDireitoLance.visibility = View.VISIBLE

            divisorDegraus.visibility = View.VISIBLE
            numeroDegraus.visibility = View.VISIBLE
            piso.visibility = View.VISIBLE
            espelho.visibility = View.VISIBLE

            divisorApoios.visibility = View.VISIBLE
            apoioEsquerdo.visibility = View.VISIBLE
            apoioDireito.visibility = View.VISIBLE
        }
    }

    private fun configurarTituloBotaoGeometria(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoGeometria.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoGeometria.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    //Cargas
    private fun onClickCargas(){
        when(binding.sobrecargaNormativa.visibility){
            View.VISIBLE -> {
                configurarTituloBotaoCargas(View.VISIBLE)
                esconderCargas()
            }
            View.GONE -> {
                configurarTituloBotaoCargas(View.GONE)
                mostrarCargas()
            }
            View.INVISIBLE -> return
        }
    }

    private fun configurarTituloBotaoCargas(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoCargas.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoCargas.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun esconderCargas(){
        esconderSobrecargas()
        esconderCargasPermanentes()
    }

    private fun mostrarCargas(){
        mostrarSobrecargas()
        mostrarCargasPermanentes()
    }

    private fun esconderSobrecargas(){
        binding.apply{
            sobrecargaNormativa.visibility = View.GONE
            sobrecargaManaual.visibility = View.GONE
        }
    }

    private fun mostrarSobrecargas(){
        binding.apply{
            sobrecargaNormativa.visibility = View.VISIBLE
            sobrecargaManaual.visibility = View.VISIBLE
        }
    }

    private fun esconderCargasPermanentes(){
        binding.apply{
            pesoProprio.visibility = View.GONE
            cargaPermanenteManual.visibility = View.GONE
        }
    }

    private fun mostrarCargasPermanentes(){
        binding.apply{
            pesoProprio.visibility = View.VISIBLE
            cargaPermanenteManual.visibility = View.VISIBLE
        }
    }

    //Flexão
    private fun onClickFlexao(){
        when(binding.fyk.visibility){
            View.VISIBLE -> {
                configurarTituloBotaoFlexao(View.VISIBLE)
                esconderFlexao()
            }
            View.GONE -> {
                configurarTituloBotaoFlexao(View.GONE)
                mostrarFlexao()
            }
            View.INVISIBLE -> return
        }
    }

    private fun configurarTituloBotaoFlexao(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoFlexao.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoFlexao.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun esconderFlexao(){
        binding.apply{
            fyk.visibility = View.GONE
            fyd.visibility = View.GONE
            divisorClasseResistencia.visibility = View.GONE
            fck.visibility = View.GONE
            fcd.visibility = View.GONE
            divisorMomentos.visibility = View.GONE
            momentoCaracteristico.visibility = View.GONE
            momentoDeCalculo.visibility = View.GONE
            divisorLinhaNeutra.visibility = View.GONE
            alturaUtil.visibility = View.GONE
            alturaLinhaNeutra.visibility = View.GONE
            kx.visibility = View.GONE
            dominio.visibility = View.GONE

        }
    }

    private fun mostrarFlexao(){
        binding.apply {
            fyk.visibility = View.VISIBLE
            fyd.visibility = View.VISIBLE
            divisorClasseResistencia.visibility = View.VISIBLE
            fck.visibility = View.VISIBLE
            fcd.visibility = View.VISIBLE
            divisorMomentos.visibility = View.VISIBLE
            momentoCaracteristico.visibility = View.VISIBLE
            momentoDeCalculo.visibility = View.VISIBLE
            divisorLinhaNeutra.visibility = View.VISIBLE
            alturaUtil.visibility = View.VISIBLE
            alturaLinhaNeutra.visibility = View.VISIBLE
            kx.visibility = View.VISIBLE
            dominio.visibility = View.VISIBLE
        }
    }

    //Flecha
    private fun onClickFlecha(){
        when(binding.momentoAtuante.visibility){
            View.VISIBLE -> {
                configurarBotaoFlecha(View.VISIBLE)
                esconderFlecha()
            }
            View.GONE -> {
                configurarBotaoFlecha(View.GONE)
                mostrarFlecha()
            }
            View.INVISIBLE -> return
        }
    }

    private fun configurarBotaoFlecha(visibilidade: Int){
        when(visibilidade){
            View.VISIBLE -> binding.botaoFlecha.text = resources.getString(R.string.mostrar_detalhes)
            View.GONE -> binding.botaoFlecha.text = resources.getString(R.string.ocultar_detalhes)
        }
    }

    private fun esconderFlecha(){
        binding.apply{
            momentoAtuante.visibility = View.GONE
            momentoFissuracao.visibility = View.GONE
            pecaFissurada.visibility = View.GONE
            divisorRigidez.visibility = View.GONE
            rigidezTotal.visibility = View.GONE
            alturaLinhaNeutraEstadioII.visibility = View.GONE
            momentoInerciaEstadioII.visibility = View.GONE
            rigidezEquivalente.visibility = View.GONE
            rigidezFinal.visibility = View.GONE
        }
    }

    private fun mostrarFlecha(){
        binding.apply {
            momentoAtuante.visibility = View.VISIBLE
            momentoFissuracao.visibility = View.VISIBLE
            pecaFissurada.visibility = View.VISIBLE
            divisorRigidez.visibility = View.VISIBLE
            rigidezTotal.visibility = View.VISIBLE
            alturaLinhaNeutraEstadioII.visibility = View.VISIBLE
            momentoInerciaEstadioII.visibility = View.VISIBLE
            rigidezEquivalente.visibility = View.VISIBLE
            rigidezFinal.visibility = View.VISIBLE
        }
    }

    //Resumo de Aço

    private fun atualizarTabelaDeAco(){
        binding.tabelaResumoPosicoes.removeAllViews()
        binding.tabelaResumoBitolas.removeAllViews()
        atualizarLinhasDePosicao()
        atualizarLinhasDeBitola()
        atualizarAcoTotal()
    }

    private fun atualizarLinhasDePosicao(){
        val resumo = ESCADA.resumoDeAco
        for (posicao in resumo.posicoes.indices){
            adicionarLinhaDePosicao(resumo.posicoes[posicao],
            resumo.bitolasPorPosicao[posicao].diametroMilimetros,
            resumo.comprimentosUnitariosPorPosicao[posicao],
            resumo.quantidadesPorPosicao[posicao],
            resumo.comprimentosTotaisPorPosicao[posicao])
        }
    }

    private fun atualizarLinhasDeBitola(){
        val resumo = ESCADA.resumoDeAco
        for (bitola in resumo.bitolas.indices){
            adicionarLinhaDeBitola(resumo.bitolas[bitola].diametroMilimetros,
            resumo.comprimentosTotaisPorBitolaMetros[bitola],
            resumo.pesoTotalPorBitola[bitola])
        }
    }

    //Quantitativo de Aço
    //Posicoes
    private fun adicionarLinhaDePosicao(posicao: Int, bitola: Double, comprimentoUnitario: Int, quantidade: Int, comprimentoTotal: Int){
        binding.tabelaResumoPosicoes.addView(criarLinhaDePosicao(View.generateViewId(), posicao, bitola, quantidade, comprimentoUnitario, comprimentoTotal))
    }

    private fun criarLinhaDePosicao(id: Int, posicao: Int, bitola: Double, quantidade: Int, comprimentoUnitario: Int,  comprimentoTotal: Int) : TableRow{
        //Linha (TableRow)
        val linhaDePosicao = TableRow(context)
        linhaDePosicao.apply {
            setId(id)
            weightSum = 100f
            linhaDePosicao.setBackgroundColor(resources.getColor(R.color.da_wae_bright))
        }

        //TextViews
        //Posicao
        val textViewPosicao = TextView(context)
        val textViewPosicaoParams = TableRow.LayoutParams()
        textViewPosicaoParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 10f
        }
        textViewPosicao.apply {
            text = posicao.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewPosicaoParams
        }

        //Bitola
        val textViewBitola = TextView(context)
        val textViewBitolaParams = TableRow.LayoutParams()
        textViewBitolaParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 20f
        }
        textViewBitola.apply {
            text = bitola.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewBitolaParams
        }

        //Quantidade
        val textViewQuantidade = TextView(context)
        val textViewQuantidadeParams = TableRow.LayoutParams()
        textViewQuantidadeParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 20f
        }
        textViewQuantidade.apply {
            text = quantidade.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewQuantidadeParams
        }

        //Comoprimento Unitario
        val textViewComprimentoUnitario = TextView(context)
        val textViewComprimentoUnitarioParams = TableRow.LayoutParams()
        textViewComprimentoUnitarioParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 25f
        }
        textViewComprimentoUnitario.apply {
            text = comprimentoUnitario.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewComprimentoUnitarioParams
        }

        //Comprimento Total
        val textViewComprimentoTotal = TextView(context)
        val textViewComprimentoTotalParams = TableRow.LayoutParams()
        textViewComprimentoTotalParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 25f
        }
        textViewComprimentoTotal.apply {
            text = comprimentoTotal.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewComprimentoTotalParams
        }

        linhaDePosicao.apply {
            addView(textViewPosicao)
            addView(textViewBitola)
            addView(textViewQuantidade)
            addView(textViewComprimentoUnitario)
            addView(textViewComprimentoTotal)
        }
        return linhaDePosicao
    }

    //Bitolas
    private fun adicionarLinhaDeBitola(bitola: Double, comprimentoTotal: Double, pesoTotal: Int){
        binding.tabelaResumoBitolas.addView(criarLinhaDeBitola(View.generateViewId(), bitola, comprimentoTotal, pesoTotal))
    }

    private fun criarLinhaDeBitola(id: Int, bitola: Double,  comprimentoTotal: Double, pesoTotal: Int): TableRow{
        //Linha (TableRow)
        val linhaDeBitola = TableRow(context)
        linhaDeBitola.apply {
            setId(id)
            weightSum = 100f
            linhaDeBitola.setBackgroundColor(resources.getColor(R.color.da_wae_bright))
        }

        val textViewBitola = TextView(context)
        val textViewBitolaParams = TableRow.LayoutParams()
        textViewBitolaParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 20f
        }
        textViewBitola.apply {
            text = bitola.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewBitolaParams
        }

        val textViewComprimenTototal = TextView(context)
        val textViewComprimentoTotalParams = TableRow.LayoutParams()
        textViewComprimentoTotalParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 40f
        }
        textViewComprimenTototal.apply {
            text = comprimentoTotal.format(2)
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewComprimentoTotalParams
        }

        val textViewPesototal = TextView(context)
        val textViewPesototalParams = TableRow.LayoutParams()
        textViewPesototalParams.apply {
            setMargins(resources.getDimensionPixelSize(R.dimen.default_start_end_margin))
            width = 0
            weight = 40f
        }
        textViewPesototal.apply {
            text = pesoTotal.toString()
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
            textAlignment = TextView.TEXT_ALIGNMENT_VIEW_END
            layoutParams = textViewPesototalParams
        }

        linhaDeBitola.apply{
            addView(textViewBitola)
            addView(textViewComprimenTototal)
            addView(textViewPesototal)
        }
        return linhaDeBitola
    }

    //Total
    private fun atualizarAcoTotal(){
        binding.pesoTotalAco.text = ESCADA.resumoDeAco.pesoTotal.toString()
    }
}