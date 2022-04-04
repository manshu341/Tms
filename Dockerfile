FROM openjdk:11
ADD target/eureka-server.jar eureka.jar
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "eureka.jar" ]