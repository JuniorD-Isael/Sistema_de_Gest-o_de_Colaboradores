package br.com.ufvnw.colaborador;

import br.com.ufvnw.contratacao.Contratacao;
import br.com.ufvnw.contratacao.ContratacaoCLT;
import br.com.ufvnw.contratacao.ContratacaoPJ;

import java.util.UUID;

public abstract class Colaborador {

    private String contrato;
    protected Contratacao contratacao;
    private String nome;
    private final String matricula = UUID.randomUUID().toString();
    private final String cpf;
    private String genero;
    private boolean isAtivo = true;

    public Colaborador(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public String visualizar() {
        if (contratacao == null) {
            return "[ Nome: " + this.nome + ", Cpf: " + this.cpf + ", Tipo: Não especificado, Matrícula: " + this.matricula + " ]\nEstá ativo? " + this.isAtivo;
        }

        String tipoContratacao = (contratacao instanceof ContratacaoPJ) ? "PJ" : "CLT";
        return "[ Nome: " + this.nome + ", Cpf: " + this.cpf + ", Tipo: " + tipoContratacao + ", Matrícula: " + this.matricula + " ]\nEstá ativo? " + this.isAtivo
                + "\nDetalhes do Contrato:\n" + contratacao.toString();
    }

    public void desvincularColaborador(char resposta) {
        System.out.println("Tem certeza que deseja desvincular " + this.nome);

        if (resposta == 's') {
            this.isAtivo = false;
            System.out.println("Você está demitido!!!!!!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    public abstract void aumentarSalario();

    public String getMatricula() {
        return matricula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContrato() {
        return contrato;
    }

    public Contratacao getContratacao() {
        return contratacao;
    }

    public void setContrato(String tipoContratacao) {
        if ("pj".equalsIgnoreCase(tipoContratacao)) {
            this.contratacao = new ContratacaoPJ(0.0, 0.0);  // Valores iniciais
        } else if ("clt".equalsIgnoreCase(tipoContratacao)) {
            this.contratacao = new ContratacaoCLT();  // Sem valores iniciais, pois são calculados internamente
        } else {
            throw new IllegalArgumentException("Tipo de contrato não suportado: " + tipoContratacao);
        }
        this.contrato = tipoContratacao;

        if (this.contrato != null) {
            inicializarContratacao();
        }
    }

    private Contratacao obterContratoEspecifico() {
        if ("pj".equalsIgnoreCase(contrato)) {
            return new ContratacaoPJ(0.0, 0.0);  // Valores iniciais
        } else if ("clt".equalsIgnoreCase(contrato)) {
            return new ContratacaoCLT();  // Sem valores iniciais, pois são calculados internamente
        } else {
            throw new IllegalArgumentException("Tipo de contrato não suportado: " + contrato);
        }
    }

    public void inicializarContratacao() {
        if (this.contrato != null) {
            if ("pj".equalsIgnoreCase(contrato)) {
                this.contratacao = new ContratacaoPJ(0.0, 0.0);  // Valores iniciais
            } else if ("clt".equalsIgnoreCase(contrato)) {
                this.contratacao = new ContratacaoCLT();  // Sem valores iniciais, pois são calculados internamente
            } else {
                throw new IllegalArgumentException("Tipo de contrato não suportado: " + contrato);
            }
        } else {
            throw new IllegalStateException("Contrato não foi definido para o colaborador: " + this.nome);
        }

    }
}
