package com.opwar.opwar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.opwar.opwar.model.Regimiento;

import java.util.List;

/**
 * Created by URZU on 25/04/2016.
 */
public class ListBatallaAdapter extends ArrayAdapter<Regimiento> {
    private TextView puntosTotales;

    public ListBatallaAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListBatallaAdapter(Context context, int resource, List<Regimiento> items, TextView puntosTotales) {
        super(context, resource, items);
        this.puntosTotales = puntosTotales;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.unidad_batalla, null);
        }

        final Regimiento p = getItem(position);

        if (p != null) {
            final TextView textView = (TextView) view.findViewById(R.id.unidadTextView);
            Button imageButton = (Button) view.findViewById(R.id.btMinus1Unidad);

            if (textView != null) {
                String textoOp = String.format("%sx %s    [%dpts]\n",
                                                p.getTamanyo(),
                                                p.getUnidad().getNombre(),
                                                p.getPuntos());
                textView.setText(textoOp);
            }
            if (imageButton != null) {
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String numero = null;
                        if (textView != null) {
                            numero = textView.getText().toString();
                            numero = numero.substring(0, numero.indexOf("x"));
                            int num = Integer.parseInt(numero) - 1;
                            if (num >= 0) {
                                puntosTotales.setText(String.valueOf(Integer.parseInt(puntosTotales
                                        .getText().toString()) - p.getUnidad().getPuntos()));
                                Log.d("numero", numero);
                                String textoOp = String.format("%sx %s    [%dpts]\n",
                                        num,
                                        p.getUnidad().getNombre(),
                                        p.getUnidad().getPuntos() * num);
                                textView.setText(textoOp);
                            }
                        }
                    }
                });
            }
        }

        return view;
    }
}
