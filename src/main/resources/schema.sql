/*
--Crie um banco de dados no seu computador
--Certifique-se de alterar o application.properties nas seguintes linhas

spring.datasource.url=jdbc:mysql://localhost:3306/themyra_0
spring.datasource.username=root
spring.datasource.password=root

Respectivamente:

Linha 1: local e nome da DB
Linha 2: usuario capaz de acessar o mysql
Linha 3: senha do seu usuário no mysql
*/

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);
