package com.byhyuchiha.studentdb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.byhyuchiha.studentdb.DB.UserDataSource;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void ingresa(View view){
        EditText usernameText = (EditText) findViewById(R.id.usernameRegister);
        EditText passwordTex= (EditText) findViewById(R.id.passwordRegister);
        UserDataSource data = new UserDataSource(getApplicationContext());
        data.open();
        data.insertUser(usernameText.getText().toString(), passwordTex.getText().toString());
        data.close();
        putPreferences(usernameText.getText().toString(), passwordTex.getText().toString());
    }

    private void putPreferences(String username, String password){
        SharedPreferences pref = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogued", true);
        editor.apply();
        sendToNewView();
        finish();
    }

    private void sendToNewView(){
        Intent intent = new Intent(this, StudentDetails.class);
        startActivity(intent);
    }
}
