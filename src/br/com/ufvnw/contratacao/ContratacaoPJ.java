//package br.com.ufvnw.contratacao;
//
//public class ContratacaoPJ extends Contratacao {
//
//    private double valorRemuneracaoPorHora;
//    private double horasTrabalhadas;
//    private double proLabore;
//
//    public ContratacaoPJ(double valorRemuneracaoPorHora, double horasTrabalhadas) {
//        this.valorRemuneracaoPorHora = valorRemuneracaoPorHora;
//        setHorasTrabalhadas(horasTrabalhadas);
//        setProLabore();
//    }
//
//    @Override
//    public double calcularINSS() {
//        // Verifica se o colaborador PJ tem pró-labore definido
//        if (getProLabore() > 0) {
//            double salarioContribuicao = getProLabore();
//
//            // Teto de contribuição do INSS
//            double tetoINSS = 7786.02;
//
//            // Alíquota de contribuição para PJ
//            double aliquotaPJ = 0.11;
//
//            // Cálculo do INSS
//            double inss = salarioContribuicao * aliquotaPJ;
//
//            // Limita o INSS ao teto, se ultrapassar
//            return Math.min(inss, tetoINSS);
//        } else {
//            return 0.0; // Caso não tenha pró-labore definido, o INSS é zero
//        }
//    }
//
//    @Override
//    public double getDescontoINSS() {
//        return 0;
//    }
//
//    public void setValorRemuneracaoPorHora(double valorRemuneracaoPorHora) {
//        this.valorRemuneracaoPorHora = valorRemuneracaoPorHora;
//    }
//
//    public double getProLabore() {
//        return proLabore;
//    }
//
//    public void setProLabore() {
//        this.proLabore = this.horasTrabalhadas * this.valorRemuneracaoPorHora;
//    }
//
//    public void setHorasTrabalhadas(double horasTrabalhadas) {
//        this.horasTrabalhadas = horasTrabalhadas;
//    }
//}
package br.com.ufvnw.contratacao;

import java.text.NumberFormat;

public class ContratacaoPJ extends Contratacao {

    private double valorRemuneracaoPorHora;
    private double horasTrabalhadas;
    private double proLabore;

    public ContratacaoPJ(double valorRemuneracaoPorHora, double horasTrabalhadas) {
        this.valorRemuneracaoPorHora = valorRemuneracaoPorHora;
        this.horasTrabalhadas = horasTrabalhadas;
        calcularProLabore();
    }

    @Override
    public double calcularINSS() {
        double aliquotaPJ = 0.11;
        double inss = this.proLabore * aliquotaPJ;
        double tetoINSS = 7786.02;
        return Math.min(inss, tetoINSS);
    }

    @Override
    public double getDescontoINSS() {
        return calcularINSS();
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        return super.toString() +
               "\nSalário: " + currencyFormat.format(this.proLabore) +
               "\nDesconto INSS: " + currencyFormat.format(calcularINSS());
    }

    public void setValorRemuneracaoPorHora(double valorRemuneracaoPorHora) {
        this.valorRemuneracaoPorHora = valorRemuneracaoPorHora;
        calcularProLabore();
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
        calcularProLabore();
    }

    private void calcularProLabore() {
        this.proLabore = this.horasTrabalhadas * this.valorRemuneracaoPorHora;
    }

    public double getProLabore() {
        return proLabore;
    }

    public void setProLabore(double proLabore) {
        this.proLabore = proLabore;
    }

}
