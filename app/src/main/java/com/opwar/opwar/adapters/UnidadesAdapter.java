package com.opwar.opwar.adapters;

import android.os.AsyncTask;

import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.model.Unidad;
import com.opwar.opwar.util.Constants;

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
        json = json.replace("[", "{").replace("]", "}");
        try {
     /**       JSONObject jsonObject = new JSONObject(json);
            unidades.add(new Ejercito(jsonObject.getInt("id_ejercito"),
                    jsonObject.getString("nombre"),
                    jsonObject.getString("descripcion")));**/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
