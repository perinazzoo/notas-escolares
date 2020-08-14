package com.gabrielperinazzo.appnotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText inputN1, inputN2;
    TextView valueAVG, valueSituation;
    LinearLayout container, dataContainer, resultContainer, imageContainer;
    Button btnCalc, btnClear;
    ImageView imgSituation;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText n1 = findViewById(R.id.inputN1);
        final EditText n2 = findViewById(R.id.inputN2);
        final TextView average = findViewById(R.id.valueAVG);
        final TextView situation = findViewById(R.id.valueSituation);
        final ImageView image = findViewById(R.id.imgSituation);


        final InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        Button calc = findViewById(R.id.btnCalc);
        Button clear = findViewById(R.id.btnClear);

        final LinearLayout layout = findViewById(R.id.resultContainer);

        layout.setVisibility(View.INVISIBLE);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1.setText("");
                n2.setText("");
                layout.setVisibility(View.INVISIBLE);
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                keyboard.hideSoftInputFromWindow(n2.getWindowToken(), 0);

                if (n1.getText().toString().trim().isEmpty()) {
                    n1.setError(getString(R.string.txt_error));
                    return;
                }

                if (n2.getText().toString().trim().isEmpty()) {
                    n2.setError(getString(R.string.txt_error));
                    return;
                }

                float grade1 = Float.parseFloat(n1.getText().toString());
                float grade2 = Float.parseFloat(n2.getText().toString());

                float gradeAverage = (grade1 + grade2) / 2;

                average.setText(String.format("%.1f", gradeAverage));

                if (gradeAverage < 5) {
                    situation.setText(getString(R.string.txt_disapproved));
                    situation.setTextColor(Color.parseColor("#DF2935"));
                    image.setImageResource(R.drawable.reprovado);
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_disapproved), Toast.LENGTH_SHORT).show();

                } else if (gradeAverage < 7) {
                    situation.setText(getString(R.string.txt_recovery));
                    situation.setTextColor(Color.parseColor("#FDCA40"));
                    image.setImageResource(R.drawable.recuperacao);
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_recovery), Toast.LENGTH_SHORT).show();
                } else {
                    situation.setText(getString(R.string.txt_approved));
                    situation.setTextColor(Color.parseColor("#26C485"));
                    image.setImageResource(R.drawable.aprovado);
                    Toast.makeText(getApplicationContext(), getString(R.string.txt_approved), Toast.LENGTH_SHORT).show();
                }

                layout.setVisibility(View.VISIBLE);
            }
        });

    }
}
