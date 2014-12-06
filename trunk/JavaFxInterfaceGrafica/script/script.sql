/*  PRODUTO */
drop taBLE produto;
drop SEQUENCE sq_cd_produto;

create table produto(
  codigo integer PRIMARY KEY,
  nome VARCHAR2(50),
  preco numeric(14,2)
);
CREATE SEQUENCE sq_cd_produto;

select * from produto

/*  CLIENTE */
drop taBLE cliente;
drop SEQUENCE sq_cd_cliente;
create table cliente(
  codigo integer PRIMARY KEY,
  nome VARCHAR2(30),
  cpf VARCHAR2(11),
  telefone VARCHAR2(15)
);

CREATE SEQUENCE sq_cd_cliente;

select * from cliente;

/*  FORNECEDOR  */
drop table FORNECEDOR;
drop sequence sq_cd_fornecedor;
create table fornecedor(
  codigo integer PRIMARY KEY,
  nome_fantasia VARCHAR2(20),
  razao_social VARCHAR2(20),
  inscricao_estadual VARCHAR2(20),
  telefone VARCHAR2(20),
  cnpj VARCHAR2(14)
);

CREATE SEQUENCE sq_cd_fornecedor;

select * from fornecedor;
