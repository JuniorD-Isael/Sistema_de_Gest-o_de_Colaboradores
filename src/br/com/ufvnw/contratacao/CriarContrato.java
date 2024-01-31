package br.com.ufvnw.contratacao;

public class CriarContrato {

    public static Contratacao criarContrato(String tipoContrato) {
        switch (tipoContrato.toUpperCase()) {
            case "CLT":
                return new ContratacaoCLT();
            case "PJ":
                return new ContratacaoPJ(0.0, 0.0);  // Valores iniciais
            default:
                throw new IllegalArgumentException("Tipo de contrato não suportado: " + tipoContrato);
        }
    }

    public static void main(String[] args) {
        Contratacao contratoCLT = criarContrato("CLT");
        Contratacao contratoPJ = criarContrato("PJ");

        // Exemplo de uso
        contratoCLT.setSalario(5000.0);
        contratoPJ.setSalario(40.0);
        ((ContratacaoPJ) contratoPJ).setHorasTrabalhadas(160);
        ((ContratacaoPJ) contratoPJ).setValorRemuneracaoPorHora(8.50);

        System.out.println("Contrato CLT - Desconto INSS: " + contratoCLT.getDescontoINSS());
        System.out.println("Contrato PJ - Desconto INSS: " + contratoPJ.getDescontoINSS());
        System.out.println("Contrato PJ - Valor Pró-labore: " + contratoPJ.toString());
    }
}
