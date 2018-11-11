package br.edu.ifpe.tads.pt.personaltech;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {


    SharedPreferences prefs;
    String emailLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        prefs = getSharedPreferences("EMAIL_LOGIN", MODE_PRIVATE);
        emailLogin = prefs.getString("emailUsuario", "");
    }

    public void mostrarEmail(View view){
        if (emailLogin != null){
            String msg = "Email: " + emailLogin;
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        }else {
            String msg = "Não há nenhum dado salvo";
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        }
    }
}
