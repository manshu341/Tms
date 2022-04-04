FROM openjdk:11
ADD target/api-gateway.jar api.jar
EXPOSE 8765
ENTRYPOINT [ "java", "-jar", "api.jar" ]