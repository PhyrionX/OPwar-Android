package com.opwar.opwar.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.adapters.EjercitosAdapter;
import com.opwar.opwar.model.Ejercito;

import java.util.ArrayList;
import java.util.List;

public class ListaWarActivity extends AppCompatActivity {
    private ImageButton cancelImageButton;
    private TextView ejercitoTextView;
    private AlertDialog.Builder ejercitoAlertDialog;

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
    }

    public void setOpcionesEjercito(List<Ejercito> ejercitos) {
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
                ejercitoTextView.setText(opcionesEjercitos[which].toString());
            }
        });
    }
}