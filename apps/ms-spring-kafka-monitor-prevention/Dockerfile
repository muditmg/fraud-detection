#FROM eclipse-temurin:17-jdk-alpine
#VOLUME /tmp
#
## Get Jar file
#ARG JAR_FILE=target/ms-spring-kafka-monitor-prevention-*.jar
#
#COPY ${JAR_FILE} docker-ms-spring-kafka-monitor-prevention-1.0.0.jar
#EXPOSE 8090
#
#ENTRYPOINT ["java","-jar","/docker-ms-spring-kafka-monitor-prevention-1.0.0.jar"]



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
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/*.jar docker-ms-spring-kafka-monitor-prevention-1.0.0.jar
EXPOSE 8090
CMD ["java", "-jar", "/docker-ms-spring-kafka-monitor-prevention-1.0.0.jar"]

### Create Image with Spring
# $ mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ms-spring-kafka-monitor-prevention:1.0.0 -DskipTests



### Commnad's
## Create image with dockerfile
# $ docker build --tag=ms-spring-kafka-monitor-prevention:1.0.0 .

## Run image
# $ docker run -p 8090:8090 --name container-ms-spring-kafka-monitor-prevention ms-spring-kafka-monitor-prevention:1.0.0


## DOCKER COMPOSE UP
# $ docker-compose -f docker-compose.yml up -d

