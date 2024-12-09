package org.example.view;

import javax.swing.*;


public class BuyTicket {
    private JPanel buyTicketPanel;
    private JButton payTicketsButton;
    private JCheckBox isEmployeeCheckBox;
    private JLabel cardNumberLabel;
    private JLabel cardHolderNameLabel;
    private JLabel cardExpirationDateLabel;
    private JLabel cvcLabel;
    private JTextField cardNumberTextField;
    private JTextField cardHolderNameTextField;
    private JTextField cvcTextField;
    private JTextField expirationDateTextField;
    private JLabel errorMessageLabel;
    private JButton languageButton;

    public JPanel getBuyTicketPanel() {
        return buyTicketPanel;
    }

    public JButton getLanguageButton() {
        return languageButton;
    }

    public JLabel getCardNumberLabel() {
        return cardNumberLabel;
    }

    public JLabel getCardHolderNameLabel() {
        return cardHolderNameLabel;
    }

    public JLabel getCardExpirationDateLabel() {
        return cardExpirationDateLabel;
    }

    public JLabel getCvcLabel() {
        return cvcLabel;
    }

    public JTextField getCardNumberTextField() {
        return cardNumberTextField;
    }

    public JTextField getCardHolderNameTextField() {
        return cardHolderNameTextField;
    }

    public JTextField getCvcTextField() {
        return cvcTextField;
    }

    public JTextField getExpirationDateTextField() {
        return expirationDateTextField;
    }

    public JLabel getErrorMessageLabel() {
        return errorMessageLabel;
    }

    public JButton getPayTicketsButton() {
        return payTicketsButton;
    }

    public JCheckBox getIsEmployeeCheckBox() {
        return isEmployeeCheckBox;
    }
}
