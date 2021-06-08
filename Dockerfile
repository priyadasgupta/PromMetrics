FROM maven:3.6.3-openjdk-8 as MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=MAVEN_BUILD /build/target/PromMetrics*.jar PromMetrics.jar
ENTRYPOINT ["java","-jar","PromMetrics.jar"]
