package com.example.passwordstrengthchecker;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etPassword;
    private ProgressBar pbStrength;
    private TextView tvFeedback;
    private CheckBox cbLength, cbUpperCase, cbNumber, cbSpecialChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassword = findViewById(R.id.etPassword);
        pbStrength = findViewById(R.id.pbStrength);
        tvFeedback = findViewById(R.id.tvFeedback);
        cbLength = findViewById(R.id.cbLength);
        cbUpperCase = findViewById(R.id.cbUpperCase);
        cbNumber = findViewById(R.id.cbNumber);
        cbSpecialChar = findViewById(R.id.cbSpecialChar);

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkPasswordStrength(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void checkPasswordStrength(String password) {
        int strength = 0;

        // Check length
        if (password.length() >= 8) {
            cbLength.setChecked(true);
            strength += 25;
        } else {
            cbLength.setChecked(false);
        }

        // Check for uppercase letters
        if (password.matches(".*[A-Z].*")) {
            cbUpperCase.setChecked(true);
            strength += 25;
        } else {
            cbUpperCase.setChecked(false);
        }

        // Check for numbers
        if (password.matches(".*\\d.*")) {
            cbNumber.setChecked(true);
            strength += 25;
        } else {
            cbNumber.setChecked(false);
        }

        // Check for special characters
        if (password.matches(".*[@#$%^&+=!].*")) {
            cbSpecialChar.setChecked(true);
            strength += 25;
        } else {
            cbSpecialChar.setChecked(false);
        }

        // Update progress bar and feedback
        pbStrength.setProgress(strength);
        if (strength < 50) {
            tvFeedback.setText("Weak Password");
            tvFeedback.setTextColor(Color.RED);
            tvFeedback.setTypeface(null, Typeface.BOLD);
            pbStrength.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (strength < 75) {
            tvFeedback.setText("Moderate Password");
            tvFeedback.setTextColor(getResources().getColor(R.color.dark_yellow));
            tvFeedback.setTypeface(null, Typeface.BOLD);
            pbStrength.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFA500")));
        } else {
            tvFeedback.setText("Strong Password");
            tvFeedback.setTextColor(Color.GREEN);
            tvFeedback.setTypeface(null, Typeface.BOLD);
            pbStrength.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
    }
}
