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
                    // Check if any process is using port 8080 and stop it
                    def portCheck = bat(script: 'netstat -ano | findstr :8080 | findstr LISTENING', returnStatus: true)
                    if (portCheck == 0) {
                        // If the command succeeded, extract the PID and kill the process
                        def pid = bat(script: '''
                            for /F "tokens=5" %%i in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do @echo %%i
                        ''', returnStdout: true).trim()
                        if (pid) {
                            bat "taskkill /PID ${pid} /F || echo No process found on port 8080"
                        }
                    } else {
                        echo 'No process found on port 8080'
                    }

                    // Find the JAR file in the target directory
                    def jarFile = bat(script: '''
                        for %i in (target\\*.jar) do @echo %i
                    ''', returnStdout: true).trim()
                    jarFile = jarFile.readLines().find { it.endsWith(".jar") }
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
