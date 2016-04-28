package com.opwar.opwar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.activities.InfoRegimiento;
import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.util.Constants;
import com.opwar.opwar.util.ListViewUtil;

import java.util.List;

/**
 * Created by URZU on 25/04/2016.
 */
public class ListUnidadesAdapter extends ArrayAdapter<Regimiento> {
    private ListaWarActivity listaWar;
    private ListView listView;
    private TextView cuentaUnidades;
    private TextView puntosTotales;

    public ListUnidadesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListUnidadesAdapter(Context context, int resource, List<Regimiento> items,
                               ListView listView, TextView cuentaUnidades, TextView puntosTotales, ListaWarActivity listaWar) {
        super(context, resource, items);
        this.listView = listView;
        this.cuentaUnidades = cuentaUnidades;
        this.puntosTotales = puntosTotales;
        this.listaWar = listaWar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.unidad_list_view, null);
        }

        final Regimiento p = getItem(position);

        if (p != null) {
            final TextView textView = (TextView) view.findViewById(R.id.unidadTextView);
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.borrarUnidad);

            if (textView != null) {
//                String textoOp = String.format("%sx %s    [%dpts]\n" +
//                                               "M  HA HP F  R  H  I  AT L \n" +
//                                               "%02d %02d  %02d  %02d %02d %02d %02d %02d  %02d",
//                                                p.getTamanyo(),
//                                                p.getUnidad().getNombre(),
//                                                p.getPuntos(),8,4,4,5,4,3,2,4,5);
                String textoOp = String.format("%sx %s    [%dpts]\n",
                                                p.getTamanyo(),
                                                p.getUnidad().getNombre(),
                                                p.getPuntos());
                textView.setText(textoOp);
            }
            if (textView != null) {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.err.println(p.getUnidad().getNombre());
                        Intent intent = new Intent(listaWar, InfoRegimiento.class);
                        intent.putExtra(Constants.REGIMIENTO, p);
                        listaWar.startActivityForResult(intent, 1);
                    }
                });
            }
            if (imageButton != null) {
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remove(p);
                        if (listaWar.removeRegimiento(p)) {
                            ListViewUtil.setListViewHeightBasedOnChildren(listView);
                            cuentaUnidades.setText(String.valueOf(Integer.parseInt(cuentaUnidades.getText().toString()) - 1));
                            puntosTotales.setText(String.valueOf(Integer.parseInt(puntosTotales.getText().toString()) - (p.getPuntos())));
                        }
                    }
                });
            }
        }

        return view;
    }
}
