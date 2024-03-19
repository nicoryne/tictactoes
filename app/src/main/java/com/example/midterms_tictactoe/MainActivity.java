package com.example.midterms_tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnTicTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTicTacToe = findViewById(R.id.btnTicTacToe);

        btnTicTacToe.setOnClickListener(v -> {
            String toastMessage = "FULL NAME" + "\n" + "        TicTacToe Activity";
            Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, TicTacToe.class));
        });
    }


}