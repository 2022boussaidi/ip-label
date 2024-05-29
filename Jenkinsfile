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
}
}
