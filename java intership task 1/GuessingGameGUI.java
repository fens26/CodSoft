import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI extends JFrame {
    private static final int MAX_ATTEMPTS = 10;
    private Random random;
    private int numberToGuess;
    private int attempts;
    private int roundsWon;
    private int totalAttempts;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        random = new Random();
        roundsWon = 0;
        totalAttempts = 0;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        guessField = new JTextField();
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("Enter a number between 1 and 100", SwingConstants.CENTER);
        attemptsLabel = new JLabel("Attempts left: " + MAX_ATTEMPTS, SwingConstants.CENTER);
        scoreLabel = new JLabel("Rounds won: 0 | Total attempts: 0", SwingConstants.CENTER);

        guessButton.addActionListener(new GuessButtonListener());

        panel.add(feedbackLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(attemptsLabel);
        panel.add(scoreLabel);

        add(panel);

        startNewRound();
    }

    private void startNewRound() {
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        attemptsLabel.setText("Attempts left: " + (MAX_ATTEMPTS - attempts));
        feedbackLabel.setText("Enter a number between 1 and 100");
        guessField.setText("");
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                totalAttempts++;

                if (guess == numberToGuess) {
                    roundsWon++;
                    feedbackLabel.setText("Correct! The number was " + numberToGuess + ". Starting new round.");
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    startNewRound();
                } else if (guess > numberToGuess) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Too low! Try again.");
                }

                if (attempts >= MAX_ATTEMPTS) {
                    feedbackLabel.setText("Out of attempts! The number was " + numberToGuess + ". Starting new round.");
                    JOptionPane.showMessageDialog(null, "Sorry, you've used all attempts. The number was " + numberToGuess + ".");
                    startNewRound();
                }

                attemptsLabel.setText("Attempts left: " + (MAX_ATTEMPTS - attempts));
                scoreLabel.setText("Rounds won: " + roundsWon + " | Total attempts: " + totalAttempts);

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessingGameGUI game = new GuessingGameGUI();
            game.setVisible(true);
        });
    }
}
