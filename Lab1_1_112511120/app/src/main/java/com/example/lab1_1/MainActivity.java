package com.example.lab1_1;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView showtext; // 宣告 TextView 物件變數 showtext
    Button demobutton; // 宣告 Button 物件變數 demobutton
    Button backbutton; // 宣告 Button 物件變數 backbutton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 呼叫父類別的 onCreate 方法以執行預設初始化
        setContentView(R.layout.activity_main); // 設定 Activity 的畫面佈局內容為 activity_main.xml

        showtext = (TextView) findViewById(R.id.showtext); // 透過 ID 取得畫面中的 showtext TextView 物件
        demobutton = (Button) findViewById(R.id.demobutton); // 透過 ID 取得畫面中的 demobutton Button 物件
        backbutton = (Button) findViewById(R.id.backbutton); // 透過 ID 取得畫面中的 backbutton Button 物件

        demobutton.setOnClickListener(new View.OnClickListener() { // 設定 demobutton 按鈕的點擊監聽器
            @Override
            public void onClick(View v) { // 當 demobutton 按鈕被點擊時執行的觸發事件
                showtext.setText("Pass!"); // 將 showtext 的顯示文字設定為 "Pass!"
                int size = (int) showtext.getTextSize(); // 取得 showtext 目前的文字大小
                showtext.setTextSize(TypedValue.COMPLEX_UNIT_PX, size + 10); // 將 showtext 的文字大小增加 10 像素
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() { // 設定 backbutton 按鈕的點擊監聽器
            @Override
            public void onClick(View v) { // 當 backbutton 按鈕被點擊時執行的觸發事件
                showtext.setText("Android Lab1 Demo"); // 將 showtext 的顯示文字恢復為 "Android Lab1 Demo"
                int size = (int) showtext.getTextSize(); // 取得 showtext 目前的文字大小
                if (size > 20) { // 判斷如果字型大小大於 20 像素
                    showtext.setTextSize(TypedValue.COMPLEX_UNIT_PX, size - 10); // 將 showtext 的文字大小減少 10 像素
                }
            }
        });
    }
}