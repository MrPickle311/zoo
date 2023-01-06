# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build

COPY ./ /home
WORKDIR /home/
RUN mvn package -DskipTests

# Package stage
FROM openjdk:17-slim
COPY --from=build /home/target/zoo.jar /home/zoo.jar
ENTRYPOINT ["java","-jar","/home/zoo.jar"]