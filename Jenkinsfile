pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Ensure no running processes or locks on the target directory
                    bat 'taskkill /F /IM java.exe || echo No Java processes found'
                    bat 'mvn clean install'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop only the application running on port 8080
                    def pid = bat(script: 'netstat -ano | findstr :8080 | for /F "tokens=5" %a in (\'findstr LISTENING\') do @echo %a', returnStdout: true).trim()
                    if (pid) {
                        bat "taskkill /PID ${pid} /F"
                    }

                    // Find the JAR file in the target directory and run it
                    def jarFile = bat(script: 'for %i in (target\\*.jar) do @echo %i', returnStdout: true).trim()
                    if (jarFile) {
                        // Run the JAR file on port 8080
                        bat "start /b java -jar ${jarFile} --server.port=8080"
                    } else {
                        error 'No JAR file found in the target directory.'
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive artifacts
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
