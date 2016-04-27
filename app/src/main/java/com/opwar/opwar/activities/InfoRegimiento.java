package com.opwar.opwar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.util.Constants;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_regimiento);
        this.regimiento = (Regimiento) getIntent().getSerializableExtra(Constants.REGIMIENTO);
        iniciarAtributos();
    }

    private void iniciarAtributos() {
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
    }
}
