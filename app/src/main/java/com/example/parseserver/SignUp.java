package com.example.parseserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText edtName,edtEmail,edtPassword,edtConfirmPassword;
    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        signupButton = findViewById(R.id.signupButton);
    }

    public void Signup(View view) {
        if( TextUtils.isEmpty(edtName.getText())) {
            edtName.setError("Name is required!");
        }
        else if( TextUtils.isEmpty(edtEmail.getText())) {
            edtEmail.setError( "Email is required!" );
        }
        else if( TextUtils.isEmpty(edtPassword.getText())) {
            edtPassword.setError( "Password is required!" );
        }
        else if( TextUtils.isEmpty(edtConfirmPassword.getText())) {
            edtConfirmPassword.setError( "Password is required!" );
        }
        else if(!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){
            FancyToast.makeText(SignUp.this,"Passwords are not same",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
        }else{
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("loading....");
            progress.show();

            ParseUser user = new ParseUser();
            user.setUsername( edtName.getText().toString().trim());
            user.setEmail( edtEmail.getText().toString().trim());
            user.setPassword( edtPassword.getText().toString().trim());
            user.put("name",edtName.getText().toString().trim());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        progress.dismiss();
                        FancyToast.makeText(SignUp.this,"Welcome !",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        Intent intent = new Intent(SignUp.this,home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        ParseUser.logOut();
                        FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }
                }
            });
        }


    }

}