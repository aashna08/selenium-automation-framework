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

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false, results: [[path: 'target/allure-results']]
            }
        }

    }
}