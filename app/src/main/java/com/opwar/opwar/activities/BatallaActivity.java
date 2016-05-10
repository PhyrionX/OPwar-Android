package com.opwar.opwar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.util.Constants;

/**
 * Created by URZU on 10/05/2016.
 */
public class BatallaActivity extends AppCompatActivity {
    private ListaEjercito listaEjercito;
    private TextView puntosTotales;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.batalla_toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.modo_batalla);
        ListaEjercito listaEjercito = (ListaEjercito) getIntent().getSerializableExtra(Constants.LISTA_EJERCITO);
        if (listaEjercito != null) {
            this.listaEjercito = listaEjercito;
            String nombreLista = getIntent().getStringExtra(Constants.NOMBRE_LISTA);
            puntosTotales = (TextView) findViewById(R.id.cuentaTotal);
            puntosTotales.setText(String.valueOf(listaEjercito.getPuntos()));
        }
    }
}
