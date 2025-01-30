pipeline {
    agent any

    environment {
        // Environment variable to store the project version
        PROJECT_VERSION = ''
        SNYK_TOKEN = credentials('SNYK_TOKEN')
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code'
                git branch: 'main',
                    url: 'https://github.com/ghassenjebari/Devops'
            }
        }

        stage('Extract Version') {
            steps {
                script {
                    // Extract the version using Maven Help plugin
                    PROJECT_VERSION = sh(
                        script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout',
                        returnStdout: true
                    ).trim()
                    echo "Project version extracted: ${PROJECT_VERSION}"
                }
            }
        }

        stage('Build with Maven') {
            steps {
                echo PROJECT_VERSION
                echo 'Cleaning and compiling the project with Maven...'
                sh "mvn clean compile -Drevision=${PROJECT_VERSION}"
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running unit tests with Mockito...'
                sh "mvn test -Drevision=${PROJECT_VERSION}"
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Analyzing code quality with SonarQube...'
                withSonarQubeEnv('sonarQube') { 
                    sh "mvn sonar:sonar -Drevision=${PROJECT_VERSION}"
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                echo 'Deploying to Nexus...'
                sh "mvn deploy -DskipTests -Drevision=${PROJECT_VERSION}"
            }
        }

        stage('Snyk Security Scan') {
            steps {
                script {
                    def snykStatus = sh(script: '''
                    export SNYK_TOKEN=${SNYK_TOKEN}
                    snyk test --file=pom.xml --severity-threshold=high
                    snyk monitor --file=pom.xml --org=ghassenjebari1

                ''', returnStatus: true)

                    if (snykStatus != 0) {
                        echo "⚠️ Snyk found security vulnerabilities!"
                    currentBuild.result = 'UNSTABLE' // Mark build as "Unstable" instead of failing
                    }
                }
            }
        }





        stage('Create Docker Image') {
            steps {
                echo "Creating Docker image with tag ${PROJECT_VERSION}..."
                sh "docker build -t ghassenjebari/eventsproject:${PROJECT_VERSION} ."
            }
        }

        stage('Launch Docker Containers') {
            steps {
                echo "Launching Docker containers..."
                sh "docker-compose down"

                sh "PROJECT_VERSION=${PROJECT_VERSION} docker-compose up -d"
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        unstable {
                    echo '⚠️ Pipeline marked as UNSTABLE due to issues.'
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}