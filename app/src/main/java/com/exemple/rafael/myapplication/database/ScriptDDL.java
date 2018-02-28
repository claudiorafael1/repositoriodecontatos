package com.exemple.rafael.myapplication.database;

/**
 * Created by root on 1/30/18.
 */

public class ScriptDDL {
    public  static String getCreateTableCliente(){
        StringBuilder sql= new StringBuilder();
        sql.append(" CREATE TABLE CLIENTE (");
            sql.append("   CODIGO INTEGER PRIMARY KEY NOT NULL DEFAULT(''),");
            sql.append(" NOME VARCHAR (250) NOT NULL DEFAULT(''),");
            sql.append("ENDERECO VARCHAR (255) NOT NULL DEFAULT(''),");
            sql.append(" EMAIL VARCHAR (200) NOT NULL DEFAULT(''),");
            sql.append("TELEFONE VARCHAR (20) NOT NULL DEFAULT(''))");



        return sql.toString();

    }
}
