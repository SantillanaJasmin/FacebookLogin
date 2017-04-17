package com.example.jasmin.facebooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());                                         //initialize SDK to be able to use its methods
        callbackManager = CallbackManager.Factory.create();                                         //initialize Callbackmanager

        setContentView(R.layout.activity_main);
        btnLogin = (LoginButton) findViewById(R.id.login_button);                                   //set handler for login button

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {            //handles login responses
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getBaseContext(), loginResult.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getBaseContext(), "You cancelled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getBaseContext(), exception.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
