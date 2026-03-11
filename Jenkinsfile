pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Build Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Automation Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            allure commandline: 'Allure',
                   includeProperties: false,
                   results: [[path: 'target/allure-results']]
        }
    }
}