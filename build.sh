./gradlew build
docker build -t kikiki0611/shorten-app . --platform linux/x86_64
docker push kikiki0611/shorten-app