pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Clean and build the project using Maven
                    bat 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests using Maven
                    bat 'mvn test'
                }
            }
        }

        stage('Blue Deployment') {
            steps {
                script {
                    // Deploy to the blue environment
                    echo 'Deploying to Blue environment...'
                    // Add your deployment script/command here
                }
            }
        }

        stage('Test Deployment') {
            steps {
                script {
                    // Run tests in the deployment environment
                    echo 'Running tests in Blue environment...'
                    // Add your testing script/command here
                }
            }
        }

        stage('Green Deployment') {
            steps {
                script {
                    // Deploy to the green environment
                    echo 'Deploying to Green environment...'
                    // Add your deployment script/command here
                }
            }
        }
    }

    post {
        always {
            // Archive the built artifacts
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
