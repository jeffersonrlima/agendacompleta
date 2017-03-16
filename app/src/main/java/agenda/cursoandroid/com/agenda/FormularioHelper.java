package agenda.cursoandroid.com.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import agenda.cursoandroid.com.agenda.modelo.Aluno;

/**
 * Created by Jefferson Lima on 06/03/2017.
 */

public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private final ImageView campoFoto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.nome);
        campoEndereco = (EditText) activity.findViewById(R.id.endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.telefone);
        campoSite = (EditText) activity.findViewById(R.id.site);
        campoNota = (RatingBar) activity.findViewById(R.id.estrelas);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);

        aluno = new Aluno();
    }
        public Aluno pegaAluno(){
            aluno.setNome(campoNome.getText().toString());
            aluno.setEndereco(campoEndereco.getText().toString());
            aluno.setTelefone(campoTelefone.getText().toString());
            aluno.setSite(campoSite.getText().toString());
            aluno.setNota(campoNota.getProgress());
            aluno.setCaminhoFoto((String) campoFoto.getTag() );
            return aluno;
        }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress((int) aluno.getNota());
        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }

    }
}
