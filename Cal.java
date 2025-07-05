import java.util.*;

public class Main {

    static double lastResult = 0;
    static List<Double> allResults = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("===== Simple Calculator =====");

        while (true) {
            printMenu();

            try {
                System.out.print("Enter your choice: ");
                choice = input.nextInt();

                if (choice >= 1 && choice <= 8) {
                    System.out.print("Enter first number: ");
                    double num1 = input.nextDouble();
                    System.out.print("Enter second number: ");
                    double num2 = input.nextDouble();

                    switch (choice) {
                        case 1:
                            lastResult = add(num1, num2);
                            break;
                        case 2:
                            lastResult = subtract(num1, num2);
                            break;
                        case 3:
                            lastResult = multiply(num1, num2);
                            break;
                        case 4:
                            if (num2 == 0) {
                                System.out.println("Cannot divide by zero.");
                                continue;
                            }
                            lastResult = divide(num1, num2);
                            break;
                        case 5:
                            lastResult = modulus(num1, num2);
                            break;
                        case 6:
                            lastResult = min(num1, num2);
                            break;
                        case 7:
                            lastResult = max(num1, num2);
                            break;
                        case 8:
                            lastResult = average(num1, num2);
                            break;
                    }
                    allResults.add(lastResult);
                    System.out.println("Result: " + lastResult);
                } else if (choice == 9) {
                    System.out.println("Last result: " + lastResult);
                } else if (choice == 10) {
                    System.out.println("All results: " + allResults);
                } else if (choice == 0) {
                    System.out.println("Exiting calculator. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice! Please select from 0 to 10.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numeric values.");
                input.next();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        input.close();
    }

    // ---------- Menu ----------
    public static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("Enter 1 to add the numbers");
        System.out.println("Enter 2 to subtract the numbers");
        System.out.println("Enter 3 to multiply the numbers");
        System.out.println("Enter 4 to divide the numbers");
        System.out.println("Enter 5 to modulus the numbers");
        System.out.println("Enter 6 to find minimum number");
        System.out.println("Enter 7 to find maximum number");
        System.out.println("Enter 8 to find average of numbers");
        System.out.println("Enter 9 to print the last result");
        System.out.println("Enter 10 to print all results");
        System.out.println("Enter 0 to exit");
    }

    // ---------- Operations ----------
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }

    public static double modulus(double a, double b) {
        return a % b;
    }

    public static double min(double a, double b) {
        return Math.min(a, b);
    }

    public static double max(double a, double b) {
        return Math.max(a, b);
    }

    public static double average(double a, double b) {
        return (a + b) / 2;
    }
}
