/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Gabriel
 * Created: 14/05/2019
 */


CREATE TABLE tb_usuario (
    id_usuario SERIAL PRIMARY KEY,
    email_usuario VARCHAR(100) UNIQUE,
    senha_usuario VARCHAR(200),
    id_referencia INT,
    tipo_usuario CHAR(1) CHECK (tipo_usuario IN ('c', 'f', 'g')),
    ativo_usuario BOOLEAN DEFAULT true
);


CREATE TABLE tb_cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome_cliente VARCHAR(50),
    sobrenome_cliente VARCHAR(100),
    rg_cliente VARCHAR(15),
    cpf_cliente CHAR(11) UNIQUE,
    data_nascimento_cliente DATE
);


CREATE TABLE tb_gerente (
    id_gerente SERIAL PRIMARY KEY,
    nome_gerente VARCHAR(50),
    sobrenome_gerente VARCHAR(100),
    rg_gerente VARCHAR(15),
    cpf_gerente CHAR(11) UNIQUE,
    data_nascimento_gerente DATE
);

CREATE TABLE tb_funcionario (
    id_funcionario SERIAL PRIMARY KEY,
    nome_funcionario VARCHAR(50),
    sobrenome_funcionario VARCHAR(100),
    rg_funcionario VARCHAR(15),
    cpf_funcionario CHAR(11) UNIQUE,
    data_nascimento_funcionario DATE
);


/*A senha é admin para todos estes usuários*/
INSERT INTO tb_cliente (nome_cliente, sobrenome_cliente, rg_cliente, cpf_cliente, data_nascimento_cliente) VALUES ('Admin', 'da Silva', '111111111', '11111111111', '1997-05-21');
INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ('admin@admin.com', '21232f297a57a5a743894a0e4a801fc3', 1, 'c');
INSERT INTO tb_endereco (rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) VALUES ('Rua teste', 123, '12345678', 2878, 'cliente', 1);

INSERT INTO tb_gerente (nome_gerente, sobrenome_gerente, rg_gerente, cpf_gerente, data_nascimento_gerente) VALUES ('Comprador', 'da Silva', '111111111', '2222222222', '1997-05-21');
INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ('comprador@comprador.com', '21232f297a57a5a743894a0e4a801fc3', 1, 'g');
INSERT INTO tb_endereco (rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) VALUES ('Rua teste', 123, '12345678', 2878, 'comprador', 1);

INSERT INTO tb_funcionario (nome_funcionario, sobrenome_funcionario, rg_funcionario, cpf_funcionario, data_nascimento_funcionario) VALUES ('Organizador', 'Reponsavel', '111111111', '2222222282', '1997-05-21');
INSERT INTO tb_usuario (email_usuario, senha_usuario, id_referencia, tipo_usuario) VALUES ('organizador@organizador.com', '21232f297a57a5a743894a0e4a801fc3', 1, 'f');
INSERT INTO tb_endereco (rua_endereco, numero_endereco, cep_endereco, id_cidade, referencia_endereco, id_referencia) VALUES ('Rua teste', 123, '12345678', 2878, 'funcionario', 1);