FROM eclipse-temurin:18-jdk-alpine
EXPOSE 9090
ADD target/gateway-server*.jar gateway-server.jar
ENTRYPOINT ["java", "-jar", "gateway-server.jar"]