#!/bin/bash

echo "Init..."

echo "Build Project ms-spring-kafka-monitor-prevention And Create Image With Spring..."
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ms-spring-kafka-monitor-prevention:1.0.0 -DskipTests

#echo "Create image With Dockerfile ..."
#docker build --tag=ms-spring-kafka-monitor-prevention:1.0.0 .

echo "Up Cluster Kafka and  ms-spring-kafka-monitor-prevention ..."
docker-compose -f docker-compose.yml up -d

echo "Now you can execute the next script to send Transaction automatically to the broker kafka"
echo "To execute the script:"

/bin/bash producerTransaction.sh

echo ""