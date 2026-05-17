pipeline {
    agent any

    tools {
        git 'Git'
        maven 'mvn3.9.9'
    }

    environment {
        IMAGE_NAME = 'mailtosarankm/simplybyte'
        IMAGE_TAG = "${BUILD_NUMBER}"
        CONTAINER_NAME='simplybyte-container'
    }

    stages {

       stage('Git Clone') {
    steps {
        git branch: 'main',
        credentialsId: 'github_simplybyte_credentials',
        url: 'https://github.com/mailtosarankm/simplybyte-springboot.git'
    }
}

        stage('Version Check') {
            steps {
                bat 'mvn -v'
                bat 'git --version'
            }
        }

        stage('Maven Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Jar') {
            steps {
                bat 'mvn clean install -DskipTests=true'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                }
            }
        }

        stage('Push Image') {
            steps {
                bat 'docker push %IMAGE_NAME%:%IMAGE_TAG%'
            }
        }
        stage('Check Helm') {
    steps {
        bat '"C:\Users\DELL\AppData\Local\Microsoft\WinGet\Packages\Helm.Helm_Microsoft.Winget.Source_8wekyb3d8bbwe\windows-amd64\helm.exe" version'
    }
}
        stage('Deploy using Helm') {
    steps {
        bat '''
        helm upgrade --install simplybyte-release .\\simplybyte-chart ^
        --set image.repository=%IMAGE_NAME% ^
        --set image.tag=%IMAGE_TAG%
        '''
    }
}

    }
}