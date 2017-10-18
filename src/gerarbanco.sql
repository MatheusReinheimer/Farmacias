create database if not exists farmacia CHARACTER SET utf8;
use farmacia;

CREATE TABLE cliente(
    id int not null auto_increment,
    cpf varchar(11) not null,
    nome varchar(100) not null,
    datanascimento date not null,
    PRIMARY KEY(id)
);

CREATE TABLE remedio(
    id int not null auto_increment,
    descricao varchar(60) not null,
    bula varchar(500),
    estoque int not null,
    preco double not null,
    tarja int not null,
    PRIMARY KEY(id)
);

CREATE TABLE venda(
    id int not null auto_increment,
    total int not null,
    datavenda DATETIME not null DEFAULT CURRENT_TIMESTAMP,
    clienteid int not null,
    PRIMARY KEY(id),
    CONSTRAINT FOREIGN KEY(clienteid) REFERENCES cliente(id)
);

CREATE TABLE itemvenda(
    iditem int not null auto_increment,
    valor double not null,
    quantidade int not null,
    vendaid int not null,
    remedioid int not null,
    PRIMARY KEY(iditem),
    CONSTRAINT FOREIGN KEY(vendaid) REFERENCES venda(id),
    CONSTRAINT FOREIGN KEY(remedioid) REFERENCES remedio(id)
);

delimiter //
CREATE PROCEDURE sp_atualizaestoque(IN remediovendido INT, IN quantidade INT)
BEGIN
    update remedio set estoque=estoque-quantidade where id=remediovendido;
END//

delimiter ;

CREATE TRIGGER t_oninsertitem 
AFTER INSERT 
ON itemvenda
FOR EACH ROW
    CALL sp_atualizaestoque(NEW.remedioid, NEW.quantidade);

