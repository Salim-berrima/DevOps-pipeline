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


    }
}
