package com.example.form;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultForm extends Activity {
    TextView tvUsername, tvPassword, tvBirthdate, tvGender, tvHobbies;
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultform);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvBirthdate = (TextView) findViewById(R.id.tvBirthdate);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvHobbies = (TextView) findViewById(R.id.tvHobbies);
        btnExit = (Button) findViewById(R.id.btnExit);

        Intent myCallerIntent = getIntent();
        Bundle myBundle = myCallerIntent.getExtras();

        if (myBundle != null) {
            String username = myBundle.getString("username");
            String password = myBundle.getString("password");
            String birthdate = myBundle.getString("birthdate");
            String gender = myBundle.getString("gender");
            String hobbies = myBundle.getString("hobbies");

            tvUsername.setText(username);

            if (password != null) {
                String maskedPassword = "";
                for (int i = 0; i < password.length(); i++) {
                    maskedPassword += "*";
                }
                tvPassword.setText(maskedPassword);
            }

            tvBirthdate.setText(birthdate);
            tvGender.setText(gender);
            tvHobbies.setText(hobbies);
        }

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}