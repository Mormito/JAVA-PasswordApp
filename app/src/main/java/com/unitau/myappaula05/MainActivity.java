package com.unitau.myappaula05;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private TextView textView2;
    private Button confirmButton;
    private CheckBox checkboxUppercase, checkboxLowercase, checkboxNumbers, checkboxSymbols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.text_input1);
        editText2 = findViewById(R.id.text_input2);
        textView2 = findViewById(R.id.textViewResult);
        confirmButton = findViewById(R.id.confirm_button);

        checkboxUppercase = findViewById(R.id.checkbox_uppercase);
        checkboxLowercase = findViewById(R.id.checkbox_lowercase);
        checkboxNumbers = findViewById(R.id.checkbox_numbers);
        checkboxSymbols = findViewById(R.id.checkbox_symbols);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassword();
            }
        });
    }


    public String gerarSenha(int tamanho, boolean usarMaiusculas, boolean usarMinusculas, boolean usarNumeros, boolean usarSimbolos) {
        Random rand = new Random();
        StringBuilder senha = new StringBuilder();
        StringBuilder caracteres = new StringBuilder();

        if (usarMaiusculas) caracteres.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if (usarMinusculas) caracteres.append("abcdefghijklmnopqrstuvwxyz");
        if (usarNumeros) caracteres.append("0123456789");
        if (usarSimbolos) caracteres.append("!@#$%&*_");

        if (caracteres.length() == 0) {
            return null;
        }

        for (int j = 0; j < tamanho; j++) {
            int indice = rand.nextInt(caracteres.length());
            senha.append(caracteres.charAt(indice));
        }

        return senha.toString();
    }

    public void showPassword() {
        String input = editText2.getText().toString();
        String name = editText1.getText().toString();

        if (name.isEmpty()) {
            name = "Generic name";
        }

        try {
            int tamanhoSenha = Integer.parseInt(input);
            if (tamanhoSenha <= 0) {
                Toast.makeText(this, "Digite um número positivo!", Toast.LENGTH_SHORT).show();
                return;
            }

            String senhaGerada = gerarSenha(
                    tamanhoSenha,
                    checkboxUppercase.isChecked(),
                    checkboxLowercase.isChecked(),
                    checkboxNumbers.isChecked(),
                    checkboxSymbols.isChecked()
            );

            if (senhaGerada == null) {
                Toast.makeText(this, "Selecione pelo menos uma opção de caractere!", Toast.LENGTH_SHORT).show();
                return;
            }

            textView2.setText(name + ": " + senhaGerada);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Digite um número válido!", Toast.LENGTH_SHORT).show();
        }
    }
}

