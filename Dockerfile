# Use the official Tomcat image from the Docker Hub
FROM tomcat:latest

# Remove the default Tomcat webapps directory contents
RUN rm -rf /usr/local/tomcat/webapps/*

# Add the Spring Boot application WAR file to the Tomcat webapps directory
ADD target/main-0.0.1-SNAPSHOT.war main-0.0.1-SNAPSHOT.war 

# Expose the port that Tomcat listens on
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
