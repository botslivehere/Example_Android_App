package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.User;
import com.example.myapplication.db.UserDao;


public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText mail,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> OnClick());

    }
    protected void OnClick(){
        mail = findViewById(R.id.editTextTextEmailAddress);
        pass = findViewById(R.id.editTextTextPassword);

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            UserDao userDao = db.userDao();
            String loginToCheck = mail.getText().toString();
            String passwordToCheck = pass.getText().toString();
            final User authenticatedUser = userDao.authenticate(loginToCheck, passwordToCheck);
            runOnUiThread(() -> {
                if (authenticatedUser != null) {
                    mail.setTextColor(getResources().getColor(R.color.black, null));
                    pass.setTextColor(getResources().getColor(R.color.black, null));
                    Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("User_id",authenticatedUser.id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    mail.setTextColor(getResources().getColor(R.color.red, null));
                    pass.setTextColor(getResources().getColor(R.color.red, null));
                }
            });
        }).start();
    }
}