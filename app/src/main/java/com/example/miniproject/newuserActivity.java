package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class newuserActivity extends AppCompatActivity {
    EditText  username,fullname,phone,gender,password,repassword;
    Button create;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);

        username=findViewById(R.id.username);
        fullname=findViewById(R.id.fullname);
        phone=findViewById(R.id.number);
        gender=findViewById(R.id.gender);
        password=findViewById(R.id.password);
        repassword=findViewById(R.id.repassword);
        create=findViewById(R.id.create);
        DB=new DBHelper(this);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String name = fullname.getText().toString();
                String numbe = phone.getText().toString();
                String gende = gender.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();



                if (user.equals("") || name.equals("") || numbe.equals("") || gende.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(newuserActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check1 = vemail(user);
                    if (check1 == true) {
                        Boolean check = vnum(numbe);
                        if (check == true) {

                            if (pass.equals(repass)) {
                                Boolean checkuser = DB.checkusername(user);
                                if (checkuser == false) {
                                    Boolean insert = DB.insertData(user, name, numbe, gende, pass);
                                    if (insert == true) {
                                        Toast.makeText(newuserActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(newuserActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(newuserActivity.this, "user already exists! please sign in", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(newuserActivity.this, "passwords not matching", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(newuserActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(newuserActivity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });


    }

    private Boolean vemail(String user) {
        if(Patterns.EMAIL_ADDRESS.matcher(user).matches())
            return true;
        else
            return false;
    }

    private Boolean vnum(String numbe) {
        Pattern p=Pattern.compile("[6-9][0-9]{9}") ;
        Matcher m=p.matcher(numbe);
        return m.matches();
    }


}