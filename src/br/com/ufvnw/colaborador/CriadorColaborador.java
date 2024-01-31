package br.com.ufvnw.colaborador;

public class CriadorColaborador {

    public Colaborador criarColaborador(int stack, String nome, String cpf) {
        Colaborador colaborador = null;

        switch (stack) {
            case 1:
                colaborador = new Designer(nome, cpf);
                break;
            case 2:
                colaborador = new DevBackEnd(nome, cpf);
                break;
            case 3:
                colaborador = new DevFrontEnd(nome, cpf);
                break;
            case 4:
                colaborador = new TechLeader(nome, cpf);
                break;
        }

        return colaborador;
    }
}

