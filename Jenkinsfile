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
                    // Check if port 8080 is in use
                    echo 'Checking for processes on port 8080...'
                    def portCheck = bat(script: 'netstat -ano | findstr :8080', returnStatus: true)
                    if (portCheck == 0) {
                        echo 'Port 8080 is in use. Finding PID...'
                        // Extract PID of the process using port 8080
                        def pid = bat(script: 'for /F "tokens=5" %%i in (\'netstat -ano ^| findstr :8080 ^| findstr LISTENING\') do @echo %%i', returnStdout: true).trim()
                        if (pid) {
                            echo "Stopping process with PID ${pid}."
                            // Stop the process using the PID
                            bat "taskkill /PID ${pid} /F"
                        } else {
                            echo 'No PID found for port 8080.'
                        }
                    } else {
                        echo 'No process found on port 8080'
                    }

                    // Find the JAR file in the target directory
                    echo 'Looking for JAR files in target directory...'
                    def jarFile = bat(script: 'for /F "delims=" %%i in (\'dir /B /A-D target\\*.jar\') do @echo %%i', returnStdout: true).trim()
                    if (jarFile) {
                        // Fix path issue
                        def jarPath = "target\\${jarFile}"
                        echo "Found JAR file: ${jarPath}"
                        // Run the JAR file on port 8080
                        bat "start /b java -jar ${jarPath} --server.port=8080"
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
