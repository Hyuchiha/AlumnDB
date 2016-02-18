package com.byhyuchiha.studentdb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.byhyuchiha.studentdb.DB.UserDataSource;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
        if(pref.getBoolean("isLogued", false)){
            sendToNewView();
            finish();
        }
    }

    public void Login(View v){
        TextView usernameTV = (TextView) findViewById(R.id.usernameInput);
        TextView passwordTV = (TextView) findViewById(R.id.passwordInput);

        String username = usernameTV.getText().toString();
        String password = passwordTV.getText().toString();

        UserDataSource user = new UserDataSource(getApplicationContext());
        user.open();
        if(user.hasValidCredentials(username, password)){
            SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isLogued", true);
            editor.apply();
            sendToNewView();
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Usuario no valido", Toast.LENGTH_LONG).show();
            usernameTV.setText("");
            passwordTV.setText("");
        }
        user.close();

    }

    private void sendToNewView(){
        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);
    }

    public void sendToRegisterView(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
