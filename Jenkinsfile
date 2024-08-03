pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Run Maven build
                bat 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Stop any previously running instances of the Spring Boot application on port 8080
                    def pid = bat(script: 'for /F "tokens=5" %%i in (\'netstat -ano ^| findstr :8080 ^| findstr LISTENING\') do @echo %%i', returnStdout: true).trim()
                    if (pid) {
                        echo "Stopping process with PID ${pid} on port 8080."
                        bat "taskkill /PID ${pid} /F || echo No process found with PID ${pid}"
                    } else {
                        echo 'No process found on port 8080'
                    }

                    // Find the JAR file in the target directory
                    def jarFile = bat(script: 'for %i in (target\\*.jar) do @echo %i', returnStdout: true).trim()
                    echo "MY JAR: ${jarFile}"
                    if (jarFile) {
                        // Run the JAR file on port 8080
                        echo "Starting Spring Boot application from JAR: ${jarFile}"
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
            // Clean up or archive artifacts if needed
            echo 'Pipeline completed.'
        }
    }
}
