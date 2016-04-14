package com.opwar.opwar.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.adapters.EjercitosAdapter;
import com.opwar.opwar.adapters.UnidadesAdapter;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.Ejercito;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.model.Unidad;
import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.model.UnidadEspecial;
import com.opwar.opwar.model.UnidadSingular;

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
    private List<Regimiento> listaEjercito;
    private EditText limiteEditText;
    private ImageButton anadirComandate;
    private ImageButton anadirUnidadBasica;
    private ImageButton anadirUnidadEspecial;
    private ImageButton anadirUnidadSingular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_war);

        findViewsById();

        setCancelAction();
        setEjercitoAction();
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
    }

    public void setOpcionesEjercito(final List<Ejercito> ejercitos) {
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
        new UnidadesAdapter(this, which + 1).execute();
    }

    public void setUnidades(List<Unidad> unidades) {
        ejercitoSeleccionado.clearEjercito();
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

    public void populateComandantes(List<Comandante> comandantes) {
        anadirComandate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comandanteAlertDialog.show();
            }
        });
        List<String> listItems = new ArrayList<>();
        for (Comandante comandante : comandantes) {
            listItems.add(comandante.getNombre());
        }
        final CharSequence[] opcionesComandantes = listItems.toArray(new CharSequence[listItems.size()]);
        comandanteAlertDialog = new AlertDialog.Builder(this);
        comandanteAlertDialog.setTitle("Escoge un comandante");
        comandanteAlertDialog.setItems(opcionesComandantes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.comandantes_seleccionados);
                if (textView != null) {
                    textView.append(opcionesComandantes[which].toString() + "\n");
                }
            }
        });
    }

    public void populateUnidadesBasicas(List<UnidadBasica> unidadesBasicas) {
        anadirUnidadBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unidadBasicaAlertDialog.show();
            }
        });
        List<String> listItems = new ArrayList<>();
        for (UnidadBasica unidadBasica : unidadesBasicas) {
            listItems.add(unidadBasica.getNombre());
        }
        final CharSequence[] opcionesUnidadesBasicas = listItems.toArray(new CharSequence[listItems.size()]);
        unidadBasicaAlertDialog = new AlertDialog.Builder(this);
        unidadBasicaAlertDialog.setTitle("Escoge una unidad básica");
        unidadBasicaAlertDialog.setItems(opcionesUnidadesBasicas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.unidades_basicas_seleccionadas);
                if (textView != null) {
                    textView.append(opcionesUnidadesBasicas[which].toString() + "\n");
                }
            }
        });
    }

    public void populateUnidadesEspeciales(List<UnidadEspecial> unidadEspeciales) {
        anadirUnidadEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unidadEspecialAlertDialog.show();
            }
        });
        List<String> listItems = new ArrayList<>();
        for (UnidadEspecial unidadEspecial : unidadEspeciales) {
            listItems.add(unidadEspecial.getNombre());
        }
        final CharSequence[] unidadesEspeciales = listItems.toArray(new CharSequence[listItems.size()]);
        unidadEspecialAlertDialog = new AlertDialog.Builder(this);
        unidadEspecialAlertDialog.setTitle("Escoge una unidad especial");
        unidadEspecialAlertDialog.setItems(unidadesEspeciales, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.unidades_espaciales_seleccionadas);
                if (textView != null) {
                    textView.append(unidadesEspeciales[which].toString() + "\n");
                }
            }
        });
    }

    public void populateUnidadesSingulares(List<UnidadSingular> unidadesSingulares) {
        anadirUnidadSingular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unidadSingularAlertDialog.show();
            }
        });
        List<String> listItems = new ArrayList<>();
        for (UnidadSingular unidadSingular : unidadesSingulares) {
            listItems.add(unidadSingular.getNombre());
        }
        final CharSequence[] opcionesUnidadesEspeciales = listItems.toArray(new CharSequence[listItems.size()]);
        unidadSingularAlertDialog = new AlertDialog.Builder(this);
        unidadSingularAlertDialog.setTitle("Escoge una unidad singular");
        unidadSingularAlertDialog.setItems(opcionesUnidadesEspeciales, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) findViewById(R.id.unidades_singulares_seleccionadas);
                if (textView != null) {
                    textView.append(opcionesUnidadesEspeciales[which].toString() + "\n");
                }
            }
        });
    }
}