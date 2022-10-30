pipeline{
    agent any
    
environment {
		DOCKERHUB_CREDENTIALS=credentials('742f9082-')
	}
    
    stages{
        stage("git pull"){
            steps{


                git branch: 'facture_branches',
                credentialsId: '6052',
                url: 'https://github.com/nesrinehm1996/magasinBack.git'

                }

            }
          stage("MVN COMPILE"){
            steps{

                sh 'mvn clean compile'

                }

            }

              stage('clean'){
                steps{
                    sh 'mvn clean package'


                }

            }
            stage('MVN TEST') {
                steps {
                    sh 'mvn clean test'

                }

            }



       stage('build'){
            steps{
                sh 'mvn install package'
            }
         }
        stage('SonarQube analysis') {
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        withSonarQubeEnv('sonarserver') {
        // If you have configured more than one global server connection, you can specify its name
//      sh "${scannerHome}/bin/sonar-scanner"
        sh "mvn sonar:sonar"
    }
        }
        }
          stage('nexus deploy') {
        steps{
            sh 'mvn deploy'

        }

    }
        stage('Docker Build Image') {

			steps {
				sh 'docker build -t raed12345/tpachatproject .'
			}
		}
          stage(' Login Docker hub ') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW docker.io'
			}
		} 
        stage('Push Image') {

			steps {
				sh 'docker push raed12345/tpachatproject'
			}
	}
	
	    
    }
    
    }
	
