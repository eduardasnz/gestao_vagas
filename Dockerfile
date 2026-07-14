FROM eclipse-temurin:21-jdk AS build

RUN apt-get update
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM eclipse-temurin:21-jre
EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]