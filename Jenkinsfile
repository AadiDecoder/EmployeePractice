pipeline {
	agent any

    tools {
		maven "maven"
    }

    environment {
		PATH = "/usr/local/bin:${env.PATH}"
		APP_NAME = "spring-cicd-demo"
        RELEASE_NO = "1.0.0"
        DOCKER_USER = "adarshdev11"
        // The following need to be set dynamically in script block
        IMAGE_NAME = "${DOCKER_USER}/${APP_NAME}"
        IMAGE_TAG = "${RELEASE_NO}-${BUILD_NUMBER}"
    }

    stages {
		stage("SCM Checkout") {
			steps {
				checkout scmGit(
                    branches: [[name: '*/main']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/AadiDecoder/EmployeePractice.git']]
                )
            }
        }

        stage("Build Process") {
			steps {
				sh 'mvn clean install'
            }
        }

        stage("Build Docker Image") {
			steps {
				script {
					def imageName = "${env.DOCKER_USER}/${env.APP_NAME}"
                    def imageTag = "${env.RELEASE_NO}-${env.BUILD_NUMBER}"
                    env.IMAGE_NAME = imageName
                    env.IMAGE_TAG = imageTag
                    sh "docker build -t ${imageName}:${imageTag} ."
                }
            }
        }

        stage("Deploy Image to Docker Hub") {
			steps {
				withCredentials([string(credentialsId: 'docker-cred', variable: 'DOCKER_PASSWORD')]) {
					script {
						sh "docker login -u ${env.DOCKER_USER} -p ${DOCKER_PASSWORD}"
                        sh "docker push ${env.IMAGE_NAME}:${env.IMAGE_TAG}"
                    }
                }
            }
        }
	}
}