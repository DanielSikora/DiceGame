package com.example.dice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Tablica przechowująca referencje do obiektów ImageView (dla każdego z 5 rzutów kośćmi)
    private ImageView[] diceImages;

    // Tablica z zasobami obrazów przedstawiających wyniki od 1 do 6 (k6_1 do k6_6)
    private int[] diceDrawables = {
            R.drawable.k6_1, R.drawable.k6_2, R.drawable.k6_3,
            R.drawable.k6_4, R.drawable.k6_5, R.drawable.k6_6
    };

    // Tekst, który wyświetla wynik losowania
    private TextView resultText;

    // Obiekt Random do generowania losowych liczb (symulowanie rzutu kością)
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja tablicy ImageView dla kości
        diceImages = new ImageView[5];
        diceImages[0] = findViewById(R.id.dice1);
        diceImages[1] = findViewById(R.id.dice2);
        diceImages[2] = findViewById(R.id.dice3);
        diceImages[3] = findViewById(R.id.dice4);
        diceImages[4] = findViewById(R.id.dice5);

        // Inicjalizacja TextView, który pokazuje wynik losowania
        resultText = findViewById(R.id.result_text);

        // Inicjalizacja przycisków (RZUC KOŚCI i RESETUJ WYNIK)
        Button rollButton = findViewById(R.id.roll_button);
        Button resetButton = findViewById(R.id.reset_button);

        // Tworzenie obiektu Random do generowania losowych liczb
        random = new Random();

        // Ustawienie akcji dla przycisku "RZUC KOŚCI"
        // Po kliknięciu na ten przycisk zostaną "rzucone" kości i wynik będzie wyświetlony
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice(); // Wywołanie metody rollDice() po kliknięciu
            }
        });

        // Ustawienie akcji dla przycisku "RESETUJ WYNIK"
        // Po kliknięciu na ten przycisk gra wraca do stanu początkowego
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame(); // Wywołanie metody resetGame() po kliknięciu
            }
        });
    }

    // Funkcja symulująca rzuty kośćmi
    // Losuje liczby od 1 do 6, ustawia odpowiednie obrazki kości i sumuje wynik
    private void rollDice() {
        int totalSum = 0; // Zmienna do przechowywania sumy wyników z rzutów
        for (int i = 0; i < 5; i++) { // Pętla dla 5 kości
            // Losowanie liczby od 0 do 5 (odpowiednik liczb na kościach 1-6)
            int diceRoll = random.nextInt(6);
            // Ustawienie odpowiedniego obrazka kości (od k6_1 do k6_6)
            diceImages[i].setImageResource(diceDrawables[diceRoll]);
            // Dodawanie wyniku rzutu do całkowitej sumy (diceRoll + 1, bo random zwraca od 0 do 5)
            totalSum += (diceRoll + 1);
        }
        // Wyświetlenie sumy wyników rzutu w TextView (resultText)
        resultText.setText("Wynik tego losowania: " + totalSum);
    }

    // Funkcja resetująca grę
    // Ustawia wszystkie obrazki na znak zapytania (k6_0) i resetuje tekst z wynikiem
    private void resetGame() {
        // Pętla ustawiająca obrazki kości na stan początkowy (k6_0 to obrazek z pytajnikiem)
        for (ImageView diceImage : diceImages) {
            diceImage.setImageResource(R.drawable.k6_0); // Obrazek pytajnika dla każdej kości
        }
        // Resetowanie wyniku w TextView do początkowego komunikatu
        resultText.setText("Wynik tego losowania: ");
    }
}
