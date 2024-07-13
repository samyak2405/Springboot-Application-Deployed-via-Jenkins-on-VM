pipeline {
    agent any
    environment {
            JAVA_HOME = '/opt/homebrew/Cellar/openjdk@17/17.0.11'
        }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/samyak2405/Springboot-Application-Deployed-via-Jenkins-on-VM.git'
            }
        }
        stage('Build') {
            steps {
                sh 'echo $JAVA_HOME'

                dir('UserApp') {  // Navigate to the ECommApp subdirectory
                                    sh 'mvn clean install'
                                }
            }
        }
        stage('Deploy') {
            steps {
                sshagent(['ubuntu']) {
                    sh '''
                    scp -o StrictHostKeyChecking=no -i ~/.ssh/id_rsa -v UserApp/target/UserApp-0.0.1-SNAPSHOT.jar ubuntu@192.168.64.2:/home/ubuntu/
                    echo '#!/bin/bash' > /home/ubuntu/askpass.sh
                    echo 'echo "$SUDO_PASSWORD"' >> /home/ubuntu/askpass.sh
                    ssh -o StrictHostKeyChecking=no -i ~/.ssh/id_rsa ubuntu@192.168.64.2 'chmod +x /home/ubuntu/askpass.sh'
                    ssh -o StrictHostKeyChecking=no -i ~/.ssh/id_rsa ubuntu@192.168.64.2 'export SUDO_ASKPASS=/home/ubuntu/askpass.sh && sudo -A systemctl restart ecommstore-app'
                    '''
                }
            }
        }
    }
}