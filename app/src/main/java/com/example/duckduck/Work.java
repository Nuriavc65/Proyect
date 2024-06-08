package com.example.duckduck;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Locale;

public class Work extends AppCompatActivity {

    EditText mins;
    EditText segs;
    int  minutos;
    int segundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        mins = findViewById(R.id.mins);
        segs = findViewById(R.id.segs);

        String m = mins.getText().toString();
        Log.i("mins", String.valueOf(m));
        String s = segs.getText().toString();
        minutos = Integer.parseInt(m);
        segundos = Integer.parseInt(s);
        Log.i("mins", String.valueOf(minutos));

    }


    public void empezar_actividad(View v){
        String m = mins.getText().toString();
        String s = segs.getText().toString();
        minutos = Integer.parseInt(m);
        segundos = Integer.parseInt(s);

        Bundle enviarDatos = new Bundle();
        enviarDatos.putInt("Mins",minutos);
        enviarDatos.putInt("Segunds",segundos);

        Intent n = new Intent(Work.this, temporizador.class);
        n.putExtras(enviarDatos);
        startActivity(n);

    }
}