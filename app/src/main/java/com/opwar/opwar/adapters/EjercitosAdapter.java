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

/**
 * Created by URZU on 13/04/2016.
 */
public class EjercitosAdapter extends AsyncTask<String, String, String> {
    private ListaWarActivity listaWarActivity;
    private StringBuilder response = new StringBuilder();
    private Ejercito ejercito;

    public EjercitosAdapter(ListaWarActivity listaWarActivity) {
        this.listaWarActivity = listaWarActivity;
    }

    @Override
    protected String doInBackground(String... params) {
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
                getEjercitoFromJson(response.toString());
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


    private void getEjercitoFromJson(String json) {
        json = json.substring(1, json.length() - 1);
        try {
            JSONObject jsonObject = new JSONObject(json);
            ejercito = new Ejercito(jsonObject.getInt("id_ejercito"),
                    jsonObject.getString("nombre"),
                    jsonObject.getString("descripcion"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        listaWarActivity.setText(ejercito);
    }
}