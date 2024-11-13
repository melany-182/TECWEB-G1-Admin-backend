-- tables
-- Table: Acceso
CREATE TABLE Acceso (
                        ID_Acceso serial  NOT NULL,
                        IsDeleted boolean  NOT NULL DEFAULT false,
                        ID_Usuario int  NOT NULL,
                        CONSTRAINT Acceso_pk PRIMARY KEY (ID_Acceso)
);

-- Table: Alumno
CREATE TABLE Alumno (
                        ID_Alumno serial  NOT NULL,
                        Semestre int  NOT NULL,
                        MateriasAprobadas int  NOT NULL,
                        Puntaje decimal(7,1)  NOT NULL,
                        IsDeleted boolean  NOT NULL DEFAULT false,
                        ID_Persona int  NOT NULL,
                        CONSTRAINT Alumno_pk PRIMARY KEY (ID_Alumno)
);

-- Table: Comentario
CREATE TABLE Comentario (
                            ID_Comentario serial  NOT NULL,
                            Texto varchar(500)  NOT NULL,
                            FechaHora timestamp  NOT NULL,
                            IsDeleted boolean  NOT NULL DEFAULT false,
                            ID_Foro int  NOT NULL,
                            ID_Usuario int  NOT NULL,
                            CONSTRAINT Comentario_pk PRIMARY KEY (ID_Comentario)
);

-- Table: Docente
CREATE TABLE Docente (
                         ID_Docente serial  NOT NULL,
                         AboutMe varchar(500)  NOT NULL,
                         GradoEstudio varchar(50)  NOT NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         ID_Persona int  NOT NULL,
                         CONSTRAINT Docente_pk PRIMARY KEY (ID_Docente)
);

-- Table: Documento
CREATE TABLE Documento (
                           ID_Documento serial  NOT NULL,
                           Titulo varchar(100)  NOT NULL,
                           Direccion varchar(200)  NOT NULL,
                           Autores varchar(500)  NOT NULL,
                           Descripcion varchar(5000)  NOT NULL,
                           IsDeleted boolean  NOT NULL DEFAULT false,
                           CONSTRAINT Documento_pk PRIMARY KEY (ID_Documento)
);

-- Table: DocumentoLeido
CREATE TABLE DocumentoLeido (
                                ID_Lectura serial  NOT NULL,
                                Fecha date  NOT NULL,
                                IsDeleted boolean  NOT NULL DEFAULT false,
                                ID_Documento int  NOT NULL,
                                ID_Alumno int  NOT NULL,
                                CONSTRAINT DocumentoLeido_pk PRIMARY KEY (ID_Lectura)
);

-- Table: Foro
CREATE TABLE Foro (
                      ID_Foro serial  NOT NULL,
                      Nombre varchar(50)  NOT NULL,
                      Tema varchar(50)  NOT NULL,
                      IsDeleted boolean  NOT NULL DEFAULT false,
                      ID_Acceso int  NOT NULL,
                      CONSTRAINT Foro_pk PRIMARY KEY (ID_Foro)
);

-- Table: JefeCarrera
CREATE TABLE JefeCarrera (
                             ID_JefeCarrera serial  NOT NULL,
                             IsDeleted boolean  NOT NULL DEFAULT false,
                             ID_Persona int  NOT NULL,
                             CONSTRAINT JefeCarrera_pk PRIMARY KEY (ID_JefeCarrera)
);

-- Table: Materia
CREATE TABLE Materia (
                         ID_Materia serial  NOT NULL,
                         Nombre varchar(100)  NOT NULL,
                         Descripcion varchar(255)  NOT NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         ID_Docente int  NOT NULL,
                         CONSTRAINT Materia_pk PRIMARY KEY (ID_Materia)
);

-- Table: Medalla
CREATE TABLE Medalla (
                         ID_Medalla serial  NOT NULL,
                         Nombre varchar(50)  NOT NULL,
                         Puntaje decimal(7,1)  NOT NULL,
                         Imagen text  NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         ID_Documento int  NOT NULL,
                         CONSTRAINT Medalla_pk PRIMARY KEY (ID_Medalla)
);

-- Table: MedallaGanada
CREATE TABLE MedallaGanada (
                               ID_MedallaGanada serial  NOT NULL,
                               Fecha date  NOT NULL,
                               IsDeleted boolean  NOT NULL DEFAULT false,
                               ID_Alumno int  NOT NULL,
                               ID_Medalla int  NOT NULL,
                               CONSTRAINT MedallaGanada_pk PRIMARY KEY (ID_MedallaGanada)
);

-- Table: Noticia
CREATE TABLE Noticia (
                         ID_Noticia serial  NOT NULL,
                         Nombre varchar(100)  NOT NULL,
                         Descripcion varchar(5000)  NOT NULL,
                         Imagen text  NULL,
                         FechaInicio date  NOT NULL,
                         FechaFin date  NOT NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         ID_JefeCarrera int  NOT NULL,
                         CONSTRAINT Noticia_pk PRIMARY KEY (ID_Noticia)
);

