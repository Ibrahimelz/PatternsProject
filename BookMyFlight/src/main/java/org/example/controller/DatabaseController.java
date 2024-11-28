package org.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private static final String DATABASE_PATH = "jdbc:sqlite:./src/main/resources/database/systemdata.db";

    private static Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(DATABASE_PATH);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static final String CREATE_AIRPLANE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS airplane(
                airplaneId INTEGER PRIMARY KEY,
                assignedAirline TEXT NOT NULL,
                availableSeats INTEGER CHECK(availableSeats >= 0),
                type TEXT NOT NULL
            )
            """;

    public static final String CREATE_TICKET_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS ticket(
                ticketID INTEGER PRIMARY KEY,
                passenger_id INTEGER NOT NULL,
                airplane_id INTEGER NOT NULL,
                outboundDate STRING NOT NULL,   
                returnDate STRING NOT NULL,       
                price REAL NOT NULL CHECK(price >= 0),
                tripType TEXT NOT NULL,
                status TEXT NOT NULL,    --???? check class diagram pr enum ou pas?????
                seatType TEXT NOT NULL CHECK(seatType IN ('ECONOMY', 'BUISNESS', 'FIRST_CLASS')),
                departure TEXT NOT NULL,
                destination TEXT NOT NULL,
                FOREIGN KEY (passenger_id) REFERENCES Passenger(passengerID),
                FOREIGN KEY (airplane_id) REFERENCES Airplane(airplaneID)
            )
            """;

    public static final String CREATE_PASSENGER_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS passenger(
                passengerID INTEGER PRIMARY KEY,
                account_type TEXT NOT NULL,
                credits INTEGER NOT NULL CHECK(credits >= 0),,
                cardExpirationDate TEXT NOT NULL CHECK (cardExpirationDate > DATE('now')),    
                cvc INTEGER NOT NULL CHECK(cvc BETWEEN 100 AND 999),
                cardHolderName TEXT NOT NULL,
                cardNumber INTEGER NOT NULL,
                firstName TEXT NOT NULL,
                lastName TEXT NOT NULL,
                phoneNumber TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE CHECK (email LIKE '%@%.%'),
                password TEXT NOT NULL UNIQUE CHECK (LENGTH(password) BETWEEN 5 AND 20),
                FOREIGN KEY (account_type) REFERENCES AccountType(accountType)
            )
            """;

    public static final String CREATE_ACCOUNTTYPE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS accountType(
                accountType TEXT PRIMARY KEY,
                bonusDiscount REAL NOT NULL CHECK (bonusDiscount >= 0 AND bonusDiscount <= 1), //isnt it 5??
                limitedWifi TEXT NOT NULL 
            )
            """;

    public static final String CREATE_OPERATION_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS operation(
                operationID INTEGER PRIMARY KEY,
                passenger_id INTEGER NOT NULL, 
                employee_id INTEGER NOT NULL,
                type TEXT NOT NULL CHECK(type IN ('Cancel Operation', 'Payment Operation')),
                date TEXT NOT NULL,                 -- G pas mit localDateTime ici pcq sa fait plus de sens de le mettre dans java que ici, pcq en premier sa se passe en java ensuite sql, so sql localdatetime va etre en retard de klk seconde so cpa efficient 
                refunded REAL NOT NULL CHECK(refunded >= 0),
                totalPayment REAL NOT NULL CHECK(totalPayment >= 0),
                FOREIGN KEY (passenger_id) REFERENCES Passenger(passengerID),
                FOREIGN KEY (employee_id) REFERENCES Employee(employeeID)
            )
            """;

    public static final String CREATE_EMPLOYEE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS employee(
                employeeID INTEGER PRIMARY KEY,
                firstName TEXT NOT NULL,               
                lastName TEXT NOT NULL,                   
                phoneNumber TEXT NOT NULL,                  
                email TEXT NOT NULL UNIQUE CHECK (email LIKE '%@%.%'),
                password TEXT NOT NULL UNIQUE CHECK (LENGTH(password) BETWEEN 5 AND 20)
            )
            """;

    //    public void initSystem() {
//        DatabaseController.createDishTable();
//        DatabaseController.createUserTable();
//        DatabaseController.createUserOrderDishTable();
//
//        restaurantSystem.setDishes(DatabaseController.queryDish());
//        restaurantSystem.setUsers(DatabaseController.queryUser());
//    }
//
//    public void addNewUser(String name) {
//        threadPool.submit(() -> {
//            User user = new User(name);
//            DatabaseController.insertNewUser(user);
//        });
//    }


}
