
public class CustomerTransactionManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // System.in = keyboard

        System.out.print("Enter number of transactions: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Eats leftover Enter \n, or next ID becomes blank

        // PARALLEL ARRAYS: Same index i = Same person. 1 array = 1 type only
        String[] customerIds = new String[count]; // ID = text
        double[] transactionAmounts = new double[count]; // Amount = money with decimals
        double[][] itemDetails = new double[count][3]; // 2D: row i = customer, col j = category

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Customer " + (i + 1) + " ---");
            System.out.print("Enter Customer ID/Name: ");
            customerIds[i] = scanner.nextLine();

            System.out.print("Enter Total Transaction Amount: $");
            transactionAmounts[i] = scanner.nextDouble();

            System.out.println("Enter breakdown for 3 item categories:");
            for (int j = 0; j < 3; j++) {
                System.out.print(" Category " + (j + 1) + " amount: $");
                itemDetails[i][j] = scanner.nextDouble(); // customer i, category j
            }
            scanner.nextLine(); // Eats leftover Enter after nextDouble()
        }

        System.out.println("\nSelect Sorting Order:");
        System.out.println("[1] Ascending Order");
        System.out.println("[2] Descending Order");
        System.out.print("Enter choice (1 or 2): ");
        int sortChoice = scanner.nextInt();

        // BUBBLE SORT: Compare neighbours j and j+1, swap if wrong order
        // Outer i = passes, Inner j = comparisons
        // count - 1 - i because biggest already at end like bubble floating up
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                // Ternary ? : = short if-else. Choice 1 = > , Choice 2 = <
                boolean condition = (sortChoice == 1) ?
                    (transactionAmounts[j] > transactionAmounts[j + 1]) :
                    (transactionAmounts[j] < transactionAmounts[j + 1]);

                if (condition) {
                    // SWAP WHOLE CUSTOMER: If only swap Amount, total won't match breakdown
                    // temp = 3rd empty cup, or you lose one value

                    double tempAmount = transactionAmounts[j];
                    transactionAmounts[j] = transactionAmounts[j + 1];
                    transactionAmounts[j + 1] = tempAmount;

                    String tempId = customerIds[j];
                    customerIds[j] = customerIds[j + 1];
                    customerIds[j + 1] = tempId;

                    // tempRow is double[] because row is a whole array, not one number
                    double[] tempRow = itemDetails[j];
                    itemDetails[j] = itemDetails[j + 1];
                    itemDetails[j + 1] = tempRow;
                }
            }
        }

        // DISPLAY: Just print, no more logic. Arrays are already sorted.
        System.out.println("\n==================================================");
        System.out.println(" SORTED CUSTOMER TRANSACTION SUMMARY ");
        System.out.println("==================================================");
        // %-15s = String, 15 spaces, align left so table lines up
        System.out.printf("%-15s | %-18s | %-20s\n", "Customer ID", "Total Amount ($)", "Item Breakdown");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < count; i++) {
            // %.2f = money with 2 decimals: 250.00 | %.1f = category with 1 decimal: 25.0
            // double = STORAGE, %.1f = DISPLAY only
            System.out.printf("%-15s | $%-17.2f | [%.1f, %.1f, %.1f]\n",
                customerIds[i],
                transactionAmounts[i],
                itemDetails[i][0], itemDetails[i][1], itemDetails[i][2]);
        }
        System.out.println("==================================================");
        scanner.close(); // Free keyboard, like turning off faucet
    }
                              }