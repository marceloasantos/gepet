--liquibase formatted sql

--changeset gepet:1
DROP DATABASE IF EXISTS gepet;

CREATE DATABASE gepet;

--rollback DROP DATABASE gepet;

--changeset gepet:2
DROP TABLE IF EXISTS usu_usuario;

CREATE TABLE usu_usuario (
	usu_id VARCHAR(64),
	usu_nome VARCHAR(50) NOT NULL,
	usu_email VARCHAR(50) NOT NULL,
	usu_senha VARCHAR(100),
	CONSTRAINT pk_usu_usuario PRIMARY KEY (usu_id)
);
--rollback DROP TABLE usu_usuario;

--changeset gepet:3
DROP TABLE IF EXISTS ani_animal;

CREATE TABLE ani_animal (
	ani_id VARCHAR(64) PRIMARY KEY,
	ani_nome VARCHAR(50) NOT NULL,
	ani_peso FLOAT NOT NULL,
	ani_usu_id VARCHAR(64) NOT NULL,
	FOREIGN KEY (ani_usu_id) REFERENCES usu_usuario(usu_id)
);

--rollback DROP TABLE ani_animal;

--changeset gepet:4
DROP TABLE IF EXISTS vac_vacina;

CREATE TABLE vac_vacina (
	vac_id VARCHAR(64) PRIMARY KEY,
	vac_nome VARCHAR(30) NOT NULL
);

--rollback DROP TABLE vac_vacina;

--changeset gepet:5
DROP TABLE IF EXISTS vap_vacina_aplicada;

CREATE TABLE vap_vacina_aplicada (
	vap_ani_id VARCHAR(64),
	vap_vac_id VARCHAR(64),
	CONSTRAINT pk_vap_vacina_aplicada PRIMARY KEY (vap_ani_id, vap_vac_id),
	CONSTRAINT fk_ani_id FOREIGN KEY (vap_ani_id) REFERENCES ani_animal(ani_id),
	CONSTRAINT fk_vac_id FOREIGN KEY (vap_vac_id) REFERENCES vac_vacina(vac_id)
);

--rollback DROP TABLE vap_vacina_aplicada;

--changeset gepet:6
DROP TABLE IF EXISTS aut_autorizacao;

CREATE TABLE aut_autorizacao (
	aut_id VARCHAR(64) PRIMARY KEY,
	aut_nome VARCHAR(20)
);

--rollback DROP TABLE aut_autorizacao;

--changeset gepet:7
DROP TABLE IF EXISTS usa_usuario_autorizacao;

CREATE TABLE usa_usuario_autorizacao (
	usa_usu_id VARCHAR(64),
	usa_aut_id VARCHAR(64),
	CONSTRAINT fk_usa_usu_id FOREIGN KEY (usa_usu_id) REFERENCES usu_usuario(usu_id),
	CONSTRAINT fk_usa_aut_id FOREIGN KEY (usa_aut_id) REFERENCES aut_autorizacao(aut_id)
);

--rollback DROP TABLE usa_usuario_autorizacao;

--changeset gepet:8
DELETE FROM aut_autorizacao;

INSERT INTO aut_autorizacao (aut_id, aut_nome) VALUES ('8d517c06-a7bf-11ec-840c-00ff0c3d5b70', 'ROLE_ADMIN');
INSERT INTO aut_autorizacao (aut_id, aut_nome) VALUES ('9f7f0f60-a7bf-11ec-840c-00ff0c3d5b70', 'ROLE_USER');

--rollback DELETE FROM aut_autorizacao;
