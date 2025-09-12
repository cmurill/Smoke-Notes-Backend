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
3. (Optional) Change the database credentials &#8212; this is a local API so the credentials can be whatever you'd like
    * Open the project in your IDE of choice and navigate to the `compose.yml` file
    * Modify the `POSTGRES_USER` & `POSTGRES_PASSWORD` to whatever you'd like
4. Open your terminal and `cd` to the project directory
5. Run the command `./mvnw spring-boot:run`
    * Note: The project will run on localhost port 8080 and the database will run on port 5432, so make sure these ports are clear

## API Endpoints
| Endpoint | HTTP Request |URI| Description                                      |
|:--------:|:------------:|:--|:-------------------------------------------------|
| findAll  | GET      | /api/cigars  | Returns all cigar objects stored in the database |
|findById|GET|/api/cigars/{id}|Returns the cigar object with the given ID|
|create|POST|/api/cigars|Creates a cigar object from the request body and saves it to the database|
|update|PUT|/api/cigars/{id}|Updates the cigar object with the given ID to contains the values in the request body|
|delete|DELETE|/api/cigars/{id}|Deletes the cigar object with the given ID|
|findByWrapper|GET|/api/cigars/wrapper/{wrapper}|Returns a list of all the cigars with the given wrapper type|
|findByRating|GET|/api/cigars/ratings/{rating}|Returns a list of all the cigars with the given rating|