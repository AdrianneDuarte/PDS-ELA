package com.pi.web;


import com.pi.classes.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcelo Júnior on 13/12/2017.
 */

public class UsuarioWeb extends ClasseWeb {
    public static final int BUSCAR_DO_WS = 2;
    public static final int INSERIR_NO_WS = 3;
    public static final int INSERIR = 4;
    public static final int ATUALIZAR = 5;
    public static final int EXCLUIR = 6;
    public static final int OK = 7;
    public static final int OK_EXCLUIR = 8;
    //private final String WEBSERVICE = "http://10.163.1.58/exemploWebService/wsproduto.php";
    private final String WEBSERVICE = "http://10.42.0.1/ElaWeb/wsSalvarUsuario.php";
    private List<Usuario> usuarios;

    public UsuarioWeb(List<Usuario> usuarios, IInterfaceWeb interfaceWeb, int codigoDaRequisicao) {
        super(interfaceWeb, codigoDaRequisicao);
        this.usuarios = usuarios;
    }

    @Override
    public Object executarTarefaWeb(int codigoDaRequisicao) {
        switch (codigoDaRequisicao) {
            case BUSCAR_DO_WS:
                return getUsuarios();
            case INSERIR_NO_WS:
                return inserirUsuarios();
            default:
                return null;
        }
    }

    @Override
    public JSONObject getJson(Object o) {
        Usuario usuario = (Usuario) o;
        try {
            JSONObject json = new JSONObject();
            json.put("id", usuario.getIdServidor());
            json.put("nickname", usuario.getNickname());
            json.put("email", usuario.getEmail());
            json.put("senha", usuario.getSenha());
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Usuario> inserirUsuarios() {
        JSONObject json = null;
        for (Usuario p : this.usuarios) {

            try {
                json = chamarWebservice(WEBSERVICE, POST, p);
                if (json != null) {
                    p.setIdServidor(json.getInt("id"));
                    p.setStatus(OK);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return usuarios;
    }

    private List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        JSONObject json = null;
        try {
            json = chamarWebservice(WEBSERVICE, GET, "id=3");
            if (json != null) {
                JSONArray array = json.getJSONArray("itens");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject o = array.getJSONObject(i);

                    Usuario usuario = new Usuario();
                    usuario.setIdServidor(o.getInt("id"));
                    usuario.setNickname(o.getString("nickname"));
                    usuario.setEmail(o.getString("email"));
                    usuario.setSenha(o.getString("senha"));
                    usuario.setStatus(OK);


                    usuarios.add(usuario);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
