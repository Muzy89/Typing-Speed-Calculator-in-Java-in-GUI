This Java program uses the Swing library to create a graphical user interface (GUI) for a typing speed test application. Below is a description of the code libraries and their purposes within the program:
I have also added logos in it which are eaglelogo.png, feedback.png, g5g.jpg and logoeagle.jpg
Libraries Used:
javax.swing.*:

This package provides a set of "lightweight" (all-Java language) components that, to the maximum degree possible, work the same on all platforms.
Components like JFrame, JPanel, JButton, JLabel, JTextArea, JTextField, and JOptionPane are used to create and manage the GUI elements.
java.awt.*:

This package contains all of the classes for creating user interfaces and for painting graphics and images.
Classes like BorderLayout, Dimension, GridLayout, Font, and ImageIcon are used for arranging GUI components and handling layout.
java.awt.event.ActionEvent and java.awt.event.ActionListener:

These are part of the AWT event handling mechanism.
ActionEvent is used to identify a button click event, and ActionListener is an interface that receives action events, typically from buttons.
java.util.Random:

This class is used to generate random numbers, which in this program is used to select a random statement from a predefined list for the typing test.
java.util.regex.Matcher and java.util.regex.Pattern:

These classes are used for pattern matching with regular expressions.
In this program, they are used to count the number of words in the typed text.
Purpose of the Program:
The program is a Typing Speed Test application with the following functionalities:

Login Dialog:

The user is prompted to enter a username and ID upon starting the application. This is done using a JOptionPane with a custom JPanel for input fields.
Main GUI Components:

JTextArea: Displays a randomly chosen statement for the user to type.
JTextField: Input field where the user types the given statement.
JButton (Start): Starts the typing test, displaying a new statement and starting a timer.
JButton (End): Ends the typing test, calculates results, and displays them to the user.
JLabel: Displays the remaining time for the test.
Typing Test Logic:

When the start button is pressed, a random statement is selected, and a timer starts counting down from 15 seconds.
When the time is up or the end button is pressed, the test ends, and the user's typing speed (in words per minute) and accuracy are calculated.
The results are shown in a JOptionPane dialog box.
Calculations:

Typing speed is calculated as words per minute (WPM).
Accuracy is calculated as the percentage of correctly typed characters compared to the total characters in the given statement.
The number of correctly typed characters and words is determined using regular expressions and basic string comparison.
Code Structure:
Constructor (TypingSpeedTestGUI): Initializes the frame and calls showLoginDialog().
showLoginDialog(): Displays the login dialog for username and ID input.
initComponents(): Initializes the main components of the GUI.
startTest(): Starts the typing test by displaying a random statement and starting a timer.
endTest(): Ends the test, calculates results, and displays them.
countCorrectCharacters(): Helper method to count correctly typed characters.
countWords(): Helper method to count the number of words in a given text.
main(): Entry point of the application, runs the GUI.
Overall, this program provides a simple yet functional typing speed test application with a user-friendly graphical interface.
