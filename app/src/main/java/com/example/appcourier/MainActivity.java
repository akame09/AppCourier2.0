package com.example.appcourier;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    EditText etValiCod, etValiPass;
    String codi, pass;
    Button btSesionStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValiCod=findViewById(R.id.etValiCod);
        etValiPass=findViewById(R.id.etValiPass);
        btSesionStart = findViewById(R.id.btSesionStart);
    }

    public void Registrarme(View view) {
        Intent inte = new Intent (this,registrarse.class);
        startActivity(inte);
    }

    private void ValidacionIniciarSesion() {
        String codigo=etValiCod.getText().toString();
        String password=etValiPass.getText().toString();

        if (codigo.equals("")){
            etValiCod.setError("Required");
        }
        if (password.equals("")){
            etValiPass.setError("Required");
        }
    }

    public void iniciarSesion(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String codigo = etValiCod.getText().toString();
        String password = etValiPass.getText().toString();

        if (codigo.equals("") || password.equals("")){
            ValidacionIniciarSesion();
        }else {
            db.collection("Usuario").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot document: task.getResult()){
                            codi = (String) document.getData().get("Codigo");
                            pass = (String) document.getData().get("Password");
                        }
                    }else{
                        Log.e("courier", "Error", task.getException());
                    }
                }
            });
        }
        if (etValiCod.equals(codi) && etValiPass.equals(pass)){
            Intent inte3 = new Intent (this,principal.class);
            startActivity(inte3);
        }else{
            Toast.makeText(this, "Datos Ingresados Incorrectos", Toast.LENGTH_LONG).show();
        }
    }

}