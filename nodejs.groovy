pipeline{
 agent any 
 stages{
 stage("git-pull"){
 steps{
 sh 'sudo apt-get update -y'
 sh 'sudo apt-get install git -y'
 git branch: 'main', credentialsId: 'gitnew', url: 'git@github.com:usertan123/the-example-app.nodejs.git'
 }
 }
 stage("Maven-Build"){
 steps{
 sh 'sudo apt-get update -y'
 sh 'sudo apt-get install maven curl unzip -y'
 sh 'mvn clean package'
 }
 }
//  stage("push-artifact"){
//  steps{

//  sh 'sudo mv /var/lib/jenkins/workspace/student.app/target/studentapp-2.2-SNAPSHOT.war /home/ubuntu/student-${BUILD_ID}.war'
//  sh 'aws s3 cp /home/ubuntu/student-${BUILD_ID}.war s3://new-artifacts-123'
//  }
//  }
//   stage("Dev-Deployment"){
//   steps{
//      withCredentials([sshUserPrivateKey(credentialsId: 'tomcat', keyFileVariable: 'tomcat')]) {
//          sh'''
//   ssh -i ${tomcat}  -o StrictHostKeyChecking=no ubuntu@3.7.68.98<<EOF
//     aws s3 cp s3://new-artifacts-123/student-${BUILD_ID}.war .
//                 curl -O https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.78/bin/apache-tomcat-8.5.78.tar.gz
//                 sudo tar -xvf apache-tomcat-8.5.78.tar.gz -C /opt/
//                 sudo sh /opt/apache-tomcat-8.5.78/bin/shutdown.sh
//                 sudo cp -rv student-${BUILD_ID}.war studentapp.war
//                 sudo cp -rv studentapp.war /opt/apache-tomcat-8.5.78/webapps/
//                 sudo sh /opt/apache-tomcat-8.5.78/bin/startup.sh
//                 '''
//       } 
//   }
//   }
//  }
// }