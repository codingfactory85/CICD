pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package' // Using 'sh' for compatibility with Unix systems
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test' // Using 'sh' for compatibility with Unix systems
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def os = sh(returnStdout: true, script: 'uname').trim()

                    if (os == 'Linux' || os == 'Darwin') {
                        // Linux and Mac (Darwin) commands
                        sh '''
                        #!/bin/bash
                        echo "Stopping any existing application running on port 8080..."
                        PID=$(lsof -t -i:8080)
                        if [ ! -z "$PID" ]; then
                            echo "Found PID: $PID"
                            kill -9 $PID
                        else
                            echo "No application running on port 8080."
                        fi

                        echo "Starting new application..."
                        JAR_FILE=$(find target -name "*.jar" | head -n 1)
                        if [ ! -z "$JAR_FILE" ]; then
                            echo "Running JAR file: $JAR_FILE"
                            nohup java -jar "$JAR_FILE" > /dev/null 2>&1 &
                        else
                            echo "No JAR file found in the target directory."
                            exit 1
                        fi
                        '''
                    } else if (os =~ /CYGWIN|MINGW|MSYS|Windows_NT/) {
                        // Windows commands
                        bat '''
                        @echo off
                        echo Stopping any existing application running on port 8080...
                        for /f "tokens=5" %%a in ('netstat -ano ^| findstr ":8080"') do (
                            set "pid=%%a"
                            echo Found PID: !pid!
                            tasklist /fi "pid eq !pid!" | findstr /i "java.exe" >nul && (
                                echo Stopping PID: !pid!
                                taskkill /F /PID !pid!
                            ) || (
                                echo No matching java.exe process found for PID: !pid!
                            )
                        )

                        echo Starting new application...
                        setlocal enabledelayedexpansion
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
                    } else {
                        error "Unsupported operating system: ${os}"
                    }
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
