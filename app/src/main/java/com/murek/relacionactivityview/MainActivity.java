package com.murek.relacionactivityview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.murek.relacionactivityview.databinding.ActivityMainBinding;

//*** CLASE PRINCIPAL DE LA APP ***
public class MainActivity extends AppCompatActivity {

    //*** VARIABLES GLOBALES ***
    private ActivityMainBinding binding; //binding: referencia elemetos UI - interactuamos con elementos XML por el ViewBinding
    private IncrementarViewModel incrementarViewModel; //objero para almacenar valor. persiste en la memoria durante el ciclo de vida (no se va cuando giramos la pantalla
    private int res=0; //variable que cuenta clics al usar ViewModel. Se reinicia al girar la pantalla

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cofigurar el View Binding para poder interactuar con los elementos UI desde el codigo
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Indicamos que el contenido sera el diseño asociado a binding
        setContentView(binding.getRoot());

        //inicializar el view model (mantiene los datos aunque giremos la pantalla)
            //aunque la actividad se destruya el viewmodel conserva su estado anterior
        incrementarViewModel = new ViewModelProvider(this).get(IncrementarViewModel.class);
        Log.d("TAG1", "inCreate()");

        //mostrar el valor de los editText ya que este metodo se ejecuta varias veces
        binding.tvResCon.setText("Con ViewModel: " + incrementarViewModel.getResultado()); //actualizamos el textview que almacena el viewmodel. getresultado, devuelve el valor actual almacenado
        binding.tvResSin.setText("Sin ViewModel: " + res); //el texview muestra el valor de la variable que incrementa, este se reinicia al destruirse la actividad

        // Llamar al método que asigna los listeners a los botones
        tarea(); //hace funcionar los botones
    }

    //*** METODOS ***
    public void tarea(){ //tiene los listeners para los clicks de los botones

        // Listener del botón sin ViewModel
        binding.btIncrementarSin.setOnClickListener(new View.OnClickListener() { //Cuando el usuario hace clic en el botón, se ejecuta el código dentro del onClick.
            @Override
            public void onClick(View v) {
                //actualiza solo la variable local (no se mantiene tras rotacion)
                res=Incrementar.incrementar(res); //usamos el metodo incrementar de la clase incrementar para sumar en 1
                binding.tvResSin.setText("Sin ViewModel: "+res); //se actualiza el text view
            }
        });

        // Listener del botón con ViewModel
        binding.btIncrementarCon.setOnClickListener(new View.OnClickListener() { //Cuando el usuario hace clic en el botón, se ejecuta el código dentro del onClick.
            @Override
            public void onClick(View v) {
                //incrementar el valor almacenado en el view model (se mantiene tras rotacion)
                incrementarViewModel.setResultado(Incrementar.incrementar(incrementarViewModel.getResultado()));
                binding.tvResCon.setText("Con ViewModel: " + incrementarViewModel.getResultado());
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d( "TAG1", "onStart()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d( "TAG1", "onResume()");

    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d( "TAG1", "onPause()");

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d( "TAG1", "onStop()");

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d( "TAG1", "onDestroy()");

    }
}


