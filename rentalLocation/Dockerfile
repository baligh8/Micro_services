FROM eclipse-temurin:18-jdk-alpine
EXPOSE 8084
ADD target/rentalLocation*.jar rentalLocation.jar
ENTRYPOINT ["java", "-jar", "rentalLocation.jar"]