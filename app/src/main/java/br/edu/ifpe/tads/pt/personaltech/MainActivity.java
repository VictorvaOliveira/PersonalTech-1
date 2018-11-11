package br.edu.ifpe.tads.pt.personaltech;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, pswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        //Variáveis utilizadas
        email = (EditText) findViewById(R.id.email);
        pswd = (EditText) findViewById(R.id.password);

    }

    /**
     * Função para autenticação do usuário
     *
     * @param view
     */
    public void autenticacaoUsuario(final View view) {
        String login = email.getText().toString();
        String senha = pswd.getText().toString();

        mAuth.signInWithEmailAndPassword(login, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mudarTela(view);
                        } else {
                            //Caso o Sig In não der certo, mostrar tal mensagem ao usuário
                            String msg = "Erro de login";
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Função para mudança de layout caso o Sig In der certo
     */
    public void mudarTela(View view) {
        Intent it = new Intent(this, FeedMain.class);
        startActivity(it);
    }

}
