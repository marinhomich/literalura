<h1 align="center">
  <h2 align="center">Literalura</h2>
</h1>

<h3 align="center">Alura LATAM x Oracle Next Education</h3>

<p align="center">A challenge from class 6 of ONE.</p>

Features
--------

- Query books from the Gutendex API
- Save book data in a local database
- Retrieve book data from the local database
- Use the project as a model for any similar package

Requirements
--------------------

- JDK 17
- Maven
- PostgreSQL

Install and Run
-------------------

Clone the repository to your machine:

    git clone https://github.com/marinhomich/literalura.git

Set your environment variables in the `application.properties` file:

    cd literalura/src/main/resources && ls

Go to the project directory again...

    cd literalura

and install the dependencies using this Maven command:

    mvn clean install

Run the project with the command:

    mvn spring-boot:run -q

### Tech Stack

    - Java
    - Spring Boot
    - Postgres
    - Hibernate
    - Gutendex API
