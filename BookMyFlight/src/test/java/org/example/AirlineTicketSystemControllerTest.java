package org.example;

import org.example.controller.AirLineTicketSystemController;
import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Regular;
import org.example.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirlineTicketSystemControllerTest {
    protected static Employee e1 = new Employee("Carl", "Anderson", "1234567890", "carl@gmail.com", "dah!@$#2NSA!ccs");
    protected static Passenger p1 = new Regular("Julia", "Sobh", "8901234567", "julia@gmail.com", "!#sde214NXIW4");

    @BeforeAll
    static void testFindUserInSystem_CreateEmployeeAndPassengerTable() {
        DatabaseControllerTest.createTableEmployee();
        DatabaseControllerTest.createTablePassenger();
    }

    @BeforeAll
    static void testFindUserInSystem_InsertIntoEmployeeAndPassenger() {
        //DatabaseControllerTest.insertEmployeeRecord(e1);
        //DatabaseControllerTest.insertPassengerRecord(p1);
    }

    @Test
    void testFindUserInSystem_UserNotInSystemOrIncorrectEmail() {
        String email = "john@gmail.com";
        User expResult = AirLineTicketSystemController.findUserInSystem(email);
        User result = null;
        assertEquals(expResult, result);
    }

    @Test
    void testFindUserInSystem_UserInSystem() {
        String email = "carl@gmail.com";
        User expResult = e1;
        User result = AirLineTicketSystemController.findUserInSystem(email);
        assertEquals(expResult, result);
    }


}
