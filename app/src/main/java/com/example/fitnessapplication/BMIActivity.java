package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    EditText visinaTxt, tezinaTxt;
    TextView bmiTxt;
    TextView odgovorTxt;
    Button izracunaj;
    TextView btnStanjaBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        visinaTxt = (EditText) findViewById(R.id.vasaVisina);
        tezinaTxt = (EditText) findViewById(R.id.vasaTezina);
        bmiTxt = (TextView) findViewById(R.id.bmi);
        izracunaj = (Button) findViewById(R.id.izracunaj);
        btnStanjaBmi = (TextView) findViewById(R.id.ostalaStanja);
        odgovorTxt = (TextView) findViewById(R.id.odgovor);

        izracunaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String visinaString = visinaTxt.getText().toString();
                double visina = Double.parseDouble(visinaString);
                double visinaD = visina/100;

                String tezinaString = tezinaTxt.getText().toString();
                double tezina = Double.parseDouble(tezinaString);
                double bmi = tezina/(visinaD*visinaD);

                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                double BMI_trimmed = Double.parseDouble(decimalFormat.format(bmi));
                bmiTxt.setText(Double.toString(BMI_trimmed));

                String odgovor;
                String bmiKategorizacija;

                bmiKategorizacija = "< 16 Pothranjenost" +
                        "\n" +
                        "16 - 17 Pothranjenost" +
                        "\n" +
                        "17 - 18.5 Pothranjenost" +
                        "\n" +
                        "18.5 - 25 Idealna tjelesna masa" +
                        "\n" +
                        "25 - 30 Povećana tjelesna masa" +
                        "\n" +
                        "30 - 35 Pretilost prvog stupnja" +
                        "\n" +
                        "35 - 40 Pretilost drugog stupnja" +
                        "\n" +
                        "> 40 Pretilost trećeg stupnja";

                if (bmi < 16){
                    odgovor = "Pothranjenost";
                }
                else if (bmi >= 16 && bmi < 17){
                    odgovor = "Pothranjenost";
                }
                else if (bmi >=17 && bmi < 18.5){
                    odgovor = "Pothranjenost";
                }
                else if (bmi >=18.5 && bmi < 25){
                    odgovor = "Idealna tjelesna masa";
                }
                else if (bmi >= 25 && bmi < 30){
                    odgovor = "Povećana tjelesna masa";
                }
                else if (bmi>=30 && bmi < 35 ){
                    odgovor = "Pretilost prvog stupnja";
                }
                else if (bmi>= 35 && bmi < 40){
                    odgovor = "Pretilost drugog stupnja";
                }
                else {
                    odgovor = "Pretilost trećeg stupnja";

                }
                odgovorTxt.setText(odgovor);
                btnStanjaBmi.setText(bmiKategorizacija);
            }
        });
    }
}