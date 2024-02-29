FROM maven:3.8.4-openjdk-17 as build

COPY src /usr/src/location-service/src
COPY pom.xml /usr/src/location-service
RUN mvn -f /usr/src/location-service/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17

COPY --from=build /usr/src/location-service/target/device-location-0.0.1-SNAPSHOT.jar /usr/src/location-service/app.jar
WORKDIR /usr/src/location-service
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]