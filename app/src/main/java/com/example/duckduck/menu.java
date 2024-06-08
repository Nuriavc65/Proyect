package com.example.duckduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }
    public void ir2 (View v){
        Intent intent = new Intent(menu.this, Work.class);
        startActivity(intent);
        finish();
    }
    public void volver (View v){
        Intent intent = new Intent(menu.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void irPerfil(View v){
        Intent intent = new Intent(menu.this,Perfil.class);
        startActivity(intent);
        finish();
    }
}