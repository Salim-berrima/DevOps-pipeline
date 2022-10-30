pipeline {
    agent any

    stages {
        stage("git pull"){
            steps{
              
                git branch: 'Stock', 
                credentialsId: '8ed45d***', 
                url: 'https://github.com/nesrinehm1996/magasinBack.git'
                    
                }
                
            }
        stage("MVN CLEAN"){
            steps{
              
                sh 'mvn clean package'
                             
                }
                
            }
        stage("MVN TEST"){
            steps{
              
                sh 'mvn clean test'
                             
                }
                
            }
        stage("build"){
            steps{
              
                sh 'mvn install package'
                             
                }
                
            }
        stage("UNIT TEST") {
            steps {
                echo "TESTING project"
                sh 'mvn test -Dmaven.test.failure.ignore=true '
            }
        }
        stage("SonarQube analysis"){
            steps{
            withSonarQubeEnv('sonarqube-8.9.7') {  
                sh 'mvn sonar:sonar'
                sh 'mvn test -Dmaven.test.failure.ignore=true'
                             
                }
             }
                
            }
        stage('nexus deploy') {
        steps{
            sh'mvn deploy  '
           
        }

    }
       
    }
} 
