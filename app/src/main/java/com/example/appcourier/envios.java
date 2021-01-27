package com.example.appcourier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class envios extends AppCompatActivity {

    TextView tvpedidos;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvpedidos=findViewById(R.id.tvpedidos);
    }

    public void cerrarSesion(View view) {
        Intent inte5 = new Intent (this, MainActivity.class);
        startActivity(inte5);
    }

    public void principal(View view) {
        Intent inte6 = new Intent (this,principal.class);
        startActivity(inte6);
    }

    public void verSolicitudes(View view) {
        DocumentReference docRef = db.collection("usuarios").document("LA");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("courier", "Datos: " + document.getData());
                    } else {
                        Log.d("courier", "No se encontraron datos");
                    }
                } else {
                    Log.d("courier", "Obtencion de datos fallida", task.getException());
                }
            }
        });
    }

}