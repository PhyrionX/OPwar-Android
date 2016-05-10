package com.opwar.opwar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.opwar.opwar.activities.BatallaActivity;
import com.opwar.opwar.model.Regimiento;
import com.opwar.opwar.util.ListViewUtil;

import java.util.List;

/**
 * Created by URZU on 25/04/2016.
 */
public class ListBatallaAdapter extends ArrayAdapter<Regimiento> {
    private BatallaActivity batallaActivity;
    private ListView listView;
    private TextView cuentaUnidades;
    private TextView puntosTotales;

    public ListBatallaAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListBatallaAdapter(Context context, int resource, List<Regimiento> items,
                              ListView listView, TextView cuentaUnidades, TextView puntosTotales, BatallaActivity batallaActivity) {
        super(context, resource, items);
        this.listView = listView;
        this.cuentaUnidades = cuentaUnidades;
        this.puntosTotales = puntosTotales;
        this.batallaActivity = batallaActivity;
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
                        remove(p);
                        if (batallaActivity.removeRegimiento(p)) {
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
