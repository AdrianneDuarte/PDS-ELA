package com.pi.dao;

import android.content.Context;

import com.pi.classes.Usuario;

/**
 * Created by Marcelo Júnior on 12/12/2017.
 */

public class UsuarioDao {
    public static final String TABELA = "usuario";
    public static final String ID = "id";
    public static final String NICKNAME = "nickname";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static final String ID_SERVIDOR = "id_servidor";

    private static boolean inserir(Context context, Usuario usuario) {
        Object[] valor = null;
        String[] campo = null;

        if (usuario.getIdServidor() == 0) {
            valor = new Object[]{usuario.getNickname(), usuario.getEmail(), usuario.getSenha()};
            campo = new String[]{NICKNAME, EMAIL, SENHA};
        } else {
            valor = new Object[]{usuario.getIdServidor(), usuario.getNickname(), usuario.getEmail(), usuario.getSenha()};
            campo = new String[]{ID_SERVIDOR, NICKNAME, EMAIL, SENHA};
        }
        Banco bd = new Banco(context);
        long id = bd.inserir(TABELA, campo, valor);
        bd.close();
        if (id > 0) {
            usuario.setId(id);
            return true;
        }

        return false;
    }

    private static boolean atualizar(Context context, Usuario usuario) {
        Object[] valor = null;
        String[] campo = null;


        if (usuario.getIdServidor() == 0) {
            valor = new Object[]{usuario.getNickname(), usuario.getEmail(), usuario.getSenha()};
            campo = new String[]{EMAIL, NICKNAME, SENHA};
        } else {
            valor = new Object[]{usuario.getIdServidor(), usuario.getNickname(), usuario.getEmail(), usuario.getSenha()};
            campo = new String[]{ID_SERVIDOR, EMAIL, NICKNAME, SENHA};
        }

        Banco bd = new Banco(context);
        long linhasAfetadas = bd.atualizar(TABELA, campo, valor, usuario.getId());
        bd.close();

        if (linhasAfetadas > 0) {
            return true;
        }

        return false;
    }

    public static boolean salvar(Context context, Usuario usuario) {
        if (usuario.getId() == 0)
            return inserir(context, usuario);
        else
            return atualizar(context, usuario);
    }

    /*
        public static boolean excluir(Context context, Produto produto) {
            if(produto.getStatus() == EStatus.OK_EXCLUIR) {
                Banco bd = new Banco(context);
                int n = bd.excluir(TABELA, produto.getId());
                bd.close();
                if (n == 1) {
                    return true;
                }
                return false;
            }
            else{
                return salvar(context, produto);
            }
        }

        public static List<Produto> buscar(Context context){
            //removerTabela(context);
            return buscar(context, null, null, null, null, null);
        }

        public static List<Produto> buscar(Context context, String where, String[] argumento){
            //removerTabela(context);
            return buscar(context, where, argumento, null, null, null);
        }

        public static List<Produto> buscar(Context context, String where, String[] argumento, String groupBy, String having, String orderBy){
            Banco bd = new Banco(context);
            List<Produto> produtos = new ArrayList<>();

            try{
                Cursor cursor = bd.buscar(TABELA, new String[]{ID, TEMPO_VERBAL, PERGUNTA, RESP_2, CORRETA}, where, argumento, groupBy, having, orderBy);

                while(!cursor.isAfterLast()){

                    Produto produto = new Produto(
                            cursor.getLong(cursor.getColumnIndex(ID)),
                            cursor.getLong(cursor.getColumnIndex(CORRETA)),
                            cursor.getFloat(cursor.getColumnIndex(PERGUNTA)),
                            cursor.getString(cursor.getColumnIndex(TEMPO_VERBAL)),
                            EStatus.valueOf(cursor.getString(cursor.getColumnIndex(RESP_2))));
                    produtos.add(produto);
                    cursor.moveToNext();
                }
                cursor.close();
            }
            catch (SQLiteException e){
                if(e.getMessage().startsWith("no such table")){
                    criarTabela(context);
                    produtos = buscar(context, where, argumento, groupBy, having, orderBy);
                }
                else{
                    e.printStackTrace();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return produtos;
        }
    */
    private static void criarTabela(Context context) {
        Banco bd = new Banco(context);
        bd.criarTabela(TABELA, new String[]{ID, NICKNAME, EMAIL, SENHA, ID_SERVIDOR},
                new String[]{Banco.INTEGER_PRIMARY_KEY_AUTOINCREMENT,
                        Banco.TEXT_NOT_NULL_UNIQUE, Banco.TEXT_NOT_NULL_UNIQUE, Banco.TEXT_NOT_NULL, Banco.INTEGER_UNIQUE});
        bd.close();
    }

    private static void removerTabela(Context context) {
        Banco bd = new Banco(context);
        bd.removeTabela(TABELA);
    }

}
