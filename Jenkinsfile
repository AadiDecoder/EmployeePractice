pipeline{
	agent any
	tools{
		maven "maven"
	}

	stages{
		stage("SCM Checkout"){
			steps{
				checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/AadiDecoder/EmployeePractice.git']])
			}
		}

		stage("Build Process"){
			steps{
				script{
					sh 'mvn clean install'
				}
			}
		}

		environment{
			APP_NAME = "spring-cicd-demo"
			RELEASE_NO="1.0.0"
			DOCKER_USER="adarshdev11"
			IMAGE_NAME="${DOCKER_USER}"+"/"+"${APP_NAME}"
			IMAGE_TAG="${RELEASE_NO}-${BUILD_NUMBER}"
		}

		stage("Build Docker Image"){
			steps{
				script{
					sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
				}
			}
		}

		stage("Deploy Image to Docker Hub"){
           steps{
			   withCredentials([string(credentialsId: 'docker-cred', variable: 'docker-cred')]) {
			     sh 'docker login -u adarshdev11 -p ${docker-cred}'
			     sh 'docker push ${IMAGE_NAME}:${IMAGE_TAG}'
			   }
		   }
		}
	}
}