package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {
    @Test
    void validatePaymentInput_WrongCardHolderName1() {
        String cardHolderName = "Maria White1";
        String cardNumber = "28263719320";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardHolderName2() {
        String cardHolderName = " ";
        String cardNumber = "28263719320";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardHolderName3() {
        String cardHolderName = "";
        String cardNumber = "28263719320";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardHolderName4() {
        String cardHolderName = "MariaWhite";
        String cardNumber = "28263719320";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardHolderName5() {
        String cardHolderName = "Maria White*";
        String cardNumber = "28263719320";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardNumber1() {
        String cardHolderName = "Maria White";
        String cardNumber = "2";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardNumber2() {
        String cardHolderName = "Maria White";
        String cardNumber = "2222222222222222222222222222";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardNumber3() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate1() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "0227";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate2() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "020/27";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate3() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/207";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate4() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02*20";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate5() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = "462";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate6() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = "";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCardExpirationDate7() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = " ";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCvc1() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = "46200";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCvc2() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = "4";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCvc3() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = "33a";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCvc4() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = "";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void validatePaymentInput_WrongCvc5() {
        String cardHolderName = "Maria White";
        String cardNumber = "22222222222222*";
        String cardExpirationDate = "02/a0";
        String cvc = " ";

        boolean expResult = false;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }


    @Test
    void validatePaymentInput_ValidInput() {
        String cardHolderName = "Maria White";
        String cardNumber = "212328263719320";
        String cardExpirationDate = "02/27";
        String cvc = "462";

        boolean expResult = true;
        boolean result = Passenger.validatePaymentInput(cardNumber, cardHolderName, cardExpirationDate, cvc);
        assertEquals(expResult, result);
    }

    @Test
    void isCardExpired_CardIsExpired() {
        String cardExpirationDate = "02/23";
        boolean expResult = true;
        boolean result = Passenger.isCardExpired(cardExpirationDate);
        assertEquals(expResult, result);
    }

    @Test
    void isCardExpired_CardIsNotExpired() {
        String cardExpirationDate = "04/27";
        boolean expResult = false;
        boolean result = Passenger.isCardExpired(cardExpirationDate);
        assertEquals(expResult, result);

    }

    @Test
    void isCardExpired_CardDateSameAsToday() {
        String cardExpirationDate = "11/24";
        boolean expResult = false;
        boolean result = Passenger.isCardExpired(cardExpirationDate);
        assertEquals(expResult, result);
    }

    @Test
    void testVerifySeatsAvailable() {
//        Passenger passenger = new Regular("John", "Doe", "1234567890", "john.doe@example.com", "123456");
//        Ticket ticket1 = new Ticket( new Airplane("111","AirCanada",300,"Boeing 777"), "2024-12-01", "2024-12-10", 200.0, "One-way", TicketStatus.UNBOOKED, SeatClass.ECONOMY, "NYC", "LAX");
//        try {
//            passenger.verifySeatsAvailable(ticket1);
//        } catch (IllegalArgumentException e) {
//            fail("Exception should not be thrown when seats are available");
//        }
    }
}
