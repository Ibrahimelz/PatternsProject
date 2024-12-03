package org.example;

import org.example.model.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseControllerTest {
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

    public static void createTableAirplane() {
        String sql = """
                CREATE TABLE IF NOT EXISTS airplane(
                    airplane_id TEXT PRIMARY KEY,
                    assigned_airline TEXT NOT NULL,
                    available_seats INTEGER NOT NULL,
                    type TEXT NOT NULL
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void drop() {
        String sql = "DELETE FROM passenger WHERE first_name = 'Julia'";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableTicket() {
        String sql = """
                CREATE TABLE IF NOT EXISTS ticket(
                    ticket_id TEXT PRIMARY KEY,
                    passenger_id TEXT,
                    airplane_id TEXT NOT NULL,
                    outbound_date TEXT NOT NULL,   
                    return_date TEXT NOT NULL,       
                    price REAL NOT NULL,
                    trip_type TEXT NOT NULL,
                    status TEXT NOT NULL,   
                    seat_type TEXT NOT NULL,
                    departure TEXT NOT NULL,
                    destination TEXT NOT NULL,
                    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id),
                    FOREIGN KEY (airplane_id) REFERENCES airplane(airplane_id)
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTablePassenger() {
        String sql = """
                CREATE TABLE IF NOT EXISTS passenger(
                    passenger_id TEXT PRIMARY KEY,
                    account_type TEXT NOT NULL,
                    credits INTEGER ,
                    card_expiration_date TEXT,    
                    cvc INTEGER ,
                    card_holder_name TEXT,
                    card_number TEXT ,
                    first_name TEXT NOT NULL,
                    last_name TEXT NULL,
                    phone_number TEXT NOT NULL,
                    email TEXT NOT NULL,
                    password TEXT NOT NULL,
                    bonusDiscount REAL ,
                    limitedWifi TEXT  
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableOperation() {
        String sql = """
                CREATE TABLE IF NOT EXISTS operation(
                    user_id TEXT NOT NULL, 
                    type TEXT NOT NULL ,
                    date TEXT NOT NULL,
                    refunded_credit INTEGER ,
                    refunded_payment REAL,
                    total_payment REAL ,
                    ticket_id TEXT NOT NULL,
                    PRIMARY KEY (date, user_id),
                    FOREIGN KEY (ticket_id) REFERENCES operation(ticket_id)
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTableEmployee() {
        String sql = """
                CREATE TABLE IF NOT EXISTS employee(
                    employee_id INTEGER PRIMARY KEY,
                    first_name TEXT NOT NULL,               
                    last_name TEXT NOT NULL,                   
                    phone_number TEXT NOT NULL,                  
                    email TEXT NOT NULL,
                    password TEXT NOT NULL
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertAirplaneRecord(Airplane airplane) {
        String sql = """
                INSERT INTO airplane(airplane_id, assigned_airline, available_seats, type) VALUES(?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, airplane.getAirplaneID());
            statement.setString(2, airplane.getAssignedAirline());
            statement.setInt(3, airplane.getAvailableSeats());
            statement.setString(4, airplane.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertTicketRecord(Ticket ticket) {
        String sql = """
                INSERT INTO ticket(ticket_id,airplane_id,outbound_date,return_date,price,trip_type,status,
                     seat_type,departure,destination) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ticket.getTicketID());
            statement.setString(2, ticket.getAirplane().getAirplaneID());
            statement.setString(3, ticket.getOutboundDate());
            statement.setString(4, ticket.getReturnDate());
            statement.setDouble(5, ticket.getPrice());
            statement.setString(6, ticket.getTripType());
            statement.setString(7, ticket.getStatus().toString());
            statement.setString(8, ticket.getSeatType().toString());
            statement.setString(9, ticket.getDeparture());
            statement.setString(10, ticket.getDestination());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertPassengerRecord(Passenger passenger) {
        String sql = """
                INSERT INTO passenger(passenger_id,account_type,credits,first_name,last_name,phone_number,email,password) VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, passenger.getUserID());
            statement.setString(2, passenger.getClass().getSimpleName());
            statement.setInt(3, passenger.getCredits());
            statement.setString(4, passenger.getFirstName());
            statement.setString(5, passenger.getLastName());
            statement.setString(6, passenger.getPhoneNumber());
            statement.setString(7, passenger.getEmail());
            statement.setString(8, passenger.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertCancelOperationRecordIntoOperation(Operation operation, User user, double refunded, double totalPayment) {
        String sql = """
                INSERT INTO operation(user_id,type,date,refunded,totalPayment) VALUES(?,?,?,?,?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUserID());
            statement.setString(2, operation.getType());
            statement.setString(3, String.valueOf(operation.getDate()));
            statement.setDouble(4, refunded);
            statement.setDouble(5, totalPayment);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertEmployeeRecord(Employee employee) {
        String sql = """
                INSERT INTO employee(employee_id,first_name,last_name,phone_number,email,password) VALUES(?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getUserID());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getPhoneNumber());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePassengerRecord(Passenger passenger) {
        String sql = "UPDATE passenger SET credits = ?, card_expiration_date = ?, cvc = ?, card_holder_name = ?, card_number = ?  WHERE passenger_id = " + passenger.getUserID();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, passenger.getCredits());
            statement.setString(2, passenger.getCardExpirationDate());
            statement.setInt(3, passenger.getCvc());
            statement.setString(4, passenger.getCardHolderName());
            statement.setString(5, passenger.getCardNumber());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateTicketrecord(Ticket ticket) {
        String sql = "UPDATE ticket SET passenger_id = ? WHERE ticket_id = " + ticket.getTicketID();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ticket.getPassengerID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Ticket> queryTicketAll() {
        String sql = """
                        SELECT ticket.*, airplaine.* 
                        FROM ticket
                        JOIN airplane 
                        ON ticket.airplane_id = airplane.airplane_id
                """;
        List<Ticket> tickets = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                Airplane airplane = new Airplane();
                airplane.setAirplaneID(resultSet.getString("airplane_id"));
                airplane.setAssignedAirline(resultSet.getString("assigned_airline"));
                airplane.setAvailableSeats(resultSet.getInt("available_seats"));
                airplane.setType(resultSet.getString("type"));
                ticket.setAirplane(airplane);
                ticket.setTicketID(resultSet.getString("ticket_id"));
                ticket.setPassengerID(resultSet.getString("passenger_id"));
                ticket.setOutboundDate(resultSet.getString("outbound_date"));
                ticket.setReturnDate(resultSet.getString("return_date"));
                ticket.setPrice(resultSet.getDouble("price"));
                ticket.setTripType(resultSet.getString("trip_type"));
                ticket.setStatus(TicketStatus.valueOf(resultSet.getString("status")));
                ticket.setSeatType(SeatClass.valueOf(resultSet.getString("seat_type")));
                ticket.setDeparture(resultSet.getString("departure"));
                ticket.setDestination(resultSet.getString("destination"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }


    public static List<Passenger> queryPassengerAll() {
        String sql = "SELECT * FROM passenger";
        List<Passenger> passengers = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Passenger passenger = null;
                if (resultSet.getString("account_type").equalsIgnoreCase("regular")) {
                    passenger = new Regular();
                } else if (resultSet.getString("account_type").equalsIgnoreCase("premium")) {
                    passenger = new Premium();
                }
                passenger.setUserID(resultSet.getString("passenger_id"));
                passenger.setCredits(resultSet.getInt("credits"));
                passenger.setCardExpirationDate(resultSet.getString("card_expiration_date"));
                passenger.setCvc(resultSet.getInt("cvc"));
                passenger.setCardHolderName(resultSet.getString("card_holder_name"));
                passenger.setCardNumber(resultSet.getString("card_number"));
                passenger.setFirstName(resultSet.getString("first_name"));
                passenger.setLastName(resultSet.getString("last_name"));
                passenger.setPhoneNumber(resultSet.getString("phone_number"));
                passenger.setEmail(resultSet.getString("email"));
                passenger.setPassword(resultSet.getString("password"));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengers;
    }


    public static List<Employee> queryEmployeeAll() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setUserID(resultSet.getString("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPassword(resultSet.getString("password"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public static List<CancelOperation> queryAllCancelOperations() {
        String sql = "SELECT * FROM operation WHERE type = 'CancelOperation'";
        List<CancelOperation> cancelOperations = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                CancelOperation cp = null;
                cp.setUserId(resultSet.getString("user_id"));
                cp.setType(resultSet.getString("type"));
                cp.setDate(LocalDateTime.parse(resultSet.getString("date")));
                cp.setTicketID(resultSet.getString("ticket_id"));
                cp.setRefundedCredit(resultSet.getInt("refund_credit"));
                cp.setRefundedMoney(resultSet.getDouble("refunded_payment"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cancelOperations;
    }

    public static List<PaymentOperation> queryAllPaymentOperations() {
        String sql = "SELECT * FROM operation WHERE type = 'PaymentOperation'";
        List<PaymentOperation> paymentOperations = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            Map<String, Double> paymentPerTicket = new HashMap<>();
            while (resultSet.next()) {
                PaymentOperation po = new PaymentOperation();
                po.setUserId(resultSet.getString("user_id"));
                po.setType(resultSet.getString("type"));
                po.setDate(LocalDateTime.parse(resultSet.getString("date")));
                String ticketId = resultSet.getString("ticket_id");
                Double totalAmount = resultSet.getDouble("total_amount");
                paymentPerTicket.put(ticketId, totalAmount);
                po.setPaymentPerTicket(paymentPerTicket);
                paymentOperations.add(po);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentOperations;
    }

}
