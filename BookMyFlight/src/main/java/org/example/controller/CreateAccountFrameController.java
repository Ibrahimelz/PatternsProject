package org.example.controller;

import org.example.view.CreateAccountFrame;
import org.example.view.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CreateAccountFrameController {
    private CreateAccountFrame createAccountFrame;

    public CreateAccountFrameController() {
        this.createAccountFrame = new CreateAccountFrame();
        createAccountFrame.getCreateAccount2().getCreateAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = createAccountFrame.getCreateAccount2().getFirstNameTextField().getText();
                String lastName = createAccountFrame.getCreateAccount2().getLastNameTextField().getText();
                String phone = createAccountFrame.getCreateAccount2().getPhoneNumberTextField().getText();
                String email = createAccountFrame.getCreateAccount2().getEmailTextField().getText();
                String password = createAccountFrame.getCreateAccount2().getPasswordTextField().getText();
                if (!isChecked()) {
                    createAccountFrame.getCreateAccount2().getErrorLabel().setText("Unchecked Boxes!");
                } else {
                    try {
                        if (createAccountFrame.getCreateAccount2().getEmployeeCheckBox().isSelected()) {
                            EmployeeController.addEmployee(firstName, lastName, phone, email, password);
                        } else {
                            PassengerController.addRegularPassenger(firstName, lastName, phone, email, password);
                        }
                        switchToLoginForm();
                    } catch (IllegalArgumentException exception) {
                        createAccountFrame.getCreateAccount2().getErrorLabel().setText("Invalid Input!");
                    }
                }
            }
        });
        createAccountFrame.getCreateAccount2().getPasswordTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                createAccountFrame.getCreateAccount2().getPasswordTextField().setText("");
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (createAccountFrame.getCreateAccount2().getPasswordTextField().getText().isBlank() ||
                        createAccountFrame.getCreateAccount2().getPasswordTextField().getText().isEmpty()) {
                    createAccountFrame.getCreateAccount2().getPasswordTextField().setText("5-20 digits");
                }
            }
        });
        createAccountFrame.getCreateAccount2().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createAccountFrame.getCreateAccount2().getLanguageButton().getText().equals("en")) {
                    createAccountFrame.setLanguage("en");
                    createAccountFrame.changeLanguage();
                    createAccountFrame.getCreateAccount2().getLanguageButton().setText("fr");
                } else {
                    createAccountFrame.setLanguage("fr");
                    createAccountFrame.changeLanguage();
                    createAccountFrame.getCreateAccount2().getLanguageButton().setText("en");
                }
            }
        });
    }

    private boolean isChecked() {
        return createAccountFrame.getCreateAccount2().getEmployeeCheckBox().isSelected() ||
                createAccountFrame.getCreateAccount2().getPassengerCheckBox().isSelected();
    }

    private void switchToLoginForm() {
        createAccountFrame.dispose();
        new LoginFrameController();
    }
}
