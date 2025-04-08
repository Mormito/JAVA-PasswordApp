package com.unitau.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView TextView, TextView2;
    private EditText EditText;
    private Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView = findViewById(R.id.textView1);
        TextView2 = findViewById(R.id.textViewResult);
        EditText = findViewById(R.id.text_input1);
        Button = findViewById(R.id.confirm_button);

        // Define o listener do botão
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults(v);
            }
        });
    }

    public String gerarSenha(int tamanho) {
        Random rand = new Random();
        StringBuilder senha = new StringBuilder();
        String caracteres = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789!@#$%&*_";

        for(int j = 0; j < tamanho; j++){
            int indice = rand.nextInt(caracteres.length());
            senha.append(caracteres.charAt(indice));
        }

        return senha.toString();
    }

    public void showResults(View v){
        String input = EditText.getText().toString();

        try {
            int tamanhoSenha = Integer.parseInt(input);
            if (tamanhoSenha <= 0) {
                Toast.makeText(this, "Digite um número positivo!", Toast.LENGTH_SHORT).show();
                return;
            }
            String senhaGerada = gerarSenha(tamanhoSenha);
            TextView2.setText(senhaGerada);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Digite um número válido!", Toast.LENGTH_SHORT).show();
        }
    }
}
