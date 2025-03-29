package com.unitau.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    public void showResults (View v){
        TextView2.setText(EditText.getText().toString());
    }
}