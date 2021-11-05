docker build -t position-container:v0.2 .
docker-compose up --build -d
docker network connect position-network position-api
docker network inspect position-network