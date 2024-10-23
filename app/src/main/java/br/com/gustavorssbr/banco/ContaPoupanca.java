package br.com.gustavorssbr.banco;

public class ContaPoupanca extends ContaBancaria{
    private int diaRendimento;

    public ContaPoupanca() {
        super();
    }

    public void calcularNovoSaldo(float taxa){
        depositar((float) (getSaldo() *  Math.pow((1 + taxa / 100), diaRendimento)));
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }
}
