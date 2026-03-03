package com.example.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log; // Thêm import này
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Đặt TAG cố định để dễ lọc trong Logcat
    private static final String TAG = "LifeCycle_Nhom1";
    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    private Button exitButton;
    private EditText inputText;
    private TextView viewText;
    private LinearLayout myScreen;

    private String PREFNAME = "myPrefFile1";

    int originalOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        exitButton = findViewById(R.id.exit_button);
        inputText = (EditText) findViewById(R.id.input_text);
        viewText = (TextView) findViewById(R.id.view_text);
        myScreen = (LinearLayout) findViewById(R.id.main_layout);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String color = s.toString().toLowerCase(Locale.US);
                viewText.setText(color);
                setBackgroundColor(color, myScreen);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        context = getApplicationContext();
        showLogAndToast("onCreate");
    }

    private void setBackgroundColor(String chosenColor, LinearLayout myScreen) {
        if(chosenColor.contains("kien")) myScreen.setBackgroundColor(0xffff0000);//Color.RED
        if(chosenColor.contains("duyen")) myScreen.setBackgroundColor(0xff00ff00);//Color.GREEN
        if(chosenColor.contains("giang")) myScreen.setBackgroundColor(0xff0000ff);//Color.BLUE
        if(chosenColor.contains("nam")) myScreen.setBackgroundColor(0xffffffff);//Color.WHITE
        if(chosenColor.contains("minh")) myScreen.setBackgroundColor(0xff00ffff);//Color.WHITE
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        String chosenColor = inputText.getText().toString();
        outState.putString("chosenBackgroundColor", chosenColor);
        super.onSaveInstanceState(outState);
    }

    private void saveStateData(String chosenColor){
        android.content.SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
        String key = "chosenBackgroundColor",value=inputText.getText().toString();
        myPrefEditor.putString(key,value);
        myPrefEditor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLogAndToast("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLogAndToast("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLogAndToast("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLogAndToast("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLogAndToast("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLogAndToast("onDestroy");
    }

    // Hàm tiện ích để vừa Log vừa Toast cho gọn code
    private void showLogAndToast(String message) {
        Log.d(TAG, "---------- " + message + " ----------");
        // Dùng getApplicationContext() để Toast bền bỉ hơn khi đóng App
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}