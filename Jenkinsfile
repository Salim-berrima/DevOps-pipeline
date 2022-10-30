pipeline {
    agent any
 
environment {
		DOCKERHUB_CREDENTIALS=credentials('*******')
	}
    
    stages{
        stage("git pull"){
            steps{
              
                git branch: 'reglementBack', 
                credentialsId: '**', 
                url: 'https://github.com/nesrinehm1996/magasinback.git'
                    
                }
                
            }
            
        stage('MVN COMPILE') {
                steps {
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
         
   //    stage('SonarQube analysis') {

      //  steps{
      // withSonarQubeEnv('sonarserver') { 
       
      //  sh "mvn sonar:sonar"
  //  }
      // }
      //  }
      //  stage('nexus deploy') {
     //   steps{
         //   sh 'mvn deploy'
           
      //  }

  //  }
        
      stage('Docker Build') {

			steps {
				sh 'docker build -t aminemasseoudi/tpachatproject .'
			}
		}
        
       
		stage('Docker Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		} 
        
        
        	stage('Push') {

			steps {
				sh 'docker push aminemasseoudi/tpachatproject'
			}
		}
	
          
            
}
    post {
		always {
			sh 'docker logout'
		}
	}

}
