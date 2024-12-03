package org.example;

import org.example.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void validateInputUserCreation_WrongFirstName1() {
        String firstName = "maria1";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongFirstName2() {
        String firstName = "**";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongFirstName3() {
        String firstName = "maria**";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongFirstName4() {
        String firstName = " ";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongFirstName5() {
        String firstName = "";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongFirstName6() {
        String firstName = "Maira White";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPhoneNumber1() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "AAAAAAAAAA";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPhoneNumber2() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890000";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPhoneNumber3() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPhoneNumber4() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "";
        String email = "maria@ggmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPhoneNumber5() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = " ";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPhoneNumber7() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "%%%";
        String email = "maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongEmail1() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongEmail2() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "mariagmailcom";
        String password = "dce!@#1HUGW_92ns";

        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongEmail3() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = ".maria@gmail.com";
        String password = "dce!@#1HUGW_92ns";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongEmail4() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = " ";
        String password = "dce!@#1HUGW_92ns";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongEmail5() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "mariagmail.com";
        String password = "dce!@#1HUGW_92ns";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPassword1() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "1234567890";
        String email = "maria@gmail.com";
        String password = "d";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPassword2() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "%%%";
        String email = "maria@gmail.com";
        String password = " ";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPassword3() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "%%%";
        String email = "maria@gmail.com";
        String password = "";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_WrongPassword4() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "%%%";
        String email = "maria@gmail.com";
        String password = "dwqknk312#!#Ziyg8ogfeoh9AHSDQ#!ED3Wdkg3beqdh";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }

    @Test
    void validateInputUserCreation_ValidInput() {
        String firstName = "Maira";
        String lastName = "White";
        String phoneNumber = "%%%";
        String email = "maria@gmail.com";
        String password = " ";
        boolean expResult = false;
        boolean result = User.validateInputUserCreation(firstName, lastName, phoneNumber, email, password);
        assertEquals(expResult, result);
    }
}
