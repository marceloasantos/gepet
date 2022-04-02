pipeline {

    agent any
    
    tools {
        maven 'Maven 3.8.5'
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
                    mvn clean install
                    '''
            }

        }
        
        
        stage('Deploy') {

            steps {
                sh '''
                    docker build -t springio/gs-spring-boot-docker .
                    #docker run springio/gs-spring-boot-docker
                    '''
            }

        }


    }
   }
