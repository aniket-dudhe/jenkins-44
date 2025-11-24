pipeline {
    agent {label 'slave'}
    stages {
        stage('pull-stage') {
            steps {
                git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
            }
        }
        stage('Build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
            }
        }
        stage('test') {
            steps {
                withSonarQubeEnv(installationName: 'sonar',credentialsId: 'sonar-cred') {
                   sh '/opt/maven/bin/mvn sonar:sonar'
               }
                    // sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://18.183.110.133:9000  -Dsonar.login=51590797e14e0536d584661cedf809f92367e2e1'
            }
        }
        stage('deploy') {
            steps {
                echo "deploy sucess"
            }
        }
    }
}


// pipeline {
//     agent {label 'slave'}
//     stages {
//         stage('pull-stage') {
//             steps {
//                 echo "git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'"
//             }
//         }
//         stage('Build') {
//             steps {
//                 echo " build sucess"
//             }
//         }
//         stage('test') {
//             steps {
//                 echo "test suecss"
//             }
//         }
//         stage('deploy') {
//             steps {
//                 echo "deploy sucess"
//             }
//         }
//     }
// }