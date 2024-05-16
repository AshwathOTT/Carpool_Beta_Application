package com.carPool.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Carpool App - Home");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Opens in full window size
        initializeUI();
    }

    private void initializeUI() {
        // Main panel that contains everything
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // Top panel for user's info and tokens
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("User: user")); // Placeholder for user info
        topPanel.add(new JLabel("Ride Tokens: 5")); // Placeholder for tokens
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel has button to open Google Maps
        JButton openMapButton = new JButton("Open Google Maps");
        openMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGoogleMaps();
            }
        });
        mainPanel.add(openMapButton, BorderLayout.CENTER);

        // Side panel is for search and actions
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        // Button to find users within a 5-mile radius
        JButton findUsersButton = new JButton("Find Nearby Users");
        sidePanel.add(findUsersButton);
        sidePanel.add(Box.createVerticalStrut(10)); // Spacer

        // Button to refresh tokens or other stuff
        JButton refreshTokensButton = new JButton("Refresh Tokens");
        sidePanel.add(refreshTokensButton);

        mainPanel.add(sidePanel, BorderLayout.EAST);

        // Making sure the UI components are visible
        pack(); // Adjusts window to fit the preferred size and layouts of its subcomponents
        setVisible(true);
    }

    private void openGoogleMaps() {
        if (Desktop.isDesktopSupported()) {
            try {
                // Specifies the path to the HTML file on my local system
                File htmlFile = new File("/Users/ashwath.s49/IdeaProjects/APCSA Final Project/src/googlemapsjavascript/index.html");
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to open the map: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Desktop is not supported", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Runs the application
        SwingUtilities.invokeLater(() -> new HomeScreen());
    }
}
