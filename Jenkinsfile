pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.6'
    }

    stages {
        stage('Prepare enviroment') {
            steps {
                sh'''
                sudo yum update -y
                sudo amazon-linux-extras install docker
                sudo yum install docker
                sudo service docker start
                sudo usermod -a -G docker ec2-user
                '''

                sh'''
                sudo chmod 777 /var/run/docker.sock
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
