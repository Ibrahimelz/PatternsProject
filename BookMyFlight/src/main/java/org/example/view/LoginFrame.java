package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFrame extends JFrame {
    private LoginForm loginForm;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    ResourceBundle resourceBundle;

    public LoginFrame() throws HeadlessException {
        setTitle("Login Page");
        setSize(300, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginForm = new LoginForm();
        changeLanguage();
        add(loginForm.getLoginFormPanel());
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        loginForm.getLoginButton().setText(resourceBundle.getString("login"));
        loginForm.getIsEmployeeCheckBox().setText(resourceBundle.getString("isEmployee"));
        loginForm.getEmailLabel().setText(resourceBundle.getString("email"));
        loginForm.getPasswordLabel().setText(resourceBundle.getString("password"));
        loginForm.getCreateAccountButton().setText(resourceBundle.getString("createAccount"));
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }
}
