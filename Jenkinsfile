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
	stage('Build and Push Docker Image') {
            steps {
                echo 'Build'
                withCredentials([usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'USERNAME_MG', passwordVariable: 'PASSWORD_MG')]) {
                    sh '''
			docker login -u ${USERNAME_MG} -p ${PASSWORD_MG}
			docker build -t ${DOCKER_IMAGE_NAME}:v${BUILD_NUMBER} .
   			docker push ${DOCKER_IMAGE_NAME}:v${BUILD_NUMBER}
                    '''
                }
            }
        }
	stage('Trivy Scan Image') {
            steps {
                echo 'Scan Image'
                    sh ('docker run -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image ${DOCKER_IMAGE_NAME}:v${BUILD_NUMBER} --no-progress --scanners vuln  --exit-code 0 --severity HIGH,CRITICAL --format table')
                }
            }
        }
	stage ('Cleanup Artifacts') {
            steps {
                script {
                    sh "docker rmi ${DOCKER_IMAGE_NAME}:v${IMAGE_TAG}"
                    sh "docker rmi ${DOCKER_IMAGE_NAME}:latest"
                }
            }
        }
    }
}
