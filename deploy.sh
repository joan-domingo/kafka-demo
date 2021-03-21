#!/usr/bin/env bash

rm -rf build/
echo "delete build folder"

./gradlew build
echo "app built"

./gradlew bootJar
echo "jar file generated"

scp -r -i kafka-demo-ec2-instance.pem build/libs/kafka-demo-0.0.1-SNAPSHOT.jar ec2-user@18.185.102.47:/home/ec2-user
echo "jar file copied to ec2 instance"

echo "Connecting to ec2 instance"
ssh -i kafka-demo-ec2-instance.pem ec2-user@18.185.102.47 sudo fuser -k 8080/tcp
echo "Killed process running on port 8080"

ssh -i kafka-demo-ec2-instance.pem ec2-user@18.185.102.47 java -jar kafka-demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=production
echo "Started server using java -jar command"
