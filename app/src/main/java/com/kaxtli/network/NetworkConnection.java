package com.kaxtli.network;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.kaxtli.awesomemovieapp.R;
import com.kaxtli.listeners.NetworkConnectionInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kaxtli on 11/03/2017.
 */

public class NetworkConnection extends AsyncTask<Void, Void, Boolean>{
    // TAG para manejar logs
    private final String TAG = NetworkConnection.class.getSimpleName();
    private Context context;
    private String responseStr;
    private NetworkConnectionInterface listener;
    // Constructor. Recibe el contexto de Android.
    public NetworkConnection(Context context, NetworkConnectionInterface networkConnectionInterface){
        this.context = context;
        this.listener = networkConnectionInterface;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        // Construcción de la Uri. Definiendo parámetros
        final String BASE_URL = "http://api.themoviedb.org/3/movie";
        final String POPULAR_PATH = "popular";
        final String API_KEY_PARAM = "api_key";

        // Construcción de la URL
        Uri uriToAPI = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(POPULAR_PATH)
                .appendQueryParameter(API_KEY_PARAM, this.context.getString(R.string.api_key_value))
                .build();
        // Declarando el objeto para la conexión
        Log.d(TAG, uriToAPI.toString());
        HttpURLConnection urlConnection;
        // Declarando el BufferReader para leer los bytes
        BufferedReader reader;

        // Indicando la URL a seguir
        try {
            URL url = new URL(uriToAPI.toString());
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Extrayendo los datos
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            // Se valida la respuesta del servidor usando el input stream xdxd
            if(inputStream == null) {
                // Ya me morí :C. No hubo respuesta del servidor. ¿Error de conexión?.
                return false;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Leer el buffer
            String line;
            while((line = reader.readLine()) != null) {
                buffer.append(line + '\n');
            }
            if(buffer.length() == 0) {
                // La respuesta desde el servidor está vacía. Algo salió mal.
                return false;
            }
            responseStr = buffer.toString();
            Log.d(TAG, "SERVER_RESPONSE:" + responseStr);
            // Todo OK
            return true;
        } catch (MalformedURLException e) {
            Log.e(TAG, e.toString());
            return false;
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            if(listener != null) {
                listener.OnSuccessfullyResponse(responseStr);
            }
        } else {
            if (listener != null) {
                listener.OnFailResponse();
            }
        }
    }


}
