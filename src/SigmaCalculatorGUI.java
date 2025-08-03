import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigmaCalculatorGUI extends JFrame {

    private JTextField inputField;
    private JLabel resultLabel;
    private JLabel errorLabel;

    public SigmaCalculatorGUI() {
        setTitle("σ - Standard Deviation Calculator");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        JLabel titleLabel = new JLabel("Enter numbers separated by commas:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        inputField = new JTextField();
        add(inputField);

        JButton calculateButton = new JButton("Calculate Standard Deviation");
        add(calculateButton);

        resultLabel = new JLabel("Result: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(errorLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateStandardDeviation();
            }
        });
    }

    private void calculateStandardDeviation() {
        errorLabel.setText("");
        resultLabel.setText("Result: ");

        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            errorLabel.setText("Input cannot be empty.");
            return;
        }

        String[] tokens = input.split(",");
        if (tokens.length < 2) {
            errorLabel.setText("At least two values are required.");
            return;
        }

        if (tokens.length > 100) {
            errorLabel.setText("Too many values. Maximum allowed is 100.");
            return;
        }

        try {
            double[] values = new double[tokens.length];
            double sum = 0;

            for (int i = 0; i < tokens.length; i++) {
                String trimmed = tokens[i].trim();
                if (trimmed.isEmpty()) {
                    throw new NumberFormatException(); // catches duplicate commas
                }

                values[i] = Double.parseDouble(trimmed);
                if (!Double.isFinite(values[i])) {
                    throw new NumberFormatException();
                }
                sum += values[i];
            }

            double mean = sum / values.length;
            double squaredDiffSum = 0;

            for (double val : values) {
                squaredDiffSum += square(val - mean);
            }

            double stdDev = sqrt(squaredDiffSum / values.length);
            resultLabel.setText(String.format("Result: σ = %.4f", stdDev));

        } catch (NumberFormatException ex) {
            errorLabel.setText("Invalid input. Please enter only real numbers.");
        }
    }

    // Manually defined square method
    private double square(double x) {
        return x * x;
    }

    // Manually defined square root method using Newton-Raphson
    private double sqrt(double x) {
        if (x < 0) return -1;
        double guess = x / 2.0;
        double epsilon = 0.00001;
        while (absolute(guess * guess - x) > epsilon) {
            guess = (guess + x / guess) / 2.0;
        }
        return guess;
    }

    // Manually defined absolute value method
    private double absolute(double x) {
        return x < 0 ? -x : x;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SigmaCalculatorGUI().setVisible(true);
        });
    }
}
