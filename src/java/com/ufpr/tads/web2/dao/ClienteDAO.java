/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ClienteDAO {
    private Connection conn;

    public ClienteDAO() throws SQLException, ClassNotFoundException {
        this.conn = ConnectionFactory.getConnection();
    }
    
    public String selectNameById (int id) throws SQLException {
        String sql = "SELECT nome_cliente "
                + "FROM tb_cliente "
                + "WHERE id_cliente = (?);";
        
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        String nome = "";
        
        while (rs.next()) {
            nome = rs.getString("nome_cliente");
        }
        return nome;
    }
    
}
