package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotActivity extends AppCompatActivity {

    EditText username;
    Button btn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        username=findViewById(R.id.verify);
        btn=findViewById(R.id.forgotbtn);
        DB=new DBHelper(this);

         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 String user=username.getText().toString();
                 Boolean checkuser=DB.checkusername(user);
                 if(checkuser==true){
                     Intent intent=new Intent(getApplicationContext(),resetActivity.class);
                     intent.putExtra("username",user);
                     startActivity(intent);
                 }
                 else{
                     Toast.makeText(forgotActivity.this, "User doesn't exist!", Toast.LENGTH_SHORT).show();
                 }


             }
         });
    }
}