package com.example.pc_8.datos;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    /**
     *
     * Declaración de variables para guardar datos
     * internamente en la aplicación
     *
     */

    SharedPreferences preferencias;
    SharedPreferences.Editor editor;

    OutputStreamWriter archivoEscritura;
    TextView encabezado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encabezado = (TextView) findViewById(R.id.encabezado);

        /**
         * Guarda Datos en Preferencias Compartidas
         */
        //  Nombre del archivo y modo de privacidad
        preferencias = getSharedPreferences("Preferencias",MODE_PRIVATE);
        editor = preferencias.edit();
        //  Agrega datos a SP
        agregaDatosSP();

        //  Muestra los datos
        recuperaDatosSP();

        /**
         * Guarda Datos en archivos
         */
        try {
            archivoEscritura = new OutputStreamWriter(
                    openFileOutput("archivoInterno.txt", MODE_PRIVATE));
            archivoEscritura.write("Estoy escribiendo en el archivo");
            archivoEscritura.write("\n Estoy escribiendo de nuevo.");
            archivoEscritura.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se encontro el archivo\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo leer.\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        leerDatosArchivo();


    }

    private void agregaDatosSP()
    {
        //  Agregar valores al archivo
        editor.putString("player1","Mario Bross");
        editor.putInt("vidas",3);
        editor.putLong("score",7750);
        editor.putInt("monedas",14);
        editor.putFloat("world",(float)1.1);
        editor.putBoolean("activo",true);

        //  Aplicar cambios
        editor.commit();
    }
    private void recuperaDatosSP()
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

    private void leerDatosArchivo()
    {
        try {
            BufferedReader archivoLectura = new BufferedReader(new InputStreamReader(
                    openFileInput("archivoInterno.txt")));
            String linea;
            encabezado.setText("Esto dice el archivo: ");
            while ( (linea = archivoLectura.readLine()) != null)
            {
                encabezado.append("\n" + linea);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se encontro el archivo\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se pudo leer.\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}
