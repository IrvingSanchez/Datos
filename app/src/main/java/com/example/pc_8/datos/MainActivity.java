package com.example.pc_8.datos;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    /**
     *
     * Declaración de variables para guardar datos
     * internamente en la aplicación
     *
     */

    SharedPreferences preferencias;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Nombre del archivo y modo de privacidad
        preferencias = getSharedPreferences("Preferencias",MODE_PRIVATE);
        editor = preferencias.edit();

        //  Agregar valores al archivo
        editor.putString("player1","Mario Bross");
        editor.putInt("vidas",3);
        editor.putLong("score",7750);
        editor.putInt("monedas",14);
        editor.putFloat("world",(float)1.1);
        editor.putBoolean("activo",true);

        //  Aplicar cambios
        editor.commit();
        //  Muestra los datos
        recuperaDatos();
    }

    private void recuperaDatos()
    {
        String player1 = preferencias.getString("player1","");
        int vidas = preferencias.getInt("vidas",0);
        long score = preferencias.getLong("score",0);
        int monedas = preferencias.getInt("monedas",0);
        float world = preferencias.getFloat("world",0);
        boolean activo = preferencias.getBoolean("activo",false);
        Toast.makeText(this,    "Player 1: " + player1 +
                                "\nVidas: " + vidas +
                                "\nScore: " + score +
                                "\nMonedas: " + monedas +
                                "\nWorld: " + world +
                                "\nActivo: " + activo,
                Toast.LENGTH_SHORT).show();
    }
}
