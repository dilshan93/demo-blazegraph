FROM java:8
EXPOSE 9090:9090
ADD /target/spring-boot-project.jar spring-boot-project.jar
ENTRYPOINT ["java","-jar","spring-boot-project.jar"]