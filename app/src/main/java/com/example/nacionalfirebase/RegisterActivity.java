package com.example.nacionalfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    EditText txtUserR, txtNameR, txtApellidoR, txtEmailR, txtPassR, txtPassRC, txtAgreeR;
    DatabaseReference referencia;

    private Button BtnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        txtEmailR = findViewById(R.id.txtEmailR);
        txtUserR = findViewById(R.id.txtUserR);
        txtNameR = findViewById(R.id.txtNameR);
        txtApellidoR = findViewById(R.id.txtApellidoR);
        txtEmailR = findViewById(R.id.txtEmailR);
        txtPassR = findViewById(R.id.txtPassR);
        BtnRegistrar = findViewById(R.id.buttonSignR);

        BtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUserR.getText().toString();
                String name = txtNameR.getText().toString();
                String apellido = txtApellidoR.getText().toString();
                String email = txtEmailR.getText().toString();
                String pass = txtPassR.getText().toString();


                if (user.isEmpty() || name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {


                                    Users users = new Users(user, name, apellido, email, pass);
                                    referencia.child(auth.getCurrentUser().getUid()).setValue(users);
                                    Intent intent = new Intent(RegisterActivity.this, AuthActivity.class);

                                    Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(RegisterActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                    Log.e("RegisterActivity", "Error al registrar el usuario", task.getException());
                                }
                            }
                        });
            }
        });
    }
}
