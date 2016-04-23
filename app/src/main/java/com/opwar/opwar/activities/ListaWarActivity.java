package com.opwar.opwar.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.opwar.opwar.R;
import com.opwar.opwar.adapters.EjercitosAdapter;
import com.opwar.opwar.adapters.UnidadesAdapter;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.Ejercito;
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
    private AlertDialog.Builder comandanteAlertDialog;
    private AlertDialog.Builder unidadBasicaAlertDialog;
    private AlertDialog.Builder unidadEspecialAlertDialog;
    private AlertDialog.Builder unidadSingularAlertDialog;
    private List<Unidad> unidades;
    private Ejercito ejercitoSeleccionado;
    private ListaEjercito listaEjercito;
    private EditText limiteEditText;
    private ImageButton anadirComandate;
    private ImageButton anadirUnidadBasica;
    private ImageButton anadirUnidadEspecial;
    private ImageButton anadirUnidadSingular;
    private boolean editTextLimitePuntosRellenado = false;
    private ListView listaComandates;
    private ArrayAdapter<Comandante> adaptadorComandantes;
    private TextView cuentaComandantesTextView;
    private ListView listaUnidadesBasicas;
    private ArrayAdapter<UnidadBasica> adaptadorUnidadesBasicas;
    private TextView cuentaUnidadesBasicasTextView;
    private ListView listaUnidadesEspeciales;
    private ArrayAdapter<UnidadEspecial> adaptadorUnidadesEspeciales;
    private TextView cuentaUnidadesEspecialesTextView;
    private ListView listaUnidadesSingulares;
    private ArrayAdapter<UnidadSingular> adaptadorUnidadesSingulares;
    private TextView cuentaUnidadesSingularesTextView;
    private ProgressDialog progressDialog;
    private TextView puntosTotalesTextView;
    private int puntosTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_war);

        findViewsById();

        setCancelAction();
        setEjercitoAction();
        progressDialog = ProgressDialog.show(this, "Cargando ejércitos...", "", true);
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
        limiteEditText = (EditText) findViewById(R.id.limite_edittext);
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
    }

    public void setOpcionesEjercito(final List<Ejercito> ejercitos) {
        progressDialog.dismiss();
        List<String> listItems = new ArrayList<>();
        for (Ejercito ejercito : ejercitos) {
            listItems.add(ejercito.getNombre());
        }
        final CharSequence[] opcionesEjercitos = listItems.toArray(new CharSequence[listItems.size()]);
        ejercitoAlertDialog = new AlertDialog.Builder(this);
        ejercitoAlertDialog.setTitle("Escoge un ejército");
        ejercitoAlertDialog.setItems(opcionesEjercitos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setEjercito(opcionesEjercitos, which, ejercitos);
            }
        });
    }

    private void setEjercito(CharSequence[] opcionesEjercitos, int which, List<Ejercito> ejercitos) {
        ejercitoTextView.setText(opcionesEjercitos[which].toString());
        for (Ejercito ejercito : ejercitos) {
            if (ejercito.getId() == (which + 1)) {
                ejercitoSeleccionado = ejercito;
            }
        }
        progressDialog = ProgressDialog.show(this, "Cargando unidades...", "", true);
        new UnidadesAdapter(this, which + 1).execute();
    }

    public void setUnidades(List<Unidad> unidades) {
        progressDialog.dismiss();
        ejercitoSeleccionado.clearEjercito();
        puntosTotales = 0;
        this.unidades = unidades;
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
        populateComandantes(ejercitoSeleccionado.getComandantes());
        populateUnidadesBasicas(ejercitoSeleccionado.getUnidadesBasicas());
        populateUnidadesEspeciales(ejercitoSeleccionado.getUnidadesEspeciales());
        populateUnidadesSingulares(ejercitoSeleccionado.getUnidadesSingulares());
    }

    public void populateComandantes(final List<Comandante> comandantes) {
        final int[] cuentaComandantes = {0};

        anadirComandate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.limite_edittext);

                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellena los puntos", Toast.LENGTH_SHORT).show();
                } else if (!et.getText().toString().equals("") && !editTextLimitePuntosRellenado) {
                    listaEjercito = new ListaEjercito(Integer.parseInt(et.getText().toString()));
                    et.setEnabled(false);
                    editTextLimitePuntosRellenado = true;
                    comandanteAlertDialog.show();
                } else {
                    comandanteAlertDialog.show();
                }
            }
        });
        List<String> listItems = new ArrayList<>();
        for (Comandante comandante : comandantes) {
            listItems.add(comandante.getNombre());
        }

        final List<Comandante> comandantesSeleccionados = new ArrayList<>();
        adaptadorComandantes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comandantesSeleccionados);
        listaComandates.setAdapter(adaptadorComandantes);

        final CharSequence[] opcionesComandantes = listItems.toArray(new CharSequence[listItems.size()]);
        comandanteAlertDialog = new AlertDialog.Builder(this);
        comandanteAlertDialog.setTitle("Escoge un comandante");
        comandanteAlertDialog.setItems(opcionesComandantes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Regimiento reg = new Regimiento(comandantes.get(which), comandantes.get(which).getTamanyoMinimo());
                try {
                    listaEjercito.addRegimiento(reg);
                    cuentaComandantesTextView.setText(String.valueOf(++cuentaComandantes[0]));
                    comandantesSeleccionados.add(comandantes.get(which));
                    ListViewUtil.setListViewHeightBasedOnChildren(listaComandates);
                    adaptadorComandantes.notifyDataSetChanged();
                    puntosTotales += reg.getPuntos();
                    puntosTotalesTextView.setText(String.valueOf(puntosTotales));
                } catch (ListaEjercitoException e) {
                    Toast.makeText(getApplicationContext(), "Superas los puntos permitidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void populateUnidadesBasicas(final List<UnidadBasica> unidadesBasicas) {
        final int[] cuentaUnidadesBasicas = {0};
        anadirUnidadBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.limite_edittext);

                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellena los puntos", Toast.LENGTH_SHORT).show();
                } else if(!et.getText().toString().equals("") && !editTextLimitePuntosRellenado) {
                    listaEjercito = new ListaEjercito(Integer.parseInt(et.getText().toString()));
                    et.setEnabled(false);
                    editTextLimitePuntosRellenado = true;
                    unidadBasicaAlertDialog.show();
                } else {
                    unidadBasicaAlertDialog.show();
                }
            }
        });
        List<String> listItems = new ArrayList<>();
        for (UnidadBasica unidadBasica : unidadesBasicas) {
            listItems.add(unidadBasica.getNombre());
        }

        final List<UnidadBasica> unidadesBasicasSeleccionadas = new ArrayList<>();
        adaptadorUnidadesBasicas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unidadesBasicasSeleccionadas);
        listaUnidadesBasicas.setAdapter(adaptadorUnidadesBasicas);

        final CharSequence[] opcionesUnidadesBasicas = listItems.toArray(new CharSequence[listItems.size()]);
        unidadBasicaAlertDialog = new AlertDialog.Builder(this);
        unidadBasicaAlertDialog.setTitle("Escoge una unidad básica");
        unidadBasicaAlertDialog.setItems(opcionesUnidadesBasicas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Regimiento reg = new Regimiento(unidadesBasicas.get(which),unidadesBasicas.get(which).getTamanyoMinimo());
                try {
                    listaEjercito.addRegimiento(reg);
                    cuentaUnidadesBasicasTextView.setText(String.valueOf(++cuentaUnidadesBasicas[0]));
                    unidadesBasicasSeleccionadas.add(unidadesBasicas.get(which));
                    ListViewUtil.setListViewHeightBasedOnChildren(listaUnidadesBasicas);
                    adaptadorUnidadesBasicas.notifyDataSetChanged();
                    puntosTotales += reg.getPuntos();
                    puntosTotalesTextView.setText(String.valueOf(puntosTotales));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Superas los puntos permitidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void populateUnidadesEspeciales(final List<UnidadEspecial> unidadesEspeciales) {
        final int[] cuentaUnidadesEspeciales = {0};

        anadirUnidadEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.limite_edittext);

                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellena los puntos", Toast.LENGTH_SHORT).show();
                } else if(!et.getText().toString().equals("") && !editTextLimitePuntosRellenado) {
                    listaEjercito = new ListaEjercito(Integer.parseInt(et.getText().toString()));
                    et.setEnabled(false);
                    unidadEspecialAlertDialog.show();
                    editTextLimitePuntosRellenado = true;
                } else {
                    unidadEspecialAlertDialog.show();
                }
            }
        });
        List<String> listItems = new ArrayList<>();
        for (UnidadEspecial unidadEspecial : unidadesEspeciales) {
            listItems.add(unidadEspecial.getNombre());
        }

        final List<UnidadEspecial> unidadesEspecialesSeleccionados = new ArrayList<>();
        adaptadorUnidadesEspeciales = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unidadesEspecialesSeleccionados);
        listaUnidadesEspeciales.setAdapter(adaptadorUnidadesEspeciales);

        final CharSequence[] opcionesUnidadesEspeciales = listItems.toArray(new CharSequence[listItems.size()]);
        unidadEspecialAlertDialog = new AlertDialog.Builder(this);
        unidadEspecialAlertDialog.setTitle("Escoge una unidad especial");
        unidadEspecialAlertDialog.setItems(opcionesUnidadesEspeciales, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Regimiento reg = new Regimiento(unidadesEspeciales.get(which), unidadesEspeciales.get(which).getTamanyoMinimo());
                try {
                    listaEjercito.addRegimiento(reg);
                    cuentaUnidadesEspecialesTextView.setText(String.valueOf(++cuentaUnidadesEspeciales[0]));
                    unidadesEspecialesSeleccionados.add(unidadesEspeciales.get(which));
                    ListViewUtil.setListViewHeightBasedOnChildren(listaUnidadesEspeciales);
                    adaptadorUnidadesEspeciales.notifyDataSetChanged();
                    puntosTotales += reg.getPuntos();
                    puntosTotalesTextView.setText(String.valueOf(puntosTotales));
                    scrollToTheBotton();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Superas los puntos permitidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void populateUnidadesSingulares(final List<UnidadSingular> unidadesSingulares) {
        final int[] cuentaUnidadesSingulares = {0};

        anadirUnidadSingular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.limite_edittext);

                if (et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Rellena los puntos", Toast.LENGTH_SHORT).show();
                } else if(!et.getText().toString().equals("") && !editTextLimitePuntosRellenado) {
                    listaEjercito = new ListaEjercito(Integer.parseInt(et.getText().toString()));
                    et.setEnabled(false);
                    editTextLimitePuntosRellenado = true;
                    unidadSingularAlertDialog.show();
                } else {
                    unidadSingularAlertDialog.show();
                }
            }
        });
        List<String> listItems = new ArrayList<>();
        for (UnidadSingular unidadSingular : unidadesSingulares) {
            listItems.add(unidadSingular.getNombre());
        }

        final List<UnidadSingular> unidadesSingularesSeleccionadas = new ArrayList<>();
        adaptadorUnidadesSingulares = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, unidadesSingularesSeleccionadas);
        listaUnidadesSingulares.setAdapter(adaptadorUnidadesSingulares);

        final CharSequence[] opcionesUnidadesSingulares = listItems.toArray(new CharSequence[listItems.size()]);
        unidadSingularAlertDialog = new AlertDialog.Builder(this);
        unidadSingularAlertDialog.setTitle("Escoge una unidad singular");
        unidadSingularAlertDialog.setItems(opcionesUnidadesSingulares, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Regimiento reg = new Regimiento(unidadesSingulares.get(which),unidadesSingulares.get(which).getTamanyoMinimo());
                try {
                    listaEjercito.addRegimiento(reg);
                    cuentaUnidadesSingularesTextView.setText(String.valueOf(++cuentaUnidadesSingulares[0]));
                    unidadesSingularesSeleccionadas.add(unidadesSingulares.get(which));
                    ListViewUtil.setListViewHeightBasedOnChildren(listaUnidadesSingulares);
                    adaptadorUnidadesSingulares.notifyDataSetChanged();
                    puntosTotales += reg.getPuntos();
                    puntosTotalesTextView.setText(String.valueOf(puntosTotales));
                    scrollToTheBotton();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Superas los puntos permitidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void scrollToTheBotton() {
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}