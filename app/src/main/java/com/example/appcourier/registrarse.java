package com.example.appcourier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class registrarse extends AppCompatActivity {

    EditText etcod, etpass, etnombre, etapellidos;
    Button btregistro, btvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etnombre = findViewById(R.id.etnombre);
        etapellidos = findViewById(R.id.etapellidos);
        etcod = findViewById(R.id.etcod);
        etpass = findViewById(R.id.etpass);

    }

    public void anterior(View view) {
        Intent inte2 = new Intent (this,MainActivity.class);
        startActivity(inte2);
    }

    public void registrar(View view) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("codigo", etcod.getText().toString());
        usuarios.put("nombre", etnombre.getText().toString());
        usuarios.put("apellido", etapellidos.getText().toString());
        usuarios.put("password", etpass.getText().toString());

        db.collection("usuarios").document("LA")
                .set(usuarios)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("courier", "Se agregó correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("courier", "Fallo al agregar", e);
                    }
                });
    }
}