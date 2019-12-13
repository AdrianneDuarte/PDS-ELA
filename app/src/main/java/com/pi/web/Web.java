package com.pi.web;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Marcelo Júnior on 13/12/2017.
 */

public abstract class Web {

    public final String GET = "GET";
    public final String POST = "POST";
    public final String PUT = "PUT";
    public final String DELETE = "DELETE";

    private final int SEGUNDOS = 1000;
    private final String CONTENT_TYPE = "Content-Type";
    private final String APPLICATION_JSON = "application/json";

    private HttpURLConnection conexao;
    private WebTask webTask;

    protected boolean conectar(String urlWebService) {
        return conectar(urlWebService, GET, false);
    }

    protected boolean conectar(String urlWebService, String metodo) {
        return conectar(urlWebService, metodo, false);
    }

    protected boolean conectar(String urlWebService, String metodo, boolean fazOutput) {
        try {
            URL url = new URL(urlWebService);
            this.conexao = (HttpURLConnection) url.openConnection();
            this.conexao.setReadTimeout(15 * SEGUNDOS);
            this.conexao.setConnectTimeout(10 * SEGUNDOS);
            this.conexao.setRequestMethod(metodo);
            this.conexao.setDoInput(true);
            this.conexao.setDoOutput(fazOutput);

            if (fazOutput) {
                this.conexao.addRequestProperty(CONTENT_TYPE, APPLICATION_JSON);
            }
            this.conexao.connect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void onPreExecute() {

    }

    public void onPostExecute(Object o) {

    }

    public abstract Object doInBackground(int requestCode);

    public String getDados() {
        try {
            return streamParaString(this.conexao.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String streamParaString(InputStream is) {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos = 0;
        try {
            while ((lidos = is.read(bytes)) > 0) {
                baos.write(bytes, 0, lidos);
            }
            return new String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executar(String urlWebService, int requestCode) {
        if (webTask == null || webTask.getStatus() != AsyncTask.Status.RUNNING) {
            this.webTask = new WebTask(urlWebService, this);
            this.webTask.execute(new Integer[]{requestCode});
        }
    }
}
