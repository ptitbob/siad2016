FROM postgres:latest

ENV POSTGRES_DB siad2016db
ENV POSTGRES_USER siad
ENV POSTGRES_PASSWORD siad2015

ADD sql/*.sql /docker-entrypoint-initdb.d/
