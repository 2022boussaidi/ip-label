FROM openjdk:8
EXPOSE 8080
ADD target/Main.jar Main.jar
ENTRYPOINT ["java","-jar","/Main.jar"]
