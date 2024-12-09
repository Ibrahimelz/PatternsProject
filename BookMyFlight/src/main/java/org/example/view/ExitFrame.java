package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ExitFrame extends JFrame {
    private Exit exit;
    @Setter
    @Getter
    private String language = "en";
    private Locale currentLocale;
    private ResourceBundle resourceBundle;

    public ExitFrame() throws HeadlessException {
        setTitle("Exit Page");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        exit = new Exit();
        add(exit.getExitPanel());
        setVisible(true);
    }

    /**
     * Applies i18 to the application by changing the language with english and French
     */
    public void changeLanguage() {
        currentLocale = Locale.of(language, "CA");
        resourceBundle = ResourceBundle.getBundle("messages.message", currentLocale);
        exit.getRefundTicketsButton().setText(resourceBundle.getString("requestRefunds"));
        exit.getLogoutButton().setText(resourceBundle.getString("logout"));
    }

    public Exit getExit() {
        return exit;
    }
}
