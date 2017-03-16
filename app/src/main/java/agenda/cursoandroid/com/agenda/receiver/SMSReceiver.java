package agenda.cursoandroid.com.agenda.receiver;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

import agenda.cursoandroid.com.agenda.ListaAlunosActivity;
import agenda.cursoandroid.com.agenda.R;
import agenda.cursoandroid.com.agenda.dao.AlunoDAO;

/**
 * Created by Jefferson Lima on 09/03/2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        if(dao.ehAluno(telefone)){
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((Activity) context,
                        new String[] {android.Manifest.permission.RECEIVE_SMS}, 123);
            }else{
                Toast.makeText(context, "Chegou um SMS!", Toast.LENGTH_SHORT).show();
                MediaPlayer somMsg = MediaPlayer.create(context, R.raw.cao);
                somMsg.start();
                somMsg.release();
                somMsg.stop();
            }
        }
        dao.close();
    }
}
