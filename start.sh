#!/bin/bash

# Path to the JAR file
JAR_PATH="/home/ubuntu/UserApplication/UserApp-0.0.1-SNAPSHOT.jar"

# Path to the log file
LOG_FILE="/home/ubuntu/UserApplication/application.log"

CONFIG_PATH="/home/ubuntu/UserApplication/config/application-qa.yml"

# Java options (you can add JVM options here)
JAVA_OPTS="-Xms512m -Xmx1024m"

# Start the Spring Boot application
echo "Starting the Spring Boot application..."
nohup java $JAVA_OPTS -jar $JAR_PATH --spring.config.location=$CONFIG_PATH > $LOG_FILE 2>&1 &
# Print the process ID
echo "Spring Boot application started with PID $!"
