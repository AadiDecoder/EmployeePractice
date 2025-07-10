FROM openjdk:21

# Install PostgreSQL client for pg_isready
RUN apt-get update && apt-get install -y postgresql-client

WORKDIR /appContainer

COPY ./target/demo.jar .
COPY wait-for-postgres.sh .

RUN chmod +x wait-for-postgres.sh

EXPOSE 8282

CMD ["sh", "wait-for-postgres.sh"]