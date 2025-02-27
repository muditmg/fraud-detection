# Build stage: Maven with Java 21
ARG BUILD_IMAGE=maven:3.9.6-eclipse-temurin-21
# Runtime stage: Java 21
ARG RUNTIME_IMAGE=eclipse-temurin:21-jdk

FROM ${BUILD_IMAGE} AS maven_tool_chain
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean package -DskipTests

FROM ${RUNTIME_IMAGE}
COPY --from=maven_tool_chain /tmp/target/*.jar /app.jar
EXPOSE 8090
CMD ["java", "-jar", "/app.jar"]