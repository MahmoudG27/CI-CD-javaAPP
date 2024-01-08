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
    }
}
