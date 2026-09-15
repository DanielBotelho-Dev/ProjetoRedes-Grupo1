package calculadora;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR"));
        Scanner scanner = new Scanner(System.in);

        float valorEmprestimo = Metodos.lerValorEmprestimo(scanner);
        float taxaJurosMensal = Metodos.lerTaxaJuros(scanner); // em decimal (ex: 0.02 para 2%)
        int prazoMeses = Metodos.lerPrazoMeses(scanner);

        double valorParcela = Metodos.calcularValorParcela(valorEmprestimo, taxaJurosMensal, prazoMeses);

        System.out.println();
        System.out.println("=========================================================");
        System.out.printf("Valor do empréstimo: R$ %,.2f%n", valorEmprestimo);
        System.out.printf("Taxa de juros mensal: %.2f%%%n", taxaJurosMensal * 100);
        System.out.printf("Prazo: %d meses%n", prazoMeses);
        System.out.printf("Valor da parcela fixa: R$ %,.2f%n", valorParcela);
        System.out.println("=========================================================");
        System.out.println();

        Metodos.exibirDemonstrativo(valorEmprestimo, taxaJurosMensal, prazoMeses, valorParcela);

        scanner.close();
    }
}
