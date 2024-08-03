pipeline {
    agent any

    tools {
        maven 'Maven 3.9.8' // Ensure this matches the Maven installation name in Jenkins
    }

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
                    // Stop the existing application
                    bat 'stop_application.bat'

                    // Start the new application
                    bat 'for /R %i in (target\\*.jar) do java -jar %i'
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
