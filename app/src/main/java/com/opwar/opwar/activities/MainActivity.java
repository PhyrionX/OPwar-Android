package com.opwar.opwar.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.opwar.opwar.R;
import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.ListFileOperations;
import com.opwar.opwar.model.ListaEjercito;
import com.opwar.opwar.util.NetworkManager;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private static ArrayAdapter<String> itemsAdapter;
    private static List<String> listas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listas = ListFileOperations.listListas(getBaseContext());
        mostrarPantallaPrincipal();

        setBotonAction();
        setActionItemLista();
    }

    private void mostrarPantallaPrincipal() {
        listView = (ListView) findViewById(R.id.war_list);
        if (listas.size() != 0) {
            setTitle(R.string.listas_guardadas);
            itemsAdapter =
                    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listas);
            assert listView != null;
            listView.setAdapter(itemsAdapter);
            for (String lista : listas) {
                System.out.println(lista);
            }
        } else {
            ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.lista_flipper);
            assert viewFlipper != null;
            viewFlipper.showNext();
        }
    }

    public static void getListAdapter(String nombreLista){
        listas.add(nombreLista);
        itemsAdapter.notifyDataSetChanged();
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

    private void setActionItemLista() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = (String) listView.getItemAtPosition(position);
                ListaEjercito listaEjercito = ListFileOperations.loadList(getApplicationContext(), seleccionado);
                if (listaEjercito != null) {
                    Intent intent = new Intent(MainActivity.this, ListaWarActivity.class);
                    try {
                        NetworkManager.checkNetworkConnection(getApplicationContext());
                        intent.putExtra(Constants.HAY_CONEXION, true);
                        intent.putExtra(Constants.LISTA_EJERCITO, listaEjercito);
                        startActivity(intent);
                    } catch (IOException e) {
                        intent.putExtra(Constants.HAY_CONEXION, true);
                        intent.putExtra(Constants.LISTA_EJERCITO, listaEjercito);
                        startActivity(intent);
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
}