package br.edu.ifpe.tads.pt.personaltech;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class activity_Exercicio extends AppCompatActivity {
   private Chronometer cronometro1;
   private boolean running;
   private long pauseOffSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__exercicio);

        cronometro1 = findViewById(R.id.chronometer1);
    }

    public void StartChronometer1(View v){
        if(!running){
            cronometro1.setBase(SystemClock.elapsedRealtime() -pauseOffSet);
            cronometro1.start();
            running=true;
        }

    }

    public void StopChronometer(View v){
        if(running){
            cronometro1.stop();
            pauseOffSet= SystemClock.elapsedRealtime() - cronometro1.getBase();
            running=false;
        }

    }
}
