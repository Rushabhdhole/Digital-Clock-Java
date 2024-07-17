// Import necessary libraries from the java.awt, javax.swing, and java.time packages
import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Define a class named DigitalClock that extends JFrame and implements Runnable
 class DigitalClock extends JFrame implements Runnable {
    private JLabel timeLabel; // Declare a private JLabel variable to display time

    // Constructor for the DigitalClock class
    public DigitalClock() {
        setTitle("Digital Clock"); // Set the title for the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation when the window is closed
        setSize(250, 100); // Set the size of the window
        setLocationRelativeTo(null); // Center the window on the screen

        timeLabel = new JLabel("", SwingConstants.CENTER); // Create a label to display time
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font for the time label

        add(timeLabel); // Add the time label to the JFrame

        // Start a new thread to continuously update the time
        Thread thread = new Thread(this); // Create a new thread using the current class (implements Runnable)
        thread.start(); // Start the thread's execution
    }

    // Override the run method of the Runnable interface
    @Override
    public void run() {
        try {
            while (true) { // Continuous loop to update time
                updateTime(); // Call method to update time
                Thread.sleep(1000); // Wait for 1 second
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle thread interruption
        }
    }

    // Method to update time
    private void updateTime() {
        SwingUtilities.invokeLater(() -> { // Execute code in the event dispatch thread
            // Get current system time in 12-hour format with AM/PM
            LocalDateTime currentTime = LocalDateTime.now(); // Get current date and time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a"); // Define time format
            String time = formatter.format(currentTime); // Format current time according to pattern

            // Update the time label
            timeLabel.setText(time); // Set the formatted time to the label
        });
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Execute code in the event dispatch thread
            DigitalClock clock = new DigitalClock(); // Create an instance of the DigitalClock class
            clock.setVisible(true); // Display the window
        });
    }
 }
