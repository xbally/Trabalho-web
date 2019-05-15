/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.dao.ClienteDAO;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class ClienteFacade {
    
    public static String buscarNomePorId(int id) throws SQLException, ClassNotFoundException {
        ClienteDAO cad = new ClienteDAO();
        return cad.selectNameById(id);
    }

}
