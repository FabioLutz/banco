package main.java.com.banco;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== EXTRATO DA CONTA CORRENTE ===");
        imprimirInformacoes();
    }
}