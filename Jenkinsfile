pipeline{
    agent any
    
  
   
    stages{
        
          stage("git pull"){
            steps{
              
                git branch: 'produitBack', 
                credentialsId: '8cca21f7-732f-4451-8b06-512b72565d25', 
                url: 'https://github.com/nesrinehm1996/magasinBack.git'
                  
                }
                
            }
    
           
        stage('MVN CLEAN') {
steps {
sh' mvn clean install -DskipTests'

}
}
stage('MVN COMPILE') {
steps {
  sh 'mvn clean compile -DskipTests'
}
}


stage('Build'){
steps{
sh 'mvn package -DskipTests'
}
}

stage('MVN TEST') {
steps {
sh 'mvn test -DskipTests'
}

}

stage('Test & Jacoco Static Analysis') {
steps {
junit 'src/reports/*.xml'
jacoco()
}
}
 
 
        stage('SonarQube analysis') {
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        withSonarQubeEnv('sonarqube-8.9.7') { 
        // If you have configured more than one global server connection, you can specify its name
//      sh "${scannerHome}/bin/sonar-scanner"
        sh "mvn sonar:sonar"
    }
        }
        }
  
     
        
        

    stage('nexus deploy') {
        steps{
            sh'mvn deploy -DskipTests '
           
        }

    }       
        
    
           stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t devops-integration .'
                }
            }
        
           }
        
        
        stages ('pushing Image'){
steps{
script{
sh 'docker login -u "nesrinehm1996" -p "Nesrine@1996 docker.io"
sh 'docker tag devops-integration:latest nesrinehm1996/devops-integration:latest'
sh ' docker push nesrinehm1996/devops-integration:latest'
}
}
}
        
}
   post {
            always {
                junit 'src/reports/TEST-*.xml'
            }
        }
}


