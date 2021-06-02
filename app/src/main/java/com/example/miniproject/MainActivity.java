package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView signup,forgot;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.signin);
        signup=findViewById(R.id.newuser);
        forgot=findViewById(R.id.forgotpassword);
        DB=new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuser = DB.checkusername(user);
                    if (checkuser == true) {
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                        if (checkuserpass == true) {
                            Toast.makeText(MainActivity.this, "sign in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "user doesn't exist! please create", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),newuserActivity.class);
               startActivity(intent);
           }
       });

       forgot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(),forgotActivity.class);
               startActivity(intent);
           }
       });

    }
}