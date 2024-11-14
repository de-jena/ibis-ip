pipeline  {
    agent any

    tools {
        jdk 'OpenJDK17'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        skipDefaultCheckout()
    }

    stages {
    	stage('Clean Workspace') {
            steps {
                // Cleanup before starting the stage
                cleanWs()
            }
        }
    	stage('Checkout') {
            steps {
                // Checkout the repository
                checkout scm 
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    echo "I am building on ${env.BRANCH_NAME}"
                    try {
                        sh "./gradlew clean build testOSGi --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
                    } finally {
                        junit testResults: '**/generated/test-reports/**/TEST-*.xml', skipPublishingChecks: true 
                    }
                }
            }
        }
        stage('Main branch release') {
            when { 
                branch 'main' 
            }
            steps {
                echo "I am building on ${env.BRANCH_NAME}"
                sh "./gradlew clean build release -Drelease.dir=$JENKINS_HOME/repo.gecko/release/org.gecko.emf.util --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }
        stage('Snapshot branch release') {
            when { 
                branch 'develop'
            }
            steps  {
                echo "I am building on ${env.JOB_NAME}"
                sh "./gradlew release --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
        	}
        }
        stage('Resolve Application'){

            steps  {
                echo "I am exporting applications on branch: ${env.GIT_BRANCH}"

                sh "./gradlew de.jena.ibis.runtime:resolve.ibis --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }

        stage('Export Application'){

            steps  {
                echo "I am exporting applications on branch: ${env.GIT_BRANCH}"

                sh "./gradlew de.jena.ibis.runtime:export.ibis --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }

    }
}
