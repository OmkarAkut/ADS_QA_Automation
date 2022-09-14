pipeline {
    agent { label '10.204.20.105' }
    stages {
        stage('Checkout code') {
            agent { label '10.204.20.105' }
            steps {
                checkout([$class: 'GitSCM',
                branches: [[name: '*/main' ]],
                extensions: scm.extensions,
                userRemoteConfigs: [[
                    url: 'http://ads-gitlab.harrispaas.com:8082/dss/AffinityDecisionSupport.git',
                    credentialsId: '4a6fb591-0193-489d-aacb-f445734a1a0e'
                ]]
            ])
            }
        }
         stage('Clone code into tmp folder') {
            
            steps {
               // bat 'start cmd.exe /c E:\\tmp\\testcase.bat'
               Runtime.getRuntime().exec("E:\\tmp\\testcase.bat");
            }
        }
    }
}
