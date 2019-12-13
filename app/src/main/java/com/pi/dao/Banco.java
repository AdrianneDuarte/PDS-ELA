package com.pi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marcelo Júnior on 12/12/2017.
 */

public class Banco extends SQLiteOpenHelper {

    //CONSTANTES QUE DEFINEM O BANCO
    private static final String BANCO = "ela";
    private static final int VERSAO = 1;

    //MODIFICADORES DOS CAMPOS DAS TABELAS
    public static final String INTEGER = "INTEGER";
    public static final String INTEGER_NOT_NULL = "INTEGER NOT NULL";
    public static final String INTEGER_UNIQUE = "INTEGER UNIQUE";
    public static final String INTEGER_PRIMARY_KEY_AUTOINCREMENT = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String REAL = "REAL";
    public static final String TEXT = "TEXT";
    public static final String TEXT_NOT_NULL = "TEXT NOT NULL";
    public static final String TEXT_NOT_NULL_UNIQUE = "TEXT NOT NULL UNIQUE";
    public static final String DATETIME_NOT_NULL = "DATETIME NOT NULL";

    public Banco(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    protected void criarTabela(String tabela, String[] campo, String[] modificador) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(tabela);
        sql.append(" (");
        for (int i = 0; i < campo.length; i++) {
            if (i > 0) sql.append(", ");
            sql.append(campo[i]);
            sql.append(" ");
            sql.append(modificador[i]);
        }
        sql.append(");");

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql.toString());
        db.close();
    }

    protected void removeTabela(String tabela) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE " + tabela + ";");
        db.close();
    }

    protected long inserir(String tabela, String[] campo, Object[] valor) {
        long id = -1;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = getContentValues(campo, valor);

        id = db.insert(tabela, null, cv);
        db.close();

        return id;
    }

    protected long atualizar(String tabela, String[] campo, Object[] valor, long id) {
        long linhasAfetadas = -1;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = getContentValues(campo, valor);

        linhasAfetadas = db.update(tabela, cv, "id=?", new String[]{String.valueOf(id)});
        db.close();

        return linhasAfetadas;
    }

    protected int excluir(String tabela, long id) {
        int linhasAfetadas = 0;
        SQLiteDatabase db = getWritableDatabase();
        linhasAfetadas = db.delete(tabela, "id=?", new String[]{String.valueOf(id)});
        db.close();

        return linhasAfetadas;
    }

    protected Cursor buscar(String tabela, String[] campo, String orderBy) {
        return buscar(tabela, campo, null, null, orderBy);
    }

    protected Cursor buscar(String tabela, String[] campo, String where, String[] argumento, String orderBy) {
        return buscar(tabela, campo, where, argumento, null, null, orderBy);
    }

    protected Cursor buscar(String tabela, String[] campo, String where, String[] argumento, String groupBy, String having, String orderBy) throws SQLiteException {
        Cursor cursor = null;

        SQLiteDatabase db = getReadableDatabase();
        cursor = db.query(tabela, campo, where, argumento, groupBy, having, orderBy);

        if (cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    private ContentValues getContentValues(String[] campo, Object[] valor) {
        ContentValues cv = new ContentValues();

        for (int i = 0; i < campo.length; i++) {
            if (valor[i] instanceof String) {
                cv.put(campo[i], (String) valor[i]);
            } else if (valor[i] instanceof Long) {
                cv.put(campo[i], (Long) valor[i]);
            } else if (valor[i] instanceof Double) {
                cv.put(campo[i], (Double) valor[i]);
            } else if (valor[i] instanceof Float) {
                cv.put(campo[i], (Float) valor[i]);
            }
        }
        return cv;
    }
    protected void executar(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
}
