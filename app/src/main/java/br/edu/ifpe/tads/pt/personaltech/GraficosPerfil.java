package br.edu.ifpe.tads.pt.personaltech;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.tads.pt.personaltech.model.Acompanhamento;

public class GraficosPerfil extends AppCompatActivity {

    private FirebaseUser user;
    DatabaseReference databaseReference;

    TextView pesoPerfil;
    TextView alturaPerfil;
    TextView dataAvaliacaoPerfil;

    GraphView graph;
    BarGraphSeries series;
    private List<Acompanhamento> listAcompanhamento = new ArrayList<>();
    private ArrayAdapter<Acompanhamento> arrayAdapteracompanhamento;

    int tamanhoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos_perfil);

////        Pegando referencia do database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Acompanhamento");
        databaseReference.keepSynced(true);

        graph = (GraphView) findViewById(R.id.graph);
        series = new BarGraphSeries();
        graph.addSeries(series);
        user = FirebaseAuth.getInstance().getCurrentUser();
        eventoDatabase();
    }

    public void eventoDatabase() {
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index = 0;
                for (DataSnapshot obj : dataSnapshot.getChildren()) {
                    Acompanhamento acomp = obj.getValue(Acompanhamento.class);
                    System.out.println("Acompanhamento:" + acomp.getPeso());

                    dp[index] = new DataPoint(index, acomp.getPeso());
                    listAcompanhamento.add(acomp);
                    index++;
                }

                tamanhoLista = listAcompanhamento.size() - 1;

                preencherCardViewPerfil(
                        listAcompanhamento.get(tamanhoLista).getPeso(),
                        listAcompanhamento.get(tamanhoLista).getData(),
                        listAcompanhamento.get(tamanhoLista).getAltura());

                series.resetData(dp);
                series.setSpacing(10);
                graph.getViewport().setScrollable(true); // enables horizontal scrolling
                graph.getViewport().setScrollableY(true); // enables vertical scrolling
                graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
                graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling
                arrayAdapteracompanhamento = new ArrayAdapter<Acompanhamento>(GraficosPerfil.this,
                        android.R.layout.simple_list_item_1, listAcompanhamento);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void preencherCardViewPerfil(int peso, String data, int altura){

        pesoPerfil = (TextView) findViewById(R.id.pesoPerfil);
        alturaPerfil = (TextView) findViewById(R.id.alturaPerfil);
        dataAvaliacaoPerfil = (TextView) findViewById(R.id.dataAvaliacaoPerfil);

        pesoPerfil.setText(String.valueOf(peso));
        alturaPerfil.setText(data);
        dataAvaliacaoPerfil.setText(String.valueOf(altura));
    }
}
