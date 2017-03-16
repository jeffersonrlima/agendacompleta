package agenda.cursoandroid.com.agenda;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import agenda.cursoandroid.com.agenda.modelo.Prova;

public class ProvasDetalhesActivity extends AppCompatActivity {

    private TextView campoMateria;
    private TextView campoData;
    private ListView listaTopicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas_detalhes);

        Intent intent = getIntent();
        Prova prova = (Prova) intent.getSerializableExtra("prova");

        campoMateria = (TextView) findViewById(R.id.detalhes_prova_materia);
        campoData = (TextView) findViewById(R.id.detalhes_prova_data);
        listaTopicos = (ListView) findViewById(R.id.detalhes_provas_lista);

        campoMateria.setText(prova.getMateria());
        campoData.setText(prova.getData());

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, prova.getTopicos()){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =  super.getView(position, convertView, parent);
                ((TextView) view).setTextColor(Color.parseColor("#000000"));
                return view;
            }
        };

        listaTopicos.setAdapter(adapter);
    }
}
