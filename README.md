# FinTA
FinTA is FINancial Transactions Analytics application, it's main purpose is to extract transactions from bank statement PDF, store them to database, and then to pair these transactions with invoices. This is a work in progress, there is no working version of this application right now.

## Technology

The application is based on Spring Boot 3 (backend) and Vaadin 24 (frontend). We're using PostgreSQL for the database, but you can change this quite easily and use the database of your choice.

## Installation

Just clone this repository, create the empty database (seee the _application.properties_ file for details on database name and user) and run `./mvnw` to start the application. It might take some time to download all the dependencies to yout computer and do the first compile, but the consecutive application starts should be much faster.
