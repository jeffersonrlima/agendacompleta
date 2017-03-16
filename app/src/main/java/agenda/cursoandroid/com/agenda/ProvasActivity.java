package agenda.cursoandroid.com.agenda;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import agenda.cursoandroid.com.agenda.modelo.Prova;

public class ProvasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_principal, new ListaProvasFragment());
        if(estaNoModoPaisagem()){
            fragmentTransaction.replace(R.id.frame_secundario, new DetalhesProvaFragment());
        }

        fragmentTransaction.commit();
    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selecionaProva(Prova prova) {
        FragmentManager manager = getSupportFragmentManager();
        if(!estaNoModoPaisagem()) {
            FragmentTransaction transaction = manager.beginTransaction();

            DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("prova", prova);
            detalhesProvaFragment.setArguments(parametros);

            transaction.replace(R.id.frame_principal, detalhesProvaFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else{
            DetalhesProvaFragment detalhesProvaFragment = (DetalhesProvaFragment) manager.findFragmentById(R.id.frame_secundario);
            detalhesProvaFragment.populaCamposCom(prova);
        }
    }
}
