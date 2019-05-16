/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.tcc.exceptions.EmailDuplicadoException;
import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Endereco;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.facade.ClienteFacade;
import com.ufpr.tads.web2.facade.EnderecoFacade;
import com.ufpr.tads.web2.facade.EstadoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@WebServlet(name = "CadastrarServlet", urlPatterns = {"/CadastrarServlet"})
public class CadastrarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String acao = request.getParameter("action");

        //Comprador us = (Comprador) session.getAttribute("usuario");
        if (acao == null || acao.equals("list")) {
            List<Cliente> clientes;
            try {
                clientes = ClienteFacade.buscarTodosClientes();
                request.setAttribute("clientes", clientes);
            } catch (SQLException | ClassNotFoundException ex) {
                request.setAttribute("javax.servlet.jsp.jspException", ex);
                request.setAttribute("javax.servlet.error.status_code", 500);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
            rd.forward(request, response);
        } else {
            if (acao.equals("formNew")) {
                try {
                    List<Estado> estados = new ArrayList<>();
                    estados = EstadoFacade.buscarTodosEstados();
                    request.setAttribute("estados", estados);
                } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                    request.setAttribute("exception", ex);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                    rd.forward(request, response);
                }
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastro.jsp");
                rd.forward(request, response);
            } else {
                if (acao.equals("new")) {
                    Cliente cliente = new Cliente();
                    try {
                        String email = request.getParameter("email");

                        Cliente us = (Cliente) UsuarioFacade.buscarUsuarioByEmail(email);
                        try {
                            if (us != null && us.getId() != cliente.getId()) {
                                throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
                            }
                        } catch (EmailDuplicadoException ex) {
                            request.setAttribute("msg", ex.getMessage());

                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/CadastrarServlet?action=list");
                            rd.forward(request, response);
                        }

                        cliente.setEmail(email);
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    String cpf;
                    String rg;
                    cliente.setNome(request.getParameter("nome"));
                    cliente.setSobrenome(request.getParameter("sobrenome"));
                    cliente.setSenha(request.getParameter("senha"));

                    String dataNascimentoString = request.getParameter("dataNascimento");
                    DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date data = new Date(fmt.parse(dataNascimentoString).getTime());
                        cliente.setDataNascimento(data);
                    } catch (ParseException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    cpf = request.getParameter("cpf");
                    rg = request.getParameter("rg");

                    cliente.setCpf(cpf.replaceAll("[.-]", ""));
                    cliente.setRg(rg.replaceAll("[.-]", ""));

                    Endereco endereço = new Endereco();
                    endereço.setRua(request.getParameter("rua"));
                    endereço.setCep(request.getParameter("cep"));
                    endereço.setReferencia("comprador");
                    try {
                        endereço.setNumero(Integer.parseInt(request.getParameter("numero")));
                        Cidade cidade = new Cidade();
                        cidade.setId(Integer.parseInt(request.getParameter("cidade")));
                        endereço.setCidade(cidade);
                    } catch (NumberFormatException ex) {
                        request.setAttribute("exception", ex);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }

                    try {
                        ClienteFacade.inserir(cliente);
                        cliente.setIdCliente(ClienteFacade.buscarIdPorDadosCliente(cliente));
                        endereço.setIdReferencia(cliente.getIdCliente());
                        EnderecoFacade.inserir(endereço);
                    } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                        request.setAttribute("javax.servlet.jsp.jspException", ex);
                        request.setAttribute("javax.servlet.error.status_code", 500);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                        rd.forward(request, response);
                    }
                    Cliente us = new Cliente();
                    us.setId(cliente.getId());
                    us.setNome(cliente.getNome());
                    session = request.getSession();
                    session.setAttribute("usuario", us);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/portal.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
