package br.com.howads.calculadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import br.com.howads.calculadora.database.DatabaseHelper;
import br.com.howads.calculadora.historico.Valores;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//pegar tudo que queremos que apareça e/ou interaja e listar aqui de acordo com o tipo

    private Button numero1,numero2,numero3,numero4,numero5,numero6,numero7,numero8,numero9,numero0,botao_divisao,botao_multiplicacao,
    botao_subtracao,botao_soma, botao_igual,ponto,botao_clear, abre_parentese, fecha_parentese;

    private TextView txt_expressao, txt_resultado;
    private ImageView botao_apagar;
    private MenuFragment menuFragment;
    private Object MenuInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inciarComponentes();
        //mostrar a actionbar
        ActionBar actionBar= getSupportActionBar();

        numero0.setOnClickListener(this);
        numero1.setOnClickListener(this);
        numero2.setOnClickListener(this);
        numero3.setOnClickListener(this);
        numero4.setOnClickListener(this);
        numero5.setOnClickListener(this);
        numero6.setOnClickListener(this);
        numero7.setOnClickListener(this);
        numero8.setOnClickListener(this);
        numero9.setOnClickListener(this);
        abre_parentese.setOnClickListener(this);
        fecha_parentese.setOnClickListener(this);
        botao_divisao.setOnClickListener(this);
        botao_igual.setOnClickListener(this);
        botao_multiplicacao.setOnClickListener(this);
        botao_subtracao.setOnClickListener(this);
        botao_soma.setOnClickListener(this);
        ponto.setOnClickListener(this);

        botao_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_expressao.setText("");
                txt_resultado.setText("");
            }
        });

        botao_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView expressao = findViewById(R.id.txt_expressao);
                String string = expressao.getText().toString();

                if (!string.isEmpty()){

                    byte var0 = 0;
                            int var1 = string.length()-1;
                    String txtexpressao = string.substring(var0,var1);
                    expressao.setText(txtexpressao);
                                    }
                txt_resultado.setText(" ");
            }
        });

        botao_igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    //ExpressionBuldrr fz os calculos do app

                    Expression expressao = new ExpressionBuilder(txt_expressao.getText().toString()).build();
                    double resultado = expressao.evaluate();
                    long longResultado = (long) resultado;
                    if (resultado == (double) longResultado) {

                        txt_resultado.setText((CharSequence) String.valueOf(longResultado));

                    } else {
                        txt_resultado.setText((CharSequence) String.valueOf(resultado));
                    }
                } catch (Exception e){}

                TextView expressao = findViewById(R.id.txt_expressao);
                String string = expressao.getText().toString();

               if (string.equals("")){

                    Toast.makeText(MainActivity.this,"Por favor insira os valores que deseja calcular",Toast.LENGTH_LONG).show();
                }
               else{
                   DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                   Valores v = new Valores();
                   v.setExpressao(txt_expressao.getText().toString());
                   v.setResultado(txt_resultado.getText().toString());
                   databaseHelper.criarHistorico(v);
               }

            }
        });


            }



// referenciar o nome com os id's
    private  void inciarComponentes(){
        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        numero3 = findViewById(R.id.numero3);
        numero4 = findViewById(R.id.numero4);
        numero5 = findViewById(R.id.numero5);
        numero6 = findViewById(R.id.numero6);
        numero7 = findViewById(R.id.numero7);
        numero8 = findViewById(R.id.numero8);
        numero9 = findViewById(R.id.numero9);
        numero0 = findViewById(R.id.numero0);
        abre_parentese = findViewById(R.id.abre_parentese);
        fecha_parentese = findViewById(R.id.fecha_parentese);
        botao_clear = findViewById(R.id.botao_clear);
        botao_apagar = findViewById(R.id.botao_apagar);
        botao_divisao = findViewById(R.id.botao_divisao);
        botao_igual = findViewById(R.id.botao_igual);
        botao_multiplicacao = findViewById(R.id.botao_multiplicacao);
        botao_subtracao = findViewById(R.id.botao_subtracao);
        botao_soma = findViewById(R.id.botao_soma);
        ponto= findViewById(R.id.ponto);
        txt_resultado = findViewById(R.id.txt_resultado);
        txt_expressao = findViewById(R.id.txt_expressao);





    }

    public void Acrescentarexpressao(String string,Boolean limpar_dados){
        if (txt_resultado.getText().equals("")){
            txt_expressao.setText(" ");
        }
        if (limpar_dados){
            txt_resultado.setText(" ");
            txt_expressao.append(string);
        }
        else {
            txt_expressao.append(txt_resultado.getText());
            txt_expressao.append(string);
            txt_resultado.setText(" ");
        }
    }

    //codigo pra aparecer o numero quando clicar nos botoes

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.numero0:
                Acrescentarexpressao("0" , true);


            case R.id.numero1:
                Acrescentarexpressao("1" , true);
                break;

            case R.id.numero2:
                Acrescentarexpressao("2" , true);
                break;

            case R.id.numero3:
                Acrescentarexpressao("3" , true);
                break;

            case R.id.numero4:
                Acrescentarexpressao("4" , true);
                break;

            case R.id.numero5:
                Acrescentarexpressao("5" , true);
                break;

            case R.id.numero6:
                Acrescentarexpressao("6" , true);
                break;

            case R.id.numero7:
                Acrescentarexpressao("7" , true);
                break;

            case R.id.numero8:
                Acrescentarexpressao("8" , true);
                break;

            case R.id.numero9:
                Acrescentarexpressao("9" , true);
                break;

            case R.id.botao_divisao:
                Acrescentarexpressao("/" , false);
                break;

            case R.id.botao_multiplicacao:
                Acrescentarexpressao("*" , false);
                break;

            case R.id.botao_subtracao:
                Acrescentarexpressao("-" , false);
                break;

            case R.id.botao_soma:
                Acrescentarexpressao("+" , false);
                break;

            case R.id.ponto:
                Acrescentarexpressao("." , true);
                break;

            case R.id.abre_parentese:
                Acrescentarexpressao("(" , true);
                break;

            case R.id.fecha_parentese:
                Acrescentarexpressao(")" , true);
                break;

        }

    }
//habilitar a funcao de voltar no botaozinho lá em cima
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}