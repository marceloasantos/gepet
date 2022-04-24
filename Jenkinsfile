pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.5'
    }

    stages {
        stage('Build') {
            steps {
                sh '''
                mvn clean install -DskipTests
                docker build -t springio/gs-spring-boot-docker .
                '''
            }
        }
        
        stage('Deploy') {
            steps {
                sh '''
                docker rm -f cachorro || true
                docker run -d -p 8081:8081 --name cachorro springio/gs-spring-boot-docker
                '''
            }
        }
        
        stage('Cleanup') {
            steps {
                sh '''
                docker rmi $(docker images -q --filter dangling=true)
                '''
            }
        }
    }
}
