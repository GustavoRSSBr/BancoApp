package br.com.gustavorssbr.banco;

public abstract class ContaBancaria {
private String Cliente;
private int num_conta;
private float saldo;


    public ContaBancaria() {
        super();
    }

    public void sacar(float valor){
        if(saldo < valor){
            throw new RuntimeException("Saldo Indisponivel");
        }
        saldo -= valor;
    }

    public void depositar(float valor){
        saldo += valor;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
