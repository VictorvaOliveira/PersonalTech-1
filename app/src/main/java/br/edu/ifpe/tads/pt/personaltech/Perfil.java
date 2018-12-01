package br.edu.ifpe.tads.pt.personaltech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.tads.pt.personaltech.model.Aluno;

public class Perfil extends AppCompatActivity {

    SharedPreferences prefs;
    String emailLogin;

    TextView nomePerfil;
    TextView emailPerfil;
    TextView telefonePerfil;

    private List<Aluno> listAluno = new ArrayList<>();
    private ArrayAdapter<Aluno> arrayAdapteraluno;

    FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        prefs = getSharedPreferences("EMAIL_LOGIN", MODE_PRIVATE);
        emailLogin = prefs.getString("emailUsuario", "");

        databaseReference = database.getInstance().getReference().child("Aluno");

        prefs = getSharedPreferences("EMAIL_LOGIN", MODE_PRIVATE);
        emailLogin = prefs.getString("emailUsuario", "");
        user = FirebaseAuth.getInstance().getCurrentUser();
        dadosUsuario();

    }

    private void dadosUsuario() {
        databaseReference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Aluno aluno = dataSnapshot.getValue(Aluno.class);
//                        }
                preencherEditText(aluno.getNome(),aluno.getEmail(),
                aluno.getTelefone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error on method dadosUsuario:" + databaseError);
            }
        });
    }

    public void atualizarCampos() {
        Toast.makeText(this, "Funcionalidade em construção", Toast.LENGTH_LONG);
    }

    public void preencherEditText(String nome, String email, int telefone) {
        nomePerfil = (TextView) findViewById(R.id.nomePerfil);
        emailPerfil = (TextView) findViewById(R.id.emailPerfil);
        telefonePerfil = (TextView) findViewById(R.id.telefonePerfil);

        nomePerfil.setText(nome);
        emailPerfil.setText(email);
        telefonePerfil.setText(String.valueOf(telefone));
    }

    public void mudarTelaPerfil(View view) {
        Intent it = new Intent(this, FeedMain.class);
        startActivity(it);
    }
}
