package calculadora;

import java.util.Scanner;

public class Metodos {
	
    public static float lerValorEmprestimo(Scanner scanner) {
        float valor;

        while (true) {
            System.out.print("Informe o valor total do empréstimo (R$): ");

            if (scanner.hasNextFloat()) {
                valor = scanner.nextFloat();

                if (valor > 0) {
                    return valor;
                } else {
                    System.out.println("Erro: o valor do empréstimo deve ser maior que zero.");
                }
            } else {
                System.out.println("Erro: entrada inválida. Informe um número válido.");
                scanner.next();
            }
        }
    }

    /**
     * Solicita e valida a taxa de juros mensal (em porcentagem). Não aceita valores negativos.
     * Retorna a taxa já convertida para decimal.
     */
    public static float lerTaxaJuros(Scanner scanner) {
        float taxaPercentual;

        while (true) {
            System.out.print("Informe a taxa de juros mensal (em %): ");

            if (scanner.hasNextFloat()) {
                taxaPercentual = scanner.nextFloat();

                if (taxaPercentual >= 0) {
                    return taxaPercentual / 100f;
                } else {
                    System.out.println("Erro: a taxa de juros não pode ser negativa.");
                }
            } else {
                System.out.println("Erro: entrada inválida. Informe um número válido.");
                scanner.next();
            }
        }
    }

    /**
     * Solicita e valida o prazo em meses. Não aceita valores negativos ou iguais a zero.
     */
    public static int lerPrazoMeses(Scanner scanner) {
        int prazo;

        while (true) {
            System.out.print("Informe o prazo de pagamento (em meses): ");

            if (scanner.hasNextInt()) {
                prazo = scanner.nextInt();

                if (prazo > 0) {
                    return prazo;
                } else {
                    System.out.println("Erro: o prazo deve ser maior que zero.");
                }
            } else {
                System.out.println("Erro: entrada inválida. Informe um número inteiro válido.");
                scanner.next();
            }
        }
    }

    /**
     * Calcula o valor fixo da parcela pela fórmula da Tabela Price (juros compostos):
     * PMT = PV * i / (1 - (1 + i)^-n)
     * Caso a taxa de juros seja zero, a parcela é simplesmente o valor dividido pelo prazo.
     */
    public static double calcularValorParcela(double valorPresente, double taxaMensal, int prazoMeses) {
        if (taxaMensal == 0) {
            return valorPresente / prazoMeses;
        }
        double fator = Math.pow(1 + taxaMensal, -prazoMeses);
        return (valorPresente * taxaMensal) / (1 - fator);
    }

    /**
     * Exibe o demonstrativo mês a mês (juros, amortização e saldo devedor)
     * e, ao final, o resumo com total pago e total de juros.
     */
    public static void exibirDemonstrativo(double valorEmprestimo, double taxaMensal, int prazoMeses, double valorParcela) {
        double saldoDevedor = valorEmprestimo;
        double totalJuros = 0.0;
        double totalPago = 0.0;

        System.out.printf("%-8s %-15s %-18s %-18s%n", "Mês", "Juros (R$)", "Amortização (R$)", "Saldo Devedor (R$)");
        System.out.println("---------------------------------------------------------------");

        for (int mes = 1; mes <= prazoMeses; mes++) {
            double juros = saldoDevedor * taxaMensal;
            double amortizacao = valorParcela - juros;

            if (mes == prazoMeses) {
                amortizacao = saldoDevedor;
                valorParcela = amortizacao + juros;
            }

            saldoDevedor -= amortizacao;
            if (saldoDevedor < 0.005 && saldoDevedor > -0.005) {
                saldoDevedor = 0.0;
            }

            totalJuros += juros;
            totalPago += (juros + amortizacao);

            System.out.printf("%-8d %-15.2f %-18.2f %-18.2f%n", mes, juros, amortizacao, saldoDevedor);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println();
        System.out.println("Resumo Final:");
        System.out.printf("Total pago ao final do empréstimo: R$ %,.2f%n", totalPago);
        System.out.printf("Total de juros acumulados: R$ %,.2f%n", totalJuros);
    }
}
