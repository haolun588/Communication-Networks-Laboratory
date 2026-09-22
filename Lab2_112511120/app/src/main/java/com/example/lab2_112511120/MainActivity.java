package com.example.lab2_112511120;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextID;
    private EditText editTextName;
    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. 宣告與連結 xml 中的元件
        editTextID = findViewById(R.id.editTextID);
        editTextName = findViewById(R.id.editTextName);
        buttonStartGame = findViewById(R.id.buttonStartGame);

        // 2. 設定按鈕點擊監聽器
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取得輸入的學號與姓名
                String idStr = editTextID.getText().toString();
                String nameStr = editTextName.getText().toString();

                // 建立 Intent 並指定跳轉目標為 MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // 使用 Bundle 傳送資料
                Bundle bundle = new Bundle();
                bundle.putString("id", idStr);
                bundle.putString("name", nameStr);

                // 將 bundle 加入 intent 中並啟動新的 Activity
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}