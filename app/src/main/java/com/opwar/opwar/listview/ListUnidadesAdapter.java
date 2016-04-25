package com.opwar.opwar.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.model.Unidad;
import com.opwar.opwar.util.ListViewUtil;

import java.util.List;

/**
 * Created by URZU on 25/04/2016.
 */
public class ListUnidadesAdapter extends ArrayAdapter<Unidad> {
    private ListView listView;
    private TextView cuentaUnidades;
    private TextView puntosTotales;

    public ListUnidadesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListUnidadesAdapter(Context context, int resource, List<Unidad> items,
                               ListView listView, TextView cuentaUnidades, TextView puntosTotales) {
        super(context, resource, items);
        this.listView = listView;
        this.cuentaUnidades = cuentaUnidades;
        this.puntosTotales = puntosTotales;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.unidad_list_view, null);
        }

        final Unidad p = getItem(position);

        if (p != null) {
            final TextView textView = (TextView) view.findViewById(R.id.unidadTextView);
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.borrarUnidad);

            if (textView != null) {
                textView.setText(p.getNombre());
            }

            if (imageButton != null) {
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remove(p);
                        ListViewUtil.setListViewHeightBasedOnChildren(listView);
                        cuentaUnidades.setText(getContext().getString(R.string.cero));
                        puntosTotales.setText(String.valueOf(Integer.parseInt(puntosTotales.getText().toString()) - (p.getPuntos() * p.getTamanyoMinimo())));
                    }
                });
            }
        }

        return view;
    }
}
