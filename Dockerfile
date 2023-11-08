FROM openjdk:11
EXPOSE 8099
ADD target/DevOps_Project-2.1.jar DevOps_Project.jar
ENTRYPOINT ["java","-jar","DevOps_Project.jar"]