package com.example.lab2_112511120;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    // UI 元件
    private Button buttonRestart;
    private Button buttonLockin;
    private Button buttonEndGame;
    private EditText editTextText;
    private TextView textViewHint;
    private TextView textViewGuesstime;
    private TextView textViewBestRecord;

    // 遊戲狀態變數
    private int randNum;
    private int min = 1;
    private int max = 50;
    private int count = 0;
    private int history = 0; // 最佳紀錄 (0 表示尚未產生紀錄)

    // 儲存從 MainActivity 傳過來的資料
    private String idStr = "";
    private String nameStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. 接收來自 MainActivity (Q1) 的 Bundle 資料
        Bundle bundleFromMain = getIntent().getExtras();
        if (bundleFromMain != null) {
            idStr = bundleFromMain.getString("id", "");
            nameStr = bundleFromMain.getString("name", "");
        }

        // 2. 連結 XML 元件
        buttonRestart = findViewById(R.id.buttonRestart);
        buttonLockin = findViewById(R.id.buttonLockin);
        buttonEndGame = findViewById(R.id.buttonEndGame);
        editTextText = findViewById(R.id.editTextText);
        textViewHint = findViewById(R.id.textViewHint);
        textViewGuesstime = findViewById(R.id.textViewGuesstime);
        textViewBestRecord = findViewById(R.id.textViewBestRecord);

        // 3. 初始化猜數字題目 (1~50)
        resetGame();

        // 4. 「送出」按鈕事件 (Q2 猜數字邏輯)
        buttonLockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editTextText.getText().toString().trim();

                if (inputStr.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "請輸入數字！", Toast.LENGTH_SHORT).show();
                    return;
                }

                int inputNum = Integer.parseInt(inputStr);

                if (inputNum >= min && inputNum <= max) {
                    count++;
                    if (inputNum > randNum) {
                        max = inputNum;
                        textViewHint.setText("請輸入" + min + "~" + max + "的數字");
                    } else if (inputNum < randNum) {
                        min = inputNum;
                        textViewHint.setText("請輸入" + min + "~" + max + "的數字");
                    } else { // 猜中答案
                        textViewHint.setText("猜對了！答案是 " + randNum);

                        // 更新最佳紀錄
                        if (history == 0 || count < history) {
                            history = count;
                            textViewBestRecord.setText("最佳紀錄: " + history);
                        }
                    }
                    textViewGuesstime.setText("猜測次數: " + count);
                } else {
                    textViewHint.setText("請輸入" + min + "~" + max + "的數字，請輸入正常值");
                }

                editTextText.setText("");
            }
        });

        // 5. 「重新開始」按鈕事件
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        // 6. 「結束遊戲」按鈕事件 (Q3 傳遞資料至 MainActivity3)
        buttonEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                Bundle bundle = new Bundle();

                // 打包 Q1 的學號與姓名 + Q2 的最佳紀錄
                bundle.putString("id", idStr);
                bundle.putString("name", nameStr);
                bundle.putInt("best", history);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    // 重置單輪遊戲
    private void resetGame() {
        randNum = (int) (Math.random() * 50 + 1);
        min = 1;
        max = 50;
        count = 0;

        textViewHint.setText("請輸入1~50的數字");
        textViewGuesstime.setText("猜測次數: 0");
        editTextText.setText("");
    }
}