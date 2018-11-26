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
    EditText emailPerfil;
    EditText telefonePerfil;

    private List<Aluno> listAluno = new ArrayList<>();
    private ArrayAdapter<Aluno> arrayAdapteraluno;

    FirebaseDatabase database;
    private DatabaseReference databaseReference;

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

        dadosUsuario();

    }

    private void dadosUsuario() {
        databaseReference.child("aluno01").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAluno.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot obj : dataSnapshot.getChildren()) {
                        Aluno aluno = dataSnapshot.getValue(Aluno.class);
//                                if (aluno.getEmail() == "vvao@a.recife.ifpe.edu.br"){
                        System.out.println("DataSnapShot Perfil:" + dataSnapshot);
                        listAluno.add(aluno);
                    }
                }
//                        }
                preencherEditText(listAluno.get(0).getNome(), listAluno.get(0).getEmail(),
                listAluno.get(0).getTelefone());
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
        emailPerfil = (EditText) findViewById(R.id.emailPerfil);
        telefonePerfil = (EditText) findViewById(R.id.telefonePerfil);

        nomePerfil.setText(nome);
        emailPerfil.setText(email);
        telefonePerfil.setText(String.valueOf(telefone));
    }

    public void mudarTelaPerfil(View view) {
        Intent it = new Intent(this, FeedMain.class);
        startActivity(it);
    }
}
