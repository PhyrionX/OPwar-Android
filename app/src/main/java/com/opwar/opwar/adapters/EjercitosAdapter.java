package com.opwar.opwar.adapters;

import android.os.AsyncTask;

import com.opwar.opwar.activities.ListaWarActivity;
import com.opwar.opwar.model.Ejercito;
import com.opwar.opwar.util.Constants;

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
 * Created by URZU on 13/04/2016.
 */
public class EjercitosAdapter extends AsyncTask<Void, Void, List<Ejercito>> {
    private ListaWarActivity listaWarActivity;
    private StringBuilder response = new StringBuilder();

    public EjercitosAdapter(ListaWarActivity listaWarActivity) {
        this.listaWarActivity = listaWarActivity;
    }

    @Override
    protected List<Ejercito> doInBackground(Void... params) {
        List<Ejercito> ejercitos = new ArrayList<>();
        try {
            URL url = new URL(Constants.URL_GET_EJERCITOS);

            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setReadTimeout(10000);
            httpUrlConnection.setConnectTimeout(15000);
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setDoInput(true);

            int status = httpUrlConnection.getResponseCode();

            if (status == HttpURLConnection.HTTP_OK) {
                getResponse(httpUrlConnection.getInputStream());
                ejercitos = getEjercitoFromJson(response.toString());
            } else {
                getResponse(httpUrlConnection.getErrorStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ejercitos;
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


    private List<Ejercito> getEjercitoFromJson(String json) {
        List<Ejercito> ejercitos = new ArrayList<>();
        json = json.substring(1, json.length() - 1);
        try {
            JSONObject jsonObject = new JSONObject(json);
            ejercitos.add(new Ejercito(jsonObject.getInt("id_ejercito"),
                    jsonObject.getString("nombre"),
                    jsonObject.getString("descripcion")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ejercitos;
    }

    @Override
    protected void onPostExecute(List<Ejercito> ejercitos) {
        super.onPostExecute(ejercitos);

        listaWarActivity.setOpcionesEjercito(ejercitos);
    }
}