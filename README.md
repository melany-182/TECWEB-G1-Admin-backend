# TECWEB-G1-Admin-backend

## Descripción

Backend para el proyecto de Portal Web para la carrera de Administración de Empresas - UCB

## Instrucciones para la instalación de la base de datos

**1.** Tener instalado _PostgreSQL 16_.

**2.** A través de una herramienta de gestión gráfica para Postgres (como _pgAdmin_), crear una base de datos con el nombre `AdminPortalWeb`.

**3.** Ejecutar el script `init.sql` del directorio `database` en la base de datos recién creada.

**4.** Configurar las credenciales de la base de datos en el archivo `application.properties` del proyecto.

## Compilación y ejecución de la aplicación

Para compilar y levantar la aplicación, ejecutar los siguientes comandos en la raíz del proyecto:

```
mvn clean install
mvn spring-boot:run
```
