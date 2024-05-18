import java.util.Scanner;
import java.util.regex.Pattern;

public class ContaBancaria {
    private static final int CONTA_CORRENTE = 1;
    private static final int CONTA_POUPANCA = 2;
    private static Scanner scanner = new Scanner(System.in);
    private String nomeCliente;
    private double saldoCliente;
    private int tipoDeConta;

    public ContaBancaria() {

        System.out.println("Bem-vindo à Conta Bancária! :)");

        do {
            System.out.print("Insira seu nome: ");
            String input = scanner.nextLine();
            if (input.matches("^\\s*[\\p{L}]+(\\s[\\p{L}]+)*\\s*$")) {
                nomeCliente = input;
                break; // Sai do loop quando um nome válido é inserido
            } else {
                System.out.println("Por favor, insira um nome válido.");
            }
        } while (true);

        escolherTipoDeConta();
    }
    private void escolherTipoDeConta() {
        do {
            System.out.printf("""
                            
        Olá %s,
                            
        Escolha o tipo da sua conta:
        %d - Conta Corrente
        %d - Conta Poupança          
        """, nomeCliente, CONTA_CORRENTE, CONTA_POUPANCA);

            // Verifica se o próximo token é um inteiro
            if (scanner.hasNextInt()) {
                tipoDeConta = scanner.nextInt();
                if (tipoDeConta >= 1 && tipoDeConta <= 2){
                    break;
                } else {
                    System.out.println("Por favor, insira um número válido.");
                }
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); // Limpa o buffer do scanner

            }

        } while (tipoDeConta != CONTA_CORRENTE && tipoDeConta != CONTA_POUPANCA);

        System.out.print("Insira o seu saldo inicial: ");

        do {
            if (scanner.hasNextDouble()) {
                saldoCliente = scanner.nextDouble();
                exibirMenuOperacoes();
                break; // Sai do loop quando um número válido é inserido
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); // Limpa o buffer do scanner

            }
        } while (true);

    }
    private void exibirMenuOperacoes() {
        String tipoConta = tipoDeConta == CONTA_CORRENTE ? "Corrente" : "Poupança";
        do {
            System.out.printf("""
                                
                    ************************************
                    Dados iniciais do cliente:
                    
                    Nome:          %s
                    Tipo conta:    %s
                    Saldo inicial: R$ %.2f
                    ************************************
                    
                    Operações:
                    1- Consultar saldos
                    2- Receber valor
                    3- Transferir valor
                    4- Sair
                    
                    Digite a opção desejada:
                    """, nomeCliente, tipoConta, saldoCliente);

            if (scanner.hasNextInt()) {
                int operacoesConta = scanner.nextInt();

                executarOperacao(operacoesConta);
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.next(); // Limpa o buffer do scanner
            }

        } while (true);
    }

    private void executarOperacao(int operacao) {
        switch (operacao) {
            case 1:
                System.out.println("Consultar saldos \n");
                System.out.printf("Seu saldo é R$ %.2f", saldoCliente);
                break;
            case 2:
                double receberSaldo;
                do {
                    System.out.println("Receber valor\n");

                    System.out.print("Insira o valor desejado: ");
                    receberSaldo = scanner.nextDouble();
                    if (receberSaldo > 0) {
                        saldoCliente += receberSaldo;
                    } else {
                        System.out.println("Não é possível receber um valor 0 ou negativado o");
                    }
                } while (receberSaldo <= 0);

                System.out.printf("Seu saldo atualizado é R$ %.2f", saldoCliente);

                break;
            case 3:
                double transferirSaldo;
                do {
                    System.out.println("Transferir valor \n");

                    System.out.print("Insira o valor desejado: ");
                    transferirSaldo = scanner.nextDouble();
                    if (transferirSaldo > 0 && saldoCliente > 0) {
                        saldoCliente -= transferirSaldo;
                    } else {
                        System.out.println("Não é possível fazer a transferência, sem saldo ou valor negativado");
                    }
                } while (transferirSaldo <= 0);

                System.out.printf("Seu saldo atualizado é R$ %.2f", saldoCliente);
                break;
            case 4:
                System.out.println("Sair");
                scanner.close();
                System.exit(0);
            default:
                System.out.printf(" Escolha entre os números (1 a 4).\n O número %d é inválido!!! \n\n", operacao);
                break;
        }
    }

    public static void main(String[] args) {
        new ContaBancaria();
    }
}
