package br.com.ufvnw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.ufvnw.colaborador.Colaborador;
import br.com.ufvnw.colaborador.CriadorColaborador;
import br.com.ufvnw.colaborador.Designer;
import br.com.ufvnw.colaborador.DevFrontEnd;
import br.com.ufvnw.contratacao.ContratacaoCLT;
import br.com.ufvnw.contratacao.ContratacaoPJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static List<Colaborador> colaboradores = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção (0 para sair): ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    criarColaborador(scanner);
                    break;
                case 2:
                    visualizarColaboradores();
                    break;
                case 3:
                    definirSalario(scanner);
                    break;
                case 4:
                    promoverColaborador(scanner);
                    break;
                case 5:
                    aumentarSalario(scanner);
                    break;
                case 0:
                    System.out.println("Encerrando o programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n### Menu ###");
        System.out.println("1. Criar Colaborador");
        System.out.println("2. Visualizar Colaboradores");
        System.out.println("3. Definir Salário Inicial");
        System.out.println("4. Promover Colaborador");
        System.out.println("5. Aumentar Salário");
        System.out.println("0. Sair");
    }

    private static void criarColaborador(Scanner scanner) {
        System.out.println("\n### Criar Colaborador ###");
        System.out.print("Informe o nome do colaborador: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o CPF do colaborador: ");
        String cpf = scanner.nextLine();

        Colaborador colaborador = new CriadorColaborador().criarColaborador(1, nome, cpf); // Stack 1 para Designer
        System.out.println("Colaborador criado com sucesso!\n");

        colaboradores.add(colaborador);
    }

    private static void visualizarColaboradores() {
        System.out.println("\n### Visualizar Colaboradores ###");
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
        } else {
            for (Colaborador colaborador : colaboradores) {
                System.out.println(colaborador.visualizar());
                System.out.println("===========================");
            }
        }
    }

    private static void definirSalario(Scanner scanner) {
        System.out.println("\n### Definir Salário Inicial ###");
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado para definir salário inicial.");
        } else {
            System.out.print("Informe o nome do colaborador para definir o salário: ");
            String nome = scanner.nextLine();

            Colaborador colaborador = buscarColaboradorPorNome(nome);

            if (colaborador != null) {
                System.out.print("Informe o salário inicial: ");
                double salario = scanner.nextDouble();
                colaborador.getContratacao().setSalario(salario);
                System.out.println("Salário definido com sucesso!\n");
            } else {
                System.out.println("Colaborador não encontrado. Tente novamente.");
            }
        }
    }

    private static void promoverColaborador(Scanner scanner) {
        System.out.println("\n### Promover Colaborador ###");
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado para promoção.");
        } else {
            System.out.print("Informe o nome do colaborador para promoção: ");
            String nome = scanner.nextLine();

            Colaborador colaborador = buscarColaboradorPorNome(nome);

            if (colaborador != null) {
                System.out.print("Informe a nova senioridade (Junior, Pleno, Senior): ");
                String senioridade = scanner.nextLine();
                ((ContratacaoCLT) colaborador.getContratacao()).setSenioridade(senioridade);
                System.out.println("Colaborador promovido com sucesso!\n");
            } else {
                System.out.println("Colaborador não encontrado. Tente novamente.");
            }
        }
    }

    private static void aumentarSalario(Scanner scanner) {
        System.out.println("\n### Aumentar Salário ###");
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado para aumento de salário.");
        } else {
            System.out.print("Informe o nome do colaborador para aumento de salário: ");
            String nome = scanner.nextLine();

            Colaborador colaborador = buscarColaboradorPorNome(nome);

            if (colaborador != null) {
                colaborador.aumentarSalario();
                System.out.println("Salário aumentado com sucesso!\n");
            } else {
                System.out.println("Colaborador não encontrado. Tente novamente.");
            }
        }
    }

    private static Colaborador buscarColaboradorPorNome(String nome) {
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getNome().equalsIgnoreCase(nome)) {
                return colaborador;
            }
        }
        return null;
    }
}
