# Smoke-Notes-Backend
Smoke Notes Backend is the backend service for the Smoke Notes web application (ongoing development) -- a digital cigar journal where users can log and manage detailed notes about the cigars they smoke.

The backend contains the REST API for the future web application, and it supports CRUD operations for creating, reading, updating, and deleting cigars as an entry.

## Technologies Used
* Spring Boot Framework
* Java Programming Language
* PostgreSQL database
* Docker
* Maven Build Tools
* Postman (HTTP Request testing)

## Core Functionality
The API allows users to keep a digital journal of their cigar smoking experiences by logging what cigar they are smoking as well as some of the key traits of the cigar, including:

* Cigar Object:
  * Cigar Name
  * Factory Name
  * Wrapper Type (MADURO, OSCURO, HABANO, COROJO, CONNECTICUT, SAN_ANDRES, CANDELA, SUMATRA)
  * Wrapper Country
  * Binder Country
  * Filler Country
  * User Ratings

## How to Run the Backend Applicaion
1. Download Docker Desktop
    * Docker Desktop will need to be open in order for the Docker Compose file to spin up the container with the PostgreSQL
2. Clone the repo from GitHub
3. Create a `.env` file. The .env file is ignored by git, therefore you will need to create your own and fill it out accordingly. There should be 3 fields that you need to fill in for your project. Make sure not to use any spaces or quotes.
   1. `DB_NAME=name`: this will represent the name of the database
   2. `DB_USER=username`: the username for the database that you are creating
   3. `DB_PASS=password`: the password for your database
4. Open your terminal and `cd` to the project directory
5. Run the command `./mvnw spring-boot:run`
    * Note: The project will run on localhost port 8080 and the database will run on port 5432, so make sure these ports are clear

## API Endpoints
|   Endpoint    | HTTP Request | URI                           | Description                                                                           |
|:-------------:|:------------:|:------------------------------|:--------------------------------------------------------------------------------------|
|    findAll    |     GET      | /api/cigars                   | Returns all cigar objects stored in the database                                      |
|   findById    |     GET      | /api/cigars/{id}              | Returns the cigar object with the given ID                                            |
|    create     |     POST     | /api/cigars                   | Creates a cigar object from the request body and saves it to the database             |
|    update     |     PUT      | /api/cigars/{id}              | Updates the cigar object with the given ID to contains the values in the request body |
|    delete     |    DELETE    | /api/cigars/{id}              | Deletes the cigar object with the given ID                                            |
| findByWrapper |     GET      | /api/cigars/wrapper/{wrapper} | Returns a list of all the cigars with the given wrapper type                          |
| findByRating  |     GET      | /api/cigars/ratings/{rating}  | Returns a list of all the cigars with the given rating                                |
|    findAll    |     GET      | /api/users                    | Returns all user objects stored in the database                                       |
|   findById    |     GET      | /api/users/{id}               | Returns the user object with the given ID                                             |
|    create     |     POST     | /api/users                    | Creates a user object from the request body and saves it to the database              |
|    update     |     PUT      | /api/users/{id}               | Updates the user object with the given ID to contain the values in the request body   |
|    delete     |    DELETE    | /api/users/{id}               | Deletes the user object with the given ID                                             |