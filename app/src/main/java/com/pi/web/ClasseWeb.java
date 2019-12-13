package com.pi.web;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;

/**
 * Created by Marcelo Júnior on 13/12/2017.
 */

public abstract class ClasseWeb extends AsyncTask<Integer, Void, Object> {

    //CONSTANTES QUE REPRESENTAM OS MÉTODOS SUPORTADOS PELO SERVIÇO
    public final String GET = "GET";
    public final String POST = "POST";
    public final String PUT = "PUT";
    public final String DELETE = "DELETE";

    //CONSTANTES INTERNAS QUE AUXILIAM NO CÓDIGO DA CLASSE
    private final int SEGUNDOS = 1000;
    private final String TAG = this.getClass().getSimpleName();
    private final String CONTENT_TYPE = "Content-Type";
    private final String APPLICATION_JSON = "application/json";

    //VARIÁVEIS GLOBAIS DA CLASSE
    private HttpURLConnection conexao;
    private IInterfaceWeb interfaceGrafica;
    private int codigoDaRequisicao;
    public int codigoDeResposta; //ESTA DEVE SER ATRIBUÍDA NOS MÉTODOS DA CLASSE HERDEIRA, VALOR PADRÃO = RESULT_OK

    //------------------------------MÉTODOS CONSTRUTORES

    /**
     * CONSTRUTOR QUE RECEBE A REFERÊNCIA PARA A Activity QUE RECEBERÁ A RESPOSTA DA TAREFA
     * E UM INTEIRO QUE IDENTIFIQUE A REQUISIÇÃO
     *
     * @param interfaceGrafica   REFERÊNCIA PARA A Activity QUE RECEBERÁ A RESPOSTA
     * @param codigoDaRequisicao INTEIRO QUE IDENTIFIQUE A REQUISIÇÃO
     */
    public ClasseWeb(IInterfaceWeb interfaceGrafica, int codigoDaRequisicao) {
        this.interfaceGrafica = interfaceGrafica;
        this.codigoDaRequisicao = codigoDaRequisicao;
        if (this.getStatus() != Status.RUNNING) {
            this.execute(new Integer[]{this.codigoDaRequisicao});
        }
    }

    /**
     * CONSTRUTOR QUE RECEBE A REFERÊNCIA PARA A Activity QUE RECEBERÁ A RESPOSTA DA TAREFA
     * E QUE O SERVIÇO NÃO PRECISE QUE IDENTIFIQUE A REQUISIÇÃO
     *
     * @param interfaceWeb REFERÊNCIA PARA A Activity QUE RECEBERÁ A RESPOSTA
     */
    public ClasseWeb(IInterfaceWeb interfaceWeb) {
        this(interfaceWeb, 0);
    }

    //------------------------------FIM DOS MÉTODOS CONSTRUTORES

    //------------------------------MÉTODOS A SEREM SOBRESCRITOS

    /**
     * MÉTODO QUE DETERMINA O QUE SERÁ REALIZADO NA THREAD PARALELA
     * O MÉTODO chamarWebservice DEVE SER CHAMADO DE DENTRO DESSE MÉTODO
     *
     * @param codigoDaRequisicao INTEIRO QUE IDENTIFICA A REQUISIÇÃO A SER ATENDIDA
     * @return O OBJETO DE RESPOSTA (CASO SEJA ESPERADO)
     */
    public abstract Object executarTarefaWeb(int codigoDaRequisicao);

    /**
     * MÉTODO QUE DEVE RETORNAR O JSON ESPERADO PELO WEBSERVICE
     *
     * @param o OBJETO A SER CONVERTIDO EM JSON
     * @return OBJETO JSON
     */
    public abstract JSONObject getJson(Object o);

    //------------------------------FIM DOS MÉTODOS A SEREM SOBRESCRITOS

    //------------------------------MÉTODO DISPONÍVEL PARA CONSULTA E RETORNO DO WEBSERVICE

