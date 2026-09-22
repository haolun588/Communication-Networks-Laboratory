package com.example.lab1_2_112511120;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView Text; // 宣告 TextView 物件變數 Text
    EditText editTextText; // 宣告 EditText 物件變數 editTextText
    Button buttonset; // 宣告 Button 物件變數 buttonset
    Button buttonreset; // 宣告 Button 物件變數 buttonreset

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 呼叫父類別的 onCreate 方法以執行預設初始化
        setContentView(R.layout.activity_main); // 設定 Activity 的畫面佈局內容為 activity_main.xml

        Text = (TextView) findViewById(R.id.Text); // 透過 ID 取得畫面中的 Text TextView 物件
        editTextText = (EditText) findViewById(R.id.editTextText); // 透過 ID 取得畫面中的 editTextText EditText 物件
        buttonset = (Button) findViewById(R.id.buttonset); // 透過 ID 取得畫面中的 buttonset Button 物件
        buttonreset = (Button) findViewById(R.id.buttonreset); // 透過 ID 取得畫面中的 buttonreset Button 物件

        buttonset.setOnClickListener(new View.OnClickListener() { // 設定 buttonset 按鈕的點擊監聽器
            @Override
            public void onClick(View v) { // 當 buttonset 被點擊時執行的事件
                Text.setText(" Welcome to Android, " + editTextText.getText().toString()); // 將 Text 文字設定為歡迎詞加上輸入框的內容
            }
        });

        buttonreset.setOnClickListener(new View.OnClickListener() { // 設定 buttonreset 按鈕的點擊監聽器
            @Override
            public void onClick(View v) { // 當 buttonreset 被點擊時執行的事件
                Text.setText(" Hello World!"); // 將 Text 的顯示文字重設為 " Hello World!"
            }
        });
    }
}