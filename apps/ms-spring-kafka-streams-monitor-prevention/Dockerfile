#maven:3.8.5-openjdk-17
ARG BUILD_IMAGE=maven@sha256:e299c7dd06091386e49ce6658b30d61bbf62b4287608e156c3468b52c0c78305
#eclipse-temurin:17-jdk
ARG RUNTIME_IMAGE=eclipse-temurin@sha256:83192a0fa4653d389d50671f723f79e28a928fb7e3c9ee7002e3a69ae5dd3149

FROM ${BUILD_IMAGE} AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM ${RUNTIME_IMAGE}
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/*.jar docker-ms-spring-kafka-streams-monitor-prevention-1.0.0.jar
EXPOSE 8091
CMD ["java", "-jar", "/docker-ms-spring-kafka-streams-monitor-prevention-1.0.0.jar"]
