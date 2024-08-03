pipeline {
    agent any

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

                    // Check if the script stopped the application correctly
                    if (currentBuild.result == 'UNSTABLE') {
                        echo "Application was not running, but proceeding with deployment."
                    }

                    // Start the new application
                    bat '''
                    set JAR_FILE=""
                    for /R %%i in (target\\*.jar) do (
                        set JAR_FILE=%%i
                    )
                    if not "!JAR_FILE!"=="" (
                        echo Running JAR file: !JAR_FILE!
                        start "" java -jar "!JAR_FILE!"
                    ) else (
                        echo No JAR file found in the target directory.
                        exit /b 1
                    )
                    '''
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
        failure {
            echo "Build failed."
        }
        success {
            echo "Build succeeded."
        }
    }
}
