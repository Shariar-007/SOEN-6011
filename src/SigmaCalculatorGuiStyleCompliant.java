import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * <p>This implementation avoids all built-in math utility methods to meet SOEN 6011 D2 constraints.
 *
 * @author Mohammad Al-Shariar
 * @version 1.0
 */
public class SigmaCalculatorGuiStyleCompliant extends JFrame {

  private JTextField inputField;
  private JLabel resultLabel;
  private JLabel errorLabel;

  /**
   * Constructs the GUI interface for the Sigma Standard Deviation Calculator.
   */
  public SigmaCalculatorGuiStyleCompliant() {
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
      @Override
      public void actionPerformed(ActionEvent e) {
        calculateStandardDeviation();
      }
    });
  }

  /**
   * Parses the input and calculates the standard deviation.
   */
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
      resultLabel.setText(String.format("Result: σ = %.4f", stdDev));

    } catch (NumberFormatException ex) {
      errorLabel.setText("Invalid input. Please enter only real numbers.");
    }
  }

  /**
   * Computes the square of a number.
   *
   * @param x the input number
   * @return the square of x
   */
  private double square(double x) {
    return x * x;
  }

  /**
   * Computes the square root of a number using Newton-Raphson method.
   *
   * @param x the input number
   * @return the square root of x
   */
  private double sqrt(double x) {
    if (x < 0) {
      return -1;
    }

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
  private double absolute(double x) {
    return x < 0 ? -x : x;
  }

  /**
   * Launches the GUI application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new SigmaCalculatorGuiStyleCompliant().setVisible(true);
      }
    });
  }
}
