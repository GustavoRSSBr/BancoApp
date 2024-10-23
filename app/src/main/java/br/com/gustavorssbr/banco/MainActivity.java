/*
 *@author:Gustavo Rodrigues Santos Silva
 * RA: 1110481922011
 */
package br.com.gustavorssbr.banco;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etNumConta;
    private EditText etDiaRend;
    private EditText etLimite;
    private EditText etValor;
    private EditText etTaxa;

    private RadioButton rbContPoup;
    private RadioButton rbContEsp;

    private TextView tvCadastro;
    private TextView tvDados;

    private ContaBancaria conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNome = findViewById(R.id.etNome);
        etNumConta = findViewById(R.id.etNumConta);
        etDiaRend = findViewById(R.id.etDiaRend);
        etLimite = findViewById(R.id.etLimite);
        etValor = findViewById(R.id.etValor);
        etTaxa = findViewById(R.id.etTaxa);

        rbContEsp = findViewById(R.id.rbContEsp);
        rbContPoup = findViewById(R.id.rbContPoup);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        Button btnSacar = findViewById(R.id.btnSacar);
        Button btnDepositar = findViewById(R.id.btnDepositar);
        Button btnRender = findViewById(R.id.btnRender);

        tvCadastro = findViewById(R.id.tvCadastro);
        tvDados = findViewById(R.id.tvDados);


        btnCadastrar.setOnClickListener(op -> cadastrar());

        btnDepositar.setOnClickListener(op -> depositar());

        btnSacar.setOnClickListener(op -> sacar());

        btnRender.setOnClickListener(op -> render());
    }

    private void render() {
        if(conta instanceof  ContaPoupanca){
            float taxa = Float.parseFloat(etTaxa.getText().toString());
            String textoSaldo;

            if(conta.getSaldo() > 0){
                ((ContaPoupanca) conta).calcularNovoSaldo(taxa);
                textoSaldo = "Saldo atualizado";
            }else{
                textoSaldo = "Sem rendimentos";
            }

            tvCadastro.setText(textoSaldo);

            String textoDados = "Nome Cliente: " + conta.getCliente() + "\n" + "Número Conta: " + conta.getNum_conta() + "\n" + "Saldo: " + conta.getSaldo();
            tvDados.setText(textoDados);
        }else {
            String textoInvalido = "Operação indisponivel para esse tipo de conta";
            tvCadastro.setText(textoInvalido);
        }


    }

    private void sacar() {
        float valor = Float.parseFloat(etValor.getText().toString());

        try {
            conta.sacar(valor);
            String textoSaldo = "Saldo atualizado";
            tvCadastro.setText(textoSaldo);

            String textoDados = "Nome Cliente: " + conta.getCliente() + "\n" + "Número Conta: " + conta.getNum_conta() + "\n" + "Saldo: " + conta.getSaldo();
            tvDados.setText(textoDados);
            
        } catch (Exception e) {
                tvCadastro.setText(e.getLocalizedMessage());
        }
    }

    private void depositar() {
        float valor = Float.parseFloat(etValor.getText().toString());
        conta.depositar(valor);

        String textoSaldo = "Saldo atualizado";
        tvCadastro.setText(textoSaldo);

        String textoDados = "Nome Cliente: " + conta.getCliente() + "\n" + "Número Conta: " + conta.getNum_conta() + "\n" + "Saldo: " + conta.getSaldo();
        tvDados.setText(textoDados);
    }

    private void cadastrar() {
        if(rbContPoup.isChecked()){
            conta = new ContaPoupanca();

            conta.setNum_conta(Integer.parseInt(etNumConta.getText().toString()));
            conta.setCliente(etNome.getText().toString());

            ((ContaPoupanca) conta).setDiaRendimento(Integer.parseInt(etDiaRend.getText().toString()));
            tvCadastro.setText(R.string.conta_cadastrada);
        }else if(rbContEsp.isChecked()){
            conta = new ContaEspecial();

            conta.setNum_conta(Integer.parseInt(etNumConta.getText().toString()));
            conta.setCliente(etNome.getText().toString());

            ((ContaEspecial) conta).setLimite(Float.parseFloat(etLimite.getText().toString()));
            tvCadastro.setText(R.string.conta_cadastrada);
        }else{
            tvCadastro.setText(R.string.selecione_conta);
        }


    }
}