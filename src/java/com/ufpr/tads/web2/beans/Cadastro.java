/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
    public class Cadastro extends Usuario implements Serializable{
            private int idCadastro;
            private String nome;
            private String sobrenome;
            private String rg;
            private String cpf;
            private Date dataNascimento;


    public Cadastro(int id, String email, String senha, String tipo, Endereco endereco) {
        super(id, email, senha, tipo, endereco);
            }
    

            public int getIdCadastro() {
                return idCadastro;
            }

            public void setIdCadastro(int idCadastro) {
                this.idCadastro = idCadastro;
            }

            public String getNome() {
                return nome;
            }

            public void setNome(String nome) {
                this.nome = nome;
            }

            public String getSobrenome() {
                return sobrenome;
            }

            public void setSobrenome(String sobrenome) {
                this.sobrenome = sobrenome;
            }

            public String getRg() {
                return rg;
            }

            public void setRg(String rg) {
                this.rg = rg;
            }

            public String getCpf() {
                return cpf;
            }

            public void setCpf(String cpf) {
                this.cpf = cpf;
            }

            public Date getDataNascimento() {
                return dataNascimento;
            }

            public void setDataNascimento(Date dataNascimento) {
                this.dataNascimento = dataNascimento;
            }   
    
}
