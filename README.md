# SmartShopping
A Microservice Based Smart Shopping App


# How to run:
1. Download the repository
2. Open terminal and go to the root directory of this repository
3. Export Docker host ip address (http://eventuate.io/docs/usingdocker.html)
4. Write following commands in the terminal:
    - `./mvnw clean package`
    - `docker-compose build`
    - `docker-compose up`
5. The app will be running on $(DOCKER_HOST_IP):7088
6. Use `ctr + c` to stop. Then write `docker-compose down` to remove the containers
7. To re-run, write the commands mentioned in step 4
