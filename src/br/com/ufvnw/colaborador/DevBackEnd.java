package br.com.ufvnw.colaborador;

import br.com.ufvnw.contratacao.ContratacaoPJ;

public class DevBackEnd extends Colaborador {

    public DevBackEnd(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void aumentarSalario() {
        if (getContratacao() instanceof ContratacaoPJ) {
            ContratacaoPJ contratacaoPJ = (ContratacaoPJ) getContratacao();
            double proLaboreAntigo = contratacaoPJ.getProLabore();

            // Aumento específico para DevBackEnd
            double aumentoPercentualDevBackEnd = 0.20;
            double novoProLabore = proLaboreAntigo * (1 + aumentoPercentualDevBackEnd);

            contratacaoPJ.setProLabore(novoProLabore);
        } else {
            // Aumento padrão para CLT e outros tipos de contrato
            double salarioAntigo = getContratacao().getSalario();
            double novoSalario = salarioAntigo * 1.9;

            getContratacao().setSalario(novoSalario);
        }
    }

    // Adicionando método para definir salário
    public void setSalario(double salario) {
        if (getContratacao() instanceof ContratacaoPJ) {
            ((ContratacaoPJ) getContratacao()).setProLabore(salario);
        } else {
            getContratacao().setSalario(salario);
        }
    }
}
