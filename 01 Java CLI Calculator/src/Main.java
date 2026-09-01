import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            try {
                System.out.print("Enter First Number (or q to quit): ");
                String firstInput = scanner.next();
                if(firstInput.equalsIgnoreCase("q")){
                    running = false;
                    continue;
                }
                double firstNumber = Double.parseDouble(firstInput);

                System.out.print("Enter operator (+ - * /): ");
                String operator = scanner.next();

                System.out.print("Enter Second Number: ");
                double secondNumber = scanner.nextDouble();

                double result = calculate(firstNumber, operator, secondNumber);
                System.out.printf("Result: %.2f%n", result);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (ArithmeticException | IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static double calculate(double firstNumber, String operator, double secondNumber){
        double result;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":
                if (secondNumber == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }

                result = firstNumber / secondNumber;
                break;

            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

        return result;
    }
}