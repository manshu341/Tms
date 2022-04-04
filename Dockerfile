FROM openjdk:11
ADD target/trainer-server.jar trainer.jar
EXPOSE 8100
ENTRYPOINT [ "java", "-jar", "trainer.jar" ]