package com.example.form;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnSelectDate, btnSignup, btnReset;
    EditText etBirthdate, etRetype, etPassword, etUsername;

    RadioGroup rgGender;

    CheckBox cbTennis, cbFutbal, cbOthers;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSelectDate = (Button) findViewById(R.id.btnSelectDate);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnReset = (Button) findViewById(R.id.btnReset);

        etBirthdate = (EditText) findViewById(R.id.etBirthdate);
        etRetype = (EditText) findViewById(R.id.etRetype);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etUsername);

        rgGender = (RadioGroup) findViewById(R.id.rgGender);

        cbTennis = (CheckBox) findViewById(R.id.cbTennis);
        cbFutbal = (CheckBox) findViewById(R.id.cbFutbal);
        cbOthers = (CheckBox) findViewById(R.id.cbOthers);


        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        btnSelectDate.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnSelectDate.getId()) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year);
                            etBirthdate.setText(selectedDate);
                        }
                    }, year, month, day);
            datePickerDialog.show();
        }
        if (v.getId() == btnSignup.getId()) {
            boolean isValid = true;
            String password = etPassword.getText().toString().trim();
            String retype = etRetype.getText().toString().trim();
            if (!(password.equals(retype))) {
                Toast.makeText(this, "Password is not the same as Re-type password", Toast.LENGTH_SHORT).show();
                isValid = false;
            }

            String birthdate = etBirthdate.getText().toString().trim();
            String datePattern = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
            if (!birthdate.matches(datePattern) || birthdate.isEmpty()) {
                Toast.makeText(this, "Invalid Birthdate field", Toast.LENGTH_SHORT).show();
                isValid = false;
            }

            if (isValid) {
                String userName = etUsername.getText().toString().trim();

                int selectedId = rgGender.getCheckedRadioButtonId();
                String gender = "";
                if (selectedId != -1) {
                    RadioButton rbSelected = findViewById(selectedId);
                    gender = rbSelected.getText().toString();
                }

                StringBuilder hobbies = new StringBuilder();
                if (cbTennis.isChecked()) hobbies.append("Tennis, ");
                if (cbFutbal.isChecked()) hobbies.append("Futbal, ");
                if (cbOthers.isChecked()) hobbies.append("Others, ");

                String hobbiesStr = hobbies.toString();
                if (hobbiesStr.endsWith(", ")) {
                    hobbiesStr = hobbiesStr.substring(0, hobbiesStr.length() - 2);
                }

                Intent myIntentA1A2 = new Intent(MainActivity.this, ResultForm.class);
                Bundle myBundle1 = new Bundle();

                myBundle1.putString("username", userName);
                myBundle1.putString("password", password);
                myBundle1.putString("birthdate", birthdate);
                myBundle1.putString("gender", gender);
                myBundle1.putString("hobbies", hobbiesStr);

                myIntentA1A2.putExtras(myBundle1);
                startActivity(myIntentA1A2);
            }

        }
        if (v.getId() == btnReset.getId()) {
            etUsername.setText("");
            etPassword.setText("");
            etRetype.setText("");
            etBirthdate.setText("dd/mm/yyyy");
            cbOthers.setChecked(false);
            cbTennis.setChecked(false);
            cbFutbal.setChecked(false);

            rgGender.clearCheck();
        }

    }
}