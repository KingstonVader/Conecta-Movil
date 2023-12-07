package com.example.nacionalfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {



    private FirebaseAuth auth;

    EditText Email, Contrasena, Usuario;
    private Button BtnRegistrar, BtnAcceder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);




        auth = FirebaseAuth.getInstance();
        BtnRegistrar = findViewById(R.id.BtnRegister);

        BtnAcceder = findViewById(R.id.BtnAcceder);

        BtnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, Mainmenu.class);
                startActivity(intent);
            }
        });

        BtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}






