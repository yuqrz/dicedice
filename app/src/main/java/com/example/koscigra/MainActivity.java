package com.example.koscigra;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.koscigra.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView grra;
    private TextView[] kosc = new TextView[5];
    private TextView wyniklosowania;
    private TextView wynikgry;
    private TextView liczbarzutow;
    private Button koscibutton;
    private Button buttonreset;

    private int calywynik = 0;
    private int liczbarzutowValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grra = findViewById(R.id.grra);
        kosc[0] = findViewById(R.id.ksc1);
        kosc[1] = findViewById(R.id.ksc2);
        kosc[2] = findViewById(R.id.ksc3);
        kosc[3] = findViewById(R.id.ksc4);
        kosc[4] = findViewById(R.id.ksc5);
        wyniklosowania = findViewById(R.id.wyniklosowania);
        wynikgry = findViewById(R.id.wynikgry);
        liczbarzutow = findViewById(R.id.liczbarzutow);
        koscibutton = findViewById(R.id.koscibutton);
        buttonreset = findViewById(R.id.buttonreset);

    }

    private void rollDice() {
        Random random = new Random();
        int[] numery = new int[5];
        for (int i = 0; i < 5; i++) {
            numery[i] = random.nextInt(6) + 1;
        }
        wyswietlWynikiKosci(numery);
        int wynik = obliczWynik(numery);
        updateScore(wynik);
        updaterollcount();
    }

    private int obliczWynik(int[] numery) {
        int[] indeksy = new int[7];
        for (int result : numery) {
            indeksy[result]++;
        }
        int wynik = 0;
        for (int i = 1; i <= 6; i++) {
            if (indeksy[i] >= 2) {
                wynik += i * indeksy[i];
            }
        }
        return wynik;
    }

    private void resetGame() {
        for (TextView k : kosc) {
            k.setText("?");
        }
        calywynik = 0;
        liczbarzutowValue = 0;
        wyniklosowania.setText("Wynik tego losowania: 0");
        wynikgry.setText("Wynik gry: 0");
        liczbarzutow.setText("Liczba rzutów: 0");
    }

    private void updateScore(int nowyWynik) {
        calywynik += nowyWynik;
        wynikgry.setText("Wynik gry: " + calywynik);
        wyniklosowania.setText("Wynik tego losowania: " + nowyWynik);
    }

    private void updaterollcount() {
        liczbarzutowValue++;
        liczbarzutow.setText("Liczba rzutów: " + liczbarzutowValue);
    }

    private void wyswietlWynikiKosci(int[] numery) {
        for (int i = 0; i < numery.length; i++) {
            kosc[i].setText(String.valueOf(numery[i]));
        }
        koscibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }
}