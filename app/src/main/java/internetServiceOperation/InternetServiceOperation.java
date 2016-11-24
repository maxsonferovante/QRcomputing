package internetServiceOperation;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import participant.Participant;

/**
 * Created by mferovante on 21/11/16.
 */


public class InternetServiceOperation extends IntentService {
    private static final String address = "127.0.0.1";
    private static final int port = 8080;
    private Participant participant = null;

    public InternetServiceOperation(String name) {
        super("InternetServiceOperation");
    }

    public InternetServiceOperation() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int mat_academic = intent.getExtras().getInt("Enrollment");

        Context contexto = getApplicationContext();

        String texto = "Client begin...";
        int duracao = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(contexto, texto, duracao);
        toast.show();

        Socket socket = null;
        try {
            socket = new Socket(address, port);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeInt(mat_academic);
            participant = (Participant) inputStream.readObject();
            socket.close();
            inputStream.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
