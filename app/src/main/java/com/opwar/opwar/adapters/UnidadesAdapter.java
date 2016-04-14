package com.opwar.opwar.adapters;

import android.os.AsyncTask;

import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.model.Comandante;
import com.opwar.opwar.model.Unidad;
import com.opwar.opwar.model.UnidadBasica;
import com.opwar.opwar.model.UnidadEspecial;
import com.opwar.opwar.model.UnidadSingular;
import com.opwar.opwar.util.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by URZU on 14/04/2016.
 */
public class UnidadesAdapter extends AsyncTask<String, String, String> {
    private ListaWarActivity listaWarActivity;
    private StringBuilder response = new StringBuilder();
    private List<Unidad> unidades;
    private int idEjercito;

    public UnidadesAdapter(ListaWarActivity listaWarActivity, int idEjercito) {
        this.listaWarActivity = listaWarActivity;
        this.idEjercito = idEjercito;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(Constants.URL_GET_UNIDADES + idEjercito);

            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setReadTimeout(10000);
            httpUrlConnection.setConnectTimeout(15000);
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setDoInput(true);

            int status = httpUrlConnection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                getResponse(httpUrlConnection.getInputStream());
                getUnidadesFromJson(response.toString());
            } else {
                getResponse(httpUrlConnection.getErrorStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private void getResponse(InputStream inputStream) {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getUnidadesFromJson(String json) {
        unidades = new ArrayList<>();
        try {
            JSONObject o = new JSONObject(json);
            JSONArray jsonArray = o.getJSONArray("unidades");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonOb = new JSONObject(jsonArray.get(i).toString());
                switch (jsonOb.getInt("tipo_id")) {
                    case 1: unidades.add(new Comandante(jsonOb.getInt("id_unidad"), jsonOb.getString("nombre"),
                                jsonOb.getInt("movimiento"), jsonOb.getInt("habilidad_armas"),
                                jsonOb.getInt("habilidad_proyectiles"), jsonOb.getInt("fuerza"),
                                jsonOb.getInt("resistencia"), jsonOb.getInt("heridas"),
                                jsonOb.getInt("iniciativa"), jsonOb.getInt("ataques"),
                                jsonOb.getInt("liderazgo"), jsonOb.getInt("puntos"),
                                jsonOb.getInt("tamanyoMinimo")));
                        break;
                    case 2: unidades.add(new UnidadBasica(jsonOb.getInt("id_unidad"), jsonOb.getString("nombre"),
                                jsonOb.getInt("movimiento"), jsonOb.getInt("habilidad_armas"),
                                jsonOb.getInt("habilidad_proyectiles"), jsonOb.getInt("fuerza"),
                                jsonOb.getInt("resistencia"), jsonOb.getInt("heridas"),
                                jsonOb.getInt("iniciativa"), jsonOb.getInt("ataques"),
                                jsonOb.getInt("liderazgo"), jsonOb.getInt("puntos"),
                                jsonOb.getInt("tamanyoMinimo")));
                        break;
                    case 3: unidades.add(new UnidadEspecial(jsonOb.getInt("id_unidad"), jsonOb.getString("nombre"),
                                jsonOb.getInt("movimiento"), jsonOb.getInt("habilidad_armas"),
                                jsonOb.getInt("habilidad_proyectiles"), jsonOb.getInt("fuerza"),
                                jsonOb.getInt("resistencia"), jsonOb.getInt("heridas"),
                                jsonOb.getInt("iniciativa"), jsonOb.getInt("ataques"),
                                jsonOb.getInt("liderazgo"), jsonOb.getInt("puntos"),
                                jsonOb.getInt("tamanyoMinimo")));
                        break;
                    case 4: unidades.add(new UnidadSingular(jsonOb.getInt("id_unidad"), jsonOb.getString("nombre"),
                                jsonOb.getInt("movimiento"), jsonOb.getInt("habilidad_armas"),
                                jsonOb.getInt("habilidad_proyectiles"), jsonOb.getInt("fuerza"),
                                jsonOb.getInt("resistencia"), jsonOb.getInt("heridas"),
                                jsonOb.getInt("iniciativa"), jsonOb.getInt("ataques"),
                                jsonOb.getInt("liderazgo"), jsonOb.getInt("puntos"),
                                jsonOb.getInt("tamanyoMinimo")));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        listaWarActivity.setOpcionesUnidades(unidades);
    }
}
