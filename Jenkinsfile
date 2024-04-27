pipeline {
    agent any
    environment {
        // Define default values for environment variables
        JAVA_HOME = '/var/lib/jenkins/jdk-17'
        PATH = "$JAVA_HOME/bin:$PATH"
        SCANNER_HOME=tool 'sonar-scanner'
    }
    tools{
        maven 'Maven'
    }
    

    stages {

         

       
        stage('test') {
            steps {
                script {
                    sh './mvnw test'
                }
            }
       
        
 
}
}
}
