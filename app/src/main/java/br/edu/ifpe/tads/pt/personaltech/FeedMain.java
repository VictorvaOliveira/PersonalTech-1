package br.edu.ifpe.tads.pt.personaltech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.tads.pt.personaltech.model.Exercicio;

import br.edu.ifpe.tads.pt.personaltech.model.Aluno;

public class FeedMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // Criei uma LISTVIEW  aleatória por causa da videoaula, se não usar, eu comentarei.
    //ListView listExercicio;
    //TextView nivel, titulo, tipo;
    FirebaseDatabase firebaseDatabase ;
    private List<Exercicio> listExercicio = new ArrayList<Exercicio>();
    private ArrayAdapter<Exercicio> arrayAdapterExercicio;
    private DatabaseReference mDatabase;
    //    Inicializando campos
    TextView nomeUsuario;
    TextView emailUsuario;
    //    Recuperando dados do sharedPreferences
    SharedPreferences prefs;
    String emailLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ABAIXO SERÁ A INDEX + VARIAVEL (MARCOS // LEMBRAR QUE É MEU)
           //nivel = (TextView)findViewById(R.id.NivelExercicio);
          // titulo = (TextView)findViewById(R.id.AvisoExercicio);
          // tipo = (TextView)findViewById(R.id.TipoExercicio);

           inicializarFirebase();
           eventoDatabase();
           // FIM DO CÓDIGO DE MARCOS ////


//        NÃO MEXER DE FORMA ALGUMA NESSE COMENTÁRIO
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //      Recuperando email do usuário logado
        prefs = getSharedPreferences("EMAIL_LOGIN", MODE_PRIVATE);
        emailLogin = prefs.getString("emailUsuario", "");
        //      Funções de preenchimento de dados no Feed
        inicializarFirebase();
        dadosUsuario(emailLogin);
    }

    private void eventoDatabase() {
       mDatabase.child("Exercicio").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          listExercicio.clear();

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(FeedMain.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
    }
              /*ABAIXO, METODO DE CREATE DATABASSE (PARA ESTUDO)
               *
                *
                * public boolean createExercicio(){
                * Exercicio e = new Exercicio();
                * e.setTitulo("Fulaninho");
                * e.setTipo("Perna");
                * e.setNivel("Facil");
                * mDatabase.child("Exercicio").child(e.getID).setvalue(e);
                * return true;
                *
                * }
                * */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            Intent it = new Intent(this, Perfil.class);
            startActivity(it);
        } else if (id == R.id.nav_configuracao) {
            Intent it = new Intent(this, Configuracao.class);
            startActivity(it);
        } else if (id == R.id.nav_logout) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                mAuth.signOut();
                this.finish();
                Intent it = new Intent(this, MainActivity.class);
                startActivity(it);
            } else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void irExercicio(View view) {
        Intent it = new Intent(this, activity_Exercicio.class);
        startActivity(it);
    }

    public void irGrafico(View view) {
        Intent it = new Intent(this, GraficosPerfil.class);
        startActivity(it);
    }

    private void dadosUsuario(String emailLogin) {
        Query consultarUsuario;
        consultarUsuario = mDatabase.child("Aluno").child("aluno01").child("email")
                .equalTo("vvao@a.recife.ifpe.edu.br");

        consultarUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    Aluno aluno = dataSnapshot.getValue(Aluno.class);
                    preencherNavHeader(aluno.getNome(), aluno.getEmail());
                    System.out.println("Object User: Email:" + aluno.getEmail());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void preencherNavHeader(String nome, String email) {

        nomeUsuario = (TextView) findViewById(R.id.nomeUsuarioFeed);
        emailUsuario = (TextView) findViewById(R.id.emailUsuarioFeed);

        nomeUsuario.setText(nome);
        emailUsuario.setText(email);
    }

    ;
}
