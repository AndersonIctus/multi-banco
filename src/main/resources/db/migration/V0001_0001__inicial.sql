-- TABELA DE PERFIS PARA TESTES
CREATE SEQUENCE PERFIS_SEQ
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE PERFIS (
    ID                  BIGINT NOT NULL,
    DESCRICAO           VARCHAR(20) NOT NULL,
    SUPER               CHARACTER(1) NOT NULL DEFAULT 'N',

    CRIADO_EM           TIMESTAMP NOT NULL DEFAULT NOW(),
    ALTERADO_EM         TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT PERFIS_PKEY PRIMARY KEY (ID),
    CONSTRAINT UN_PERFIS UNIQUE(DESCRICAO)
);
