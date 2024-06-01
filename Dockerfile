FROM openjdk:8
EXPOSE 8080
ADD target/Main-0.0.1-SNAPSHOT.jar Main-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Main-0.0.1-SNAPSHOT.jar"]
