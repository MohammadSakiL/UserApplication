package com.example.parseserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.parseserver.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
    private EditText edtUserEmail, edtUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtUserPassword = findViewById(R.id.edtUserPassword);
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, home.class);
            startActivity(intent);
            finish();
        }

    }

    public void Login(View view) {
        if (TextUtils.isEmpty(edtUserEmail.getText())) {
            edtUserEmail.setError("Email is required!");
        } else if (TextUtils.isEmpty(edtUserPassword.getText())) {
            edtUserPassword.setError("Password is required!");
        } else {
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("loading....");
            progress.show();
            ParseUser.logInInBackground(edtUserEmail.getText().toString(), edtUserPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (user != null) {
                        progress.dismiss();
                        FancyToast.makeText(MainActivity.this, "Welcome Back !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        Intent intent = new Intent(MainActivity.this, home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }


            });

        }



    }

    public void SignUp(View view) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }

    public void forgetPassword(View view) {
        Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
        startActivity(intent);
    }
}
