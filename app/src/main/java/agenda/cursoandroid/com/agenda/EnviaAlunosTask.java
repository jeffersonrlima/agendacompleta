package agenda.cursoandroid.com.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import agenda.cursoandroid.com.agenda.converter.AlunoConverter;
import agenda.cursoandroid.com.agenda.dao.AlunoDAO;
import agenda.cursoandroid.com.agenda.modelo.Aluno;

/**
 * Created by Jefferson Lima on 09/03/2017.
 */

public class EnviaAlunosTask extends AsyncTask<Object, Object, String>{

    private Context context;
    private ProgressDialog dialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando Alunos...", true, true);
    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converteParaJSON(alunos);
        WebClient client = new WebClient();
        String resposta = client.post(json);


        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
    }
}
