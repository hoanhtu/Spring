# syntax=docker/dockerfile:1
# aaa
FROM openjdk:22-oraclelinux8
LABEL authors="Dev"

#working directory

WORKDIR /app

#copy from your pc to container

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

#run this inside the image
RUN ./mvnw dependency:go-offline

COPY src ./src

#CMD echo aaaa
CMD ./mvnw spring-boot:run

#ENTRYPOINT ["top", "-b"]