-- ////////////////////////////////// HOSPITAL ////////////////////////////////// --
CREATE TABLE Hospital (
    id_hospital NUMBER(10) NOT NULL,
    nome VARCHAR2(50) NOT NULL,
     CONSTRAINT PK_HOSPITAL PRIMARY KEY(id_hospital)     
);

INSERT INTO Hospital(id_hospital, nome)
	VALUES(1, 'WB Health');

-- ////////////////////////////////// PACIENTE ////////////////////////////////// --
CREATE TABLE Paciente (
	id_paciente NUMBER(10) NOT NULL,
	id_hospital NUMBER(1) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	cep CHAR(9) NOT NULL,
	cpf CHAR(11) NOT NULL,
	 CONSTRAINT PK_PACIENTE PRIMARY KEY (id_paciente),
	 CONSTRAINT UK_PACIENTE UNIQUE (cpf),
	 CONSTRAINT FK_PACIENTE_HOSPITAL FOREIGN KEY (id_hospital) REFERENCES Hospital(id_hospital)
);

---- SEQUENCE PACIENTE ----
CREATE SEQUENCE SEQ_PACIENTE
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

---- INSERT PACIENTE ----
INSERT INTO Paciente (id_paciente, id_hospital, nome, cep, cpf)
	VALUES (SEQ_PACIENTE.nextval, 1, 'Daiane Sônia Oliveira', '787157-46', '69974805350');

INSERT INTO Paciente (id_paciente, id_hospital, nome, cep, cpf)
	VALUES (SEQ_PACIENTE.nextval, 1, 'Adriana Natália Benedita', '640830-50', '37084189564');

-- ////////////////////////////////// MEDICO ////////////////////////////////// --
CREATE TABLE Medico (
    id_medico NUMBER(10) NOT NULL,
    id_hospital NUMBER(1) NOT NULL,
    nome VARCHAR2(50) NOT NULL,
    cep CHAR(9) NOT NULL,
    crm CHAR(13) UNIQUE NOT NULL,
    salario_mensal DECIMAL(6,2) NOT NULL,
    CONSTRAINT PK_MEDICO PRIMARY KEY (id_medico),
    CONSTRAINT FK_MEDICO_HOSPITAL FOREIGN KEY (id_hospital) REFERENCES Hospital(id_hospital)
);


---- SEQUENCE MEDICO ----
CREATE SEQUENCE SEQ_MEDICO
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

 ---- INSERT PACIENTE ----
INSERT INTO Medico (id_medico, id_hospital, nome, cep, crm, salario_mensal)
VALUES(SEQ_MEDICO.nextval, 1, 'Benício Theo Brito', '26262-100', 'SP-1234567/89', 4000.30);

INSERT INTO Medico (id_medico, id_hospital, nome, cep, crm, salario_mensal)
VALUES(SEQ_MEDICO.nextval, 1, 'Emanuel Elias dos Santos', '23456-101', 'PE-1292867/79', 5600.0);

INSERT INTO Medico (id_medico, id_hospital, nome, cep, crm, salario_mensal)
VALUES(SEQ_MEDICO.nextval, 1, 'Lara Teresinha da Mota', '55116-120', 'MG-4222867/91', 7600.0);

INSERT INTO Medico (id_medico, id_hospital, nome, cep, crm, salario_mensal)
VALUES(SEQ_MEDICO.nextval, 1, 'Elza Ana Vera Farias', '13016-134', 'GO-3792867/38', 8350.0);


-- ////////////////////////////////// FUNCIONÁRIO ////////////////////////////////// --
CREATE TABLE Funcionario (
	id_funcionario NUMBER(10) NOT NULL,
	id_hospital NUMBER(1) NOT NULL,
	salario_base DECIMAL(6,2) NOT NULL,
	nome VARCHAR(50) NOT NULL,
 	 CONSTRAINT PK_FUNCIONARIO PRIMARY KEY (id_funcionario),
 	 CONSTRAINT FK_FUNCIONARIO_HOSPITAL FOREIGN KEY (id_hospital) REFERENCES Hospital(id_hospital)
);

---- SEQUENCE FUNCIONARIO ----
CREATE SEQUENCE SEQ_FUNCIONARIO
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;


---- INSERT FUNCIONARIO ----
INSERT INTO Funcionario (id_funcionario, id_hospital, salario_base, nome)
	VALUES (SEQ_FUNCIONARIO.nextval, 1, 3500.00,'Tomás Isaac Thales');

INSERT INTO Funcionario (id_funcionario, id_hospital, salario_base, nome)
	VALUES (SEQ_FUNCIONARIO.nextval,  1, 4500.00,'Sophia Vanessa Yasmin Costa');

-- ////////////////////////////////// ATENDIMENTO ////////////////////////////////// --
CREATE TABLE Atendimento(
    id_atendimento NUMBER(10) NOT NULL,
    id_hospital NUMBER(1) NOT NULL,
    id_paciente NUMBER(10) NOT NULL,
    id_medico NUMBER(10) NOT NULL,
    dataAtendimento VARCHAR2(10) NOT NULL,
    laudo VARCHAR2(100) NOT NULL,
     CONSTRAINT PK_ATENDIMENTO PRIMARY KEY(id_atendimento),
     CONSTRAINT FK_ATENDIMENTO_PACIENTE FOREIGN KEY(id_paciente) REFERENCES PACIENTE(id_paciente),
     CONSTRAINT FK_ATENDIMENTO_MEDICO FOREIGN KEY(id_medico) REFERENCES MEDICO(id_medico),
     CONSTRAINT FK_ATENDIMENTO_HOSPITAL FOREIGN KEY (id_hospital) REFERENCES Hospital(id_hospital)
);

---- SEQUENCE FUNCIONARIO ----
CREATE SEQUENCE SEQ_ATENDIMENTO
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

INSERT INTO ATENDIMENTO(id_atendimento, id_hospital, id_paciente, id_medico, dataAtendimento, laudo)
	VALUES(SEQ_ATENDIMENTO.nextval, 1, 1, 1, TO_DATE('26-07-2023', 'dd-mm-yyyy'), 'Bateu as botas');

INSERT INTO ATENDIMENTO(id_atendimento, id_hospital, id_paciente, id_medico, dataAtendimento, laudo)
	VALUES(SEQ_ATENDIMENTO.nextval, 1, 2, 2, TO_DATE('25-02-2023', 'dd-mm-yyyy'), 'Escapou por pouco');


