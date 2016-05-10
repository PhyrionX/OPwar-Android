package com.opwar.opwar.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.opwar.opwar.R;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.ListFileOperations;
import com.opwar.opwar.util.ListPDF;
import com.opwar.opwar.util.NetworkManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int DELETE_MENU = 0;
    private static final int EXPORTAR_MENU = 1;
    private static final int ENVIAR_MENU = 2;
    private ListView listView;
    private static ArrayAdapter<String> itemsAdapter;
    private static List<String> listas;
    private static ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listas = ListFileOperations.listListas(getBaseContext());
        quitarExtension();
        mostrarPantallaPrincipal();

        setBotonAction();
        setSelectItemLista();
        setContextualMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ordenar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ordenar_nombre:
                ordenarPorNombre();
                return true;
            case R.id.ordenar_puntos:
                ordenarPorPuntos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void ordenarPorNombre() {
        Collections.sort(listas);
        itemsAdapter.notifyDataSetChanged();
    }

    private void ordenarPorPuntos() {
        List<ListaEjercito> ejercitos = ListFileOperations.loadAllLists(getApplicationContext());

    }

    private void mostrarPantallaPrincipal() {
        listView = (ListView) findViewById(R.id.war_list);
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listas);
        listView.setAdapter(itemsAdapter);
        viewFlipper = (ViewFlipper) findViewById(R.id.lista_flipper);
        setTitle(R.string.listas_guardadas);
        if (listas.size() != 0) {
            assert listView != null;
            for (String lista : listas) {
                System.out.println(lista);
            }
        } else {
            assert viewFlipper != null;
            viewFlipper.showNext();
        }
    }

    private void setBotonAction() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    NetworkManager.checkNetworkConnection(getApplicationContext());
                    Intent intent = new Intent(MainActivity.this, ListaWarActivity.class);
                    startActivity(intent);
                } catch (IOException e) {
                    Snackbar.make(view, R.string.sin_conexion, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setSelectItemLista() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = (String) listView.getItemAtPosition(position);
                ListaEjercito listaEjercito = ListFileOperations.loadList(getApplicationContext(), seleccionado + Constants.EXTENSION);
                if (listaEjercito != null) {
                    Intent intent = new Intent(MainActivity.this, ListaWarActivity.class);
                    try {
                        NetworkManager.checkNetworkConnection(getApplicationContext());
                        intent.putExtra(Constants.NOMBRE_LISTA, seleccionado);
                        intent.putExtra(Constants.HAY_CONEXION, true);
                        intent.putExtra(Constants.LISTA_EJERCITO, listaEjercito);
                        startActivity(intent);
                    } catch (IOException e) {
                        intent.putExtra(Constants.HAY_CONEXION, false);
                        intent.putExtra(Constants.LISTA_EJERCITO, listaEjercito);
                        startActivity(intent);
                    }
                } else {
                    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                    assert fab != null;
                    Snackbar.make(fab, R.string.erros_cargar_lista, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setContextualMenu() {
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                List<String> listItems = new ArrayList<>();
                listItems.add("Borrar");
                listItems.add("Exportar a PDF");
                listItems.add("Enviar por correo");
                CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
                new AlertDialog.Builder(MainActivity.this)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DELETE_MENU) {
                                    menuDelete(pos);
                                } else if (which == EXPORTAR_MENU) {
                                    menuExportarPDF(pos);
                                } else if (which == ENVIAR_MENU) {
                                    menuEnviar(pos);
                                }
                            }
                        }).show();

                return true;
            }
        });
    }

    private boolean menuDelete(final int pos) {
        final String seleccionado = (String) listView.getItemAtPosition(pos);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Borrar lista")
                .setMessage("¿Desea borrar la lista '" + seleccionado + "'?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("-------------- Borrando --------------");
                        if (ListFileOperations.deleteList(getBaseContext(), seleccionado + Constants.EXTENSION)) {
                            listas.remove(pos);
                            itemsAdapter.notifyDataSetChanged();
                            if (listas.size() == 0) {
                                viewFlipper.showNext();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al borrar la lista", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();
        return true;
    }

    private void menuExportarPDF(int pos) {
        String seleccionado = (String) listView.getItemAtPosition(pos);
        ListaEjercito listaEjercito = ListFileOperations.loadList(getApplicationContext(),
                seleccionado + Constants.EXTENSION);
        if (ListPDF.existsPDF(seleccionado)) {
            ListPDF.deletePDF(seleccionado);
        }
        if (ListPDF.createPDF(seleccionado, listaEjercito)) {
            ListPDF.viewPDF(MainActivity.this, seleccionado);
        } else {
            Toast.makeText(getApplicationContext(), "No se pudo exportar la lista a PDF",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void menuEnviar(int pos) {
        String seleccionado = (String) listView.getItemAtPosition(pos);
        ListaEjercito listaEjercito = ListFileOperations.loadList(getApplicationContext(),
                seleccionado + Constants.EXTENSION);
        String pathFile;
        if (ListPDF.existsPDF(seleccionado)) {
            pathFile = ListPDF.getPDFPath(seleccionado);
        } else {
            if (ListPDF.createPDF(seleccionado, listaEjercito)) {
                pathFile = ListPDF.getPDFPath(seleccionado);
            } else {
                Toast.makeText(getApplicationContext(), "No se pudo exportar la lista a PDF",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent.setType("vnd.android.cursor.dir/email");
        // the attachment
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"
                + pathFile));
        // the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Lista Warhammer: "
                + seleccionado);
        // the mail body
        emailIntent.putExtra(Intent.EXTRA_TEXT, "¡Quiero que eches un vistazo a mi nueva lista de" +
                " Warhammer creada desde la app de OPwar!");
        startActivity(Intent.createChooser(emailIntent,
                "Enviar email..."));
    }

    public static void anadirNuevaLista(String nombreLista) {
        if (listas.size() == 0) {
            viewFlipper.showPrevious();
        }
        listas.add(quitarExtension(nombreLista));
        itemsAdapter.notifyDataSetChanged();
    }

    private void quitarExtension() {
        String nombre;
        for (int i = 0; i < listas.size(); i++) {
            nombre = listas.get(i).substring(0, listas.get(i).length() - Constants.EXTENSION.length());
            listas.remove(i);
            listas.add(i, nombre);
        }
    }

    private static String quitarExtension(String nombre) {
        return nombre.substring(0, nombre.length() - Constants.EXTENSION.length());
    }
}