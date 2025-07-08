import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            runSigmaCalculator();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void runSigmaCalculator() {
        // current code logic here...
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> numbers = new ArrayList<>();

        System.out.println("=== Sigma Function CLI ===");
        System.out.println("Enter the number of values (min 2, max 100):");

        int n;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine());
                if (n < 2 || n > 100) {
                    System.out.println("Please enter a number between 2 and 100.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }

        for (int i = 1; i <= n; i++) {
            while (true) {
                System.out.print("Enter value " + i + ": ");
                try {
                    double value = Double.parseDouble(scanner.nextLine());
                    numbers.add(value);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a real number.");
                }
            }
        }

        System.out.println("\nChoose Operation:");
        System.out.println("1. Standard Deviation (σ)");
        System.out.println("2. Summation (∑x)");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }

        if (choice == 1) {
            double mean = numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double sumSquaredDiffs = 0;
            for (double val : numbers) {
                sumSquaredDiffs += Math.pow(val - mean, 2);
            }
            double standardDeviation = Math.sqrt(sumSquaredDiffs / n);
            System.out.printf("Standard Deviation (σ): %.4f\n", standardDeviation);
        } else {
            double sum = numbers.stream().mapToDouble(Double::doubleValue).sum();
            System.out.printf("Summation (∑x): %.4f\n", sum);
        }

        scanner.close();
    }
}
