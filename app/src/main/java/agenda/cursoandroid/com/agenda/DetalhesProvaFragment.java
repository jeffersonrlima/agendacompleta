package agenda.cursoandroid.com.agenda;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import agenda.cursoandroid.com.agenda.modelo.Prova;

public class DetalhesProvaFragment extends Fragment {

    private TextView campoMateria;
    private TextView campoData;
    private ListView listaTopicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        campoMateria = (TextView) view.findViewById(R.id.detalhes_prova_materia);
        campoData = (TextView) view.findViewById(R.id.detalhes_prova_data);
        listaTopicos = (ListView) view.findViewById(R.id.detalhes_provas_lista);

        Bundle parametros = getArguments();
        if(parametros != null){
            Prova prova = (Prova) parametros.getSerializable("prova");
            populaCamposCom(prova);
        }

        return view;
    }

    public void populaCamposCom(Prova prova){
        campoData.setText(prova.getData());
        campoMateria.setText(prova.getMateria());

        ArrayAdapter<String> adapterTopicos = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,
                prova.getTopicos()){
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =  super.getView(position, convertView, parent);
                ((TextView) view).setTextColor(Color.parseColor("#000000"));
                return view;
            }
        };

        listaTopicos.setAdapter(adapterTopicos);
    }

}
