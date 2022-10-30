pipeline {
environment {
registry = "salimberrima/projet"
registryCredential = 'fd2b5514-02a3-465e-b20c-cee88537a313'
dockerImage = ''
}
agent any
stages {


stage("Git clone"){
            steps{
              
                git branch: 'Fournisseur', 
                credentialsId: '73bd4b1f-f1e8-48a9-93b3-3c8a2d195c60', 
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
            
            
              stage('Test & Jacoco Static Analysis') {
            steps {
            junit 'src/reports/*.xml'
            jacoco()
            }
        }
        
                  stage ('MVN SONARQUBE') { 
//    def scannerHome = tool 'SonarScanner 4.0';
        steps{
        withSonarQubeEnv('sonarqube.8.9.7') { 
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
            
stage('Build Dockerfile') {
steps{
            
script {
dockerImage = docker.build registry + ":$BUILD_NUMBER"
}
}
}
stage('Pushing image ') {
steps{
script {
docker.withRegistry( '', registryCredential ) {
dockerImage.push()
}
}
}
}
stage('Cleaning up') {
steps{
sh "docker rmi $registry:$BUILD_NUMBER"
}
}
stage('deploy docker-compose'){
steps{
script{
  sh 'docker-compose up -d'

}
}
       
}
}
}
