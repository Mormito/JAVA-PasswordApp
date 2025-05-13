package com.unitau.StrongPass;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private TextView textView2;
    private Button confirmButton;
    private CheckBox checkboxUppercase, checkboxLowercase, checkboxNumbers, checkboxSymbols;

    private AdView adView;
    private FrameLayout adContainerView;
    private final String AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"; // id teste: ca-app-pub-3940256099942544/9214589741
// ca-app-pub-7117707009404695/6661783671
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adContainerView = findViewById(R.id.ad_view_container);
        MobileAds.initialize(this, initializationStatus -> {});
        loadBanner();

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

    private void loadBanner() {
        // [START create_ad_view]
        // Create a new ad view.
        adView = new AdView(this);
        adView.setAdUnitId(AD_UNIT_ID);
        // [START set_ad_size]
        // Request an anchored adaptive banner with a width of 360.
        adView.setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, 360));
        // [END set_ad_size]

        // Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);
        // [END create_ad_view]

        // [START load_ad]
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        // [END load_ad]
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
                Toast.makeText(this, "Digite um número maior que zero!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (tamanhoSenha > 200) {
                Toast.makeText(this, "Numeros acima de 200 caracteres podem travar seu dispositivo!", Toast.LENGTH_SHORT).show();
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

