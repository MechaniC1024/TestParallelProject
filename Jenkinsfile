pipeline {
  agent any
  stages{
    stage("TEST"){
      steps{
        git url: "https://github.com/MechaniC1024/TestParallelProject.git"
        dir("TestParallelProject"){
          bat ''' mvn clean test '''
        }
      }
    }
  }
}    
