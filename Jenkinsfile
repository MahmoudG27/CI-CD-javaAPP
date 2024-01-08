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
    }
}
