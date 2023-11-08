FROM openjdk:11
EXPOSE 8099
ADD target/Devops-master-1.0.jar Devops-master.jar
ENTRYPOINT ["java","-jar","Devops-master.jar "]