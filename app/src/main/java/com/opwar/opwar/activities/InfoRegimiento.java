package com.opwar.opwar.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.TamanyoMinimoUnidadException;


public class InfoRegimiento extends AppCompatActivity {

    private Regimiento regimiento;
    private TextView tvMovimiento;
    private TextView tvHabilidadArmas;
    private TextView tvHabilidadProyectiles;
    private TextView tvFuerza;
    private TextView tvResistencia;
    private TextView tvHeridas;
    private TextView tvIniciativa;
    private TextView tvAtaques;
    private TextView tvLiderazgo;
    private TextView tvPuntos;
    private TextView tvNombreUnidad;
    private TextView tvTamanyoUnidad;
    private TextView tvPuntosUnidad;
    private Button btPlus1Unidad;
    private Button btMinus1Unidad;
    private TextView tvSave;
    private int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_regimiento);

        this.regimiento = (Regimiento) getIntent().getSerializableExtra(Constants.REGIMIENTO);
        puntos = this.regimiento.getPuntos();
        IniciarAtributos();
    }

    private void IniciarAtributos() {
        tvNombreUnidad = (TextView) findViewById(R.id.tvNombreUnidad);
        tvMovimiento = (TextView) findViewById(R.id.tvMovimiento);
        tvHabilidadArmas = (TextView) findViewById(R.id.tvHabilidadArmas);
        tvHabilidadProyectiles = (TextView) findViewById(R.id.tvHabilidadProyectiles);
        tvFuerza = (TextView) findViewById(R.id.tvFuerza);
        tvResistencia = (TextView) findViewById(R.id.tvResistencia);
        tvHeridas = (TextView) findViewById(R.id.tvHeridas);
        tvIniciativa = (TextView) findViewById(R.id.tvIniciativa);
        tvAtaques = (TextView) findViewById(R.id.tvAtaques);
        tvLiderazgo = (TextView) findViewById(R.id.tvLiderazgos);
        tvPuntos = (TextView) findViewById(R.id.tvPuntos);
        tvTamanyoUnidad = (TextView) findViewById(R.id.tvTamanyoUnidad);
        tvPuntosUnidad = (TextView) findViewById(R.id.tvPuntosUnidad);
        btPlus1Unidad = (Button) findViewById(R.id.btPlus1Unidad);
        btMinus1Unidad = (Button) findViewById(R.id.btMinus1Unidad);
        tvSave = (TextView) findViewById(R.id.btSave);

        tvNombreUnidad.setText(String.valueOf(regimiento.getUnidad().getNombre()));
        tvMovimiento.setText(String.valueOf(regimiento.getUnidad().getMovimiento()));
        tvHabilidadArmas.setText(String.valueOf(regimiento.getUnidad().getHabilidadArmas()));
        tvHabilidadProyectiles.setText(String.valueOf(regimiento.getUnidad().getHabilidadProyectiles()));
        tvFuerza.setText(String.valueOf(regimiento.getUnidad().getFuerza()));
        tvResistencia.setText(String.valueOf(regimiento.getUnidad().getResistencia()));
        tvHeridas.setText(String.valueOf(regimiento.getUnidad().getHeridas()));
        tvIniciativa.setText(String.valueOf(regimiento.getUnidad().getIniciativa()));
        tvAtaques.setText(String.valueOf(regimiento.getUnidad().getAtaques()));
        tvLiderazgo.setText(String.valueOf(regimiento.getUnidad().getLiderazgo()));
        tvPuntos.setText(String.valueOf(regimiento.getUnidad().getPuntos()));
        tvTamanyoUnidad.setText(String.valueOf(regimiento.getTamanyo()));
        tvPuntosUnidad.setText(String.valueOf(regimiento.getPuntos()));

        if (regimiento.getUnidad().getTamanyoMinimo() == 1) {
            btMinus1Unidad.setVisibility(View.INVISIBLE);
            btPlus1Unidad.setVisibility(View.INVISIBLE);
        } else {
            btPlus1Unidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int tamanyo = Integer.parseInt(tvTamanyoUnidad.getText().toString());
                    tvTamanyoUnidad.setText(String.valueOf(tamanyo + 1));
                    regimiento.addUnit();
                    tvPuntosUnidad.setText(String.valueOf(regimiento.getPuntos()));
                }
            });


            btMinus1Unidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int tamanyo = Integer.parseInt(tvTamanyoUnidad.getText().toString());
                        regimiento.removeUnit();
                        tvTamanyoUnidad.setText(String.valueOf(tamanyo - 1));
                        tvPuntosUnidad.setText(String.valueOf(regimiento.getPuntos()));
                    } catch (TamanyoMinimoUnidadException e) {
                        Snackbar.make(getWindow().getDecorView(), "El tamaño mínimo de la unidad es " + regimiento.getUnidad().getTamanyoMinimo(), Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        }
        
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.err.println(puntos + "  " + regimiento.getPuntos());
                if (puntos != regimiento.getPuntos()) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", regimiento);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    finish();
                }
            }
        });

    }
}
