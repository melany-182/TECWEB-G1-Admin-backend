-- tables
-- Table: Alumno
CREATE TABLE Alumno (
    ID_Alumno serial  NOT NULL,
    Semestre int  NOT NULL,
    Materias_Aprobadas int  NOT NULL,
    Puntaje decimal(7,1)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Persona int  NOT NULL,
    CONSTRAINT Alumno_pk PRIMARY KEY (ID_Alumno)
);

-- Table: Comentario
CREATE TABLE Comentario (
    ID_Comentario serial  NOT NULL,
    Texto varchar(500)  NOT NULL,
    Fecha_Hora timestamp  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Foro int  NOT NULL,
    ID_Usuario int  NOT NULL,
    CONSTRAINT Comentario_pk PRIMARY KEY (ID_Comentario)
);

-- Table: Docente
CREATE TABLE Docente (
    ID_Docente serial  NOT NULL,
    About_Me varchar(500)  NOT NULL,
    Grado_Estudio varchar(50)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
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
    Is_Deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT Documento_pk PRIMARY KEY (ID_Documento)
);

-- Table: Documento_Leido
CREATE TABLE Documento_Leido (
    ID_Documento_Leido serial  NOT NULL,
    Fecha date  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Alumno int  NOT NULL,
    ID_Documento int  NOT NULL,
    CONSTRAINT Documento_Leido_pk PRIMARY KEY (ID_Documento_Leido)
);

-- Table: Foro
CREATE TABLE Foro (
    ID_Foro serial  NOT NULL,
    Nombre varchar(50)  NOT NULL,
    Tema varchar(50)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Tipo_Acceso int  NOT NULL,
    CONSTRAINT Foro_pk PRIMARY KEY (ID_Foro)
);

-- Table: Jefe_Carrera
CREATE TABLE Jefe_Carrera (
    ID_Jefe_Carrera serial  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Persona int  NOT NULL,
    CONSTRAINT Jefe_Carrera_pk PRIMARY KEY (ID_Jefe_Carrera)
);

-- Table: Materia
CREATE TABLE Materia (
    ID_Materia serial  NOT NULL,
    Nombre varchar(100)  NOT NULL,
    Descripcion varchar(255)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Docente int  NOT NULL,
    CONSTRAINT Materia_pk PRIMARY KEY (ID_Materia)
);

-- Table: Medalla
CREATE TABLE Medalla (
    ID_Medalla serial  NOT NULL,
    Nombre varchar(50)  NOT NULL,
    Puntaje decimal(7,1)  NOT NULL,
    Imagen text  NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Documento int  NOT NULL,
    CONSTRAINT Medalla_pk PRIMARY KEY (ID_Medalla)
);

-- Table: Medalla_Ganada
CREATE TABLE Medalla_Ganada (
    ID_Medalla_Ganada serial  NOT NULL,
    Fecha date  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Alumno int  NOT NULL,
    ID_Medalla int  NOT NULL,
    CONSTRAINT Medalla_Ganada_pk PRIMARY KEY (ID_Medalla_Ganada)
);

-- Table: Noticia
CREATE TABLE Noticia (
    ID_Noticia serial  NOT NULL,
    Nombre varchar(100)  NOT NULL,
    Descripcion varchar(5000)  NOT NULL,
    Imagen text  NULL,
    Fecha_Inicio date  NOT NULL,
    Fecha_Fin date  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Jefe_Carrera int  NOT NULL,
    CONSTRAINT Noticia_pk PRIMARY KEY (ID_Noticia)
);

-- Table: Persona
CREATE TABLE Persona (
    ID_Persona serial  NOT NULL,
    CI varchar(20)  NOT NULL,
    Nombres varchar(100)  NOT NULL,
    Ap_Paterno varchar(100)  NOT NULL,
    Ap_Materno varchar(100)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Usuario int  NOT NULL,
    CONSTRAINT Persona_pk PRIMARY KEY (ID_Persona)
);

-- Table: Pokemon
CREATE TABLE Pokemon (
    ID_Alumno int  NOT NULL,
    Nombre_Pokemon varchar(50)  NOT NULL,
    Nivel int  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT Pokemon_pk PRIMARY KEY (ID_Alumno)
);

-- Table: Tipo_Acceso
CREATE TABLE Tipo_Acceso (
    ID_Tipo_Acceso serial  NOT NULL,
    Nombre varchar(50)  NOT NULL,
    Descripcion varchar(255)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT Tipo_Acceso_pk PRIMARY KEY (ID_Tipo_Acceso)
);

-- Table: Usuario
CREATE TABLE Usuario (
    ID_Usuario serial  NOT NULL,
    Nombre_Google varchar(255)  NOT NULL,
    Correo_Google varchar(255)  NOT NULL,
    Is_Deleted boolean  NOT NULL DEFAULT false,
    ID_Tipo_Acceso int  NOT NULL,
    CONSTRAINT Usuario_pk PRIMARY KEY (ID_Usuario)
);

-- foreign keys
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

-- Reference: Documento_Leido_Alumno (table: Documento_Leido)
ALTER TABLE Documento_Leido ADD CONSTRAINT Documento_Leido_Alumno
    FOREIGN KEY (ID_Alumno)
    REFERENCES Alumno (ID_Alumno)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Documento_Leido_Documento (table: Documento_Leido)
ALTER TABLE Documento_Leido ADD CONSTRAINT Documento_Leido_Documento
    FOREIGN KEY (ID_Documento)
    REFERENCES Documento (ID_Documento)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Foro_Tipo_Acceso (table: Foro)
