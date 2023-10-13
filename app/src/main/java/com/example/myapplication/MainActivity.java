package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText mail,pass;
    String[] emails,passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> OnClick());

    }
    protected void OnClick(){
        boolean ok = false;
        //current
        mail = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);
        //all
        emails = getResources().getStringArray(R.array.emails);
        passwords = getResources().getStringArray(R.array.password);
        for(int i=0;emails.length>i & passwords.length > i;i++){
            if(mail.getText().toString().equals(emails[i]) && pass.getText().toString().equals(passwords[i])){
                ok=true;
            }
        }
        if (!ok) {
           mail.setTextColor(getResources().getColor(R.color.red, null));
           pass.setTextColor(getResources().getColor(R.color.red, null));
       } else {
            mail.setTextColor(getResources().getColor(R.color.black, null));
            pass.setTextColor(getResources().getColor(R.color.black, null));
           Intent intent = new Intent(MainActivity.this,MainActivity2.class);
           startActivity(intent);
       }
    }
}