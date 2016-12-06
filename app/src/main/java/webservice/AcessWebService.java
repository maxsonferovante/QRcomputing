package webservice;

import com.google.gson.Gson;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;

import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;


import java.io.IOException;
import java.io.StringReader;


/**
 * Created by mferovante on 05/12/16.
 */


public class AcessWebService {

    private static final int TIMEOUT = 3000;
    private HttpClient httpClient = null;
    private HttpGet callget = null;
    private StrictMode.ThreadPolicy policy = null;
    private ResponseHandler<String> responseHandler = null;
    private String responseBody = null;

    private String url = null;

    public AcessWebService(String s) {
        this.url = s;
    }
    public Boolean getStatusWebService(){
        httpClient = new DefaultHttpClient();
        callget = new HttpGet(url);
        try{
            policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(callget,responseHandler);

        } catch (ClientProtocolException e) {
            setAllNull();
            return false;

        } catch (IOException e) {
            setAllNull();
            return false;

        }
        setAllNull();
        return true;
    }
    public String webServiceRequest(){
        httpClient = new DefaultHttpClient();
        callget = new HttpGet(url);
        try{
            policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            responseHandler = new BasicResponseHandler();
            responseBody = httpClient.execute(callget,responseHandler);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }
    private void setAllNull() {
        httpClient = null;
        callget = null;
        policy = null;
        responseBody = null;
        responseHandler = null;
        url = null;
    }
}
