CREATE TABLE atendimento(
	idAten SERIAL PRIMARY KEY,
	idTiAt SERIAL,
	idCliente SERIAL,
	dataAte date,
	hora TIME,
	situacao BOOLEAN,
	descricao VARCHAR(20),
	solucao VARCHAR(20),
	CONSTRAINT fkAtenTipo foreign key (idTiAt) REFERENCES tipoAtendimento(idTiAt),
	CONSTRAINT fkAtenClie foreign key (idCliente) REFERENCES tb_cliente(id_cliente)
);