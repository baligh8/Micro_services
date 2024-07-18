FROM eclipse-temurin:18-jdk-alpine
EXPOSE 8081
ADD target/vehicle*.jar vehicle.jar
ENTRYPOINT ["java", "-jar", "vehicle.jar"]
