package com.example.lasttic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[9];
    private boolean player1trun = true;

    private int roundCount = 0;
    private int player1Points = 0;
    private int player2Points = 0;

    private TextView player1, player2;
    Button restartbt;

    private boolean isGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = findViewById(R.id.player1Id);
        player2 = findViewById(R.id.player2Id);

        for (int i = 0; i < 9; i++) {
            String allbox = "btn" + String.valueOf(i);
            int resID = getResources().getIdentifier(allbox, "id", getPackageName());

            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);

        }
        restartbt = findViewById(R.id.resertId);

        restartbt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                restart();



            }
        });


    }

    //share-----


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()== R.id.shareId){


            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                    startActivity(Intent.createChooser(sharingIntent, "Share using"));


                    return true;
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }


//share finish ------

    @Override
    public void onClick(View v) {

        if (isGameOver) {

            Toast.makeText(this, "Game already over", Toast.LENGTH_SHORT).show();
            return;
        }

        if (((Button) v).getText().toString().equals("")) {
            if (roundCount % 2 == 0) {

                ((Button) v).setText("X");


            } else {
                ((Button) v).setText("O");


            }
            checkforWins();
            roundCount++;
        }

    }


    public void checkforWins() {
        String[] field = new String[10];
        int i;
        for (i = 1; i <= 9; i++) {
            field[i] = buttons[i - 1].getText().toString();
        }

        if
        (
                        field[1].equals(field[2]) && field[1].equals(field[3]) && !field[1].equals("") ||
                        field[4].equals(field[5]) && field[4].equals(field[6]) && !field[4].equals("") ||
                        field[7].equals(field[8]) && field[7].equals(field[9]) && !field[7].equals("") ||
                        field[1].equals(field[4]) && field[1].equals(field[7]) && !field[1].equals("") ||
                        field[2].equals(field[5]) && field[2].equals(field[8]) && !field[2].equals("") ||
                        field[3].equals(field[6]) && field[3].equals(field[9]) && !field[3].equals("") ||
                        field[1].equals(field[5]) && field[1].equals(field[9]) && !field[1].equals("") ||
                        field[3].equals(field[5]) && field[3].equals(field[7]) && !field[3].equals("")) {
            if (roundCount % 2 == 0) {
                Toast.makeText(this, "Player two WIN", Toast.LENGTH_SHORT).show();
                player1trun = true;
                player2Points++;
                player1.setText("Player 1:" + player2Points);

                isGameOver = true;

            } else {
                Toast.makeText(this, "Player One WIN !!!", Toast.LENGTH_SHORT).show();
                player1trun = true;
                player1Points++;

                player2.setText("Player 2:" + player1Points);

                isGameOver = true;

            }
         }
        else {
            if (roundCount == 8) {
                Toast.makeText(this, "DRAW !!!", Toast.LENGTH_SHORT).show();
                player1trun = true;

                isGameOver = true;
            }
        }
    }


    public void restart() {
        roundCount = 0;
        player1trun = false;

        isGameOver = false;

        for (int i = 0; i < 9; i++) {
            String allbox = "btn" + String.valueOf(i);
            int resID = getResources().getIdentifier(allbox, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setText("");
        }
    }


}