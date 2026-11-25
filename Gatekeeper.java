import java.util.Scanner;
import java.util.InputMismatchException;

// ==========================================
// INTERACTIVE MAIN APPLICATION (Step 4)
// ==========================================
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create the initial account instance
        BankAccount account = new BankAccount("ACC-1001", "Richard Broa", 500.00);

        System.out.println("=== BANK ACCOUNT EXCEPTION GATEKEEPER ===");
        System.out.printf("Account Created: %s (Holder: %s, Balance: $%.2f)\n", 
            account.getAccountNumber(), account.getAccountHolder(), account.getBalance());

        // 'try': Encloses code where an exception might potentially happen
        try {
            System.out.print("Enter withdrawal amount: ");
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);
        } 
        // 'catch': Captures and handles specific exceptions so the program doesn't crash
        catch (InsufficientFundsException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[EXCEPTION CAUGHT]: Invalid input type! Please enter a valid number.");
        }

        try {
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("[EXCEPTION CAUGHT]: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("[EXCEPTION CAUGHT]: Invalid input type! Please enter a valid number.");
        } 
        // 'finally': Always runs at the end, regardless of whether an exception occurred or not
        finally {
            System.out.println("[SESSION COMPLETE]: Transaction log updated.");
        }

        System.out.printf("Current Balance: $%.2f\n", account.getBalance());
        scanner.close();
    }
}