    /**
     * MÉTODO QUE, ATRAVÉS DOS PARÂMETROS, CONSTROI E OBTÉM A RESPOSTA DO WEBSERVICE.
     * DEVE SER CHAMADO DENTRO DA IMPLEMENTAÇÃO DO MÉTODO executarTarefaWeb.
     *
     * @param urlWebservice URL DO WEBSERVICE A SER CONSULTADO
     * @param metodo        CONSTANTES DESSA CLASSE: GET, POST, PUT & DELETE
     * @param o             PARA OS MÉTODOS GET & DELETE, É ESPERADA STRING (PARÂMETROS DA URL); PARA OS MÉTODOS
     *                      POST E PUT, É ESPERADO OBJETO QUE SEJA CONVERTIDO EM JSON PELA IMPLEMENTAÇÃO DO MÉTODO getJson
     * @return OBJETO JSON COM A RESPOSTA DO WEBSERVICE
     */
    public JSONObject chamarWebservice(String urlWebservice, String metodo, Object o) {
        boolean fazOutput = false;
        switch (metodo) {
            case DELETE:
                urlWebservice += "/";
                break;
            case GET:
                urlWebservice += "?";
                break;
            case POST:
            case PUT:
                fazOutput = true;
                break;
        }
        if (!fazOutput) {
            String parametros = String.valueOf(o);
            urlWebservice += parametros;
        }

        if (conectar(urlWebservice, metodo, fazOutput)) {
            Log.i(TAG, "Conectado ao Webservice...\nURL: '" + urlWebservice + "'\nMétodo: '" + metodo + "'");
            if (fazOutput) {
                try {
                    OutputStream outputStream = this.conexao.getOutputStream();
                    outputStream.write(getJson(o).toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Falha ao chamarWebservice os dados...\nERRO: " + e.getMessage());
                } catch (NullPointerException e) {
                    Log.e(TAG, "Verifique se o método getJson(Object o) foi implementado...\nERRO: " + e.getMessage());
                } catch (Exception e) {
                    Log.e(TAG, "Falha não identificada...\nERRO: " + e.getMessage());
                }
            }
        } else {
            Log.e(TAG, "Falha ao conectar ao Webservice...\nURL: '" + urlWebservice + "'\nMétodo: '" + metodo + "'");
        }
        try {
            int codigoDeResposta = conexao.getResponseCode();
            if (codigoDeResposta == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conexao.getInputStream();
                String resposta = streamParaString(inputStream);
                inputStream.close();

                if (resposta.startsWith("<br")) {
                    Log.e(TAG, "Erro no Servidor:\n" + resposta);
                } else {
                    JSONObject json = new JSONObject(resposta);
                    return json;
                }
            } else {
                Log.e(TAG, "Falha no código resposta do servidor...\nCódigo: " + codigoDeResposta);
            }
        } catch (IOException e) {
            Log.e(TAG, "Falha na resposta do servidor...\nERRO: " + e.getMessage());
        } catch (JSONException e) {
            Log.e(TAG, "Falha na criação do objeto Json...\nERRO: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Falha não identificada...\nERRO: " + e.getMessage());
        }
        return null;
    }

    //------------------------------FIM DO MÉTODO DISPONÍVEL PARA CONSULTA E RETORNO DO WEBSERVICE

    //------------------------------MÉTODOS DA CLASSE AsyncTask

    /**
     * MÉTODO QUE CHAMA O MÉTODO preTarefaWeb DA INTERFACE GRÁFICA
     * E DEFINE O CÓDIGO DA RESPOSTA COMO = RESULT_OK
     */
    @Override
    protected final void onPreExecute() {
        super.onPreExecute();
        this.codigoDeResposta = Activity.RESULT_OK;
        this.interfaceGrafica.preTarefaWeb();
    }

    /**
     * MÉTODO QUE CHAMA O MÉTODO executarTarefaWeb EM UMA THREAD PARALELA
     *
     * @param integers NA POSIÇÃO [0] É RECEBIDO O CÓDIGO DA REQUISIÇÃO
     * @return O OBJETO DE RESPOSTA (CASO SEJA ESPERADO)
     */
    @Override
    protected final Object doInBackground(Integer... integers) {
        int requestCode = 0;
        if (integers.length > 0) {
            requestCode = integers[0];
        }
        return this.executarTarefaWeb(requestCode);
    }

    /**
     * MÉTODO QUE CHAMA O MÉTODO posTarefaWeb DA INTERFACE GRÁFICA INFORMANDO:
     * O CÓDIGO DA REQUISIÇÃO, QUE PERMITE DIFERENCIAR AS RESPOSTAS
     * O CÓDIGO DE RESPOSTA OBITIDO
     * E O OBJETO DE RESPOSTA (CASO SEJA ESPERADO)
     *
     * @param o OBJETO RECEBIDO PELO MÉTODO executarTarefaWeb (CASO SEJA ESPERADO)
     */
    @Override
    protected final void onPostExecute(Object o) {
        super.onPostExecute(o);
        this.interfaceGrafica.posTarefaWeb(this.codigoDaRequisicao, this.codigoDeResposta, o);
    }

    //------------------------------FIM DOS MÉTODOS DA CLASSE AsyncTask

    //------------------------------MÉTODOS PRIVADOS QUE DÃO SUPORTE ÀS FUNCIONALIDADES DA CLASSE

    /**
     * MÉTODO RESPONSÁVEL POR CONSTRUIR O OBJETO HttpURLConnection PARA CONSULTA AO WEBSERVICE
     *
     * @param urlWebService URL DO WEBSERVICE
     * @param metodo        CONSTANTES DESSA CLASSE: GET, POST, PUT & DELETE
     * @param fazOutput     true SE HÁ JSON A SER ENVIADO PARA O WEBSERVICE
     * @return true SE A CONEXÃO FOI ESTABELECIDA COM SUCESSO
     */
    private final boolean conectar(String urlWebService, String metodo, boolean fazOutput) {
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

    /**
     * LÊ O INPUTSTREAM RECEBIDO DO WEBSERVICE E CONVERTE PARA STRING
     *
     * @param is INPUTSTREAM RECEBIDO DO WEBSERVICE
     * @return STRING DOS DADOS RECEBIDOS DO WEBSERVICE
     */
    private final String streamParaString(InputStream is) {
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

}
