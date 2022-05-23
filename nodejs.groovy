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
//  stage("Maven-Build"){
//  steps{
//  sh 'sudo apt-get update -y'
//  sh 'sudo apt-get install maven curl unzip -y'
//  sh 'mvn clean package'
//  }
//  }
//   stage("push-artifact"){
//   steps{

//   sh 'sudo mv /var/lib/jenkins/workspace/student.app/target/studentapp-2.2-SNAPSHOT.war /home/ubuntu/student-${BUILD_ID}.war'
//   sh 'aws s3 cp /home/ubuntu/student-${BUILD_ID}.war s3://new-artifacts-123'
//   }
//   }
   stage("Dev-Deployment"){
   steps{
      withCredentials([sshUserPrivateKey(credentialsId: 'nodejs', keyFileVariable: 'nodejs')]) {
          sh'''
   ssh -i ${nodejs} -o StrictHostKeyChecking=no ubuntu@3.6.92.33<<EOF
   git clone https://github.com/contentful/the-example-app.nodejs.git
   sudo apt-get install git -y
   cd the-example-app.nodejs
   curl -fsSL https://deb.nodesource.com/setup_16.x | sudo -E bash -
   sudo apt-get install -y nodejs
   node -v
   npm install
   npm run start:dev
   '''
       } 
   }
   }
  }
 }