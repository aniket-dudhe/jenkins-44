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
                withSonarQubeEnv(installationName:'sonar',credentialsId: 'sonar-token') {
                        sh '/opt/maven/bin/mvn sonar:sonar'
                   }   
            }
        }
        //  stage('Quality-gate') {
        //     steps {
        //         timeout(10) {
    
        //     }
        //         waitForQualityGate true
        //     }
        // }
        stage('deploy') {
            steps {
                deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'tomcat-cred', path: '', url: 'http://35.78.123.34:8080')], contextPath: '/', war: '**/*.war'
            }
        }
    }
}





// pipeline {
//     agent {label 'slave'}
//     stages {
//         stage('pull-stage') {
//             steps {
//                 git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
//             }
//         }
//         stage('Build') {
//             steps {
//                 sh '/opt/maven/bin/mvn clean package'
//             }
//         }
//         stage('test') {
//             steps {

//                sh '''/opt/maven/bin/mvn sonar:sonar \\
//                       -Dsonar.projectKey=studentapp \\
//                       -Dsonar.host.url=http://18.180.215.234:9000 \\
//                       -Dsonar.login=5fe5333d656b765e9b759cfb4a166b8594f4b779'''
               
//                 //     sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://35.74.27.54:9000  -Dsonar.login=9c547e0733545b5a64a73f56e83c0169e6b5e1d7'
//             }
//         }
//         stage('deploy') {
//             steps {
//                 echo "deploy sucess"
//             }
//         }
//     }
// }




// pipeline {
//     agent {label 'slave'}
//     stages {
//         stage('pull-stage') {
//             steps {
//                 git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
//             }
//         }
//         stage('Build') {
//             steps {
//                 sh '/opt/maven/bin/mvn clean package'
//             }
//         }
//         stage('test') {
//             steps {
//                 withSonarQubeEnv(installationName:'sonar',credentialsId: 'sonar-token') {
//                          sh '/opt/maven/bin/mvn sonar:sonar'
//                   }
                 


//             //   sh '''/opt/maven/bin/mvn sonar:sonar \\
//             //           -Dsonar.projectKey=studentapp \\
//             //           -Dsonar.host.url=http://18.180.215.234:9000 \\
//             //           -Dsonar.login=5fe5333d656b765e9b759cfb4a166b8594f4b779'''
               
//                 //     sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://35.74.27.54:9000  -Dsonar.login=9c547e0733545b5a64a73f56e83c0169e6b5e1d7'
//             }
//         }
//         stage('deploy') {
//             steps {
//                 echo "deploy sucess"
//             }
//         }
//     }
// }