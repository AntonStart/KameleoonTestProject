FROM eclipse-temurin:17-jdk-alpine
WORKDIR /tmp
COPY target/kameleoon-test-*.jar kameleoonTest.jar
ENTRYPOINT ["java","-jar","kameleoonTest.jar"]