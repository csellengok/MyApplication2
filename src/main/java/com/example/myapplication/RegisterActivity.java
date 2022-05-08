package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = RegisterActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordagainEditText;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        int secret_key =  getIntent().getIntExtra("SECRET_KEY",0);

        usernameEditText = findViewById(R.id.userNameEditText);
        emailEditText = findViewById(R.id.useremailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordagainEditText = findViewById(R.id.passwordAgain);
        preferences = getSharedPreferences(PREF_KEY,MODE_PRIVATE);
        String username = preferences.getString("username","");
        String password = preferences.getString("password","");

        usernameEditText.setText(username);
        passwordEditText.setText(password);

        mAuth = FirebaseAuth.getInstance();

        Log.i(LOG_TAG, "OnCreate");
    }

    public void register(View view) {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().  toString();
        String password = passwordEditText.getText().toString();
        String passwordagain = passwordagainEditText.getText().toString();

        if (!password.equals(passwordagain)){
            Log.e(LOG_TAG,"Nem jo jelszo erosites");
            return;
        }

        Log.i(LOG_TAG,"Regisztralt: "+username+" ,jelszo: "+password);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(LOG_TAG,"sikeres felhasznalo regisztralas");
                    startlooking();
                }
                else{
                    Log.d(LOG_TAG, "sikertelen regisztralas");
                }
            }
        });

    }

    public void cancel(View view) {
        finish();
    }

    private void startlooking(/* registered user data */){
        Intent intent = new Intent(this, SzallasokActivity.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }
}