package com.opwar.opwar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ViewFlipper;

import com.opwar.opwar.R;
import com.opwar.opwar.util.NetworkManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        // TODO que muestre la otra vista únicamente si no hay listas guardadas
        viewFlipper = (ViewFlipper) findViewById(R.id.lista_flipper);
        viewFlipper.showNext();
    }
}
