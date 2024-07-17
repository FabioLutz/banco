package main.java.com.banco;

public interface IConta {

    void sacar(double valor);

    void depositar(double valor);

    void transferencia(Conta contaDestino, double valor);

    void imprimirExtrato();
}
