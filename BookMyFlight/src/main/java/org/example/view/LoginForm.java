package org.example.view;

import javax.swing.*;

public class LoginForm {
    private JButton loginButton;
    private JButton createAccountButton;
    private JTextField emailTextField;
    private JLabel passwordLabel;
    private JCheckBox isEmployeeCheckBox;
    private JLabel emailLabel;
    private JPanel loginFormPanel;
    private JLabel errorLabel;
    private JPasswordField passwordField;
    private JButton languageButton;

    public JButton getLanguageButton() {
        return languageButton;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JPanel getLoginFormPanel() {
        return loginFormPanel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JCheckBox getIsEmployeeCheckBox() {
        return isEmployeeCheckBox;
    }
}
