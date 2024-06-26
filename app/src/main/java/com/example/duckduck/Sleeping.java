package com.example.duckduck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class Sleeping extends AppCompatActivity {

    Button start;
    Button stop;
    EditText time;
    Handler handler;
     Runnable runnable;
     long startTime;
     ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleeping);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        time = findViewById(R.id.time);
        gif = findViewById(R.id.gif);
        handler = new Handler();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        Glide.with(this)
                .asGif()
                .load(R.drawable.patomuertovi)
                .into(gif);
    }
    private void startTimer() {
        startTime = System.currentTimeMillis();
        runnable = new Runnable() {
            @Override
            public void run() {
                long elapsedMillis = System.currentTimeMillis() - startTime;
                int seconds = (int) (elapsedMillis / 1000);
                int minutes = seconds / 60;
                int hours = minutes / 60;
                seconds = seconds % 60;
                minutes = minutes % 60;

                time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    private void stopTimer() {
        handler.removeCallbacks(runnable);
    }

    public void volverMenu(View view){
        Intent intent = new Intent(Sleeping.this, menu.class);
        startActivity(intent);
        finish();
    }
}