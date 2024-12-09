package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateAccountFrame extends JFrame {
    private CreateAccount2 createAccount2;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    ResourceBundle resourceBundle;

    public CreateAccountFrame() throws HeadlessException {
        setTitle("Create Account Page");
        setSize(500, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createAccount2 = new CreateAccount2();
        add(createAccount2.getCreateAccountPanel());
        changeLanguage();
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        createAccount2.getFirstNameLabel().setText(resourceBundle.getString("firstName"));
        createAccount2.getLastNameLabel().setText(resourceBundle.getString("lastName"));
        createAccount2.getPhoneNumberLabel().setText(resourceBundle.getString("phoneNumValidation"));
        createAccount2.getEmailLabel().setText(resourceBundle.getString("email"));
        createAccount2.getPhoneNumberLabel().setText(resourceBundle.getString("phonenumber"));
        createAccount2.getPhoneNumberTextField().setText(resourceBundle.getString("phoneNumValidation"));
        createAccount2.getEmployeeCheckBox().setText(resourceBundle.getString("isEmployee"));
        createAccount2.getPassengerCheckBox().setText(resourceBundle.getString("imAPassenger"));
        createAccount2.getCreateAccountButton().setText(resourceBundle.getString("createAccount"));
        createAccount2.getPasswordTextField().setText(resourceBundle.getString("5To20digits"));
    }

    public CreateAccount2 getCreateAccount2() {
        return createAccount2;
    }
}
