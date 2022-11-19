pipeline {
    agent any
    
    tools {
        maven '3.8.6'
    }

    stages {
        stage('Prepare enviroment') {
            steps {
                sh'''
                echo "jenkins" | sudo -S apt-get update
                sudo apt-get install docker.io -y
                sudo service docker start
                sudo usermod -a -G docker ubuntu
                '''

                sh'''
                echo "jenkins" | sudo -S chmod 777 /var/run/docker.sock
                '''
            }
        }

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
