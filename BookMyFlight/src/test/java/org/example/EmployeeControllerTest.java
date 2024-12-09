package org.example;

import org.example.controller.DatabaseController;
import org.example.controller.EmployeeController;
import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Regular;
import org.example.model.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeControllerTest {
    protected static Employee e1 = new Employee("Carl", "Anderson", "1234567890", "carl@gmail.com", "dah!@$#2NSA!ccs");

    @Test
    void testAddEmployee_FailName1() {
        String firstName = "Maria1";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailName2() {
        String firstName = "Maria-";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailName3() {
        String firstName = "Maria ";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailName4() {
        String firstName = "";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber1() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117A";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber2() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117*";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber3() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117 ";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber4() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "438002111";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber5() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "438002111111";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> employee.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber6() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPhoneNumber7() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = " ";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailEmail1() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmailcom";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailEmail2() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "mariagmailcom";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee("Carl", "Anderson", phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailEmail3() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "@mariagmailcom";
        String password = "R@ah!&uDnDS";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPassword1() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria1@gmail.com";
        String password = "R@a";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_FailPassword2() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria1@gmail.com";
        String password = "R@aAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaa";
        EmployeeController employee = new EmployeeController();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_AddExistingUser() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> EmployeeController.addEmployee("Carl", "Anderson", "1234567890", "carl@gmail.com", "dah!@$#2NSA!ccs"));
        assertEquals("Account already exists!", exception.getMessage());
    }

    @Test
    void testAddEmployee_SuccessfullyAddedEmployee() {
        boolean expResult = true;
        boolean result = EmployeeController.addEmployee("Rain", "Parker", "9214189143", "rain@gmail.com", "mlswq!@3i8xdnND");
        assertEquals(expResult, result);
    }

    @Test
    void bookTickets_BookTicketsForNonexistentPassenger() {
        Employee e = new Employee();
        Ticket t = new Ticket();
        List<Ticket> tickets = new ArrayList<>(List.of(t));
        EmployeeController employee = new EmployeeController();
        Passenger p = new Regular("Jeffrey", "Johnston", "2345678921", "jeffrey@gmail.com", "123@snadLDB3$@2");
        Exception executable = assertThrows(IllegalArgumentException.class, () -> employee.bookTickets(p, tickets));
        assertEquals("Wrong Passenger Credentials", executable.getMessage());
    }
}
