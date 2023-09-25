# Shipping API

<details>
<summary>Table of content</summary>

- - [Shipping Microservice Description](#shipping-microservice-description)
  - [Features ✨](#features-)
  - [Project structure](#project-structure)
  - [API Endpoints](#api-endpoints)
  - [API Documentation](#api-documentation)
  - [Database Schema](#database-schema)
  - [Installation & How to use 📥](#installation-)
  - [Running Tests 🧪](#running-tests-)
    - [Tests Structure](#tests-structure)
  - [Tech/Framework used 🧰](#techframework-used-)
  </details>

### Note!: Its part of a graduation project of Fawry internship.

## Shipping Microservice Description

The Shipping Microservice is an important component of our e-commerce platform, designed to efficiently manage the shipping and delivery aspects of our customers' orders.
And it encapsulates shipping-related functionalities

## Features ✨

you can do this :

- You can list all of your shipments between a start and end date.
- You can create shipments with items.

## Project structure

<details>
<summary>Click to expand!</summary>

```bash
## Project Structure

📦main
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┗ 📂shippingapis
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ShipmentController.java
 ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Shipment.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItem.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Store.java
 ┃ ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItemMapper.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentMapper.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┣ 📂shipment
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentModelForRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShipmentModelForResponse.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂shipmentItem
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ShipmentItemModel.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂store
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜StoreModelForRequest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreModelForResponse.java
 ┃ ┃ ┃ ┃ ┣ 📂repo
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItemRepo.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentRepo.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreRepo.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItemServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItemService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreService.java
 ┃ ┃ ┃ ┃ ┗ 📜ShippingApIsApplication.java
 ┗ 📂resources
 ┃ ┣ 📂db
 ┃ ┃ ┗ 📂migration
 ┃ ┃ ┃ ┗ 📜V1__create_shipment_Item_store_tables.sql
 ┃ ┗ 📜application.properties
```

</details>

</details>

## API Endpoints

| Methods | Endpoints                                |
| :------ | :--------------------------------------- |
| `POST`  | `/shipments `                            |
| `GET`   | `/shipments/by-customer-and-date-range ` |

## API Documentation

Swagger URL

```
https://shipping-service-dr3e.onrender.com/swagger-ui/index.html#/
```

## Database Schema

- [ERD link!](https://mermaid.live/edit#pako:eNqVk11rgzAUhv9KyLUt1mn9uCubYzLcSpVdDEGCydpATVyMsE7974vadqW6sUWQJO_xPT4nJzXMOCbQg0TcUbQVKE8YUCNh3dPNoodgHfpPMaiHdTcCJsmWCEAxWD-Ot7OqlDwnIlX6_YUeB6EfxatwDcodLXLCZIqRJN8BL6vN7cNqA0qJZFWOjZWtIMp1_AUXWOXrWCbcVLKCsm265xmSlB_B2oFxgrRpZrOmPq_TIPZD4IGMM4koK6_jB_3v5TnDX5XnZ8qT8l4hJqk8jBkLwXGVyYsKtNO_2XZsDYji542vmGg5VIXgFMmrMvweerbv5X_Q93TTJzU6IKhB1Uc5oli1aJ8jgXJHcpJAT00xeUPVXiYwYa0KRZXk0YFl0JOiIhqsiq69jn192iwQg14NP6A3M-e6pVtLyzUM3XFu9IWlwYPaXxrG3HRtR1_orqXeRqvBT86VhTFfmK5h26bpLC1zYVu932uvDfYEUwUYDpeqv1vtF3Z9BKQ)

## Installation

To run the Shipping System locally, you will need the following:
- Java 17 or higher
- Maven
- PostgreSQL

Once you have the required tools installed, follow these steps to install the Shipping System:

1. Clone this repository:
    ```shell
       https://github.com/Muhammed-Sameih/ShippingAPIs.git
    ```
2. Edit the database configurations in application.properties file.
3. Navigate to the project directory:
    ```shell
    cd ShippingAPIs
    ```
4. Build and run the application using Maven
    ```shell
    mvn spring-boot:run
    ```
5. Explore the Application: Once the application is up and running, open your web browser and access it at: `http://localhost:8080`
6. Access API Documentation: Additionally, you can explore the API documentation by navigating to: `http://localhost:8080/swagger-ui.html`. This provides detailed insights into the available API endpoints and functionalities.
## Running Tests 🧪

The testing is done using `Mockito and JUnit `.

<details>
<summary> All </summary>

![1](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/fd0bb9f1-4c8e-415f-9632-3975c6ef50c5)

</details>

<details>
<summary>ShipmentItemRepoTest</summary>

![5](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/b37e9732-a601-4356-ac4b-8146e5bf5424)

</details>

<details>
<summary> ShipmentRepoTest </summary>

![6](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/54ea1f03-ecf2-4ff0-b2c7-41cd1547c707)

</details>
<details>
<summary> StoreRepoTest </summary>

![7](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/eea98e76-661b-4387-ae98-7ced629e82ef)

</details>

<details>
<summary> ShipmentItemServiceImplTest </summary>

![4](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/86915807-0ab7-4909-87e6-9cbbdace371a)

</details>

<details>
<summary>ShipmentServiceImplTest</summary>

![3](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/62070de9-ea85-4f48-b466-8d52c16a7bfe)

</details>
<details>
<summary> StoreServiceImplTest </summary>

![2](https://github.com/AbdelrahmanShaheen/task-manager-api/assets/77184432/74482665-d1a2-4294-b532-7ebfd680b24c)

</details>

### Tests Structure

<details>
<summary>Click to expand!</summary>

```bash
📦test
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┗ 📂shippingapis
 ┃ ┃ ┃ ┃ ┣ 📂repo
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItemRepoTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentRepoTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreRepoTest.java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┗ 📂impl
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentItemServiceImplTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ShipmentServiceImplTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜StoreServiceImplTest.java
 ┃ ┃ ┃ ┃ ┗ 📜ShippingApIsApplicationTests.java
 ┗ 📂resources
 ┃ ┗ 📜application.properties

```

</details>

## Tech/Framework used 🧰

- Java
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL
- Maven 
- Swagger
- Docker
- Intellij
