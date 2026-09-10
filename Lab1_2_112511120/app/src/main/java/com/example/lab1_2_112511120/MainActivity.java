package com.example.lab1_2_112511120;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView Text;
    EditText editTextText;
    Button buttonset;
    Button buttonreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = (TextView) findViewById(R.id.Text);
        editTextText = (EditText) findViewById(R.id.editTextText);
        buttonset = (Button) findViewById(R.id.buttonset);
        buttonreset = (Button) findViewById(R.id.buttonreset);

        buttonset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text.setText(" Welcome to Android, " + editTextText.getText().toString());
            }
        });

        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text.setText(" Hello World!");
            }
        });
    }
}