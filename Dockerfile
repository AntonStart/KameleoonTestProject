FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/kameleoonTest-*.jar kameleoonTest.jar
ENTRYPOINT ["java","-jar","/kameleoonTest.jar"]