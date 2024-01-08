pipeline{
    agent any
	
    tools {
        jdk 'Java17'
        maven 'Maven3'
    }

    environment {
        DOCKER_IMAGE_NAME = "elnabatshy/cicd-java"
    }
	
    stages{
        
	stage('Clone repository') {
		steps {
        		checkout scm
    		}
	}
	stage('Build Application') {
		steps {
        		sh "mvn clean package"
    		}
	}
	stage('Test Application') {
		steps {
        		sh "mvn test"
    		}
	}
	stage("Sonarqube Analysis") {
            steps {
		script {
                    withSonarQubeEnv(credentialsId: 'sonarqube-cred') {
                        sh "mvn sonar:sonar"
                    }
                }
            }
    	}
	stage('Build Docker Image') {
            steps {
                echo 'Build'
                withCredentials([usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'USERNAME_MG', passwordVariable: 'PASSWORD_MG')]) {
                    sh '''
			docker login -u ${USERNAME_MG} -p ${PASSWORD_MG}
			docker build -t ${DOCKER_IMAGE_NAME}:v${BUILD_NUMBER} .
                    '''
                }
            }
        }
    }
}
