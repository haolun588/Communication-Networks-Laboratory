package com.example.lab2_112511120;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private TextView textViewID;
    private TextView textViewName;
    private TextView textViewBest;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. 連結 XML 元件
        textViewID = findViewById(R.id.textViewID);
        textViewName = findViewById(R.id.textViewName);
        textViewBest = findViewById(R.id.textViewBest);
        buttonBack = findViewById(R.id.buttonBack);

        // 2. 接收從 MainActivity2 傳過來的 Bundle 資料
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String id = bundle.getString("id", "");
            String name = bundle.getString("name", "");
            int best = bundle.getInt("best", 0);

            // 顯示資料到畫面上
            textViewID.setText("學號: " + id);
            textViewName.setText("姓名: " + name);
            textViewBest.setText("最佳紀錄: " + best);
        }

        // 3. 設定 Back 按鈕點擊事件 (回到主頁面 MainActivity)
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                // 確保返回主頁時清除上層的 Activity 堆疊
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}