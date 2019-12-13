package com.pi.web;

import android.os.AsyncTask;

/**
 * Created by Marcelo Júnior on 13/12/2017.
 */

public class WebTask extends AsyncTask<Integer, Void, Object> {

    private Web classe;

    public WebTask(String urlWebService, Web classe) {
        super();
        this.classe = classe;
    }

    @Override
    protected Object doInBackground(Integer... integers) {
        int requestCode = 0;
        if (integers.length > 0) {
            requestCode = integers[0];
        }
        return this.classe.doInBackground(requestCode);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.classe.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        this.classe.onPostExecute(o);
    }
}
