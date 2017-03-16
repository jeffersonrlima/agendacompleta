package agenda.cursoandroid.com.agenda;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import agenda.cursoandroid.com.agenda.dao.AlunoDAO;
import agenda.cursoandroid.com.agenda.modelo.Aluno;

/**
 * Created by Jefferson Lima on 15/03/2017.
 */

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        LatLng posicaoDaEscola = pegaCoordenadaDoEndereco("IFPI CENTRAL");
        if(posicaoDaEscola != null){
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola, 17);
            googleMap.moveCamera(update);
        }

        AlunoDAO alunoDAO = new AlunoDAO(getContext());
        for(Aluno aluno : alunoDAO.buscaAlunos()){
            LatLng coordenada = pegaCoordenadaDoEndereco(aluno.getEndereco());
            if (coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(aluno.getNome());
                marcador.snippet(String.valueOf(aluno.getNota()));
                googleMap.addMarker(marcador);
            }
        }
        alunoDAO.close();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
    }

    private LatLng pegaCoordenadaDoEndereco(String endereco){
        try{
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultado = geocoder.getFromLocationName(endereco, 1);
            if(!resultado.isEmpty()){
                LatLng posicao = new LatLng(resultado.get(0).getLatitude(), resultado.get(0).getLongitude());
                return posicao;
            }
        }catch (IOException e){
            Toast.makeText(getContext(), "Se lascou", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
