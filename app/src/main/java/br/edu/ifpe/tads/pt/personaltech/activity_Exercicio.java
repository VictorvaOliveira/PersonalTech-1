package br.edu.ifpe.tads.pt.personaltech;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class activity_Exercicio extends AppCompatActivity {
   private Chronometer cronometro1;
   private boolean running;
   private long pauseOffSet;
   public String nome,descricao,tipo,nivel, imagem;
   TextView titulo, desc;
   ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__exercicio);
         Intent intent= getIntent();
         nome =intent.getStringExtra("exercicioNome");
         descricao = intent.getStringExtra("exercicioDescricao");
         tipo = intent.getStringExtra("exercicioTipo");
        nivel = intent.getStringExtra("exercicioNivel");
        imagem = intent.getStringExtra("exercicioImagem");

        titulo=(TextView)findViewById(R.id.Title_Exercicio);
        desc=(TextView)findViewById(R.id.Descricao_Exercicio);
        image = (ImageView)findViewById(R.id.imagemExercicio);

        titulo.setText(nome);
        desc.setText(descricao);
        Picasso.with(this).load(imagem).into(image);
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
