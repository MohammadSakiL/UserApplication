package com.example.parseserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class ForgetPassword extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        resetEmail = findViewById(R.id.resetEmail);
        resetButton = findViewById(R.id.resetButton);

    }

    public void resetPassword(View view) {
        if (TextUtils.isEmpty(resetEmail.getText())) {
            resetEmail.setError("Email is required!");
        } else
            ParseUser.requestPasswordResetInBackground(resetEmail.getText().toString(), new RequestPasswordResetCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(ForgetPassword.this, "A email is successfully sent with reset instruction", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                    } else {
                        FancyToast.makeText(ForgetPassword.this, "Something went wrong", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
    }
}