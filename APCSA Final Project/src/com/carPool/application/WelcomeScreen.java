package com.carPool.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WelcomeScreen {

    private static final Map<String, String> credentials = new HashMap<>();

    static {
        // dummy accounts with username as key and password as value
        credentials.put("user1", "pass1");
        credentials.put("user2", "pass2");
        credentials.put("ashwath.s", "ashwath@108");
        credentials.put("enguyen4", "3th4n2007");
        // can add more
    }

    public static void main(String[] args) {
        // frame for the welcome screen
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(300, 200);

        // label with a welcome message
        JLabel welcomeLabel = new JLabel("Welcome to CarPool App!", SwingConstants.CENTER);

        // button that opens the login screen
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open the login screen
                createLoginScreen();
                // hide the welcome screen
                welcomeFrame.setVisible(false);
            }
        });

        // adds the label and button to the frame
        welcomeFrame.setLayout(new BorderLayout());
        welcomeFrame.add(welcomeLabel, BorderLayout.CENTER);
        welcomeFrame.add(loginButton, BorderLayout.SOUTH);

        // center the window on the screen
        welcomeFrame.setLocationRelativeTo(null);
        // make the frame visible
        welcomeFrame.setVisible(true);
    }

    private static void createLoginScreen() {
        // frame for the login screen
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);

        // panel to hold the login components
        JPanel panel = new JPanel(new GridLayout(3, 2));

        // Add username and password fields to the panel
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        //login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Inside your WelcomeScreen's login button action listener
                if (verifyLogin(username, password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                    loginFrame.setVisible(false); // Hide the login window
                    new HomeScreen(); // Create and show the home screen
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Login Failed. Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Reset fields...
                }
            }
        });
        panel.add(loginButton);

        // placeholder for a message label
        JLabel messageLabel = new JLabel("");
        panel.add(messageLabel);

        // panel to the frame
        loginFrame.add(panel);

        // center the window on the screen
        loginFrame.setLocationRelativeTo(null);
        // make the frame visible
        loginFrame.setVisible(true);
    }

    private static boolean verifyLogin(String username, String password) {
        String validPassword = credentials.get(username);
        return validPassword != null && validPassword.equals(password);
    }
}
