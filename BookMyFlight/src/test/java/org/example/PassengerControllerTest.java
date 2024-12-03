package org.example;

import org.example.controller.EmployeeController;
import org.example.controller.PassengerController;
import org.example.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerControllerTest {
    protected static Passenger p1 = new Regular("Julia", "Sobh", "8901234567", "julia@gmail.com", "!#sde214NXIW4");
    void testAddPassenger_FailName1() {
        String firstName = "Maria1";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailName2() {
        String firstName = "Maria-";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailName3() {
        String firstName = "Maria ";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailName4() {
        String firstName = "";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber1() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117A";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber2() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117*";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber3() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117 ";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber4() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "438002111";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber5() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "438002111111";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber6() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPhoneNumber7() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = " ";
        String email = "maria@gmail.com";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailEmail1() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria@gmailcom";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailEmail2() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "mariagmailcom";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailEmail3() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailEmail4() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = " ";
        String password = "R@ah!&uDnDS";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPassword1() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria1@gmail.com";
        String password = "*";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddPassenger_FailPassword2() {
        String firstName = "Maria";
        String lastName = "White";
        String phoneNumber = "4380021117";
        String email = "maria1@gmail.com";
        String password = "R@aAAAAAAAAAAAAAAAAAAAAAAAbbalwijwiuqrhdbqwaaaa";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger(firstName, lastName, phoneNumber, email, password));
        assertEquals("User information provided is incorrect!", exception.getMessage());
    }

    @Test
    void testAddEmployee_AddExistingUser() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> PassengerController.addRegularPassenger("Carl", "Anderson", "1234567890", "carl@gmail.com", "dah!@$#2NSA!ccs"));
        assertEquals("Account already exists!", exception.getMessage());
    }

    @Test
    void testAddRegularPassenger_SuccessfullyAddedPassenger() {
        boolean expResult = true;
        boolean result = PassengerController.addRegularPassenger("Robert", "LeBlanc", "2312411223", "robert@gmail.com", "jkn@#siwSAe2D");
        assertEquals(expResult, result);
    }


}
