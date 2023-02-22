
# Online Shop

A project of online shop, based on a REST API architecture. Provides resources for the frontend. Application allows for the sale of a wide range of products, making it suitable for use in various commercial industries. 


## Technology Stacks

**Backend**

- Java 17
- Spring Boot
- Spring Security / JWT Authentication
- MySQL
- Liquibase
- JPA/Hibernate
- Maven 

**Frontend**

- Angular
- TypeScript
- HTML
- SCSS


## Features

**User:**                         

- Account creation and login
- Product browsing 
- Product searching
- Placing orders
- Adding reviews of a product
- Email notifications about placed orders
- Filtering products by category


**Admin:**

- Product management
- Order management
- Product category management
- Moderation of product reviews

## Installation

Clone the repository:
```sh
git clone https://github.com/xSawk/Shop-backend.git
```
Navigate to the project directory and build the project using Maven:
```sh
mvn clean install
```  
Run the application:
```sh
mvn spring-boot:run
```  
The application should now be running at http://localhost:8080

