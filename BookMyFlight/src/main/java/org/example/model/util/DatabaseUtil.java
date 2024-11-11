package org.example.model.util;

import java.sql.*;

public class DatabaseUtil {
    private static final String BASE_URL = "jdbc:sqlite:./src/main/resources/database/systemdata.db";
    public static final String CREATE_AIRPLANE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS airplane(
                airplaneId INTEGER PRIMARY KEY,
                assignedAirline VARCHAR(50) NOT NULL,
                availableSeats INTEGER CHECK(availableSeats >= 0),
                type VARCHAR(25) NOT NULL
            )
            """;
    public static final String CREATE_TICKET_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS ticket(
                ticketID INTEGER PRIMARY KEY,
                passenger_id INTEGER NOT NULL,
                airplane_id INTEGER NOT NULL,
                outboundDate DATETIME NOT NULL,     
                returnDate DATETIME NOT NULL,       
                price DOUBLE NOT NULL CHECK(price >= 0),
                tripType VARCHAR(25) NOT NULL,
                status VARCHAR(25) NOT NULL,    --???? check class diagram pr enum ou pas?????
                seatType VARCHAR(11) NOT NULL CHECK(seatType IN ('ECONOMY', 'BUISNESS', 'FIRST_CLASS')),
                departure VARCHAR(50) NOT NULL,
                destination VARCHAR(50) NOT NULL,
                FOREIGN KEY (passenger_id) REFERENCES Passenger(passengerID),
                FOREIGN KEY (airplane_id) REFERENCES Airplane(airplaneID)
            )
            """;
    public static final String CREATE_PASSENGER_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS passenger(
                passengerID INTEGER PRIMARY KEY,
                account_type VARCHAR(25) NOT NULL,
                credits INTEGER NOT NULL CHECK(credits >= 0),,
                cardExpirationDate DATETIME NOT NULL CHECK (cardExpirationDate > DATE('now')),    
                cvc INTEGER NOT NULL CHECK(cvc BETWEEN 100 AND 999),
                cardHolderName VARCHAR(50) NOT NULL,
                cardNumber INTEGER NOT NULL,
                firstName VARCHAR(50) NOT NULL,
                lastName VARCHAR(50) NOT NULL,
                phoneNumber VARCHAR(15) NOT NULL,
                email VARCHAR(255) NOT NULL UNIQUE CHECK (email LIKE '%@%.%')
                FOREIGN KEY (account_type) REFERENCES AccountType(accountType)
            )
            """;
    public static final String CREATE_ACCOUNTTYPE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS accountType(
                accountType VARCHAR(25) PRIMARY KEY,
                bonusDiscount DOUBLE NOT NULL CHECK (bonusDiscount >= 0 AND bonusDiscount <= 1),
                numOfBagages INTEGER NOT NULL CHECK (numOfBagages >= 0) 
            )
            """;
    public static final String CREATE_OPERATION_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS operation(
                operationID INTEGER PRIMARY KEY,
                passenger_id INTEGER NOT NULL, 
                employee_id INTEGER NOT NULL,
                type VARCHAR(16) NOT NULL CHECK(type IN ('CancelOperation', 'PaymentOperation')),
                date DATETIME NOT NULL,                 -- G pas mit localDateTime ici pcq sa fait plus de sens de le mettre dans java que ici, pcq en premier sa se passe en java ensuite sql, so sql localdatetime va etre en retard de klk seconde so cpa efficient 
                refunded DOUBLE NOT NULL CHECK(refunded >= 0),
                totalPayment DOUBLE NOT NULL CHECK(totalPayment >= 0),
                FOREIGN KEY (passenger_id) REFERENCES Passenger(passengerID),
                FOREIGN KEY (employee_id) REFERENCES Employee(employeeID)
            )
            """;
    public static final String CREATE_EMPLOYEE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS employee(
                employeeID INTEGER PRIMARY KEY,
                position VARCHAR(7) NOT NULL CHECK(position IN ('MANAGER', 'AGENT')),  --???avec ou sans majuscule pcq c enum 
                firstName VARCHAR(50) NOT NULL,               
                lastName VARCHAR(50) NOT NULL,                   
                phoneNumber VARCHAR(15) NOT NULL,                  
                email VARCHAR(255) NOT NULL UNIQUE CHECK (email LIKE '%@%.%')
            )
            """;

}