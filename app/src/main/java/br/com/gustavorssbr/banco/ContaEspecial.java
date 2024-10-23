package br.com.gustavorssbr.banco;

public class ContaEspecial extends ContaBancaria{
    private float limite;

    public ContaEspecial() {
        super();
    }

    @Override
    public void sacar(float valor){
        if (valor > limite + getSaldo()){
            throw new RuntimeException("Saldo Indisponivel");
        }
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }
}
