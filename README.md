# Cart Service
This service manages user's cart. In addition, it also sort the cart according to the criterta, i.e., By Product Price, By Store Distance, given by the user. 

## How to run
1. Clone the repository

2. Open terminal and go to the root directory (SmartShopping) of this repository

3. Export Docker host ip address (http://eventuate.io/docs/usingdocker.html)

4. Write following commands in the terminal:

    - `git checkout cart-service`

    - `./mvnw clean package`

    - `docker-compose build`

    - `docker-compose up`

5. The query service will be running on $(DOCKER_HOST_IP):8082/carts. The command service will be running on  $(DOCKER_HOST_IP):8081/carts

6. Use `ctr + c` to stop. Then write `docker-compose down` to remove the containers

7. To re-run, use following commands, please write the commands mentioned in step 3 (except `git checkout cart-service`)
 