package com.opwar.opwar.adapters;

import android.os.AsyncTask;

import com.opwar.opwar.util.Constants;

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
    private StringBuilder response = new StringBuilder();

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
                //getEjercitoFromJson(response.toString());
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

    /**
    private List<Ejercito> getEjercitoFromJson(String json) {
        labels = new ArrayList<>();
        try {
            JSONObject o = new JSONObject(json);
            JSONArray jsonArray = o.getJSONArray("etiquetas");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonOb = new JSONObject(jsonArray.get(i).toString());
                labels.add(new Label(jsonOb.getString("etiqueta"),
                        jsonOb.getString("color")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.reverse(labels);

        return labels;
    }**/


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
