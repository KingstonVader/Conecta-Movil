package com.example.nacionalfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Model.persona;

public class Mainmenu extends AppCompatActivity {

    EditText nomP, appP,correoP, passwordP;
    ListView lv_datosPersonas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainantivity);




        nomP =findViewById(R.id.txtNombre);
        correoP = findViewById(R.id.txtCorreo);

        lv_datosPersonas = findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         return super. onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nombre =nomP.getText().toString();
        String correo =correoP.getText().toString();
        String password =passwordP.getText().toString();
        String app =appP.getText().toString();

        int itemId = item.getItemId();
        if (itemId == R.id.imageBuscar) {
            if (nombre.equals("")) {
                validacion();
                return true;
            } else {
                persona p = new persona();
                p.setId(UUID.randomUUID().toString());
                p.setNombre(nombre);
                p.setApellido(app);
                p.setCorreo(correo);
                p.setContrasenia(password);

                databaseReference.child("Usuario").child(p.getId()).setValue(p);

                Toast.makeText(this, "-Agregar-", Toast.LENGTH_LONG).show();

            }
            Toast.makeText(this, "-Guardar-", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "-Eliminar-", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.imageBuscar) {
            Toast.makeText(this, "-Guardar-", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "-Eliminar-", Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.imageDelete) {
            Toast.makeText(this, "-Eliminar-", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private void validacion() {
        String nombre = nomP.getText().toString();

        if (nombre.equals("")){
            nomP.setError("Required");


        }
    }
}