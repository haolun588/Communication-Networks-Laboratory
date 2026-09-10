package com.example.lab1_1;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView showtext;
    Button demobutton;
    Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showtext = (TextView) findViewById(R.id.showtext);
        demobutton = (Button) findViewById(R.id.demobutton);
        backbutton = (Button) findViewById(R.id.backbutton);

        demobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtext.setText("Pass!");
                int size = (int) showtext.getTextSize();
                showtext.setTextSize(TypedValue.COMPLEX_UNIT_PX, size + 10);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtext.setText("Android Lab1 Demo");
                int size = (int) showtext.getTextSize();
                if (size > 20) {
                    showtext.setTextSize(TypedValue.COMPLEX_UNIT_PX, size - 10);
                }
            }
        });
    }
}