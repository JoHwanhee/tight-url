./gradlew assemble
docker build -t kikiki0611/shorten-app . --platform linux/x86_64
docker push kikiki0611/shorten-app

ssh ubuntu@tight.ga -i ~/.ssh/id_rsa "sudo ./deploy.sh"