create database bdacademia;

use bdacademia;

create table aluno(
	codAluno int primary key auto_increment,
	nomeAluno varchar(150) not null,
	cpf char(14) not null unique,
	email varchar(100) not null unique,
	rg varchar (20) unique,
	dataNasc date not null
);

create table usuario(
	codUsuario int primary key auto_increment,
	nomeUsuario varchar(30) unique not null,
	senha varchar(15) not null,
	nivel varchar(10) not null,
	dataCadastro date default (current_date())
);
