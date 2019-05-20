CREATE TABLE produto(
	idProd SERIAL PRIMARY KEY,
	idAten SERIAL,
	idCat SERIAL,
	nome VARCHAR(20),
	descricao VARCHAR(20),
	peso REAL,
	CONSTRAINT fkProdAten foreign key (idAten) REFERENCES atendimento(idAten),
	CONSTRAINT fkProdCat foreign key (idCat) REFERENCES categoria(idCat)
);