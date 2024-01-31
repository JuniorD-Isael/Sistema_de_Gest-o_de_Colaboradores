package br.com.ufvnw.colaborador;

import br.com.ufvnw.contratacao.ContratacaoPJ;

public class DevFrontEnd extends Colaborador {

    public DevFrontEnd(String nome, String cpf) {
        super(nome, cpf);
    }

    @Override
    public void aumentarSalario() {
        if (getContratacao() instanceof ContratacaoPJ) {
            ContratacaoPJ contratacaoPJ = (ContratacaoPJ) getContratacao();
            double proLaboreAntigo = contratacaoPJ.getProLabore();

            // Aumento específico para DevFrontEnd
            double aumentoPercentualDevFrontEnd = 0.15;
            double novoProLabore = proLaboreAntigo * (1 + aumentoPercentualDevFrontEnd);

            contratacaoPJ.setProLabore(novoProLabore);
        } else {
            // Aumento padrão para CLT e outros tipos de contrato
            double salarioAntigo = getContratacao().getSalario();
            double novoSalario = salarioAntigo * 1.4;

            getContratacao().setSalario(novoSalario);
        }
    }
}
