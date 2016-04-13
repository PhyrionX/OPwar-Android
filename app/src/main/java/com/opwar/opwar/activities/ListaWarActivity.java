package com.opwar.opwar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.adapters.EjercitosAdapter;
import com.opwar.opwar.model.Ejercito;

public class ListaWarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_war);

        ImageButton cancelImageButton = (ImageButton) findViewById(R.id.clear);

        assert cancelImageButton != null;
        cancelImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new EjercitosAdapter(this).execute();
    }

    public void setText(Ejercito ejercito) {
        TextView textView = (TextView) findViewById(R.id.text);
        if (textView != null) {
            if (ejercito != null) {
                String texto = "ID: " + ejercito.getId() + "\nNombre: " + ejercito.getNombre() + "\nDescripcion: " + ejercito.getDescripcion();
                textView.setText(texto);
            } else {
                textView.setText("Nop");
            }
        }
    }
}