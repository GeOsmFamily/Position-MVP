docker build -t position-container:v0.1 .
docker-compose up --build -d
docker network inspect position-network
lazydocker

docker network connect position-network position-api
docker network connect position-network api-position-mysql