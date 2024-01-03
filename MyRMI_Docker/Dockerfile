FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/main/java/myrmi src/main/java/myrmi
#COPY . .
RUN mvn package

FROM openjdk:8
WORKDIR /usr/app
COPY --from=build /usr/src/app/target/MyRMI_Docker-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
