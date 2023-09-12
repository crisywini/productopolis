[![Java CI with Maven](https://github.com/crisywini/productopolis/actions/workflows/maven.yml/badge.svg?branch=develop&event=push)](https://github.com/crisywini/productopolis/actions/workflows/maven.yml)

# Productopolis 🥽👓🎹


This microservice will handle all the product-related functionalities. It will be responsible for managing product information, such as name, description, price, stock availability, etc. Users can search for products, view their details, and add them to their shopping cart. Some possible features include:

- Product creation, updating, and deletion
- Product search and filtering
- Product details retrieval


## Usage

Installed Maven and Java 17 and Kafka.

To start Kafka:

    kafka-server-start.sh ~/kafka_2.12-3.5.1/config/kraft/server.properties

To Fix IPV6 error in WSL2:

    sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1

And 

    sudo sysctl -w net.ipv6.conf.default.disable_ipv6=1

And change config servers in _config/server.properties_

    listeners=PLAINTEXT://localhost:9092


## Api Documentation

Can be found at:

    http://localhost:8081/swagger-ui/index.html


## Event Driven Communication

### Use case 

_**When**_ the message Order Processed its listened

_**Then**_ The products will be updated by its new stock


### Use Case

_**When**_ the message Order Processed its listened

_**And**_ The products has incorrect ids

_**Or**_ The products has incorrect stock to update

_**Then**_ The Order Failed message will be sent to Ship-M8



## Database Schema 

![Database_schema](https://github.com/crisywini/productopolis/blob/develop/db-scheme-productopolis.png)
