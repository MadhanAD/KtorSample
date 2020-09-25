# KtorSample
##### Documentation is in progress..........
#### Commands to Deploy Ktor Project
After creating Dockerfile in root project folder. run the following commands to deploy

1) docker build -t [tag for docker image] .
2) docker images ls
3) docker run -d -p 8090:8090 --name=[name of the container] [image_tag_name]
4) docker start [container_name]
5) docker stop [container_name]
6) docker rm [container_name]

