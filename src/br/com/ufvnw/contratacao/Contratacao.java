package br.com.ufvnw.contratacao;

import java.text.NumberFormat;
import java.time.LocalDate;

public abstract class Contratacao {

    protected String senioridade;
    protected double descontoINSS;
    protected double horasExtras;
    protected final LocalDate dataContratacao = LocalDate.now();
    protected double salario;

    // Construtor, getters e setters para os atributos
    // ...
    public Contratacao() {
    }

    public void setSenioridade(String senioridade) {
        this.senioridade = senioridade;
    }

    public String getSenioridade() {
        return senioridade;
    }

    public abstract double calcularINSS();

    public abstract double getDescontoINSS();

    public void setHorasExtras(double horasExtras) {
        this.horasExtras = horasExtras;
    }

    public double getHorasExtras() {
        return horasExtras;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        if (this instanceof ContratacaoCLT) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

            return "Salário: " + currencyFormat.format(salario) + "\n" +
                    "Desconto INSS: " + currencyFormat.format(descontoINSS);
        } else if (this instanceof ContratacaoPJ) {
            return "";
        } else {
            return super.toString();
        }
    }
}
