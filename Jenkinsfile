pipeline {
    agent any

  //  tools {
  //      maven 'Maven 3.8.5' // Ensure this matches the Maven installation name in Jenkins
  //  }

    stages {
        stage('Build') {
            steps {
                script {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    bat 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop any running instance of the application
                    bat 'taskkill /F /IM java.exe'

                    // Find and run the JAR file
                    bat 'for /R %i in (*.jar) do java -jar %i'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
