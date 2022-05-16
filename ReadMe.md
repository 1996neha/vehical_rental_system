# Vehicle Rental System
Rent different kinds of vehicles such as cars and bikes.

## Tech-Stack
* Java 1.11
* Maven
* Junit
* Spring Boot
* InMemory - H2 Database

## Features Implemented
* Onboard a new branch with available vehicle types.
* Onboard new vehicle(s) of an existing type to a particular branch.
* Display available vehicles for a given branch sorted on price.
* Rent a vehicle for a time slot and a vehicle type with the lowest price.
* Bonus Feature - Dynamic Pricing - when 80% of vehicles of a branch are booked, increase the price by 10% for the remaining not booked vehicles.

## Coding Practices
* Unit Tests for Controller and Service layer.
* Request Validation with correct message specifying the cause.
* Exception Handling - Custom exceptions when Branch/Vehicle not found.

## How to run the code
 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust-1.0.jar` in the `target` folder.
 * `java -jar target/geektrust-1.0.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument.

Input will be read from the text file, and output will be printed to the console.

## How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

## Future Scope
* Unit tests at each level covering all cases.
* Async call for dynamic pricing. 
* Delete endpoints. 
* Features to enhance Dynamic pricing - Decreasing the price to its original value once the demands goes below 80%.
