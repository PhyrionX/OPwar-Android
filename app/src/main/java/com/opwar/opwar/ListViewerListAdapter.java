package com.opwar.opwar;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.model.ListaStats;

import java.util.List;

/**
 * Created by phyrion on 10/05/16.
 */
public class ListViewerListAdapter extends ArrayAdapter<ListaStats> {
    private ListaWarActivity listaWar;
    private ListView listView;

    public ListViewerListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListViewerListAdapter(Context context, int resource, List<ListaStats> items,
                               ListView listView) {
        super(context, resource, items);
        this.listView = listView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.ejercitos_list_view, null);
        }
        final ListaStats le = getItem(position);
        String porcentaje = "N/A";
        if ((le.getVictorias() + le.getDerrotas()) != 0) {
            double calculo = (double) le.getVictorias() / (le.getVictorias() + le.getDerrotas());
            porcentaje = String.valueOf(calculo);
        }
        final TextView textView = (TextView) view.findViewById(R.id.ejercitoTextView);
        CharSequence fecha = DateFormat.format("dd/MM/yyyy", le.getFecha());
        String text = le.getNombre() + " [" + le.getPuntos() + " pts] V: " + le.getVictorias() +
                " D: " + le.getDerrotas() + " V/D: " + porcentaje + " (" +  fecha + ")";
        textView.setText(text);

        return view;
    }
}
