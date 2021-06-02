package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class resetActivity extends AppCompatActivity {


    TextView username;
    EditText pass,repass;
    Button reset;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reset);


        username=findViewById(R.id.username_reset);
        pass=findViewById(R.id.reset_password);
        repass=findViewById(R.id.reset_confirm);
        reset=findViewById(R.id.reset);
        DB=new DBHelper(this);

        Intent intent=getIntent();
        username.setText(intent.getStringExtra("username"));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if (password.equals(repassword)) {

                    Boolean checkpasswordupdate = DB.updatepassword(user, password);
                    if (checkpasswordupdate == true) {
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(resetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(resetActivity.this, "Password not Updated", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(resetActivity.this, "passwords not matched", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}