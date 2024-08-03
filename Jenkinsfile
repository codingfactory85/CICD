pipeline {
    agent any

    environment {
        JAR_FILE = 'C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\TestCICD\\target\\cicd-0.0.1-SNAPSHOT.jar'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Build the project with Maven
                    bat 'mvn clean install'
                }
            }
        }
        stage('Run') {
            steps {
                script {
                    // Stop any running instance of the application on port 8080
                    bat '''
                        for /f "tokens=5 delims= " %a in ('netstat -ano ^| findstr :8080') do (
                            taskkill /F /PID %a
                        )
                    '''
                    // Start the new instance of the application
                    bat '''
                        cd C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\TestCICD\\target
                        java -jar cicd-0.0.1-SNAPSHOT.jar
                    '''
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
