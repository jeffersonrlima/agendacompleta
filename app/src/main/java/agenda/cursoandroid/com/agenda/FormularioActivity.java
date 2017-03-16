package agenda.cursoandroid.com.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;

import agenda.cursoandroid.com.agenda.dao.AlunoDAO;
import agenda.cursoandroid.com.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 567;
    private FormularioHelper helper;
    private Button btnCamera;
    private ImageView foto;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        btnCamera = (Button) findViewById(R.id.formulario_btnCamera);
        foto = (ImageView) findViewById(R.id.formulario_foto);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalCacheDir() + "/" + "Img" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri. fromFile(arquivoFoto));
                startActivityForResult(intentCamera,  REQUEST_CODE_CAMERA);
            }
        });

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_CODE_CAMERA){
                helper.carregaImagem(caminhoFoto);
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();
                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId() != null){
                    dao.altera(aluno);
                    Toast.makeText(getApplicationContext(), "Aluno "+aluno.getNome()+ " alterado com Sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    dao.insere(aluno);
                    Toast.makeText(getApplicationContext(), "Aluno "+aluno.getNome()+ " cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                }
                dao.close();


                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
