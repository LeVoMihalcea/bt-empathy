pipeline{
    agent any
stages {
stage('Build'){
    steps{
        withMaven{
            sh 'mvn clean package -DskipTests'
        }
    }
}

stage('Deployment'){
    steps{
        sh '''
        docker-compose -f /opt/bt-empathy/docker-compose.yml down || true
        docker image rm bt-empathy || true
        docker build -t bt-empathy .
        docker-compose -f /opt/bt-empathy/docker-compose.yml up -d
        '''
}}}}
