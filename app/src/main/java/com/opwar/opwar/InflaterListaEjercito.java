package com.opwar.opwar;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.adapters.UnidadesAdapter;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.model.UnidadEspecial;
import com.opwar.opwar.model.UnidadSingular;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by URZU on 27/04/2016.
 */
public class InflaterListaEjercito {
    private ListaWarActivity listaWarActivity;
    private ListaEjercito listaEjercito;
    private String nombreLista;
    private EditText titulo;
    private EditText limitePuntos;
    private TextView nombreEjercito;
    private TextView puntosTotales;
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

    public InflaterListaEjercito(ListaWarActivity listaWarActivity, ListaEjercito listaEjercito,
                                 String nombreLista) {
        this.listaWarActivity = listaWarActivity;
        this.listaEjercito = listaEjercito;
        this.nombreLista = nombreLista;
    }

    private void findViewsById() {
        titulo = (EditText) listaWarActivity.findViewById(R.id.title);
        limitePuntos = (EditText) listaWarActivity.findViewById(R.id.limite_edittext);
        nombreEjercito = (TextView) listaWarActivity.findViewById(R.id.ejercito_textview);
        puntosTotales = (TextView) listaWarActivity.findViewById(R.id.cuentaTotal);
        cuentaComandantesTextView = (TextView) listaWarActivity.findViewById(R.id.cuentaComandantes);
        cuentaUnidadesBasicasTextView = (TextView) listaWarActivity.findViewById(R.id.cuentaUnidadesBasicas);
        cuentaUnidadesEspecialesTextView = (TextView) listaWarActivity.findViewById(R.id.cuentaUnidadesEspeciales);
        cuentaUnidadesSingularesTextView = (TextView) listaWarActivity.findViewById(R.id.cuentaUnidadesSingulares);
        listaComandates = (ListView) listaWarActivity.findViewById(R.id.listviewComandantes);
        listaUnidadesBasicas = (ListView) listaWarActivity.findViewById(R.id.listviewUnidadesBasicas);
        listaUnidadesEspeciales = (ListView) listaWarActivity.findViewById(R.id.listviewUnidadesEspeciales);
        listaUnidadesSingulares = (ListView) listaWarActivity.findViewById(R.id.listviewUnidadesSingulares);
    }

    public void populateFields() {
        findViewsById();
        titulo.setText(nombreLista);
        titulo.setSelection(titulo.getText().length());
        limitePuntos.setText(String.valueOf(listaEjercito.getLimitePuntos()));
        limitePuntos.setEnabled(false);
        nombreEjercito.setText(listaEjercito.getNombre());
        puntosTotales.setText(String.valueOf(listaEjercito.getPuntos()));
        extractUnidades();

        ListUnidadesAdapter adaptadorComandantes = new ListUnidadesAdapter(listaWarActivity.getBaseContext(), android.R.layout.simple_list_item_1,
                comandantesSeleccionados, listaComandates, cuentaComandantesTextView, puntosTotales, listaWarActivity);
        listaWarActivity.iniciarLista(adaptadorComandantes, listaComandates, cuentaComandantesTextView);
        cuentaComandantesTextView.setText(String.valueOf(comandantesSeleccionados.size()));
        listaWarActivity.setComandantesSeleccionados(comandantesSeleccionados);

        ListUnidadesAdapter adaptadorUnidadesBasicas = new ListUnidadesAdapter(listaWarActivity.getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesBasicasSeleccionadas, listaUnidadesBasicas, cuentaUnidadesBasicasTextView, puntosTotales, listaWarActivity);
        listaWarActivity.iniciarLista(adaptadorUnidadesBasicas, listaUnidadesBasicas, cuentaUnidadesBasicasTextView);
        cuentaUnidadesBasicasTextView.setText(String.valueOf(unidadesBasicasSeleccionadas.size()));
        listaWarActivity.setUnidadesBasicasSeleccionadas(unidadesBasicasSeleccionadas);

        ListUnidadesAdapter adaptadorUnidadesEspeciales = new ListUnidadesAdapter(listaWarActivity.getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesEspecialesSeleccionados, listaUnidadesEspeciales, cuentaUnidadesEspecialesTextView, puntosTotales, listaWarActivity);
        listaWarActivity.iniciarLista(adaptadorUnidadesEspeciales, listaUnidadesEspeciales, cuentaUnidadesEspecialesTextView);
        cuentaUnidadesEspecialesTextView.setText(String.valueOf(unidadesEspecialesSeleccionados.size()));
        listaWarActivity.setUnidadesEspecialesSeleccionados(unidadesEspecialesSeleccionados);

        ListUnidadesAdapter adaptadorUnidadesSingulares = new ListUnidadesAdapter(listaWarActivity.getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesSingularesSeleccionadas, listaUnidadesSingulares, cuentaUnidadesSingularesTextView, puntosTotales, listaWarActivity);
        listaWarActivity.iniciarLista(adaptadorUnidadesSingulares, listaUnidadesSingulares, cuentaUnidadesSingularesTextView);
        cuentaUnidadesSingularesTextView.setText(String.valueOf(unidadesSingularesSeleccionadas.size()));
        listaWarActivity.setUnidadesSingularesSeleccionadas(unidadesSingularesSeleccionadas);
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

    public void setEnable(boolean enable) {
        if (enable) {
            new UnidadesAdapter(listaWarActivity, listaEjercito.getIdEjercito()).execute();
        } else {
            titulo.setEnabled(false);
            limitePuntos.setEnabled(false);
        }
    }
}
