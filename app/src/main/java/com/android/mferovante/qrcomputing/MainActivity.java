package com.android.mferovante.qrcomputing;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import database.FeedReaderDbHelper;
import webservice.AcessWebService;

public class MainActivity extends AppCompatActivity {
    public final static String URL= "http://192.168.1.117:36804/WebWeek/webresources/app/Authentication/get/";
    public final static String EXTRA_MESSAGE = "com.android.mferovante.qrcomputing";

    private String response = null;

    private ImageView imagem = null;
    private Button bttAuth;
    private EditText editTextEnrollment;
    private AcessWebService aws = null;

    private FeedReaderDbHelper mDbHelper = null;
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
        mDbHelper = new FeedReaderDbHelper(getApplicationContext());
    }

    public void authentication(View view) {
        Intent intent = new Intent(this, SpaceParticipantActivity.class);

        EditText editText = (EditText) findViewById(R.id.editTextAcademicEnrollment);
        editText.setText("");

        String message = editText.getText().toString();
        aws = new AcessWebService(URL+message);

        if (aws.getStatusWebService()){
            Toast.makeText(getApplicationContext(),
                        "WebService available!",
                        Toast.LENGTH_SHORT).show();

            response = aws.webServiceRequest();
            if (response != null){
                Toast.makeText(getApplicationContext(),
                        "Enrollment found!",
                        Toast.LENGTH_SHORT).show();
                Log.i("JSON",response);
                intent.putExtra(EXTRA_MESSAGE, response);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "Enrollment not found! try again!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("We had a problem connecting to our servers." +
                                    "Please try again. In case, from the error, " +
                                    "you are probably experiencing serious problems on our servers." +
                                    "Let us know by e-mail: maxsonferovante@gmail.com. Thanks :D");

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
