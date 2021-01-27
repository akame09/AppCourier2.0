package com.example.appcourier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void salir(View view) {
        Intent inte4 = new Intent (this,MainActivity.class);
        startActivity(inte4);
    }

    public void verEnvios(View view) {
        Intent inte9 = new Intent (this,envios.class);
        startActivity(inte9);
    }

}
