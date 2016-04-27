package com.opwar.opwar.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.opwar.opwar.R;
import com.opwar.opwar.adapters.EjercitosAdapter;
import com.opwar.opwar.adapters.UnidadesAdapter;
import com.opwar.opwar.listview.ListUnidadesAdapter;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.Ejercito;
import com.opwar.opwar.util.ListFileOperations;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.model.Unidad;
import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.model.UnidadEspecial;
import com.opwar.opwar.model.UnidadSingular;
import com.opwar.opwar.util.ListViewUtil;
import com.opwar.opwar.util.ListaEjercitoException;

import java.util.ArrayList;
import java.util.List;

public class ListaWarActivity extends AppCompatActivity {
    private ImageButton cancelImageButton;
    private static TextView ejercitoTextView;
    private AlertDialog.Builder ejercitoAlertDialog;
    private Ejercito ejercitoSeleccionado;
    private int ejercitosDesplegableSeleccionado;
    private ListaEjercito listaEjercito;
    private ImageButton anadirComandate;
    private ImageButton anadirUnidadBasica;
    private ImageButton anadirUnidadEspecial;
    private ImageButton anadirUnidadSingular;
    private boolean editTextLimitePuntosRellenado = false;
    private ListView listaComandates;
    private ListUnidadesAdapter adaptadorComandantes;
    private TextView cuentaComandantesTextView;
    private ListView listaUnidadesBasicas;
    private ListUnidadesAdapter adaptadorUnidadesBasicas;
    private TextView cuentaUnidadesBasicasTextView;
    private ListView listaUnidadesEspeciales;
    private ListUnidadesAdapter adaptadorUnidadesEspeciales;
    private TextView cuentaUnidadesEspecialesTextView;
    private ListView listaUnidadesSingulares;
    private ListUnidadesAdapter adaptadorUnidadesSingulares;
    private TextView cuentaUnidadesSingularesTextView;
    private ProgressDialog progressDialog;
    private TextView puntosTotalesTextView;
    private List<Regimiento> comandantesSeleccionados;
    private List<Regimiento> unidadesBasicasSeleccionadas;
    private List<Regimiento> unidadesEspecialesSeleccionados;
    private List<Regimiento> unidadesSingularesSeleccionadas;
    private TextView saveTextView;
    private ListFileOperations listFileOperations;
    private EditText titleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_war);
        listFileOperations = new ListFileOperations();
        findViewsById();

        setCancelAction();
        setEjercitoAction();
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.cargando_ejercitos), true);
        new EjercitosAdapter(this).execute();
    }

    private void setCancelAction() {
        assert cancelImageButton != null;
        cancelImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setEjercitoAction() {
        ejercitoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ejercitoAlertDialog != null) {
                    ejercitoAlertDialog.show();
                }
            }
        });
    }

    private void findViewsById() {
        ejercitoTextView = (TextView) findViewById(R.id.ejercito_textview);
        cancelImageButton = (ImageButton) findViewById(R.id.clear);
        anadirComandate = (ImageButton) findViewById(R.id.anadir_comandante);
        anadirUnidadBasica = (ImageButton) findViewById(R.id.anadir_unidad_basica);
        anadirUnidadEspecial = (ImageButton) findViewById(R.id.anadir_unidad_especial);
        anadirUnidadSingular = (ImageButton) findViewById(R.id.anadir_unidad_singular);
        listaComandates = (ListView) findViewById(R.id.listviewComandantes);
        listaUnidadesBasicas = (ListView) findViewById(R.id.listviewUnidadesBasicas);
        listaUnidadesEspeciales = (ListView) findViewById(R.id.listviewUnidadesEspeciales);
        listaUnidadesSingulares = (ListView) findViewById(R.id.listviewUnidadesSingulares);
        cuentaComandantesTextView = (TextView) findViewById(R.id.cuentaComandantes);
        cuentaUnidadesBasicasTextView = (TextView) findViewById(R.id.cuentaUnidadesBasicas);
        cuentaUnidadesEspecialesTextView = (TextView) findViewById(R.id.cuentaUnidadesEspeciales);
        cuentaUnidadesSingularesTextView = (TextView) findViewById(R.id.cuentaUnidadesSingulares);
        puntosTotalesTextView = (TextView) findViewById(R.id.cuentaTotal);
        saveTextView = (TextView) findViewById(R.id.save);
        titleEditText = (EditText) findViewById(R.id.title);
    }

    public void setOpcionesEjercito(final List<Ejercito> ejercitos) {
        progressDialog.dismiss();
        List<String> listItems = new ArrayList<>();
        for (Ejercito ejercito : ejercitos) {
            listItems.add(ejercito.getNombre());
        }
        final CharSequence[] opcionesEjercitos = listItems.toArray(new CharSequence[listItems.size()]);
        ejercitoAlertDialog = new AlertDialog.Builder(this);
        ejercitoAlertDialog.setTitle(R.string.escoge_ejercito);
        ejercitoAlertDialog.setItems(opcionesEjercitos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ejercitosDesplegableSeleccionado != which + 1) {
                    if (ejercitoSeleccionado != null) {
                        ejercitoSeleccionado.clearEjercito();
                    }
                    puntosTotalesTextView.setText(getString(R.string.cero));

                    iniciarListas();

                    setEjercito(opcionesEjercitos, which, ejercitos);
                }
            }
        });
    }

    private void iniciarListas() {
        comandantesSeleccionados = new ArrayList<>();
        adaptadorComandantes = new ListUnidadesAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                comandantesSeleccionados, listaComandates, cuentaComandantesTextView, puntosTotalesTextView, this);
        iniciarLista(adaptadorComandantes, listaComandates, cuentaComandantesTextView);

        unidadesBasicasSeleccionadas = new ArrayList<>();
        adaptadorUnidadesBasicas = new ListUnidadesAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesBasicasSeleccionadas, listaUnidadesBasicas, cuentaUnidadesBasicasTextView, puntosTotalesTextView, this);
        iniciarLista(adaptadorUnidadesBasicas, listaUnidadesBasicas, cuentaUnidadesBasicasTextView);

        unidadesEspecialesSeleccionados = new ArrayList<>();
        adaptadorUnidadesEspeciales = new ListUnidadesAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesEspecialesSeleccionados, listaUnidadesEspeciales, cuentaUnidadesEspecialesTextView, puntosTotalesTextView, this);
        iniciarLista(adaptadorUnidadesEspeciales, listaUnidadesEspeciales, cuentaUnidadesEspecialesTextView);

        unidadesSingularesSeleccionadas = new ArrayList<>();
        adaptadorUnidadesSingulares = new ListUnidadesAdapter(getBaseContext(), android.R.layout.simple_list_item_1,
                unidadesSingularesSeleccionadas, listaUnidadesSingulares, cuentaUnidadesSingularesTextView, puntosTotalesTextView, this);
        iniciarLista(adaptadorUnidadesSingulares, listaUnidadesSingulares, cuentaUnidadesSingularesTextView);
    }

    private void iniciarLista(ListUnidadesAdapter listUnidadesAdapter, ListView listView, TextView cuenta) {
        listView.setAdapter(listUnidadesAdapter);
        cuenta.setText(R.string.cero);
        listUnidadesAdapter.notifyDataSetChanged();
        ListViewUtil.setListViewHeightBasedOnChildren(listView);
    }

    private void setEjercito(CharSequence[] opcionesEjercitos, int which, List<Ejercito> ejercitos) {
        ejercitoTextView.setText(opcionesEjercitos[which].toString());
        for (Ejercito ejercito : ejercitos) {
            if (ejercito.getId() == (which + 1)) {
                ejercitoSeleccionado = ejercito;
            }
        }
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.cargando_unidades), true);
        ejercitosDesplegableSeleccionado = which + 1;
        new UnidadesAdapter(this, ejercitosDesplegableSeleccionado).execute();
    }

    public void setUnidades(List<Unidad> unidades) {
        progressDialog.dismiss();
        ejercitoSeleccionado.clearEjercito();
        for (Unidad unidad : unidades) {
            if (unidad instanceof Comandante) {
                ejercitoSeleccionado.setComandante((Comandante) unidad);
            } else if (unidad instanceof UnidadBasica) {
                ejercitoSeleccionado.setUnidadBasica((UnidadBasica) unidad);
            } else if (unidad instanceof UnidadEspecial) {
                ejercitoSeleccionado.setUnidadEspecial((UnidadEspecial) unidad);
            } else if (unidad instanceof UnidadSingular) {
                ejercitoSeleccionado.setUnidadSingular((UnidadSingular) unidad);
            }
        }
        populateLists();
    }

    private void populateLists() {
        AlertDialog.Builder comandanteAlertDialog = new AlertDialog.Builder(this);
        CharSequence[] opcionesComandante = getListaOpciones(ejercitoSeleccionado.getComandantes());
        setActionDialog(comandanteAlertDialog, ejercitoSeleccionado.getComandantes(), opcionesComandante, cuentaComandantesTextView,
                comandantesSeleccionados, listaComandates, adaptadorComandantes, R.string.escoge_comandante);
        setAnadirAction(anadirComandate, comandanteAlertDialog);

        AlertDialog.Builder unidadBasicaAlertDialog = new AlertDialog.Builder(this);
        CharSequence[] opcionesUnidadesBasicas = getListaOpciones(ejercitoSeleccionado.getUnidadesBasicas());
        setActionDialog(unidadBasicaAlertDialog, ejercitoSeleccionado.getUnidadesBasicas(), opcionesUnidadesBasicas, cuentaUnidadesBasicasTextView,
                unidadesBasicasSeleccionadas, listaUnidadesBasicas, adaptadorUnidadesBasicas, R.string.escoge_unidad_basica);
        setAnadirAction(anadirUnidadBasica, unidadBasicaAlertDialog);

        AlertDialog.Builder unidadEspecialAlertDialog = new AlertDialog.Builder(this);
        CharSequence[] opcionesUnidadesEspeciales = getListaOpciones(ejercitoSeleccionado.getUnidadesEspeciales());
        setActionDialog(unidadEspecialAlertDialog, ejercitoSeleccionado.getUnidadesEspeciales(), opcionesUnidadesEspeciales, cuentaUnidadesEspecialesTextView,
                unidadesEspecialesSeleccionados, listaUnidadesEspeciales, adaptadorUnidadesEspeciales, R.string.escoge_unidad_especial);
        setAnadirAction(anadirUnidadEspecial, unidadEspecialAlertDialog);

        AlertDialog.Builder unidadSingularAlertDialog = new AlertDialog.Builder(this);
        CharSequence[] opcionesUnidadesSingulares = getListaOpciones(ejercitoSeleccionado.getUnidadesSingulares());
        setActionDialog(unidadSingularAlertDialog, ejercitoSeleccionado.getUnidadesSingulares(), opcionesUnidadesSingulares, cuentaUnidadesSingularesTextView,
                unidadesSingularesSeleccionadas, listaUnidadesSingulares, adaptadorUnidadesSingulares, R.string.escoge_unidad_singular);
        setAnadirAction(anadirUnidadSingular, unidadSingularAlertDialog);
    }

    private CharSequence[] getListaOpciones(List<Unidad> unidades) {
        List<String> listItems = new ArrayList<>();
        for (Unidad unidad : unidades) {
            listItems.add(unidad.getNombre());
        }

        return listItems.toArray(new CharSequence[listItems.size()]);
    }

    private void setActionDialog(AlertDialog.Builder alertDialog, final List<Unidad> unidades,
                                 CharSequence[] opciones, final TextView cuenta,
                                 final List<Regimiento> seleccionados, final ListView listView,
                                 final ListUnidadesAdapter listUnidadesAdapter, int tituloId) {
        alertDialog.setTitle(getResources().getString(tituloId));
        alertDialog.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Regimiento regimiento = new Regimiento(unidades.get(which), unidades.get(which).getTamanyoMinimo());
                try {
                    //regimiento.getId();
                    listaEjercito.addRegimiento(regimiento);
                    int puntos = Integer.parseInt(cuenta.getText().toString());
                    cuenta.setText(String.valueOf(++puntos));
                    seleccionados.add(regimiento);
                    ListViewUtil.setListViewHeightBasedOnChildren(listView);
                    listUnidadesAdapter.notifyDataSetChanged();
                    int puntosTotales = Integer.parseInt(puntosTotalesTextView.getText().toString());
                    puntosTotalesTextView.setText(String.valueOf(puntosTotales + regimiento.getPuntos()));
                    scrollToTheBotton();
                } catch (ListaEjercitoException e) {
                    Toast.makeText(getApplicationContext(), R.string.supera_puntos, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAnadirAction(ImageButton anadir, final AlertDialog.Builder alertDialog) {
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.limite_edittext);

                assert et != null;
                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.rellena_puntos, Toast.LENGTH_SHORT).show();
                } else if (!et.getText().toString().equals("") && !editTextLimitePuntosRellenado) {
                    listaEjercito = new ListaEjercito(Integer.parseInt(et.getText().toString()));
                    saveTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            guardarLista();
                        }
                    });
                    et.setEnabled(false);
                    editTextLimitePuntosRellenado = true;
                    alertDialog.show();
                } else {
                    alertDialog.show();
                }
            }
        });
    }

    private void guardarLista() {

        final String nombreFichero = titleEditText.getText() + ".opw";
        List<String> listas = ListFileOperations.listListas(getBaseContext());
        if (!titleEditText.getText().toString().equals("")) {
            if (listas.contains(nombreFichero)) {
                new AlertDialog.Builder(this)
                        .setTitle("Guardar lista")
                        .setMessage("¿La lista con el título '" + titleEditText.getText() + "' ya existe, desea sobreescribirla?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("-------------- Guardando --------------");
                                ListFileOperations.saveList(getBaseContext(), nombreFichero, listaEjercito);
                                MainActivity.getListAdapter(nombreFichero);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else {
                System.out.println("-------------- Guardando --------------");
                ListFileOperations.saveList(getBaseContext(), nombreFichero, listaEjercito);
                MainActivity.getListAdapter(nombreFichero);
                finish();
            }
        } else {
            Snackbar.make(getWindow().getDecorView(), "El título de la lista está vacío", Snackbar.LENGTH_LONG).show();
        }
    }

    private void scrollToTheBotton() {
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

        assert scrollView != null;
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public boolean removeRegimiento(Regimiento p) {
        return listaEjercito.remove(p);
    }
}