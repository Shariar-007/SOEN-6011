import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * A GUI application that calculates the standard deviation (σ) of a list of numbers.
 * Users enter numbers separated by commas, and the result is computed using
 * manually implemented math functions (square, sqrt, abs).
 *
 * <p>This implementation avoids all built-in math utility methods to meet SOEN 6011 D3 constraints.
 *
 * @author Mohammad Al-Shariar
 */

public class SigmaCalculatorGuiStyleCompliant extends JFrame {

  private JTextField inputField;
  private JLabel resultLabel;
  private JLabel errorLabel;
  private JLabel titleLabel;

  /**
   * Constructs the GUI interface for the Sigma Standard Deviation Calculator.
   */
  public SigmaCalculatorGuiStyleCompliant() {
    setTitle("σ - Standard Deviation Calculator");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new GridLayout(6, 1));

    Font font = new Font("Arial", Font.PLAIN, 16);
    Color errorColor = new Color(180, 0, 0);  // Darker red for better contrast

    // Title with accessibility relationship
    titleLabel = new JLabel("Enter numbers separated by commas:");
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(font);
    add(titleLabel);

    // Input field with accessibility metadata
    inputField = new JTextField();
    inputField.setFont(font);
    inputField.setToolTipText("Enter up to 100 real numbers separated by commas");
    titleLabel.setLabelFor(inputField);  // Critical for screen readers

    inputField.getAccessibleContext().setAccessibleName("Number input field");
    inputField.getAccessibleContext().setAccessibleDescription("Enter comma-separated numbers for standard deviation calculation");
    add(inputField);

    // Calculate button with keyboard support
    JButton calculateButton = new JButton("Calculate Standard Deviation");
    calculateButton.setFont(font);
    calculateButton.setToolTipText("Click to calculate standard deviation");
    calculateButton.setMnemonic(KeyEvent.VK_C);  // Alt+C shortcut
    getRootPane().setDefaultButton(calculateButton);  // Enter key support

    calculateButton.getAccessibleContext().setAccessibleName("Calculate button");
    calculateButton.getAccessibleContext().setAccessibleDescription("Triggers standard deviation calculation");
    add(calculateButton);

    // Result display
    resultLabel = new JLabel("Result: ");
    resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
    resultLabel.setFont(font);
    resultLabel.getAccessibleContext().setAccessibleName("Calculation result");
    add(resultLabel);

    // Error display
    errorLabel = new JLabel(" ");
    errorLabel.setForeground(errorColor);
    errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
    errorLabel.setFont(font);
    errorLabel.getAccessibleContext().setAccessibleName("Error messages");
    add(errorLabel);

    // Action listener with accessibility announcements
    calculateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        calculateStandardDeviation();
        // Force focus for screen reader navigation
        calculateButton.requestFocusInWindow();
      }
    });
  }

  /**
   * Parses the input and calculates the standard deviation.
   */
  private void calculateStandardDeviation() {
    // Clear previous results
    errorLabel.setText("");
    resultLabel.setText("Result: ");

    // Announce changes to accessibility tools
    announceAccessibleText(errorLabel, "");
    announceAccessibleText(resultLabel, "Result: ");

    String input = inputField.getText().trim();
    if (input.isEmpty()) {
      errorLabel.setText("Input cannot be empty.");
      announceAccessibleText(errorLabel, "Input cannot be empty.");
      return;
    }

    String[] tokens = input.split(",");
    if (tokens.length < 2) {
      errorLabel.setText("At least two values are required.");
      announceAccessibleText(errorLabel, "At least two values are required.");
      return;
    }

    if (tokens.length > 100) {
      errorLabel.setText("Too many values. Maximum allowed is 100.");
      announceAccessibleText(errorLabel, "Too many values. Maximum allowed is 100.");
      return;
    }

    try {
      double[] values = new double[tokens.length];
      double sum = 0;

      for (int i = 0; i < tokens.length; i++) {
        String trimmed = tokens[i].trim();
        if (trimmed.isEmpty()) {
          throw new NumberFormatException();
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
      String resultText = String.format("Result: σ = %.4f", stdDev);
      resultLabel.setText(resultText);
      announceAccessibleText(resultLabel, resultText);

    } catch (NumberFormatException ex) {
      errorLabel.setText("Invalid input. Please enter only real numbers.");
      announceAccessibleText(errorLabel, "Invalid input. Please enter only real numbers.");
    }
  }

  /**
   * Announces text changes to accessibility tools
   */
  private void announceAccessibleText(JLabel label, String text) {
    // For Java Accessibility API, we just need to set the text normally
    // Screen readers will detect the change automatically

    // Additional technique: use accessible description for dynamic updates
    label.getAccessibleContext().setAccessibleDescription(text);
  }

  /**
   * Computes the square of a number.
   *
   * @param x the input number
   * @return the square of x
   */
  public double square(double x) {
    return x * x;
  }

  /**
   * Computes the square root of a number using Newton-Raphson method.
   *
   * @param x the input number
   * @return the square root of x
   */
  public double sqrt(double x) {
    if (x < 0) return Double.NaN;

    double guess = x / 2.0;
    double epsilon = 0.00001;
    while (absolute(guess * guess - x) > epsilon) {
      guess = (guess + x / guess) / 2.0;
    }
    return guess;
  }

  /**
   * Computes the absolute value of a number.
   *
   * @param x the input number
   * @return the absolute value of x
   */
  public double absolute(double x) {
    return x < 0 ? -x : x;
  }

  /**
   * Launches the GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      SigmaCalculatorGuiStyleCompliant calculator = new SigmaCalculatorGuiStyleCompliant();
      calculator.setVisible(true);

      // Initial accessibility focus
      calculator.inputField.requestFocusInWindow();
    });
  }
}
