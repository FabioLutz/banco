package main.java.com.banco;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Valor inválido");
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void transferencia(Conta contaDestino, double valor) {
        sacar(valor);
        contaDestino.depositar(valor);
    }

    protected void imprimirInformacoes() {
        System.out.println(
                String.format("Titular: %s\n", this.cliente.getNome()) +
                        String.format("Agência: %d\n", this.agencia) +
                        String.format("Número: %d\n", this.numero) +
                        String.format("Saldo: %.2f\n", this.saldo)
        );
    }
}