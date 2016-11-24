package com.android.mferovante.qrcomputing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import internetServiceOperation.InternetServiceOperation;
import participant.Participant;


public class MainActivity extends AppCompatActivity {

    private ImageView imagem = null;
    private Button bttAuth;
    private EditText editTextEnrollment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagem = (ImageView) findViewById(R.id.imageView);
        bttAuth = (Button) findViewById(R.id.buttonAuthentication);

        editTextEnrollment = (EditText) findViewById(R.id.editTextAcademicEnrollment);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        IntentFilter finlter = new IntentFilter(ReceptorOperation.ACTION_RESP);
        finlter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(new ReceptorOperation(), finlter);
    }

    public void authentication(View view) {
        Intent intent = new Intent(MainActivity.this, InternetServiceOperation.class);
        intent.putExtra("Enrollment", editTextEnrollment.getText().toString());
        startService(intent);
    }

    public class ReceptorOperation extends BroadcastReceiver {
        public static final String ACTION_RESP = "com.android.mferovante.qrcomputing.intent.action.Receptor_Operation";

        @Override
        public void onReceive(final Context context, final Intent intent) {
            final Participant part;
            part = (Participant) intent.getSerializableExtra("participant");
            if (part != null) {

                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you registered! Welcome , Maxson Ferovante");
                builder.setTitle("Success ... ");
                final AlertDialog.Builder builder1 = builder.setPositiveButton("Continue!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Context contexto = getApplicationContext();
                        intent.setClassName(contexto, "InternetServiceOperation.class");
                        intent.putExtra("participant", part);
                        startService(intent);
                    }
                });
                // Create the AlertDialog object and return it
                (builder.create()).show();
            } else {
                AlertDialog.Builder builder;
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert ...");
                builder.setMessage("Are you registered? I did not find your registration.");
                builder.setPositiveButton("Try again!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editTextEnrollment.setText("");
                    }
                });
                (builder.create()).show();
            }
        }
    }
}
