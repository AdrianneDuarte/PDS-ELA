package com.pi.classes;

import android.content.Context;

import com.pi.dao.UsuarioDao;
import com.pi.web.IInterfaceWeb;
import com.pi.web.UsuarioWeb;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private long id;
    private String nickname;
    private String senha;
    private String email;
    private int idServidor;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(int idServidor) {
        this.idServidor = idServidor;
    }

    public void salvar(Context context) {

        UsuarioDao.salvar(context, this);
    }

    public void salvarWeb(IInterfaceWeb activity) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(this);
        new UsuarioWeb(usuarios, activity, UsuarioWeb.INSERIR_NO_WS);

    }
}
