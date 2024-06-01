pipeline {
    agent any
    tools{
        maven 'maven_3_5_0'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/2022boussaidi/ip-labelBack']]])
                sh 'mvn clean install'
            }
        }

         stage('test') {
            steps {
                script {
                    sh './mvnw test'
                }
            }
    }
          stage('Build docker image'){
                   steps{
                       script{
                           sh 'docker build -t chaimaboussaidi2000/ekara_robot_management_service .'
                       }
                   }
               }
       
        stage('Push image to Hub') {
                   steps {
                       script {
                           withCredentials([string(credentialsId: 'id', variable: 'nouveaupass123')]) {
                               sh "echo \${nouveaupass123} | docker login -u chaimaboussaidi2000 --password-stdin"
                           }
                            sh "docker push chaimaboussaidi2000/ekara_robot_management_service"
                       }
                       
                    }
                          
                       }
                   }
               }
         stage('Build and Run Docker Compose') {
                           steps {
                               script {
                                   // Assuming your docker-compose.yml file is in the project root
                                   sh 'docker-compose up -d'
                               }
                           }
                       }
}
}
