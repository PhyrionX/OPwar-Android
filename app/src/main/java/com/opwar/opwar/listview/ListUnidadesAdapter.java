package com.opwar.opwar.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.opwar.opwar.R;
import com.opwar.opwar.model.Unidad;

import java.util.List;

/**
 * Created by URZU on 25/04/2016.
 */
public class ListUnidadesAdapter extends ArrayAdapter<Unidad> {

    public ListUnidadesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListUnidadesAdapter(Context context, int resource, List<Unidad> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.unidad_list_view, null);
        }

        Unidad p = getItem(position);

        if (p != null) {
            TextView textView = (TextView) view.findViewById(R.id.unidadTextView);

            if (textView != null) {
                textView.setText(p.getNombre());
            }
        }

        return view;
    }
}
