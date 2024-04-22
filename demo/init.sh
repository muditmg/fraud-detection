#!/bin/bash

echo "Init..."

APPS=$(pwd)/../apps
DEMO=$(pwd)/../demo

cd "$APPS/ms-spring-kafka-monitor-prevention"

echo "1. Build Project ms-spring-kafka-monitor-prevention And Create Image With Spring..."
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ms-spring-kafka-monitor-prevention:1.0.0 -DskipTests

#echo "Create image With Dockerfile ..."
#docker build --tag=ms-spring-kafka-monitor-prevention:1.0.0 .

cd "$APPS/ms-spring-kafka-streams-monitor-prevention"
echo "2. Build Project ms-spring-kafka-streams-monitor-prevention And Create Image With Spring..."
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=ms-spring-kafka-streams-monitor-prevention:1.0.0 -DskipTests


echo "3. Up Cluster Kafka and ms-spring-kafka-monitor-prevention and ms-spring-kafka-streams-monitor-prevention ..."
cd ../..
docker-compose -f docker-compose.yml up -d


echo ""
echo "Waiting for platform to be ready...."
(docker-compose logs spring-kafka -f -t &) | grep -q 'Started MsSpringKafkaMonitorPreventionApplication'

echo "4. Now you can execute the next script to send Transaction automatically to the broker kafka"
echo "To execute the script:"
cd "$DEMO"
/bin/bash producerTransaction.sh

echo "...Finish!"