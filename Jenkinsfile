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

        stage('Test') {
            steps {
                script {
                    // You can include test commands here if needed
                    echo 'Skipping tests for this build'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Find the JAR file in the target directory and run it
                    def jarFile = bat(script: 'for %i in (target\\*.jar) do @echo %i', returnStdout: true).trim()
                    if (jarFile) {
                        bat "java -jar ${jarFile}"
                    } else {
                        error 'No JAR file found in the target directory.'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Build finished.'
        }

        success {
            echo 'Build succeeded.'
        }

        failure {
            echo 'Build failed.'
        }
    }
}
