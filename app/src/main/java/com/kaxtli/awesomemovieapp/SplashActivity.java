package com.kaxtli.awesomemovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kaxtli on 11/03/2017.
 */

public class SplashActivity extends AppCompatActivity {
    /**
     * Lógica que se llev a acabo al iniciar la actividad*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recuperar de R el layout correspondiente a esta actividad
        setContentView(R.layout.activity_splash);

        // Implementando el temporizador de arranque
        Thread thread = new Thread(){
            @Override
            // Ejecuta en un hilo las siguientes instrucciones
            public void run() {
                super.run();
                try {
                    sleep(2500); // El tiempo deseado
                    Log.d("Error:", "run: ");
                }catch (InterruptedException e) {
                    // Error! Muestra un mensaje.
                    e.printStackTrace();
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.generic_error,
                            Toast.LENGTH_SHORT
                    ).show();
                } finally {
                    // Terminó el temporizador. Pasa el foco a la actividad principal y desaparece.
                    // Utiliza un intent para realizar el cambio del aquí a la siguiente actividad.
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    // Splash -> Main
                    startActivity(intent);
                    // Desaparece
                    finish();
                }
            }
        };
        thread.start();
    }
}
