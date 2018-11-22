package br.edu.ifpe.tads.pt.personaltech;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.tads.pt.personaltech.model.Acompanhamento;

public class GraficosPerfil extends AppCompatActivity {

    GraphView graph;
    WebView wvGrafico;

    DatabaseReference databaseReference;

    String urlGrafico;

    private List<Acompanhamento> listAcompanhamento = new ArrayList<>();
    private ArrayAdapter<Acompanhamento> arrayAdapteracompanhamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos_perfil);

        urlGrafico = "http://chart.apis.google.com/chart?" +
                "cht=bvg&chs=550x400&chd=t:100,50,115,80|10,20,15,30" +
                "&chxt=x,y" +
                "&chxl=1:|Janeiro|Fevereiro|Marco|Abril" +
                "&chxr=0,0,120&chds=0,120" +
                "&chco=4D89F9" +
                "&chbh=35,0,15" +
                "&chg=8.33,0,5,0" +
                "&chco=0A8C8A,EBB671" +
                "&chdl=Vendas|Compras";
//        graph = (GraphView) findViewById(R.id.graph);
//        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
//                new DataPoint(0, -1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        graph.addSeries(series);
//        // styling
//        series.setValueDependentColor(new ValueDependentColor<DataPoint>()
//
//        {
//            @Override
//            public int get(DataPoint data) {
//                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
//            }
//        });
//        series.setSpacing(50);
////        draw values on top
//        series.setDrawValuesOnTop(true);
//        series.setValuesOnTopColor(Color.RED);
////        series.setValuesOnTopSize(50);
////        Pegando referencia do database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Acompanhamento");
        databaseReference.keepSynced(true);

        eventoDatabase();

        wvGrafico = (WebView)findViewById(R.id.wvGrafico);
        wvGrafico.loadUrl(urlGrafico    );
    }

    public void eventoDatabase() {
        databaseReference.child("aluno01").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAcompanhamento.clear();
                for (DataSnapshot obj : dataSnapshot.getChildren()) {
                    Acompanhamento acomp = obj.getValue(Acompanhamento.class);
                    if(acomp.getPeso() < 72) {
                        System.out.println("Acompanhamento:" + acomp.getPeso());
                    }listAcompanhamento.add(acomp);
                }
                arrayAdapteracompanhamento = new ArrayAdapter<Acompanhamento>(GraficosPerfil.this,
                        android.R.layout.simple_list_item_1, listAcompanhamento);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
