CREATE TABLE CLIENTE (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(50) NOT NULL
);

CREATE TABLE PRODUTO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR(100),
    PRECO NUMERIC(20, 2) NOT NULL
);

CREATE TABLE PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENTE INTEGER REFERENCES CLIENTE(ID),
    DATA_PEDIDO TIMESTAMP,
    TOTAL NUMERIC(20, 2) NOT NULL
);

CREATE TABLE ITEM_PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PEDIDO INTEGER REFERENCES PEDIDO(ID),
    PRODUTO INTEGER REFERENCES PRODUTO(ID),
    QUANTIDADE INTEGER
);

