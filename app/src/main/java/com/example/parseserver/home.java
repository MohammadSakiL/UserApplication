package com.example.parseserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.ParseUser;

public class home extends AppCompatActivity {
    private TextView userName,userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ParseUser currentUser = ParseUser.getCurrentUser();

        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);

        if(currentUser != null){
            userName.setText(currentUser.getString("name"));
            userEmail.setText(currentUser.getEmail());
        }
    }

    public void Logout(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("loading....");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(home.this,MainActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();

    }
}