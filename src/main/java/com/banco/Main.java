package main.java.com.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Banco banco = new Banco();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Conta> clientes = new ArrayList<>();

        int escolha;
        while (true) {
            System.out.println(
                    """
                            1 - Acessar conta
                            2 - Criar conta
                            0 - Sair
                            """
            );

            escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:
                    if (banco.getContas() == null) {
                        System.out.println("Nenhum usuário cadastrado");
                        break;
                    }

                    System.out.println("Digite o número da conta:");
                    int numeroConta = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroConta > banco.getContas().size()) {
                        System.out.println("Não encontrado");
                        break;
                    }
                    acessarConta(banco.getContas().get(numeroConta - 1));
                    break;

                case 2:
                    clientes.add(criarConta());
                    banco.setContas(clientes);
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Inválido");
            }
        }
    }

    static Conta criarConta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);

        System.out.println("1 - Conta Corrente\n2 - Conta Poupança");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else {
            conta = new ContaPoupanca(cliente);
        }

        return conta;
    }

    static void acessarConta(Conta conta) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(
                    """
                            1 - Imprimir extrato
                            2 - Depósito
                            3 - Saque
                            4 - Transferência
                            0 - Voltar
                            """
            );

            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1:
                    conta.imprimirExtrato();
                    break;

                case 2:
                    System.out.println("Digite o valor");
                    double valorDeposito = scanner.nextDouble();
                    scanner.nextLine();
                    conta.depositar(valorDeposito);
                    break;

                case 3:
                    System.out.println("Digite o valor");
                    double valorSaque = scanner.nextDouble();
                    scanner.nextLine();
                    conta.sacar(valorSaque);
                    break;

                case 4:
                    System.out.println("Digite o número da conta de destino:");
                    int numeroConta = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroConta <= banco.getContas().size()) {
                        System.out.println("Digite o valor:");
                        double valor = scanner.nextDouble();
                        conta.transferencia(banco.getContas().get(numeroConta - 1), valor);
                    } else {
                        System.out.println("Conta não encontrada");
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Inválido");
                    break;
            }
        }
    }
}