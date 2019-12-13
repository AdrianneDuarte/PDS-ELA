package com.pi.classes;

import android.content.Context;

import com.pi.dao.PerguntaDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pergunta {
    private int id;
    private String tempoVerbal;
    private String pergunta;
    private String resp1;
    private String resp2;
    private String resp3;
    private String resp4;
    private int correta;
    private List<String> opcoes;

    public Pergunta() {
        opcoes = new ArrayList<>();
    }

    public static Pergunta getPergunta(Context context, int id) {
        String where = "id=" + id;
        return PerguntaDao.buscar(context, where, null, "", "", "");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempoVerbal() {
        return tempoVerbal;
    }

    public void setTempoVerbal(String tempoVerbal) {
        this.tempoVerbal = tempoVerbal;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResp1() {
        return resp1;
    }


    public void setResp1(String resp1) {
        this.resp1 = resp1;
        if (!resp1.isEmpty()) {
            opcoes.add(resp1);
        }
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
        if (!resp2.isEmpty()) {
            opcoes.add(resp2);
        }
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
        if (!resp3.isEmpty()) {
            opcoes.add(resp3);
        }
    }

    public String getResp4() {
        return resp4;
    }

    public void setResp4(String resp4) {
        this.resp4 = resp4;
        if (!resp4.isEmpty()) {
            opcoes.add(resp4);
        }
    }

    public List<String> getOpcoes() {
        return opcoes;
    }

    public int getCorreta() {
        return correta;
    }

    public void setCorreta(int correta) {
        this.correta = correta;
    }
}
