package com.kaxtli.json_utilities;

import android.content.Context;

import com.kaxtli.awesomemovieapp.R;
import com.kaxtli.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kaxtli on 11/03/2017.
 */

public class JsonParser {
    // Parsea los objetos Movie incluídos en la cadena de json
    // Context permite ingresar al contexto de la apilicación. PE. Cadenas
    public static ArrayList<Movie> getMovies(Context context, String json_string){
        ArrayList<Movie> movies = new ArrayList<>();
        // INICIANDO EL PARSEO
        try {
            // Objeto raíz
            JSONObject json_root = new JSONObject(json_string);
            // Objeniendo el arreglo contenido en results
            JSONArray json_movies = json_root.getJSONArray(context.getString(R.string.result_json_param));
            // Iterando sobre los elementos
            for(int i = 0; i < json_movies.length(); i++){
                // Obtener el siguiente objeto JSON y extraer sus valores
                JSONObject pelicula = json_movies.getJSONObject(i);
                int id = pelicula.getInt(context.getString(R.string.id_json_param));
                String title = pelicula.getString(context.getString(R.string.title_json_param));
                String description = pelicula.getString(context.getString(R.string.descritption_json_param));
                String poster_path = pelicula.getString(context.getString(R.string.poster_path_json_param));
                // poster_path es una ruta compuesta. Guarda los valores en strings
                poster_path = context.getString(R.string.base_url_image_api) +
                        context.getString(R.string.image_size_default) +
                        "/" + poster_path;
                Movie movie = new Movie(id, title, description, poster_path);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // FIN DE PARSEO
        return movies;
    }
}
