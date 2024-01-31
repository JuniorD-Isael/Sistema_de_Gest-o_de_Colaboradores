package br.com.ufvnw.colaborador;

import br.com.ufvnw.contratacao.ContratacaoPJ;

public class TechLeader extends Colaborador {

    public TechLeader(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void aumentarSalario() {
        if (getContratacao() instanceof ContratacaoPJ) {
            ContratacaoPJ contratacaoPJ = (ContratacaoPJ) getContratacao();
            double proLaboreAntigo = contratacaoPJ.getProLabore();

            // Aumento específico para TechLeader
            double aumentoPercentualTechLeader = 0.25;
            double novoProLabore = proLaboreAntigo * (1 + aumentoPercentualTechLeader);

            contratacaoPJ.setProLabore(novoProLabore);
        } else {
            // Aumento padrão para CLT e outros tipos de contrato
            double salarioAntigo = getContratacao().getSalario();
            double novoSalario = salarioAntigo * 1.5;

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
