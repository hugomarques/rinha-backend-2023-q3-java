SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER SCHEMA PUBLIC OWNER TO rinha;

SET default_tablespace = '';

SET default_table_access_method = heap;

DROP TABLE IF EXISTS PUBLIC."pessoas";

CREATE TABLE public."pessoas" (
	id UUID PRIMARY KEY NOT NULL,
	apelido VARCHAR(32) UNIQUE NOT NULL,
	nome VARCHAR(100) NOT NULL,
	nascimento VARCHAR(10) NOT NULL,
	stack VARCHAR(255) NULL
);

CREATE UNIQUE INDEX idx_apelido ON PUBLIC."pessoas" (apelido);

CREATE EXTENSION IF NOT EXISTS pg_trgm SCHEMA pg_catalog;

CREATE INDEX idx_pessoas_apelido_trgm ON PUBLIC."pessoas" USING gin("apelido" gin_trgm_ops);
CREATE INDEX idx_pessoas_nome_trgm ON PUBLIC."pessoas" USING gin("nome" gin_trgm_ops);

ALTER TABLE PUBLIC."pessoas" OWNER TO rinha;