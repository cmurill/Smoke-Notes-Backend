# Smoke-Notes-Backend
Smoke Notes Backend is the backend service for the Smoke Notes web application (ongoing development) -- a digital cigar journal where users can log and manage detailed notes about the cigars they smoke.

The backend contains the REST API for the future web application, and it supports CRUD operations for creating, reading, updating, and deleting cigars as an entry.

## Technologies Used
* Spring Boot Framework
* Java Programming Language
* PostgreSQL database
* Docker
* Maven Build Tools

## Core Functionality
The API allows users to keep a digital journal of their cigar smoking experiences by logging what cigar they are smoking as well as some of the key traits of the cigar, including:

* Cigar Object:
  * Cigar Name
  * Factory Name
  * Wrapper Type (MADURO, OSCURO, HABANO, COROJO, CONNECTICUT, SAN_ANDRES, CANDELA, SUMATRA)
  * Wrapper Country
  * Binder Country
  * Filler Country

## API Endpoints
| Endpoint | HTTP Request |URI| Description                                      |
|:--------:|:------------:|:--|:-------------------------------------------------|
| findAll  | GET      | /api/cigars  | Returns all cigar objects stored in the database |
|findById|GET|/api/cigars/{id}|Returns the cigar object with the given ID|
|create|POST|/api/cigars|Creates a cigar object from the request body and saves it to the database|
|update|PUT|/api/cigars/{id}|Updates the cigar object with the given ID to contains the values in the request body|
|delete|DELETE|/api/cigars/{id}|Deletes the cigar object with the given ID|
|findByWrapper|GET|/api/cigars/wrapper/{wrapper}|Returns a list of all the cigars with the given wrapper type