-- Table: Persona
CREATE TABLE Persona (
                         ID_Persona serial  NOT NULL,
                         CI varchar(20)  NOT NULL,
                         Nombres varchar(100)  NOT NULL,
                         Ap_Paterno varchar(100)  NOT NULL,
                         Ap_Materno varchar(100)  NOT NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         ID_Usuario int  NOT NULL,
                         CONSTRAINT Persona_pk PRIMARY KEY (ID_Persona)
);

-- Table: Pokemon
CREATE TABLE Pokemon (
                         ID_Alumno int  NOT NULL,
                         NombrePokemon varchar(50)  NOT NULL,
                         Nivel int  NOT NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         CONSTRAINT Pokemon_pk PRIMARY KEY (ID_Alumno)
);

-- Table: Usuario
CREATE TABLE Usuario (
                         ID_Usuario serial  NOT NULL,
                         NombreGoogle varchar(255)  NOT NULL,
                         CorreoGoogle varchar(255)  NOT NULL,
                         IsDeleted boolean  NOT NULL DEFAULT false,
                         CONSTRAINT Usuario_pk PRIMARY KEY (ID_Usuario)
);

-- foreign keys
-- Reference: Acceso_Usuario (table: Acceso)
ALTER TABLE Acceso ADD CONSTRAINT Acceso_Usuario
    FOREIGN KEY (ID_Usuario)
        REFERENCES Usuario (ID_Usuario)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Alumno_Persona (table: Alumno)
ALTER TABLE Alumno ADD CONSTRAINT Alumno_Persona
    FOREIGN KEY (ID_Persona)
        REFERENCES Persona (ID_Persona)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Comentario_Foro (table: Comentario)
ALTER TABLE Comentario ADD CONSTRAINT Comentario_Foro
    FOREIGN KEY (ID_Foro)
        REFERENCES Foro (ID_Foro)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Comentario_Usuario (table: Comentario)
ALTER TABLE Comentario ADD CONSTRAINT Comentario_Usuario
    FOREIGN KEY (ID_Usuario)
        REFERENCES Usuario (ID_Usuario)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Docente_Persona (table: Docente)
ALTER TABLE Docente ADD CONSTRAINT Docente_Persona
    FOREIGN KEY (ID_Persona)
        REFERENCES Persona (ID_Persona)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: DocumentoLeido_Alumno (table: DocumentoLeido)
ALTER TABLE DocumentoLeido ADD CONSTRAINT DocumentoLeido_Alumno
    FOREIGN KEY (ID_Alumno)
        REFERENCES Alumno (ID_Alumno)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: DocumentoLeido_Documento (table: DocumentoLeido)
ALTER TABLE DocumentoLeido ADD CONSTRAINT DocumentoLeido_Documento
    FOREIGN KEY (ID_Documento)
        REFERENCES Documento (ID_Documento)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Foro_Acceso (table: Foro)
ALTER TABLE Foro ADD CONSTRAINT Foro_Acceso
    FOREIGN KEY (ID_Acceso)
        REFERENCES Acceso (ID_Acceso)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: JefeCarrera_Persona (table: JefeCarrera)
ALTER TABLE JefeCarrera ADD CONSTRAINT JefeCarrera_Persona
    FOREIGN KEY (ID_Persona)
        REFERENCES Persona (ID_Persona)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Materia_Docente (table: Materia)
ALTER TABLE Materia ADD CONSTRAINT Materia_Docente
    FOREIGN KEY (ID_Docente)
        REFERENCES Docente (ID_Docente)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: MedallaGanada_Alumno (table: MedallaGanada)
ALTER TABLE MedallaGanada ADD CONSTRAINT MedallaGanada_Alumno
    FOREIGN KEY (ID_Alumno)
        REFERENCES Alumno (ID_Alumno)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: MedallaGanada_Medalla (table: MedallaGanada)
ALTER TABLE MedallaGanada ADD CONSTRAINT MedallaGanada_Medalla
    FOREIGN KEY (ID_Medalla)
        REFERENCES Medalla (ID_Medalla)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Medalla_Documento (table: Medalla)
ALTER TABLE Medalla ADD CONSTRAINT Medalla_Documento
    FOREIGN KEY (ID_Documento)
        REFERENCES Documento (ID_Documento)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Noticia_JefeCarrera (table: Noticia)
ALTER TABLE Noticia ADD CONSTRAINT Noticia_JefeCarrera
    FOREIGN KEY (ID_JefeCarrera)
        REFERENCES JefeCarrera (ID_JefeCarrera)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Persona_Usuario (table: Persona)
