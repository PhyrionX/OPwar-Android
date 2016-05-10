package com.opwar.opwar.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.opwar.opwar.ListBatallaAdapter;
import com.opwar.opwar.R;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.model.UnidadEspecial;
import com.opwar.opwar.model.UnidadSingular;
import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.ListViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by URZU on 10/05/2016.
 */
public class BatallaActivity extends AppCompatActivity {
    private ListaEjercito listaEjercito;
    private String nombreLista;
    private TextView nombreEjercito;
    private TextView puntosTotales;
    private Button bCancelar;
    private Button bFinalizar;
    private List<Regimiento> comandantesSeleccionados;
    private List<Regimiento> unidadesBasicasSeleccionadas;
    private List<Regimiento> unidadesEspecialesSeleccionados;
    private List<Regimiento> unidadesSingularesSeleccionadas;
    private TextView cuentaComandantesTextView;
    private TextView cuentaUnidadesBasicasTextView;
    private TextView cuentaUnidadesEspecialesTextView;
    private TextView cuentaUnidadesSingularesTextView;
    private ListView listaComandates;
    private ListView listaUnidadesBasicas;
    private ListView listaUnidadesEspeciales;
    private ListView listaUnidadesSingulares;
    private ListBatallaAdapter adaptadorComandantes;
    private ListBatallaAdapter adaptadorUnidadesBasicas;
    private ListBatallaAdapter adaptadorUnidadesEspeciales;
    private ListBatallaAdapter adaptadorUnidadesSingulares;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.batalla_toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.modo_batalla);
        findViewsById();
        ListaEjercito listaEjercito = (ListaEjercito) getIntent().getSerializableExtra(Constants.LISTA_EJERCITO);
        if (listaEjercito != null) {
            this.listaEjercito = listaEjercito;
            populateFields();
        }
        setCancelAction();
        setFinalizarAction();
        populateFields();
    }

    private void findViewsById() {
        puntosTotales = (TextView) findViewById(R.id.cuentaTotal);
        nombreEjercito = (TextView) findViewById(R.id.ejercito_textview);
        bCancelar = (Button) findViewById(R.id.cancelar);
        bFinalizar = (Button) findViewById(R.id.finalizar);
        cuentaComandantesTextView = (TextView) findViewById(R.id.cuentaComandantes);
        cuentaUnidadesBasicasTextView = (TextView) findViewById(R.id.cuentaUnidadesBasicas);
        cuentaUnidadesEspecialesTextView = (TextView) findViewById(R.id.cuentaUnidadesEspeciales);
        cuentaUnidadesSingularesTextView = (TextView) findViewById(R.id.cuentaUnidadesSingulares);
        listaComandates = (ListView) findViewById(R.id.listviewComandantes);
        listaUnidadesBasicas = (ListView) findViewById(R.id.listviewUnidadesBasicas);
        listaUnidadesEspeciales = (ListView) findViewById(R.id.listviewUnidadesEspeciales);
        listaUnidadesSingulares = (ListView) findViewById(R.id.listviewUnidadesSingulares);
    }

    private void setCancelAction() {
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BatallaActivity.this)
                        .setTitle("Cancelar batalla")
                        .setMessage("¿Deseas cancelar el progreso de la batalla?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    private void setFinalizarAction() {
        bFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BatallaActivity.this)
                        .setTitle("Resultado de la batalla")
                        .setPositiveButton("Victoria", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Derrota", null)
                        .show();
            }
        });
    }

    private void populateFields() {
        nombreLista = getIntent().getStringExtra(Constants.NOMBRE_LISTA);
        puntosTotales.setText(String.valueOf(listaEjercito.getPuntos()));
        nombreEjercito.setText(listaEjercito.getNombre());

        extractUnidades();

        cuentaComandantesTextView.setText(String.valueOf(comandantesSeleccionados.size()));
        cuentaUnidadesBasicasTextView.setText(String.valueOf(unidadesBasicasSeleccionadas.size()));
        cuentaUnidadesEspecialesTextView.setText(String.valueOf(unidadesEspecialesSeleccionados.size()));
        cuentaUnidadesSingularesTextView.setText(String.valueOf(unidadesSingularesSeleccionadas.size()));

        adaptadorComandantes = new ListBatallaAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                comandantesSeleccionados, listaComandates, cuentaComandantesTextView, puntosTotales, this);
        iniciarLista(adaptadorComandantes, listaComandates);

        adaptadorUnidadesBasicas = new ListBatallaAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesBasicasSeleccionadas, listaUnidadesBasicas, cuentaUnidadesBasicasTextView, puntosTotales, this);
        iniciarLista(adaptadorUnidadesBasicas, listaUnidadesBasicas);

        unidadesEspecialesSeleccionados = new ArrayList<>();
        adaptadorUnidadesEspeciales = new ListBatallaAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesEspecialesSeleccionados, listaUnidadesEspeciales, cuentaUnidadesEspecialesTextView, puntosTotales, this);
        iniciarLista(adaptadorUnidadesEspeciales, listaUnidadesEspeciales);

        unidadesSingularesSeleccionadas = new ArrayList<>();
        adaptadorUnidadesSingulares = new ListBatallaAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesSingularesSeleccionadas, listaUnidadesSingulares, cuentaUnidadesSingularesTextView, puntosTotales, this);
        iniciarLista(adaptadorUnidadesSingulares, listaUnidadesSingulares);
    }

    private void extractUnidades() {
        List<Regimiento> regimientos = listaEjercito.getRegimientos();
        comandantesSeleccionados = new ArrayList<>();
        unidadesBasicasSeleccionadas = new ArrayList<>();
        unidadesEspecialesSeleccionados = new ArrayList<>();
        unidadesSingularesSeleccionadas = new ArrayList<>();
        for (Regimiento regimiento : regimientos) {
            if (regimiento.getUnidad() instanceof Comandante) {
                comandantesSeleccionados.add(regimiento);
            } else if (regimiento.getUnidad() instanceof UnidadBasica) {
                unidadesBasicasSeleccionadas.add(regimiento);
            } else if (regimiento.getUnidad() instanceof UnidadEspecial) {
                unidadesEspecialesSeleccionados.add(regimiento);
            } else if (regimiento.getUnidad() instanceof UnidadSingular) {
                unidadesSingularesSeleccionadas.add(regimiento);
            }
        }
    }

    public boolean removeRegimiento(Regimiento p) {
        return listaEjercito.remove(p);
    }

    public void iniciarLista(ListBatallaAdapter listBatallaAdapter, ListView listView) {
        listView.setAdapter(listBatallaAdapter);
        listBatallaAdapter.notifyDataSetChanged();
        ListViewUtil.setListViewHeightBasedOnChildren(listView);
    }
}
