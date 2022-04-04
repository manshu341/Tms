FROM openjdk:11
ADD target/employee-server.jar employee.jar
EXPOSE 8200
ENTRYPOINT [ "java", "-jar", "employee.jar" ]