ALTER TABLE Foro ADD CONSTRAINT Foro_Tipo_Acceso
    FOREIGN KEY (ID_Tipo_Acceso)
    REFERENCES Tipo_Acceso (ID_Tipo_Acceso)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Jefe_Carrera_Persona (table: Jefe_Carrera)
ALTER TABLE Jefe_Carrera ADD CONSTRAINT Jefe_Carrera_Persona
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

-- Reference: Medalla_Documento (table: Medalla)
ALTER TABLE Medalla ADD CONSTRAINT Medalla_Documento
    FOREIGN KEY (ID_Documento)
    REFERENCES Documento (ID_Documento)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Medalla_Ganada_Alumno (table: Medalla_Ganada)
ALTER TABLE Medalla_Ganada ADD CONSTRAINT Medalla_Ganada_Alumno
    FOREIGN KEY (ID_Alumno)
    REFERENCES Alumno (ID_Alumno)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Medalla_Ganada_Medalla (table: Medalla_Ganada)
ALTER TABLE Medalla_Ganada ADD CONSTRAINT Medalla_Ganada_Medalla
    FOREIGN KEY (ID_Medalla)
    REFERENCES Medalla (ID_Medalla)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Noticia_Jefe_Carrera (table: Noticia)
ALTER TABLE Noticia ADD CONSTRAINT Noticia_Jefe_Carrera
    FOREIGN KEY (ID_Jefe_Carrera)
    REFERENCES Jefe_Carrera (ID_Jefe_Carrera)  
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

-- Reference: Usuario_Tipo_Acceso (table: Usuario)
ALTER TABLE Usuario ADD CONSTRAINT Usuario_Tipo_Acceso
    FOREIGN KEY (ID_Tipo_Acceso)
    REFERENCES Tipo_Acceso (ID_Tipo_Acceso)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- sample data
-- Insert data into Tipo_Acceso table
INSERT INTO Tipo_Acceso (Nombre, Descripcion) VALUES
                                                  ('Estudiante', 'Acceso para estudiantes'),
                                                  ('Docente', 'Acceso para docentes'),
                                                  ('Jefe de Carrera', 'Acceso para jefes de carrera');

-- Insert data into Usuario table
INSERT INTO Usuario (Nombre_Google, Correo_Google, ID_Tipo_Acceso) VALUES
                                                                       ('Juan Perez Gomez', 'juan.perez@ucb.edu.bo', 1),
                                                                       ('Maria Lopez Fernandez', 'maria.lopez@ucb.edu.bo', 1),
                                                                       ('Carlos Sanchez Rojas', 'csanchez@ucb.edu.bo', 2),
                                                                       ('Laura Gomez Perez', 'lgomez@ucb.edu.bo', 3);

-- Insert data into Persona table
INSERT INTO Persona (CI, Nombres, Ap_Paterno, Ap_Materno, ID_Usuario) VALUES
                                                                          ('12345678', 'Juan', 'Perez', 'Gomez', 1),
                                                                          ('87654321', 'Maria', 'Lopez', 'Fernandez', 2),
                                                                          ('11223344', 'Carlos', 'Sanchez', 'Rojas', 3),
                                                                          ('22334455', 'Laura', 'Gomez', 'Perez', 4);

-- Insert data into Alumno table
INSERT INTO Alumno (Semestre, Materias_Aprobadas, Puntaje, ID_Persona) VALUES
                                                                           (3, 12, 75.5, 1),
                                                                           (5, 20, 85.0, 2);

-- Insert data into Docente table
INSERT INTO Docente (About_Me, Grado_Estudio, ID_Persona) VALUES
                                                              ('Docente con amplia experiencia en administración.', 'Maestría', 3),
                                                              ('Jefe de Carrera y docente con experiencia en gestión académica.', 'Doctorado', 4);

-- Insert data into Jefe_Carrera table
INSERT INTO Jefe_Carrera (ID_Persona) VALUES
    (4);

-- Insert data into Foro table
INSERT INTO Foro (Nombre, Tema, ID_Tipo_Acceso) VALUES
                                                    ('Foro de Administración', 'Discusión sobre técnicas de administración', 1),
                                                    ('Foro de Innovación', 'Ideas para la mejora de procesos', 2);

-- Insert data into Comentario table
INSERT INTO Comentario (Texto, Fecha_Hora, ID_Foro, ID_Usuario) VALUES
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

-- Insert data into Documento_Leido table
INSERT INTO Documento_Leido (Fecha, ID_Alumno, ID_Documento) VALUES
                                                                 ('2024-11-10', 1, 1),
                                                                 ('2024-11-11', 2, 2);

-- Insert data into Medalla table
INSERT INTO Medalla (Nombre, Puntaje, ID_Documento) VALUES
                                                        ('Medalla de Oro', 100.0, 1),
                                                        ('Medalla de Plata', 75.0, 2),
                                                        ('Medalla de Bronce', 50.0, 1);

-- Insert data into Medalla_Ganada table
INSERT INTO Medalla_Ganada (Fecha, ID_Alumno, ID_Medalla) VALUES
                                                              ('2024-11-12', 1, 1),
                                                              ('2024-11-13', 2, 2);

-- Insert data into Noticia table
INSERT INTO Noticia (Nombre, Descripcion, Fecha_Inicio, Fecha_Fin, ID_Jefe_Carrera) VALUES
    ('Conferencia de Administración', 'Conferencia sobre técnicas modernas de administración', '2024-11-15', '2024-11-16', 1);

-- Insert data into Pokemon table
INSERT INTO Pokemon (ID_Alumno, Nombre_Pokemon, Nivel) VALUES
                                                           (1, 'Pikachu', 10),
                                                           (2, 'Charmander', 8);
