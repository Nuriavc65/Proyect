package com.example.duckduck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class temporizador extends AppCompatActivity {

    TextView text;
    EditText minutos;
    EditText segundos;
    Button bt_stop;
    Button bt_start;
    CountDownTimer timer;
    int mins;
    int segs;
    AlertDialog.Builder alert;
    private int[] imagenes = {R.drawable.patojovenvi,R.drawable.patojuniorvi,R.drawable.patoenfermovi};
    ImageView img;
    Bundle rec;

    //aviso de + de dos mins
    long stopTime;
    long dosMinutos = 2*60*1000; //2 mins milisegundos
    Handler handler = new Handler();
    Runnable CheckStoppedTimeRunnable;

    boolean isActivityDestroyed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        text = findViewById(R.id.text);
        minutos = findViewById(R.id.minsW);
        segundos = findViewById(R.id.segsW);
        bt_stop = findViewById(R.id.stop);
        bt_start = findViewById(R.id.start);
        img = findViewById(R.id.imgs);

        rec = getIntent().getExtras();
        mins = rec.getInt("Mins");
        segs = rec.getInt("Segunds");
        img.setImageResource(R.drawable.patojovenvi);
        minutos.setText(String.valueOf(mins));
        segundos.setText(String.valueOf(segs));


        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

    }

    public void startTimer(){
        long totalTime = (mins*60 +segs)*1000; //pasarlo  milisegundos
        final long duracionNivel =  totalTime/3;
        timer = new CountDownTimer(totalTime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minsQuedan = (int) (millisUntilFinished / 1000) / 60;
                int segsQuedan = (int) (millisUntilFinished / 1000) % 60;
                minutos.setText(String.format("%02d",minsQuedan));
                segundos.setText(String.format("%02d",segsQuedan));
                text.setText("");

                if (!isActivityDestroyed) {
                    if (millisUntilFinished > 2 * duracionNivel) {
                        Glide.with(temporizador.this)
                                .asGif()
                                .load(imagenes[0])
                                .into(img); // Primer nivel
                        text.setText("Kepp going!");
                    } else if (millisUntilFinished > duracionNivel) {
                        Glide.with(temporizador.this)
                                .asGif()
                                .load(imagenes[1])
                                .into(img); // Segundo nivel
                        text.setText("You can do it!");
                    } else {
                        Glide.with(temporizador.this)
                                .asGif()
                                .load(imagenes[2])
                                .into(img); // Tercer nivel
                        text.setText("Almost finish");
                    }
                }

            }
            @Override
            public void onFinish() {
                minutos.setText("00");
                segundos.setText("00");
                showTimeFinishedAlertDialog();
            }
        }.start();
    }
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            stopTime = System.currentTimeMillis();
            startCheckStoppedTimeRunnable();
        }
    }

    private void startCheckStoppedTimeRunnable(){
        if (CheckStoppedTimeRunnable == null) {
            CheckStoppedTimeRunnable = new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - stopTime >= dosMinutos) {
                        showAlertDialog();
                        img.setImageResource(R.drawable.patomuerto);
                    } else {
                        handler.postDelayed(this, 1000); // Verificar cada segundo
                    }
                }
            };
        }
        handler.post(CheckStoppedTimeRunnable);
    }

    private void showTimeFinishedAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(temporizador.this);
        builder.setTitle("Tiempo terminado");
        builder.setMessage("El temporizador ha llegado a cero.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(temporizador.this);
        builder.setTitle("Aviso");
        builder.setMessage("El temporizador ha estado detenido por más de dos minutos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActivityDestroyed = true;
        if (handler != null && CheckStoppedTimeRunnable != null) {
            handler.removeCallbacks(CheckStoppedTimeRunnable);
        }
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Salir de la aplicación?");
        builder.setMessage("¿Estás seguro que quieres salir?Todo tu progreso contará como cero");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
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
    public void Back(View v){
        showExitConfirmationDialog();
    }


}






