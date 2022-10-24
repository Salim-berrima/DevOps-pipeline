pipeline{
    agent any

    stages{
        stage("git pull"){
            steps{


                git branch: 'facture_branches',
                credentialsId: '60527384-563b-4df0-85b8-10954cf21222',
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
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t devops-integration .'
                }
            }
        
           }
        
        
        stage ('pushing Image'){
            steps{
                script{
                    sh 'docker login -u "omri.raed@esprit.tn" -p "raed1234#" docker.io'
                    sh 'docker tag devops-integration:latest raed12345/devops-integration:latest'
                    sh ' docker push nesrinehm1996/devops-integration:latest'
                }
            }
        }


    }
}
