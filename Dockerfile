

# Start Tomcat

FROM openjdk:8
EXPOSE 8080
ADD target/main-0.0.1-SNAPSHOT.war main-0.0.1-SNAPSHOT.war 
ENTRYPOINT ["java","-jar","/mongo-demo-0.0.1-SNAPSHOT.jar"]
