import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypingSpeedTestGUI extends JFrame {
    private String username;
    private JTextArea textArea;
    private JTextField inputField;
    private JButton startButton;
    private JButton endButton;
    private JLabel timeLabel;
    private String[] statements = {
            "She works two jobs to make ends meet; at least, that was her reason for not having time to join us.",
            "As he waited for the shower to warm, he noticed that he could hear water change temperature.",
            "In the end, he realized he could see sound and hear words.",
            "Mehran university of science and technology jamshoro.",
            "As he entered the church he could hear the soft voice of someone whispering into a cell phone."
    };
    private String currentStatement;
    private long startTime;
    private Timer timer;
    private int remainingTime;

    public TypingSpeedTestGUI() {
        setTitle("Typing Speed Test");
        setSize(512, 512); // Increased height to accommodate the input field and time label
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        JLabel background;
        ImageIcon img= new ImageIcon("g5g.jpg");
        background= new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,77,1357,460);
        add(background);


        showLoginDialog();
    }

    private void showLoginDialog() {
            JPanel panel = new JPanel(new GridLayout(6, 1)); // Adjusted to 3 rows
            panel.setPreferredSize(new Dimension(400, 300)); // Set panel size
            JLabel background = new JLabel(new ImageIcon("unnamed.jpg"));
            panel.add(background);

            // Username field

            JTextField usernameField = new JTextField();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        //ID
        JTextField ID = new JTextField();
        panel.add(new JLabel("ID :"));
        panel.add(ID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            username = usernameField.getText();
            initComponents();
        } else {
            System.exit(0); // Close the application if the user cancels the login
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(textArea.getFont().deriveFont(20f)); // Set font size for inputField
        mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputField.setFont(inputField.getFont().deriveFont(20f)); // Set font size for inputField

        inputPanel.add(inputField, BorderLayout.NORTH);

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(250, 50));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTest();
            }
        });
        inputPanel.add(startButton, BorderLayout.WEST);

        endButton = new JButton("End");
        endButton.setPreferredSize(new Dimension(250, 50));
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTest();
            }
        });
        inputPanel.add(endButton, BorderLayout.EAST);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        timeLabel = new JLabel("Time remaining: 15 seconds", SwingConstants.CENTER);
        mainPanel.add(timeLabel, BorderLayout.NORTH);

        add(mainPanel);
    }

    private void startTest() {
        // Pick a random statement
        Random rand = new Random();
        currentStatement = statements[rand.nextInt(statements.length)];
        textArea.setText(currentStatement);

        // Record start time
        startTime = System.currentTimeMillis();

        // Set focus to input field
        inputField.requestFocus();

        // Start timer
        remainingTime = 15;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timeLabel.setText("Time remaining: " + remainingTime + " seconds");
                if (remainingTime == 0) {
                    endTest();
                }
            }
        });
        timer.start();
    }

    private void endTest() {
        // Stop the timer
        timer.stop();

        // Get the typed text
        String typedText = inputField.getText();

        // Calculate typing speed
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double timeTaken = elapsedTime / 1000.0; // Time taken in seconds
        int numCharsCorrect = countCorrectCharacters(currentStatement, typedText);
        int numWordsTyped = countWords(typedText);
        double wpm = (double) numWordsTyped / (timeTaken / 60.0);

        // Calculate accuracy
        double accuracy = ((double) numCharsCorrect / currentStatement.length()) * 100.0;

        // Show results
        JOptionPane.showMessageDialog(this,
                String.format("Typing Speed: %.2f WPM%nAccuracy: %.2f%%%nTime taken: %.2f seconds", wpm, accuracy, timeTaken),
                "Dear "+"'"+username+"'"+" your results are: ",
                JOptionPane.INFORMATION_MESSAGE);

        // Clear text fields
        inputField.setText("");
        textArea.setText("");
        timeLabel.setText("Time remaining: 15 seconds");
    }

    private int countCorrectCharacters(String givenText, String typedText) {
        int count = 0;
        int minLength = Math.min(givenText.length(), typedText.length());
        for (int i = 0; i < minLength; i++) {
            if (givenText.charAt(i) == typedText.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private int countWords(String text) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TypingSpeedTestGUI().setVisible(true);
            }
        });
    }
}
