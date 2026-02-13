package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        add = findViewById(R.id.btnAdd);
        sub = findViewById(R.id.btnSub);
        mul = findViewById(R.id.btnMul);
        div = findViewById(R.id.btnDiv);

        add.setOnClickListener(v -> calculate("+"));
        sub.setOnClickListener(v -> calculate("-"));
        mul.setOnClickListener(v -> calculate("*"));
        div.setOnClickListener(v -> calculate("/"));
    }

    private void calculate(String operator) {

        double n1 = Double.parseDouble(num1.getText().toString());
        double n2 = Double.parseDouble(num2.getText().toString());
        double result = 0;

        switch (operator) {
            case "+": result = n1 + n2; break;
            case "-": result = n1 - n2; break;
            case "*": result = n1 * n2; break;
            case "/": result = n1 / n2; break;
        }

        String output = n1 + " " + operator + " " + n2 + " = " + result;

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("result", output);
        startActivity(intent);
    }
}