package org.example.model;

import java.util.List;

import lombok.*;
import org.example.controller.UserController;


@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Getter
    protected String userID;
    @Getter
    protected String firstName;
    @Getter
    protected String lastName;
    @Getter
    protected String phoneNumber;
    @Getter
    protected String email;
    @Getter
    protected String password;
    @Getter
    private static final int CREDIT_FIVE_PERCENT = 500;
    @Getter
    private static final int CREDIT_TEN_PERCENT = 1000;
    @Getter
    private static final int CREDIT_FIFTEEN_PERCENT = 1500;
    @Getter
    private static final int CREDIT_TWENTY_PERCENT = 2000;
    @Getter
    private static final double DISCOUNT_FIVE_PERCENT = 0.05;
    @Getter
    private static final double DISCOUNT_TEN_PERCENT = 0.1;
    @Getter
    private static final double DISCOUNT_FIFTEEN_PERCENT = 0.15;
    @Getter
    private static final double DISCOUNT_TWENTY_PERCENT = 0.2;
    @Getter
    protected static int counter = 1;

    public User(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.userID = generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    /**
     * Generates an id with a specific format
     * @return the formatted generated id
     */
    protected static String generateId() {
        return String.format("000%02d", counter++);
    }

    /**
     * Validates if inputted data to create an account for passenger is valid or not
     * @param firstName first name that will be validated
     * @param lastName last name that will be validated
     * @param phoneNumber phone number that will be validated
     * @param email email that will be validated
     * @return true if all fields entered are valid and false if a field is incorrect
     */
    public static boolean validateInputUserCreation(String firstName, String lastName, String phoneNumber, String email, String password) {
        int minPassWordLen = 5;
        int MaxPassWordLen = 20;
        int emailMinLen = 5;
        int emailMaxLen = 255;
        if (!firstName.matches("[a-zA-Z]{1,50}") || !lastName.matches("[a-zA-Z]{1,50}")) {
            return false;
        }
        if (!phoneNumber.matches("\\d{10}")) {
            return false;
        }
        if (!(email.length() >= emailMinLen && email.length() <= emailMaxLen) || !(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) ||
                !email.matches("^[^.].*")) {
            return false;
        }
        if (!(password.length() >= minPassWordLen) || !(password.length() <= MaxPassWordLen)) {
            return false;
        }
        return true;
    }
}
