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
                sshagent(['dfe54083-b74c-4c57-af30-1547b7d09fff']) {
                    sh '''
                    scp target/UserApp-0.0.1-SNAPSHOT.jar ubuntu@192.168.64.2:/home/ubuntu/
                    ssh ubuntu@192.168.64.2 'sudo systemctl restart ecommstore-app'
                    '''
                }
            }
        }
    }
}