package com.example.duckduck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class temporizador extends AppCompatActivity {

    EditText minutos;
    EditText segundos;
    Button bt_stop;
    Button bt_start;
    CountDownTimer timer;
    int mins;
    int segs;
    AlertDialog.Builder alert;
    private int[] imagenes = {}; //meter los gifs animados aqui
    ImageView img;
    Bundle rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        View rootView = findViewById(android.R.id.content);

        minutos = findViewById(R.id.minsW);
        segundos = findViewById(R.id.segsW);
        bt_stop = findViewById(R.id.stop);
        bt_start = findViewById(R.id.start);
        img = findViewById(R.id.imgs);

        rec = getIntent().getExtras();
        mins = rec.getInt("Mins");
        segs = rec.getInt("Segunds");
        minutos.setText(String.valueOf(mins));
        segundos.setText(String.valueOf(segs));

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // presionó el botón de retroceso
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    showExitConfirmationDialog();
                    return true; // manejado
                }
                return false; //
            }
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
    }
    public void startTimer(){
        long totalTime = (mins*60 +segs)*1000; //pasarlo  milisegundos
        timer = new CountDownTimer(totalTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minsQuedan = (int) (millisUntilFinished / 1000) / 60;
                int segsQuedan = (int) (millisUntilFinished / 1000) % 60;
                minutos.setText(String.format("%02d",minsQuedan));
                segundos.setText(String.format("%02d",segsQuedan));
            }
            @Override
            public void onFinish() {
                minutos.setText("00");
                segundos.setText("00");
            }
        }.start();
    }
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            minutos.setText(String.format("%02d", mins));
            segundos.setText(String.format("%02d", segs));
        }
    }
    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Salir de la aplicación?");
        builder.setMessage("¿Estás seguro que quieres salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
                dialog.dismiss();
                //poner pato muerto
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}






