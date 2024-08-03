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

                    // Create the PowerShell script file to start the application
                    writeFile file: 'start-application.ps1', text: '''
                        Start-Process -FilePath 'java' -ArgumentList '-jar C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\TestCICD\\target\\cicd-0.0.1-SNAPSHOT.jar' -NoNewWindow -PassThru
                    '''
                    // Execute the PowerShell script
                    powershell 'powershell -ExecutionPolicy Bypass -File start-application.ps1'
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
