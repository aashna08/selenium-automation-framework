pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Select Browser')
        string(name: 'URL', defaultValue: 'https://example.com', description: 'Enter URL')
    }

    stages {

        stage('Build Project') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Automation Tests') {
            steps {
                sh "mvn test -Dbrowser=${params.BROWSER} -Durl=${params.URL}"
            }
        }
    }

    post {
        always {

            // Generate Allure Report
            allure commandline: 'Allure',
                   includeProperties: false,
                   results: [[path: 'target/allure-results']]

            // Send Email
            emailext (
                subject: "Automation Test Report - Build #${env.BUILD_NUMBER}",
                body: """
                    Hi Team,

                    Test execution completed.

                    🔹 Build Number: ${env.BUILD_NUMBER}
                    🔹 Build Status: ${currentBuild.currentResult}

                    🔗 Allure Report:
                    ${env.BUILD_URL}allure

                    Thanks,
                    QA Team
                """,
                to: "your-email@example.com"
            )
        }
    }
}