ALTER TABLE Persona ADD CONSTRAINT Persona_Usuario
    FOREIGN KEY (ID_Usuario)
        REFERENCES Usuario (ID_Usuario)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Pokemon_Alumno (table: Pokemon)
ALTER TABLE Pokemon ADD CONSTRAINT Pokemon_Alumno
    FOREIGN KEY (ID_Alumno)
        REFERENCES Alumno (ID_Alumno)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- sample data
-- Insert data into Usuario table
INSERT INTO Usuario (NombreGoogle, CorreoGoogle) VALUES
                                                     ('Juan Perez Gomez', 'juan.perez.gomez@ucb.edu.bo'),
                                                     ('Maria Lopez Fernandez', 'maria.lopez.fernandez@ucb.edu.bo'),
                                                     ('Carlos Sanchez Rojas', 'carlos.sanchez.rojas@ucb.edu.bo');

-- Insert data into Persona table
INSERT INTO Persona (CI, Nombres, Ap_Paterno, Ap_Materno, ID_Usuario) VALUES
                                                                          ('12345678', 'Juan', 'Perez', 'Gomez', 1),
                                                                          ('87654321', 'Maria', 'Lopez', 'Fernandez', 2),
                                                                          ('11223344', 'Carlos', 'Sanchez', 'Rojas', 3);

-- Insert data into Alumno table
INSERT INTO Alumno (Semestre, MateriasAprobadas, Puntaje, ID_Persona) VALUES
                                                                          (3, 12, 75.5, 1),
                                                                          (5, 20, 85.0, 2);

-- Insert data into Docente table
INSERT INTO Docente (AboutMe, GradoEstudio, ID_Persona) VALUES
    ('Docente con amplia experiencia en administración.', 'Maestría', 3);

-- Insert data into JefeCarrera table
INSERT INTO JefeCarrera (ID_Persona) VALUES
    (3);

-- Insert data into Acceso table
INSERT INTO Acceso (ID_Usuario) VALUES
                                    (1),
                                    (2),
                                    (3);

-- Insert data into Foro table
INSERT INTO Foro (Nombre, Tema, ID_Acceso) VALUES
                                               ('Foro de Administración', 'Discusión sobre técnicas de administración', 1),
                                               ('Foro de Innovación', 'Ideas para la mejora de procesos', 2);

-- Insert data into Comentario table
INSERT INTO Comentario (Texto, FechaHora, ID_Foro, ID_Usuario) VALUES
                                                                   ('Creo que la innovación es clave para el éxito.', '2024-11-13 10:00:00', 1, 1),
                                                                   ('Estoy de acuerdo, la gestión de procesos es muy importante.', '2024-11-13 11:00:00', 1, 2);

-- Insert data into Materia table
INSERT INTO Materia (Nombre, Descripcion, ID_Docente) VALUES
                                                          ('Administración I', 'Introducción a los conceptos básicos de la administración', 1),
                                                          ('Marketing', 'Conceptos y estrategias de marketing', 1),
                                                          ('Finanzas', 'Principios de finanzas empresariales', 1);

-- Insert data into Documento table
INSERT INTO Documento (Titulo, Direccion, Autores, Descripcion) VALUES
                                                                    ('Manual de Administración', 'https://ucb.edu.bo/docs/admin_manual', 'Juan Perez', 'Manual completo sobre administración'),
                                                                    ('Guía de Marketing', 'https://ucb.edu.bo/docs/marketing_guide', 'Maria Lopez', 'Guía esencial sobre marketing');

-- Insert data into DocumentoLeido table
INSERT INTO DocumentoLeido (Fecha, ID_Documento, ID_Alumno) VALUES
                                                                ('2024-11-10', 1, 1),
                                                                ('2024-11-11', 2, 2);

-- Insert data into Medalla table
INSERT INTO Medalla (Nombre, Puntaje, ID_Documento) VALUES
                                                        ('Medalla de Oro', 100.0, 1),
                                                        ('Medalla de Plata', 75.0, 2),
                                                        ('Medalla de Bronce', 50.0, 1);

-- Insert data into MedallaGanada table
INSERT INTO MedallaGanada (Fecha, ID_Alumno, ID_Medalla) VALUES
                                                             ('2024-11-12', 1, 1),
                                                             ('2024-11-13', 2, 2);

-- Insert data into Noticia table
INSERT INTO Noticia (Nombre, Descripcion, FechaInicio, FechaFin, ID_JefeCarrera) VALUES
    ('Conferencia de Administración', 'Conferencia sobre técnicas modernas de administración', '2024-11-15', '2024-11-16', 1);

-- Insert data into Pokemon table
INSERT INTO Pokemon (ID_Alumno, NombrePokemon, Nivel) VALUES
                                                          (1, 'Pikachu', 10),
                                                          (2, 'Charmander', 8);
