# SpringBoot with Postgresql rest services

A simple spring boot project to expose a CRUD with rest services, usea postgresql as datastore.

## Services
The project exposes 4 routes to create, list, update and delete an entity.

* GET /developers - return a list of entities.
* POST /developers - create a new entity.
```
# BODY
{
    "nombres_completos": "ponga los nombres aqui",
    "link_github": "ponga el link de github aqui",
    "tecnologias_conocidas": ["tecnologia-uno", "tecnologia-dos", ...]
}
```
* PUT /developers/{id} - update an existing entity.
```
# BODY
{
    "nombres_completos": "ponga los nombres aqui",
    "link_github": "ponga el link de github aqui",
    "tecnologias_conocidas": ["tecnologia-uno", "tecnologia-dos", ...]
}
```
* DELETE /developers{id} - delete an entity by id.

*A live demo can be found at [https://spring-boot-postgresql-rest.herokuapp.com/]*

# Requirements

* JAVA 11
* POSTGRESQL 10 

# Usage

*Before all:* Copy the .env.example to .env, set the variables values and source them.

To run the project exec:

`./gradlew bootRun`

To run the tests exec:

`./gradlew test`

# Docker Image
To create a docker image of the project, as a prerequisite is necesary to have installed docker and run:

`docker build . -t [image_name]:[version]`

# Licence

MIT