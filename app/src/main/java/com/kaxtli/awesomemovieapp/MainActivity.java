package com.kaxtli.awesomemovieapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kaxtli.adapters.MoviesAdapter;
import com.kaxtli.json_utilities.JsonParser;
import com.kaxtli.listeners.NetworkConnectionInterface;
import com.kaxtli.models.Movie;
import com.kaxtli.network.NetworkConnection;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkConnectionInterface{
    // Creamos un tag
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView)findViewById(R.id.movie_list);
        list.setLayoutManager(new GridLayoutManager(this, 2));
        list.setHasFixedSize(true);
        // Creando la conexión.
        NetworkConnection connection = new NetworkConnection(this, this);
        // Ejecutando la conexión
        connection.execute(); // Si hubiera parámetros, aqú se ponen.

        /* Creando la Uri BEST PRACTICES!!
        Uri uriExample;
        // Construcción de Uri, definiendo la base y a la espera de nueva información.
        Uri.Builder builder = Uri.parse("http://www.google.com").buildUpon();
        // Agregando secciones!!
        builder.appendPath("users");
        builder.appendPath("params");
        // Agregando parámetros
        builder.appendQueryParameter("id","1533");
        builder.appendQueryParameter("name", "Jesús");

        // Finalizando la Uri
        uriExample = builder.build();

        // LOG
        Log.d(TAG, "URL: " + uriExample.toString());

        // PLAN B ( Forma reducida xdxd)
        uriExample = Uri.parse("http://www.google.com")
                .buildUpon()
                .appendPath("users")
                .appendPath("params")
                .appendQueryParameter("id","1533")
                .appendQueryParameter("name", "Jesús")
                .build();
    }*/
    }

    @Override
    public void OnSuccessfullyResponse(String response) {
        // Parsear el JSON
        ArrayList<Movie> movies = JsonParser.getMovies(this, response);
        int ifeje = 0;
        MoviesAdapter adapter = new MoviesAdapter(this, movies);
        list.setAdapter(adapter);
    }

    @Override
    public void OnFailResponse() {

    }
}
