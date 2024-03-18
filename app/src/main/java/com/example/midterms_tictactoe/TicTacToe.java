package com.example.midterms_tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class TicTacToe extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnRestart;
    TextView turnView;
    Boolean isOTurn = true;
    CharSequence playerXTurn = "Player X's turn";
    CharSequence playerOTurn = "Player O's turn";

    ArrayList<Button> buttonArrayList = new ArrayList<>();
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    int checkCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        initButtons();
        turnView = findViewById(R.id.tv1);

        if (savedInstanceState != null) {
            isOTurn = savedInstanceState.getBoolean("isOTurn");
            CharSequence turnText = savedInstanceState.getCharSequence("turnText");
            turnView.setText(turnText);

            btn1.setText(savedInstanceState.getCharSequence("btn1Text"));
            btn2.setText(savedInstanceState.getCharSequence("btn2Text"));
            btn3.setText(savedInstanceState.getCharSequence("btn3Text"));
            btn4.setText(savedInstanceState.getCharSequence("btn4Text"));
            btn5.setText(savedInstanceState.getCharSequence("btn5Text"));
            btn6.setText(savedInstanceState.getCharSequence("btn6Text"));
            btn7.setText(savedInstanceState.getCharSequence("btn7Text"));
            btn8.setText(savedInstanceState.getCharSequence("btn8Text"));
            btn9.setText(savedInstanceState.getCharSequence("btn9Text"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("isOTurn", isOTurn);
        outState.putCharSequence("turnText", turnView.getText());

        outState.putCharSequence("btn1Text", btn1.getText());
        outState.putCharSequence("btn2Text", btn2.getText());
        outState.putCharSequence("btn3Text", btn3.getText());
        outState.putCharSequence("btn4Text", btn4.getText());
        outState.putCharSequence("btn5Text", btn5.getText());
        outState.putCharSequence("btn6Text", btn6.getText());
        outState.putCharSequence("btn7Text", btn7.getText());
        outState.putCharSequence("btn8Text", btn8.getText());
        outState.putCharSequence("btn9Text", btn9.getText());
    }

    void initButtons() {
        btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(v -> restartGame());

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        //  ADDING BUTTONS TO ARRAYLIST
        buttonArrayList.add(btn1);
        buttonArrayList.add(btn2);
        buttonArrayList.add(btn3);
        buttonArrayList.add(btn4);
        buttonArrayList.add(btn5);
        buttonArrayList.add(btn6);
        buttonArrayList.add(btn7);
        buttonArrayList.add(btn8);
        buttonArrayList.add(btn9);

        // SET ON CLICKERS
        for(Button button : buttonArrayList) {
            button.setOnClickListener(v -> {
                int index = buttonArrayList.indexOf(button);
                checkCounter++;
                button.setClickable(false);

                if (isOTurn) {
                    gameState[index] = 0;
                    button.setText("O");
                    turnView.setText(playerXTurn);
                    isOTurn = false;
                } else {
                    gameState[index] = 1;
                    button.setText("X");
                    turnView.setText(playerOTurn);
                    isOTurn = true;
                }

                if (checkCounter == 9) {
                    turnView.setText((CharSequence) "Match Draw!");
                }

                if(checkCounter > 4) {
                    for(int[] winPosition : winPositions) {
                        if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                                gameState[winPosition[0]] != 2) {

                            for(Button otherButtons: buttonArrayList) {
                                otherButtons.setEnabled(false);
                            }

                            if(!isOTurn) {
                                turnView.setText((CharSequence) "Player O won!");
                            } else {
                                turnView.setText((CharSequence) "Player X won!");
                            }
                            Toast.makeText(TicTacToe.this, "Winner!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }

    void restartGame() {
        Arrays.fill(gameState, 2);

        for(Button button : buttonArrayList) {
            button.setText("");
            button.setClickable(true);
            button.setEnabled(true);
        }

        isOTurn = true;
        checkCounter = 0;
        turnView.setText(playerOTurn);
    }


}