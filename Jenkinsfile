pipeline{

    agent any

    stages{

        stage("build"){
            steps{
                echo("Building")
            }

        }
         stage("deploy to dev"){
            steps{
                echo("deploy to dev")
            }

        }
         stage("deploy to qa "){
            steps{
                echo("deploy to dev")
            }

        }
         stage("regression test on qa"){
            steps{
                echo("regression test on qa")
            }
}
             stage("deploy to stage"){
            steps{
                echo("deploy to stage")
            }
}
             stage("sanity test"){
            steps{
                echo("sanity test")
            }
}
            stage("prod env"){
            steps{
                echo("prod env")
            }
        }

    }
}
