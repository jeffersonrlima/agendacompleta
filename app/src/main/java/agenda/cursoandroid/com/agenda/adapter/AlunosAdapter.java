package agenda.cursoandroid.com.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import agenda.cursoandroid.com.agenda.ListaAlunosActivity;
import agenda.cursoandroid.com.agenda.R;
import agenda.cursoandroid.com.agenda.modelo.Aluno;

/**
 * Created by Jefferson Lima on 08/03/2017.
 */

public class AlunosAdapter extends BaseAdapter{


    private final List<Aluno> alunos;
    private final Context context;
    private TextView campoNome;
    private TextView campoTelefone;
    private ImageView campoFoto;
    private TextView campoSite;
    private TextView campoEndereco;

    public AlunosAdapter(Context context, List<Aluno> alunos) {
        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {

        return alunos.size();
    }

    @Override
    public Object getItem(int position) {

        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {

        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.list_item_formulario, parent, false);
        }

        campoNome = (TextView) view.findViewById(R.id.item_nome);
        campoNome.setText(aluno.getNome());

        campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(aluno.getTelefone());

        campoSite = (TextView) view.findViewById(R.id.land_site);
        if(campoSite != null){
            campoSite.setText(aluno.getSite());
        }

        campoEndereco = (TextView) view.findViewById(R.id.land_endereco);
        if(campoEndereco != null){
            campoEndereco.setText(aluno.getEndereco());
        }

        campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
