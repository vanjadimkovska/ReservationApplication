FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ReservationProject-0.0.1-SNAPSHOT.jar /app/ReservationProject.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "ReservationProject.jar", "--spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/reservation?serverTimezone=UTC", "--spring.datasource.username=vanja", "--spring.datasource.password=vanja123", "--spring.jpa.properties.hibernate.ddl-auto=update"]
