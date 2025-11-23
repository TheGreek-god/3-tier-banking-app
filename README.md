docker run --name mysql-bank -e MYSQL_ROOT_PASSWORD=greekgod -e MYSQL_DATABASE=bankappdb -p 3306:3306 -d mysql:8.0

docker-compose up -d bankapp