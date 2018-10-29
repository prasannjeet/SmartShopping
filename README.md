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
 
---
## API for User Service
---
**Create a cart**
---
* **URL** `$(DOCKER_IP_ADDRESS):8081/carts`
* **Method:** `POST`
* **Headers:** `Content-Type: application/json`
* **Body:**
        
        {
            "userId": "Your userId"
        }

* **Response:**

        {
            "id": "some id",
            "userId": "Your userId",
            "products": []
        }

---
## API for Product Service
---
**Update products**
---
* **URL** `$(DOCKER_IP_ADDRESS):8081/carts/products/{barcode}`
* **Method:** `PUT`
* **Headers:** `Content-Type: application/json`
* **Body:**
        
        {
            "name": "Your name",
            "brand": "Your brand"
        }

* **Response status:** 200

---
**Delete products**
---
* **URL** `$(DOCKER_IP_ADDRESS):8081/carts/products/{barcode}`
* **Method:** `DELETE`
* **Response status:** 200

---
## API for Client
---
**Get carts**
---
* **URL** `$(DOCKER_IP_ADDRESS):8082/carts`
* **Method:** `GET`
* **Response:**

        [
            {
                "id": "some id",
                "userId": "some userId",
                "products": [
                    {
                        "id": "product id"
                        "barcode": "product barcode",
                        "name": "product name",
                        "brand": "product brand",
                        "quantity": "product quantity"
                    },
                    ...
                ]
            },
            ...
        ]

---
**Get a cart**
---
* **URL** `$(DOCKER_IP_ADDRESS):8082/carts/{userId}`
* **Method:** `GET`
* **Response:**

            {
                "id": "some id",
                "userId": "some userId",
                "products": [
                    {
                        "id": "product id"
                        "barcode": "product barcode",
                        "name": "product name",
                        "brand": "product brand",
                        "quantity": "product quantity"
                    },
                    ...
                ]
            }
            
---
**Add product in cart**
---
* **URL** `$(DOCKER_IP_ADDRESS):8081/carts/products`
* **Method:** `POST`
* **Headers:** `Content-Type: application/json`
* **Body:**

        {
            "barcode": "Your product barcode",
            "name": "Your product name",
            "brand": "Your product brand",
            "quantity": "Your product quantity (e.g. 1, 2.6)",
            "userId": "Your userId"
        }

* **Response:**

        {
            "id": "some id"
            "barcode": "Your product barcode",
            "name": "Your product name",
            "brand": "Your product brand",
            "quantity": "Your product quantity (e.g. 1, 2.6)",
            "userId": "Your userId"
        }

---
**Update product quantity from cart**
---
* **URL** `$(DOCKER_IP_ADDRESS):8081/carts/{userId}/products/{productId}/{quantity}`
* **Method:** `PUT`
* **Response:**

        {
            "id": "some id"
            "barcode": "Your product barcode",
            "name": "Your product name",
            "brand": "Your product brand",
            "quantity": "Your quantity",
            "userId": "Your userId"
        }

---
**Delete product quantity from cart**
---
* **URL** `$(DOCKER_IP_ADDRESS):8081/carts/{userId}/products/{productId}`
* **Method:** `PUT`
* **Response:**

        {
            "id": "id of delete product"
            "barcode": "barcode of delete product",
            "name": "product name of delete product",
            "brand": "product brand of delete product",
            "quantity": "quantity of delete product",
            "userId": "userId of delete product"
        }
---

