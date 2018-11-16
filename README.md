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

# How run with two stores :
Same steps 1-5.
5.1. Initialize the current running store servie (see APIs)
5.2. Open a new terminal and go to the root directory of this repository
5.3. Export Docker host ip address
5.4. Open an editor and manually uncoment the lines corresponding to the store-command-service-2 then save in :
    - `docker-compose.yml` -> (lines 177-194)
    - `docker-compose-common.yml` - (lines 37-40)
5.5. Write following `docker-compose up` command in the terminal
Back to step 6.
Don't forget to comment again the files after set down the services to do the whole process again.
    
