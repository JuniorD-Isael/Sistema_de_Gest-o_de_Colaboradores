package br.com.ufvnw.contratacao;

import java.time.LocalDate;

public class ContratacaoCLT extends Contratacao {

    private double valorDecimoTerceiro = calcularDecimoTerceiro();
    private boolean ferias = true;
    private double descontoFGTS;

    public ContratacaoCLT() {
        setDescontoFGTS();
        setHorasExtras(0.0);
    }

    @Override
    public double calcularINSS() {
        double salarioContribuicao = this.salario;

        // Definindo as faixas de contribuição do INSS
        double faixa1 = 1412.00;
        double faixa2 = 2666.68;
        double faixa3 = 4000.03;

        // Alíquotas de contribuição
        double aliquotaFaixa1 = 0.075;
        double aliquotaFaixa2 = 0.09;
        double aliquotaFaixa3 = 0.12;
        double aliquotaFaixa4 = 0.14;

        // Cálculo do INSS
        double inss = 0.0;

        // Faixa 1
        if (salarioContribuicao <= faixa1) {
            inss = salarioContribuicao * aliquotaFaixa1;
        } else {
            // Faixa 2
            inss += faixa1 * aliquotaFaixa1;

            if (salarioContribuicao <= faixa2) {
                inss += (salarioContribuicao - faixa1) * aliquotaFaixa2;
            } else {
                // Faixa 3
                inss += (faixa2 - faixa1) * aliquotaFaixa2;

                if (salarioContribuicao <= faixa3) {
                    inss += (salarioContribuicao - faixa2) * aliquotaFaixa3;
                } else {
                    // Faixa 4
                    inss += (faixa3 - faixa2) * aliquotaFaixa3;
                    inss += (salarioContribuicao - faixa3) * aliquotaFaixa4;
                }
            }
        }

        return inss;
    }

    @Override
    public double getDescontoINSS() {
        this.descontoINSS = calcularINSS();
        return descontoINSS;
    }

    public double getValorDecimoTerceiro() {
        return valorDecimoTerceiro;
    }

    public int calcularMesesTrabalhados() {
        int anoDaContratacao = this.dataContratacao.getYear();
        int mesDaContratacao = this.dataContratacao.getMonthValue();

        int anoAtual = LocalDate.now().getYear();
        int mesAtual = LocalDate.now().getMonthValue();

        if (anoDaContratacao < anoAtual) {
            // Se a contratação foi em um ano anterior, considera todos os meses até o mês atual
            return mesAtual;
        } else if (anoDaContratacao == anoAtual) {
            // Se a contratação foi no mesmo ano, considera apenas os meses já transcorridos
            return mesAtual - mesDaContratacao + 1;
        } else {
            return 0;
        }
    }

    public double calcularDecimoTerceiro() {
        int mesesTrabalhados = calcularMesesTrabalhados();
        if (mesesTrabalhados > 0) {

            double salarioMensal = this.salario;
            double valorDecimoTerceiro = (salarioMensal / 12) * mesesTrabalhados;

            return valorDecimoTerceiro;
        } else {
            return 0.0;
        }
    }

    public double calcularDescontoFGTS() {
        return this.salario * 0.08;
    }

    public void setDescontoFGTS() {
        this.descontoFGTS = calcularDescontoFGTS();
    }

    public double getDescontoFGTS() {
        return descontoFGTS;
    }
}
