pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                // Run Maven build
                bat 'taskkill /F /IM java.exe || echo No Java processes found'
                bat 'mvn clean install'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Change to the target directory
                    dir("${env.WORKSPACE}\\target") {
                        echo 'Looking for JAR files in target directory...'
                        def jarFile = "cicd-0.0.1-SNAPSHOT.jar"

                        // Debug output to verify the jar file name
                        echo "Jar file found: ${jarFile}"

                        if (jarFile) {
                            // Run the JAR file on port 8080
                            echo "Starting JAR file..."
                            bat "start /b java -jar \"${jarFile}\" --server.port=8080"
                        } else {
                            error 'No JAR file found in the target directory.'
                        }
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
