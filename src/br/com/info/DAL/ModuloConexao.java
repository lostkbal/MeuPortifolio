/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.[DAL - DATA ACESS LAYER]
 */
package br.com.info.DAL;

import java.sql.*;

/**
 *
 * @author User
 */
public class ModuloConexao { //Classe Modulo Conexao

    //Método responsável por estabelecer a conexão com o Banco Dados.
    public static Connection conector() {
        java.sql.Connection conexao = null;

        //Chamar o driver de conexao
        String driver = "com.mysql.jdbc.Driver";

        //Armazenando Informações do banco de dados
        String url = "jdbc:mysql://localhost:3306/dbinfo";
        String user = "root";
        String password = "";

        //Estabelecendo a Conexão com o Banco de Dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
