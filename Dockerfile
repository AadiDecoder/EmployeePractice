FROM openjdk:17
WORKDIR /appContainer
COPY ./target/demo.jar /appContainer
EXPOSE 8282
CMD ["java","-jar","demo.jar"]