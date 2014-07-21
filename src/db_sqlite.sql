CREATE TABLE IF NOT EXISTS Local (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	cidade	VARCHAR(254),
	estado	VARCHAR(254),
	descricao	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS InstituicaoSubmissao (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS InstituicaoCooperadora (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS FonteFinanciamento (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS Curso (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS AreaFormacao (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS AreaConhecimento (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254),
	ciencia VARCHAR(254),
	areaAvaliacao VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS Pesquisador (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	nome	VARCHAR(254),
	nome_cientifico	VARCHAR(254),
	email	VARCHAR(254),
	sexo	VARCHAR(254),
	classe	VARCHAR(254),
	titulacao	VARCHAR(254),
	curso_vinculado	INTEGER,
	areaformacao	INTEGER,
	curriculo_lattes VARCHAR(300),
	FOREIGN KEY (curso_vinculado) REFERENCES Curso(id),
	FOREIGN KEY (areaformacao) REFERENCES AreaFormacao(id)
);

CREATE TABLE IF NOT EXISTS Pesquisa (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	titulo	VARCHAR(254),
	orientador	INTEGER,
	pesquisador_responsavel	INTEGER,
	ano_submissao	INTEGER,
	tempo_duracao	INTEGER,
	tipo	VARCHAR(254),
	qualificacao	VARCHAR(254),
	impacto_pesquisa	VARCHAR(254),
	gerou_patente	BOOL,
	status	VARCHAR(254),
	resultado	VARCHAR(254),
	instituicao_submissao	INTEGER,
	fonte_financiamento	INTEGER,
	area_conhecimento_CNPq	INTEGER,
	local	INTEGER,
	resumo	TEXT,
	FOREIGN KEY (pesquisador_responsavel) REFERENCES Pesquisador(id),
	FOREIGN KEY (orientador) REFERENCES Pesquisador(id),
	FOREIGN KEY (instituicao_submissao) REFERENCES InstituicaoSubmissao(id),
	FOREIGN KEY (fonte_financiamento) REFERENCES FonteFinanciamento(id),
	FOREIGN KEY (area_conhecimento_CNPq) REFERENCES AreaConhecimento(id),
	FOREIGN KEY (local) REFERENCES Local(id)
	
);

CREATE TABLE Pesquisacolaboradores(
	id1 INTEGER NOT NULL,
	id2 INTEGER NOT NULL,
	FOREIGN KEY (id1) REFERENCES Pesquisa(id),
	FOREIGN KEY (id2) REFERENCES Pesquisador(id)
);

CREATE TABLE Pesquisapalavras_chave(
	id1 INTEGER NOT NULL,
	id2 INTEGER NOT NULL,
	FOREIGN KEY (id1) REFERENCES Pesquisa(id),
	FOREIGN KEY (id2) REFERENCES PalavraChave(id)
);

CREATE TABLE Pesquisainstituicoes_cooperadoras(
	id1 INTEGER NOT NULL,
	id2 INTEGER NOT NULL,
	FOREIGN KEY (id1) REFERENCES Pesquisa(id),
	FOREIGN KEY (id2) REFERENCES InstituicaoCooperadora(id)
);

CREATE TABLE IF NOT EXISTS PalavraChave (
	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	palavra	VARCHAR(254)
);

CREATE TABLE IF NOT EXISTS pesquisalocal (
	id1 INTEGER NOT NULL,
	id2 INTEGER NOT NULL,
	FOREIGN KEY (id1) REFERENCES Pesquisa(id),
	FOREIGN KEY (id2) REFERENCES Local(id)
);

INSERT INTO `curso`(`nome`) VALUES 
('Bacharelado em Ciências Biológicas'),
('Bacharelado em Ciências Econômicas'),
('Bacharelado em Sistemas de Informação'),
('Bacharelado em Agronomia'),
('Engenharia de Pesca'),
('Licenciatura em Química'),
('Licenciatura em Letras'),
('Administração'),
('Zootecnia');

INSERT INTO `local`(`cidade`, `estado`, `descricao`) VALUES 
('Abreu e Lima', 'PE',''),
('Afogados da Ingazeira', 'PE',''),
('Afrânio', 'PE',''),
('Agrestina', 'PE',''),
('Água Preta', 'PE',''),
('Águas Belas', 'PE',''),
('Alagoinha', 'PE',''),
('Aliança', 'PE',''),
('Altinho', 'PE',''),
('Amaraji', 'PE',''),
('Angelim', 'PE',''),
('Araçoiaba', 'PE',''),
('Araripina', 'PE',''),
('Arcoverde', 'PE',''),
('Barra de Guabiraba', 'PE',''),
('Barreiros', 'PE',''),
('Belém de Maria', 'PE',''),
('Belém de São Francisco', 'PE',''),
('Belo Jardim', 'PE',''),
('Betânia', 'PE',''),
('Bezerros', 'PE',''),
('Bodocó', 'PE',''),
('Bom Conselho', 'PE',''),
('Bom Jardim', 'PE',''),
('Bonito', 'PE',''),
('Brejão', 'PE',''),
('Brejinho', 'PE',''),
('Brejo da Madre de Deus', 'PE',''),
('Buenos Aires', 'PE',''),
('Buíque', 'PE',''),
('Cabo de Santo Agostinho', 'PE',''),
('Cabrobó', 'PE',''),
('Cachoeirinha', 'PE',''),
('Caetés', 'PE',''),
('Calçado', 'PE',''),
('Calumbi', 'PE',''),
('Camaragibe', 'PE',''),
('Camocim de São Félix', 'PE',''),
('Camutanga', 'PE',''),
('Canhotinho', 'PE',''),
('Capoeiras', 'PE',''),
('Carnaíba', 'PE',''),
('Carnaubeira da Penha', 'PE',''),
('Carpina', 'PE',''),
('Caruaru', 'PE',''),
('Casinhas', 'PE',''),
('Catende', 'PE',''),
('Cedro', 'PE',''),
('Chã de Alegria', 'PE',''),
('Chã Grande', 'PE',''),
('Condado', 'PE',''),
('Correntes', 'PE',''),
('Cortês', 'PE',''),
('Cumaru', 'PE',''),
('Cupira', 'PE',''),
('Custódia', 'PE',''),
('Dormentes', 'PE',''),
('Escada', 'PE',''),
('Exu', 'PE',''),
('Feira Nova', 'PE',''),
('Fernando de Noronha', 'PE',''),
('Ferreiros', 'PE',''),
('Flores', 'PE',''),
('Floresta', 'PE',''),
('Frei Miguelinho', 'PE',''),
('Gameleira', 'PE',''),
('Garanhuns', 'PE',''),
('Glória do Goitá', 'PE',''),
('Goiana', 'PE',''),
('Granito', 'PE',''),
('Gravatá', 'PE',''),
('Iati', 'PE',''),
('Ibimirim', 'PE',''),
('Ibirajuba', 'PE',''),
('Igarassu', 'PE',''),
('Iguaraci', 'PE',''),
('Ilha de Itamaracá', 'PE',''),
('Inajá', 'PE',''),
('Ingazeira', 'PE',''),
('Ipojuca', 'PE',''),
('Ipubi', 'PE',''),
('Itacuruba', 'PE',''),
('Itaíba', 'PE',''),
('Itambé', 'PE',''),
('Itapetim', 'PE',''),
('Itapissuma', 'PE',''),
('Itaquitinga', 'PE',''),
('Jaboatão dos Guararapes', 'PE',''),
('Jaqueira', 'PE',''),
('Jataúba', 'PE',''),
('Jatobá', 'PE',''),
('João Alfredo', 'PE',''),
('Joaquim Nabuco', 'PE',''),
('Jucati', 'PE',''),
('Jupi', 'PE',''),
('Jurema', 'PE',''),
('Lagoa do Carro', 'PE',''),
('Lagoa do Itaenga', 'PE',''),
('Lagoa do Ouro', 'PE',''),
('Lagoa dos Gatos', 'PE',''),
('Lagoa Grande', 'PE',''),
('Lajedo', 'PE',''),
('Limoeiro', 'PE',''),
('Macaparana', 'PE',''),
('Machados', 'PE',''),
('Manari', 'PE',''),
('Maraial', 'PE',''),
('Mirandiba', 'PE',''),
('Moreilândia', 'PE',''),
('Moreno', 'PE',''),
('Nazaré da Mata', 'PE',''),
('Olinda', 'PE',''),
('Orobó', 'PE',''),
('Orocó', 'PE',''),
('Ouricuri', 'PE',''),
('Palmares', 'PE',''),
('Palmeirina', 'PE',''),
('Panelas', 'PE',''),
('Paranatama', 'PE',''),
('Parnamirim', 'PE',''),
('Passira', 'PE',''),
('Paudalho', 'PE',''),
('Paulista', 'PE',''),
('Pedra', 'PE',''),
('Pesqueira', 'PE',''),
('Petrolândia', 'PE',''),
('Petrolina', 'PE',''),
('Poção', 'PE',''),
('Pombos', 'PE',''),
('Primavera', 'PE',''),
('Quipapá', 'PE',''),
('Quixaba', 'PE',''),
('Recife', 'PE',''),
('Riacho das Almas', 'PE',''),
('Ribeirão', 'PE',''),
('Rio Formoso', 'PE',''),
('Sairé', 'PE',''),
('Salgadinho', 'PE',''),
('Salgueiro', 'PE',''),
('Saloá', 'PE',''),
('Sanharó', 'PE',''),
('Santa Cruz', 'PE',''),
('Santa Cruz da Baixa Verde', 'PE',''),
('Santa Cruz do Capibaribe', 'PE',''),
('Santa Filomena', 'PE',''),
('Santa Maria da Boa Vista', 'PE',''),
('Santa Maria do Cambucá', 'PE',''),
('Santa Terezinha', 'PE',''),
('São Benedito do Sul', 'PE',''),
('São Bento do Una', 'PE',''),
('São Caitano', 'PE',''),
('São João', 'PE',''),
('São Joaquim do Monte', 'PE',''),
('São José da Coroa Grande', 'PE',''),
('São José do Belmonte', 'PE',''),
('São José do Egito', 'PE',''),
('São Lourenço da Mata', 'PE',''),
('São Vicente Ferrer', 'PE',''),
('Serra Talhada', 'PE',''),
('Serrita', 'PE',''),
('Sertânia', 'PE',''),
('Sirinhaém', 'PE',''),
('Solidão', 'PE',''),
('Surubim', 'PE',''),
('Tabira', 'PE',''),
('Tacaimbó', 'PE',''),
('Tacaratu', 'PE',''),
('Tamandaré', 'PE',''),
('Taquaritinga do Norte', 'PE',''),
('Terezinha', 'PE',''),
('Terra Nova', 'PE',''),
('Timbaúba', 'PE',''),
('Toritama', 'PE',''),
('Tracunhaém', 'PE',''),
('Trindade', 'PE',''),
('Triunfo', 'PE',''),
('Tupanatinga', 'PE',''),
('Tuparetama', 'PE',''),
('Venturosa', 'PE',''),
('Verdejante', 'PE',''),
('Vertente do Lério', 'PE',''),
('Vertentes', 'PE',''),
('Vicência', 'PE',''),
('Vitória de Santo Antão', 'PE',''),
('Xexéu', 'PE','');
