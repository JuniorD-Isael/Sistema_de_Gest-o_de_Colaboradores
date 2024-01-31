package br.com.ufvnw.colaborador;

import br.com.ufvnw.contratacao.ContratacaoPJ;

public class Designer extends Colaborador {

    public Designer(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void aumentarSalario() {
        if (getContratacao() instanceof ContratacaoPJ) {
            ContratacaoPJ contratacaoPJ = (ContratacaoPJ) getContratacao();
            double proLaboreAntigo = contratacaoPJ.getProLabore();

            // Aumento específico para Designer
            double aumentoPercentualDesigner = 0.15;
            double novoProLabore = proLaboreAntigo * (1 + aumentoPercentualDesigner);

            contratacaoPJ.setSalario(novoProLabore);
        } else {
            // Aumento padrão para CLT e outros tipos de contrato
            double salarioAntigo = getContratacao().getSalario();
            double novoSalario = salarioAntigo * 1.4;

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
